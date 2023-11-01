package com.simplon.apisimplon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.apisimplon.model.Player;
import com.simplon.apisimplon.repository.PlayerRepository;

import lombok.Data;

@Data
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

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
}