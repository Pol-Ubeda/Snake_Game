package Game;

import javax.swing.*;

public class snakeGame {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		snakePanel panel = new snakePanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocusInWindow();

		panel.launch();
	}
}
