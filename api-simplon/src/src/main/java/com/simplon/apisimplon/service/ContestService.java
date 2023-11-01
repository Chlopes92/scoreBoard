package com.simplon.apisimplon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apisimplon.model.Contest;
import com.simplon.apisimplon.repository.ContestRepository;

import lombok.Data;

@Data
@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    /**
    * Récupérer toutes les parties
    */
    public Iterable<Contest> getAllContests(){
        return contestRepository.findAll();
    }
    
    /**
     * Récupérer une partie avec son id
     */
    public Optional<Contest> getContest(final long id){
        return contestRepository.findById(id);
    }

    /**
     * Ajouter/modifier une partie
     */
    public Contest saveContest(Contest c){
        return this.contestRepository.save(c);
    }

    /**
     * Supprimer une partie
     */
    public void deleteContest (final long id) {
        this.contestRepository.deleteById(id);
    }
    
}