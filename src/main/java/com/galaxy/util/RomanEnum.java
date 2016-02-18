package com.galaxy.util;

/**
 * This class stores enum with valid roman values
 * @author qamer ali
 *
 */
public class RomanEnum {

	public enum Roman{
			I (1),
			V (5),
			X (10),
			L (50),
			C (100),
			D (500),
			M (1000);
	
		private double value;
		private Roman(double decimal){
			this.value = decimal;
		}
		
		public double getValue(){
			return value;
		}
		
	}
}
