package game.pong.client;

import org.newdawn.slick.Color;

public class StateMenu {
	
	public static void onSetup(){
		Gui.addNewButton(new Gui.button(60, 60, 70, 20, "menu", "Start", Textures.shadow, Textures.textBackground, Color.white));
	}
	
	public static void onUpdate(){
		GuiButton StartButton = Gui.buttonArray[Gui.getButtonByName("Start")];
		if(StartButton.isPressed()){
			StateManager.changeState("game");
		}
	}
}
