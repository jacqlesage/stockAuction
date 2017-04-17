package model;

import com.avaje.ebean.Model;
import controllers.routes;
import org.mindrot.jbcrypt.BCrypt;
import play.mvc.Result;


import javax.persistence.*;
import java.io.Serializable;

import static play.mvc.Results.redirect;

/**
 * Created by james on 12/04/17.
 */
@Entity
@Table(name = "customer_login")
public class CustomerLogin extends Model{

    @Id
    public int Id;

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
     *
     * @param email user name of the customer
     * @param password password in plan text to hash
     * @return a Result - to decide which page we are going to redircct too.
     */
    public static Result createLogin(String email, String password) {

        if(CustomerLogin.find.where().eq("email",email).findUnique() != null) {//then email is alreay in system
            return redirect (controllers.routes.AsyncController.message());
        }else {
            CustomerLogin cus = new CustomerLogin();

            cus.email = email;
            cus.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            cus.password = cus.passwordHash;

            cus.save();

        }

        return redirect (controllers.routes.HomeController.index());
    }

    /**
     * A method which checks the user login informaiton - to determine whether a new customer or not.
     *
     * @param email - user name of the customer
     * @param password - password in plan text to check
     * @return - a customer login object - if there is one new customer if there is not, exsisting customer.
     */
    public static CustomerLogin authenticate(String email, String password) {
        CustomerLogin cus = CustomerLogin.find.where().eq("email",email).findUnique();
        if (cus != null && BCrypt.checkpw(password, cus.passwordHash)) {
            return cus;
        } else {
            return null;
        }
    }


}
