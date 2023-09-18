package com.alexdev.ms.model;

@FunctionalInterface
public interface FieldObserver {
	public void eventOccurred(Field f, FieldEvent e);
}