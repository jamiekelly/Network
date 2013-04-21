package game.pong.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Input{
	static boolean lmbp = false;
	static boolean rmbp = false;
	static boolean upWasLastp0 = true;
	static boolean upWasLastp1 = true;
	static double paddleStopSpeed = 0.8;  //Higher = slower stop // lower = faster stop
	//Just putting all the input stuff in an actual input class and then calling it from
	//the main class. Tidy stuffs up
	static boolean isP1Stunned = false; //From power ups, if player is stunned then they won't be able to move
	static boolean isP2Stunned = false; //From power ups, if player is stunned then they won't be able to move
	
	static int stunP1Time = 100;
	static int stunP1Current = 0;
	
	static int stunP2Time = 100;
	static int stunP2Current = 0;
	public static void onUpdate(){
		if(isP1Stunned){
			stunP1Current++;
			if(stunP1Current >= stunP1Time){
				stunP1Current = 0;
				isP1Stunned = false;
			}
		}
		if(isP2Stunned){
			stunP2Current++;
			if(stunP2Current >= stunP2Time){
				stunP2Current = 0;
				isP2Stunned = false;
			}
		}
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
				PowerUpList.addNewRandomPowerUp();
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
					if(StateGame.P1Y > 0){
						StateGame.p1dY = -5;
						upWasLastp0 = true;
					}else{
						StateGame.p1dY = 0;
					}
					if(isP1Stunned){
						StateGame.p1dY = 0;
					}
				}
				else if(StateGame.playerNum == 1)
				{
					if(StateGame.p2Y > 0)
					{
						StateGame.p2dY = -5;
						upWasLastp1 = true;
					}else{
						StateGame.p2dY = 0;
					}
					if(isP2Stunned){
						StateGame.p2dY = 0;
					}
				}
			}else{
				if(StateGame.playerNum == 0 && upWasLastp0){      //The smooth slow down of the paddle
					StateGame.p1dY *= paddleStopSpeed;			  //Makes the paddle slow down gradually, 
				}else if(StateGame.playerNum == 1 && upWasLastp1){//it adds a smooth effect to movement
					StateGame.p2dY *= paddleStopSpeed;			  //Well, smooth for me anyway :D
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
					}else{
						StateGame.p1dY = 0;
					}
					if(isP1Stunned){
						StateGame.p1dY = 0;
					}
				}
				else if(StateGame.playerNum == 1)
				{
					if(StateGame.p2Y + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}else{
						StateGame.p2dY = 0;
					}
					if(isP2Stunned){
						StateGame.p2dY = 0;
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
					if(StateGame.p2Y > 0)
					{
						StateGame.p2dY = -5;
						upWasLastp1 = false;
					}else{
						StateGame.p1dY = 0;
					}
					if(isP2Stunned){
						StateGame.p2dY = 0;
					}
				}else{
					if(upWasLastp1){//The smooth slow down of the paddle
						StateGame.p2dY *= paddleStopSpeed;
					}
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_S))
				{
					if(StateGame.p2Y + StateGame.player2.getHeight() < Display.getHeight())
					{
						StateGame.p2dY = 5;
						upWasLastp1 = false;
					}else{
						StateGame.p2dY = 0;
					}
					if(isP2Stunned){
						StateGame.p2dY = 0;
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
