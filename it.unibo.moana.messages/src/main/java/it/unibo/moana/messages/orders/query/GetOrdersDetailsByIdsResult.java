package it.unibo.moana.messages.orders.query;

public class GetOrdersDetailsByIdsResult {
	public OrderDetails[] OrdersDetails;
	
	public class OrderDetails{
		
		public OrderDetails() {
			// TODO Auto-generated constructor stub
		}
		public String Id;
		public double positionLatitude;
		public double positionLongitude;
	}
}
