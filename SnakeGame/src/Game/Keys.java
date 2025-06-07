package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {
	public static String pressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			pressed = "UP";
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			pressed = "DOWN";
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			pressed = "LEFT";
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pressed = "RIGHT";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
