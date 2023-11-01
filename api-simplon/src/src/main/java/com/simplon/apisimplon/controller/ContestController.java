package com.simplon.apisimplon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.apisimplon.model.Contest;
import com.simplon.apisimplon.model.dto.ContestDTO;
import com.simplon.apisimplon.service.ContestService;
import com.simplon.apisimplon.service.GameService;
import com.simplon.apisimplon.service.PlayerService;

// @RestController
// public class ContestController {

//     @Autowired
//     private ContestService contestService;

//     //Ajout du game et player service
//     @Autowired
//     private GameService gameService;

//     @Autowired
//     private com.simplon.apisimplon.service.PlayerService PlayerService;

//     @GetMapping("/contests")
//     public Iterable<Contest> allContests() {
//         return contestService.getAllContests();
//     }

//     @GetMapping("/contest/{id}")
//     public Contest contest(@PathVariable("id") long id ) {
//         Optional<Contest> c = contestService.getContest(id);
//         if( c.isPresent() ) {
//             return c.get();  // la méthode get de l'objet Optional retourne un objet Contest
//         } else {
//             return null;
//         }
//     }

//     // @PostMapping("/contest")
//     // public Contest insertContest(@RequestBody Contest c) {
//     //     return contestService.saveContest(c);
//     // }

//     @PostMapping("/contest")
//     public ContestDTO insertContest(@RequestParam String start_date, @RequestParam int game_id, @RequestParam Integer winner_id){
//         Contest contest = new Contest();
//         contest.setStarDate(java.sql.Date.valueOf(start_date));
//         contest.setGame(gameService.getGame(game_id).get());
//         if(winner_id != null){
//             contest.setWinner(PlayerService.getPlayer(winner_id).get());
//         }

//         return new ContestDTO(contestService.saveContest(contest));
//     }

//     @DeleteMapping("/contest/{id}")
//     public boolean deleteContest(@PathVariable("id") long id) {
//         Optional<Contest> c = contestService.getContest(id);
//             if( c.isPresent() ) {
//                 contestService.deleteContest(id);
//                 return true;
//             } else {
//                 return false;
//             }
//     }

//     @PutMapping("/contest/{id}") 
//     public Contest updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
        
//     Optional<Contest> optionalContest = contestService.getContest(id);
    
//         if(optionalContest.isPresent()) {
//         contest.setId(id); 

//         return contestService.saveContest(contest);
//     }
//     return null;
// }

// @RestController
// public class ContestController {

//     @Autowired
//     private ContestService contestService;

//     @Autowired
//     private GameService gameService;

//     @Autowired
//     private PlayerService playerService;

//     @GetMapping("/contests")
//     public Iterable<ContestDTO> allContests() {
//         Iterable<Contest> contests = contestService.getAllContests();
//         List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
//         for(Contest contest : contests) {
//             contestsDTO.add(new ContestDTO(contest));
//         }
//         return contestsDTO;
//     }

//     @GetMapping("/contest/{id}")
//     public ContestDTO contest(@PathVariable("id") long id ) {
//         Optional<Contest> g = contestService.getContest(id);
//         if( g.isPresent() ) {
//             return new ContestDTO(g.get());  // la méthode get de l'objet Optional retourne un objet Contest
//         } else {
//             return null;
//         }
//     }
//     /*
//      * L'annotation RequestBody est utilisée pour récupérer les données
//      * passées dans le corps de la requête HTTP.
//      * En méthode HTTP POST, les données sont passées dans le corps de la
//      * requête (alors qu'en GET, des données peuvent être passées dans l'URL).
//      * 
//      */
//     @PostMapping("/contest")
//     public ContestDTO insertContest(@RequestParam String start_date, @RequestParam int game_id, @RequestParam Integer winner_id) {
//         Contest contest = new Contest();
//        contest.setStarDate(java.sql.Date.valueOf(start_date));
//         contest.setGame( gameService.getGame(game_id).get() );
//         if( winner_id != null) {
//             contest.setWinner( playerService.getPlayer(winner_id).get() );
//         }

//         return new ContestDTO(contestService.saveContest(contest));
//     }

//     @DeleteMapping("/contest/{id}")
//     public boolean deleteContest(@PathVariable("id") long id) {
//         /* EXO : vérifier si un contest existe pour l'identifiant.
//          * Si le contest existe : supprimer le contest et renvoyer true
//          * sinon renvoyer false
//          */
//         Optional<Contest> g = contestService.getContest(id);
//         if( g.isPresent() ) {
//             contestService.deleteContest(id);
//             return true;
//         } else {
//             return false;
//         }
//     }

//     @PutMapping("/contest/{id}")
//     public Contest updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
//         Optional<Contest> g = contestService.getContest(id);
//         if(g.isPresent()) {
//             contest.setId(id);
            
//             return contestService.saveContest(contest);
//         }
//         return null;
//     }
    
// }

@RestController
public class ContestController {
    @Autowired
    private ContestService contestService;

    @Autowired
    private GameService GameService;

    @Autowired
    private PlayerService playerService;


    //Retourne tout les jeux sur le chemin http://localhost:9000/contests
    @GetMapping("/contests")
    public Iterable<ContestDTO> allContests() {
        Iterable<Contest> contests =  contestService.getAllContests();
        List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
        for(Contest contest : contests) {
            contestsDTO.add(new ContestDTO(contest));
        }
        return contestsDTO;
    }


    //Retourne un jeux (si il y en a un) sur le chemin http://localhost:9000/contest/(id)
    @GetMapping("/contest/{id}")
    public ContestDTO contest(@PathVariable("id") int id ) {
        // Je récupère le jeu
       Optional<Contest> g = contestService.getContest(id); 
       // Contest est otionnel donc on doit faire un if pour les différents cas de figure
       if(g.isPresent()) {
        return new ContestDTO(g.get());
       } else {
        return null;
       }
    } 

    
    // Ajoute un jeu
    @PostMapping("/contest")
    public ContestDTO insertContest(@RequestParam String start_date, @RequestParam int game_id, @RequestParam Integer winner_id) {
        Contest contest = new Contest();
        // Value of est une fonction statique de la classe Date
        contest.setStarDate(java.sql.Date.valueOf(start_date));
        contest.setGame(GameService.getGame(game_id).get());
        if(winner_id != null) {
            contest.setWinner(playerService.getPlayer(winner_id).get());
        }

        return new ContestDTO(contestService.saveContest(contest));
    }


    // Supprime un jeu
    @DeleteMapping("/contest/{id}")
    public boolean deleteContest(@PathVariable("id") long id) {
        // Je récupère le jeu pour vérifier si il existe
        Optional<Contest> g = contestService.getContest(id);
        if(g.isPresent()) {
            contestService.deleteContest(id);
            return true;
        } else {
            return false;
        }
    }


    // Modifie un jeu 
    @PutMapping("/contest/{id}")
    public ContestDTO updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
        Optional<Contest> g = contestService.getContest(id);
        if(g.isPresent()) {
            contest.setId(id);
            
            return new ContestDTO(contestService.saveContest(contest));
        }
        return null;
    }
}