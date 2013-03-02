package game.pong.client;


public class StateManager {
	
	/*
	 * Different states include:
	 * 	intro - Introduction screen
	 * 	load - Loading all textures and fonts and such
	 * 	menu - Main menu
	 * 	settings - Changing settings and stuff
	 * 	game - actual game screen
	 * 	win - winning screen
	 * 	loose - loosing screen
	 */
	static String State = "load";
	
	public static void draw(){
		
	}
	
	public static void onUpdate(){
		if(State.equals("intro")){
			
		}else if(State.equals("load")){
			
		}else if(State.equals("menu")){
			
		}else if(State.equals("settings")){
			
		}else if(State.equals("game")){
			
		}else if(State.equals("win")){
			
		}else if(State.endsWith("lose")){
			
		}else{
			System.out.println("Invalid state!");
		}
	}
	
}
