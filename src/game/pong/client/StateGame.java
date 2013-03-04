package game.pong.client;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class StateGame {
	
	static Paddle player1;
	static Paddle player2;
	
	static boolean applet = false;
	static boolean isPaused = false;
	
	static boolean isPlayer1Paused = false;
	static boolean isPlayer2Paused = false;
	
	static int isSinglePlayer;
	static int difficulty;
	
	static String whoPausedTheGame = "";
	static int playerNum;
	
	static int score0 = 0;
	static int score1 = 0;
	
	static int P1X = 0;
	static int P1Y;
	static int p2X;
	static int p2Y;
	
	static Ball ball = new Ball(0,0);
	
	
	public static void onSetup(){
		player1 = new Paddle(0, 0, Textures.player1);
		player2 = new Paddle(0, 0, Textures.player2);
		P1Y = (Display.getHeight()/2) - 30; 
		p2Y = (Display.getHeight()/2) - 30;
		p2X = (Display.getWidth() - 20);
		ball.setX(Display.getWidth()/2);
		ball.setY(Display.getHeight()/2);
		BallFollower.createFollowers();
		Server.startServer("192.168.0.7:7777");
	}
	
	public static void onUpdate(){
		ball.setX(ball.getX());
		ball.setY(ball.getY());
		player1.setX(P1X);
		player1.setY(P1Y);
		player2.setX(p2X);
		player2.setY(p2Y);
		BallFollower.onUpdate();
		PaddleShadow.onUpdate();
		player1.draw();
		player2.draw();
		
		Gui.onUpdate();
		Input.onUpdate();
		
		/*AI MOVEMENT*/
		AI.onUpdate(difficulty);
		
		
		/*Text at the top of the screen*/
		Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 4, Color.white);
		Fonts.drawString(score0 + "", (int)(Display.getWidth()*.25)-40, 20, 4, Color.white);
		Fonts.drawString(score1 + "", (int)(Display.getWidth()*.75)-40, 20, 4, Color.white);
		
		//Drawing the Who paused the game text :p
		Fonts.drawString(whoPausedTheGame, Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5, Color.white);
		
		/*Text if someone won the game!*/
		String playerWon[] = {"Player One won the game!", "PLayer Two won the game!"};
		
		if(score0!=21){
			Fonts.drawString("", Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5, Color.white);
		}
		else if(score1!=21){
			Fonts.drawString("", Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5, Color.white);
		}
		
		if(score0>=21)
		{
			Fonts.drawString(playerWon[0], Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5, Color.white);
		}
		else if(score1>=21)
		{
			Fonts.drawString(playerWon[1], Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5, Color.white);
		}
		
		//Setting color after to white
		/*
		 * 1.......2
		 * .       .
		 * .       .
		 * .       .
		 * 4.......3
		 */
		//Ball
		ball.draw();
		
		//Here we draw the text on who paused the screen! :p
		if(isPaused)
		{
			if(playerNum == 0)
			{
				if(isPlayer1Paused && isPlayer2Paused)
				{
					whoPausedTheGame = "Both players paused the game";
				}
				else if(isPlayer1Paused || isPlayer2Paused)
				{
					if(isPlayer1Paused){
						whoPausedTheGame = "Player one paused the game";
					}
					else if(isPlayer2Paused){
						whoPausedTheGame = "Player two paused the game";
					}
				}
			}
			else if(playerNum == 1)
			{
				if(isPlayer1Paused && isPlayer2Paused){
					whoPausedTheGame = "Both players paused the game";
				}
				else if(!isPlayer2Paused){
					whoPausedTheGame = "Player one paused the game!";
				}
				else if(isPlayer2Paused){
					whoPausedTheGame = "Player two paused the game!";
				}
			}
		}
		else
		{
			whoPausedTheGame = ""; //Setting the variable to blank so no text is rendered
		}
	}
}
