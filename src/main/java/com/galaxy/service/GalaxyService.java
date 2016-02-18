package com.galaxy.service;

import java.util.List;

/**
 * This is galaxy service which takes list of input lines and find unit results
 * @author qamer ali
 *
 */
public interface GalaxyService {
	
	public List<String> getUnitResult(final List<String> inputLines);
}