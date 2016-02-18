package com.galaxy;

import java.util.List;

import com.galaxy.service.GalaxyService;
import com.galaxy.service.GalaxyServiceImpl;
import com.galaxy.util.InputReader;
import com.galaxy.validator.InputValidator;

/**
 * This is main class. It reads input file, validate input and call service to get result
 * @author qamer ali
 *
 */
public class GalaxyMain {

	private InputReader inputReader = new InputReader();
	private InputValidator inputValidator = new InputValidator();
	private GalaxyService galaxyService = new GalaxyServiceImpl();
	
	
	public static void main(String[] args) {
		new GalaxyMain().findResult("", "input.txt");
	}
	
	/**
	 * This method calls inputReader, inputValidator and service method to find result
	 */
	public void findResult(String path, String fileName) {
		List<String> inputList = inputReader.readInputFile(path,fileName);
		inputValidator.validate(inputList);
		List<String> answers = galaxyService.getUnitResult(inputList);
		System.out.println("Test Output:");
		answers.stream().forEach(System.out::println);
	}

	public void setInputReader(InputReader inputReader) {
		this.inputReader = inputReader;
	}

	public void setInputValidator(InputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}

	public void setGalaxyService(GalaxyService galaxyService) {
		this.galaxyService = galaxyService;
	}

	
	
}
