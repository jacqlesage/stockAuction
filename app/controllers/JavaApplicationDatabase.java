package controllers;

import model.Customer;
import model.DatabaseModel;
import play.data.Form;
import play.data.FormFactory;
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
    FormFactory formFactory;

    @Inject
    public JavaApplicationDatabase(Database db){
        this.db = db;
    }



    public Result insertUser(){
        //take the 'userForm' and bind it to the customer model.
        Customer customer = formFactory.form(Customer.class).bindFromRequest().get();
        //save the info to the database
        customer.save();
        System.out.println(db.toString());
        //return to index page
        return redirect(routes.HomeController.index());

    }



}
