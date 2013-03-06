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
	private int height, width;
	private int midHeight, midWidth;
	private float moveToY;
	
	private Texture tex;
	private Texture shadow = Textures.shadow;
	
	public Paddle(int x, int y, int width, int height, Texture tex){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
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
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getMidWidth() {
		return midWidth;
	}
	public void setMidWidth(int midWidth) {
		this.midWidth = this.getWidth()/2;
	}
	
	public int getMidHeight() {
		return midHeight;
	}
	public void setMidHeight(int midHeight) {
		this.midHeight = this.getHeight()/2;
	}
	
	public float getMoveToY() {
		return moveToY;
	}
	public void setMoveToY(float f) {
		this.moveToY = f;
	}
	
	public void draw(){
		if(tex != null){
			tex.bind();
		}
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2i(this.getX() , this.getY());	//1
			glTexCoord2d(1, 0);
			glVertex2i(this.getX() + this.getWidth() , this.getY());	//2
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() + this.getWidth(), this.getY() + this.getHeight());	//3
			glTexCoord2d(0, 1);
			glVertex2i(this.getX() , this.getY() + this.getHeight());	//4
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
			glVertex2i(this.getX() , this.getY());	//1
			glTexCoord2d(1, 0);
			glVertex2i(this.getX() + this.getWidth() , this.getY());	//2
			glTexCoord2d(1, 1);
			glVertex2i(this.getX() + this.getWidth(), this.getY() + this.getHeight());	//3
			glTexCoord2d(0, 1);
			glVertex2i(this.getX() , this.getY() + this.getHeight());	//4
		glEnd();
		
		glColor3f(1,1,1);
	}
}
