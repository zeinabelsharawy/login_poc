package com.squadio.util;

import io.jsonwebtoken.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Date;

public class JWTUtil {

    private static final String SECRET_KEY = "ZEINAB-KEY";

    public static String generateJWTToken(String username) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() +   + 20 * 60 * 1000);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static boolean verifyToken() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String token = (String) externalContext.getSessionMap().get("token");

        if (token != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                Date expirationDate = claims.getExpiration();

                return expirationDate != null && expirationDate.after(new Date());

            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                     IllegalArgumentException e) {

                return false;
            }
        } else {
            return false;
        }
    }

}