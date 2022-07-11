package minesweeper.gui.components;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.MineGrid;

public class MineGridGui extends JPanel{
	private MineGrid grid;
	
	public MineGridGui(MineGrid grid) {
		this.grid = grid;
		
		int[] dimensions = grid.getDimensions();
		
		int horizontalCount = dimensions[0];
		int verticalCount = dimensions[1];
		
		setLayout(new GridLayout(verticalCount, horizontalCount));
		setPreferredSize(new Dimension(32*horizontalCount, 32*verticalCount));
		
		for (int i = 0; i < verticalCount; i++) {
			for (int j = 0; j < horizontalCount; j++) {
				MineCell cell = new MineCell(j, i);
				cell.addActionListener(grid.getCellListener());
				
				add(cell);
			}
		}
	}
}
