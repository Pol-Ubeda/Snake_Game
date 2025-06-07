package Game;

import Elements.Snake;
import Elements.Apple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class snakePanel extends JPanel implements Runnable{
	final int FPS = 2;
	public static final int MAX_COL = 20;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int SQUARE_SIZE = WIDTH/MAX_COL;
	Thread snakeThread;
	
	Snake snake = new Snake();
	

	Apple apple = new Apple(3, 4);
		
	public snakePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);
		setFocusable(true);
		addKeyListener(new Keys());		
	}
	public void launch() {
		snakeThread = new Thread(this);
		snakeThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(snakeThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;

			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
		
	}	
	public void update() {
		if(gameStarted() && borderCheck() && collisionCheck()) {
			snake.updateSnake();
		}
		
	}
	
	//Check if player has started playing
	public boolean gameStarted() {
		if(Keys.pressed != null) return true;
		return false;
	}
	//Check if snake hits wall
	public boolean borderCheck() {
		if(Keys.pressed.equals("UP") && snake.newFirst.get(1) == 0) {
			return false;			
		}
		if(Keys.pressed.equals("DOWN") && snake.newFirst.get(1) == 19) {
			return false;
		}
		if(Keys.pressed.equals("LEFT") && snake.newFirst.get(0) == 0) {
			return false;
		}
		if(Keys.pressed.equals("RIGHT") && snake.newFirst.get(0) == 19) {
			return false;
		}
		
		return true;
	}
	
	//Check if snake eats itself
	public boolean collisionCheck() {
		int nextX = -1, nextY = -1;
		
		if(Keys.pressed.equals("UP")){
			nextX = snake.squares.get(0).get(0);
			nextY = snake.squares.get(0).get(1) - 1;
		}
		if(Keys.pressed.equals("DOWN")) {
			nextX = snake.squares.get(0).get(0);
			nextY = snake.squares.get(0).get(1) + 1;
		}
		if(Keys.pressed.equals("LEFT")) {
			nextX = snake.squares.get(0).get(0) - 1;
			nextY = snake.squares.get(0).get(1);
		}
		if(Keys.pressed.equals("RIGHT")) {
			nextX = snake.squares.get(0).get(0) + 1;
			nextY = snake.squares.get(0).get(1);
		}
		
		if(Keys.pressed != null) {
			for(int i = 1; i < snake.len; i++) {
				if(nextX == snake.squares.get(i).get(0) &&
						nextY == snake.squares.get(i).get(1)){
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		snake.drawSnake(g2);
		
	}
}
