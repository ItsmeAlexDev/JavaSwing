package com.alexdev.calc.vision;

import static com.alexdev.calc.model.Memory.getMemory;
import static java.awt.Color.WHITE;
import static java.awt.FlowLayout.RIGHT;
import static java.awt.Font.PLAIN;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	
	private final JLabel label;
	
	public Display() {
		setBackground(new Color(50, 50, 50));
		label = new JLabel(getMemory().getCurrentText());
		label.setForeground(WHITE);
		label.setFont(new Font("courier", PLAIN, 30));
		
		setLayout(new FlowLayout(RIGHT, 10, 30));
		add(label);
	}
}