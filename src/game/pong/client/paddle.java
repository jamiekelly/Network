package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Paddle {
	private int x, y;
	public Paddle(int x, int y){
		
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void draw(){
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/font/a" + 1 + ".png"), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2i(x , y);	//1
			glTexCoord2d(1, 0);
			glVertex2i(x + 20 , y);	//2
			glTexCoord2d(1, 1);
			glVertex2i(x + 20, y + 60);	//3
			glTexCoord2d(0, 1);
			glVertex2i(x , y + 60);	//4
		glEnd();
		tex.release();
	}
}
