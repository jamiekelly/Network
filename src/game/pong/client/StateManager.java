package game.pong.client;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;



public class StateManager {
	
	/*
	 * Different states include:
	 * 	intro - Introduction screen
	 * 	load - Loading all textures and fonts and such
	 * 	menu - Main menu
	 * 	settings - Changing settings and stuff
	 * 	game - actual game screen
	 * 	win - winning screen
	 * 	lose - losing screen
	 */
	static String State = "menu";
	static String changeToState = "";
	
	static boolean hasFinishedChanging = false;
	static boolean isHalfWayChanging = false;
	static boolean hasStartedChanging = false;
	
	static boolean isIntroSetUp = false;
	static boolean isLoadSetUp = false;
	static boolean isMenuSetUp = false;
	static boolean isSettingsSetUp = false;
	static boolean isGameSetUp = false;
	static boolean isWinSetUp = false;
	static boolean isLoseSetUp = false;
	
	static float opacity = 0F;
	
	public static void onUpdate(int delta){
		Gui.onUpdate();
		if(State.equals("intro")){
			if(!isIntroSetUp){
				isIntroSetUp =  true;
			}
			
		}else if(State.equals("load")){
			if(!isLoadSetUp){
				isLoadSetUp = true;
			}
			
		}else if(State.equals("menu")){
			if(!isMenuSetUp){
				StateMenu.onSetup();
				isMenuSetUp = true;
			}
			StateMenu.onUpdate();
		}else if(State.equals("settings")){
			if(!isSettingsSetUp){
				isSettingsSetUp = true;
			}
			
		}else if(State.equals("game")){
			if(!isGameSetUp){
				StateGame.onSetup();
				isGameSetUp = true;
			}
			StateGame.onUpdate(delta);
		}else if(State.equals("win")){
			if(!isWinSetUp){
				isWinSetUp = true;
			}
			
		}else if(State.endsWith("lose")){
			if(!isLoseSetUp){				
				isLoseSetUp = true;
			}
			
		}else{
			System.out.println("Invalid state!");
		}
		updateChange();
	}
	public static void updateChange(){
		if(hasStartedChanging){
			if(opacity >= 1){
				isHalfWayChanging = true;
				State = changeToState;
			}
			if(opacity <= 0 && isHalfWayChanging){
				hasStartedChanging = false;
				isHalfWayChanging = false;
				hasFinishedChanging = true;
			}
			if(hasStartedChanging && !isHalfWayChanging){
				Color.white.bind();
				opacity += 0.01F;
				Textures.none.bind();
				glColor4f(0,0,0, opacity);
				
				
			}
			if(isHalfWayChanging){
				opacity -= 0.01F;
				Textures.none.bind();
				glColor4f(0,0,0, opacity);
			}
			
			glBegin(GL_QUADS);
				glTexCoord2d(0, 0);
				glVertex2i(0, 0);	//1
				glTexCoord2d(1, 0);
				glVertex2i(Display.getWidth(), 0);	//2
				glTexCoord2d(1, 1);
				glVertex2i(Display.getWidth(), Display.getHeight());	//3
				glTexCoord2d(0, 1);
				glVertex2i(0, Display.getHeight());	//4
			glEnd();
			glColor4f(0,0,0,1F);
		}
	}
	public static void changeState(String toState){
		if(!State.equals(toState)){
			changeToState = toState;
			hasStartedChanging = true;
			hasFinishedChanging = false;
		}
	}
	
}
