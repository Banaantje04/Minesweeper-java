package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.MainBackend;
import minesweeper.backend.MineGrid;
import minesweeper.gui.components.MineGridGui;

public class MainGui {
	private JFrame frame;
	private Container pane;
	private MainBackend backend;
	
	public MainGui(MainBackend backend) {
		this.backend = backend;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
	}
	
	public void generateAndAddMineGrid(MineGrid grid) {
		MineGridGui gridGui = new MineGridGui(grid);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
		constraint.weighty = 0.5;
		constraint.fill = GridBagConstraints.BOTH;
		
		pane.add(gridGui.getGridContainer(), constraint);
	}

	public void setVisible() {
		frame.pack();
		frame.setVisible(true);
	}
}
