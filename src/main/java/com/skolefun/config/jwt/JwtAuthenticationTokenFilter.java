package com.skolefun.config.jwt;

import com.skolefun.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authentication");

        if (authToken != null) {
            if (jwtTokenUtil.validateToken(authToken)) {
                JwtTokenUserData jwtTokenUserData = jwtTokenUtil.getTokenData(authToken);
                Collection<GrantedAuthority> authorities = jwtTokenUserData.getRoles().stream().map(Role::toSpringGrantedAuthority).collect(Collectors.toSet());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtTokenUserData, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
            }
        }
        filterChain.doFilter(request, response);
    }
}
