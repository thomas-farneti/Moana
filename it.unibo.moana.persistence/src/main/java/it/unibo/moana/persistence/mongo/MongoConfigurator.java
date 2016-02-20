package it.unibo.moana.persistence.mongo;

import java.net.UnknownHostException;

import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;

public class MongoConfigurator {
	private static MongoSessionManager sessionManager;
	
	public static MongoSessionManager getSessionManager() throws UnknownHostException{
		if(sessionManager == null){
	    	Settings settings = Settings.defaultInstance()
	                .withDefaultUpdateStrategy(UpdateStrategies.DIFF)
	                .withDbName("Moana")
	                .withAddresses(Lists.newArrayList(new ServerAddress("tfarneti-srv.westeurope.cloudapp.azure.com", 27017)));
	    	
	        ContextBuilder builder = new ContextBuilder("it.unibo.persistence.mongo.mappings");
	    	//ContextBuilder builder = new ContextBuilder("it.unibo.moana.persistence.mongo.mappings");
	        sessionManager = MongoSessionManager.create(builder, settings);
		}
		
		return sessionManager;
	}
}
