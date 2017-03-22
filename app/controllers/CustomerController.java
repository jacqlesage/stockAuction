package controllers;

import model.Customer;
import play.data.Form;
import javax.inject.Inject;
import javax.inject.Singleton;
import views.html.*;
import play.mvc.Controller;
import play.mvc.*;
import play.data.FormFactory;
import static play.data.Form.*;
//import play.sbt.routes.RoutesKeys.*;


/**
 * Created by james on 14/03/17.
 */
@Singleton
public class CustomerController extends Controller {

    private final Form<Customer> customerForm;

    @Inject
    public CustomerController(FormFactory formFactory) {
        this.customerForm =  formFactory.form(Customer.class);
    }


    //how a blank sign up form
    public Result showBlank(){
        return ok(views.html.userSignUpForm.render(customerForm));
    }


    //add the customer to the database
    public Result addCustomer(){

        return TODO;
    }


}
