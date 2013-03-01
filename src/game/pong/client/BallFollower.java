package game.pong.client;

public class BallFollower {
	
	private static int numOfFollowers = 5;
	private static Ball[] ballFollow = new Ball[numOfFollowers];
	
	@SuppressWarnings("serial")
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
		int delayCount = 0;
		delayCount++;
		if(delayCount >= 0)
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
		for(int i = 0; i < ballFollow.length; i++)
		{
			ballFollow[i].draw();
		}
	}
}
