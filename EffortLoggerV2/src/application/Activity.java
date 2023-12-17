package application;

import java.time.*;

public class Activity {
	private Instant startTime;
	private Instant endTime;
	
	private int activityID;
	private static int currentID;
	
	private String project;
	private String lifeCycleStep;
	private String effortCategory;
	private String otherDetails;
	
	private Clock clock;
	
	public Activity(String projectTitle, String step, String category, String other){
		this.clock = Clock.systemDefaultZone();
		this.startTime = null;
		this.endTime = null;
		
		this.activityID = currentID;
		
		this.project = projectTitle;
		this.lifeCycleStep = step;
		this.effortCategory = category;
		this.otherDetails = other;
	}
	
	public Activity(String projectTitle, String step, String category, String other, String start, String end, String date){
		this.clock = Clock.systemDefaultZone();
		this.startTime = null;
		this.endTime = null;
		
		this.project = projectTitle;
		this.lifeCycleStep = step;
		this.effortCategory = category;
		this.otherDetails = other;
	}
	
	public void startActivity() {
		this.startTime = clock.instant();
	}
	
	public void endActivity() {
		this.endTime = clock.instant();
	}
	
	public static void setCurrentID(int id) {
		currentID = id;
	}
	public Instant getStart() {return this.startTime;}
	public Instant getEnd() {return this.endTime;}
	public int getID() {return this.activityID;}
	
	public Duration getDuration() {
		if(endTime != null) {
			return Duration.between(startTime, endTime);
		} else {return null;}
	}
	
	public Duration getDurationFromNow() {
		if(startTime != null) {
			return Duration.between(startTime, clock.instant());
		} else {return null;}
	}
	
	public String[] getDebrief() {
		return new String[] 
				{this.project,
				this.lifeCycleStep,
				this.effortCategory,
				this.otherDetails};
	}
}
