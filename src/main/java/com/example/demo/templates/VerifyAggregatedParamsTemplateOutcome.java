package com.example.demo.templates;

import org.springframework.stereotype.Component;

@Component
public class VerifyAggregatedParamsTemplateOutcome extends FastLinkResponseTemplate {

    @Override
    protected String buildExtraParams() {
        return "flow=edit" + "delimiter" + "userExperienceFLow=blah";
    }

}