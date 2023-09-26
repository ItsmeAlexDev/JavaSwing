package com.alexdev.calc.vision;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static javax.swing.BorderFactory.createLineBorder;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	private Color color;
	
	private int timesRun = 0;
	private ScheduledExecutorService executorService = newSingleThreadScheduledExecutor();
	
	public Button(String text, Color color) {
		this.color = color;
		
		setText(text);
		setOpaque(true);
		setFocusable(false);
		setColor(1);
		setContentAreaFilled(false);
		setOpaque(true);
		setFont(new Font("courier", PLAIN, 25));
		setBorder(createLineBorder(BLACK));
		
		addActionListener(e -> animateButton());
	}

	private void animateButton() {
		executorService.execute(new Runnable() {
	        @Override
	        public void run() {
	            colorChanger();
	            timesRun++;
	            try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            colorChanger();
	        }
	    });
	}
	
	private void colorChanger() {
		if(timesRun == 0) setColor(0);
		if(timesRun == 1) setColor(1);
	    if(timesRun >= 1) timesRun = 0;
	}
	
	private void setColor(int state) {
		int colorRed = color.getRed();
		int colorGreen = color.getGreen();
		int colorBlue = color.getBlue();
		
		int colorOffset = colorRed + 30 >= 255   ||
						  colorGreen + 30 >= 255 ||
						  colorBlue + 30 >= 255   ? -30 : 30;
		
		if (state == 1) {
			setBackground(color);
			setForeground(WHITE);
		} else {
			setBackground(new Color(colorRed + colorOffset,
									colorGreen + colorOffset,
									colorBlue + colorOffset));
			setForeground(BLACK);
		}
	}
}