package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.Serializable;

import org.lwjgl.opengl.Display;

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
	private float prospectBallY = 200;
	
	public Ball(int x, int y, int dimensions){
		this.setX(x);
		this.setY(y);
		this.setWAndHt(dimensions);
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
	
	public void onUpdate(Ball ball){
		
		if(StateGame.isPlayer1Paused || StateGame.isPlayer2Paused){
			StateGame.isPaused = true;
		}else{
			StateGame.isPaused = false;
		}
		/*FUTURE NOTE- 
		 * (dX > 0) ball moves LEFT 
		 * (dX < 0) ball moves RIGHT 
		 * */
		if(!StateGame.isPaused && ((StateGame.score0 < 21) && (StateGame.score1 < 21)))
		{
			//This bit updates the movement of the ball. Without it the ball would
			//just be static and wouldn't move at all :3
			ball.setX((int) (ball.getX()-ball.getdX()));
			ball.setY((int) (ball.getY()-ball.getdY()));
			
			if(ball.getdX() > 0){//Makes ball go faster slowly over time
				ball.setdX((float) (ball.getdX() + .002));
			}else{
				ball.setdX((float) (ball.getdX() - .002));
			}
		}
		
		//Checking collision for the two player paddles if the
		//ball is colliding with the paddle
		boolean hitPlayerOnesPaddle = ((ball.getX() > StateGame.P1X) 
									&& (ball.getX() < (StateGame.P1X + StateGame.player1.getWidth()))
									&& (ball.getY() > StateGame.P1Y))
									&& (ball.getY() < (StateGame.P1Y + StateGame.player1.getHeight()))
									||(((ball.getX() + ball.getWAndH()) > StateGame.P1X) 
									&& ((ball.getX() + ball.getWAndH()) < (StateGame.P1X + StateGame.player1.getWidth()))
									&& (ball.getY() > StateGame.P1Y))
									&& (ball.getY() < (StateGame.P1Y + StateGame.player1.getHeight()))
									||((ball.getX() > StateGame.P1X) 
									&& (ball.getX() < (StateGame.P1X + StateGame.player1.getWidth()))
									&& ((ball.getY() + ball.getWAndH())  > StateGame.P1Y))
									&& ((ball.getY() + ball.getWAndH()) < (StateGame.P1Y + StateGame.player1.getHeight()))
									||(((ball.getX() + ball.getWAndH()) > StateGame.P1X) 
									&& ((ball.getX() + ball.getWAndH()) < (StateGame.P1X + StateGame.player1.getWidth()))
									&& ((ball.getY() + ball.getWAndH())  > StateGame.P1Y))
									&& ((ball.getY() + ball.getWAndH()) < (StateGame.P1Y + StateGame.player1.getHeight()));
									
		boolean hitPlayersTwosPaddle = ((ball.getX() > StateGame.p2X) 
									&& (ball.getX() < (StateGame.p2X + StateGame.player2.getWidth()))
									&& (ball.getY() > StateGame.p2Y))
									&& (ball.getY() < (StateGame.p2Y + StateGame.player2.getHeight()))
									||(((ball.getX() + ball.getWAndH()) > StateGame.p2X) 
									&& ((ball.getX() + ball.getWAndH()) < (StateGame.p2X + StateGame.player2.getWidth()))
									&& (ball.getY() > StateGame.p2Y))
									&& (ball.getY() < (StateGame.p2Y + StateGame.player2.getHeight()))
									||((ball.getX() > StateGame.p2X) 
									&& (ball.getX() < (StateGame.p2X + StateGame.player2.getWidth()))
									&& ((ball.getY() + ball.getWAndH())  > StateGame.p2Y))
									&& ((ball.getY() + ball.getWAndH()) < (StateGame.p2Y + StateGame.player2.getHeight()))
									||(((ball.getX() + ball.getWAndH()) > StateGame.p2X) 
									&& ((ball.getX() + ball.getWAndH()) < (StateGame.p2X + StateGame.player2.getWidth()))
									&& ((ball.getY() + ball.getWAndH())  > StateGame.p2Y))
									&& ((ball.getY() + ball.getWAndH()) < (StateGame.p2Y + StateGame.player2.getHeight()));
		
		if(hitPlayerOnesPaddle)
		{
			//Calculating where the ball will go after being hit off
			//the paddle, same as in brick breaker
			ball.setdX(-ball.getdX());
			ball.setdY((float) (((StateGame.P1Y + (StateGame.player1.getHeight()/2)) - ball.getCenterOfBallY())/7.5));
			if(StateGame.isSinglePlayer && (StateGame.difficulty>3))//Finds were the AI paddle will need to move
			{
				ball.predictAI(ball);
				ball.setdY((float) (((StateGame.P1Y + (StateGame.player1.getHeight()/2)) - ball.getCenterOfBallY())/7.5));//Resets dY
			}
		}
		if(hitPlayersTwosPaddle)
		{
			//Calculating where the ball will go after being hit off
			//the paddle, same as in brick breaker
			ball.setdX(-ball.getdX());
			ball.setdY((float) (((StateGame.p2Y+(StateGame.player2.getHeight()/2)) - ball.getCenterOfBallY()) /7.5));
		}
		if(ball.getCenterOfBallX() > Display.getWidth()) //Scored on RIGHT side of screen
		{
			StateGame.score0++;
			
			if(StateGame.score0 == 21){
				ball.setX(Display.getWidth() / 2);
				ball.setY((int) (Display.getHeight() * 0.25));
			}else{
				ball.setX(Display.getWidth() / 2);
				ball.setY(Display.getHeight() / 2);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {e.printStackTrace();}
			ball.setdX(5);
			ball.setdY((float) (Math.random()*8-4));
		}
		if(ball.getCenterOfBallX() < 0) //Scored on LEFT side of screen
		{
			
			StateGame.score1++;
			if(StateGame.score1 == 21){
				ball.setX(Display.getWidth() / 2);
				ball.setY((int) (Display.getHeight() * 0.25));
			}else{
				ball.setX(Display.getWidth() / 2);
				ball.setY(Display.getHeight() / 2);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {e.printStackTrace();}
			ball.setdX(-5);
			ball.setdY((float) (Math.random()*8-4));
			if(StateGame.isSinglePlayer && (StateGame.difficulty>3))//Finds were the AI paddle will need to move
			{
				ball.predictAI(ball);
			}
		}
		if((ball.getY() <= 0) || ((ball.getY() + ball.getWAndH()) >= Display.getHeight())) //Bounce off ceiling or floor
		{ 
			ball.setdY(-ball.getdY());
		}
	}
	
	private void predictAI(Ball ball)
	{
		ball.setProspectBallX(ball.getX());
		ball.setProspectBallY(ball.getY());
		while(ball.getProspectBallX() <= StateGame.player2.getX())
		{
			if(ball.getProspectBallX() >= StateGame.player1.getX())
			{
				ball.setProspectBallX((float) (ball.getProspectBallX() - (ball.getdX() - .002)));
				ball.setProspectBallY(ball.getProspectBallY() - ball.getdY());
				if((ball.getProspectBallY() <= 0) || ((ball.getProspectBallY() + ball.getWAndH()) >= Display.getHeight()))
				{
					ball.setdY(-ball.getdY());
				}
			}else{
				break;
			}
		}
		StateGame.player2.setMoveToY(ball.getProspectBallY());
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
