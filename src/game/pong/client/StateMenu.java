package game.pong.client;

import org.newdawn.slick.Color;

public class StateMenu {
	
	public static void onSetup(){
		Gui.addNewButton(new Gui.button(60, 60, 70, 20, "menu", "Start", Textures.shadow, Textures.textBackground, Color.white));
		Gui.addNewButton(new Gui.button(170, 60, 70, 20, "menu", "StartAI", Textures.player1, Textures.textBackground, Color.red));
		Gui.addNewTextBox(new Gui.text("Play with AI!", 50, 50, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend!", 150, 50, 1, "menu", "Start", Color.white));
		Gui.addNewButton(new Gui.button(260, 60, 70, 20, "menu", "ConnectServer", Textures.player2, Textures.player1, Color.cyan));
		Gui.addNewTextField(new Gui.textField(100, 120, 100, 30, "ip", Color.blue, "menu", 20));
	}
	
	public static void onUpdate(){
	GuiButton StartButton = Gui.buttonArray[Gui.getButtonByName("Start")];
	GuiButton StartOfflineButton = Gui.buttonArray[Gui.getButtonByName("StartAI")];
	GuiButton ConnectServer = Gui.buttonArray[Gui.getButtonByName("ConnectServer")];
	GuiTextField ipInputBox = Gui.textFieldArray[Gui.getTextFieldByName("ip")];
	
		if(StartButton.isPressed()){
			StateManager.changeState("game");
			StateGame.isSinglePlayer = true;
		}
		if(StartOfflineButton.isPressed()){
			StateGame.isTwoPlayerOfflineMode = false;
			StateGame.isSinglePlayer = false;
			StateGame.playerNum = 0;
			StateManager.changeState("game");
		}
		if(ConnectServer.isPressed() && ipInputBox.getText().length() >= 5){
			StateGame.isConnectingToServer = true;
			StateGame.isTwoPlayerOfflineMode = false;
			StateGame.isSinglePlayer = false;
			StateGame.playerNum = 1;
			StateGame.toIP = ipInputBox.getText();
			StateManager.changeState("game");
		}
		
	}
}
