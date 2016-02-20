package it.unibo.moana.infrastructure;

public class MoanaSettings {
	public boolean mongoPersistence;
	
	public MoanaSettings() {

	}
	
	public  MoanaSettings getDefault(){
		MoanaSettings set = new MoanaSettings();
		set.mongoPersistence = true;
		return set;
	}
	
	public  MoanaSettings getTestDefault(){
		MoanaSettings set = new MoanaSettings();
		set.mongoPersistence = false;
		return set;
	}
}
