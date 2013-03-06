package game.pong.client;

import org.newdawn.slick.Color;

public class StateMenu {
	
	public static void onSetup(){
		Gui.addNewButton(new Gui.button(60, 60, 70, 20, "menu", "Start", Textures.shadow, Textures.textBackground, Color.white));
		Gui.addNewButton(new Gui.button(170, 60, 70, 20, "menu", "StartAI", Textures.player1, Textures.textBackground, Color.red));
		Gui.addNewTextBox(new Gui.text("Play with AI!", 50, 50, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend!", 150, 50, 1, "menu", "Start", Color.white));
	}
	
	public static void onUpdate(){
		GuiButton StartButton = Gui.buttonArray[Gui.getButtonByName("Start")];
		GuiButton StartOfflineButton = Gui.buttonArray[Gui.getButtonByName("StartAI")];
		if(StartButton.isPressed()){
			StateManager.changeState("game");
		}
		if(StartOfflineButton.isPressed()){
			StateGame.isTwoPlayerOfflineMode = true;
			StateManager.changeState("game");
		}
	}
}
