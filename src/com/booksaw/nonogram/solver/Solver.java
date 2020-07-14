package com.booksaw.nonogram.solver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.booksaw.nonogram.Nonogram;

public abstract class Solver implements ActionListener {

	protected Nonogram nonogram;

	public Solver(Nonogram nonogram) {
		this.nonogram = nonogram;
	}

	Timer timer;

	public void start(int delay) {
		timer = new Timer(delay, this);
		timer.start();
	}

	public abstract void runCycle();

	@Override
	public void actionPerformed(ActionEvent e) {
		runCycle();

		if (nonogram.checkSolution()) {
			timer.stop();
			JOptionPane.showMessageDialog(null, "solved");
		}
	}

}
