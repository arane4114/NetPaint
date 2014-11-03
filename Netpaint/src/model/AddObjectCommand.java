package model;

import controller.Server;

public class AddObjectCommand extends Command<Server> {
	private static final long serialVersionUID = 4222014184904080846L;
	private Drawable item;
	
	public AddObjectCommand(Drawable item){
		this.item = item;
	}

	@Override
	public void execute(Server executeOn) {
		executeOn.addItem(item);
	}
}
