package command;

import java.io.Serializable;

/**
 * This is the base class for all commands. A command encapsulates a block of code that can be sent between machines.
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public abstract class Command<T> implements Serializable {
	private static final long serialVersionUID = -4838155228547508978L;
	
	/**
	 * This method will contain the code to be executed on the target.
	 * @param executeOn The target location to execute the command.
	 */
   public abstract void execute(T executeOn);
}