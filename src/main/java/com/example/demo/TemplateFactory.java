package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemplateFactory {

    public RestTemplate createSession() {
        return new RestTemplate();
    }

}