package com.enums;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
	VERTICAL, HORIZONTAL;
	public static final Map<Integer,Direction> directions= new HashMap<>();
	
	static {
		directions.put(0, VERTICAL);
		directions.put(1, HORIZONTAL);
	}
}
