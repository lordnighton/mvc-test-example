package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class AccountService {

    @Resource
    private TemplateFactory templateFactory;

    public String getAccount() {
        RestTemplate restTemplate = templateFactory.createSession();

        return restTemplate.getForEntity("google.com", String.class).getBody();
    }

}
