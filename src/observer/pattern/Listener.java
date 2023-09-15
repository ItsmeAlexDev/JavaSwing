package observer.pattern;

public class Listener implements Observer{

	@Override
	public void arrived(Event e) {
		System.out.println("An event has happened and this Listener was notified!");
	}
}