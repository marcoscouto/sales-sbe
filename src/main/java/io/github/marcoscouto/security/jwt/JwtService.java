package io.github.marcoscouto.security.jwt;

import io.github.marcoscouto.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key}")
    private String key;

    public String generateToken(User user) {
        long expiration = Long.valueOf(this.expiration);
        LocalDateTime dateTimeExpiration = LocalDateTime.now().plusMinutes(expiration);
        Date date = Date.from(dateTimeExpiration.atZone(ZoneId.systemDefault()).toInstant());

//        Map claims = new HashMap();
//        claims.put("email", "user@email.com");
//        claims.put("infos", "abcd");

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(date)
//                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime date = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isAfter(date);
        } catch (Exception e){
            return false;
        }
    }

    public String getUsernameUser(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }

}
