package minesweeper.gui;

import java.awt.*;
import javax.swing.*;

import minesweeper.backend.*;
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
	}
	
	public void createNewGameGui(NewGame newGame) {
		pane = new NewGameGui(newGame);

		setContentPane(pane);
	}
	
	public void generateAndAddMineGrid(MineGrid grid) {
		pane = new GameGui(grid);
		setContentPane(pane);
		pack();
	}

	public CompletionTask showCompletionGui(GameState state) {
		final String[] options = new String[] { "Play again", "Close", "Exit game" };
		final String defaultOption = "Play again";
		
		String message;
		String title;
		Icon icon;
		
		if (state == GameState.FAILED) {
			message = "A mine exploded. You lost!";
			title = "You lost!";
			icon = new ImageIcon(this.getClass().getResource("/assets/Mine.png"));
		}
		else {
			message = "You cleared all the mines. You won!";
			title = "You won!";
			icon = new ImageIcon(this.getClass().getResource("/assets/Flag.png"));
		}
		
		int chosenTask = JOptionPane.showOptionDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, defaultOption);
				
		return CompletionTask.values()[chosenTask];
	}

	public void setVisible() {
		pack();
		setVisible(true);
	}
}
