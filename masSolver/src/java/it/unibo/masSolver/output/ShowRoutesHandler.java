package it.unibo.masSolver.output;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult.OrderDetails;

public class ShowRoutesHandler {

	private JTextArea textArea;
	private IOrdersReadModel ordersReadModel;
	private Logger logger;
	private UpdateOrderHandler updateOrderHandler;
	private ExecutorService executor;
	
	public ShowRoutesHandler(UpdateOrderHandler updateOrderHandler, IOrdersReadModel ordersReadModel, Logger logger) {
		this.updateOrderHandler = updateOrderHandler;
		this.ordersReadModel = ordersReadModel;
		this.logger = logger;
		this.executor = Executors.newSingleThreadExecutor();
	}
	
	public void setTextArea(JTextArea textArea){
		this.textArea = textArea;
	}
	
	public void updateOutput() {
			logger.info("button pressed");
			
			Callable<String> callable = new Callable<String>() {
		        @Override
		        public String call() {
		        	HashMap<String, Collection<String>> routes = updateOrderHandler.getRoutes(); 
					String result = "";
		        	
					try{
					
						for (String k : routes.keySet()){
							Collection<String> v = routes.get(k);
							
							result += "Route: " + k + "\n";
							
							GetOrdersDetailsByIds query = new GetOrdersDetailsByIds();
							query.Ids = v;
							GetOrdersDetailsByIdsResult resultQuery = ordersReadModel.Query(query);
							int total = 0;
							for( OrderDetails x : resultQuery.OrdersDetails){
								result += "\t" + "Order:" + x.Id + " Demand: " + Math.round(x.demand) + "\n";
								total += x.demand;
							}
							result += "TotalDemand: " + total + " \n";
						}
					} catch (Exception e){
						result = "";
					}
					return result;
		        }
		    };
		    
		    Future<String> future = executor.submit(callable);
			
			try {
				String output = future.get();
				if (!output.equals(""))
					textArea.setText(output);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}