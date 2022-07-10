package minesweeper.backend;

public class MainBackend {
	
	private MineGrid mineGrid;
	
	public MainBackend() {
	}
	
	public MineGrid getGrid() {
		return mineGrid;
	}
	
	public void createNewMineGrid(int horizontalCount, int verticalCount, int amountOfMines) {
		mineGrid = new MineGrid(horizontalCount, verticalCount, amountOfMines);
	}
	
}
