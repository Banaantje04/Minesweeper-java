package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

import minesweeper.gui.components.MineGrid;

public class MainGui {
	private JFrame frame;
	private Container pane;
	
	public MainGui() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
	}
	
	public void generateAndAddMineGrid(int horizontalCount, int verticalCount) {
		MineGrid grid = new MineGrid(horizontalCount, verticalCount);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
		constraint.weighty = 0.5;
		constraint.fill = GridBagConstraints.BOTH;
		
		pane.add(grid.getGridContainer(), constraint);
	}

	public void setVisible() {
		frame.pack();
		frame.setVisible(true);
	}
}
