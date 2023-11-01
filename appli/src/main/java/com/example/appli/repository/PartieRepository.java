package com.example.appli.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Joueur;
import com.example.appli.model.Partie;

@Component
public class PartieRepository extends Repository{
    // public PartieRepository(CustomProperties customProperties){
    //     super(customProperties);
    // }

    public PartieRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
    }


    public Iterable<Partie> getAllParties() {
        // String baseUrlApi = properties.getApiURL();
        String getContestUrl = baseUrlApi + "/contests";

        /**
         * L'objet de la classe RestTemplate fait des requêtes HTTP et 
         * convertit le JSON retourné par la requête en objet JAVA.
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Partie>> response = restTemplate.exchange(
            getContestUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Partie>>() {}
        );

        return response.getBody();
    }

    public Partie getPartieById(long id) {
        String getContestUrl = baseUrlApi + "/contest/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Partie> response = restTemplate.exchange(
            getContestUrl,
            HttpMethod.GET,
            null,
            Partie.class
        );
        return response.getBody();
    }

    public Partie addPartie(String start_date, Integer game_id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("start_date", start_date);
        map.add("game_id", game_id.toString());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<Partie> response = restTemplate.exchange(
            baseUrlApi + "/contest",
            HttpMethod.POST,
            request,
            Partie.class
        );
        return response.getBody();
    }

    public boolean deletePartie(long id) {
        RestTemplate r = new RestTemplate();
        ResponseEntity<Boolean> response = r.exchange(
            baseUrlApi + "/contest/" + id,
            HttpMethod.DELETE,
            null,
            Boolean.class
        );
        return response.getBody();
    }

    public Partie updatePartie(Partie p){
        String url = this.baseUrlApi + "/contest/" + p.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Partie> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<Partie>(p),
            Partie.class
        );
        return response.getBody();
    }

    public Iterable<Partie> searchPartie(String word){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Partie>> response = restTemplate.exchange(
            baseUrlApi + "/contest/search?word=" + word,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Partie>>(){}
        );
        return response.getBody();
    }

    public List<Joueur> getPlayers(Partie p){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Joueur>> response = restTemplate.exchange(
            baseUrlApi + "/contest/" + p.getId() + "/players",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Joueur>>() {}
            );
        return response.getBody();
    }

    public Partie setWinner(long idPartie, long idJoueur) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Partie> response = restTemplate.exchange(
            baseUrlApi + "/contest/" + idPartie + "/" + idJoueur,
            HttpMethod.PUT,
            null,
            Partie.class
        );
        return response.getBody();
    }
   
}