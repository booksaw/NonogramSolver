package com.booksaw.nonogram.graphics;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.element.Element;
import com.booksaw.nonogram.element.Header;

public class GridRender extends NonogramRender {

	public GridRender(Nonogram toRender) {
		super(toRender);
	}

	@Override
	public JPanel generatePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(toRender.rows.size() + 1, toRender.columns.size() + 1));

		for (int y = 0; y < toRender.rows.size() + 1; y++) {
			for (int x = 0; x < toRender.columns.size() + 1; x++) {
				if (x == 0 && y == 0) {
					panel.add(new JLabel());
				} else if (x == 0) {
					panel.add(createHeaderRender(toRender.rows.get(y - 1)));
				} else if (y == 0) {
					panel.add(createHeaderRender(toRender.columns.get(x - 1)));
				} else {
					panel.add(getRender(toRender.elements[x - 1][y - 1]));
				}
			}
		}

		return panel;
	}

	public JComponent createHeaderRender(Header header) {
		return new JLabel(header.toString());

	}

	@Override
	public ElementRender getElementRender(Element element) {
		return new ElementRender(element);
	}

}
