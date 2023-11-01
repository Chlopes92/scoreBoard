package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.appli.service.JeuService;
import com.example.appli.model.Jeu;
import com.example.appli.model.Joueur;

@Controller
// @RequestMapping("/jeux")
public class JeuController {
    @Autowired
    JeuService jeuService;

    @GetMapping("/jeux")
    public String jeux(Model model) {
        Iterable<Jeu> jeux = jeuService.getJeux();
        model.addAttribute("jeux", jeux);
        return "jeu/liste";
    }


    @GetMapping("/jeu/{id}/fiche")
    public String fiche(@PathVariable("id") long id, Model model) {
        Jeu j = jeuService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeu/fiche";
    }

    //----------------------------------AJOUTER
    @GetMapping("/jeu/ajouter")
    public String ajouter(Model model) {
        Jeu jeu = new Jeu();
        model.addAttribute("jeu", jeu);
        return "jeu/form";
    }

    @PostMapping("/jeu/ajouter")
    public ModelAndView sauvegarder(@ModelAttribute Jeu jeu){
        jeuService.saveJeu(jeu);
        return new ModelAndView("redirect:/jeux");
    }

    //----------------------------------SUPPRIMER
    @GetMapping("/jeu/{id}/supprimer")
    public String supprimer(@PathVariable("id") long id, Model model) {
        Jeu j = jeuService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeu/confirmation";
    }

    @PostMapping("/jeu/{id}/supprimer")
    public ModelAndView supprimer(@PathVariable("id") long id) {
        jeuService.removeJeu(id);
        return new ModelAndView("redirect:/jeux");
    }
    
    //---------------------------------- ACTUALISER 
    @GetMapping("/jeu/{id}/modifier")
    public String modifier(@PathVariable Long id, Model model) {
        Jeu j = jeuService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeu/form";
    }

    @PostMapping(value="/jeu/{id}/modifier")
    public ModelAndView modifier(@PathVariable Long id, @ModelAttribute Jeu j) {
        // j.setId(id);
        jeuService.updateJeu(j);
        return new ModelAndView("redirect:/jeux");
    }

}