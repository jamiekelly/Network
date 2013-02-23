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
	
	static int y = 0;
	static int p2Y = 0;
	
	
	public Main(){
		if(!applet){
			try{
			
				Display.setDisplayMode(new DisplayMode(600, 400));
				Display.setTitle("Two player pong! Over the Internet!");//Isn't
				Display.create();//isn't
				Display.setResizable(false);//isn't
			
			}catch(LWJGLException e){
			e.printStackTrace();
			}
		}
		
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT);
			
			/*
			 * 1.......2
			 * .       .
			 * .       .
			 * .       .
			 * 4.......3
			 */
			
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				if(y > 0){
					y -= 5;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				if(y + 60 < Display.getHeight()){
					y += 5;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				if(y > 0){
					p2Y -= 5;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				if(y + 60 < Display.getHeight()){
					p2Y += 5;
				}
			}
			
			glBegin(GL_QUADS);
				glVertex2i(0 , y);	//1
				glVertex2i(0 + 20 , y);	//2
				glVertex2i(0 + 20, y + 60);	//3
				glVertex2i(0 , y + 60);	//4
			glEnd();
			
			glBegin(GL_QUADS);
				glVertex2i(Display.getWidth() - 20, p2Y);
				glVertex2i(Display.getWidth(), p2Y);
				glVertex2i(Display.getWidth(), p2Y + 60);
				glVertex2i(Display.getWidth() - 20, p2Y + 60);
			glEnd();
			
			Display.sync(60);
			Display.update();
		}
	}
	
	public static void main(String []args){
		new Main();
	}
}
