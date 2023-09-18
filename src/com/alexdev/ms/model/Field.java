package com.alexdev.ms.model;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

public class Field {
	
	private final int row_X;
	private final int col_Y;
	
	private boolean mined;
	private boolean open;
	private boolean marked;
	
	private List<Field> neighbors = new ArrayList<>();
	private List<FieldObserver> observers = new ArrayList<>();
	
	Field(int x, int y){
		this.row_X = x;
		this.col_Y = y;
	}
	
	public void registerObserver(FieldObserver observer) {
		observers.add(observer);
	}
	
	public void notifyObservers(FieldEvent e) {
		observers.stream()
				 .forEach(obs -> obs.eventOccurred(this, e));
	}
	
	boolean addNeighbor(Field neighbor) {
		boolean isDiagonal = row_X != neighbor.row_X && col_Y != neighbor.col_Y;
		int delta = abs(row_X - neighbor.row_X) + abs(col_Y - neighbor.col_Y);
		
		if (delta == 1 && !isDiagonal) {
			neighbors.add(neighbor);
			return true;
		} else if (delta == 2 && isDiagonal) {
			neighbors.add(neighbor);
			return true;
		} else 
			return false;
	}
	
	public void toggleMarkup() {
		if (!open) {
			marked = !marked;
		
		if (marked)
			notifyObservers(FieldEvent.MARK);
		else 
			notifyObservers(FieldEvent.MARK_OFF);
		}
	}

	void mine() {
		mined = true;
	}
	
	public boolean open() {
		if (!open && !marked) {
			open = true;
			
			if (mined) {
				notifyObservers(FieldEvent.EXPLODE);
				return true;
			}
			
			setOpen(true);
			
			if (safeNeighborhood())
				neighbors.forEach(neighbor -> neighbor.open());
			
			return true;
		} else
			return false;
	}
	
	
	public boolean safeNeighborhood() {
		return neighbors.stream().noneMatch(neighbor -> neighbor.isMined());
	}
	
	public boolean isMined() {
		return mined;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	void setOpen(boolean open) {
		this.open = open;
		
		if(open)
			notifyObservers(FieldEvent.OPEN);
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public boolean isLocked() {
		return !open;
	}

	public int getRow_X() {
		return row_X;
	}

	public int getCol_Y() {
		return col_Y;
	}
	
	boolean goalAchieved() {
		return (!mined && open) || (mined && marked);
	}
	
	public long minesInNeighborhood() {
		return neighbors.stream().filter(neighbor -> neighbor.mined).count();
	}
	
	void reiniciar() {
		open = false;
		mined = false;
		marked = false;
		notifyObservers(FieldEvent.AGAIN);
	}
}