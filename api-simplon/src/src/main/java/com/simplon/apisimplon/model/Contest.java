package com.simplon.apisimplon.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Contest {
    @Id /* clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto incrémentation */
    private Long id;

    @Column(name = "start_date")
    private Date starDate;

    // Many contest to One game = Plusieurs parties pour un jeu !
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game; // clé étrangère

    @ManyToOne
    private Player winner; // clé étrangère

    @ManyToMany(mappedBy = "contests")
    private List<Player> players;

}