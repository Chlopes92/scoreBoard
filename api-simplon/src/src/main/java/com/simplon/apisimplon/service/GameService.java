package com.simplon.apisimplon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apisimplon.model.Game;
import com.simplon.apisimplon.repository.GameRepository;

import lombok.Data;

@Data
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    /**
     * Récupérer tous les jeux
     */
    public Iterable<Game> getAllGames(){
        return gameRepository.findAll();
    }
    
    /**
     * Récupérer un jeux avec son id
     */
    public Optional<Game> getGame(final long id){
        return gameRepository.findById(id);
    }

    /**
     * Ajouter/modifier un jeu
     */
    public Game saveGame(Game g){
        return this.gameRepository.save(g);
    }

    /**
     * Supprimer un jeu
     */
    public void deleteGame (final long id) {
        this.gameRepository.deleteById(id);
    }
}