package com.alexdev.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	private enum CommandType {
		INVERSE, CLEAR, NUMBER, DIVISION, MULTIPLICATION, ADDITION, SUBTRACTION, COMMA, EQUALS
	};
	
	private static final Memory instance = new Memory();
	
	private final List<MemoryObserver> observers = new ArrayList<>();
	
	private String currentValue = "";
	
	private Memory() {}
	
	public static Memory getMemory() {
		return instance;
	}
	
	public String getCurrentValue() {
		return currentValue.isEmpty() ? "0" : currentValue;
	}
	
	public void addObserver(MemoryObserver MObserver) {
		observers.add(MObserver);
	}
	
	public void doCommand(String value) {
		CommandType commandType = detectCommandType(value);
		
		if(commandType == CommandType.CLEAR) currentValue = "";
		else currentValue += value;
		
		observers.forEach(obs -> obs.changedValue(getCurrentValue()));
	}
	
	private CommandType detectCommandType(String value) {
		
		if(currentValue.isEmpty() && value == "0") return null;
		
		try {
			Integer.parseInt(value);
			return CommandType.NUMBER;
		} catch (Exception e) {
			if("±".equals(value)) return CommandType.INVERSE;
			if("C".equals(value)) return CommandType.CLEAR;
			if("/".equals(value)) return CommandType.DIVISION;
			if("*".equals(value)) return CommandType.MULTIPLICATION;
			if("+".equals(value)) return CommandType.ADDITION;
			if("-".equals(value)) return CommandType.SUBTRACTION;
			if("=".equals(value)) return CommandType.EQUALS;
			if(",".equals(value)) return CommandType.COMMA;
		}
		return null;
	}
}