/**
 * 
 */
package minesweeper;

import minesweeper.gui.MainGui;

/**
 * @author sarah
 *
 */
public class MainApp {

	private MainGui gui;
	
	public MainApp() {
		gui = new MainGui();
		
		gui.generateAndAddMineGrid(14, 14);
		
		gui.setVisible();
	}
	
	public static void main(String[] args) {
		//dirty way to get around the fact that the main method is static
		new MainApp();
	}
}
