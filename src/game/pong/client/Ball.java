package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.Serializable;

import org.lwjgl.opengl.Display;

@SuppressWarnings("serial")
public class Ball implements Serializable{
	private double x = 0;
	private double y = 0;
	
	private double dX;
	private double dY;
	
	private double centerOfBallX;
	private double centerOfBallY;
	private int wAndH;
	
	private double prospectBallX;
	private double prospectBallY = 200;
	
	private double speedInc = .005;
	
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
			ball.setX((ball.getX()-ball.getdX()));
			ball.setY((ball.getY()-ball.getdY()));
			
			if(ball.getdX() > 0){//Makes ball go faster slowly over time
				ball.setdX((ball.getdX() + ball.getSpeedInc()));
			}else{
				ball.setdX((ball.getdX() - ball.getSpeedInc()));
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
			ball.setX(StateGame.player1.getWidth());
			ball.setdY((((StateGame.P1Y + (StateGame.player1.getHeight()/2)) - ball.getCenterOfBallY())/7.5));
			if(StateGame.isSinglePlayer)//Finds were the AI paddle will need to move
			{
				ball.predictAI(ball);
				ball.setdY((((StateGame.P1Y + (StateGame.player1.getHeight()/2)) - ball.getCenterOfBallY())/7.5));//Resets dY
			}
		}
		else if(hitPlayersTwosPaddle)
		{
			//Calculating where the ball will go after being hit off
			//the paddle, same as in brick breaker
			ball.setdX(-ball.getdX());
			ball.setX(Display.getWidth()-StateGame.player2.getWidth()-ball.wAndH);
			ball.setdY((((StateGame.p2Y+(StateGame.player2.getHeight()/2)) - ball.getCenterOfBallY()) /7.5));
		}
		if(ball.getCenterOfBallX() > Display.getWidth()) //Scored on RIGHT side of screen
		{
			StateGame.score0++;
			
			if(StateGame.score0 == 21)
			{
				ball.setX(Display.getWidth() / 2);
				ball.setY((Display.getHeight() * 0.25));
			}
			else
			{
				ball.setX(Display.getWidth() / 2);
				ball.setY(Display.getHeight() / 2);
			}
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) {e.printStackTrace();}
			ball.setdX(5);
			ball.setdY((Math.random()*8-4));
		}
		else if(ball.getCenterOfBallX() < 0) //Scored on LEFT side of screen
		{
			
			StateGame.score1++;
			if(StateGame.score1 == 21)
			{
				ball.setX(Display.getWidth() / 2);
				ball.setY((Display.getHeight() * 0.25));
			}
			else
			{
				ball.setX(Display.getWidth() / 2);
				ball.setY(Display.getHeight() / 2);
			}
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) {e.printStackTrace();}
			ball.setdX(-5);
			ball.setdY((Math.random()*8-4));
			if(StateGame.isSinglePlayer)//Finds were the AI paddle will need to move
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
				ball.setProspectBallX((ball.getProspectBallX() - (ball.getdX() - ball.getSpeedInc())));
				ball.setProspectBallY(ball.getProspectBallY() - ball.getdY());
				if((ball.getProspectBallY() <= 0) || ((ball.getProspectBallY() + ball.getWAndH()) >= Display.getHeight()))
				{
					ball.setdY(-ball.getdY());
				}
			}
			else
			{
				break;
			}
		}
		StateGame.player2.setMoveToY(ball.getProspectBallY());
	}

	public Ball(int x, int y, int dimensions){
		this.setX(x);
		this.setY(y);
		this.setWAndHt(dimensions);
	}

	public double getX() {  //Getter for ball.X
		return x;
	}

	public void setX(double x) {//Setter for ball.Y
		this.x = x;
	}

	public double getY() {//Getter for ball.X
		return y;
	}

	public void setY(double y) {//Setter for ball.Y
		this.y = y;
	}

	public double getdX() {//Getter for ball.dX
		return dX;
	}

	public void setdX(double dX) {//Setter for ball.dX
		this.dX = dX;
	}

	public double getdY() {//Getter for ball.dY
		return dY;
	}

	public void setdY(double dY) {//Setter for ball.dX
		this.dY = dY;
	}

	public double getCenterOfBallX() {
		this.setCenterOfBallX(this.getX() + (this.getWAndH()/2));
		return centerOfBallX;
	}

	public void setCenterOfBallX(double f) {
		this.centerOfBallX = f;
	}

	public double getCenterOfBallY() {
		this.setCenterOfBallY(this.getY() + (this.getWAndH()/2));
		return centerOfBallY;
	}

	public void setCenterOfBallY(double f) {
		this.centerOfBallY = f;
	}

	public double getProspectBallX() {
		return prospectBallX;
	}

	public void setProspectBallX(double prospectBallX) {
		this.prospectBallX = prospectBallX;
	}

	public double getProspectBallY() {
		return prospectBallY;
	}

	public void setProspectBallY(double prospectBallY) {
		this.prospectBallY = prospectBallY;
	}

	public int getWAndH() {
		return wAndH;
	}

	public void setWAndHt(int wAndH) {
		this.wAndH = wAndH;
	}

	public double getSpeedInc() {
		return speedInc;
	}

	public void setSpeedInc(double speedInc) {
		this.speedInc = speedInc;
	}

	public void draw(){
		Textures.none.bind();
		glBegin(GL_QUADS);//ball.getY() thingy
			glTexCoord2d(0, 1);
			glVertex2d(this.getX(), this.getY()); //1
			glTexCoord2d(1, 1);
			glVertex2d(this.getX() + this.getWAndH(), this.getY()); //2
			glTexCoord2d(1, 0);
			glVertex2d(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
			glTexCoord2d(0, 0);
			glVertex2d(this.getX(), this.getY() + this.getWAndH()); //4
		glEnd();
	}
	
	public void drawFollower(){
		//Drawing a square with lines!
		Textures.none.bind();
		glBegin(GL_LINES);//1 to 2
			glTexCoord2d(0, 0);
			glVertex2d(this.getX(), this.getY()); //1
			glTexCoord2d(1, 1);
			glVertex2d(this.getX() + this.getWAndH(), this.getY()); //2
		glEnd();
		
		glBegin(GL_LINES);//2 to 3
			glTexCoord2d(0, 0);
			glVertex2d(this.getX() + this.getWAndH(), this.getY()); //2
			glTexCoord2d(1, 1);
			glVertex2d(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
		glEnd();
		
		glBegin(GL_LINES);//3 to 4
			glTexCoord2d(0, 0);
			glVertex2d(this.getX() + this.getWAndH(), this.getY() + this.getWAndH()); //3
			glTexCoord2d(1, 1);
			glVertex2d(this.getX() , this.getY() + this.getWAndH()); //4
		glEnd();
		
		glBegin(GL_LINES);//4 to 1
			glTexCoord2d(0, 0);
			glVertex2d(this.getX(), this.getY() + this.getWAndH()); //4
			glTexCoord2d(1, 1);
			glVertex2d(this.getX(), this.getY()); //1
		glEnd();
	}
}
