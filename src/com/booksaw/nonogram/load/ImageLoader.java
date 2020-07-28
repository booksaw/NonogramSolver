package com.booksaw.nonogram.load;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.Utils;

public class ImageLoader implements NonogramLoader {

	private final BufferedImage compareTo = Utils.getImage("hardSearch.png");
	private final BufferedImage[] numbers;
	private int xOnScreen, yOnScreen;

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

		// loaded image
		for (int x = 0; x + compareTo.getWidth() < image.getWidth(); x++) {
			for (int y = 0; y + compareTo.getHeight() < image.getHeight(); y++) {
				if (checkLocation(x, y, image)) {
					System.out.println("found at x = " + x + " y = " + y);
					xOnScreen = x;
					yOnScreen = y;
					break;
				}
			}
		}
	}

	private boolean checkLocation(int offsetX, int offsetY, BufferedImage pscr) {
		for (int x = 0; x < compareTo.getTileWidth(); x++) {
			for (int y = 0; y < compareTo.getHeight(); y++) {
				int rgb = compareTo.getRGB(x, y);
				if ((rgb >> 24) == 0x00) {
					continue;
				} else if (rgb != pscr.getRGB(offsetX + x, offsetY + y)) {
					return false;
				}
			}
		}

		return true;
	}
}
