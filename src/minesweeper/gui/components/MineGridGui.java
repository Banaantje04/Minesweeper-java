package minesweeper.gui.components;

import java.awt.*;
import javax.swing.*;

public class MineGridGui {
	private JPanel gridContainer;
	
	public MineGridGui(int horizontalCount, int verticalCount) {
		gridContainer = new JPanel(new GridLayout(verticalCount, horizontalCount));
		
		for (int i = 0; i < verticalCount; i++) {
			for (int j = 0; j < horizontalCount; j++) {
				gridContainer.add(new MineButton(j, i));
			}
		}
	}
	
	public JPanel getGridContainer() {
		return gridContainer;
	}
}
