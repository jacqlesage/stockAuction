package model;

import com.avaje.ebean.Model;

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

    public String email;

    public String password;

    @OneToOne()
    public Customer customer;


}
