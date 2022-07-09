package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

public class MainGui {
	private JFrame frame;
	private Container pane;
	
	public MainGui() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = frame.getContentPane();
	}

	public void setVisible() {
		frame.pack();
		frame.setVisible(true);
	}
}
