package game.pong.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Input{
	static boolean lmbp = false;
	static boolean rmbp = false;
	static boolean upWasLastp0 = true;
	static boolean upWasLastp1 = true;
	//Just putting all the input stuff in an actual input class and then calling it from
	//the main class. Tidy stuffs up
	public static void onUpdate(){
		lmbp = false;
		rmbp = false;
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				if(Mouse.isButtonDown(0)){
					lmbp = true;
				}
				if(Mouse.isButtonDown(1)){
					rmbp = true;
				}
			}
		}
		while(Keyboard.next())
		{
			if(Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if(StateGame.playerNum == 0)
				{
					if(!StateGame.isPlayer1Paused){
						StateGame.isPlayer1Paused = true;
					}
					else if(StateGame.isPlayer1Paused){
						StateGame.isPlayer1Paused = false;
					}
				}
				if(StateGame.playerNum == 1)
				{
					if(!StateGame.isPlayer2Paused){
						StateGame.isPlayer2Paused = true;
					}
					else if(StateGame.isPlayer2Paused){
						StateGame.isPlayer2Paused = false;
					}  //Changed from == false because I think it seems nicer like this. Don't know why
				}
			}
			if(Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_G)){
				PowerUpList.addNewPowerUp(new PowerUpList.Powerup(20, 40, PowerUpType.SpeedBoost));
			}
		}
		
		if(!StateGame.isPlayer1Paused && !StateGame.isPlayer2Paused)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddles
			{
				if(StateGame.playerNum == 0)
				{
					if(StateGame.P1Y > 0){
						StateGame.p1dY = -5;
						upWasLastp0 = true;
					}
				}
				else if(StateGame.playerNum == 1)
				{
					if(StateGame.p2dY > 0)
					{
						StateGame.p2dY = -5;
						upWasLastp1 = true;
					}
				}
			}else{
				//Makes the paddle slow down gradually, it adds a smooth effect to movement
				//Well, smooth for me anyway :D
				if(StateGame.playerNum == 0 && upWasLastp0){
					StateGame.p1dY *= 0.9;
				}else if(StateGame.playerNum == 1 && upWasLastp1){
					StateGame.p2dY *= 0.9;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(StateGame.playerNum == 0)
				{
					if(StateGame.P1Y + StateGame.player1.getHeight() < Display.getHeight())
					{
						StateGame.p1dY = 5;
						upWasLastp0 = false;
					}
				}
				else if(StateGame.playerNum == 1)
				{
					if(StateGame.p2Y + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}
				}
			}else{
				if(StateGame.playerNum == 0 && !upWasLastp0){
					StateGame.p1dY *= 0.9;
				}else if(StateGame.playerNum == 1 && !upWasLastp1){
					StateGame.p2dY *= 0.9;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_B))
			{
				if(StateGame.playerNum == 0){
					StateGame.player2.setHeight(StateGame.player2.getHeight() + 1);
				}
			}
			if(StateGame.isTwoPlayerOfflineMode)
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_W))
				{
					if(StateGame.p2Y > 0)
					{
						StateGame.p2dY = -5;
						upWasLastp1 = false;
					}
				}else{
					if(upWasLastp1){
						StateGame.p2dY *= 0.9;
					}
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_S))
				{
					if(StateGame.p2Y + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}
				}else{
					if(!upWasLastp1){
						StateGame.p2dY *= 0.9;
					}
				}
			}
		}
	}
}
