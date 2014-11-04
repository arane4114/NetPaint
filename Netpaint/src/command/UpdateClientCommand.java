package command;

import java.util.List;
import java.util.LinkedList;

import shapes.Drawable;
import controller.Client;

/**
 * Updates a client with the current list of chat messages
 * 
 * @author Gabriel Kishi
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public class UpdateClientCommand extends Command<Client> {
	private static final long serialVersionUID = 4222014184904080846L;
	private List<Drawable> items; // the message log from the server
	
	/**
	 * Creates a new UpdateClientCommand with the given log of messages
	 * @param items	The list of drawable items.
	 */
	public UpdateClientCommand(List<Drawable> items){
		this.items = new LinkedList<Drawable>(items); // note: we are making a copy of the given list
	}

	public void execute(Client executeOn) {
		// update the client
		executeOn.update(items);
	}
}
