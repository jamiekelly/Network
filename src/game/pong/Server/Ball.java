package game.pong.Server;

import java.io.Serializable;

import org.lwjgl.opengl.Display;

public class Ball implements Serializable{
	public int x = Display.getWidth() / 2;
	public int y = Display.getHeight() / 2;
	
	public int dX = 5;
	public int dY = 0;
}
