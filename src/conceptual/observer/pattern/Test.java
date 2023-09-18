package conceptual.observer.pattern;

public class Test {

	public static void main(String[] args) {
		Listener listener = new Listener();
		Subject subject = new Subject();
		
		subject.registerObserver(listener);
		subject.inspect();
	}
}