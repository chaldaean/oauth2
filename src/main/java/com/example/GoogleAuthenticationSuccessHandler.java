package com.example;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserInfoTokenServices tokenServices;

    public GoogleAuthenticationSuccessHandler(UserInfoTokenServices tokenServices) {
        this.tokenServices=tokenServices;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String tokenValue = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
    }
}
