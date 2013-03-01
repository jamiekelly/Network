package game.pong.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.ArrayList;

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
	private static boolean isPaused = false;
	
	private static boolean isPlayer1Paused = false;
	private static boolean isPlayer2Paused = false;
	
	private static int isSinglePlayer;
	private static int difficulty;
	
	private static String whoPausedTheGame = "";
	private static int playerNum;
	
	private static int score0 = 0;
	private static int score1 = 0;
	
	private static int P1X = 0;
	private static int P1Y;
	private static int p2X;
	private static int p2Y;
	
	static Ball ball = new Ball(0,0);
	
	public static ServerSocket server;
	
	//TODO In applet, load parameters from here
	private static int port = 7777;
	private static String ip = "82.71.22.183";
	private static Socket socket;

	
	public static void main(String []args)
	{
		new MainClient();
	}

	private MainClient()
	{
		if(!applet)
		{
			try
			{
				Display.setDisplayMode(new DisplayMode(600, 400));
				Display.setTitle("PONG!");
				Display.create();
				Display.setResizable(false);
			
			}
			catch(LWJGLException e){e.printStackTrace();}
		}
		
		/*The 30 will need to be changed if paddle heights are modified*/
		P1Y = (Display.getHeight()/2) - 30; 
		p2Y = (Display.getHeight()/2) - 30;
		p2X = (Display.getWidth() - 20);
		ball.setX(Display.getWidth()/2);
		ball.setY(Display.getHeight()/2);
		BallFollower.createFollowers();
		
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 600, 400, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		String options[] = {"Yes","No"};
		
		isSinglePlayer = (Integer) JOptionPane.showOptionDialog(null, "Do you want to play single Player?", 
																		"Single Player?",
																		JOptionPane.DEFAULT_OPTION, 
																		JOptionPane.INFORMATION_MESSAGE,
																		null, options, options[0]);
		if(isSinglePlayer == 0)
		{
			playerNum = 0;
			String difficulties[] = {"Easy", "Medium", "Hard!", "IMPOSSIBLE! (Seriously!)"};
			difficulty = (Integer) JOptionPane.showOptionDialog(null, "What difficulty would you like to play at?", 
																"difficulty?",
																JOptionPane.DEFAULT_OPTION, 
																JOptionPane.INFORMATION_MESSAGE,
																null, difficulties, difficulties[1]);
			
			new Thread(onUpdate).start();
		}
		else
		{
			int isServer = (Integer) JOptionPane.showOptionDialog(null, "Will you host the server?", 
																	"Host the server?", 
																	JOptionPane.DEFAULT_OPTION, 
																	JOptionPane.INFORMATION_MESSAGE,
																	null, options, options[0]);
			if(isServer == 0)
			{
				playerNum = 0;
				try 
				{
					ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
					server = new ServerSocket(port, 0, InetAddress.getLocalHost());
					new Thread(accept).start();
				}
				catch (UnknownHostException e) {e.printStackTrace();}
				catch (IOException e) {e.printStackTrace();}
			}
			else
			{
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
				
				ObjectInputStream ois;
				try {
						ois = new ObjectInputStream(socket.getInputStream());
						JOptionPane.showMessageDialog(null, (String) ois.readObject());
				} 
				catch (Exception e) {e.printStackTrace();}
				new Thread(receive).start();
				new Thread(send).start();
			}
		}
		
		//int test = 0; TODO commented code to get rid of warning
		/*THE START OF THE GAME SCREEN*/
		while(!Display.isCloseRequested())
		{
			ball.setX(ball.getX());
			ball.setY(ball.getY());
			glClear(GL_COLOR_BUFFER_BIT);
			
			/* Controls */
			while(Keyboard.next())
			{
				if(Keyboard.getEventKeyState())
				{
					if(Keyboard.isKeyDown(Keyboard.KEY_P))
					{
						if(playerNum == 0)
						{
							if(isPlayer1Paused == false){
								isPlayer1Paused = true;
							}
							else if(isPlayer1Paused == true){
								isPlayer1Paused = false;
							}
						}
						if(playerNum == 1)
						{
							if(isPlayer2Paused == false){
								isPlayer2Paused = true;
							}
							else if(isPlayer2Paused == true){
								isPlayer2Paused = false;
							}  //Changed from == false because I think it seems nicer like this. Don't know why
						}
					}
				}
			}
			
			if(!isPlayer1Paused && !isPlayer2Paused)
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_UP)) //Player paddles
				{
					if(P1Y > 0)
					{
						if(playerNum == 0 && !isPlayer1Paused){
							P1Y -= 5;
						}else if(playerNum == 1 && !isPlayer2Paused){
							p2Y -= 5;
						}
					}
				}
				if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				{
					if(P1Y + 60 < Display.getHeight())
					{
						if(playerNum == 0 && !isPlayer1Paused){
							P1Y += 5;
						}else if(playerNum == 1 && !isPlayer2Paused){
							p2Y += 5;
						}
					}
				}
			}
			
			/*AI MOVEMENT*/
			
			/*Difficultly Chart is as follows
			 * Easy = 0
			 * Medium = 1
			 * Hard = 2
			 * Impossible = 3
			 * */
			if(!isPlayer1Paused)
			{
				if(isSinglePlayer == 0)
				{
					if(p2Y > 0)
					{
						if(ball.getY() < p2Y + 30)
						{
							if(difficulty == 0){
								p2Y -= 1.5;
							}
							if(difficulty == 1){
								p2Y -= 2.5;
							}
							if(difficulty == 2){
								p2Y -= 4;
							}
							if(difficulty == 3){
								p2Y = ball.getY() - 30;
							}
						}
					}
					if(p2Y + 60 < Display.getHeight())
					{
						if(ball.getY() > p2Y + 30)
						{
							if(difficulty == 0){
								p2Y += 1.5;
							}
							if(difficulty == 1){
								p2Y += 2.8;
							}
							if(difficulty == 2){
								p2Y += 4;
							}
							if(difficulty == 3){
								p2Y = ball.getY() - 30;
							}
						}
					}
				}
			}
			
			/*Text at the top of the screen*/
			Fonts.drawString("Pong",(Display.getWidth()/2)-50,20, 1);
			Fonts.drawString(score0 + "", (int)(Display.getWidth()*.25)-40, 20, 1);
			Fonts.drawString(score1 + "", (int)(Display.getWidth()*.75)-40, 20, 1);
			
			//Drawing the Who paused the game text :p
			Fonts.drawString(whoPausedTheGame, Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5);
			
			/*Text if someone won the game!*/
			String playerWon[] = {"Player One won the game!", "PLayer Two won the game!"};
			
			if(score0!=21){
				Fonts.drawString("", Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5);
			}
			else if(score1!=21){
				Fonts.drawString("", Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5);
			}
			
			if(score0>=21)
			{
				Fonts.drawString(playerWon[0], Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5);
			}
			else if(score1>=21)
			{
				Fonts.drawString(playerWon[1], Display.getWidth()/2-120, (Display.getHeight() / 2)-30,  5);
			}
			
			//Setting color after to white
			glColor3f(1, 1, 1);
			/*
			 * 1.......2
			 * .       .
			 * .       .
			 * .       .
			 * 4.......3
			 */
			
			glBegin(GL_QUADS);  // Player 0 paddle, Left paddle
				glVertex2i(P1X , P1Y);	//1
				glVertex2i(P1X + 20 , P1Y);	//2
				glVertex2i(P1X + 20, P1Y + 60);	//3
				glVertex2i(P1X , P1Y + 60);	//4
			glEnd();
			
			glBegin(GL_QUADS);  // Player 1 paddle, Right paddle
				glVertex2i(Display.getWidth() - 20, p2Y); //1
				glVertex2i(Display.getWidth(), p2Y); //2
				glVertex2i(Display.getWidth(), p2Y + 60); //3
				glVertex2i(Display.getWidth() - 20, p2Y + 60); //4
			glEnd();
			//This has to be upside down otherwise they would all be in the same location
			//So every tick the location is updated
			//Finally draws all of the balls following  :p
			glColor3f(1, 0, 0);
			BallFollower.onUpdate();
			glColor3f(1, 1, 1);
			//Ball
			glBegin(GL_QUADS);//ball.getY() thingy
				glVertex2i(ball.getX(), ball.getY()); //1
				glVertex2i(ball.getX() + 20, ball.getY()); //2
				glVertex2i(ball.getX() + 20, ball.getY() + 20); //3
				glVertex2i(ball.getX(), ball.getY() + 20); //4
			glEnd();
			
			//Here we draw the text on who paused the screen! :p
			if(isPaused)
			{
				if(playerNum == 0)
				{
					if(isPlayer1Paused && isPlayer2Paused)
					{
						whoPausedTheGame = "Both players paused the game";
					}
					else if(isPlayer1Paused || isPlayer2Paused)
					{
						if(isPlayer1Paused){
							whoPausedTheGame = "Player one paused the game";
						}
						else if(isPlayer2Paused){
							whoPausedTheGame = "Player two paused the game";
						}
					}
				}
				else if(playerNum == 1)
				{
					if(isPlayer1Paused && isPlayer2Paused){
						whoPausedTheGame = "Both players paused the game";
					}
					else if(!isPlayer2Paused){
						whoPausedTheGame = "Player one paused the game!";
					}
					else if(isPlayer2Paused){
						whoPausedTheGame = "Player two paused the game!";
					}
				}
			}
			else
			{
				whoPausedTheGame = ""; //Setting the variable to blank so no text is rendered
			}
			Display.sync(60);
			Display.update();
		}
		Display.destroy();
		System.exit(0);
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
				oos.writeObject("Welcome to the game!  You will be player two! (Left)");
				new Thread(send).start();
				new Thread(receive).start();
				socket = null;
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
					if(playerNum == 0)
					{
						ois = new ObjectInputStream(socket.getInputStream());
						p2Y = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						isPlayer2Paused = (Boolean) ois.readObject();
					}
					else if(playerNum == 1)
					{	
						ois = new ObjectInputStream(socket.getInputStream());
						P1Y = (Integer) ois.readObject();
						
						ois = new ObjectInputStream(socket.getInputStream());
						Ball b = (Ball) ois.readObject();
						
						ball.setX(b.getX());
						ball.setY(b.getY());
						
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
			while(true)
			{
				try
				{
					oos = new ObjectOutputStream(socket.getOutputStream());
					if(playerNum == 0){
						oos.writeObject(P1Y);
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(ball);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(score0);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(score1);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(isPaused);
					}
					else if(playerNum == 1){							
						oos.writeObject(p2Y);
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(isPlayer2Paused);
					}
					Thread.sleep(20);
				}catch(Exception e){}
			}
		}
	};
	private static Runnable onUpdate = new Runnable()
	{
		public void run() 
		{
			while(true)
			{
				if(isPlayer1Paused || isPlayer2Paused){
					isPaused = true;
				}else{
					isPaused = false;
				}
				if(!isPaused && ((score0 < 21) && (score1 < 21)))
				{
					if(ball.getdX() > 0){
						ball.setdX((float) (ball.getdX() + .002));
					}else{
						ball.setdX((float) (ball.getdX() - .002));
					}
					//This bit updates the movement of the ball. Without it the ball would
					//just be static and wouldn't move at all :3
					ball.setX((int) (ball.getX()-ball.getdX()));
					ball.setY((int) (ball.getY()-ball.getdY()));
				}
				
				/*
				>>>>NOTE<<< TODO 
				ball.getcenterOfBallX() = ball.getcenterOfBallX();
				ball.getCenterOfBallY(); =  ball.getCenterOfBallY();  DELETE AFTER READING*/
				
				//Checking collision for the two player paddles if the
				//ball is colliding with the paddle
				boolean hitPlayerOnesPaddle = ball.getCenterOfBallX() >= P1X && ball.getCenterOfBallX() <= P1X + 20 && ball.getCenterOfBallY() >= P1Y && ball.getCenterOfBallY() <= P1Y + 60;
				boolean hitPlayersTwosPaddle = ball.getCenterOfBallX() >= p2X && ball.getCenterOfBallX() <= p2X + 20 && ball.getCenterOfBallY() >= p2Y && ball.getCenterOfBallY() <= p2Y + 60;
				
				if(hitPlayerOnesPaddle)
				{
					//Calculating where the ball will go after being hit off
					//the paddle, same as in brick breaker
					ball.setdX(-ball.getdX());
					ball.setdY((float) (((P1Y+30)-ball.getCenterOfBallY())/7.5));
				}
				if(hitPlayersTwosPaddle)
				{
					//Calculating where the ball will go after being hit off
					//the paddle, same as in brick breaker
					ball.setdX(-ball.getdX());
					ball.setdY((float) (((p2Y+30)-ball.getCenterOfBallY())/7.5));
					//ball.setdY(p2Y )
				}
				if(ball.getCenterOfBallX() > Display.getWidth()) //Scored on RIGHT side of screen
				{
					score0++;
					if(score0 == 21){
						ball.setX(Display.getWidth() / 2);
						ball.setY((int) (Display.getHeight() * 0.25));
					}else{
						ball.setX(Display.getWidth() / 2);
						ball.setY(Display.getHeight() / 2);
					}
					ball.setdX(5);
					ball.setdY(0);
					
				}
				if(ball.getCenterOfBallX() < 0) //Scored on LEFT side of screen
				{
					score1 ++;
					if(score1 == 21){
						ball.setX(Display.getWidth() / 2);
						ball.setY((int) (Display.getHeight() * 0.25));
					}else{
						ball.setX(Display.getWidth() / 2);
						ball.setY(Display.getHeight() / 2);
					}
					ball.setdX(5);
					ball.setdY(0);
				}
				if(ball.getY() < 0){ //Bounce off ceiling
					ball.getY();
					ball.setdY(-ball.getdY());
				}
				if(ball.getY() > Display.getHeight()) //Bounce off floor
				{
					ball.setY(Display.getHeight() - 1);
					ball.setdY(-ball.getdY());
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
