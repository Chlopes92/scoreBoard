package com.example.appli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Joueur;
import com.example.appli.model.Partie;
import com.example.appli.repository.PartieRepository;

import lombok.Data;

@Data
@Service
public class PartieService {
    @Autowired
    PartieRepository partieRepository;

    public Iterable<Partie> getParties() {
        return partieRepository.getAllParties();
    }

    public Partie getPartie(long id) {
        return partieRepository.getPartieById(id);
    }

    public Partie savePartie(String start_date, Integer game_id) {
        return partieRepository.addPartie(start_date, game_id);
    }

    public boolean removePartie(long id) {
        return partieRepository.deletePartie(id);
    }

    public Partie updatePartie(Partie partie) {
        return partieRepository.updatePartie(partie);
    }

    public Iterable<Partie> searchPartie(String word){
        return partieRepository.searchPartie(word);
    }

    public List<Joueur> getParticipants(Partie partie){
        return partieRepository.getPlayers(partie);
    }

     public Partie setVainqueur(Long idPartie, Long idJoueur) throws Exception{
        Partie p = partieRepository.getPartieById(idPartie);
        if(p != null){
            return partieRepository.setWinner(idPartie, idJoueur);
        }
        throw new Exception("La partie n°" + idPartie + " n'existe pas");

        // return partieRepository.setWinner(idPartie, idJoueur);
    }
    
}