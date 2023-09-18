package conceptual.observer.pattern;

import java.util.Date;

public class Event {

	private final Date date;
	
	public Event() {
		this.date = new Date();
	}
	
	public Date getDate() {
		return this.date;
	}
}