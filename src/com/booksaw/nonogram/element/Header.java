package com.booksaw.nonogram.element;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store the details of a row or column of a nonogram
 * 
 * @author booksaw
 *
 */
public class Header {

	private List<Integer> headers;
	private int location;

	public Header(String details, int location) {
		this.location = location;
		String[] split = details.split(",");

		headers = new ArrayList<>();

		for (String str : split) {
			headers.add(Integer.parseInt(str));
		}

	}

	public List<Integer> getHeaders() {
		return headers;
	}

	@Override
	public String toString() {
		String str = "";
		for (Integer integer : headers) {
			str = str + integer + ",";
		}

		str = str.substring(0, str.length() - 1);
		return str;

	}

	public boolean isSolved(Element[] row) {

		List<Integer> toSatisfy = new ArrayList<>(headers);

		int count = -1;
		for (Element ele : row) {
			if (ele.getState() == State.UNKNOWN || ele.getState() == State.BLANK) {

				if (count > 0) {
					return false;
				}

				if (count == 0) {
					count = -1;
				}

			} else {
				if (count == 0) {
					return false;
				} else if (count == -1) {
					if (toSatisfy.size() == 0) {
						return false;
					}
					Integer temp = toSatisfy.get(0);
					toSatisfy.remove(temp);
					count = --temp;

				}
			}
		}
		if (count > 0) {
			return false;
		}
		return true;

	}

	public int getLocation() {
		return location;
	}

}
