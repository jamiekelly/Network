package game.pong.client;

import org.newdawn.slick.Color;

public class StateMenu {
	
	public static void onSetup(){
		
		Gui.addNewButton(new Gui.button(60, 60, 70, 20, "menu", "StartAI", Textures.shadow, Textures.textBackground, Color.white));
		Gui.addNewButton(new Gui.button(60, 140, 70, 20, "menu", "Start", Textures.player1, Textures.textBackground, Color.red));
		Gui.addNewButton(new Gui.button(170, 240, 70, 20, "menu", "ConnectServer", Textures.player2, Textures.player1, Color.cyan));
		Gui.addNewTextField(new Gui.textField(50, 240, 100, 30, "ip", Color.blue, "menu", 20));
		
		
		Gui.addNewTextBox(new Gui.text("Play with AI!", 50, 50, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend!", 60, 110, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Controls Up plus Down and W plus D", 60, 120, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend ONLINE!", 60, 200, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Enter IP and press the button!", 60, 210, 1, "menu", "Start", Color.white));
	}
	
	public static void onUpdate(){
	GuiButton StartButton = Gui.buttonArray[Gui.getButtonByName("StartAI")];
	GuiButton StartOfflineButton = Gui.buttonArray[Gui.getButtonByName("Start")];
	GuiButton ConnectServer = Gui.buttonArray[Gui.getButtonByName("ConnectServer")];
	GuiTextField ipInputBox = Gui.textFieldArray[Gui.getTextFieldByName("ip")];
	
		if(StartButton.isPressed()){
			StateManager.changeState("game");
			StateGame.isSinglePlayer = true;
		}
		if(StartOfflineButton.isPressed()){
			StateGame.isTwoPlayerOfflineMode = true;
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
