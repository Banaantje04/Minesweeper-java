package minesweeper.backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public class ButtonActionListener implements ActionListener {
		
		public ButtonActionListener(NewGameGui gui) {
			newGameGui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Difficulty chosen");
			GameDifficulty difficulty = GameDifficulty.valueOf(e.getActionCommand().toUpperCase());
			System.out.println(difficulty);
			
			DifficultySettings settings;
			
			if (difficulty == GameDifficulty.CUSTOM) {
				newGameGui.showCustomMenu();
			}
			
			switch (difficulty) {
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
		
		private enum GameDifficulty {
			BEGINNER,
			INTERMEDIATE,
			EXPERT,
			CUSTOM
		}
		
	}
	
}
