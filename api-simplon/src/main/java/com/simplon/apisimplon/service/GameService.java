package com.simplon.apisimplon.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.EntityManager;

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

    @Autowired
    private EntityManager entityManager;

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

    /**
     * SELECT g.* FROM game WHERE g.title LIKE "%a%"
     */
    public List<Game> searchTitle(String word) {
        String sql = "SELECT new " + Game.class.getName() + "(g.id, g.title, g.min, g.max)" 
                        + " FROM " + Game.class.getName() + " g"
                        + " WHERE g.title LIKE '%" + word + "%'";
        Query query = entityManager.createQuery(sql, Game.class);
        List<Game> liste = new ArrayList<Game>();
        liste = query.getResultList();
        return liste;
    }
}