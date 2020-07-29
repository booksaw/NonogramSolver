package com.booksaw.nonogram.load;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.Utils;
import com.booksaw.nonogram.element.Header;

public class ImageLoader implements NonogramLoader {

	private final BufferedImage compareTo = Utils.getImage("hardSearch.png");
	private final BufferedImage[] numbers;
	private int xOnScreen, yOnScreen;
//	private Color ignore = new Color(235, 239, 247);

	public ImageLoader() {
		numbers = new BufferedImage[15];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Utils.getImage("numbers" + File.separator + (i + 1) + ".png");
		}
	}

	@Override
	public void load(Nonogram nonogram) {
		BufferedImage image = null;
		try {
			Robot robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			image = robot.createScreenCapture(screenRect);
//			ImageIO.write(image, "png", new File("printscreen.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (image == null) {
			return;
		}

		BufferedImage pscr = null;
		// loaded image
		for (int x = 0; x + compareTo.getWidth() < image.getWidth(); x++) {
			for (int y = 0; y + compareTo.getHeight() < image.getHeight(); y++) {
				if (checkStrictLocation(x, y, image, compareTo)) {
					System.out.println("found at x = " + x + " y = " + y);
					xOnScreen = x;
					yOnScreen = y;
					pscr = image.getSubimage(x, y, compareTo.getWidth(), compareTo.getHeight());
					break;
				}
			}
		}

		if (pscr == null) {
			JOptionPane.showMessageDialog(null, "Could not find nonogram");
			return;
		}

		addColumns(pscr, nonogram);
	}

	private boolean checkStrictLocation(int offsetX, int offsetY, BufferedImage toCheck, BufferedImage compareTo) {
		for (int x = 0; x < compareTo.getTileWidth(); x++) {
			for (int y = 0; y < compareTo.getHeight(); y++) {
				int rgb = compareTo.getRGB(x, y);
				if ((rgb >> 24) == 0x00) {
					continue;
				} else if (rgb != toCheck.getRGB(offsetX + x, offsetY + y)) {
					return false;
				}
			}
		}
		return true;
	}

	private double checkLocation(int offsetX, int offsetY, BufferedImage toCheck, BufferedImage compareTo) {
		int total = 0;
		int success = 0;
		for (int x = 0; x < compareTo.getTileWidth(); x++) {
			for (int y = 0; y < compareTo.getHeight(); y++) {
				int rgb = compareTo.getRGB(x, y);
				if ((rgb >> 24) == 0x00 || new Color(rgb).getGreen() > 150) {
					continue;
				} else if (new Color(toCheck.getRGB(offsetX + x, offsetY + y)).getGreen() < 150) {
					success++;
				}
				total++;
			}
		}
		return (success) / (double) total;
	}

	private void addColumns(BufferedImage pscr, Nonogram nono) {
		boolean full = true;
		int column = 0;
		for (int x = 0; x < pscr.getWidth(); x++) {
			int rgb = compareTo.getRGB(x, 0);
			if ((rgb >> 24) != 0x00) {
				full = true;
				continue;
			}

			if (!full) {
				// the column has already been searched
				continue;
			}

			// new column found.
			System.out.println("Column " + column + " found at x = " + x);
			Header columnHeader = new Header(column);
			populateColumn(pscr, columnHeader, x);
			nono.columns.add(columnHeader);
			column++;
			full = false;
		}
	}

	private void populateColumn(BufferedImage pscr, Header column, int offsetX) {
		// hashmap of numbers found format <y, number>
		HashMap<Integer, Integer> numbersFound = new HashMap<>();
		List<Integer> ignore = new ArrayList<>();

		for (int i = numbers.length - 1; i >= 0; i--) {
			// looping through all y values
			y: for (int y = 0; y < 90; y++) {
				// if a number has been found in that location
				if (ignore.contains(y)) {
					continue;
				}

				for (int x = 0; x < 24; x++) {
					try {
//						if()
						if (checkLocation(offsetX + x, y, pscr, numbers[i]) > .95) {
							numbersFound.put(y, i + 1);
							// looping through the image height to add to ignore list
							for (int j = 0; j < numbers[i].getHeight(); j++) {
								ignore.add(j + y);
							}
							continue y;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						continue y;
					}
				}

			}
		}
		System.out.println("found hashmap = " + numbersFound);

	}

	public int getxOnScreen() {
		return xOnScreen;
	}

	public int getyOnScreen() {
		return yOnScreen;
	}

}
