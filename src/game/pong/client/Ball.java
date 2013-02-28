package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ball implements Serializable{
	private int x = 0;
	private int y = 0;
	
	private float dX = 5;
	private float dY = 0;
	
	private int centerOfBallX;
	private int centerOfBallY;
	
	
	public int getX() {  //Getter for ball.X
		return x;
	}
	public void setX(int x) {//Setter for ball.Y
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

	
	public int getCenterOfBallX() {
		this.setCenterOfBallX(this.getX() + 10);
		return centerOfBallX;
	}
	public void setCenterOfBallX(int centerOfBallX) {
		this.centerOfBallX = centerOfBallX;
	}
	
	public int getCenterOfBallY() {
		this.setCenterOfBallY(this.getY() + 10);
		return centerOfBallY;
	}
	public void setCenterOfBallY(int centerOfBallY) {
		this.centerOfBallY = centerOfBallY;
	}
	
	public void draw(){
		glBegin(GL_QUADS);//ball.getY() thingy
			glVertex2i(x, y); //1
			glVertex2i(x + 20, y); //2
			glVertex2i(x + 20, y + 20); //3
			glVertex2i(x, y + 20); //4
		glEnd();
	}
}
