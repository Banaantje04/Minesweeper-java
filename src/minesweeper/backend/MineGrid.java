package minesweeper.backend;

import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import minesweeper.gui.components.MineCell;
import minesweeper.gui.components.MineCell.ClickedState;

public class MineGrid {
	
	private boolean[][] grid;
	
	private int horizontalCount;
	private int verticalCount;
	private int amountOfMines;
	private MineGridCellPressedListener cellListener;
	
	public MineGrid(int horizontalCount, int verticalCount, int amountOfMines) {

		this.horizontalCount = horizontalCount;
		this.verticalCount = verticalCount;
		this.amountOfMines = amountOfMines;
		
		cellListener = new MineGridCellPressedListener();
		
		generateRandomLayout();
	}
	
	public int[] getDimensions() {
		int[] dimensions = new int[2];
		
		dimensions[0] = horizontalCount;
		dimensions[1] = verticalCount;
		
		return dimensions;
	}
	
	public MineGridCellPressedListener getCellListener() {
		return cellListener;
	}

	private void generateRandomLayout() {
		
		grid = new boolean[verticalCount][horizontalCount];
		
		for (int i = 0; i < amountOfMines; i++) {
			addRandomMine();
		}
		
		if (!ensureCorrectAmountOfMines()) {
			System.out.println("this should not happen");
			//just run it again and hope this doesn't happen too often lol
			generateRandomLayout();
		}
		

		System.out.println(Arrays.deepToString(grid).replace("]", "]\n").replace("false", "0").replace("true", "1"));
	}

	private void addRandomMine() {
		Random rand = new Random();
		
		int randHorizontal = rand.nextInt(horizontalCount);
		int randVertical = rand.nextInt(verticalCount);
		
		if (grid[randVertical][randHorizontal] == false)
			grid[randVertical][randHorizontal] = true;
		else
			addRandomMine();
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
	
	private class MineGridCellPressedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("pressed cell");
			
			MineCell pressedCell = (MineCell)e.getSource();
			
			int[] position = pressedCell.getPosition();
			int horizontalPosition = position[0];
			int verticalPosition = position[1];
			
			if (grid[verticalPosition][horizontalPosition] == true) {
				pressedCell.setCellState(ClickedState.IS_MINE, 0);
				System.out.println("triggered mine");
				
				//TODO: add failure
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
				
				pressedCell.setCellState(ClickedState.IS_CLEAR, surroundingMinesCount);
				System.out.println(surroundingMinesCount);
			}
		}
		
	}
	
//	public static void main(String[] args) {
//		MineGrid grid = new MineGrid(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
//		
//		System.out.println(Arrays.deepToString(grid.grid).replace("]", "]\n").replace("false", "0").replace("true", "1"));
//	}
}
