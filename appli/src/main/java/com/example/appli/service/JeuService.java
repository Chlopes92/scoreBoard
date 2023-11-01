package com.example.appli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Jeu;
import com.example.appli.repository.JeuRepository;

import lombok.Data;

@Data
@Service
public class JeuService {
    @Autowired
    JeuRepository jeuRepository;
    
    public Iterable<Jeu> getJeux() {
        return jeuRepository.getAllJeux();
    }

    public Jeu getJeu(long id) {
        return jeuRepository.getJeuById(id);
    }

    public Jeu saveJeu(Jeu jeu) {
        return jeuRepository.addJeu(jeu);
    }

    public boolean removeJeu(long id) {
        return jeuRepository.deleteJeu(id);
    }

    public Jeu updateJeu(Jeu jeu) {
        return jeuRepository.updateJeu(jeu);
    }

    public Iterable<Jeu> searchJeu(String word){
        return jeuRepository.searchJeu(word);
    }
}