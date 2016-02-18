package com.galaxy.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * This is galaxy service implementation which takes list of input lines and fing unit results
 * @author qamer ali
 *
 */
public class GalaxyServiceImpl implements GalaxyService {
	
	private final RomanConverterService romanConverterService = new RomanConverterServiceImpl();
	
	/**
	 * This method takes list of string and convert given intergalactic units into decimal number
	 * and return final results
	 * @param inputLines
	 * @return List<String> 
	 */
	@Override
	public List<String> getUnitResult(final List<String> inputLines) {
		
		Map<String, String> unitMap = initializeUnitValues(inputLines);
		Map<String, String> creditUnitMap = initializeCreditUnitValues(inputLines,unitMap);
		return generateResult(inputLines,unitMap,creditUnitMap);
	}
	
	private List<String> generateResult(List<String> inputLines,
			Map<String, String> unitMap, Map<String, String> creditUnitMap) {
		
		List<String> answerList = newArrayList();
		inputLines.stream().filter(input -> isLineEndWithQuestion(input)).forEach(question -> {
			
			String[] words = question.split(" ");

			if(question.toLowerCase().startsWith("how much is")) {
				
				String romanNumber = "";
				String galaxyUnits = "";
				int noOfWords = words.length;
				for(int i=3;i<noOfWords-1;i++){
					romanNumber += unitMap.get(words[i]);
					galaxyUnits += words[i] + " ";
				}
				double digit = romanConverterService.convertRomanToDecimal(romanNumber);
				answerList.add(galaxyUnits+"is "+digit);
				
			}else if(question.toLowerCase().startsWith("how many credits is")) {
				
				String romanNumber = "";
				String galaxyUnits = "";
				int noOfWords = words.length;
				for(int i=4;i<noOfWords-2;i++) {
					romanNumber += unitMap.get(words[i]);
					galaxyUnits += words[i] + " ";
				}
				double digit = romanConverterService.convertRomanToDecimal(romanNumber);
				double answer = digit * Double.parseDouble(creditUnitMap.get(words[noOfWords-2]));
				answerList.add(galaxyUnits+words[noOfWords-2]+" is "+answer);
			}else{
				answerList.add("I have no idea what you are talking about");
			}
		});
		return answerList;
	}

	private Map<String, String> initializeCreditUnitValues(List<String> inputList,Map<String, String> unitMap) {
		return inputList.stream().filter(input -> isTextHasValidCreditUnit(input)).
				collect(Collectors.toMap(input -> getCreditKey(input),input -> getCreditValue(input,unitMap)));
	}

	private Map<String, String> initializeUnitValues(List<String> inputList) {
		return inputList.stream().filter(input -> isTextHasValidUnit(input)).
				collect(Collectors.toMap(input -> getKey(input),input -> getValue(input)));
	}
	
	private String getKey(String line) {
		String[] words = line.split(" ");
		return words[0];
	}
	
	private String getValue(String line) {
		String[] words = line.split(" ");
		return words[2];
	}
	
	private String getCreditKey(String line){
		String[] words = line.split(" ");
		return words[words.length-4];
	}
	
	private String getCreditValue(String line, Map<String, String> unitMap){
		String[] words = line.split(" ");
		int noOfWords = words.length;
		double dividend = Double.parseDouble(words[noOfWords-2]);
		
		String romanNumber = getRomanNumberFromGivenLineUnits(unitMap,
				words, noOfWords);
		double divisor = romanConverterService.convertRomanToDecimal(romanNumber);
		return dividend / divisor + "" ;
	}

	private String getRomanNumberFromGivenLineUnits(
			Map<String, String> unitMap, String[] words, int noOfWords) {
		StringBuilder romanNumber = new StringBuilder();
		for(int i=0;i<noOfWords-4;i++){
			romanNumber.append(unitMap.get(words[i])); 
		}
		return romanNumber.toString();
	}
	
	private boolean isTextHasValidUnit(String line) {
		String[] words = line.split(" ");
		return !(line.endsWith("?") || words.length != 3);
	}

	private boolean isTextHasValidCreditUnit(String line) {
		String[] words = line.split(" ");
		return !(line.endsWith("?") || words.length < 4);
	}
	
	private boolean isLineEndWithQuestion(String line) {
		return line.endsWith("?");
	}
}