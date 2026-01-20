package com.marianadev.task_manager.security.jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;

/**
* JwtService handles JWT generation and validation.
* Secret and expiration are loaded from application.properties
*/
@Service
@AllArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    /**
     * Generate a JWT token for a given user.
     * - Subject: username
     * - Extra claims: extraClaims
     * - Expiration: current time + jwtProperties.expiration (ms)
     * - Sign With: HS256 key (jwtProperties.secret)
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user){
       return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ getExpiration()))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Return the HS256 key used for signing and verifying tokens
     * @return HS256 key (jwtProperties.secret)
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = getSecret().getBytes(StandardCharsets.UTF_8);
       return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Get token Expiration time (ms) from jwtProperties.expiration
     * @return Expiration time (ms)
     */
    public long getExpiration(){
        return jwtProperties.getExpiration();
    }

    /**
     * Get Secret Key from jwtProperties.secret
     * @return Secret Key
     */
    public String getSecret(){
        return jwtProperties.getSecret();
    }

    /**
     * Get all claims in token
     * @param token string
     * @return Claims
     */
    private Claims getAllClaims(String token){
        return Jwts.parser()
        .decryptWith(getSigningKey())
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }

    /**
     * General method to get an specific Claim
     * @param <T> 
     * @param token string token 
     * @param claimsResolver 
     * @return value of specific Claim
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get expiration token from token
     * @param token string
     * @return Expiration Date from token
     */
    private Date getExpirationToken(String token){
        return getClaim(token, Claims:: getExpiration);
    }

    /**
     * get Username from Token 
     * @param token string
     * @return username
     */
    public String getUserNameFromToken(String token){
       return getClaim(token, Claims:: getSubject);

    }

    /**
     * Verify if token has expired
     * @param token string
     * @return true or false
     */
    private boolean isTokenExpired(String token){
        return getExpirationToken(token).before(new Date());
    }

    /**
     * Compare and verify if token is valid from token and UserDetails
     * @param token string 
     * @param userDetails UserDetails from request
     * @return true or false
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName= getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
