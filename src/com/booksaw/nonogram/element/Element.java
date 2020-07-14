package com.booksaw.nonogram.element;

import com.booksaw.nonogram.Nonogram;

public class Element {

	private State state;
	private Nonogram parent;

	public Element(Nonogram parent) {
		state = State.UNKNOWN;
		this.parent = parent;
	}

	public Element(Nonogram parent, State state) {
		this.state = state;
		this.parent = parent;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		if (parent != null && parent.getRender() != null)
			parent.getRender().repaint();
	}

}
