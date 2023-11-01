package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.appli.model.Jeu;
import com.example.appli.model.Joueur;
import com.example.appli.service.JeuService;
import com.example.appli.service.JoueurService;

@Controller
@RequestMapping("/recherche")
public class RechercheController {
    @Autowired
    JeuService jeuService;    
    JoueurService joueurService;


    @GetMapping("/jeu")
    public String rechercheJeu(@RequestParam("search") String word, Model model){
        /* 
         * Dans l'annotation RequestParam, le 1er argument est le nom de la valeur
         * récupérer dans la requête HTTP (par exempel, le 'name' d'un input).
         * Si la variable liée (ici, word) a le même nom que la valeur récupérée (ici, search),
         * on n'a pas besoin de la préciser dans l'annotation.
         */
        Iterable<Jeu> jeux = jeuService.searchJeu(word);
        model.addAttribute("jeux", jeux); // On ajoute les résultats à la vue
        model.addAttribute("word", word);
        return "recherche/jeu";
    }

     @GetMapping("/joueur")
    public String rechercheJoueur(@RequestParam("search") String word, Model model){
        /* 
         * Dans l'annotation RequestParam, le 1er argument est le nom de la valeur
         * récupérer dans la requête HTTP (par exempel, le 'name' d'un input).
         * Si la variable liée (ici, word) a le même nom que la valeur récupérée (ici, search),
         * on n'a pas besoin de la préciser dans l'annotation.
         */
        Iterable<Joueur> joueurs = joueurService.searchJoueur(word);
        model.addAttribute("joueurs", joueurs); // On ajoute les résultats à la vue
        model.addAttribute("word", word);
        return "recherche/joueur";
    }
}