package game.pong.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

public class Server {
	
	public static ServerSocket server;
	
	static Socket socket;
	static boolean isSinglePlayer = true;
	
	
	static int difficulty = 0;
	
	
	public static void startServer(String startIP){
		if(isSinglePlayer)
		{
			new Thread(onUpdate).start();
		}else{
			try{
				server = new ServerSocket(7777, 0, InetAddress.getLocalHost());
			}catch(Exception e){}
		}
	}
	
	public static void connectToServer(String toIP){
		
		int port = Integer.parseInt(toIP.substring(toIP.indexOf(":") + 1));
		String ip = toIP.substring(0, toIP.indexOf(":"));
			
		try {
			socket = new Socket(ip, port);
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
			System.out.println("Connection Failed!");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Connection Failed!");
		}
		
		new Thread(receive).start();
		new Thread(send).start();
	}
	
	private static Runnable accept = new Runnable()
	{
		public void run()
		{
			try
			{
				socket = server.accept();
				
				new Thread(send).start();
				new Thread(receive).start();
				new Thread(onUpdate).start();
			}
			catch(Exception e){}
		}
	};
	
	private static Runnable receive = new Runnable()
	{
		public void run()
		{
			ObjectInputStream ois;
			while(true)
			{
				try
				{
					if(StateGame.playerNum == 0)
					{
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.p2Y = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.isPlayer2Paused = (Boolean) ois.readObject();
					}
					else if(StateGame.playerNum == 1)
					{	
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.P1Y = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						Ball b = (Ball) ois.readObject();
						
						StateGame.ball.setX(b.getX());
						StateGame.ball.setY(b.getY());
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.score0 = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.score1 = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.isPaused = (Boolean) ois.readObject();
						
					}
				Thread.sleep(10);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	private static Runnable send = new Runnable()
	{
		public void run()
		{
			//Get the previous location of x
			ObjectOutputStream oos;
			while(true)
			{
				try
				{
					oos = new ObjectOutputStream(socket.getOutputStream());
					if(StateGame.playerNum == 0){
						oos.writeObject(StateGame.P1Y);
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.ball);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.score0);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.score1);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.isPaused);
					}
					else if(StateGame.playerNum == 1){							
						oos.writeObject(StateGame.p2Y);
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.isPlayer2Paused);
					}
					Thread.sleep(20);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	private static Runnable onUpdate = new Runnable()
	{
		public void run() 
		{
			while(true)
			{
				if(StateGame.isPlayer1Paused || StateGame.isPlayer2Paused){
					StateGame.isPaused = true;
				}else{
					StateGame.isPaused = false;
				}
				if(!StateGame.isPaused && ((StateGame.score0 < 21) && (StateGame.score1 < 21)))
				{
					if(StateGame.ball.getdX() > 0){
						StateGame.ball.setdX((float) (StateGame.ball.getdX() + .002));
					}else{
						StateGame.ball.setdX((float) (StateGame.ball.getdX() - .002));
					}
					//This bit updates the movement of the ball. Without it the ball would
					//just be static and wouldn't move at all :3
					StateGame.ball.setX((int) (StateGame.ball.getX()-StateGame.ball.getdX()));
					StateGame.ball.setY((int) (StateGame.ball.getY()-StateGame.ball.getdY()));
				}
				
				//Checking collision for the two player paddles if the
				//ball is colliding with the paddle
				boolean hitPlayerOnesPaddle = StateGame.ball.getCenterOfBallX() >= StateGame.P1X && StateGame.ball.getCenterOfBallX() <= StateGame.P1X + 20 && StateGame.ball.getCenterOfBallY() >= StateGame.P1Y && StateGame.ball.getCenterOfBallY() <= StateGame.P1Y + 60;
				boolean hitPlayersTwosPaddle = StateGame.ball.getCenterOfBallX() >= StateGame.p2X && StateGame.ball.getCenterOfBallX() <= StateGame.p2X + 20 && StateGame.ball.getCenterOfBallY() >= StateGame.p2Y && StateGame.ball.getCenterOfBallY() <= StateGame.p2Y + 60;
				
				if(hitPlayerOnesPaddle)
				{
					//Calculating where the ball will go after being hit off
					//the paddle, same as in brick breaker
					StateGame.ball.setdX(-StateGame.ball.getdX());
					StateGame.ball.setdY((float) (((StateGame.P1Y+30)-StateGame.ball.getCenterOfBallY())/7.5));
				}
				if(hitPlayersTwosPaddle)
				{
					//Calculating where the ball will go after being hit off
					//the paddle, same as in brick breaker
					StateGame.ball.setdX(-StateGame.ball.getdX());
					StateGame.ball.setdY((float) (((StateGame.p2Y+30)-StateGame.ball.getCenterOfBallY())/7.5));
				}
				if(StateGame.ball.getCenterOfBallX() > Display.getWidth()) //Scored on RIGHT side of screen
				{
					StateGame.score0++;
					if(StateGame.score0 == 21){
						StateGame.ball.setX(Display.getWidth() / 2);
						StateGame.ball.setY((int) (Display.getHeight() * 0.25));
					}else{
						StateGame.ball.setX(Display.getWidth() / 2);
						StateGame.ball.setY(Display.getHeight() / 2);
					}
					StateGame.ball.setdX(5);
					StateGame.ball.setdY((float) (Math.random()*8-4));
					
				}
				if(StateGame.ball.getCenterOfBallX() < 0) //Scored on LEFT side of screen
				{
					StateGame.score1 ++;
					if(StateGame.score1 == 21){
						StateGame.ball.setX(Display.getWidth() / 2);
						StateGame.ball.setY((int) (Display.getHeight() * 0.25));
					}else{
						StateGame.ball.setX(Display.getWidth() / 2);
						StateGame.ball.setY(Display.getHeight() / 2);
					}
					StateGame.ball.setdX(-5);
					StateGame.ball.setdY((float) (Math.random()*8-4));
				}
				if(StateGame.ball.getY() < 0){ //Bounce off ceiling
					StateGame.ball.getY();
					StateGame.ball.setdY(-StateGame.ball.getdY());
				}
				if(StateGame.ball.getY() > Display.getHeight()) //Bounce off floor
				{
					StateGame.ball.setY(Display.getHeight() - 1);
					StateGame.ball.setdY(-StateGame.ball.getdY());
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
}