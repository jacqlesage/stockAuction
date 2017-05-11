package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import play.api.libs.json.Json;
import play.api.mvc.Session;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import  play.mvc.*;
import play.db.*;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.swing.text.html.HTML;
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
     * just a temp mehtod will need to tidy up this as it shouldnt be in controller
     * @return
     */
    public Result getCurrentAuction(){

        JsonNode ca = CurrentAuction.getCurrentAuction();

        return ok(views.html.auctionTestPage.render(ca));

    }

    /**
     * For checking the customer is a customer of ours.
     */
    public Result authenticateUser()  {

        Session session = null;

        CustomerLogin customerLogin = formFactory.form(CustomerLogin.class).bindFromRequest().get();

        //for potential session scope - add customer to the session first
        CustomerLogin customerInSession= formFactory.form(CustomerLogin.class).bindFromRequest().get();

        //find all the information so I can pick up ID
        CustomerLogin customerSession = customerInSession.findCustomer(customerInSession.email);

        customerLogin = customerLogin.authenticate(customerLogin.email, customerLogin.password);
       
        if(customerLogin != null){

            //add the cusotmer ID and email to the session so I can add it to the bid.
            session("email", customerSession.email);
            session("id", Integer.toString(customerSession.id));

            return ok(views.html.auctionTestPage.render(CurrentAuction.getCurrentAuction()));
        }

        return ok(views.html.hello.render(customerLogin.email));
    }

    public Result placeBid(){

       AuctionBidHistory auctionBidHistory = formFactory.form(AuctionBidHistory.class).bindFromRequest().get();
       //pass method back to object to preform logic
       auctionBidHistory.placeBid(auctionBidHistory);

        JsonNode ca = CurrentAuction.getCurrentAuction();

        return ok(views.html.auctionTestPage.render(ca));
    }

    /**
     * A method which delivers the points to a Json end point : users in this case
     * @return
     */
    public Result getAllUsers(){
        String jsonNode = null;
        Customer customer = new Customer();
        jsonNode = customer.getAllCustomers();
        return ok(jsonNode);

    }
}
