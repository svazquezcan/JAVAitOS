package com.dto;

import java.util.List;
import java.util.Map;
/**
 * Clase que cuyo objetivo consiste en la transferencia de datos entre cliente-servidor.
 * @author aimry
 *
 */
public class SoapLetterDTO {
	
	private long idGame;
	
	private String[][] table;
	
	private Map<String, Boolean> wordsMap;
	
	private List<String> words;
	
	private String userName;
	
	private String word;
	
	private boolean gameFinished;
	
	private boolean wordValidated;
	
	public SoapLetterDTO() {
		
	}
	
	public SoapLetterDTO(String[][] table) {
		this.table=table;
	}

	public String[][] getTable() {
		return table;
	}

	public void setTable(String[][] table) {
		this.table = table;
	}
	
	public long getIdGame() {
		return idGame;
	}

	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}

	public List<String> getWords() {
		return words;
	}
	
	public void setWords(List<String> words) {
		this.words=words;
	}
	
	public Map<String, Boolean> getWordsMap() {
		return wordsMap;
	}

	public void setWordsMap(Map<String, Boolean> wordsMap) {
		this.wordsMap = wordsMap;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isGameFinished() {
		return gameFinished;
	}

	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	public boolean isWordValidated() {
		return wordValidated;
	}

	public void setWordValidated(boolean wordValidated) {
		this.wordValidated = wordValidated;
	}
	
}
