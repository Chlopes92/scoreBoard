package com.simplon.apisimplon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplon.apisimplon.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    
}