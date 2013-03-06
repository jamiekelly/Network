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
	static String State = "menu";
	
	static boolean isIntroSetUp = false;
	static boolean isLoadSetUp = false;
	static boolean isMenuSetUp = false;
	static boolean isSettingsSetUp = false;
	static boolean isGameSetUp = false;
	static boolean isWinSetUp = false;
	static boolean isLoseSetUp = false;
	
	public static void onUpdate(){
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
			StateGame.onUpdate();
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
	}
	public static void changeState(String toState){
		State = toState;
	}
	
}
