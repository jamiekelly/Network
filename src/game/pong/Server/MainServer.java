package game.pong.Server;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.lwjgl.opengl.Display;


public class MainServer
{
	public static int port = 7777;
	public static String ip = "";
	
	//When game will be ready then both players will be brought
	//in game and the ball will appear and players will be able to
	//move their paddles and rebound the ball :p
	public static boolean isGameReady = false;
	//It's set to -1 because the code is run once started, and every
	//time someone connects to the server; so to make it 0 when the first
	//player joins, it should be set to -1 :p
	public static int numPeopleConnected = 0;
	public static ServerSocket server;
	
	//Server side location of player one!
	public static int p1X = 0;
	public static int p1Y = 0;
	//Server side location of player two!
	public static int p2X = 0;
	public static int p2Y = 0;
	
	public static Ball ball = new Ball();
	//ArrayList that holds the players, the data won't be sent if the player
	//is the same as the data being sent
	public static ArrayList<Player> list_players = new ArrayList<Player>();
	
	public static ArrayList<Socket> list_sockets = new ArrayList<Socket>();
	public static ArrayList<Integer> list_client_states = new ArrayList<Integer>();
	public static ArrayList<DataPackage> list_data = new ArrayList<DataPackage>();
	private static Runnable onUpdate = new Runnable(){
		public void run() {
			while(isGameReady){
				if(isGameReady){
					ball.x += ball.dX;
					ball.y += ball.dY;
					int x = ball.x + 10;
					int y = ball.y + 10;
					int pW = 20;
					int pH = 60;
					//Checking collision for the two player paddles if the
					//ball is colliding with the paddle
					for(int i = 0; i < list_players.size(); i++){
						int pX = list_players.get(i).x;
						int pY = list_players.get(i).y;
						if(x  > pX && x < pX + pW && y > pY && y < pY + pH){
							ball.dX = -ball.dX;
							//Calculating where the ball will go after being hit off
							//the paddle, same as in brick breaker
							ball.dY = (pX + (pH / 2)) - (y + 10);
						}
					}
					if(x > Display.getWidth()){
						//The Left sided player scores
						list_players.get(0).score += 1;
						//Resets the location of the ball :p
						x = Display.getWidth() / 2;
						y = Display.getHeight() / 2;
						//TODO Set a wait time for reset?
					}
					if(x < 0){
						//The Right side player scores
						list_players.get(1).score += 1;
						//Resets the location of the ball :p
						x = Display.getWidth() / 2;
						y = Display.getHeight() / 2;
						//TODO Set a wait time for reset?
						/*
						 * That's actually a good idea, how about a count down
						 * like when the ball is reset then it will spawn back in
						 * like 2 seconds or something? or the player who scored
						 * gets to start it?
						 * -Rob
						 */
					}
				}
			}
		}
	};
	private static Runnable accept = new Runnable()
	{
		@Override
		public void run()
		{

			new Thread(onUpdate).start();
			
			while (true)
			{
				//Notifying console the amount of people connected currently
				numPeopleConnected++;
				System.out.println(numPeopleConnected); //TODO stop more than two clients from connecting to server?
				try{
					//Server accepts the user and is made a socket connection
					Socket socket = server.accept();
					
					list_clients_model.addElement(numPeopleConnected + " - " + socket.getInetAddress().getHostAddress() + " - " + socket.getInetAddress().getHostName());
					list_client_states.add(0);
					list_players.add(new Player());
					list_sockets.add(socket);
					
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(numPeopleConnected - 1);
					if(numPeopleConnected == 1)
					{
						System.out.println("one player!");
						//Adds first player to array
					}
					else if(numPeopleConnected == 2) //TODO Shouldn't this either be set to "== 1" or change initial val of numPeopleConnected = 0?
					{
						System.out.println("two player!");
						isGameReady = true;
						//Adds second player to array
						//Start the send + receive threads! :D
						
						ball.x = Display.getWidth() / 2 - 10;
						ball.y = Display.getHeight() / 2 - 10;
						
						new Thread(send).start();
						new Thread(receive).start();
					}
					Thread.sleep(20);
				}
				catch (Exception ex) {}
			}
		}
	};
	
