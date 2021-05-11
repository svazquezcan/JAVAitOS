package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
	LEFT, RIGHT, TOP, BOTTOM;

	public static final Map<Integer, Orientation> orientations = new HashMap<>();

	static {
		orientations.put(0, LEFT);
		orientations.put(1, RIGHT);
		orientations.put(2, TOP);
		orientations.put(3, BOTTOM);
	}
}
