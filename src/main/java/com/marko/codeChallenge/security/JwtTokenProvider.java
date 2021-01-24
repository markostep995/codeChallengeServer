package com.marko.codeChallenge.security;

import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.payload.JWTLoginSuccessResponse;
import com.marko.codeChallenge.payload.RefreshTokenRequest;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {


    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, SecurityConstants.EXPIRATION_TIME_REFRESH_TOKEN);
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, SecurityConstants.EXPIRATION_TIME);
    }

    private String generateToken(Authentication authentication, long expirationTime) {

        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + expirationTime);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());


        return Jwts.builder().setSubject(userId).setClaims(claims)
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }

    public JWTLoginSuccessResponse refreshToken(RefreshTokenRequest request) {
        JWTLoginSuccessResponse response = new JWTLoginSuccessResponse();
        response.setSuccess(false);
        response.setRefreshToken("");
        response.setToken("");

        if (!validateToken(request.getRefreshToken())) {
            return response;
        }

        String plainAccessToken = extractToken(request.getToken());
        if (!validateToken(plainAccessToken)) {
            return response;
        }

        String extendedAccessToken = extendToken(plainAccessToken, SecurityConstants.EXPIRATION_TIME);
        String accessTokenWithPrefix = packageAccessToken(extendedAccessToken);

        response.setToken(accessTokenWithPrefix);
        response.setRefreshToken(extendToken(request.getRefreshToken(), SecurityConstants.EXPIRATION_TIME_REFRESH_TOKEN));

        response.setSuccess(true);
        return response;
    }

    private String extractToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(SecurityConstants.TOKEN_PREFIX.length(), bearerToken.length());
        }
        return null;
    }

    private String packageAccessToken(String token) {
        return SecurityConstants.TOKEN_PREFIX + token;
    }

    private String extendToken(String jwt, long time) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + time);
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(jwt).getBody();

        return Jwts.builder().setSubject(claims.getSubject()).setClaims(claims)
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalit JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalit JWT Token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}

