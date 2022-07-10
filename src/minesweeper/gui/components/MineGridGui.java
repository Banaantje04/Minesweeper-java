package minesweeper.gui.components;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.MineGrid;

public class MineGridGui {
	private JPanel gridContainer;
	private MineGrid grid;
	
	public MineGridGui(MineGrid grid) {
		this.grid = grid;
		
		int[] dimensions = grid.getDimensions();
		
		int horizontalCount = dimensions[0];
		int verticalCount = dimensions[1];
		
		gridContainer = new JPanel(new GridLayout(verticalCount, horizontalCount));
		
		for (int i = 0; i < verticalCount; i++) {
			for (int j = 0; j < horizontalCount; j++) {
				MineCell cell = new MineCell(j, i);
				cell.addActionListener(grid.getCellListener());
				
				gridContainer.add(cell);
			}
		}
	}
	
	public JPanel getGridContainer() {
		return gridContainer;
	}
}
