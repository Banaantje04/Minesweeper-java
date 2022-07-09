package minesweeper.gui.components;

import java.awt.*;
import javax.swing.*;

public class MineGrid {
	private JPanel gridContainer;
	
	public MineGrid(int horizontalCount, int verticalCount) {
		gridContainer = new JPanel(new GridLayout(verticalCount, horizontalCount));
		
		for (int i = 0; i < verticalCount; i++) {
			for (int j = 0; j < horizontalCount; j++) {
				gridContainer.add(new JButton());
			}
		}
	}
	
	public JPanel getGridContainer() {
		return gridContainer;
	}
}
