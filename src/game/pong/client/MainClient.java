package game.pong.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
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
import static org.lwjgl.opengl.GL11.glColor3f;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import static org.lwjgl.opengl.GL11.GL_QUADS;
public class MainClient {

	static boolean applet = false;
	static boolean isPaused = false;
	
	//Checking which player pa
	static boolean isPlayer1Paused = false;
	static boolean isPlayer2Paused = false;
	
	//String set to empty for the starter, because it's going to be 
	//drawn on screen the whole time, if it's blank, then nothing will be there :)
	static String whoPausedTheGame = "";
	static int playerNum = 0;
	
	static int score0 = 0;
	static int score1 = 0;
	
	static int x = 0;
	static int y;
	static int p2X;
	static int p2Y;
	
	static int x2 = 0;
	static int y2 = 0;
	static int x3 = 0;
	static int y3 = 0;
	static int x4 = 0;
	static int y4 = 0;
	
	static Ball ball = new Ball();
	static int ballX;
	static int ballY;
	
	public static ServerSocket server;
	
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
		p2X = (Display.getWidth() - 20);
		
		ballX = Display.getHeight()/2;
		ballY = Display.getHeight()/2;
		
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		String isServer = (String) JOptionPane.showInputDialog(null, "Is server? (yes/no): ", "Do it", JOptionPane.INFORMATION_MESSAGE);
		if(isServer.toLowerCase().equals("yes")){
			playerNum = 0;
			
			try {
				
				ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
				server = new ServerSocket(port, 0, InetAddress.getLocalHost());
				new Thread(accept).start();
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}else{
			playerNum = 1;
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
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		ObjectInputStream ois;
		try {
				ois = new ObjectInputStream(socket.getInputStream());
				JOptionPane.showMessageDialog(null, (String) ois.readObject());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		new Thread(receive).start();
		new Thread(send).start();
		}
		while(!Display.isCloseRequested())
		{
			ball.x = ballX;
			ball.y = ballY;
			glClear(GL_COLOR_BUFFER_BIT);
			
			/* Controls */
			//This should have been in the while loop, so ESCAPE isn't registered
			//more than one time at a time, so it's like a toggle switch
			while(Keyboard.next())
			{
				if(Keyboard.getEventKeyState())
				{
					if(Keyboard.isKeyDown(Keyboard.KEY_P))
					{
						if(playerNum == 0){
							if(isPlayer1Paused == false){
								isPlayer1Paused = true;
							}else if(isPlayer1Paused == true){
								isPlayer1Paused = false;
							}
						}
						if(playerNum == 1){
							if(isPlayer2Paused == false){
								isPlayer2Paused = true;
							}else if(isPlayer2Paused == true){
								isPlayer2Paused = false;
							}
						}
					}
				}
			}
			//Changed from == false because I think it seems nicer like this. Don't know why
			
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddle
			{
				if(y > 0)
				{
					if(playerNum == 0 && !isPlayer1Paused){
						y -= 5;
					}else if(playerNum == 1 && !isPlayer2Paused){
						p2Y -= 5;
					}
					
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if(y + 60 < Display.getHeight())
				{
					if(playerNum == 0 && !isPlayer1Paused){
						y += 5;
					}else if(playerNum == 1 && !isPlayer2Paused){
						p2Y += 5;
					}
				}
			}
			
			/*Text at the top of the screen*/
			Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 1);
			Fonts.drawString(score0 + "", (int)(Display.getWidth()*.25)-40, 20, 1);
			Fonts.drawString(score1 + "", (int)(Display.getWidth()*.75)-40, 20, 1);
			
			//Drawing the Who paused the game text :p
			//Setting text color to Cyan
			glColor3f(0, 1, 1);
			Fonts.drawString(whoPausedTheGame, 0, Display.getHeight() / 2,  5);
			//Setting color back to white
			glColor3f(1, 1, 1);
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
			glBegin(GL_QUADS);//ballY thingy
				glVertex2i(ballX, ballY); //1
				glVertex2i(ballX + 20, ballY); //2
				glVertex2i(ballX + 20, ballY + 20); //3
				glVertex2i(ballX, ballY + 20); //4
			glEnd();
			//Here we draw the text on who paused the screen! :p
			if(isPaused){
				if(playerNum == 0){
					if(isPlayer1Paused || isPlayer2Paused){
						if(isPlayer1Paused){
							whoPausedTheGame = "Player one paused the game";
						}else if(isPlayer2Paused){
							whoPausedTheGame = "Player two paused the game";
						}
					}
				}else if(playerNum == 1){
					if(!isPlayer2Paused){
						whoPausedTheGame = "Player one paused the game!";
					}else if(isPlayer2Paused){
						whoPausedTheGame = "Player two paused the game!";
					}
				}
			}else{
				//Setting the variable to blank so no text is rendered
				whoPausedTheGame = "";
			}
			Display.sync(60);
			Display.update();
			
		}
	}
	
	public static void main(String []args)
	{
		new MainClient();
	}
	private static Runnable accept = new Runnable()
	{
		public void run()
		{
			try
			{
				socket = server.accept();
				
				ObjectOutputStream oos;
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject("Welcome to the server!");
				
				new Thread(send).start();
				new Thread(receive).start();
				new Thread(onUpdate).start();
			}
			catch(Exception e)
			{
			
			}
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
					
					if(playerNum == 0){
						ois = new ObjectInputStream(socket.getInputStream());
						p2Y = (Integer) ois.readObject();
						ois = new ObjectInputStream(socket.getInputStream());
						isPlayer2Paused = (Boolean) ois.readObject();
					}
					if(playerNum == 1){
						ois = new ObjectInputStream(socket.getInputStream());
						y = (Integer) ois.readObject();
						ois = new ObjectInputStream(socket.getInputStream());
						Ball b = (Ball) ois.readObject();
						ballX = b.x;
						ballY = b.y;
						
						ois = new ObjectInputStream(socket.getInputStream());
						score0 = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						score1 = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						isPaused = (Boolean) ois.readObject();
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
						oos = new ObjectOutputStream(socket.getOutputStream());
						if(playerNum == 0){
							oos.writeObject(y);
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(ball);
							
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(score0);
							
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(score1);
							
							//Sending player 2 if game is paused, only sending one
							//variable and not two because if the game is paused
							//and player one didn't pause it, then obviously it was
							//player two that paused it.
							//I did it like this because otherwise it was throwing errors
							//and I didn't like that! :)
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(isPaused);
						}
						if(playerNum == 1){
							oos.writeObject(p2Y);
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeObject(isPlayer2Paused);
						}
						Thread.sleep(20);
					}catch(Exception e){}
			}
		}
	};
	private static Runnable onUpdate = new Runnable(){
		public void run() 
		{
			while(true){
				
				/*
				 * Here I'm gonig to check if either one of the players are paused
				 * naturally, if they are the global isPaused variable is going
				 * to be set. this will just stop the ball and will not stop the
				 * players from moving
				 * 
				 * The variables isPlayer1Paused and isPlayer2Paused are going to both
				 * act as checks for the main isPaused variable and for the movement 
				 * keys, regarding to which playerNum you have been assigned :)
				 */
				if(isPlayer1Paused || isPlayer2Paused){
					isPaused = true;
				}else{
					isPaused = false;
				}
					if(true){
						if(!isPaused){
							if(ball.dX > 0){
								ball.dX += 0.2;
							}else{
								ball.dX -= 0.2;
							}
							ballX -= ball.dX;
							ballY -= ball.dY;
						}
						int bX = ballX + 10;
						int bY = ballY + 10;
						
						//Checking collision for the two player paddles if the
						//ball is colliding with the paddle
						boolean one = bX >= x && bX <= x + 20 && bY >= y && bY <= y + 60;
						boolean two = bX >= p2X && bX <= p2X + 20 && bY >= p2Y && bY <= p2Y + 60;
						if(one){
							ball.dX = -ball.dX;
							//Calculating where the ball will go after being hit off
							//the paddle, same as in brick breaker
							ball.dY = ((y + (20 / 2)) - (bY + 10)) / 10;
						}
						if(two){
							ball.dX = -ball.dX;
							//Calculating where the ball will go after being hit off
							//the paddle, same as in brick breaker
							ball.dY = ((p2Y + (20 / 2)) - (bY + 10)) / 10;
						}
						if(ballX > Display.getWidth()){
							score0 ++;
							ball.dX = 5;
							ball.dY = 0;
							ballX = Display.getWidth() / 2;
							ballY = Display.getHeight() / 2;
						}
						if(ballX < 0){
							score1 ++;
							ball.dX = 5;
							ball.dY = 0;
							ballX = Display.getWidth() / 2;
							ballY = Display.getHeight() / 2;
						}
						if(ballY < 0){
							ballY = 1;
							ball.dY = -ball.dY;
						}
						if(ballY > Display.getHeight()){
							ballY = Display.getHeight() - 1;
							ball.dY = -ball.dY;
						}
							/*
							 * That's actually a good idea, how about a count down
							 * like when the ball is reset then it will spawn back in
							 * like 2 seconds or something? or the player who scored
							 * gets to start it?
							 * -Rob
							 * 
							 * 
							 * IT'S A VERY GOOD IDEA!!!!
							 * DO THAT!!
							 * -Tim
							 */
							//TODO ^^^^^^^
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			
		}
	};
}
