package minesweeper.backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import minesweeper.gui.NewGameGui;

public class NewGame {
	
	private final static DifficultySettings BEGINNER = new DifficultySettings(9, 9, 10);
	private final static DifficultySettings INTERMEDIATE = new DifficultySettings(16, 16, 40);
	private final static DifficultySettings EXPERT = new DifficultySettings(30, 16, 99);
	
	private MainBackend backend;
	private NewGameGui newGameGui;

	public NewGame(MainBackend mainBackend) {
		backend = mainBackend;
	}
	
	public ButtonActionListener initialiseListener(NewGameGui gui) {
		return new ButtonActionListener(gui);
	}

	public DifficultySettings getCustomSettings() {
		JTextField[] settingFields = newGameGui.getCustomSettings();
		
		int horizontalCount = Integer.parseInt(settingFields[0].getText());
		int verticalCount = Integer.parseInt(settingFields[1].getText());
		int amountOfBombs = Integer.parseInt(settingFields[2].getText());
		
		return new DifficultySettings(horizontalCount, verticalCount, amountOfBombs);
	}
	
	public class ButtonActionListener implements ActionListener {
		
		public ButtonActionListener(NewGameGui gui) {
			newGameGui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Difficulty chosen");
			NewGameButtonChoice choice = NewGameButtonChoice.valueOf(e.getActionCommand().toUpperCase());
			System.out.println(choice);
			
			DifficultySettings settings;
			
			if (choice == NewGameButtonChoice.CUSTOM) {
				newGameGui.showCustomMenu();
			}
			else if (choice == NewGameButtonChoice.CUSTOMCONFIRM) {
				settings = getCustomSettings();
				
				backend.createNewMineGrid(settings);
			}
			else {
				switch (choice) {
				case BEGINNER:
					System.out.println("EASY");
					settings = BEGINNER;
					break;
				case INTERMEDIATE:
					System.out.println("MEDIUM");
					settings = INTERMEDIATE;
					break;
				case EXPERT:
					System.out.println("HARD");
					settings = EXPERT;
					break;
				default:
					settings = BEGINNER;
					break;
				}
			
				System.out.println("Selected difficulty is: " + settings);
			
				backend.createNewMineGrid(settings);
			}
		}
		
		private enum NewGameButtonChoice {
			BEGINNER,
			INTERMEDIATE,
			EXPERT,
			CUSTOM,
			CUSTOMCONFIRM
		}
		
	}
	
}
