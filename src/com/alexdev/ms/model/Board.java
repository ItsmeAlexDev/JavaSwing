package com.alexdev.ms.model;

import static java.lang.Math.random;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Board implements FieldObserver{
	
	private final int rows;
	private final int cols;
	private final int mines;
	
	private final List<Field> fields = new ArrayList<>();
	private final List<Consumer<ResultEvent>> observers = new ArrayList<>();

	public Board(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.mines = mines;
		
		generateFields();
		associateNeighbors();
		mineFields();
	}
	
	public void forEachField(Consumer<Field> f) {
		fields.forEach(f);
	}
	
	public void registerObserver(Consumer<ResultEvent> observer) {
		observers.add(observer);
	}
	
	public void notifyObservers(boolean result) {
		observers.stream().forEach(obs -> obs.accept(new ResultEvent(result)));
	}
	
	public void open(int row, int col) {
			fields.stream()
				.filter(field -> field.getRow_X() == row &&
				     	         field.getCol_Y() == col)
				.findFirst()
				.ifPresent(field -> field.open());
	}
	
	public void toggleMarkup(int row, int col) {
		fields.stream()
			.filter(field -> field.getRow_X() == row &&
			     	         field.getCol_Y() == col)
			.findFirst()
			.ifPresent(field -> field.toggleMarkup());
	}

	private void generateFields() {
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < rows; col++) {
				Field field = new Field(row, col);
				field.registerObserver(this);
				fields.add(field);
			}
	}

	private void associateNeighbors() {
		for (Field field_1 : fields)
			for (Field field_2 : fields)
				field_1.addNeighbor(field_2);
	}
	
	private void mineFields() {
		long minedFields = 0;
		
		while (minedFields < mines) {
			int rnd = (int) (random() * fields.size());
			fields.get(rnd).mine();
			minedFields = fields.stream().filter(field -> field.isMined()).count();
		}
	}

	public boolean goalAchieved() {
		return fields.stream().allMatch(field -> field.goalAchieved());
	}
	
	public void restartGame() {
		fields.stream().forEach(field -> field.reiniciar());
		mineFields();
	}
	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getMines() {
		return mines;
	}
	
	public List<Field> getFields() {
		return unmodifiableList(fields);
	}

	public int getFieldsCount() {
		return fields.size();
	}

	@Override
	public void eventOccurred(Field f, FieldEvent e) {
		if (e == FieldEvent.EXPLODE) { 
			notifyObservers(false);
			showMines();
		}
		else if(goalAchieved())
			notifyObservers(true);
	}
	
	private void showMines() {
		fields.stream()
			  .filter(f -> f.isMined())
			  .forEach(field -> field.setOpen(true));
	}
}