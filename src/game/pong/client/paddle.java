package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glColor3f;

import org.newdawn.slick.opengl.Texture;

public class Paddle {
	private int x, y;
	private Texture tex;
	private Texture shadow = Textures.shadow;
	public Paddle(int x, int y, Texture tex){
		this.x = x;
		this.y = y;
		this.tex = tex;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void draw(){
		if(tex != null){
			tex.bind();
		}
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
	}
	public void drawShadow(){
		if(tex.equals(Textures.player1)){
			glColor3f(1,0,0);
		}else{
			glColor3f(0,0,1);
		}
		shadow.bind();
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
		
		glColor3f(1,1,1);
	}
}
