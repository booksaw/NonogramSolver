package com.booksaw.nonogram;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	public static BufferedImage getImage(String reference) {

		File file = new File(reference);
		return getImage(file);

	}

	public static BufferedImage getImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			return null;
		}
	}
}
