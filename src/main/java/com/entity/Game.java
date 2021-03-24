package com.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Game {
	
	@Id
	private Long id;
	
	private String username;
	
	private Long duration;
	

}
