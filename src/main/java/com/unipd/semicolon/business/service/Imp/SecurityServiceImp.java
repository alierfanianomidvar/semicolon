package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.LocalTimeService;
import com.unipd.semicolon.business.service.SecurityService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityServiceImp implements SecurityService {

    @Autowired
    private LocalTimeService localTimeService;

    @Value("${security.jwt.token.secret-key:secret-key}")  // the secret-key
    private String secretKey;

    @Value("${security.jwt.token.expire-sec:36000}")  // Expire time of token : A year.
    private long validityInMilliseconds;

    @Override
    public String createToken(String accountId, String role) {
        Claims claims = Jwts.claims().setSubject(accountId);
        claims.put("Role" , role);

        Date validity = new Date(localTimeService.nowTime() + validityInMilliseconds * 1000000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        return getParseToken(token) != null;
    }

    @Override
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return (String) claims.get("role");
    }

    @Override
    public String getAccountId(String token) {
        return (getParseToken(token).getBody().getSubject());
    }

    /* ----  private class ----- */
    private Jws<Claims> getParseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
