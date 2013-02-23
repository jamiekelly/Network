package game.pong.client;

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

import Client.DataPackage;

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
	 * 
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
	static int p2X = 0;
	static int p2Y;
	
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
		p2Y = (Display.getHeight()/2) - 30;
		
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
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			/* Controls */
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddle
			{
				if(y > 0)
				{
						y -= 5;
					//TODO Update server about location
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(y + 60 < Display.getHeight())
				{
					y += 5;
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
			glBegin(GL_QUADS);  // Player 0 paddle, Right paddle
				glVertex2i(x , y);	//1
				glVertex2i(x + 20 , y);	//2
				glVertex2i(x + 20, y + 60);	//3
				glVertex2i(x , y + 60);	//4
			glEnd();
			
			glBegin(GL_QUADS);  // Player 1 paddle, Left paddle
				glVertex2i(Display.getWidth() - 20, p2Y); //1
				glVertex2i(Display.getWidth(), p2Y); //2
				glVertex2i(Display.getWidth(), p2Y + 60); //3
				glVertex2i(Display.getWidth() - 20, p2Y + 60); //4
			glEnd();
			
			Display.sync(60);
			Display.update();
			
		}
	}
	
	public static void main(String []args)
	{
		new MainClient();
	}
	
	public static void send(){
		ObjectOutputStream oos;
					try
					{
						Player dp = new Player();
						dp.x = x;
						dp.y = y;
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(dp);
						Thread.sleep(100);
					}catch(Exception e){}
			
		
	}
}
