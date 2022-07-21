package minesweeper.backend;

import minesweeper.Minesweeper;

public class MainBackend {
	
	private NewGame newGame;
	private MineGrid mineGrid;
	private Minesweeper main;
	
	public MainBackend(Minesweeper main) {
		this.main = main;
	}
	
	public void createNewGame() {
		newGame = new NewGame(this);
		main.createNewGameGui(newGame);
	}

	public MineGrid getGrid() {
		return mineGrid;
	}
	
	public void createNewMineGrid(DifficultySettings settings) {
		mineGrid = new MineGrid(settings, this);
		main.createGridGui(mineGrid);
	}
	
	public void completeGame(GameState state) {
		CompletionTask task = main.showCompletionGui(state);
		
		switch (task) {
		case PLAY_AGAIN:
			createNewGame();
			break;
		case NOTHING:
			break;
		case EXIT_GAME:
			System.exit(0);
		}
	}
}
