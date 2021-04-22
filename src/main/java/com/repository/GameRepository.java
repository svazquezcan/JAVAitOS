package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	
	List<Game> findByDuration(Long duration);

}
