package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.CurrentAuction;
import model.Customer;
import model.CustomerLogin;
import model.DatabaseModel;
import play.api.libs.json.Json;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import  play.mvc.*;
import play.db.*;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;


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

        //bind to the customer login
        CustomerLogin customerLogin = formFactory.form(CustomerLogin.class).bindFromRequest().get();
        Result temp = customerLogin.createLogin(customerLogin.email, customerLogin.password, customer);

        //return to index page
        return temp;

    }

//    public void insertAuction(){
//        CurrentAuction currentAuction = formFactory.form(CurrentAuction.class).bindFromRequest().get();
//        currentAuction.addAuction(currentAuction); //just to seperate out the controller from the class. Could have saved here - should I have ?
//        //showCurrentAuction();
//
//
//    }

    public Result showCurrentAuction(){

//        insertAuction(); //above method
        CurrentAuction currentAuction = formFactory.form(CurrentAuction.class).bindFromRequest().get();
        currentAuction.addAuction(currentAuction); //just to seperate out the controller from the class. Could have saved here - should I have ?
        //showCurrentAuction();

        JsonNode ca = CurrentAuction.getCurrentAuction();
//        List currentAuctions = CurrentAuction.getCurrentAuctionJava();

//        return ok(views.html.auctionTestPage.render(currentAuctions));
        return ok(views.html.auctionTestPage.render(ca));
    }

    /**
     * For checking the customer is a customer of ours.
     */
    public Result authenticateUser(){

        CustomerLogin customerLogin = formFactory.form(CustomerLogin.class).bindFromRequest().get();

        customerLogin = customerLogin.authenticate(customerLogin.email, customerLogin.password);

        if(customerLogin != null){

            return ok(views.html.auctionTestPage.render(CurrentAuction.getCurrentAuction()));
        }

        return ok(views.html.hello.render(customerLogin.toString()));
    }


}
