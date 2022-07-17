package minesweeper.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import minesweeper.backend.MineGrid;
import minesweeper.gui.components.MineGridGui;

public class GameGui extends JPanel {
	private MineGridGui gridGui;
	
	public GameGui(MineGrid grid) {
		setLayout(new GridBagLayout());
		
		gridGui = new MineGridGui(grid);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 1.0;
		constraint.weighty = 1.0;
		constraint.fill = GridBagConstraints.BOTH;
		
		add(gridGui, constraint);
	}

}
