package com.galaxy.validator;

import static com.galaxy.constants.Constants.CREDIT_UNITS;
import static com.galaxy.constants.Constants.UNITS;
import static java.util.Objects.isNull;

import java.util.List;

import com.galaxy.exception.InvalidParameterException;
import com.galaxy.util.RomanEnum.Roman;

/**
 * This class validate input
 * @author qamer ali
 *
 */
public class InputValidator {

	
	/**
	 * This method takes list of string and validate.
	 * @param inputList
	 * @throws InvalidParameterException
	 */
	public void validate(List<String> inputList) {
		
		if(isNull(inputList)) {
			throwInvalidException("Null input !");
		}else if(inputList.isEmpty()) {
			throwInvalidException("Empty input file !");
		}
		
		if(isFirstInputLineEndsWithQuestion(inputList)) {
			throwInvalidException("input should not start with question !");
		}

		for(String line : inputList) {
			if(!line.endsWith("?")) {
				isUnitInitializationLineValid(line);
			}
		}
	}
	
	/**
	 * This method validate given line
	 * @param line
	 * @throws InvalidParameterException
	 */
	private void isUnitInitializationLineValid(String line) {
		String[] words = line.split(" ");
		int noOfWords = words.length;
		if(noOfWords > 3){
				for(int i=0;i<noOfWords;i++){
					
					if(i < noOfWords-4 && !UNITS.contains(words[i].toLowerCase())){
						throwInvalidException("invalid unit: "+words[i]);
					}
					else if(i == noOfWords-4 && !CREDIT_UNITS.contains(words[i].toLowerCase())){
						throwInvalidException("invalid credit unit: "+words[i]);
					}
					else if(i == noOfWords-3 && !words[i].equalsIgnoreCase("is")){
						throwInvalidException("invalid input: "+words[i]);
					}
					else if(i == noOfWords-2 && isNotNumeric(words[i])){
						throwInvalidException("invalid number: "+words[i]);
					}
					else if(i == noOfWords-1 && !words[i].equalsIgnoreCase("Credits")){
						throwInvalidException("invalid input: "+words[i]);
					}
				}
		}else if(noOfWords == 3){
			if(!UNITS.contains(words[0].toLowerCase())){
				throwInvalidException("Invalid text description!");
			}else if(!words[1].equalsIgnoreCase("is")){
				throwInvalidException("Invalid text description!");
			}else if(!isValidRomanNumber(words[2])) {
				throwInvalidException("Invalid text description!");
			}
		}else {
			throwInvalidException("invalid input line description!");
		}
	}
	
	
	/**
	 * This method returns true if given input string is valid roman number else throw exception
	 * @param romanString
	 * @throws IllegalArgumentException
	 * @return boolean
	 */
	private boolean isValidRomanNumber(String romanString){
		Roman.valueOf(romanString);
		return true;
	}
	
	
	/**
	 * This method returns true if given number is not numeric else return false
	 * @param str
	 * @return boolean
	 */
	private boolean isNotNumeric(String str) {  
		try  {  
			Double.parseDouble(str);  
		} catch(NumberFormatException nfe) {  
			return true;  
		}  
		return false;  
	}
	
	/**
	 * This method throws InvalidParameterException
	 * @param message
	 */
	private void throwInvalidException(String message){
		throw new InvalidParameterException(message);
	}
	
	/**
	 * This method returns true if first line ends with ?
	 * @param inputList
	 * @return boolean
	 */
	private boolean isFirstInputLineEndsWithQuestion(List<String> inputList) {
		return inputList.get(0).endsWith("?");
	}

}
