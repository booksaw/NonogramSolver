package com.booksaw.nonogram.graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.booksaw.nonogram.element.Element;

public class ElementRender extends JPanel {

	private static final long serialVersionUID = -9127202327633539697L;

	Element element;

	public ElementRender(Element element) {
		this.element = element;
	}

	public void paint(Graphics g) {

		switch (element.getState()) {
		case BLANK:
			g.setColor(Color.gray);
			g.fillRect(0, 0, getWidth(), getHeight());
			break;
		case FILLED:
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			break;
		case UNKNOWN:
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			break;

		}
	}

}
