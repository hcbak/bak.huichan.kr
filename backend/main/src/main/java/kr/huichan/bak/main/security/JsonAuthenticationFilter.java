package kr.huichan.bak.main.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.huichan.bak.main.dto.SignInRequest;

public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JsonAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/auth/sign-in", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        
        SignInRequest signInRequest = new ObjectMapper().readValue(request.getInputStream(), SignInRequest.class);

        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password()));
    }
}
