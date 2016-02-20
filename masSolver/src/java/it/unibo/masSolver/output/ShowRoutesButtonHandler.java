package it.unibo.masSolver.output;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
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
			
			ExecutorService executor = Executors.newSingleThreadExecutor();
			
			Callable<String> callable = new Callable<String>() {
		        @Override
		        public String call() {
		        	HashMap<String, Collection<String>> routes = updateOrderHandler.getRoutes(); 
					String result = "";
		        	
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
					
					return result;
		        }
		    };
		    
		    Future<String> future = executor.submit(callable);
			
		    SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						while(!future.isDone()){}
						textArea.setText(future.get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			executor.shutdown();
		}
}
