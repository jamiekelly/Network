package game.pong.client;

import org.newdawn.slick.opengl.Texture;

public enum PowerUpType {
	
	SpeedIncrease(Textures.powerUpSpeedIncrease), PaddleSizeIncrease(Textures.powerUpSizeIncrease), BallSpeedIncrease(Textures.powerUpBallSpeedIncrease), BallSpeedDecrease(Textures.powerUpBallSpeedIncrease),
	SpeedDecreaseOther(Textures.powerUpSpeedDecrease), PaddleSizeDecreaseOther(Textures.powerUpSizeDecrease), StunOther(Textures.powerUpPaddleFreeze);

	public final Texture tex;
	PowerUpType(Texture tex){
		this.tex = tex;
	}
	
	public static void doEffect(PowerUpType type, int whoHitLast){
		if(type == SpeedIncrease){
			if(whoHitLast == 0){
				if(StateGame.p1SpeedMultiplier < 2.5) StateGame.p1SpeedMultiplier += 0.1;
			}else{
				if(StateGame.p2SpeedMultiplier < 2.5) StateGame.p2SpeedMultiplier += 0.1;
			}
		}
		if(type == PaddleSizeIncrease){
			if(whoHitLast == 0){
				if(StateGame.player1.getHeight() <= 300) StateGame.player1.setHeight(StateGame.player1.getHeight() + 10);
			}else{
				if(StateGame.player2.getHeight() <= 300) StateGame.player2.setHeight(StateGame.player2.getHeight() + 10);
			}
		}
		if(type == BallSpeedIncrease){
			StateGame.ball.setdX(StateGame.ball.getdX() + 1);
		}
		if(type == BallSpeedDecrease){
			StateGame.ball.setdX(StateGame.ball.getdX() - 1);
		}
		if(type == SpeedDecreaseOther){
			if(whoHitLast == 1){
				if(StateGame.p1SpeedMultiplier > 0.3) StateGame.p1SpeedMultiplier -= 0.1;
			}else{
				if(StateGame.p2SpeedMultiplier > 0.3) StateGame.p2SpeedMultiplier -= 0.1;
			}
		}
		if(type == PaddleSizeDecreaseOther){
			if(whoHitLast == 1){
				if(StateGame.player1.getHeight() > 10) StateGame.player1.setHeight(StateGame.player1.getHeight() - 10);
			}else{
				if(StateGame.player2.getHeight() > 10) StateGame.player2.setHeight(StateGame.player2.getHeight() - 10);
			}
		}
		if(type == StunOther){
			if(whoHitLast == 1){
				Input.isP1Stunned = true;
			}else{
				Input.isP2Stunned = true;
			}
		}
	}
}
