package conceptual.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Observer {
	
	static int WIDTH = 600;
	static int HEIGHT = 200;
	static Dimension SIZE = new Dimension(WIDTH, HEIGHT);

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Observer");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(SIZE);
		frame.setLayout(new FlowLayout());
		frame.setLocationRelativeTo(null);
		
		JButton button = new JButton("Button");
		frame.add(button);
		
		button.addActionListener(e -> {
			System.out.println("I was clicked on :D");
		});
		
		frame.setVisible(true);
	}
}