package com.example.appli.repository;

import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;

public class Repository {
    CustomProperties properties;

    String baseUrlApi;

    // public Repository(CustomProperties customProperties) {
    //     properties = customProperties;
    //     baseUrlApi = properties.getApiURL();
    //     restTemplate = new RestTemplate();
    // }

    // public Repository(){}

}