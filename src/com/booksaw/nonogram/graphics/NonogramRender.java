package com.booksaw.nonogram.graphics;

import java.util.HashMap;

import javax.swing.JPanel;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.element.Element;

public abstract class NonogramRender {

	protected Nonogram toRender;
	protected HashMap<Element, ElementRender> elements;

	public NonogramRender(Nonogram toRender) {
		this.toRender = toRender;
		elements = new HashMap<>();
		toRender.setRender(this);

	}

	private JPanel panel;

	public JPanel getPanel() {
		if (panel == null) {
			panel = generatePanel();
		}

		return panel;
	}

	public abstract JPanel generatePanel();

	public void registerElementRender(Element element) {
		ElementRender render = getElementRender(element);
		elements.put(element, render);
	}

	public abstract ElementRender getElementRender(Element element);

	public void repaint() {
		if (panel == null) {
			return;
		}

		panel.repaint();
	}

	public ElementRender getRender(Element element) {
		return elements.get(element);
	}

}
