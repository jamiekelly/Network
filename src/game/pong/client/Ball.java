package game.pong.client;

import java.io.Serializable;

import org.lwjgl.opengl.Display;

@SuppressWarnings("serial")
public class Ball implements Serializable{
	public int x = Display.getWidth() / 2;
	public int y = Display.getHeight() / 2;
	
	public float dX = 5;
	public float dY = 0;
}
