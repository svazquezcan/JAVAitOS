package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idGame;
	private String username;
	private long duration;
	private int unCompletedWords;
	
	public Game() {
		
	}
	
	public Game (long idGame, String username, long duration){
		this.idGame = idGame;
		this.username = username;
		this.duration = duration;
	}
	
	public long getIdGame() {
		return this.idGame; 		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public void setIdGame(long idGame) {
		this.idGame = idGame; 
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getUnCompletedWords() {
		return unCompletedWords;
	}

	public void setUnCompletedWords(int unCompletedWords) {
		this.unCompletedWords = unCompletedWords;
	}
}
