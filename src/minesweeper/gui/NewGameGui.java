package minesweeper.gui;

import java.awt.*;

import javax.swing.*;

import minesweeper.backend.NewGame;
import minesweeper.backend.NewGame.ButtonActionListener;

public class NewGameGui extends JPanel {
	
	private MainGui mainGui;
	
	public NewGameGui(NewGame newGame, MainGui mainGui) {
		this.mainGui = mainGui;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = GridBagConstraints.RELATIVE;
		
		ButtonActionListener actionListener = newGame.initialiseListener(this);
		
		JLabel label = new JLabel("Create a new game");
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 0, 10);
		add(label, constraint);
		
		JButton easyButton = new JButton("Beginner");
		easyButton.addActionListener(actionListener);
		constraint.insets = new Insets(0, 10, 0, 10);
		add(easyButton, constraint);
		
		JButton mediumButton = new JButton("Intermediate");
		mediumButton.addActionListener(actionListener);
		add(mediumButton, constraint);
		
		JButton hardButton = new JButton("Expert");
		hardButton.addActionListener(actionListener);
		add(hardButton, constraint);
		
		JButton customButton = new JButton("Custom");
		customButton.addActionListener(actionListener);
		constraint.insets = new Insets(0, 10, 10, 10);
		add(customButton, constraint);
	}

	public void showCustomMenu() {
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = GridBagConstraints.RELATIVE;
		
		JLabel topLabel = new JLabel("Custom settings");
		Font font = topLabel.getFont();
		topLabel.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 0, 10);
		constraint.gridwidth = 2;
		add(topLabel, constraint);
		
		JLabel horizontalLabel = new JLabel("Horizontal columns:");
		constraint.gridwidth = 1;
		add(horizontalLabel, constraint);
		
		JLabel verticalLabel = new JLabel("Vertical rows:");
		add(verticalLabel, constraint);
		
		JLabel amountOfBombsLabel = new JLabel("Amount of bombs:");
		add(amountOfBombsLabel, constraint);
		
		JTextField horizontalCountField = new JTextField(5);
		constraint.gridx = 2;
		add(horizontalCountField, constraint);
		
		JTextField verticalCountField = new JTextField(5);
		add(verticalCountField, constraint);
		
		JTextField bombCountField = new JTextField(5);
		add(bombCountField, constraint);
		
		mainGui.pack();
	}
}
