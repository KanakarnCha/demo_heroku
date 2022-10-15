package com.example.BackendFinalProject.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.BackendFinalProject.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    public final  JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        String authorization = httpServlet.getHeader("Authorization");
        if (authorization == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(!authorization.startsWith("Bearer ")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String token = authorization.substring(7,authorization.length());
        DecodedJWT tokenDecoded = jwtService.verifyToken(token);
        if (tokenDecoded == null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String principal = String.valueOf(tokenDecoded.getClaim("principal"));
        String role = String.valueOf(tokenDecoded.getClaim("role"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,"(secret)",authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
