package minesweeper.gui.components;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.MineGrid;
import minesweeper.backend.MineGrid.MineGridCellPressedListener;
import minesweeper.gui.components.MineCell;

public class MineGridGui extends JPanel{
	private MineGrid grid;
	private MineCell[][] cellGrid;
	
	public MineGridGui(MineGrid grid) {
		this.grid = grid;
		
		int[] dimensions = grid.getDimensions();
		
		int horizontalCount = dimensions[0];
		int verticalCount = dimensions[1];
		
		setLayout(new GridLayout(verticalCount, horizontalCount));
		setPreferredSize(new Dimension(32*horizontalCount, 32*verticalCount));
		
		MineGridCellPressedListener cellListener = grid.initialiseCellListener(this);
		
		cellGrid = new MineCell[verticalCount][horizontalCount];
		
		for (int i = 0; i < verticalCount; i++) {
			for (int j = 0; j < horizontalCount; j++) {
				MineCell cell = new MineCell(j, i);
				cell.addActionListener(cellListener);
				
				add(cell);
				cellGrid[i][j] = cell;
			}
		}
	}
	
	public MineCell getMineCell(int horizontalPosition, int verticalPosition) {
		return cellGrid[verticalPosition][horizontalPosition];
	}
}
