package com.galaxy.service;

import static com.galaxy.util.RomanEnum.Roman;


/**
 * This class converts given roman number to decimal value
 * @author qamer ali
 *
 */
public class RomanConverterServiceImpl implements RomanConverterService {

	public static void main(String s[]){
		System.out.println(new RomanConverterServiceImpl().convertRomanToDecimal("LLVII"));
	}

	/**
	 * This method converts roman number to decimal 
	 * @param romanNumber
	 * @return double
	 */
	@Override
	public double convertRomanToDecimal(String romanNumber){

		String[] romanValues = romanNumber.split("");
		Roman previousRomanNumber = Roman.valueOf(romanValues[0]);
		Roman currentRomanNumber = null;
		double digit = previousRomanNumber.getValue();
		
		int size = romanValues.length;
		for(int i=1; i<size; i++){
			currentRomanNumber = Roman.valueOf(romanValues[i]);
			if(previousRomanNumber.getValue() >= currentRomanNumber.getValue()){
				digit += currentRomanNumber.getValue();
			}else{
				digit += currentRomanNumber.getValue() - previousRomanNumber.getValue() - previousRomanNumber.getValue();
			}
			previousRomanNumber = currentRomanNumber;
		}
		return digit;
	}
}
