package com.jsc.oauth.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

import java.util.Map;

/**
 * The type Custom oauth 2 request factory.
 */
public class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {


    /**
     * Instantiates a new Custom oauth 2 request factory.
     *
     * @param clientDetailsService the client details service
     */
    public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }


    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters,
                                           ClientDetails authenticatedClient) {
        return super.createTokenRequest(requestParameters, authenticatedClient);
    }
}
