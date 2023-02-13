package sv.com.bandesal.pruebatecnica.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS=2_592_00L;

    public static String createToken(String name, String email){
        long expirationTIme = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date exipirationDate = new Date(System.currentTimeMillis() + expirationTIme);
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(exipirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
             Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

             String email = claims.getSubject();
              return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
         }catch (JwtException e) {
          return null;
      }
    }

}
