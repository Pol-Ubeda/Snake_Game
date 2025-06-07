package Elements;

import java.util.ArrayList;

import Game.snakePanel;

import java.awt.Graphics2D;
import java.awt.Color;

public class Apple {
	ArrayList<Integer> position = new ArrayList<>();
	
	public Apple(int col, int row) {
		position.add(col);
		position.add(row);
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(position.get(0) * snakePanel.SQUARE_SIZE, position.get(1) * snakePanel.SQUARE_SIZE,
				snakePanel.SQUARE_SIZE, snakePanel.SQUARE_SIZE);
	}
}
