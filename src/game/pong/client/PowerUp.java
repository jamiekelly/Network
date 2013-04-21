package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.IOException;
import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class PowerUp implements Serializable{
	private int x, y;
	private int w, h;
	private boolean isActivated = false;
	private int activatedBy;
	private int number;
	private PowerUpType powerUp;
	public PowerUp(int x, int y, PowerUpType powerUp){
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;
		this.powerUp = powerUp;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public void setActivatedBy(int activatedBy){
		this.activatedBy = activatedBy;
	}
	public int getActivatedBy(){
		return activatedBy;
	}
	public PowerUpType powerUpType(){
		return this.powerUp;
	}
	public boolean isActivated(){
		double bX = StateGame.ball.getX();
		double bY = StateGame.ball.getY();
		int bW = StateGame.ball.getWAndH();
		int bH = StateGame.ball.getWAndH();
		boolean boxDot = x + w / 2 >= bX && x + w / 2 >= bX + bW && y + h / 2 >= bY && y + h / 2 <= bY + bH;
		boolean powerUpDot = bX + bW / 2 >= x && bX + bW / 2 <= x + w && bY + bH / 2 >= y && bY + bH / 2 <= y + h;
		if(boxDot || powerUpDot){
			isActivated = true;
			if(StateGame.ball.getdX() > 0){
				activatedBy = 1;
			}else{
				activatedBy = 0;
			}
			return true;
		}
		return false;
	}
	public void onUpdate(){
		draw();
	}
	public void draw(){
		Color.white.bind();
		powerUp.tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(x, y); //1
			glTexCoord2d(1, 1);
			glVertex2d(x + w, y); //2
			glTexCoord2d(1, 0);
			glVertex2d(x + w, y + h); //3
			glTexCoord2d(0, 0);
			glVertex2d(x, y + h); //4
		glEnd();
	}
}
