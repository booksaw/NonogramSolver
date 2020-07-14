package com.booksaw.nonogram.load;

import java.util.List;
import java.util.Scanner;

import com.booksaw.nonogram.Nonogram;
import com.booksaw.nonogram.element.Header;

public class TextLoader implements NonogramLoader {

	@Override
	public void load(Nonogram nonogram) {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Input nonogram string");
			loadString(scan.nextLine(), nonogram);

			scan.close();

		} catch (Exception e) {
			System.err.println("invalid string");
		}
	}

	public void loadString(String str, Nonogram nonogram) {
		String[] split = str.split(":");
		loadDetails(split[0], nonogram.columns);
		loadDetails(split[1], nonogram.rows);
	}

	public void loadDetails(String reference, List<Header> loadTo) {

		String[] split = reference.split(";");
		int i = 0;
		for (String str : split) {
			loadTo.add(new Header(str, i));
			i++;
		}

	}

}
