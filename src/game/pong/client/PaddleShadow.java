package game.pong.client;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.newdawn.slick.opengl.Texture;

public class PaddleShadow {
	
	static int maxShadows = 1;//I edited this for testing since it got annoying
	static int delayCount = 0;
	static int delayAmount = 0;
	
	static boolean isGenerated = false;
	
	static Paddle[] padShadow1 = new Paddle[maxShadows];
	static Paddle[] padShadow2 = new Paddle[maxShadows];
	
	public static class pShaddow extends Paddle{
		public pShaddow(int x, int y, Texture tex) {
			super(x, y, 20, 60, tex);
		}
	}
	public static void createShadows(){
		for(int i = 0; i < padShadow1.length; i++){
			padShadow1[i] = new pShaddow(-100, 0, Textures.player1);
		}
		for(int i = 0; i < padShadow2.length; i++){
			padShadow2[i] = new pShaddow(-100, 0, Textures.player2);
		}
	}
	public static void onUpdate(){
		if(!isGenerated){
			createShadows();
			isGenerated = true;
		}
		delayCount++;
		if(delayCount >= delayAmount){
			for(int i = padShadow1.length - 1; i > 0; i--){
				padShadow1[i].setX(padShadow1[i - 1].getX());
				padShadow1[i].setY(padShadow1[i - 1].getY());
			}
			for(int i = padShadow2.length - 1; i > 0; i--){
				padShadow2[i].setX(padShadow2[i - 1].getX());
				padShadow2[i].setY(padShadow2[i - 1].getY());
			}
			padShadow1[0].setX(StateGame.player1.getX());
			padShadow1[0].setY(StateGame.player1.getY());
			
			padShadow2[0].setX(StateGame.player2.getX());
			padShadow2[0].setY(StateGame.player2.getY());
			delayCount = 0;
		}
		draw();
	}
	public static void draw(){
		float opacity = 0.5F;
		for(int i = 0; i < padShadow1.length; i++)
		{
			glColor4f(1,1,1,opacity);
			padShadow1[i].drawShadow();
			//Math to calculate opacity decrease rate
			opacity -= (float)(100 / padShadow1.length) / 100;
			glColor4f(1,1,1,1);
		}
		for(int i = 0; i < padShadow2.length; i++)
		{
			glColor4f(1,1,1,opacity);
			padShadow2[i].drawShadow();
			//Math to calculate opacity decrease rate
			opacity -= (float)(100 / padShadow2.length) / 100;
			glColor4f(1,1,1,1);
		}
	}
}
