package game.pong.client;

import org.lwjgl.opengl.Display;

public class AI {
	
	private static double easy = 2;  //Edited for easier editing and in game editing
	private static double medium = 3.5;
	private static double hard = 5;
	private static double impossible = 7; 
	
	public static void onUpdate(int difficulty){
		
		/*Difficultly Chart is as follows
		 * Easy = 0
		 * Medium = 1
		 * Hard = 2
		 * */
		if(!StateGame.isPlayer1Paused && StateGame.isSinglePlayer)
		{
			if(StateGame.p2Y > 0)//Stops paddle at boundary edge
			{
				if((StateGame.ball.getCenterOfBallX()) < (Display.getWidth()/2))
				{
					if(StateGame.ball.getCenterOfBallY() < (StateGame.p2Y + (StateGame.player2.getHeight()/2)))
					{  // DUMB AI going UP
						if(difficulty == 0){
							StateGame.p2Y -= AI.getEasy();
						}
						else if(difficulty == 1){
							StateGame.p2Y -= AI.getMedium();
						}
						else if(difficulty == 2){
							StateGame.p2Y -= AI.getHard();
						}
					}
				}
				else
				{  // SMART AI going UP
					if(StateGame.player2.getMoveToY() < (StateGame.p2Y + (StateGame.player2.getHeight()/2)))
					{
						if(difficulty == 0){
							StateGame.p2Y -= AI.getEasy();
						}
						else if(difficulty == 1){
							StateGame.p2Y -= AI.getMedium();
						}
						else if(difficulty == 2){
							StateGame.p2Y -= AI.getHard();
						}
					}
				}
			}
			if(StateGame.p2Y + 60 < Display.getHeight())//Stops paddle at boundary edge
			{
				if((StateGame.ball.getCenterOfBallX()) < (Display.getWidth()/2))
				{
					if(StateGame.ball.getCenterOfBallY() > StateGame.p2Y + (StateGame.player2.getHeight()/2))
					{  // Dumb AI going DOWN
						if(difficulty == 0){
							StateGame.p2Y += AI.getEasy();
						}
						else if(difficulty == 1){
							StateGame.p2Y += AI.getMedium();
						}
						else if(difficulty == 2){
							StateGame.p2Y += AI.getHard();
						}
					}
				}
				else
				{
					if(StateGame.player2.getMoveToY() > (StateGame.p2Y + (StateGame.player2.getHeight()/2)))
					{  //Smart AI going DOWN
						if(difficulty == 0){
							StateGame.p2Y += AI.getEasy();
						}
						else if(difficulty == 1){
							StateGame.p2Y += AI.getMedium();
						}
						else if(difficulty == 2){
							StateGame.p2Y += AI.getHard();
						}
					}
				}
			}
		}
	}

	public static double getEasy() {
		return easy;
	}

	public void setEasy(double easy) {
		AI.easy = easy;
	}

	public static double getMedium() {
		return medium;
	}

	public void setMedium(double medium) {
		AI.medium = medium;
	}

	public static double getHard() {
		return hard;
	}

	public void setHard(double hard) {
		AI.hard = hard;
	}

	public static double getImpossible() {
		return impossible;
	}

	public void setImpossible(double impossible) {
		AI.impossible = impossible;
	}
}
