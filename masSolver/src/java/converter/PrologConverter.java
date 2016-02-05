package converter;

import it.unibo.moana.core.domain.Orders.Order;
import jason.asSyntax.Literal;

public class PrologConverter {
	//"order(id,description,client(clientId,clientDescription,position(latitude,longitude)))"
	private static final String ORDER_STRUCTURE = "order(%s,%s,client(%s,%s,position(%d,%d)))";
	
	public static Literal toProlog(Order order){
		return Literal.parseLiteral(String.format(ORDER_STRUCTURE, 
				order.getId(),
				order.description,
				order.getClientId(),
				order.getClientDescription(),
				order.getLatitude(),
				order.getLongitude()));
	}
}
