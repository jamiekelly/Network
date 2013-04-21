package game.pong.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

public class PowerUpList implements Serializable{
	public static class Powerup extends PowerUp implements Serializable{
		public Powerup(int x, int y, PowerUpType powerUp) {
			super(x, y, powerUp);
		}
	}
	
	static ArrayList<Powerup> PowerUpArray = new ArrayList<Powerup>();
	
	public static void addNewPowerUp(PowerUpType type){
		Random random = new Random();
		int ranX = 50 + random.nextInt(Display.getWidth() - 100);
		int ranY = 60 + random.nextInt(Display.getHeight() - 80);
			PowerUpArray.add(new Powerup(ranX, ranY, type));
	}
	public static void addNewRandomPowerUp(){
		Random random = new Random();
		int randomPowerUp = random.nextInt(7);
		int ranX = 50 + random.nextInt(Display.getWidth() - 100);
		int ranY = 60 + random.nextInt(Display.getHeight() - 80);
		if(randomPowerUp == 0){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.SpeedIncrease));
		}else if(randomPowerUp == 1){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.PaddleSizeIncrease));
		}else if(randomPowerUp == 2){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.BallSpeedIncrease));
		}else if(randomPowerUp == 3){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.BallSpeedDecrease));
		}else if(randomPowerUp == 4){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.SpeedDecreaseOther));
		}else if(randomPowerUp == 5){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.PaddleSizeDecreaseOther));
		}else if(randomPowerUp == 6){
			PowerUpArray.add(new Powerup(ranX, ranY, PowerUpType.StunOther));
		}
	}
	public static void onUpdate(){
		for(int i = 0; i < PowerUpArray.size(); i++){
			if(PowerUpArray.get(i) != null){
				if(PowerUpArray.get(i).isActivated()){
					/*
					 * Here I managed to save a lot of time by doing this method in PowerUpType class
					 * I simply have to call it and say the type and who activated it and everything else is done
					 * automatically, no need for millions of if statements to check who did what
					 */
					PowerUpType.doEffect(PowerUpArray.get(i).powerUpType(), PowerUpArray.get(i).getActivatedBy());
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
