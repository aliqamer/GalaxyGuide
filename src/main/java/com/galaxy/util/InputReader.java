package com.galaxy.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class reads input file 
 * @author qamer ali
 *
 */
public class InputReader {

	/**
	 * This method reads file at given path & filename and returns file contents in list of string
	 * @param foldersPath
	 * @param fileName
	 * @return List<String>
	 */
	public List<String> readInputFile(String foldersPath,String fileName) 
	{
	    Path path = Paths.get(foldersPath, fileName);
	    try(Stream<String> lines = Files.lines(path)) {	    	
	    	return lines.collect(Collectors.toList());
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
	}
}
