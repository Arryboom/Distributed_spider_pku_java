package edu.pku.ss.hw;

import java.text.DecimalFormat;

public class DecimalUtils {
	/**
	* Àƒ…·ŒÂ»Î
	*/
	public static Double round2digit(Double d) {
		if (d == null) {
		  return 0.0;
		}
		Double result = Double.parseDouble(String.format("%.2f", d));
		return result;
	}

	public static float round2digitFloat(Float d) {
		if (d == null) {
		  return 0.0f;
		}
		Float result = Float.parseFloat(String.format("%.2f", d));
		return result;
	}
	
	// format double
	public static Double formatDouble(Double d) {
		if (d == null) {
		  return 0.0;
		}
		java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");
		Double result = df.format(d);
		return result;
	}
}
