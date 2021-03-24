package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	List<Game> findByDuration(Long duration);

}
