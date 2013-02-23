package Client;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 
 * @author Vallentin <vallentinsource@gmail.com>
 * @since April 8, 2012
 * 
 */

public class Client extends JComponent
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setTitle("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new Client());
		
		frame.pack();
		frame.setSize(650, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	public Client()
	{
		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
		});
		
		
		try
		{
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
			
			socket = new Socket(ip, port);
			
			String set_username = System.getProperty("user.name");
			set_username = (String) JOptionPane.showInputDialog(null, "Username: ", "Info", JOptionPane.INFORMATION_MESSAGE, null, null, set_username);
			username = set_username;
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(username);
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String response = (String) ois.readObject();
			String response2 = (String) ois.readObject();
			
			JOptionPane.showMessageDialog(null, response, "Message", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, response2, "Message", JOptionPane.INFORMATION_MESSAGE);
			if (response.equals("Your name is already taken!"))
			{
				System.exit(0);
			}
			
			new Thread(send).start();
			new Thread(receive).start();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Alert", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	
	public int state = 0;
	public boolean connected = true;
	
	public static void updateArray(){
		synchronized(list_data){
			for(int i = -1; i < list_data.size(); i++){
			}
		}
		synchronized(others){
			for(int i = -1; i < others.size(); i++){
			}
		}
	}
	
	Runnable send = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			
			while (connected)
			{
				updateArray();
				if (socket != null)
				{
					try
					{
						DataPackage dp = new DataPackage();
						dp.x = x;
						dp.y = y;
						dp.username = username;
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(state);
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(dp);
						
						if (state == 1) // Client Disconnected
						{
							connected = false;
							socket = null;
							
							JOptionPane.showMessageDialog(null, "Client Disconnected", "Info", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}
					catch (Exception ex) {}
				}
				else
				{
					break;
				}
			}
		}
	};
	
	public static ArrayList<DataPackage> list_data = new ArrayList<DataPackage>();
	
	Runnable receive = new Runnable()
	{
		@Override
		public void run()
		{
			ObjectInputStream ois;
			
			while (connected)
			{
				updateArray();
				try
				{
					ois = new ObjectInputStream(socket.getInputStream());
					int receive_state = (Integer) ois.readObject();
					
					if (receive_state == 1) // Kicked / Disconnected by Server
					{
						connected = false;
						socket = null;
						
						JOptionPane.showMessageDialog(null, "Disconnected by Server", "Info", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					else if (receive_state == 2) // Server Disconnected
					{
						connected = false;
						socket = null;
						
						JOptionPane.showMessageDialog(null, "Server Disconnected", "Info", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					try{
						updateArray();
						ois = new ObjectInputStream(socket.getInputStream());
						list_data = (ArrayList<DataPackage>) ois.readObject();
					}catch(Exception ex){
						System.out.println("Haywire");
					}
					for (int i = 0; i< list_data.size(); i++)
					{
						DataPackage dp = list_data.get(i);
						if (list_data.size() != others.size())
						{
							System.out.println("IF");
							if (list_data.size() > others.size())
							{
								others.add(dp);
								System.out.println("Others Exist!");
							}
							
							if (list_data.size() < others.size())
							{
								others.remove(0);
							}
						}
						else
						{
							System.out.println("ELSE");
							others.set(i, dp);
						}
					}
				}
				catch (Exception ex) {}
			}
		}
	};
	
	
	
	public static Socket socket;
	
	public static int port = 7777;
	public static String ip = "";
	
	
	
	public int x = 0;
	public int y = 0;
	
	public String username = "Vallentin";
	
	public static ArrayList<DataPackage> others = new ArrayList<DataPackage>();
	
	
	
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < others.size(); i++)
		{
			try
			{
				DataPackage dp = others.get(i);
				
				if (!dp.username.toLowerCase().equals(username.toLowerCase()))
				{
					g2d.setColor(Color.RED);
					g2d.fillOval((int) dp.x - 50, (int) dp.y - 50, 100, 100);
					
					g2d.setColor(Color.BLACK);
					g2d.drawString(dp.username, dp.x - 50, dp.y - 70);
				}
			}
			catch (Exception ex) {}
		}
		
		
		
		g2d.setColor(Color.BLUE);
		g2d.fillOval(x - 50, y - 50, 100, 100);
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(username, x - 50, y - 70);
		
		
		
		try
		{
			Thread.sleep(1);
		}
		catch (Exception ex) {}
		
		repaint();
	}
}
