package model;
//import org.springframework.ui.Model;
import controllers.JavaApplicationDatabase;
import play.data.format.*; // date format
import play.data.validation.*; // constraints
import play.db.Database;
import play.libs.Crypto; // crypt AES

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import com.avaje.ebean.Model;

/**
 * Created by james on 2/03/17.
 */

public class Customer extends Model{

    @Constraints.Required(message="validation.required")
    @Constraints.Email(message="validation.fname")
    @Constraints.MaxLength(value=100,message="validation.maxLength")
//    @Column(unique=true, nullable=false, length=100)
    public String firstName;

    @Constraints.Required(message="validation.required")
    @Constraints.Email(message="validation.lname")
    @Constraints.MaxLength(value=100,message="validation.maxLength")
//    @Column(unique=true, nullable=false, length=100)
    public String lastName;

    @Constraints.MaxLength(value=20,message="validation.maxLength")
    @Constraints.Required(message="validation.required")
    public String phoneNumber;

    //    @Column(unique=true, nullable=false, length=100)
    @Constraints.Required(message="validation.required")
    @Constraints.Email(message="validation.email")
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    public String email;

    //    address details

    @Constraints.Required(message="validation.required")
    public String address1;

    public String address2;

    @Constraints.Required(message="validation.required")
    public String suburb;

    @Constraints.Required(message="validation.required")
    public String city;

    @Constraints.Required(message="validation.required")
    public int postcode;

    @Constraints.Required(message="validation.required")
    public String country;

    @Constraints.Required(message="validation.required")
    public int active;


    public static Finder<Long, Customer> find = new Finder<Long,Customer>(Customer.class);



}
