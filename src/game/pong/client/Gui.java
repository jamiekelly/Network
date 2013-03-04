package game.pong.client;

import org.newdawn.slick.Color;

public class Gui {
	
	public static int maxTextBoxes = 100;
	static GuiText[] textArray = new GuiText[maxTextBoxes];
	public static class text extends GuiText{
		public text(String text, double x, double y, double size, String state, String name, Color textColor) {
			super(text, x, y, size, state, name, textColor);
		}
	}
	public static void addNewTextBox(text t){
		for(int i = 0; i < maxTextBoxes; i++){
			if(textArray[i] == null){
				textArray[i] = t;
				System.out.println("I've been added to! o_O");
				break;
			}
		}
	}
	public static void onUpdate(){
		for(int i = 0; i < 100; i++){
			if(textArray[i] != null){
				textArray[i].drawBoxWithText();
				System.out.println("aaa");
			}
		}
	}
	public static int getTextByName(String name){
		for(int i = 0; i < textArray.length; i++){
			if(textArray[i] != null){
				if(textArray[i].getName().equals(name)){
					return i;
				}
			}
		}
		return -1;
	}
}
