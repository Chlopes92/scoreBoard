package com.example.appli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/erreur")
public class ErreurController {
    @GetMapping("/418")
    public String erreur418() {
        return "erreur/418";
    }
    
    
}