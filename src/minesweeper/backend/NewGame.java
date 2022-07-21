package minesweeper.backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import minesweeper.gui.NewGameGui;

public class NewGame {
	
	private final static DifficultySettings EASY = new DifficultySettings(0, 0, 0);
	private final static DifficultySettings MEDIUM = new DifficultySettings(0, 0, 0);
	private final static DifficultySettings HARD = new DifficultySettings(0, 0, 0);
	
	private MainBackend backend;
	private NewGameGui gui;

	public NewGame(MainBackend mainBackend) {
		this.backend = backend;
	}
	
	public ButtonActionListener initialiseListener(NewGameGui gui) {
		return new ButtonActionListener(gui);
	}
	
	public class ButtonActionListener implements ActionListener {
		
		public ButtonActionListener(NewGameGui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			GameDifficulty difficulty = GameDifficulty.valueOf(e.getActionCommand());
			
			if (difficulty == GameDifficulty.CUSTOM) {
				gui.showCustomMenu();
			}
			
			DifficultySettings settings;
			
			switch (difficulty) {
			case EASY:
					settings = EASY;
			case MEDIUM:
					settings = MEDIUM;
			case HARD:
					settings = HARD;
			default:
				break;
			}
			
			backend.createNewMineGrid(settings);
		}
		
		private enum GameDifficulty {
			EASY,
			MEDIUM,
			HARD,
			CUSTOM
		}
		
	}
	
}
