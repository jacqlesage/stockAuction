package model;


  import io.jsonwebtoken.*;
  import io.jsonwebtoken.Claims;
  import io.jsonwebtoken.SignatureAlgorithm;
  import io.jsonwebtoken.impl.crypto.MacProvider;

  import javax.xml.bind.DatatypeConverter;
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

        //this key should be stored somewhere else - in a proteced path file should be enough.
        //Key key = MacProvider.generateKey();

        String jwtString = Jwts.builder() .setIssuer("dollarLuxury")
                .setSubject("userToken")
                .claim("username", username)
                .claim("role", role)
                // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
                .setIssuedAt(new Date())
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                //.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(
                        SignatureAlgorithm.HS256, "test"

                )
                .compact();

    return jwtString;
    }


    //Sample method to validate and read the JWT
    public String parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("test"))
                .parseClaimsJws(jwt).getBody();


        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        System.out.println("Expiration: " + claims.get("username"));
        return claims.toString();
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
