package model;

import com.avaje.ebean.Model;
import com.restfb.json.JsonArray;
import controllers.routes;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import play.api.libs.json.Json;
import play.mvc.Result;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by james on 12/04/17.
 */
@Entity
@Table(name = "customer_login")
public class CustomerLogin extends Model{

    @Id
    public int id;

    @Column(unique=true)
    public String email;

    public String password;

    @Transient
    public String passwordHash;

    @OneToOne
    @JoinColumn(name = "id")
    public Customer customer;

    public static Finder<Long, CustomerLogin> find = new Finder<Long,CustomerLogin>(CustomerLogin.class);



    /**
     *A method which creates a customer in the database and also will save a customer login object to the db.
     *
     * @param email user name of the customer
     * @param password password in plan text to hash
     * @return a Result - to decide which page we are going to redircct too.
     */
    public static Result createLogin(String email, String password, Customer customer) {

        if(CustomerLogin.find.where().eq("email",email).findUnique() != null) {//then email is already in system
            return redirect (controllers.routes.AsyncController.message());
        }else {
            //save the customer object to database
            customer.save();
            //then create the customer login table - deals with primary key issue i.e no customer object no customerLogin option can be created
            CustomerLogin cus = new CustomerLogin();
            //hash the password
            cus.email = email;
            cus.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            cus.password = cus.passwordHash;
            //save details to login table
            cus.save();

        }

        return redirect (controllers.routes.HomeController.index());
    }

    /**
     * A method which checks the user login informaiton - to determine whether a new customer or not.
     *
     * @param email - user name of the customer
     * @param password - password in plan text to check
     * @return - a customer login object - if there is a return CustomerLogin object it is a exsisting customer .
     */
    public static CustomerLogin authenticate(String email, String password) {
        CustomerLogin cus = CustomerLogin.find.where().eq("email",email).findUnique();
        if (cus != null && BCrypt.checkpw(password, CustomerLogin.find.where().eq("email",email).findUnique().password)) {

            return cus;
        } else {

            return null;
        }
    }

    @Override
    public String toString() {
        return "CustomerLogin{" +
                "Id=" + id +
                ", email='" + email ;
    }

    /**
     * A method so I can get the customers Id number
     * @param email - customers email
     * @return
     */

    public CustomerLogin findCustomer(String email){

        CustomerLogin cus = CustomerLogin.find.where().eq("email",email).findUnique();


        return cus;
    }

    /**
     * A method to build a customer login via facebook
     *
     * @param cus a cusrtomer object
     */
    public void saveCustomerViaFaceBook(Customer cus){


    }

    public Customer convertMapObj(Map values){
        String email,fname,lname, temp, temp1;
        JsonArray jsonArray = new JsonArray();
        Set set=values.entrySet();
        Iterator itr=set.iterator();


        //to add to jsonArray so I can pull values out as a string.
        while(itr.hasNext()){
            Map.Entry entry =(Map.Entry)itr.next();
            jsonArray.put(entry.getValue());
            //System.out.println(entry.getKey() + " " + entry.getValue());
            //System.out.println(jsonArray.toString());
        }
        //doing this to remove all the string noise.
        temp = jsonArray.toString();
        temp1 = temp.replace("[","");
        temp1 = temp1.replace("]","");
        temp1 = temp1.replaceAll("\"","");
        String[]array = temp1.split(",");

        email = array[0].toString();
        fname = array[1].toString();
        lname = array[2].toString();

        Customer customer = new Customer(fname, lname, email);

        return customer;
    }

    public String generateRandomPasswordForSocialMediaLogIn(){

        String randomPassword = RandomStringUtils.randomAlphanumeric(10);



        return randomPassword;
    }

}
