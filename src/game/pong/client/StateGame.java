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
	static boolean isTwoPlayerOfflineMode = false;
	static boolean isConnectingToServer = false;
	static boolean isSinglePlayer = false;
	
	static int difficulty;
	
	static String toIP = "192.168.0.4";
	static String whoPausedTheGame = "";
	static int playerNum;
	
	static int score0 = 0;
	static int score1 = 0;
	
	static int P1X = 0;
	static double P1Y;
	static int p2X;
	static double p2Y;
	
	static double p1dY = 0;
	static double p2dY = 0;
	
	//Making these dynamic so we can add bonuses and boosts easier :p
	static double p1Speed = 5;
	static double p2Speed = 5;
	
	static double p1SpeedMultiplier = 1;
	static double p2SpeedMultiplier = 1;
	static Ball ball = new Ball(0,0, 20);
	
	//static Ball ball2 = new Ball(Display.getWidth()/2, Display.getHeight()/2, 20);
	
	
	public static void onSetup(){
		player1 = new Paddle(0, 0, 20, 60, Textures.player1);
		player2 = new Paddle(0, 0, 20, 60, Textures.player2);
		P1Y = (Display.getHeight()/2) - StateGame.player1.getMidHeight(); 
		p2Y = (Display.getHeight()/2) - StateGame.player2.getMidHeight();
		p2X = (Display.getWidth() -  StateGame.player2.getWidth());
		ball.setX(Display.getWidth()/2);
		ball.setY(Display.getHeight()/2);
		ball.setdY(Math.random()*7-3.5);
		//Ball either goes left or right at start
		if((Math.random()*2)<=1)
		{
			ball.setdX(5);
		}
		else
		{
			ball.setdX(-5);
		}
		BallFollower.createFollowers();
		if(!isConnectingToServer){
			Server.startServer();
		}else{
			Server.connectToServer(toIP);
		}
	}
	
	public static void onUpdate(int delta){
		p1Speed = (delta * (p1dY * p1SpeedMultiplier)) * 0.05;
		p2Speed = (delta * (p2dY * p2SpeedMultiplier)) * 0.05;
		
		P1Y += p1Speed;
		p2Y += p2Speed;
		System.out.println(p1Speed);
		
		ball.setX(ball.getX());
		ball.setY(ball.getY());
		
		player1.setX(P1X);
		player1.setY(P1Y);
		player2.setX(p2X);
		player2.setY(p2Y);
		BallFollower.onUpdate();
		
		player1.draw();
		player2.draw();
		
		Input.onUpdate();
		
		/*AI MOVEMENT*/
		if(isSinglePlayer){
			AI.onUpdate(difficulty);
		}
		
		
		/*Text at the top of the screen*/
		Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 1, Color.white);
		Fonts.drawString(score0 + "", (int)(Display.getWidth()*.25)-40, 20, 1, Color.white);
		Fonts.drawString(score1 + "", (int)(Display.getWidth()*.75)-40, 20, 1, Color.white);
		
		//Drawing the Who paused the game text :p
		Fonts.drawString(whoPausedTheGame, Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  1, Color.white);
		
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
		ball.draw();
		PowerUpList.draw();
		
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