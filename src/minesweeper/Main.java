/**
 * 
 */
package minesweeper;

import javax.swing.SwingUtilities;
import minesweeper.gui.MainGui;

/**
 * @author sarah
 *
 */
public class Main {
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGui.createAndShowGui();
            }
        });
	}
}
