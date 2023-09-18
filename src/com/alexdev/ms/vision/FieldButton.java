package com.alexdev.ms.vision;

import static java.awt.Color.GRAY;
import static javax.swing.BorderFactory.createBevelBorder;
import static javax.swing.BorderFactory.createLineBorder;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.alexdev.ms.model.Field;
import com.alexdev.ms.model.FieldEvent;
import com.alexdev.ms.model.FieldObserver;

@SuppressWarnings("serial")
public class FieldButton extends JButton
	implements FieldObserver, MouseListener{
	
	private final Color BG_DEFAULT = new Color(184, 184, 184);
	private final Color BG_OPEN = new Color(175, 188, 200);
	private final Color BG_MARKED = new Color(8, 179, 247);
	private final Color BG_EXPLODE = new Color(189, 66, 68);
	private final Color GREEN_TEXT = new Color(0, 100, 0);
	
	private Field field;
	
	public FieldButton(Field field) {
		this.field = field;
		setBackground(BG_DEFAULT);
		setOpaque(true);
		setBorder(createBevelBorder(0));
		
		setFocusable(false);
		
		addMouseListener(this);
		field.registerObserver(this);
	}

	@Override
	public void eventOccurred(Field f, FieldEvent e) {
		switch (e) {
			case OPEN:
				applyStyleOPEN();
				break;
			case MARK:
				applyStyleMarked();
				break;
			case EXPLODE:
				applyStyleExplode();
				break;
			default:
				applyStyleDefault();
				break;
		}
	}

	private void applyStyleOPEN() {
		setBorder(createLineBorder(GRAY));
		setOpaque(true);
		setBackground(BG_OPEN);
		if(field.isMined()) setBackground(BG_EXPLODE);
		else setForeground(GREEN_TEXT);
		String text = field.isMined() ? "💣" :
					 (field.safeNeighborhood() ? "" : "" + field.minesInNeighborhood());
		setText(text);
	}
	
	private void applyStyleMarked() {
		setOpaque(true);
		setBackground(BG_MARKED);
		setText("🚩");
	}
	
	private void applyStyleExplode() {
		setOpaque(true);
		setBackground(BG_EXPLODE);
		setText("💣");
	}
	
	private void applyStyleDefault() {
		setOpaque(true);
		setBackground(BG_DEFAULT);
		setBorder(createBevelBorder(0));
		setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) field.open();
		if(e.getButton() != 1) field.toggleMarkup();
	}
		
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}