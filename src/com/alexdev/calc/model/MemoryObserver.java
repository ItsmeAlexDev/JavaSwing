package com.alexdev.calc.model;

@FunctionalInterface
public interface MemoryObserver {
	void changedValue(String newValue);
}
