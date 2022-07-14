package minesweeper.backend;

import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import minesweeper.gui.components.*;
import minesweeper.gui.components.MineCell.CellState;

public class MineGrid {
	private MainBackend backend;
	private MineGridGui guiGrid;
	private MineGridCellPressedListener cellListener;
	
	private boolean[][] grid;
	private boolean gridGenerated = false;
	
	private GameState gameState = GameState.NOT_STARTED;
	private int amountOfCellsCleared = 0;
	private int amountOfCellsFlagged = 0;
	
	private int horizontalCount;
	private int verticalCount;
	private int amountOfMines;
	private int totalAmountOfCells;
	
	public MineGrid(int horizontalCount, int verticalCount, int amountOfMines, MainBackend backend) {

		this.horizontalCount = horizontalCount;
		this.verticalCount = verticalCount;
		
		this.totalAmountOfCells = horizontalCount * verticalCount;
		
		this.amountOfMines = amountOfMines;
		
		this.backend = backend;
	}
	
	public int[] getDimensions() {
		int[] dimensions = new int[2];
		
		dimensions[0] = horizontalCount;
		dimensions[1] = verticalCount;
		
		return dimensions;
	}
	
	public MineGridCellPressedListener initialiseCellListener(MineGridGui guiGrid) {
		cellListener = new MineGridCellPressedListener(guiGrid);
		
		return cellListener;
	}

	private void generateRandomLayout(int startPositionHorizontal, int startPositionVertical) {
		
		grid = new boolean[verticalCount][horizontalCount];
		
		for (int i = 0; i < amountOfMines; i++) {
			addRandomMine(startPositionHorizontal, startPositionVertical);
		}
		
		if (!ensureCorrectAmountOfMines()) {
			System.out.println("this should not happen");
			//just run it again and hope this doesn't happen too often lol
			generateRandomLayout(startPositionHorizontal, startPositionVertical);
		}
		

		System.out.println(Arrays.deepToString(grid).replace("]", "]\n").replace("false", "0").replace("true", "1"));
		gridGenerated = true;
		gameState = GameState.STARTED;
	}

	private void addRandomMine(int startPositionHorizontal, int startPositionVertical) {
		Random rand = new Random();
		
		int randHorizontal = rand.nextInt(horizontalCount);
		int randVertical = rand.nextInt(verticalCount);
		
		if (grid[randVertical][randHorizontal] == false
				&& !((randHorizontal >= startPositionHorizontal-1 && randHorizontal <= startPositionHorizontal+1)
				&& (randVertical >= startPositionVertical-1 && randVertical <= startPositionVertical+1))) {
			
			grid[randVertical][randHorizontal] = true;
		}
		else
			addRandomMine(startPositionHorizontal, startPositionVertical);
	}
	
	private boolean ensureCorrectAmountOfMines() {
		int count = 0;
		
		for (boolean[] row : grid) {
			for (boolean cell : row) {
				if (cell)
					count++;
			}
		}
		
		if (count != amountOfMines)
			return false;
		else
			return true;
	}
	
	private void revealCell(MineCell mineCell, int horizontalPosition, int verticalPosition) {
		if (mineCell.getCellState() != CellState.NOT_CLICKED && mineCell.getCellState() != CellState.IS_FLAGGED) {
			System.out.println("already clicked");
			return;
		}
		
		if (grid[verticalPosition][horizontalPosition] == true) {
			mineCell.revealCell(CellState.IS_MINE, 0);
			System.out.println("triggered mine");
			
			//weird way to not make it infinitely loop
			if (gameState != GameState.FAILED) {
				gameState = GameState.FAILED;
				for (int i = 0; i < verticalCount; i++) {
					for (int j = 0; j < horizontalCount; j++) {
						if (grid[i][j]) {
							revealCell(j, i);
						}
					}
				}
			}
		}
		else {
			int surroundingMinesCount = 0;
			
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (verticalPosition+i < 0
							|| verticalPosition+i >= verticalCount
							|| horizontalPosition+j < 0
							|| horizontalPosition+j >= horizontalCount)
						continue;
					
					if (grid[verticalPosition+i][horizontalPosition+j])
						surroundingMinesCount++;
				}
			}
			
			mineCell.revealCell(CellState.IS_CLEAR, surroundingMinesCount);
			
			if (surroundingMinesCount == 0) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (verticalPosition+i < 0
								|| verticalPosition+i >= verticalCount
								|| horizontalPosition+j < 0
								|| horizontalPosition+j >= horizontalCount)
							continue;
						
						revealCell(horizontalPosition+j, verticalPosition+i);
					}
				}
			}
		}
		
		amountOfCellsCleared++;
	}
	
	private void revealCell(int horizontalPosition, int verticalPosition) {
		MineCell cell = guiGrid.getMineCell(horizontalPosition, verticalPosition);
		
		revealCell(cell, horizontalPosition, verticalPosition);
	}

	private void checkIfCompleted() {
		if (amountOfCellsCleared == totalAmountOfCells-amountOfMines && amountOfCellsFlagged == amountOfMines) {
			gameState = GameState.FINISHED;
		}
		
		if (gameState == GameState.FAILED || gameState == GameState.FINISHED) {
			System.out.println("completed " + gameState.toString());
			guiGrid.disableGrid();
			backend.completeGame(gameState);
		}
	}
	
	public class MineGridCellPressedListener extends MouseAdapter {
		

		public MineGridCellPressedListener(MineGridGui gridGui) {
			guiGrid = gridGui;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			MineCell pressedCell = (MineCell)e.getSource();
			
			if (!pressedCell.isEnabled()) {
				return;
			}
			
			System.out.println("pressed cell");
			
			int[] position = pressedCell.getPosition();
			int horizontalPosition = position[0];
			int verticalPosition = position[1];
			
			if (!gridGenerated) {
				generateRandomLayout(horizontalPosition, verticalPosition);
			}
			
			if (SwingUtilities.isRightMouseButton(e)) {
				pressedCell.flagCell();
				amountOfCellsFlagged++;
			}
			else if (pressedCell.getCellState() == CellState.IS_FLAGGED) {
				pressedCell.removeFlag();
				amountOfCellsFlagged--;
			}
			else {			
				/*//cheaty reveal everything for debug purposes
				if (horizontalPosition == 0 && verticalPosition == 0) {
					for (int i = 0; i < horizontalCount; i++ ) {
						for (int j = 0; j < verticalCount; j++) {
							revealCell(i, j);
						}
					}
				}*/
			
				revealCell(pressedCell, horizontalPosition, verticalPosition);
			}
			guiGrid.repaint();
			
			checkIfCompleted();
		}
	}
}
