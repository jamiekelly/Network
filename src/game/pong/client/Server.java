package game.pong.client;

import game.pong.client.PowerUpList.Powerup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.lwjgl.opengl.Display;

public class Server {
	
	public static ServerSocket server;
	
	static Socket socket;
	static String ip = "192.168.0.7";
	
	static float chanceOfNewPowerUp = 0.01F;// Out of 100 (supposed to be %)
	
	public static void startServer(){
		if(StateGame.isSinglePlayer || StateGame.isTwoPlayerOfflineMode)
		{
			new Thread(onUpdate).start();
		}else{
			try{
				server = new ServerSocket(7777, 0, InetAddress.getLocalHost());
				new Thread(accept).start();
			}catch(Exception e){}
		}
	}
	
	public static void connectToServer(String toIP){
		
		int port = 7777;
		String ip = toIP;
			
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
	
	public static Runnable accept = new Runnable()
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
			while(!MainClient.endGame == true)
			{
				try
				{
					if(StateGame.playerNum == 0)
					{
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.p2Y = (Double) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.isPlayer2Paused = (Boolean) ois.readObject();
					}
					else if(StateGame.playerNum == 1)
					{	
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.P1Y = (Double) ois.readObject();
						
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
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.player2.setHeight((Integer) ois.readObject());
						
						ois = new ObjectInputStream(socket.getInputStream());
						StateGame.player1.setHeight((Integer) ois.readObject());
						
						ois = new ObjectInputStream(socket.getInputStream());
						PowerUpList.PowerUpArray = (ArrayList<Powerup>) ois.readObject();
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
			while(!MainClient.endGame == true)
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
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.player2.getHeight());
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(StateGame.player1.getHeight());
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(PowerUpList.PowerUpArray);
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
			StateGame.ball.predictAI(StateGame.ball);
			while(!MainClient.endGame == true)
			{
				StateGame.ball.onUpdate(StateGame.ball);
				PowerUpList.onUpdate();
				
				Random random = new Random();
				float ran = random.nextFloat();
				if(ran <= chanceOfNewPowerUp){
					PowerUpList.addNewRandomPowerUp();
				}
				
				//StateGame.ball.onUpdate(StateGame.ball2);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
}