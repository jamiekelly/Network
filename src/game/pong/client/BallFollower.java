package game.pong.client;

import static org.lwjgl.opengl.GL11.glColor4f;

public class BallFollower {
	
	private static int numOfFollowers = 20;
	static int delayCount = 0;
	static int delayAmount = 10;
	private static Ball[] ballFollow = new Ball[numOfFollowers];
	
	public static class follow extends Ball
	{
		public follow(int x, int y) 
		{
			super(x, y);
		}
	}
	
	public static void createFollowers()
	{
		for(int i = 0; i < ballFollow.length; i++)
		{
			ballFollow[i] = new follow(0,0);
		}
	}
	public static void onUpdate()
	{
		
		delayCount++;
		if(delayCount >= delayAmount)
		{
			for(int i = ballFollow.length - 1; i > 0; i--)
			{
				ballFollow[i].setX(ballFollow[i - 1].getX());
				ballFollow[i].setY(ballFollow[i - 1].getY());
			}
			ballFollow[0].setX(MainClient.ball.getX());
			ballFollow[0].setY(MainClient.ball.getY());
			delayCount = 0;
		}
		draw();
	}
	private static void draw()
	{
		float opacity = 1F;
		for(int i = 0; i < ballFollow.length; i++)
		{
			glColor4f(1,1,1,opacity);
			ballFollow[i].drawFollower();
			//Math to calculate opacity decrease rate
			opacity -= (float)(100 / ballFollow.length) / 100;
			glColor4f(1,1,1,1);
		}
	}
}
