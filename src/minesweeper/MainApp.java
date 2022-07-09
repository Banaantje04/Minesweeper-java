package minesweeper;

import javax.swing.*;
import minesweeper.gui.MainGui;

public class MainApp {
	private MainGui gui;
	
	public void run() {
		gui = new MainGui();
		
		gui.setVisible();
	}
}
