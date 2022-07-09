package minesweeper;

import minesweeper.gui.MainGui;

public class MainApp {
	private MainGui gui;
	
	public MainApp() {
		gui = new MainGui();
		
		gui.generateAndAddMineGrid(14, 14);
		
		gui.setVisible();
	}
}
