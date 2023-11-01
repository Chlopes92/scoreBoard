package com.example.appli.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Jeu;

@Component
public class JeuRepository extends Repository{

    public JeuRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
    }

    // public JeuRepository(CustomProperties customProperties){
    //     super(customProperties);
    // }

    public Iterable<Jeu> getAllJeux() {
        // String baseUrlApi = properties.getApiURL();
        String getGameUrl = baseUrlApi + "/games";

        /**
         * L'objet de la classe RestTemplate fait des requêtes HTTP et 
         * convertit le JSON retourné par la requête en objet JAVA.
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Jeu>> response = restTemplate.exchange(
            getGameUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Jeu>>() {}
        );

        return response.getBody();
    }

    public Jeu getJeuById(long id) {
        String getGameUrl = baseUrlApi + "/game/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Jeu> response = restTemplate.exchange(
            getGameUrl,
            HttpMethod.GET,
            null,
            Jeu.class
        );
        return response.getBody();
    }

    public Jeu addJeu(Jeu e) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Jeu> request = new HttpEntity<Jeu>(e);
        ResponseEntity<Jeu> response = restTemplate.exchange(
            baseUrlApi + "/game",
            HttpMethod.POST,
            request,
            Jeu.class
        );
        return response.getBody();
    }

    public boolean deleteJeu(long id) {
        RestTemplate r = new RestTemplate();
        ResponseEntity<Boolean> response = r.exchange(
            baseUrlApi + "/game/" + id,
            HttpMethod.DELETE,
            null,
            Boolean.class
        );
        return response.getBody();
    }

    public Jeu updateJeu(Jeu j){
        String url = this.baseUrlApi + "/game/" + j.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Jeu> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<Jeu>(j),
            Jeu.class
        );
        return response.getBody();
    }

    public Iterable<Jeu> searchJeu(String word){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Jeu>> response = restTemplate.exchange(
            baseUrlApi + "/game/search?word=" + word,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Jeu>>(){}
        );
        return response.getBody();
    }
}