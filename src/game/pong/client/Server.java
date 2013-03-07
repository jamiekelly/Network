package game.pong.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	public static ServerSocket server;
	
	static Socket socket;
	
	
	public static void startServer(String startIP){
		if(StateGame.isSinglePlayer || StateGame.isTwoPlayerOfflineMode)
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
			while(!MainClient.endGame == true)
			{
				StateGame.ball.onUpdate(StateGame.ball);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
}