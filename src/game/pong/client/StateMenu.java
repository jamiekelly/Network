package game.pong.client;

import org.newdawn.slick.Color;

public class StateMenu {
	
	
	static GuiButton StartButton;
	static GuiButton StartOfflineButton;
	static GuiButton ConnectServer;
	static GuiButton StartServer;
	static GuiButton easy;
	static GuiButton med;
	static GuiButton hard;
	static GuiTextField ipInputBox;
	
	public static void onSetup(){
		
		Gui.addNewButton(new Gui.button(60, 60, 70, 20, "menu", "StartAI", Textures.shadow, Textures.textBackground, Color.white));
		Gui.addNewButton(new Gui.button(60, 140, 70, 20, "menu", "Start", Textures.player1, Textures.textBackground, Color.red));
		Gui.addNewButton(new Gui.button(170, 240, 70, 20, "menu", "ConnectServer", Textures.player2, Textures.player1, Color.cyan));
		Gui.addNewButton(new Gui.button(160, 60, 70, 20, "menu", "Easy", Textures.shadow, Textures.textBackground, Color.green));
		Gui.addNewButton(new Gui.button(230, 60, 70, 20, "menu", "Medium", Textures.shadow, Textures.textBackground, Color.orange));
		Gui.addNewButton(new Gui.button(300, 60, 70, 20, "menu", "Hard", Textures.shadow, Textures.textBackground, Color.red));	
		Gui.addNewButton(new Gui.button(60, 340, 70, 20, "menu", "StartServer", Textures.shadow, Textures.textBackground, Color.pink));
		
		Gui.addNewTextField(new Gui.textField(50, 240, 100, 30, "ip", Color.blue, "menu", 20));
		
		
		Gui.addNewTextBox(new Gui.text("Easy", 160, 50, 1, "menu", "easy", Color.green));
		Gui.addNewTextBox(new Gui.text("Medium", 230, 50, 1, "menu", "medium", Color.orange));
		Gui.addNewTextBox(new Gui.text("Hard", 300, 50, 1, "menu", "hard", Color.red));
		Gui.addNewTextBox(new Gui.text("Play with AI!", 50, 50, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend!", 60, 110, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Controls Up plus Down and W plus D", 60, 120, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Play with a friend ONLINE!", 60, 200, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Enter IP and press the button!", 60, 210, 1, "menu", "Start", Color.white));
		Gui.addNewTextBox(new Gui.text("Start a server!", 60, 300, 1, "menu", "StartServerText", Color.white));
		
		StartButton = Gui.buttonArray[Gui.getButtonByName("StartAI")];
		StartOfflineButton = Gui.buttonArray[Gui.getButtonByName("Start")];
		ConnectServer = Gui.buttonArray[Gui.getButtonByName("ConnectServer")];
		easy = Gui.buttonArray[Gui.getButtonByName("Easy")];
		med = Gui.buttonArray[Gui.getButtonByName("Medium")];
		hard = Gui.buttonArray[Gui.getButtonByName("Hard")];
		ipInputBox = Gui.textFieldArray[Gui.getTextFieldByName("ip")];
		StartServer = Gui.buttonArray[Gui.getButtonByName("StartServer")];
	}
	
	public static void onUpdate(){
	
		if(StartButton.isPressed()){
			StateManager.changeState("game");
			StateGame.isSinglePlayer = true;
		}
		if(easy.isPressed()){
			StateGame.difficulty = 0;
		}
		if(med.isPressed()){
			StateGame.difficulty = 1;
		}
		if(hard.isPressed()){
			StateGame.difficulty = 2;
		}
		
		if(StateGame.difficulty == 0){
			easy.setNormalTexture(Textures.textBackground);
			med.setNormalTexture(Textures.shadow);
			hard.setNormalTexture(Textures.shadow);
		}else if(StateGame.difficulty == 1){
			easy.setNormalTexture(Textures.shadow);
			med.setNormalTexture(Textures.textBackground);
			hard.setNormalTexture(Textures.shadow);
		}else if(StateGame.difficulty == 2){
			easy.setNormalTexture(Textures.shadow);
			med.setNormalTexture(Textures.shadow);
			hard.setNormalTexture(Textures.textBackground);
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
		if(StartServer.isPressed()){
			StateGame.isConnectingToServer = false;
			StateGame.isTwoPlayerOfflineMode = false;
			StateGame.isSinglePlayer = false;
			StateGame.playerNum = 0;
			StateManager.changeState("game");
		}
		
	}
}
