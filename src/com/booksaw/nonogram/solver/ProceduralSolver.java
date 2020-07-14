package com.booksaw.nonogram.solver;

import java.util.ArrayList;
import java.util.List;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.element.Element;
import com.booksaw.nonogram.element.Header;
import com.booksaw.nonogram.element.State;

public class ProceduralSolver extends Solver {

	public ProceduralSolver(Nonogram nonogram) {
		super(nonogram);
	}

	@Override
	public void runCycle() {

		for (Header head : nonogram.columns) {
			checkSolve(head, nonogram.getColumn(head.getLocation()));
		}

		for (Header head : nonogram.rows) {
			checkSolve(head, nonogram.getRow(head.getLocation()));
		}
	}

	public void checkSolve(Header heading, Element[] ele) {
		int total = (int) Math.pow(2, ele.length);

		List<String> validPatterns = new ArrayList<>();

		outside: for (int i = 0; i < total; i++) {
			Element[] temp = cloneArray(ele);
			String pattern = elongate(Integer.toBinaryString(i), ele.length);
			// checking if the pattern clashes with anything outside
			for (int j = 0; j < temp.length; j++) {
				if (pattern.charAt(j) == '1') {
					if (temp[j].getState() == State.UNKNOWN) {
						temp[j].setState(State.FILLED);
					} else if (temp[j].getState() == State.BLANK) {
						continue outside;
					}
				} else {

					if (temp[j].getState() == State.FILLED) {
						continue outside;
					}

				}
			}
			if (heading.isSolved(toElementArray(pattern)))
				validPatterns.add(pattern);

		}

		// checking for any constants between patterns
		Character[] pattern = new Character[ele.length];
		for (String temp : validPatterns) {

			for (int i = 0; i < temp.length(); i++) {
				if (pattern[i] == null) {
					pattern[i] = temp.charAt(i);
				} else if (pattern[i] != temp.charAt(i)) {
					pattern[i] = 'a';
				}
			}
		}

		// no possible values were found
		String temp = String.valueOf(pattern);
		if (temp == null || temp.equals("")) {
			return;
		}

		// setting values
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '1') {
				ele[i].setState(State.FILLED);
			}
			if (pattern[i] == '0') {
				ele[i].setState(State.BLANK);
			}
		}

	}

	public static String elongate(String value, int digits) {
		if (value.length() == digits) {
			return value;
		}

		return elongate("0" + value, digits);
	}

	public Element[] cloneArray(Element[] toClone) {
		Element[] clone = new Element[toClone.length];
		for (int i = 0; i < toClone.length; i++) {
			clone[i] = new Element(null, toClone[i].getState());
		}

		return clone;
	}

	public Element[] toElementArray(String details) {
		Element[] toReturn = new Element[details.length()];
		for (int i = 0; i < details.length(); i++) {
			if (details.charAt(i) == '0') {
				toReturn[i] = new Element(null, State.BLANK);
			} else {
				toReturn[i] = new Element(null, State.FILLED);
			}
		}

		return toReturn;
	}

}
