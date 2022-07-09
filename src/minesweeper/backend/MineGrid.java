package minesweeper.backend;

import java.util.Random;
import java.util.Arrays;

public class MineGrid {
	
	private boolean[][] grid;
	
	private int horizontalCount;
	private int verticalCount;
	private int amountOfMines;
	
	public MineGrid(int horizontalCount, int verticalCount, int amountOfMines) {

		this.horizontalCount = horizontalCount;
		this.verticalCount = verticalCount;
		this.amountOfMines = amountOfMines;
		
		generateRandomLayout();
	}

	private void generateRandomLayout() {
		Random rand = new Random();
		
		grid = new boolean[horizontalCount][verticalCount];
		
		for (int i = 0; i < amountOfMines; i++) {
			grid[rand.nextInt(horizontalCount)][rand.nextInt(verticalCount)] = true;
		}
		
		if (!ensureCorrectAmountOfMines()) {
			//just run it again and hope this doesn't happen too often lol
			generateRandomLayout();
		}
	}
	
	private boolean ensureCorrectAmountOfMines() {
		int count = 0;
		
		for (boolean[] row : grid) {
			for (boolean cell : row) {
				if (cell)
					count++;
			}
		}
		
		if (count != amountOfMines)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) {
		MineGrid grid = new MineGrid(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		
		System.out.println(Arrays.deepToString(grid.grid).replace("]", "]\n").replace("false", "0").replace("true", "1"));
	}
}
