package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by james on 9/06/17.
 */
public class Security extends Controller {

    public Result getToken(String token){
       System.out.println(token);

        return ok(token);

    }

    public Result test(){

        return ok(views.html.test.render());
    }
}
