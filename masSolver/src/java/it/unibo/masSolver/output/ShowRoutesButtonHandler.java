package it.unibo.masSolver.output;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.JTextArea;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult.OrderDetails;

public class ShowRoutesButtonHandler implements ActionListener{

	private JTextArea textArea;
	private IOrdersReadModel ordersReadModel;
	private Logger logger;
	private UpdateOrderHandler updateOrderHandler;
	
	public ShowRoutesButtonHandler(UpdateOrderHandler updateOrderHandler, IOrdersReadModel ordersReadModel, Logger logger) {
		this.updateOrderHandler = updateOrderHandler;
		this.ordersReadModel = ordersReadModel;
		this.logger = logger;
	}
	
	public void setTextArea(JTextArea textArea){
		this.textArea = textArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			logger.info("button pressed");
			textArea.setText("");
			
			HashMap<String, Collection<String>> routes = updateOrderHandler.getRoutes(); 
			
			routes.forEach((k,v) -> {
				textArea.append("Route: " + k + "\n");
				
				GetOrdersDetailsByIds query = new GetOrdersDetailsByIds();
				query.Ids = v;
				GetOrdersDetailsByIdsResult result = ordersReadModel.Query(query);
				int total = 0;
				for( OrderDetails x : result.OrdersDetails){
					textArea.append("\t" + "Order:" + x.Id + " Demand: " + Math.round(x.demand) + "\n");
					total += x.demand;
				}
				textArea.append("TotalDemand: " + total + " \n");
			});
		}
}
