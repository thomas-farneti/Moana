package it.unibo.moana.core.domainEvents;

import it.unibo.moana.messages.ICommand;

public interface ICommandSender
{
	 <T extends ICommand> void Send(final T command) throws Exception ;
}
