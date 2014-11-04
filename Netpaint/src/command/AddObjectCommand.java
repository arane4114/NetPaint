package command;

import shapes.Drawable;
import controller.Server;

/**
 * Sends a {@link shapes.Drawable} object to the {@link controller.Server} from the {@link controller.Client}.
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public class AddObjectCommand extends Command<Server> {
	private static final long serialVersionUID = 4222014184904080846L;
	private Drawable item;
	
	/**
	 * This is the main constructor for the object.
	 * @param item The item to be pushed to the {@link controller.Server}
	 */
	public AddObjectCommand(Drawable item){
		this.item = item;
	}
	
	/**
	 * This tells the server to add the {@link Drawable} object to the master list.
	 */
	@Override
	public void execute(Server executeOn) {
		executeOn.addItem(item);
	}
}

