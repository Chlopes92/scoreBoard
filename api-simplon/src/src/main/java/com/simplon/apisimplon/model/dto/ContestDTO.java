package com.simplon.apisimplon.model.dto;

import java.sql.Date;

import com.simplon.apisimplon.model.Contest;

import lombok.Data;

@Data
public class ContestDTO {
    private Long id;
    private Date starDate;
    private String game;
    private String winner;

    public ContestDTO(Contest contest){
        this.id = contest.getId();
        this.starDate = contest.getStarDate();
        this.game = contest.getGame().getId() + " - " + contest.getGame().getTitle();
        // if(contest.getWinner() != null){
        //     this.winner = contest.getWinner().getNickname();
        // }else{
        //     this.winner = "pas de vainqueur";
        // }
        this.winner = contest.getWinner() != null ? contest.getWinner().getNickname() : "pas de vainqueur";
    }
}