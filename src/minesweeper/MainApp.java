/**
 * 
 */
package minesweeper;

import minesweeper.backend.MainBackend;
import minesweeper.gui.MainGui;

/**
 * @author sarah
 *
 */
public class MainApp {

	private MainGui gui;
	private MainBackend backend;
	
	public MainApp() {
		backend = new MainBackend();
		backend.createNewMineGrid(30, 16, 99);
		
		gui = new MainGui(backend);
		
		gui.generateAndAddMineGrid(backend.getGrid());
		
		gui.setVisible();
	}
	
	public static void main(String[] args) {
		new MainApp();
	}
}
