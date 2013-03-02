package game.pong.client;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

//saucecode's text rendering in opengl 1.1
//editied by kadence.

/*
 * All credit goes to Saucecode and Kadence for this class.
 * All the things that I've added for my own game will be marked with
 * comments. Happy coding everyone!
 * 
 */

public class Fonts {
	
	static ArrayList<Texture> fontList = new ArrayList<Texture>();
	
	static String alphabet[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
								//"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
								//"y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
								//"K", "L", "M", "N", "O", "P", "Q","R", "S", "T", "U", "V",
								//"W", "X", "Y", "Z"};
	public static void drawCharacter(String c, double x, double y, double size){
		for(int i = 0; i < alphabet.length; i++){
			if(c.equals(alphabet[i])){
				
				fontList.get(i).bind();
				Color activeColor = Color.magenta;
				activeColor.bind();
				glBegin(GL_QUADS);
					glTexCoord2d(0, 0);
					glVertex2d(x, y);
					glTexCoord2d(1, 0);
					glVertex2d(x + (8 * size), y);
					glTexCoord2d(1, 1);
					glVertex2d(x + (8 * size), y + (6 * size));
					glTexCoord2d(0, 1);
					glVertex2d(x, y + (6 * size));
				glEnd();
				
				
			}
		}
	}
	//Goes through the loop and sets up all the textures(All fonts are saved similarly
	//and according to what letter is of what number
	static boolean texSetUp = false;
	public static void setUpTextures(){
		if(!texSetUp){
			for(int i = 0; i < alphabet.length; i++){
				fontList.add(null);
				try {
					fontList.set(i, TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/font/a" + i + ".png"), true));
				} catch (IOException e) {
				}
			}
			texSetUp = true;
		}
	}
	
	public static void drawString(String text, int x, int y){
		for(char c : text.toCharArray()){
			System.out.println("LoL: " + c);
		}
	}
	public static void drawString(String s, int x, int y, int size){
		y += 7 * size;
		int startX = x;
		//I decided to completely re-write all the code for letters...
		//Just because I wanted to add size. Eh...
		GL11.glBegin(GL11.GL_POINTS);
		for(char c : s.toLowerCase().toCharArray()){
			if(c == 'a'){
				for(int i=0;i<8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'b'){
				for(int i=0;i<8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+7, y-5);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-6);
				
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				x+=8;
			}else if(c == 'c'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'd'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-3);
				GL11.glVertex2f(x+6, y-4);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'e'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y-0);
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'f'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'g'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-3);
				GL11.glVertex2f(x+5, y-3);
				GL11.glVertex2f(x+7, y-3);
				
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-7);
				
				x+=8;
			}else if(c == 'h'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x+=8;
			}else if(c == 'i'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+3, y-i);
				}
				for(int i=1;i<=5;i++){
					GL11.glVertex2f(x+i, y-0);
					GL11.glVertex2f(x+i, y-8);
				}
				x+=7;
			}else if(c == 'j'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+6, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-0);
				}
				GL11.glVertex2f(x+1, y-3);
				GL11.glVertex2f(x+1, y-2);
				GL11.glVertex2f(x+1, y-1);
				x+=8;
			}else if(c == 'k'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				GL11.glVertex2f(x+6, y-8);
				GL11.glVertex2f(x+5, y-7);
				GL11.glVertex2f(x+4, y-6);
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+2, y-4);
				GL11.glVertex2f(x+2, y-3);
				GL11.glVertex2f(x+3, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 'l'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				x+=7;
			}else if(c == 'm'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+3, y-6);
				GL11.glVertex2f(x+2, y-7);
				GL11.glVertex2f(x+4, y-5);
				
				GL11.glVertex2f(x+5, y-6);
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+4, y-5);
				x+=8;
			}else if(c == 'n'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+2, y-7);
				GL11.glVertex2f(x+2, y-6);
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+4, y-4);
				GL11.glVertex2f(x+5, y-3);
				GL11.glVertex2f(x+6, y-2);
				GL11.glVertex2f(x+6, y-1);
				x+=8;
			}else if(c == 'o' || c == '0'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				x+=8;
			}else if(c == 'p'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				x+=8;
			}else if(c == 'q'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					if(i != 1) GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					if(i != 6) GL11.glVertex2f(x+i, y-0);
				}
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 'r'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+6, y-6);
				
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+5, y-2);
				GL11.glVertex2f(x+6, y-1);
				GL11.glVertex2f(x+7, y);
				x+=8;
			}else if(c == 's'){
				for(int i=2;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-6);
				GL11.glVertex2f(x+1, y-5);
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y);
				}
				GL11.glVertex2f(x+7, y-3);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+1, y-1);
				GL11.glVertex2f(x+1, y-2);
				x+=8;
			}else if(c == 't'){
				for(int i=0;i<=8;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				x+=7;
			}else if(c == 'u'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-0);
				}
				x+=8;
			}else if(c == 'v'){
				for(int i=2;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+6, y-i);
				}
				GL11.glVertex2f(x+2, y-1);
				GL11.glVertex2f(x+5, y-1);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+4, y);
				x+=7;
			}else if(c == 'w'){
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				GL11.glVertex2f(x+2, y);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+5, y);
				GL11.glVertex2f(x+6, y);
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				x+=8;
			}else if(c == 'x'){
				for(int i=1;i<=7;i++)
					GL11.glVertex2f(x+i, y-i);
				for(int i=7;i>=1;i--)
					GL11.glVertex2f(x+i, y-8-i);
				x+=8;
			}else if(c == 'y'){
				GL11.glVertex2f(x+4, y);
				GL11.glVertex2f(x+4, y-1);
				GL11.glVertex2f(x+4, y-2);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+4, y-4);
				
				GL11.glVertex2f(x+3, y-5);
				GL11.glVertex2f(x+2, y-6);
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-8);
				
				GL11.glVertex2f(x+5, y-5);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-8);
				x+=8;
			}else if(c == 'z'){
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-i);
				}
				GL11.glVertex2f(x+6, y-7);
				x += 8;
			}else if(c == '1'){
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=1;i<=8;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				GL11.glVertex2f(x+3, y-7);
				x += 8;
			}else if(c == '2'){
				for(int i=1;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+1, y-7);
				GL11.glVertex2f(x+1, y-6);
				
				GL11.glVertex2f(x+6, y-7);
				GL11.glVertex2f(x+6, y-6);
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+3, y-2);
				GL11.glVertex2f(x+2, y-1);
				x += 8;
			}else if(c == '3'){
				for(int i=1;i<=5;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y);
				}
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+6, y-i);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x += 8;
			}else if(c == '4'){
				for(int i=2;i<=8;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=7;i++){
					GL11.glVertex2f(x+i, y-1);
				}
				for(int i=0;i<=4;i++){
					GL11.glVertex2f(x+4, y-i);
				}
				x+=8;
			}else if(c == '5'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+i, y-8);
				}
				for(int i=4;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				GL11.glVertex2f(x+1, y-1);
				GL11.glVertex2f(x+2, y);
				GL11.glVertex2f(x+3, y);
				GL11.glVertex2f(x+4, y);
				GL11.glVertex2f(x+5, y);
				GL11.glVertex2f(x+6, y);
				
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				
				GL11.glVertex2f(x+6, y-4);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-4);
				GL11.glVertex2f(x+3, y-4);
				GL11.glVertex2f(x+2, y-4);
				x += 8;
			}else if(c == '6'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y);
				}
				for(int i=2;i<=5;i++){
					GL11.glVertex2f(x+i, y-4);
					GL11.glVertex2f(x+i, y-8);
				}
				GL11.glVertex2f(x+7, y-1);
				GL11.glVertex2f(x+7, y-2);
				GL11.glVertex2f(x+7, y-3);
				GL11.glVertex2f(x+6, y-4);
				x+=8;
			}else if(c == '7'){
				for(int i=0;i<=7;i++)
					GL11.glVertex2f(x+i, y-8);
				GL11.glVertex2f(x+7, y-7);
				GL11.glVertex2f(x+7, y-6);
				
				GL11.glVertex2f(x+6, y-5);
				GL11.glVertex2f(x+5, y-4);
				GL11.glVertex2f(x+4, y-3);
				GL11.glVertex2f(x+3, y-2);
				GL11.glVertex2f(x+2, y-1);
				GL11.glVertex2f(x+1, y);
				x+=8;
			}else if(c == '8'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				x += 8;
			}else if(c == '9'){
				for(int i=1;i<=7;i++){
					GL11.glVertex2f(x+7, y-i);
				}
				for(int i=5;i<=7;i++){
					GL11.glVertex2f(x+1, y-i);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-8);
					GL11.glVertex2f(x+i, y-0);
				}
				for(int i=2;i<=6;i++){
					GL11.glVertex2f(x+i, y-4);
				}
				GL11.glVertex2f(x+1, y-0);
				x += 8;
			}else if(c == '.'){
				GL11.glVertex2f(x+1, y);
				x+=2;
			}else if(c == ','){
				GL11.glVertex2f(x+1, y);
				GL11.glVertex2f(x+1, y-1);
				x+=2;
			}else if(c == '\n'){
				y-=10;
				x = startX;
			}else if(c == ' '){
				x += 8;
			}
		}
		GL11.glEnd();
	}

}