package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.Command;
import command.DisconnectCommand;
import command.UpdateClientCommand;

import shapes.Drawable;

/**
 * Creates a server at the local machine and begins to listen to port 9001 for incoming connections.
 * @author Abhishek Rane
 * @author Bryce Hammond
 *
 */
public class Server {
	private ServerSocket socket;
	
	private List<Drawable> items;
	private HashMap<String, ObjectOutputStream> outputs;
	
	private class ClientHandler implements Runnable{
		private ObjectInputStream input; // the input stream from the client
		
		public ClientHandler(ObjectInputStream input){
			this.input = input;
		}
		
		public void run() {
			try{
				while(true){
					// read a command from the client, execute on the server
					Command<Server> command = (Command<Server>)input.readObject();
					command.execute(Server.this);
					
					// terminate if client is disconnecting
					if (command instanceof DisconnectCommand){
						input.close();
						return;
					}
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 *	This thread listens for incoming connection and adds them to the server.
	 *
	 *@author Gabe Kishi
	 */
	private class ClientAccepter implements Runnable{
		public void run() {
			try{
				while(true){
					// accept a new client, get output & input streams
					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(s.getInputStream());
					
					// read the client's name
					String clientName = (String)input.readObject();
					
					// map client name to output stream
					outputs.put(clientName, output);
					
					// spawn a thread to handle communication with this client
					new Thread(new ClientHandler(input)).start();
					
					// add a notification message to the chat log
					System.out.println(clientName + " connected");
					updateClients();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sets up a new server at the port 9001.
	 */
	public Server(){
		this.items = new ArrayList<Drawable>();
		this.outputs = new HashMap<String, ObjectOutputStream>(); // setup the map
		
		try{
			// start a new server on port 9001
			socket = new ServerSocket(9001);
			System.out.println("NetPaintServer started on port 9001");
			
			// spawn a client accepter thread
			new Thread(new ClientAccepter()).start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an item to the master list of {@link shapes.Drawable} objects. This method is called by the {@link command.AddObjectCommand}
	 * Informs all {@link controller.Client}s that an update occurred.
	 * @param item The {@link shapes.Drawable} to be added. 
	 */
	public void addItem(Drawable item){
		items.add(item);
		updateClients();
	}
	
	/**
	 * Called when the list of items is modified.
	 * Sends an {@link command.UpdateClientCommand} to all (@link controller.Client}s.
	 */
	public void updateClients() {
		// make an UpdateClientCommmand, write to all connected users
		UpdateClientCommand update = new UpdateClientCommand(items);
		try{
			for (ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Executed when a (@link commands.DisconnectCommand} is received by the server. Removes the (@link controller.Client}.
	 * @param clientName The username of the (@link controller.Client} that disconnected.
	 */
	public void disconnect(String clientName) {
		try{
			outputs.get(clientName).close(); // close output stream
			outputs.remove(clientName); // remove from map
			
			// add notification message
			System.out.println(clientName + " disconnected");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * The entry point for the server app.
	 * @param args Not used here.
	 */
	public static void main(String[] args){
		new Server();
	}
}