	public static void updateArray()
	{
		synchronized(list_sockets)
		{
			for(int i = 0; i < list_sockets.size(); i++)
			{
			}
		}
		synchronized(list_client_states){
			for(int i = 0; i < list_client_states.size(); i++)
			{
			}
		}
		synchronized(list_data)
		{
			for(int i = 0; i < list_data.size(); i++)
			{
			}
		}
		synchronized(list_players)
		{
			for(int i = 0; i < list_data.size(); i++)
			{
			}
		}
	}
	private static Runnable send = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (true)
			{
				updateArray();
				if(isGameReady){
					/*
					* First the server is going to 
					*/
					for(int i = 0; i < list_players.size(); i++){
						try{
							//Send player data to each other
							oos = new ObjectOutputStream(list_sockets.get(i).getOutputStream());
							Player p = list_players.get(i);
							int num = 0;;
							if(i == 0){
								num = 1;
							}else if(i == 1){
								num = 0;
							}
							oos.writeObject(list_players.get(num));
							
							oos = new ObjectOutputStream(list_sockets.get(i).getOutputStream());
							
							
							
							Thread.sleep(20);
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			}
		}
	};
	
	private static Runnable receive = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectInputStream ois;
			
			while (true)
			{
				updateArray();
				
				
				if(isGameReady){
					/*
					* First the server is going to 
					*/
					for(int i = 0; i < list_players.size(); i++){
						try{
							ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
							Player p = (Player) ois.readObject();
							list_players.set(i, p);
							Thread.sleep(20);
						}catch(Exception ex){}
					}
				}
			}
		}
	};
	
	public static void disconnectClient(int index)
	{
		try
		{
			list_clients_model.removeElementAt(index);
			list_client_states.remove(index);
			list_data.remove(index);
			list_sockets.remove(index);
		}
		catch (Exception ex) {}
	}
	
	
	
	public static JFrame frame;
	
	public static JPanel content;
	public static JPanel panel1;
	public static JPanel panel2;
	public static JPanel panel3;
	
	public static JButton btn_disconnect;
	
	public static JList list_clients;
	public static DefaultListModel list_clients_model;
	
	
	
	public static void main(String[] args)
	{
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ex) {}
		
		try
		{
			ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
			
			server = new ServerSocket(port, 0, InetAddress.getLocalHost());
			new Thread(accept).start();
		}
		catch (IOException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		
		btn_disconnect = new JButton();
		btn_disconnect.setText("Disconnet");
		btn_disconnect.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selected = list_clients.getSelectedIndex();
				
				if (selected != -1)
				{
					try
					{
						updateArray();
						list_client_states.set(selected, 1);
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		list_clients_model = new DefaultListModel();
		list_clients = new JList(list_clients_model);
		list_clients.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (e.getValueIsAdjusting())
				{
					System.out.println(list_clients.getSelectedIndex());
				}
			}
		});
		
		frame = new JFrame();
		frame.setTitle("Server - " + ip);
		
		frame.addWindowListener(new WindowListener()
		{
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e)
			{
				while (list_sockets.size() != 0)
				{
					try
					{
						for (int i = 0; i < list_client_states.size(); i++)
						{
							list_client_states.set(i, 2);
						}
					}
					catch (Exception ex) {}
				}
				
				System.exit(0);
			}
			
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1, 1, 1));
		panel1.add(btn_disconnect);
		
		panel2 = new JPanel();
		panel2.add(new JLabel(ip));
		
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(1, 1));
		panel3.add(panel1, BorderLayout.NORTH);
		panel3.add(new JScrollPane(list_clients), BorderLayout.CENTER);
		panel3.add(panel2, BorderLayout.SOUTH);
		
		content = new JPanel();
		content.setLayout(new GridLayout(1, 1, 1, 1));
		content.add(panel3);
		
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		frame.setContentPane(content);
		frame.pack();
		frame.setSize(350, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}