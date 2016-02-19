package it.unibo.moana.messages.orders.query;

import java.util.Collection;

public class GetOrdersDetailsByIdsResult {
	public Collection<OrderDetails> OrdersDetails;
	
	public class OrderDetails{
		
		public OrderDetails() {
			// TODO Auto-generated constructor stub
		}
		public String Id;
		public double demand;
		public double positionLatitude;
		public double positionLongitude;
	}
}
