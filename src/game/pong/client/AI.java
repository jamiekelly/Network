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
		if(!MainClient.isPlayer1Paused)
		{
			if(MainClient.isSinglePlayer == 0)
			{
				if(MainClient.p2Y > 0)
				{
					if(MainClient.ball.getY() < MainClient.p2Y + 30)
					{
						if(difficulty == 0){
							MainClient.p2Y -= 1.5;
						}
						if(difficulty == 1){
							MainClient.p2Y -= 2.5;
						}
						if(difficulty == 2){
							MainClient.p2Y -= 4;
						}
						if(difficulty == 3){
							MainClient.p2Y = MainClient.ball.getY() - 30;
						}
					}
				}
				if(MainClient.p2Y + 60 < Display.getHeight())
				{
					if(MainClient.ball.getY() > MainClient.p2Y + 30)
					{
						if(difficulty == 0){
							MainClient.p2Y += 1.5;
						}
						if(difficulty == 1){
							MainClient.p2Y += 2.8;
						}
						if(difficulty == 2){
							MainClient.p2Y += 4;
						}
						if(difficulty == 3){
							MainClient.p2Y = MainClient.ball.getY() - 30;
						}
					}
				}
			}
		}
	}
}
