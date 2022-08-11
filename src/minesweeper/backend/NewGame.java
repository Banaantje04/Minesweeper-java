package minesweeper.backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import minesweeper.gui.NewGameGui;
import minesweeper.gui.NewGameGui.CustomSettingsErrors;

public class NewGame {
	
	private final static DifficultySettings BEGINNER = new DifficultySettings(9, 9, 10);
	private final static DifficultySettings INTERMEDIATE = new DifficultySettings(16, 16, 40);
	private final static DifficultySettings EXPERT = new DifficultySettings(30, 16, 99);
	
	private MainBackend backend;
	private NewGameGui newGameGui;

	public NewGame(MainBackend mainBackend) {
		backend = mainBackend;
	}
	
	public DifficultyButtonActionListener initialiseDifficultyButtonActionListener(NewGameGui gui) {
		return new DifficultyButtonActionListener(gui);
	}
	
	public CustomSettingsActionListener initialiseCustomSettingsActionListener() {
		return new CustomSettingsActionListener();
	}

	public DifficultySettings getCustomSettings() {
		JFormattedTextField[] settingFields = newGameGui.getCustomSettings();
		
		//these object to long to int casts are necessary, for some reason i couldn't cast directly to int
		int horizontalCount = (int)(long)settingFields[0].getValue();
		int verticalCount = (int)(long)settingFields[1].getValue();
		int amountOfBombs = (int)(long)settingFields[2].getValue();

		return new DifficultySettings(horizontalCount, verticalCount, amountOfBombs);
	}

	public CustomSettingsErrors[] checkIfValidSettings(DifficultySettings settings) {
		CustomSettingsErrors[] errorList = new CustomSettingsErrors[0];
		
		/* the amount of mines can't be more than 9 less than the total amount of cells
		 * this is needed because the starting cell and the 8 surrounding cells need to be empty
		 */
		if (settings.amountOfMines() > settings.horizontalCount()*settings.verticalCount()-9) {
			errorList = addErrorToList(errorList, CustomSettingsErrors.TOOMANYBOMBS);
		}
		
		/* the rows and columns cannot be 0
		 * this would make an invisible and thus unusable grid
		 */
		if (settings.horizontalCount() == 0) {
			errorList = addErrorToList(errorList, CustomSettingsErrors.HORIZONTALZERO);
		}
		
		if (settings.verticalCount() == 0) {
			errorList = addErrorToList(errorList, CustomSettingsErrors.VERTICALZERO);
		}
		
		return errorList;
	}

	//TODO: i really should start using that apache thingy because using arrays for these things is just silly
	private CustomSettingsErrors[] addErrorToList(CustomSettingsErrors[] errorList, CustomSettingsErrors error) {
		int length = errorList.length;
		CustomSettingsErrors[] tempList = errorList;
		errorList = new CustomSettingsErrors[length + 1];
		for (int i = 0; i < length; i++) {
			errorList[i] = tempList[i];
		}
		errorList[length] = error;
		
		return errorList;
	}
	
	public class DifficultyButtonActionListener implements ActionListener {
		
		public DifficultyButtonActionListener(NewGameGui gui) {
			newGameGui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Difficulty chosen");
			NewGameButtonChoice choice = NewGameButtonChoice.valueOf(e.getActionCommand().toUpperCase());
			System.out.println(choice);
			
			if (choice == NewGameButtonChoice.CUSTOM) {
				newGameGui.showCustomMenu();
			}
			else {
				DifficultySettings settings;
				
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
			CUSTOM
		}
		
	}
	
	public class CustomSettingsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DifficultySettings settings = getCustomSettings();
			
			CustomSettingsErrors[] errors = checkIfValidSettings(settings);
			
			if (errors.length != 0) {
				newGameGui.showCustomSettingsErrors(errors);
				return;
			}
			
			System.out.println("Selected difficulty is: " + settings);
			
			backend.createNewMineGrid(settings);
		}
		
	}
	
}
