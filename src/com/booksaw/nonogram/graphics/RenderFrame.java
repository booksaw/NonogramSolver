package com.booksaw.nonogram.graphics;

import javax.swing.JFrame;

import com.booksaw.nonogram.Nonogram;

public class RenderFrame extends JFrame {

	private static final long serialVersionUID = 348721L;

	public RenderFrame(Nonogram nono, NonogramRender render) {
		super();
		add(render.getPanel());
	}

}
