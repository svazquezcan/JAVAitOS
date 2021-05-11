package com.service;

import com.dto.SoapLetterDTO;

public interface SoapLettersService {
	
	SoapLetterDTO checkSoapLetter (SoapLetterDTO soapLetterDTO);
	
	SoapLetterDTO generateSoapLetter(String idGame);
	
	
}
