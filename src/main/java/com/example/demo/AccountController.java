package com.example.demo;

import com.example.demo.domain.requests.VerifyRequest;
import com.example.demo.facades.ExtraParamsFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    private ExtraParamsFacade facade;

    @GetMapping(value = "/startIav")
    public String startIav(
            @RequestParam(value = "client", required = false) String client,
            @RequestBody VerifyRequest request) {
        return facade.getIavParams(request, client);
    }

    @GetMapping(value = "/verifyAggregated")
    public String verifyAggregated(
            @RequestParam(value = "client", required = false) String client,
            @RequestBody VerifyRequest request) {
        return facade.getVerifyAggregatedParams(request, client);
    }

    @GetMapping(value = "/finishCdv")
    public String finishCdv(
            @RequestParam(value = "client", required = false) String client,
            @RequestBody VerifyRequest request) {
        return facade.getFinishCdvParams(request, client);
    }

}