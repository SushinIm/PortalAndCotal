package com.mini.util;

public class UtilTools {
	public static String toTimeFour(String tStr) {
		String result = tStr.substring(0, 2) + ":" + tStr.substring(2);
		return result;
	}
}
