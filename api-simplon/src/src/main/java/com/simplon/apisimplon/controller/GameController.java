package com.simplon.apisimplon.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.apisimplon.model.Game;
import com.simplon.apisimplon.model.dto.GameDTO;
import com.simplon.apisimplon.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    // public GameController() {
    //     this.gameService = new GameService();
    // }

    /**
     * GetMapping : permet de relier cette méthode 'allGames'
     *              à une URL qui sera appelée en méthode HTTP GET.
     * @return
     */
    // @GetMapping("/games")
    // public Iterable<Game> allGames() {
    //     return gameService.getAllGames();
    // }
     @GetMapping("/games")
    public Iterable<GameDTO> allGames() {
        Iterable<Game> games = gameService.getAllGames();
        java.util.List<GameDTO> gamesDTO = new ArrayList<GameDTO>();
        for(Game game : games){
            gamesDTO.add(new GameDTO(game));
        }
        return gamesDTO;
    }

    // @GetMapping("/game/{id}")
    // public Game game(@PathVariable("id") long id ) {
    //     Optional<Game> g = gameService.getGame(id);
    //     if( g.isPresent() ) {
    //         return g.get();  // la méthode get de l'objet Optional retourne un objet Game
    //     } else {
    //         return null;
    //     }
    // }

     @GetMapping("/game/{id}")
    public GameDTO game(@PathVariable("id") long id ) {
        Optional<Game> g = gameService.getGame(id);
        if( g.isPresent() ) {
            return new GameDTO(g.get());  // la méthode get de l'objet Optional retourne un objet Game
        } else {
            return null;
        }
    }

    /*
     * L'annotation RequestBody est utilisée pour récupérer les données
     * passées dans le corps de la requête HTTP.
     * En méthode HTTP POST, les données sont passées dans le corps de la
     * requête (alors qu'en GET, des données peuvent être passées dans l'URL).
     * 
     */
    // @PostMapping("/game")
    // public Game insertGame(@RequestBody Game g) {
    //     return gameService.saveGame(g);
    // }

    @PostMapping("/game")
    public GameDTO insertGame(@RequestBody Game g) {
        return new GameDTO(gameService.saveGame(g));
    }

    @DeleteMapping("/game/{id}")
    public boolean deleteGame(@PathVariable("id") long id) {
        /* EXO : vérifier si un game existe pour l'identifiant.
         * Si le game existe : supprimer le game et renvoyer true
         * sinon renvoyer false
         */
        Optional<Game> g = gameService.getGame(id);
        if( g.isPresent() ) {
            gameService.deleteGame(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/game/{id}")
    public GameDTO updateGame(@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> g = gameService.getGame(id);
        if(g.isPresent()) {
            Game gameToUpdate = g.get();
            if( game.getTitle() != null ) {
                gameToUpdate.setTitle(game.getTitle());
            }

            if( game.getMin() != null ) {
                gameToUpdate.setMin(game.getMin());
            }

            if( game.getMax() != null ) {
                gameToUpdate.setMax(game.getMax());
            }
            return  new GameDTO(gameService.saveGame(gameToUpdate));
        }
        return null;
    }





}