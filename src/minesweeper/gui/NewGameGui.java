package minesweeper.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import minesweeper.backend.NewGame;
import minesweeper.backend.NewGame.ButtonActionListener;

public class NewGameGui extends JPanel {
	
	public NewGameGui(NewGame newGame) {
		if (newGame == null) {
			System.out.println("newgame backend is null");
		}
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		
		ButtonActionListener actionListener = newGame.initialiseListener(this);
		
		JLabel label = new JLabel("Create a new game");
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 0, 10);
		constraint.gridy = 0;
		add(label, constraint);
		
		JButton easyButton = new JButton("Beginner");
		easyButton.addActionListener(actionListener);
		constraint.gridy = 1;
		constraint.insets = new Insets(0, 10, 0, 10);
		add(easyButton, constraint);
		
		JButton mediumButton = new JButton("Intermediate");
		mediumButton.addActionListener(actionListener);
		constraint.gridy = 2;
		add(mediumButton, constraint);
		
		JButton hardButton = new JButton("Expert");
		hardButton.addActionListener(actionListener);
		constraint.gridy = 3;
		add(hardButton, constraint);
		
		JButton customButton = new JButton("Custom");
		customButton.addActionListener(actionListener);
		constraint.gridy = 4;
		constraint.insets = new Insets(0, 10, 10, 10);
		add(customButton, constraint);
	}

	public void showCustomMenu() {
		
	}
}
