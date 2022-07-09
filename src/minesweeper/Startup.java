/**
 * 
 */
package minesweeper;

/**
 * @author sarah
 *
 */
public class Startup {
	public static void main(String[] args) {
		//dirty way to get around the fact that the main method is static
		new MainApp();
	}
}
