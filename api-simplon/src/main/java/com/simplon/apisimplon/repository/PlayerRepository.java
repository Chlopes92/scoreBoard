package com.simplon.apisimplon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplon.apisimplon.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
    
}