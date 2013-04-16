package game.pong.client;

public class PowerUp {
	private int x, y;
	private int w, h;
	private boolean isActivated = false;
	private int activatedBy;
	private int number;
	private PowerUpType powerUp;
	public PowerUp(int x, int y, PowerUpType powerUp){
		this.x = x;
		this.y = y;
		this.powerUp = powerUp;
		
	}
	public void setNumber(int number){
		this.number = number;
	}
	public boolean isActivated(){
		double bX = StateGame.ball.getX();
		double bY = StateGame.ball.getY();
		int bW = StateGame.ball.getWAndH();
		int bH = StateGame.ball.getWAndH();
		if(x + w / 2 >= bX && x + w / 2 >= bX + bW && y + h / 2 >= bY && y + h / 2 <= bY + bH){
			isActivated = true;
			return true;
		}
		return false;
	}
	public void onUpdate(){
		draw();
		if(isActivated){
			if(powerUp == PowerUpType.SpeedBoost){
				if(activatedBy == 0){
					StateGame.p1Speed += StateGame.p1Speed + 1;
				}else if(activatedBy == 1){
					StateGame.p2Speed += StateGame.p2Speed + 1;
				}
			}
		}
	}
	public void draw(){
		
	}
}
