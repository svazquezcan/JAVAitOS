package com.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Dictionary {
	
	@Id
	private long idDictionary;
	
	private String topic;
		
	//Constructor

	public Dictionary (long idDictionary, String topic){
		this.idDictionary = idDictionary;
		this.topic = topic;
	}
	
	//Getters

	public long getIdDictionary() {
		return this.idDictionary; 		
	}
	
	public String getTopic() {
		return this.topic;
	}
	
	//Setters
	
	public void setIdDictionary(long idDictionary) {
		this.idDictionary = idDictionary; 
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
		
	