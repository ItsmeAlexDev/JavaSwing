package conceptual.observer.pattern;

@FunctionalInterface
public interface Observer {
	public void arrived(Event e);
}