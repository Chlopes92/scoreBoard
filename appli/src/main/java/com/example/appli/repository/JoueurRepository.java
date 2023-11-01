package com.example.appli.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Jeu;
import com.example.appli.model.Joueur;

@Component
public class JoueurRepository {
    CustomProperties properties;

    String baseUrlApi;

    public JoueurRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
    }

    public Iterable<Joueur> getAllJoueurs() {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/players";

        /**
         * L'objet de la classe RestTemplate fait des requêtes HTTP et 
         * convertit le JSON retourné par la requête en objet JAVA.
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Joueur>> response = restTemplate.exchange(
            getPlayerUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Joueur>>() {}
        );

        return response.getBody();
    }

    public Joueur getJoueurById(long id) {
        String getPlayerUrl = baseUrlApi + "/player/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joueur> response = restTemplate.exchange(
            getPlayerUrl,
            HttpMethod.GET,
            null,
            Joueur.class
        );
        return response.getBody();
    }

    public Joueur addJoueur(Joueur e) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Joueur> request = new HttpEntity<Joueur>(e);
        ResponseEntity<Joueur> response = restTemplate.exchange(
            baseUrlApi + "/player",
            HttpMethod.POST,
            request,
            Joueur.class
        );
        return response.getBody();
    }

    public boolean deleteJoueur(long id) {
        RestTemplate r = new RestTemplate();
        ResponseEntity<Boolean> response = r.exchange(
            baseUrlApi + "/player/" + id,
            HttpMethod.DELETE,
            null,
            Boolean.class
        );
        return response.getBody();
    }

    public Joueur updateJoueur(Joueur j){
        String url = this.baseUrlApi + "/player/" + j.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joueur> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<Joueur>(j),
            Joueur.class
        );
        return response.getBody();
    }

    public Iterable<Joueur> searchJoueur(String word){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Joueur>> response = restTemplate.exchange(
            baseUrlApi + "/player/search?word=" + word,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Joueur>>(){}
        );
        return response.getBody();
    }
}