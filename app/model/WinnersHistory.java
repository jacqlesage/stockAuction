package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 * Created by james on 20/04/17.
 */
@Entity
@Table(name = "winnersHistory")
public class WinnersHistory extends Model{
    //a table with data about all the auctions ever won and who won what
    public String customerFullName = null;
    public Timestamp timeStamp = null;
    public String customerEmail = null;
    public int customerBidAmount = 0;

}
