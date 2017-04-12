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

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by james on 2/03/17.
 */

@Entity
@Table(name = "customer")
@SecondaryTable(name="customer_login")
public class Customer extends Model{

    @Id
    public int Id;

    @Constraints.Required(message="validation.required")
//    @Constraints.Email(message="validation.fname")
    @Constraints.MaxLength(value=100,message="validation.maxLength")
//    @Column(unique=true, nullable=false, length=100)
    public String firstName;

    @Constraints.Required(message="validation.required")
//    @Constraints.Email(message="validation.lname")
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

    //    address details - Better to use an address finder as opposed to regex as the pattern would be consistant
    //eg Address validation using Google Maps API or nz post code finder
    @Constraints.Required(message="validation.required")
    public String address1;

    public String address2;

//    @Min(3)
    @Constraints.Required(message="validation.required")
    public String suburb;

//    @Min(3)
    @Constraints.Required(message="validation.required")
    public String city;

//    @Min(4)
////  @Max(6) this will also be directed by the address checker.
    @Constraints.Required(message="validation.required")
    public int postcode;

    //can also use a country selector here to clean up entires into the DB.
    @Constraints.Required(message="validation.required")
    public String country;

    @Transient //Defines this field as being transient (not persisted)
    ///@JoinColumn(table="cusotmer_login")
    @Constraints.Required(message="validation.required")
    public String password;

    //this should be auto to 1 once email validation is done.- needs some work on this.
//    @Constraints.Min(1)
//    @Constraints.MaxLength(1)
     @Constraints.Required(message="validation.required")
     public int active;


    public static Finder<Long, Customer> find = new Finder<Long,Customer>(Customer.class);



}
