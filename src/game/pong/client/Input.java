package game.pong.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Input{
	
	//Just putting all the input stuff in an actual input class and then calling it from
	//the main class. Tidy stuffs up
	public static void onUpdate(){
		while(Keyboard.next())
		{
			if(Keyboard.getEventKeyState())
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_P))
				{
					if(MainClient.playerNum == 0)
					{
						if(MainClient.isPlayer1Paused == false){
							MainClient.isPlayer1Paused = true;
						}
						else if(MainClient.isPlayer1Paused == true){
							MainClient.isPlayer1Paused = false;
						}
					}
					if(MainClient.playerNum == 1)
					{
						if(MainClient.isPlayer2Paused == false){
							MainClient.isPlayer2Paused = true;
						}
						else if(MainClient.isPlayer2Paused == true){
							MainClient.isPlayer2Paused = false;
						}  //Changed from == false because I think it seems nicer like this. Don't know why
					}
				}
			}
		}
		
		if(!MainClient.isPlayer1Paused && !MainClient.isPlayer2Paused)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddles
			{
				if(MainClient.P1Y > 0)
				{
					if(MainClient.playerNum == 0 && !MainClient.isPlayer1Paused){
						MainClient.P1Y -= 5;
					}else if(MainClient.playerNum == 1 && !MainClient.isPlayer2Paused){
						MainClient.p2Y -= 5;
					}
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(MainClient.P1Y + 60 < Display.getHeight())
				{
					if(MainClient.playerNum == 0 && !MainClient.isPlayer1Paused){
						MainClient.P1Y += 5;
					}else if(MainClient.playerNum == 1 && !MainClient.isPlayer2Paused){
						MainClient.p2Y += 5;
					}
				}
			}
		}
	}
}
