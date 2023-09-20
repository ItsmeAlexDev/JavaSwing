package com.alexdev.calc.vision;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculator extends JFrame {
	
	private static int WIDTH = 336;
	private static int HEIGHT = 510;
	private static Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	public Calculator() {
		
		organizeLayout();
		
		setTitle("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(SIZE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void organizeLayout() {
		setLayout(new BorderLayout());
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(337, 100));
		add(display, NORTH);
		
		Keyboard KBoard = new Keyboard();
		add(KBoard, CENTER);
	}

	public static void main(String[] args) {
		new Calculator();
	}
}