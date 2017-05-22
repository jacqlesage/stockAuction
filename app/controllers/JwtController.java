package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.JsonWebToken;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by james on 20/05/17.
 */
public class JwtController extends Controller {

    public Result createJwtv2(String username, String role) {
        System.out.print("hello (*)*)(*)(*)(*)(*" + username);

    JsonWebToken jsonWebToken = new JsonWebToken(username, role);
    String temp = "";
        String decoded = "";
    temp = jsonWebToken.createJWT("fish", "role");
    System.out.print(temp + "*************");
    decoded = jsonWebToken.parseJWT(temp);

   return ok(Json.toJson(decoded));
    }


}


