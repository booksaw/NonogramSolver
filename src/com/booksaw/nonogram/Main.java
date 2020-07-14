package com.booksaw.nonogram;

import com.booksaw.nonogram.element.State;
import com.booksaw.nonogram.graphics.GridRender;
import com.booksaw.nonogram.graphics.RenderFrame;
import com.booksaw.nonogram.load.TextLoader;

public class Main {

	public static void main(String[] args) {
		Nonogram nono = new Nonogram(new TextLoader());
		nono.elements[0][0].setState(State.FILLED);
		System.out.println("Is nono solved? " + nono.checkSolution());

		RenderFrame frame = new RenderFrame(nono, new GridRender(nono));
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
