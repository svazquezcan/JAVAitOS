package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long> {

}
