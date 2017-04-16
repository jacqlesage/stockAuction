package model;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;


import javax.persistence.*;
import java.io.Serializable;

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

    public static void createLogin(String email, String password) {

        CustomerLogin cus = new CustomerLogin();
        cus.email = email;
        cus.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        cus.password = cus.passwordHash;
        System.out.println("in here");
        System.out.println(cus.passwordHash);
        cus.save();
//        return cus;
    }

    //Not doing anything yet need to make adjustments on this code.
    public static CustomerLogin authenticate(String userName, String password) {
        CustomerLogin cus = CustomerLogin.find.where().eq("email", userName).findUnique();
        if (cus != null && BCrypt.checkpw(password, cus.passwordHash)) {
            return cus;
        } else {
            return null;
        }
    }


}
