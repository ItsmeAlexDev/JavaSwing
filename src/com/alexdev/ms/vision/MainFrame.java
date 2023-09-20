package com.alexdev.ms.vision;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.alexdev.ms.model.Board;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private static int WIDTH = 690;
	private static int HEIGHT = 480;
	
	private int mines = 20;
	
	private static Dimension SIZE = new Dimension(WIDTH, HEIGHT);

	public MainFrame() {
		Board board = new Board(16, 30, mines);
		add(new BoardPanel(board));
		
		setTitle("MineSweeper");
		setSize(SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}