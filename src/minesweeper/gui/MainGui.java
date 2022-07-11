package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.MainBackend;
import minesweeper.backend.MineGrid;
import minesweeper.gui.components.MineGridGui;

public class MainGui extends JFrame {
	private Container pane;
	private MainBackend backend;
	
	public MainGui(MainBackend backend) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		this.backend = backend;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
	}
	
	public void generateAndAddMineGrid(MineGrid grid) {
		MineGridGui gridGui = new MineGridGui(grid);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 0.5;
		constraint.weighty = 0.5;
		constraint.fill = GridBagConstraints.BOTH;
		
		pane.add(gridGui, constraint);
	}

	public void setVisible() {
		pack();
		setVisible(true);
	}
}
