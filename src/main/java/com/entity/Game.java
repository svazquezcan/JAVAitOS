package com.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	
	@Id
	private long idGame;
	
	private String username;
	
	private long duration;
	
	//Constructor

	public Game (long idGame, String username, long duration){
		this.idGame = idGame;
		this.username = username;
		this.duration = duration;
	}
	
	//Getters
	
	public long getIdGame() {
		return this.idGame; 		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	//Setters
	
	public void setIdGame(long idGame) {
		this.idGame = idGame; 
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
}
