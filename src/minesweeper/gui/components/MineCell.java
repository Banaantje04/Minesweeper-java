package minesweeper.gui.components;

import java.net.URL;

import javax.swing.*;

public class MineCell extends JButton {	
	private int horizontalPosition;
	private int verticalPosition;
	private CellState cellState;
	
	public MineCell(int horizontalPos, int verticalPos) {
		horizontalPosition = horizontalPos;
		verticalPosition = verticalPos;
		cellState = CellState.NOT_CLICKED;
	}
	
	public int[] getPosition(){
		int[] position = new int[2];
		
		position[0] = horizontalPosition;
		position[1] = verticalPosition;
		
		return position;
	}
	
	public CellState getCellState() {
		return cellState;
	}
	
	public void revealCell(CellState state, Integer amountOfSurroundingMines) {
		cellState = state;
		
		if (cellState == CellState.IS_MINE)
			setIcon(new ImageIcon(this.getClass().getResource("/assets/Mine.png")));
		else
			setText(amountOfSurroundingMines.toString());
	}
	
	public enum CellState {
		NOT_CLICKED,
		IS_MINE,
		IS_CLEAR,
		IS_FLAGGED
	}

}
