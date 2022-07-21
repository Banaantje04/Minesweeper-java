package minesweeper.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import minesweeper.backend.NewGame;

public class NewGameGui extends JPanel {
	
	public NewGameGui(NewGame newGame) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		
		JLabel label = new JLabel("Create a new game");
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 0, 10);
		constraint.gridy = 0;
		add(label, constraint);
		
		JButton easyButton = new JButton("Easy");
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 0, 10);
		add(easyButton, constraint);
		
		JButton mediumButton = new JButton("Medium");
		constraint.gridy = 2;
		add(mediumButton, constraint);
		
		JButton hardButton = new JButton("Hard");
		constraint.gridy = 3;
		add(hardButton, constraint);
		
		JButton customButton = new JButton("Custom");
		constraint.gridy = 4;
		constraint.insets = new Insets(0, 10, 10, 10);
		add(customButton, constraint);
	}

	public void showCustomMenu() {
		//TODO add custom parameters
	}
}
