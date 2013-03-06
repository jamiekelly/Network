package game.pong.client;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

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
import static org.lwjgl.opengl.GL11.glViewport;
public class MainClient {
	
	public static void main(String []args)
	{
		new MainClient();
	}

	private MainClient()
	{
		if(!StateGame.applet)
		{
			try
			{
				Display.setDisplayMode(new DisplayMode(600, 400));
				Display.setTitle("PONG!");
				Display.create();
				Display.setResizable(false);
			
			}
			catch(LWJGLException e){e.printStackTrace();}
		}
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glViewport(0,0,600,400);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		Fonts.setUpTextures();
		Textures.loadTextures();
		
		/*THE START OF THE GAME SCREEN*/
		//Gui.addNewTextBox(new Gui.text("", 100, 200, 2, "this", "sweetLord", Color.blue));
		//Gui.textArray[Gui.getTextByName("sweetLord")].setBackgroundVisibility(true);
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			StateManager.onUpdate();
			
			Display.sync(60);
			Display.update();
		}
		Display.destroy();
		System.exit(0);
	}
}
