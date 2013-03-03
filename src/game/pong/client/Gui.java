package game.pong.client;

public class Gui {
	
	public static int maxTextBoxes = 100;
	static GuiText[] textArray = new GuiText[maxTextBoxes];
	public static class text extends GuiText{
		public text(String text, double x, double y, double size, String state) {
			super(text, x, y, size, state);
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
}
