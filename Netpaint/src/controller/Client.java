package controller;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.swing.JOptionPane;

import view.NetPaintClient;
import model.Command;
import model.DisconnectCommand;
import model.Drawable;

public class Client {
	private String clientName; // user name of the client
	private NetPaintClient netPaintClient;
	
	private Socket server; // connection to server
	private ObjectOutputStream out; // output stream
	private ObjectInputStream in; // input stream

	/**
	 * This class reads and executes commands sent from the server
	 * 
	 * @author Gabriel Kishi
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run() {
			try{
				while(true){
					// read a command from server and execute it
					Command<Client> c = (Command<Client>)in.readObject();
					c.execute(Client.this);
				}
			}
			catch(SocketException e){
				return; // "gracefully" terminate after disconnect
			}
			catch (EOFException e) {
				return; // "gracefully" terminate
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public Client(){
		// ask the user for a host, port, and user name
		String host = JOptionPane.showInputDialog("Host address:");
		String port = JOptionPane.showInputDialog("Host port:");
		clientName = JOptionPane.showInputDialog("User name:");
		
		if (host == null || port == null || clientName == null)
			return;
		
		try{
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			
			out.writeObject(clientName);
			
			setupGUI();
			
			// start a thread for handling server events
			new Thread(new ServerHandler()).start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 	Creates a ChatPanel and adds it to this frame
	 */
	private void setupGUI() {
		netPaintClient = new NetPaintClient(out);	
		netPaintClient.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent arg0) {
			try {
				out.writeObject(new DisconnectCommand(clientName));
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
	}

	public static void main(String[] args){
		new Client();
	}

	/**
	 * Updates the ChatPanel with the updated message log
	 * 
	 * @param items	the log of messages to display
	 */
	public void update(List<Drawable> items) {
		netPaintClient.update(items);
	}
}
