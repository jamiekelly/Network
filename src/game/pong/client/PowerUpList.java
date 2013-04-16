package game.pong.client;

public class PowerUpList {
	public static class Powerup extends PowerUp{
		public Powerup(int x, int y, int number, PowerUpType powerUp) {
			super(x, y, powerUp);
		}
	}
	
	static PowerUp[] PowerUpArray = new PowerUp[20];
	
	public static void addNewPowerUp(Powerup p){
		for(int i = 0; i < PowerUpArray.length; i++){
			if(PowerUpArray[i] == null){
				PowerUpArray[i] = p;
				PowerUpArray[i].setNumber(i);
				break;
			}
		}
	}
	public static void onUpdate(){
		for(int i = 0; i < PowerUpArray.length; i++){
			if(PowerUpArray[i] != null){
				PowerUpArray[i].onUpdate();
			}
		}
	}
}
