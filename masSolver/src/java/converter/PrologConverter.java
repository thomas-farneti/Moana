package converter;

import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import jason.asSyntax.Literal;

public class PrologConverter {
	//"order(id,description,client(clientId,clientDescription,position(latitude,longitude)))"
	private static final String ORDER_STRUCTURE = "order(%s,%s,client(%s,%s,position(%d,%d)))";
	
	public static Literal toProlog(UpdateOrderCommand command){
		return Literal.parseLiteral(String.format(ORDER_STRUCTURE, 
				command.commandId,
				command.commandDescription,
				command.clientId,
				command.clientDescription,
				command.latitude,
				command.longitude));
	}
}
