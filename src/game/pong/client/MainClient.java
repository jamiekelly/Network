package game.pong.client;

import game.pong.Server.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;


import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glBlendFunc;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import static org.lwjgl.opengl.GL11.GL_QUADS;
public class MainClient {
	/*
	//TODO we need to make sure the second player get's IDed as player 1
	 * after that we need to make sure the second player controls ONLY the left paddle
	 * I just want to raise these concerns if you haven't already thought about them
	 * We should concentrate on making the server serve the ball once two clients have joined.
	 * */
	static boolean applet = false;
	
	static int playerNum = 0;
	
	static int score0 = 0;
	static int score1 = 0;
	static int score(int num){
		if(num == 0){
			return score0;
		}else{
			return score1;
		}
	}
	
	static int x = 0;
	static int y;
	//location of Y last tick
	static int lY;
	static int p2X = 0;
	static int p2Y;
	//location of Y last tick
	static int l2Y;
	
	static int ballx;
	static int bally;
	
	//TODO In applet, load parameters from here
	static int port = 7777;
	static String ip = "82.71.22.183";
	public static Socket socket;

	public MainClient()
	{
		if(!applet)
		{
			try
			{
			
				Display.setDisplayMode(new DisplayMode(600, 400));
				Display.setTitle("Two player pong! Over the Internet!");//Isn't
				Display.create();//isn't
				Display.setResizable(false);//isn't
			
			}catch(LWJGLException e)
			{
				e.printStackTrace();
			}
		}
		
		/*The 30 will need to be changed if paddle heights are modified*/
		
		y = (Display.getHeight()/2) - 30; 
		lY = y;
		p2Y = (Display.getHeight()/2) - 30;
		l2Y = p2Y;
		
		ballx = Display.getHeight()/2;
		bally = Display.getHeight()/2;
		
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		String local;
		
		try
		{
			local = InetAddress.getLocalHost().getHostAddress() + ":" + port;
		}
		catch (UnknownHostException ex)
		{
			local = "Network Error";
		}
		
		ip = (String) JOptionPane.showInputDialog(null, "IP: ", "Info", JOptionPane.INFORMATION_MESSAGE, null, null, local);
		
		port = Integer.parseInt(ip.substring(ip.indexOf(":") + 1));
		ip = ip.substring(0, ip.indexOf(":"));
		
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			playerNum = (Integer) ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			new Thread(receive).start();
			new Thread(send).start();
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			/* Controls */
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddle
			{
				if(y > 0)
				{
					if(playerNum == 0){
						y -= 5;
					}else{
						p2Y -= 5;
					}
					//TODO Update server about location
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(y + 60 < Display.getHeight())
				{
					if(playerNum == 0){
						y += 5;
					}else{
						p2Y += 5;
					}
					//TODO Update server about location
				}
			}
			
			
			/*Text at the top of the screen*/
			Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 1);
			Fonts.drawString(score0 + "", (int)(Display.getWidth()*.25)-40, 20, 1);
			Fonts.drawString(score1 + "", (int)(Display.getWidth()*.75)-40, 20, 1);
			
			/* <addition space> */
			
			
			
			/*
			 * 1.......2
			 * .       .
			 * .       .
			 * .       .
			 * 4.......3
			 */
			glBegin(GL_QUADS);  // Player 0 paddle, Left paddle
				glVertex2i(x , y);	//1
				glVertex2i(x + 20 , y);	//2
				glVertex2i(x + 20, y + 60);	//3
				glVertex2i(x , y + 60);	//4
			glEnd();
			
			glBegin(GL_QUADS);  // Player 1 paddle, Right paddle
				glVertex2i(Display.getWidth() - 20, p2Y); //1
				glVertex2i(Display.getWidth(), p2Y); //2
				glVertex2i(Display.getWidth(), p2Y + 60); //3
				glVertex2i(Display.getWidth() - 20, p2Y + 60); //4
			glEnd();
			
			//Ball
			glBegin(GL_QUADS);//Bally thingy
				glVertex2i(ballx, bally); //1
				glVertex2i(ballx + 20, bally); //2
				glVertex2i(ballx + 20, bally + 20); //3
				glVertex2i(ballx, bally + 20); //4
			glEnd();
			Display.sync(60);
			Display.update();
			
		}
	}
	
	public static void main(String []args)
	{
		new MainClient();
	}
	private static Runnable receive = new Runnable()
	{
		public void run()
		{
			ObjectInputStream ois;
			while(true)
			{
				try
				{
					//Receiving the ball class
					ois = new ObjectInputStream(socket.getInputStream());
					Ball b = (Ball) ois.readObject();
					
					ballx = b.x;
					bally = b.y;
					
					ois = new ObjectInputStream(socket.getInputStream());
					Player p = (Player) ois.readObject();	
					
					if(p.playerNum == 1 && playerNum == 0)
					{
						p2X = p.x;
						p2Y = p.y;
						score1 = p.score;
						System.out.println("Y: " + p.y + " ID: " + p.playerNum);
					}
					if(p.playerNum == 0 && playerNum == 1)
					{
						x = p.x;
						y = p.y;
						score0 = p.score;
						System.out.println("22222 Y: " + p.y + " ID: " + p.playerNum);
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
		while(true){
					try
					{
						
						if(playerNum == 0)
						{
							if(true)
							{
								Player p = new Player();
								p.x = x;
								p.y = y;
								p.playerNum = playerNum;
								oos = new ObjectOutputStream(socket.getOutputStream());
								oos.writeObject(p);
							}
						}
						else if(playerNum == 1)
						{
							if(true)
							{
								Player p1 = new Player();
								p1.x = p2X;
								p1.y = p2Y;
								p1.playerNum = playerNum;
								
								oos = new ObjectOutputStream(socket.getOutputStream());
								oos.writeObject(p1);
							}
						}
						
						lY = y;
						l2Y = p2Y;
						Thread.sleep(100);
					}catch(Exception e){}
			}
		}
	};
}
