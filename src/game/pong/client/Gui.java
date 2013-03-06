package game.pong.client;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Gui {
	
	public static int maxTextBoxes = 100;
	public static int maxButtons = 100;
	static GuiText[] textArray = new GuiText[maxTextBoxes];
	static GuiButton[] buttonArray = new GuiButton[maxButtons];
	public static class text extends GuiText{
		public text(String text, double x, double y, double size, String state, String name, Color textColor) {
			super(text, x, y, size, state, name, textColor);
		}
	}
	public static class button extends GuiButton{
		public button(double x, double y, int w, int h, String state,
				String name, Texture normal, Texture hoverOver, Color color) {
			super(x, y, w, h, state, name, normal, hoverOver, color);
		}
	}
	public static void addNewTextBox(text t){
		for(int i = 0; i < maxTextBoxes; i++){
			if(textArray[i] == null){
				textArray[i] = t;
				break;
			}
		}
	}
	public static void addNewButton(button b){
		for(int i = 0; i < maxButtons; i++){
			if(buttonArray[i] == null){
				buttonArray[i] = b;
				break;
			}
		}
	}
	public static void onUpdate(){
		for(int i = 0; i < 100; i++){
			if(textArray[i] != null){
				if(textArray[i].getState().toLowerCase().equals(StateManager.State)){
					textArray[i].drawBoxWithText();
				}
			}
			if(buttonArray[i] != null){
				if(buttonArray[i].getState().toLowerCase().equals(StateManager.State)){
					buttonArray[i].onUpdate();
				}
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
	public static int getButtonByName(String name){
		for(int i = 0; i < buttonArray.length; i++){
			if(buttonArray[i] != null){
				if(buttonArray[i].getName().equals(name)){
					return i;
				}
			}
		}
		return -1;
	}
}
