package com.skolefun.config.jwt;

import com.skolefun.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_ROLES = "roles";

    private String secret = "N4eh7hJ8Bqi3iPaQ";

    private Long getUserIdFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return new Long((String) claims.get(CLAIM_KEY_USER_ID));
    }

    private String getUsernameFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return (String) claims.get(CLAIM_KEY_USERNAME);
    }

    private Set<Role> getRolesFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        final ArrayList<String> rolesAsList = (ArrayList<String>) claims.get(CLAIM_KEY_ROLES);
        return rolesAsList.stream().map(Role::valueOf).collect(Collectors.toSet());
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public boolean validateToken(String token) {
        return getClaimsFromToken(token) != null;
    }

    public String generateToken(JwtTokenUserData userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, userDetails.getUserId().toString());
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_ROLES, userDetails.getRoles());
        return generateToken(claims);
    }

    public JwtTokenUserData getTokenData(String authToken) {
        return new JwtTokenUserData(
                getUserIdFromToken(authToken),
                getUsernameFromToken(authToken),
                getRolesFromToken(authToken));
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
