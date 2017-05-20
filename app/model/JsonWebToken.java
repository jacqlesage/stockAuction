package model;


  import io.jsonwebtoken.*;
  import io.jsonwebtoken.SignatureAlgorithm;
  import io.jsonwebtoken.impl.crypto.MacProvider;
  import java.security.Key;
  import java.util.Date;

/**
 * Created by james on 20/05/17.
 */
public class JsonWebToken {

    private String username;
    private String role;

    public JsonWebToken(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String createJWT(String username, String role){

        Key key = MacProvider.generateKey();

        String jwtString = Jwts.builder() .setIssuer("dollarLuxury")
                .setSubject("userToken")
                .claim("username", username)
                .claim("role", role)
                // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
                .setIssuedAt(new Date())
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                //.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(
                        SignatureAlgorithm.HS256, key

                )
                .compact();

    return jwtString;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
