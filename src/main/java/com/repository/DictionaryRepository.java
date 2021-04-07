package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
	
	List<Dictionary> findAll();
	
}
