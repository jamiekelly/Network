package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

public class GuiText {
	private double x, y, size;
	private String text, state;
	public GuiText(String text, double x, double y, double size, String state){
		this.text = text;
		this.x = x;
		this.y = y;
		this.size = size;
		this.state = state;
	}
	
	public void drawBoxWithText(){
		int width = Fonts.getWidth(text, size);
		int height = Fonts.getHeight(text, size);
		
		Textures.textBackground.bind();
		glBegin(GL_QUADS);//ball.getY() thingy
			glTexCoord2d(0, 1);
			glVertex2i((int) x - (2 * (int)size),(int) y - (2 * (int)size)); //1
			glTexCoord2d(1, 1);
			glVertex2i((int) x - (2 * (int)size) + width + 30,(int) y - (2 * (int)size)); //2
			glTexCoord2d(1, 0);
			glVertex2i((int) x - (2 * (int)size)+ width + 30, (int) y - (2 * (int)size) + height + 30); //3
			glTexCoord2d(0, 0);
			glVertex2i((int) x - (2 * (int)size), (int) y - (2 * (int)size) + height + 30); //4
		glEnd();
		
		Fonts.drawString(text, (int) x, (int) y, size);
	}
}
