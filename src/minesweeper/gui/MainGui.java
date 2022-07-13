package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.*;
import minesweeper.gui.components.MineGridGui;

public class MainGui extends JFrame {
	private Container pane;
	private MainBackend backend;
	private MineGridGui gridGui;
	
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
		if (gridGui != null) {
			pane.remove(gridGui);
		}
		
		gridGui = new MineGridGui(grid);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.weightx = 1.0;
		constraint.weighty = 1.0;
		constraint.fill = GridBagConstraints.BOTH;
		
		pane.add(gridGui, constraint);
		pack();
	}

	public CompletionTask showCompletionGui(GameState state) {
		//JOptionPane complete = new JOptionPane("A mine exploded. You lost!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[] { "Play again", "Exit game" }, "Play again");
		
		int chosenTask = JOptionPane.showOptionDialog(this, "A mine exploded. You lost!", "You lost!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Exit game", "Close", "Play again" }, "Play again");
				
		return CompletionTask.values()[chosenTask];
	}

	public void setVisible() {
		pack();
		setVisible(true);
	}
}
