package com.alexdev.calc.vision;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;
import static javax.swing.BorderFactory.createLineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button(String text, Color color) {
		setText(text);
		setOpaque(true);
		setFocusable(false);
		setBackground(color);
		setForeground(WHITE);
		setContentAreaFilled(false);
		setOpaque(true);
		setFont(new Font("courier", PLAIN, 25));
		setBorder(createLineBorder(BLACK));
	}
}