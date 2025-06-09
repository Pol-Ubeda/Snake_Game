package Game;

import Elements.Snake;
import Elements.Apple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class snakePanel extends JPanel implements Runnable{
	final int FPS = 2;
	public static final int MAX_COL = 20;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int SQUARE_SIZE = WIDTH/MAX_COL;
	public boolean gameOver = false;
	Thread snakeThread;
	
	Snake snake = new Snake();
	
	Random r = new Random();
	
	public int randomX = r.nextInt(MAX_COL);
	public int randomY = r.nextInt(MAX_COL);
	
	Apple apple = new Apple(randomX, randomY);
		
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
		if(gameStarted() && gameOver == false) {
			if(borderCheck() && collisionCheck()) {
				snake.updateSnake();
				if(appleReached()) {
					while(appleOnSnake()) {
						apple.position.set(0, r.nextInt(MAX_COL));
						apple.position.set(1, r.nextInt(MAX_COL));
					}
				}
			}
			//if we hit ourselves or border it's game over
			else {
				gameOver = true;
			}
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
	
	//Check if snake eats apple
	public boolean appleReached() {
		if(snake.squares.get(0).get(0) == apple.position.get(0) &&
				snake.squares.get(0).get(1) == apple.position.get(1)) {
			
			ArrayList<Integer> lastItem = new ArrayList<>();
			lastItem.add(snake.squares.get(0).get(0));
			lastItem.add(snake.squares.get(0).get(1));
			
			snake.len++;
			snake.squares.add(lastItem);
			snake.updateSnake();
			
			return true;
		}
		return  false;
	}
	
	//this is an iad method to avoid apple spawning on top of snake
	public boolean appleOnSnake() {
		for(ArrayList<Integer> s : snake.squares) {
			if(apple.position.get(0) == s.get(0) && apple.position.get(1) == s.get(1)) {
				return true;
			}
		}
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//i decided to simply display game over, without snake on background for my preference, but just remove this if statement if you want to change this
		if(gameOver == false) {
			apple.draw(g2);
			snake.drawSnake(g2);
		}
		else {
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g2.setColor(Color.red);
			g2.drawString("GAME", 250, 200);
			g2.drawString("OVER", 250, 350);
		}
	}
}
