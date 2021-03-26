package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	
	List<Word> findAll();
	Word findById();
	
	Word insertWord(Word word);
	Word updateWord(Word word);
	boolean deleteWord(Word word);
}
