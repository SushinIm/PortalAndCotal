package com.mini.util;

public class UtilTools {
	public static String toTimeFour(String tStr) {
		String result = tStr.substring(0, 2) + ":" + tStr.substring(2);
		return result;
	}
	
	public static String phoneType(String pStr) {
		String result = pStr.substring(0, 3) + "-" + pStr.substring(3, 7) + "-" + pStr.substring(7);
		return result;
	}
}
