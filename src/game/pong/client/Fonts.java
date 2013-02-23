package game.pong.client;


import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.GL_QUADS;

//saucecode's text rendering in opengl 1.1
//editied by kadence.

/*
 * All credit goes to Saucecode and Kadence for this class.
 * All the things that I've added for my own game will be marked with
 * comments. Happy coding everyone!
 * 
 */

public class Fonts {
	public static void drawBox(int x, int y, int width, int height,int size){
		glBegin(GL_QUADS);
			glVertex2f(x , y - height * size);
			glVertex2f(x + width * size, y - height * size);
			glVertex2f(x + width * size, y);
			glVertex2f(x , y);
		glEnd();
	}
	public static void drawString(String s, int x, int y, int size){
		y += 7 * size;
		int startX = x;
		//I decided to completely re-write all the code for letters...
		//Just because I wanted to add size. Eh...
			for(char c: s.toLowerCase().toCharArray()){
				if(c == 'a'){
					drawBox(x , y, 1, 8, size);
					drawBox(x + 6 * size,y, 1, 8, size);
					
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					drawBox(x + 1 * size, y - 4 * size, 5, 1, size);
					x += 8 * size;
				}else if(c == 'b'){
					drawBox(x, y, 1, 8, size);
					
					drawBox(x, y, 6, 1, size);
					drawBox(x, y - 4 * size, 6, 1, size);
					drawBox(x, y - 8 * size, 6, 1, size);
					
					drawBox(x + 6 * size, y - 1 * size, 1, 3, size);
					drawBox(x + 6 * size, y - 5 * size, 1, 3, size);
					x += 8 * size;
				}else if(c == 'c'){
					drawBox(x, y - 1 * size, 1, 7, size);
					
					drawBox(x + 1 * size, y, 5, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y - 1 * size, 1, 2, size);
					drawBox(x + 6 * size, y - 6 * size, 1, 2, size);
					x += 8 * size;
				}else if(c == 'd'){
					drawBox(x, y, 1, 9, size);
					
					drawBox(x + 1 * size, y, 5, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y - 1 * size, 1, 7, size);
					
					x += 8 * size;
				}else if(c == 'e'){
					drawBox(x, y, 1, 8, size);
					
					drawBox(x, y, 7, 1, size);
					drawBox(x, y - 4 * size, 7, 1, size);
					drawBox(x, y - 8 * size, 7, 1, size);
					x += 8 * size;
				}else if(c == 'f'){
					drawBox(x, y, 1, 8, size);
					
					drawBox(x, y - 4 * size, 5, 1, size);
					drawBox(x, y - 8 * size, 7, 1, size);
					x += 8 * size;
				}else if(c == 'g'){
					drawBox(x, y - 1 * size, 1, 7, size);
					
					drawBox(x + 1 * size, y, 5, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y - 1 * size, 1, 2, size);
					drawBox(x + 6 * size, y - 7 * size, 1, 2, size);
					
					drawBox(x + 4 * size, y - 3 * size, 3, 1, size);
					
					x += 8 * size;
				}else if(c == 'h'){
					drawBox(x, y, 1, 9, size);
					drawBox(x + 6 * size, y, 1, 9, size);
					
					drawBox(x, y - 4 * size, 6, 1, size);
					x += 8 * size;
				}else if(c == 'i'){
					drawBox(x, y, 7, 1, size);
					drawBox(x, y - 8 * size, 7, 1, size);
					
					drawBox(x + 3 * size, y - 1 * size, 1, 7, size);
					x += 8 * size;
				}else if(c == 'j'){
					drawBox(x, y - 1 * size, 1, 2, size);
					
					drawBox(x + 1 * size, y , 4, 1, size);
					
					drawBox(x + 5 * size, y - 1 * size, 1, 8, size);
					
					x += 7 * size;
				}else if(c =='k'){
					drawBox(x, y, 1, 9, size);
					
					drawBox(x + 1 * size, y - 4 * size, 1, 1, size);
					drawBox(x + 2 * size, y - 5 * size, 1, 1, size);
					drawBox(x + 3 * size, y - 6 * size, 1, 1, size);
					drawBox(x + 4 * size, y - 7 * size, 1, 1, size);
					drawBox(x + 5 * size, y - 8 * size, 1, 1, size);
					
					drawBox(x + 2 * size, y - 3 * size, 1, 1, size);
					drawBox(x + 3 * size, y - 2 * size, 1, 1, size);
					drawBox(x + 4 * size, y - 1 * size, 1, 1, size);
					drawBox(x + 5 * size, y, 1, 1, size);
					x += 7 * size;
				}else if(c == 'l'){
					drawBox(x, y, 1, 9, size);
					
					drawBox(x + 1 * size, y, 6, 1, size);
					x += 8 * size;
				}else if(c == 'm'){
					drawBox(x, y, 1, 9, size);
					drawBox(x + 6 * size, y, 1, 9, size);
					
					drawBox(x + 1 * size, y - 7 * size, 1,1, size);
					drawBox(x + 2 * size, y - 6 * size, 1,1, size);
					drawBox(x + 3 * size, y - 5 * size, 1,1, size);
					
					drawBox(x + 4 * size, y - 6 * size, 1,1, size);
					drawBox(x + 5 * size, y - 7 * size, 1,1, size);
					x += 8 * size;
				}else if(c == 'n'){
					drawBox(x, y, 1, 9,size);
					drawBox(x + 6 * size, y, 1, 9, size);
					
					drawBox(x + 1 * size, y - 6 * size, 1,1, size);
					drawBox(x + 2 * size, y - 5 * size, 1,1, size);
					drawBox(x + 3 * size, y - 4 * size, 1,1, size);
					drawBox(x + 4 * size, y - 3 * size, 1,1, size);
					drawBox(x + 5 * size, y - 2 * size, 1,1, size);
					
					x += 8 * size;
				}else if(c == 'o'){
					drawBox(x, y - 1 * size, 1, 7, size);
					drawBox(x + 6 * size, y - 1 * size, 1, 7, size);
					
					drawBox(x + 1 * size, y, 5, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					
					x += 8 * size;
				}else if(c == 'q'){
					drawBox(x, y - 1 * size, 1, 7, size);
					drawBox(x + 6 * size, y - 2 * size, 1, 6, size);
					
					drawBox(x + 1 * size, y, 4, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y, 1,1, size);
					drawBox(x + 5 * size, y - 1 * size, 1,1, size);
					drawBox(x + 4 * size, y - 2 * size, 1,1, size);
					drawBox(x + 3 * size, y - 3 * size, 1,1, size);
					
					x += 8 * size;
				}else if(c == 'p'){
					drawBox(x, y, 1, 9, size);
					
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					drawBox(x + 1 * size, y - 4 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y - 5 * size, 1, 3, size);
					
					x += 8 * size;
				}else if(c == 'r'){
					drawBox(x, y, 1, 9, size);
					
					drawBox(x + 1 * size, y - 8 * size, 5, 1, size);
					drawBox(x + 1 * size, y - 4 * size, 5, 1, size);
					
					drawBox(x + 6 * size, y - 5 * size, 1, 3, size);
					
					drawBox(x + 2 * size, y - 3 * size, 1, 1, size);
					drawBox(x + 3 * size, y - 2 * size, 1, 1, size);
					drawBox(x + 4 * size, y - 1 * size, 1, 1, size);
					drawBox(x + 5 * size, y, 1, 1, size);
					x += 8 * size;
				}else if(c == 's'){
					drawBox(x + 1 * size, y, 4, 1, size);
					drawBox(x + 1 * size, y - 4 * size, 4, 1, size);
					drawBox(x + 1 * size, y - 8 * size, 4, 1, size);
					
					drawBox(x, y - 5 * size, 1, 3, size);
					drawBox(x + 5 * size, y - 1 * size, 1, 3, size);
					drawBox(x, y - 1 * size, 1, 2, size);
					drawBox(x + 5 * size, y - 6 * size, 1,2,size);
					x += 7 * size;
				}else if(c == 't'){
					drawBox(x, y - 8 * size, 7, 1, size);
					
					drawBox(x + 3 * size, y, 1, 8, size);
					x += 8 * size;
				}else if(c == 'u'){
					drawBox(x, y - 1 * size, 1, 8, size);
					drawBox(x + 6 * size, y - 1 * size, 1, 8, size);
					
					drawBox(x + 1 * size, y, 5, 1, size);
					
					x += 8 * size;
				}else if(c == 'v'){
					drawBox(x, y - 3 * size, 1, 6,size);
					drawBox(x + 6 * size, y - 3 * size, 1, 6,size);
					
					drawBox(x + 1 * size, y - 2 * size, 1,1, size);
					drawBox(x + 2 * size, y - 1 * size, 1,1, size);
					drawBox(x + 3 * size, y - 0 * size, 1,1, size);
					
					drawBox(x + 4 * size, y - 1 * size, 1,1, size);
					drawBox(x + 5 * size, y - 2 * size, 1,1, size);
					x += 8 * size;
				}else if(c == 'w'){
					drawBox(x, y - 1 * size, 1, 8, size);
					drawBox(x + 6 * size, y - 1 * size, 1, 8, size);
					drawBox(x + 3 * size, y - 1 * size, 1, 6, size);
					
					drawBox(x + 1 * size, y, 2, 1, size);
					drawBox(x + 4 * size, y, 2, 1, size);
					
					x += 8 * size;
				}else if(c == 'x'){
					drawBox(x, y, 1,2,size);
					drawBox(x + 1 * size, y - 2 * size, 1, 2, size);
					drawBox(x + 2 * size, y - 4 * size, 1, 1, size);
					drawBox(x + 3 * size, y - 5 * size, 1, 2, size);
					drawBox(x + 4 * size, y - 7 * size, 1, 2, size);
					
					drawBox(x, y - 7 * size, 1,2,size);
					drawBox(x + 1 * size, y - 5 * size, 1, 2, size);
					drawBox(x + 3 * size, y - 2 * size, 1, 2, size);
					drawBox(x + 4 * size, y - 0 * size, 1, 2, size);
					
					x += 6 * size;
				}else if(c == 'y'){
					drawBox(x, y - 7 * size, 1, 2, size);
					drawBox(x + 1 * size, y - 6 * size, 1,1, size);
					drawBox(x + 2 * size, y - 5 * size, 1,1, size);
					drawBox(x + 3 * size, y - 0 * size, 1,5, size);
					
					drawBox(x + 4 * size, y - 5 * size, 1,1, size);
					drawBox(x + 5 * size, y - 6 * size, 1,1, size);
					drawBox(x + 6 * size, y - 7 * size, 1,2,size);
					
					x += 8 * size;
				}else if(c == 'z'){
					drawBox(x, y - 1 * size, 1,1,size);
					drawBox(x + 1 * size, y - 2 * size, 1,1,size);
					drawBox(x + 2 * size, y - 3 * size, 1,1,size);
					drawBox(x + 3 * size, y - 4 * size, 1,1,size);
					drawBox(x + 4 * size, y - 5 * size, 1,1,size);
					drawBox(x + 5 * size, y - 6 * size, 1,1,size);
					drawBox(x + 5 * size, y - 7 * size, 1,1,size);
					drawBox(x, y, 6, 1, size);
					drawBox(x, y - 8 * size, 6, 1,size);
					
					x += 7 * size;
				}else if(c == ' '){
					x += 8 * size;
				}else if(c == '.'){
					drawBox(x, y, 1, 1, size);
					x += 2 * size;
				}else if(c == '/'){
					x += 4 * size;
				}
			}
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