package com.simplon.apisimplon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data /*permet de ne pas ajouter les getters dans la classe */
@Entity /* cette classe à le rôle d'une entité */
public class Game {
    @Id /* clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto incrémentation */
    private Long id;

    private String title;

    private Integer min;

    private Integer max;

    @OneToMany(mappedBy = "game") // correspond au champs game dans l'Entité contest
    private List<Contest> contests;

}