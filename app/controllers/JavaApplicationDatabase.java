package controllers;

import play.mvc.Controller;
import  play.mvc.*;
import play.db.*;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;


/**
 * Created by james on 31/03/17.
 */
public class JavaApplicationDatabase extends Controller {


    private static Database db;

    @Inject
    public JavaApplicationDatabase(Database db){
        this.db = db;
    }

    public Result insertUser(){

        System.out.println(db.toString());
        return TODO;

    }



}
