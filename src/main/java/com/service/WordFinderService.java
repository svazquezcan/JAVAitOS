package com.service;

import com.dto.WordFinderDTO;

public interface WordFinderService {
	
	WordFinderDTO checkSoapLetter (WordFinderDTO wordFinderDTO);
	
	WordFinderDTO generateWordFinder();
	
	
}
