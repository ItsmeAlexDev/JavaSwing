package com.alexdev.calc.model;

public class Memory {
	
	private static final Memory instance = new Memory();
	
	private String currentText = "";
	
	private Memory() {
		
	}
	
	public static Memory getMemory() {
		return instance;
	}
	
	public String getCurrentText() {
		return currentText.isEmpty() ? "0" : currentText;
	}
}