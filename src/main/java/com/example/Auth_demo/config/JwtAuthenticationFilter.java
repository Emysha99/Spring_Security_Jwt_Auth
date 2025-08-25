package com.example.Auth_demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.Auth_demo.service.JwtUserDetailsService;
import java.io.IOException;

//OncePerRequestFilter guarantees the filter runs only once per request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, JwtUserDetailsService uds){
        this.jwtUtils = jwtUtils;
        this.userDetailsService = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUserNameFromJwtToken(token);
                UserDetails ud = userDetailsService.loadUserByUsername(username);
//                create authentication object with user ,credentials (null since password not needed anymore) and authorities (roles from DB)
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        ud, null, ud.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                // saves the authenticated user into the Spring Security context
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
//        Passes the request to the controller
        chain.doFilter(req, res);
    }
}
