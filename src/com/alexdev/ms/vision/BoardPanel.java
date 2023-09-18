package com.alexdev.ms.vision;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.alexdev.ms.model.Board;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	public BoardPanel(Board board) {
		int rows = board.getRows();
		int cols = board.getCols();

		setLayout(new GridLayout(rows, cols));
		
		board.forEachField(c -> add(new FieldButton(c)));
		
		board.registerObserver(e -> {
			invokeLater(() -> {
				if (e.hasWon()) showMessageDialog(this, "YOU WON!");
				else showMessageDialog(this, "YOU LOST!");
				
				board.restartGame();
			});
		});
	}
}