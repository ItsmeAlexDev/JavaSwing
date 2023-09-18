package conceptual.observer.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subject {

	private List<Observer> observers = new ArrayList<>();
	
	public void registerObserver(Observer obs) {
		observers.add(obs);
	}
	
	public void inspect() {
		Scanner input = new Scanner(System.in);
		String value = "";
		
		while(!"exit".equalsIgnoreCase(value)) {
			System.out.println("Has something happened? (y/N)");
			value = input.nextLine();
			
			if("yes".equalsIgnoreCase(value))
				observers.stream().forEach(obs -> obs.arrived(new Event()));
			else
				System.out.println("Waiting for something to happen...");
		}
		input.close();
	}
}