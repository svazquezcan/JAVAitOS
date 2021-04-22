package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.SoapLetterDTO;
import com.service.SoapLettersService;

@RestController
@RequestMapping("/soapletters")
public class SoapLetterController {
	    
	@Autowired
	private SoapLettersService soapLettersService;
	
	@GetMapping
	public SoapLetterDTO getRandomSoapLetter() {
		LdapUserDetailsImpl ldapDetails = (LdapUserDetailsImpl) SecurityContextHolder
		            .getContext().getAuthentication().getPrincipal();
		SoapLetterDTO soapLetterDTO=soapLettersService.generateSoapLetter(ldapDetails.getUsername());
		return soapLetterDTO;
	}
	
	@PostMapping("/check")
	public SoapLetterDTO checkSoapLetter(@RequestBody SoapLetterDTO soapLetterDTO) {
		SoapLetterDTO soapLetterDTOAUX=soapLettersService.checkSoapLetter(soapLetterDTO);
		return soapLetterDTOAUX;
	}   
	
}
