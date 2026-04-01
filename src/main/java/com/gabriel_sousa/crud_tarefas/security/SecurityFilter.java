package com.gabriel_sousa.crud_tarefas.security;

import com.gabriel_sousa.crud_tarefas.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var tokenJwt = recoverToken(request);
        if(tokenJwt != null){
            setRouteAuthentication(tokenJwt);
            setRouteAttributes(tokenJwt, request);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private void setRouteAuthentication(String tokenJwt){
        var subject = tokenService.getSubject(tokenJwt);
        var user = userRepository.findByEmail(subject);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void setRouteAttributes(String tokenJwt, HttpServletRequest request){
        var userId = tokenService.getUserId(tokenJwt);
        request.setAttribute(RequestAttributeKey.USER_ID, userId);
    }
}
