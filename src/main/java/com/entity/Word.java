package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {

	@Id
	private long idWord;
	private String word;
	
	

	public Word() {
		super();
	}

	public Word(long idWord, String word) {
		super();
		this.idWord = idWord;
		this.word = word;
	}

	public long getIdWord() {
		return this.idWord;
	}

	public String getWord() {
		return this.word;
	}

	public void setIdWord(long idWord) {
		this.idWord = idWord;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
