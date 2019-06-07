package com.example.demo.facades;

import com.example.demo.templates.FinishCdvParamsTemplateOutcome;
import com.example.demo.templates.StartIavParamsTemplateOutcome;
import com.example.demo.templates.VerifyAggregatedParamsTemplateOutcome;
import com.example.demo.domain.requests.VerifyRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExtraParamsFacade {

    @Resource
    private FinishCdvParamsTemplateOutcome finishCdvParamsTemplateOutcome;

    @Resource
    private StartIavParamsTemplateOutcome startIavParamsTemplateOutcome;

    @Resource
    private VerifyAggregatedParamsTemplateOutcome verifyAggregatedParamsTemplateOutcome;


    public String getIavParams(VerifyRequest request, String client) {
        return startIavParamsTemplateOutcome.buildParams(request, client);
    }

    public String getFinishCdvParams(VerifyRequest request, String client) {
        return finishCdvParamsTemplateOutcome.buildParams(request, client);
    }

    public String getVerifyAggregatedParams(VerifyRequest request, String client) {
        return verifyAggregatedParamsTemplateOutcome.buildParams(request, client);
    }

}