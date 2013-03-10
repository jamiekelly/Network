package game.pong.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	public int playerNum = 0;
	public int x = 0;;
	public int y = 0;
	
	public int score = 0;
}
