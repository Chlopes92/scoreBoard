package com.example.appli.model;

import java.util.List;

import lombok.Data;

@Data
public class Jeu {
    private Long id;

    private String title;

    private Integer min;

    private Integer max;

    private List<String> contests;
}