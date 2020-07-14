package com.booksaw.nonogram;

import java.util.ArrayList;
import java.util.List;

import com.booksaw.nonogram.element.Element;
import com.booksaw.nonogram.element.Header;
import com.booksaw.nonogram.graphics.NonogramRender;
import com.booksaw.nonogram.load.NonogramLoader;

public class Nonogram {

	protected NonogramLoader loader;
	public List<Header> columns, rows;
	public Element[][] elements;

	public Nonogram(NonogramLoader loader) {
		this.loader = loader;

		columns = new ArrayList<>();
		rows = new ArrayList<>();

		loader.load(this);

		elements = new Element[columns.size()][rows.size()];

		for (int y = 0; y < rows.size(); y++) {
			for (int x = 0; x < columns.size(); x++) {
				elements[x][y] = new Element(this);
			}
		}

	}

	@Override
	public String toString() {
		return getOutput(columns) + ":" + getOutput(rows);
	}

	private String getOutput(List<Header> headers) {
		String str = "";
		for (Header header : headers) {
			str = str + header.toString() + ";";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	NonogramRender render;

	public void setRender(NonogramRender render) {
		this.render = render;
		for (int y = 0; y < rows.size(); y++) {
			for (int x = 0; x < columns.size(); x++) {
				render.registerElementRender(elements[x][y]);
			}
		}
	}

	public NonogramRender getRender() {
		return render;
	}

	public boolean checkSolution() {
		// checking columns
		for (Header head : columns) {
			if (!head.isSolved(getColumn(head.getLocation()))) {
				System.out.println("failed on column " + head.getLocation());
				return false;
			}
		}

		for (Header head : rows) {
			if (!head.isSolved(getRow(head.getLocation()))) {
				System.out.println("failed on row" + head.getLocation());
			}
		}

		return true;

	}

	public Element[] getColumn(int location) {
		return elements[location];
	}

	public Element[] getRow(int location) {
		Element[] toReturn = new Element[columns.size()];

		for (int x = 0; x < columns.size(); x++) {
			toReturn[x] = elements[x][location];
		}
		return toReturn;
	}

}
