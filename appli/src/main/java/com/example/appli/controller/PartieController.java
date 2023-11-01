package com.example.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.appli.model.Jeu;
import com.example.appli.model.Joueur;
import com.example.appli.model.Partie;
import com.example.appli.service.JeuService;
import com.example.appli.service.PartieService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class PartieController {
    @Autowired
    PartieService partieService;
    @Autowired
    JeuService jeuService;

    @GetMapping("/parties")
    public String parties(Model model) {
        Iterable<Partie> parties = partieService.getParties();
        model.addAttribute("parties", parties);
        return "partie/liste";
    }

    @GetMapping("/partie/{id}/fiche")
    public String fiche(@PathVariable("id") long id, Model model) {
        Partie p = partieService.getPartie(id);
        model.addAttribute("partie", p);
        return "partie/fiche";
    }

    /**
     * Afficher le formulaire
     */
    @GetMapping("/partie/ajouter")
     public String ajouter(Model model) {
        Iterable<Jeu> jeux = jeuService.getJeux();
        model.addAttribute("jeux", jeux);
        Partie partie = new Partie();
        model.addAttribute("partie", partie);
        return "partie/form";
    }

    @PostMapping(value="/partie/ajouter")
    public ModelAndView ajouter(@RequestParam String start_date, 
                                @RequestParam Integer game_id) {
            partieService.savePartie(start_date, game_id);
        return new ModelAndView("redirect:/parties");
    }

    /********************************************************** */
    /* CHOISIR LE VAINQUEUR D'UNE PARTIE */
    @GetMapping("/partie/{id}/choisir-vainqueur")
    public String choisir(@PathVariable Long id, Model model){
        Partie partie = partieService.getPartie(id);
        List<Joueur> joueurs = partieService.getParticipants(partie);
        model.addAttribute("participants", joueurs);
        model.addAttribute("partie", partie);
        return "/partie/form_vainqueur";
    }

    @PostMapping("/partie/{id}/choisir-vainqueur")
    public ModelAndView choisir(@PathVariable Long id, @RequestParam Long joueur_id){
        try {
            partieService.setVainqueur(id, joueur_id);

        } catch (Exception e) {
            return new ModelAndView("redirect:/erreur/entite");
            // throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Cette partie n'existe pas");
        }

        return new ModelAndView("redirect:/parties");
    }


    // @GetMapping("/partie/{id}/supprimer")
    // public String supprimer(@PathVariable("id") long id, Model model) {
    //     Partie p = partieService.getPartie(id);
    //     model.addAttribute("partie", p);
    //     return "partie/confirmation";
    // }

    // @PostMapping("/partie/{id}/supprimer")
    // public ModelAndView supprimer(@PathVariable("id") long id) {
    //     partieService.removePartie(id);
    //     return new ModelAndView("redirect:/parties");

    // }

    // //--------------------------------ACTUALISER-----------------// 
    // @GetMapping("/joueur/{id}/modifier")
    //     public String modifier(@PathVariable Long id, Model model){
    //         Partie p = partieService.getPartie(id);
    //         model.addAttribute("partie", p);
    //         return "partie/form";

    //     }
    
    // @PostMapping(value="/partie/{id}/modifier")
    //     public ModelAndView modifier(@PathVariable Long id, @ModelAttribute Partie p){
    //         p.setId(id);
    //         partieService.updatePartie(p);
    //         return new ModelAndView("redirect:/parties");
    //     }


    
}