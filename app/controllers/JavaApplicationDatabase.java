package controllers;

import model.Customer;
import model.CustomerLogin;
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


    /**
     * This method handels the making of a new customer.
     * @return at this stage just returning the customer to the home page.
     */
    public Result insertUser(){
        //take the 'customerForm' and bind it to the customer model.
        Customer customer = formFactory.form(Customer.class).bindFromRequest().get();
        //save the info to the database
        customer.save();

        //bind to the customer login
        CustomerLogin customerLogin = formFactory.form(CustomerLogin.class).bindFromRequest().get();
        Result temp = customerLogin.createLogin(customerLogin.email, customerLogin.password);
        //customerLogin.save();
        //return to index page
        return temp;

    }



}
