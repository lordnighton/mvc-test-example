package com.example.demo.templates;

import com.example.demo.domain.requests.VerifyRequest;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.validator.routines.UrlValidator.ALLOW_LOCAL_URLS;
import static org.springframework.util.Assert.isTrue;

public abstract class FastLinkResponseTemplate {

    private static final String CALLBACK_URL_FORMAT_IS_INVALID = "Callback URL format is invalid";

    private static final String LOCATION_URL_FORMAT_IS_INVALID = "Location URL format is invalid";

    private UrlValidator URL_VALIDATOR = new UrlValidator(ALLOW_LOCAL_URLS);

    public String buildParams(final VerifyRequest request, final String client) {
        return buildPostmessageParam(request, client) + buildExtraParams();
    }

    private String buildPostmessageParam(VerifyRequest request, String client) {
        switch (Optional.ofNullable(client).orElse(EMPTY)) {
            case EMPTY:
            case "web":
                validateUrlParam(request.getLocationUrl(), LOCATION_URL_FORMAT_IS_INVALID);
                return "locationurl=" + request.getLocationUrl();
            case "mobile":
                validateUrlParam(request.getCallbackUrl(), CALLBACK_URL_FORMAT_IS_INVALID);
                return "callback=" + request.getCallbackUrl();
            default:
                throw new IllegalArgumentException("Could NOT resolve client type");
        }
    }

    private void validateUrlParam(final String urlParam, final String errorMsg) {
        isTrue(URL_VALIDATOR.isValid(urlParam), errorMsg);
    }

    protected abstract String buildExtraParams();

}
