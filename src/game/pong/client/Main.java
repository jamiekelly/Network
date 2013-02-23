package game.pong.client;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;


import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glBlendFunc;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import static org.lwjgl.opengl.GL11.GL_QUADS;
public class Main {
	
	
	static boolean applet = false;
	
	static int playerNum = 0;
	
	static int x = 0;
	static int y;
	static int p2X = 0;
	static int p2Y;
	
	static int ballx;
	static int bally;	
	
	
	public Main()
	{
		if(!applet)
		{
			try
			{
			
				Display.setDisplayMode(new DisplayMode(600, 400));
				Display.setTitle("Two player pong! Over the Internet!");//Isn't
				Display.create();//isn't
				Display.setResizable(false);//isn't
			
			}catch(LWJGLException e)
			{
				e.printStackTrace();
			}
		}
		
		/*The 30 will need to be changed if paddle heights are modified*/
		
		y = (Display.getHeight()/2) - 30; 
		p2Y = (Display.getHeight()/2) - 30;
		
		ballx = Display.getHeight()/2;
		bally = Display.getHeight()/2;	
		
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			/* Controls */
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Left side paddle, player 0
			{
				if(y > 0)
				{
					y -= 5;
					//TODO Update server about location
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(y + 60 < Display.getHeight())
				{
					y += 5;
					//TODO Update server about location
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)) //Right side paddle, player 1
			{
				if(p2Y > 0)
				{
					p2Y -= 5;
					//TODO Update server about location
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				if(p2Y + 60 < Display.getHeight())
				{
					p2Y += 5;
					//TODO Update server about location
				}
			}
			
			/*Text at the top of the screen*/
			Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 1);
			Font.drawString(score0, (Display.getWidth()*.25)-40, 20, 1);
			Font.drawString(score1, (Display.getWidth()*.75)-40, 20, 1);
			
			/* */
			
			
			
			/*
			 * 1.......2
			 * .       .
			 * .       .
			 * .       .
			 * 4.......3
			 */
			glBegin(GL_QUADS);
				glVertex2i(x , y);	//1
				glVertex2i(x + 20 , y);	//2
				glVertex2i(x + 20, y + 60);	//3
				glVertex2i(x , y + 60);	//4
			glEnd();
			
			glBegin(GL_QUADS);
				glVertex2i(Display.getWidth() - 20, p2Y); //1
				glVertex2i(Display.getWidth(), p2Y); //2
				glVertex2i(Display.getWidth(), p2Y + 60); //3
				glVertex2i(Display.getWidth() - 20, p2Y + 60); //4
			glEnd();
			
			Display.sync(60);
			Display.update();
		}
	}
	
	public static void main(String []args)
	{
		new Main();
	}
}
