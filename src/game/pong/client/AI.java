package game.pong.client;

import org.lwjgl.opengl.Display;

public class AI {
	public static void onUpdate(int difficulty){
		
		//TODO Improve the AI thinking and such
		/*Difficultly Chart is as follows
		 * Easy = 0
		 * Medium = 1
		 * Hard = 2
		 * Impossible = 3
		 * */
		if(!StateGame.isPlayer1Paused)
		{
			if(StateGame.isSinglePlayer == 0)
			{
				if(StateGame.p2Y > 0)
				{
					if(StateGame.ball.getY() < StateGame.p2Y + 30)
					{
						if(difficulty == 0){
							StateGame.p2Y -= 1.5;
						}
						if(difficulty == 1){
							StateGame.p2Y -= 2.5;
						}
						if(difficulty == 2){
							StateGame.p2Y -= 4;
						}
						if(difficulty == 3){
							StateGame.p2Y = StateGame.ball.getY() - 30;
						}
					}
				}
				if(StateGame.p2Y + 60 < Display.getHeight())
				{
					if(StateGame.ball.getY() > StateGame.p2Y + 30)
					{
						if(difficulty == 0){
							StateGame.p2Y += 1.5;
						}
						if(difficulty == 1){
							StateGame.p2Y += 2.5;
						}
						if(difficulty == 2){
							StateGame.p2Y += 4;
						}
						if(difficulty == 3){
							StateGame.p2Y = StateGame.ball.getY() - 30;
						}
					}
				}
			}
		}
	}
}
