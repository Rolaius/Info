package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener{

	public boolean upPressed,downPressed,leftPressed,rightPressed,lClicked,rClicked;

	@Override
	public void keyPressed(KeyEvent e) {
		
	int code = e.getKeyCode();
	//Tasten
		if (code == KeyEvent.VK_W) {
		upPressed = true;
		}
		else if (code == KeyEvent.VK_S) {
		downPressed = true;	
		}
		else if (code == KeyEvent.VK_A) {
		leftPressed = true;
		}
		else if (code == KeyEvent.VK_D) {
		rightPressed = true;	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
			}
		else if (code == KeyEvent.VK_S) {
			downPressed = false;	
			}
		else if (code == KeyEvent.VK_A) {
			leftPressed = false;
			}
		else if (code == KeyEvent.VK_D) {
			rightPressed = false;	
			}
		else if(code == KeyEvent.VK_E) {
			lClicked = true;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int code = e.getButton();
		if (code == MouseEvent.BUTTON1) {
			lClicked = true;
			}
		else if (code == MouseEvent.BUTTON2) {
			rClicked = true;	
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
