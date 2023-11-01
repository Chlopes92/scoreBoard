package com.simplon.apisimplon.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.simplon.apisimplon.model.Contest;
import com.simplon.apisimplon.model.Player;

import lombok.Data;

@Data
public class ContestDTO {
    private Long id;
    private Date startDate;
    private String game;
    private String winner;
    /* EXERCICE : le ContestDTO doit contenir la liste des players participants.
     *            C'est une liste de String qui ressemble à id - nickname (email)
     */
    private List<String> players;

    public ContestDTO(Contest contest){
        this.id = contest.getId();
        this.startDate = contest.getStartDate();
        this.game = contest.getGame().getId() + " - " + contest.getGame().getTitle();
        // if(contest.getWinner() != null){
        //     this.winner = contest.getWinner().getNickname();
        // }else{
        //     this.winner = "pas de vainqueur";
        // }
        this.winner = contest.getWinner() != null ? contest.getWinner().getNickname() : "pas de vainqueur";
        List<String> listePlayers = new ArrayList<String>();
            if(contest.getPlayers() != null){
                for(Player player: contest.getPlayers()){
                    listePlayers.add(player.getId() + " - " + player.getNickname() + " (" + player.getEmail() + ")");
            }
            this.players = listePlayers;
    }
    
}
}
 
