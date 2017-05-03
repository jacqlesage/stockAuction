package model;

import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by james on 3/05/17.
 */
public class CustomerAccount {

     @Id
     public int id;

     public int balance;

     public int amountDeposited;

     public Timestamp timeOfDeposit;

     public int customerId;

     public String customerEmail;

}
