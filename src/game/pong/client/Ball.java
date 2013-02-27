package game.pong.client;

import java.io.Serializable;

import org.lwjgl.opengl.Display;

@SuppressWarnings("serial")
public class Ball implements Serializable{
	private int x = Display.getWidth() / 2;
	private int y = Display.getHeight() / 2;
	
	private float dX = 5;
	private float dY = 0;
	
	public int getX() {  //Getter for ball.X
		return x;
	}
	public void setX(int x) {//Setter for ball.S
		this.x = x;
	}
	
	public int getY() {//Getter for ball.X
		return y;
	}
	public void setY(int y) {//Setter for ball.Y
		this.y = y;
	}
	
	public float getdX() {//Getter for ball.dX
		return dX;
	}
	public void setdX(float dX) {//Setter for ball.dX
		this.dX = dX;
	}
	
	public float getdY() {//Getter for ball.dY
		return dY;
	}
	public void setdY(float dY) {//Setter for ball.dX
		this.dY = dY;
	}
}
