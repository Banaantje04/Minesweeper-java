package minesweeper.gui;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

import minesweeper.backend.NewGame;
import minesweeper.backend.NewGame.CustomSettingsActionListener;
import minesweeper.backend.NewGame.DifficultyButtonActionListener;

public class NewGameGui extends JPanel {
	
	private MainGui mainGui;
	private NewGame newGame;
	
	private boolean showedCustomSettings = false;
	
	private JFormattedTextField[] customSettings = new JFormattedTextField[3];
	
	public NewGameGui(NewGame newGame, MainGui mainGui) {
		this.mainGui = mainGui;
		this.newGame = newGame;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = GridBagConstraints.RELATIVE;
		
		DifficultyButtonActionListener actionListener = newGame.initialiseDifficultyButtonActionListener(this);
		
		JLabel label = new JLabel("Create a new game");
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 5, 10);
		add(label, constraint);
		
		JButton easyButton = new JButton("Beginner");
		easyButton.addActionListener(actionListener);
		constraint.insets = new Insets(0, 10, 5, 10);
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
		if (showedCustomSettings) {
			return;
		}
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 1;
		constraint.gridy = GridBagConstraints.RELATIVE;
		
		JLabel topLabel = new JLabel("Custom settings");
		Font font = topLabel.getFont();
		topLabel.setFont(new Font(font.getName(), Font.PLAIN, 15));
		
		constraint.insets = new Insets(10, 10, 5, 10);
		constraint.gridwidth = 2;
		add(topLabel, constraint);
		
		JLabel horizontalLabel = new JLabel("Horizontal columns:");
		constraint.anchor = GridBagConstraints.LINE_END;
		constraint.insets = new Insets(0, 10, 5, 5);
		constraint.gridwidth = 1;
		add(horizontalLabel, constraint);
		
		JLabel verticalLabel = new JLabel("Vertical rows:");
		add(verticalLabel, constraint);
		
		JLabel amountOfBombsLabel = new JLabel("Amount of bombs:");
		add(amountOfBombsLabel, constraint);
		
		CustomSettingsActionListener actionListener = newGame.initialiseCustomSettingsActionListener();
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		
		JFormattedTextField horizontalCountField = new JFormattedTextField(numberFormat);
		horizontalCountField.setColumns(5);
		horizontalCountField.addActionListener(actionListener);
		customSettings[0] = horizontalCountField;
		constraint.insets = new Insets(0, 5, 5, 10);
		constraint.anchor = GridBagConstraints.LINE_START;
		constraint.gridx = 2;
		add(horizontalCountField, constraint);
		
		JFormattedTextField verticalCountField = new JFormattedTextField(numberFormat);
		verticalCountField.setColumns(5);
		verticalCountField.addActionListener(actionListener);
		customSettings[1] = verticalCountField;
		add(verticalCountField, constraint);
		
		JFormattedTextField bombCountField = new JFormattedTextField(numberFormat);
		bombCountField.setColumns(5);
		bombCountField.addActionListener(actionListener);
		customSettings[2] = bombCountField;
		add(bombCountField, constraint);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(actionListener);
		constraint.anchor = GridBagConstraints.CENTER;
		constraint.insets = new Insets(0, 10, 10, 10);
		constraint.gridwidth = 2;
		constraint.gridx = 1;
		add(confirmButton, constraint);
		
		showedCustomSettings = true;
		
		mainGui.pack();
	}

	public void showCustomSettingsErrors(CustomSettingsErrors[] errors) {
		//TODO: better messages than just these enum names
		JOptionPane.showMessageDialog(mainGui, errors, "Errors", JOptionPane.ERROR_MESSAGE, new ImageIcon(this.getClass().getResource("/assets/Mine.png")));
	}

	public JFormattedTextField[] getCustomSettings() {
		return customSettings;
	}
	
	public enum CustomSettingsErrors {
		TOOMANYBOMBS,
		HORIZONTALZERO,
		VERTICALZERO,
		
	}
}
