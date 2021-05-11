package com.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
	
	List<Word> findAll();
	
	Page<Word> findAll(Pageable pageable);
	
}
