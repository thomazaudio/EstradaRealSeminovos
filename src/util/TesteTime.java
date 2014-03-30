package util;

import java.util.Calendar;

public class TesteTime {
	
	
	private long timeini;

	public long getTimeini() {
		return timeini;
	}

	public void setTimeini(long timeini) {
		this.timeini = timeini;
	}
	
	public TesteTime(){
		
		this.setTimeini(Calendar.getInstance().getTimeInMillis() - getTimeini());
		
	}
	
	
	public  long getTotalTime(){
		
		return Calendar.getInstance().getTimeInMillis() - getTimeini();
	}
	

}
