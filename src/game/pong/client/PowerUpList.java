package game.pong.client;

import java.io.Serializable;
import java.util.ArrayList;

public class PowerUpList implements Serializable{
	public static class Powerup extends PowerUp implements Serializable{
		public Powerup(int x, int y, PowerUpType powerUp) {
			super(x, y, powerUp);
		}
	}
	
	static ArrayList<Powerup> PowerUpArray = new ArrayList<Powerup>();
	
	public static void addNewPowerUp(Powerup p){
			PowerUpArray.add(p);
	}
	public static void onUpdate(){
		for(int i = 0; i < PowerUpArray.size(); i++){
			if(PowerUpArray.get(i) != null){
				
				if(PowerUpArray.get(i).isActivated()){
					if(PowerUpArray.get(i).powerUpType() == PowerUpType.SpeedBoost){
						if(PowerUpArray.get(i).getActivatedBy() == 0){
							StateGame.player1.setHeight(StateGame.player1.getHeight() + 10);
							
							}else if(PowerUpArray.get(i).getActivatedBy() == 0){
							StateGame.player2.setHeight(StateGame.player2.getHeight() + 10);
						}
					}
					PowerUpArray.remove(i);
				}
				
				
			}
		}
	}
	public static void draw(){
		for(int i = 0; i < PowerUpArray.size(); i++){
			if(PowerUpArray.get(i) != null){
				PowerUpArray.get(i).draw();
			}
		}
	}
}
