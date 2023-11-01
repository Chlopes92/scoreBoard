package com.simplon.apisimplon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apisimplon.model.Game;
import com.simplon.apisimplon.model.Player;
import com.simplon.apisimplon.repository.PlayerRepository;

import lombok.Data;

@Data
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * Récupérer tous les joueurs
     */
    public Iterable<Player> getAllPlayers(){
        return playerRepository.findAll();
    }
    
    /**
     * Récupérer un joueur avec son id
     */
    public Optional<Player> getPlayer(final long id){
        return playerRepository.findById(id);
    }

    /**
     * Ajouter/modifier un joueur
     */
    public Player savePlayer(Player p){
        return this.playerRepository.save(p);
    }

    /**
     * Supprimer un joueur
     */
    public void deletePlayer (final long id) {
        this.playerRepository.deleteById(id);
    }

    public List<Player> searchNamePlayers(String word) {
        String sql = "SELECT new " + Player.class.getName() + "(p.id, p.nickname, p.email)" 
                        + " FROM " + Player.class.getName() + " p"
                        + " WHERE p.nickname LIKE '%" + word + "%'";
        Query query = entityManager.createQuery(sql, Player.class);
        List<Player> liste = new ArrayList<Player>();
        liste = query.getResultList();
        return liste;
    }
}