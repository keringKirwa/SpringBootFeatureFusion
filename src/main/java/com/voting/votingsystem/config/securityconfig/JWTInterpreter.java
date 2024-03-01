package com.voting.votingsystem.config.securityconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTInterpreter {
    private static final String SECRET_KEY = "556B58703272357538782F413F4428472B4B6250655368566D59713374367639";
    final long JWT_TOKEN_VALIDITY = 60 * 1000;

    /**
     * https://allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx     *
     * @param JwtToken token to be parsed, that is , interpreted and the body returned.
     *                 {
     *                 "sub": "1234567890",
     *                 "name": "John Doe",
     *                 "iat": 1516239022
     *                 }
     *                 It's quite simple to operate on dates using milliseconds
     */


    public String generateJWT(Map<String, Object> extraClaims, UserDetails userDetails) {

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) ;

        return jwtBuilder.compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && ! isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUserName(String JwtToken) {

        return getPropertyFromClaims(JwtToken, Claims::getSubject);

    }

    private Date extractExpiration(String token) {
        return getPropertyFromClaims(token, Claims::getExpiration);
    }

    /**
     *
     * @param JwtToken the token to be parsed/analyzed
     * @param claimsResolver  is a lambda function to get a claim/property from Claims object : for instance the expiration date for a token, userName , iat , issuer etc.
     */
    public <T> T getPropertyFromClaims(String JwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = parseJwtToGetClaimsObject(JwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims parseJwtToGetClaimsObject(String JwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(JwtToken).getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
