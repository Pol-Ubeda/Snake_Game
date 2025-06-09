package Elements;

import Game.snakePanel;
import Game.Keys;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Snake {
	public int len = 2;
	public ArrayList<ArrayList<Integer>> squares = new ArrayList<>();	
	public ArrayList<Integer> newFirst = new ArrayList<>();
		
	public Snake() {
		ArrayList<Integer> center = new ArrayList<>();
		center.add(7);
		center.add(7);	
		squares.add(center);
		
		ArrayList<Integer> center2 = new ArrayList<>();
		center2.add(6);
		center2.add(7);	
		squares.add(center2);
		
		newFirst.add(7);
		newFirst.add(7);
	}
	
	public int getX(int col) {
		return col * snakePanel.SQUARE_SIZE;
	}
	public int getY(int row) {
		return row * snakePanel.SQUARE_SIZE;
	}
	
	public void updateSnake() {		
		int firstX, firstY;
		
		firstX = squares.get(0).get(0);
		firstY = squares.get(0).get(1);
		newFirst.set(0, firstX);
		newFirst.set(1, firstY);
		
		
		for(int i = len - 1; i > 0; i--) {
			squares.set(i, squares.get(i-1));
		}
		System.out.println(Keys.pressed);
		if(Keys.pressed == "UP") {
			newFirst.set(1, newFirst.get(1) - 1);
			squares.set(0, new ArrayList<>(newFirst));
		}
		if(Keys.pressed == "DOWN") {
			newFirst.set(1,  newFirst.get(1) + 1);
			squares.set(0, new ArrayList<>(newFirst));
		}
		if(Keys.pressed == "LEFT") {
			newFirst.set(0,  newFirst.get(0) - 1);
			squares.set(0, new ArrayList<>(newFirst));
		}
		if(Keys.pressed == "RIGHT") {
			newFirst.set(0,  newFirst.get(0) + 1);
			squares.set(0, new ArrayList<>(newFirst));
		}
	}
	
	public void drawSnake(Graphics2D g2) {
		g2.setColor(Color.yellow);
		for(ArrayList<Integer> s : squares) {			
			g2.fillRect(getX(s.get(0)), getY(s.get(1)), snakePanel.SQUARE_SIZE, snakePanel.SQUARE_SIZE);
		}
	}

}
