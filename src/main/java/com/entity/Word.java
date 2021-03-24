package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Word {

	@Id
	private long idWord;

	private String word;
	
	//Constructor
	
	public Word (long idWord, String word){
		this.idWord = idWord;
		this.word = word;
	}
	
	//Getters
	
	public long getIdWord() {
		return this.idWord; 		
	}
	
	public String getWord() {
		return this.word;
	}
	
	//Setters
	
	public void setIdWord(long idWord) {
		this.idWord = idWord; 
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	

}
