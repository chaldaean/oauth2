package com.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.ClientTokenServices;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CustomOAuth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {
    private ClientTokenServices clientTokenServices;

    public CustomOAuth2ClientAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ClientTokenServices clientTokenServices) {
        super(defaultFilterProcessesUrl);
        Objects.requireNonNull(clientTokenServices);
        this.clientTokenServices = clientTokenServices;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        if (clientTokenServices != null) {
            clientTokenServices.saveAccessToken(restTemplate.getResource(), SecurityContextHolder.getContext().getAuthentication(), restTemplate.getAccessToken());
        }
    }
}
