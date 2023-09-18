package com.alexdev.ms.model;

public class ResultEvent {

	private final boolean hasWon;
	
	public ResultEvent(boolean hasWon) {
		this.hasWon = hasWon;
	}
	
	public boolean hasWon() {
		return hasWon;
	}
}