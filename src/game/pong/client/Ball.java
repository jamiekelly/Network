package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ball implements Serializable{
	private int x = 0;
	private int y = 0;
	
	private float dX;
	private float dY;
	
	private int centerOfBallX;
	private int centerOfBallY;
	private int wAndH;
	
	private float prospectBallX;
	private float prospectBallY;
	
	public Ball(int x, int y, int dimensions){
		this.x = x;
		this.y = y;
		this.wAndH = dimensions;
	}
	
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
		this.setCenterOfBallX(this.getX() + (this.getWAndH()/2));
		return centerOfBallX;
	}
	public void setCenterOfBallX(int centerOfBallX) {
		this.centerOfBallX = centerOfBallX;
	}
	
	public int getCenterOfBallY() {
		this.setCenterOfBallY(this.getY() + (this.getWAndH()/2));
		return centerOfBallY;
	}
	public void setCenterOfBallY(int centerOfBallY) {
		this.centerOfBallY = centerOfBallY;
	}
	
	public float getProspectBallX() {
		return prospectBallX;
	}

	public void setProspectBallX(float prospectBallX) {
		this.prospectBallX = prospectBallX;
	}

	public float getProspectBallY() {
		return prospectBallY;
	}

	public void setProspectBallY(float prospectBallY) {
		this.prospectBallY = prospectBallY;
	}
	
	public int getWAndH() {
		return wAndH;
	}

	public void setWAndHt(int wAndH) {
		this.wAndH = wAndH;
	}

	public void draw(){
		Textures.none.bind();
		glBegin(GL_QUADS);//ball.getY() thingy
			glTexCoord2d(0, 1);
			glVertex2i(this.getX(), this.getY()); //1
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() + this.getWAndH(), this.getY()); //2
			glTexCoord2d(1, 0);
			glVertex2i(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
			glTexCoord2d(0, 0);
			glVertex2i(this.getX(), this.getY() + this.getWAndH()); //4
		glEnd();
	}
	
	public void drawFollower(){
		//Drawing a square with lines!
		Textures.none.bind();
		glBegin(GL_LINES);//1 to 2
			glTexCoord2d(0, 0);
			glVertex2i(this.getX(), this.getY()); //1
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() + this.getWAndH(), this.getY()); //2
		glEnd();
		
		glBegin(GL_LINES);//2 to 3
			glTexCoord2d(0, 0);
			glVertex2i(this.getX() + this.getWAndH(), this.getY()); //2
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
		glEnd();
		
		glBegin(GL_LINES);//3 to 4
			glTexCoord2d(0, 0);
			glVertex2i(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() , this.getY() + this.getWAndH()); //4
		glEnd();
		
		glBegin(GL_LINES);//4 to 1
			glTexCoord2d(0, 0);
			glVertex2i(this.getX(), this.getY() + this.getWAndH()); //4
			glTexCoord2d(1, 1);
			glVertex2i(this.getX(), this.getY()); //1
		glEnd();
	}


}
