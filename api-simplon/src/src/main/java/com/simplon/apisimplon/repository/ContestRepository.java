package com.simplon.apisimplon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplon.apisimplon.model.Contest;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long>{
    
}