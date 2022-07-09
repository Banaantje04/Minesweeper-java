package minesweeper.gui.components;

import javax.swing.*;

public class MineButton extends JButton {
	private int horizontalPosition;
	private int verticalPosition;
	
	public MineButton(int horizontalPos, int verticalPos) {
		horizontalPosition = horizontalPos;
		verticalPosition = verticalPos;
	}

}
