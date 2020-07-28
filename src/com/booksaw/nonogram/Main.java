package com.booksaw.nonogram;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.booksaw.nonogram.graphics.GridRender;
import com.booksaw.nonogram.graphics.RenderFrame;
import com.booksaw.nonogram.load.ImageLoader;
import com.booksaw.nonogram.solver.ProceduralSolver;
import com.booksaw.nonogram.solver.Solver;

public class Main {

	public static void main(String[] args) {
		
		JOptionPane.showConfirmDialog(null, "ready?");
		Nonogram nono = new Nonogram(new ImageLoader());

		RenderFrame frame = new RenderFrame(nono, new GridRender(nono));
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);

		Solver solver = new ProceduralSolver(nono);
		solver.start(1000);
	}

}
