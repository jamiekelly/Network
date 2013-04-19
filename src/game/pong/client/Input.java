package game.pong.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Input{
	static boolean lmbp = false;
	static boolean rmbp = false;
	static boolean upWasLastp0 = true;
	static boolean upWasLastp1 = true;
	static double paddleStopSpeed = 0.65;  //Higher = slower stop // lower = faster stop
	//Just putting all the input stuff in an actual input class and then calling it from
	//the main class. Tidy stuffs up
	public static void onUpdate(){
		lmbp = false;
		rmbp = false;
		/*
		/	Here we basically did a check if in the current loop the left, or right mouse button is down
		/	if it is down then the variable is set to true, else the variable is not changed and everything
		/	keeps on rolling!
		/
		/	You can see above how the first thing I did was set lmbp (Left mouse button pressed) and rmbp (right mouse button pressed)
		/	to be automatically set to false;
		*/
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
		/*
		/	while(Keyboard.next()) method lets us listen for single key presses, this is very useful as
		/	even though you might think that you pressed the button only once, holding it in would
		/	still tell the computer that the button is down. With the while loop, even if the key is down
		/	it will only be registered as a single press, not the button being down.
		/
		*/
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
		/*
		/ Start of movement for MULTIPLAYER and SINGLE PLAYER modes!
		*/
		if(!StateGame.isPlayer1Paused && !StateGame.isPlayer2Paused)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddles
			{
				if(StateGame.playerNum == 0)
				{
					if(StateGame.p1dY > 0){
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
			}else{//The smooth slow down of the paddle
				//Makes the paddle slow down gradually, it adds a smooth effect to movement
				//Well, smooth for me anyway :D
				if(StateGame.playerNum == 0 && upWasLastp0){
					StateGame.p1dY *= paddleStopSpeed;
				}else if(StateGame.playerNum == 1 && upWasLastp1){
					StateGame.p2dY *= paddleStopSpeed;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(StateGame.playerNum == 0)
				{
					if(StateGame.p1dY + StateGame.player1.getHeight() < Display.getHeight())
					{
						StateGame.p1dY = 5;
						upWasLastp0 = false;
					}
				}
				else if(StateGame.playerNum == 1)
				{
					if(StateGame.p2dY + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}
				}
			}else{//The smooth slow down of the paddle
				if(StateGame.playerNum == 0 && !upWasLastp0){
					StateGame.p1dY *= paddleStopSpeed;
				}else if(StateGame.playerNum == 1 && !upWasLastp1){
					StateGame.p2dY *= paddleStopSpeed;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_B))
			{
				if(StateGame.playerNum == 0){
					StateGame.player2.setHeight(StateGame.player2.getHeight() + 1);
				}
			}
			/*
			/	End of MULTIPLAYER and SINGLEPLAYER input handling!
			*/
			
			
			
			/*
			/	Start of OFFLINE TWO PLAYER MODE input handling for the right side of the keyboard
			/	W to move up
			/	S to move down
			*/
			if(StateGame.isTwoPlayerOfflineMode)
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_W))
				{
					if(StateGame.p2dY > 0)
					{
						StateGame.p2dY = -5;
						upWasLastp1 = false;
					}
				}else{
					if(upWasLastp1){//The smooth slow down of the paddle
						StateGame.p2dY *= paddleStopSpeed;
					}
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_S))
				{
					if(StateGame.p2dY + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}
				}else{//The smooth slow down of the paddle
					if(!upWasLastp1){
						StateGame.p2dY *= paddleStopSpeed;
					}
				}
			}
			/*
			/	End of OFFLINE TWO PLAYER MODE input handling for the right side of the keyboard
			*/
		}
	}
}
