package com.alexdev.calc.model;

import static java.lang.Double.parseDouble;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	private enum CommandType {
		INVERSE, CLEAR, NUMBER, DIVISION, MULTIPLICATION, ADDITION, SUBTRACTION, COMMA, EQUALS
	};
	
	private static final Memory instance = new Memory();
	
	private final List<MemoryObserver> observers = new ArrayList<>();
	
	private CommandType operation = null;
	private boolean takePlace = false;
	private String currentValue = "";
	private String bufferValue = "";
	
	private Memory() {}
	
	public static Memory getMemory() {
		return instance;
	}
	
	public String getCurrentValue() {
		currentValue = currentValue.replace(".", ",");
		if(currentValue.startsWith(",")) currentValue = currentValue.replace(",", "0,");
		return currentValue.isEmpty() ? "0" : currentValue;
	}
	
	public void addObserver(MemoryObserver MObserver) {
		observers.add(MObserver);
	}
	
	public void doCommand(String value) {
		CommandType commandType = detectCommandType(value);
		
		if(commandType == null) return;
		else if(commandType == CommandType.CLEAR) {
			currentValue = "";
			bufferValue = "";
			takePlace = false;
			operation = null;
		}
		else if(commandType == CommandType.NUMBER ||
		   commandType == CommandType.COMMA) {
			currentValue = takePlace ? value : currentValue + value;
			takePlace = false;
		}
		else if(commandType == CommandType.INVERSE) {
			Double currentNumber = parseDouble(getCurrentValue().replace(",", ".")) * -1;
			if(currentNumber == 0) return;
			String result = Double.toString(currentNumber);
			boolean isInteger = result.endsWith(".0");
			currentValue = isInteger ? result.replace(".0", "") : result;
		}
		else{
			takePlace = true;
			currentValue = operationResult();
			bufferValue = currentValue;
			operation = commandType;
		}
		
		observers.forEach(obs -> obs.changedValue(getCurrentValue()));
	}
	
	private String operationResult() {
		if(operation == null ||
		   operation == CommandType.EQUALS) return currentValue;
		
		double bufferValue = this.bufferValue.isEmpty() ?
				0 : parseDouble(this.bufferValue.replace(",", "."));
		double currentValue = this.currentValue.isEmpty() ?
				0 : parseDouble(this.currentValue.replace(",", "."));
		double total = 0;
		
		switch (operation) {
			case ADDITION:
				total = bufferValue + currentValue;
				break;
			case SUBTRACTION:
				total = bufferValue - currentValue;
				break;
			case MULTIPLICATION:
				total = bufferValue * currentValue;
				break;
			case DIVISION:
				total = currentValue != 0 ? bufferValue / currentValue : 0;
				break;
		}
		
		total = Double.valueOf(new DecimalFormat("#.00").format(total));
		
		String totalString = Double.toString(total);
		boolean isInteger = totalString.endsWith(".0");
		return isInteger ? totalString.replace(".0", "") : totalString;
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
			if(",".equals(value) && !currentValue.contains(",")) return CommandType.COMMA;
		}
		return null;
	}
}