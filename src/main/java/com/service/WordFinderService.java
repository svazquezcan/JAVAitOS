package com.service;

import com.dto.WordFinderDTO;

public interface WordFinderService {
	
	WordFinderDTO checkWordFinder (WordFinderDTO wordFinderDTO);
	
	WordFinderDTO generateWordFinder();
	
	
}
