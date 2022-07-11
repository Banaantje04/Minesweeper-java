package minesweeper.gui.components;

import javax.swing.*;

public class MineCell extends JButton {	
	private int horizontalPosition;
	private int verticalPosition;
	private ClickedState clickedState;
	
	public MineCell(int horizontalPos, int verticalPos) {
		horizontalPosition = horizontalPos;
		verticalPosition = verticalPos;
	}
	
	public int[] getPosition(){
		int[] position = new int[2];
		
		position[0] = horizontalPosition;
		position[1] = verticalPosition;
		
		return position;
	}
	
	public void setCellState(ClickedState state, Integer amountOfSurroundingMines) {
		clickedState = state;
		setText(amountOfSurroundingMines.toString());
	}
	
	public enum ClickedState {
		NOT_CLICKED,
		IS_MINE,
		IS_CLEAR,
		IS_FLAGGED
	}

}
