package minesweeper.backend;

import minesweeper.Minesweeper;

public class MainBackend {
	
	private MineGrid mineGrid;
	private Minesweeper main;
	
	public MainBackend(Minesweeper main) {
		this.main = main;
	}
	
	public MineGrid getGrid() {
		return mineGrid;
	}
	
	public void createNewMineGrid(int horizontalCount, int verticalCount, int amountOfMines) {
		mineGrid = new MineGrid(horizontalCount, verticalCount, amountOfMines, this);
		main.createGridGui(mineGrid);
	}
	
	public void completeGame(GameState state) {
		CompletionTask task = main.showCompletionGui(state);
		
		switch (task) {
		case PLAY_AGAIN:
			createNewMineGrid(30, 16, 99);
			break;
		case NOTHING:
			break;
		case EXIT_GAME:
			System.exit(0);
		}
	}
}
