/**
 * 
 */
package minesweeper;

import minesweeper.backend.*;
import minesweeper.gui.MainGui;

/**
 * @author sarah
 *
 */
public class Minesweeper {

	private MainGui gui;
	private MainBackend backend;
	
	public Minesweeper() {
		backend = new MainBackend(this);
		gui = new MainGui(backend);
		
		backend.createNewMineGrid(30, 16, 99);
		
		
		gui.setVisible();
	}

	public void createGridGui(MineGrid mineGrid) {
		gui.generateAndAddMineGrid(mineGrid);
	}

	public CompletionTask showCompletionGui(GameState state) {
		return gui.showCompletionGui(state);
	}
	
	public static void main(String[] args) {
		new Minesweeper();
	}
}
