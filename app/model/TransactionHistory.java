package model;

import java.sql.Timestamp;

/**
 * Created by james on 20/04/17.
 */
public class TransactionHistory {

    public int id;

    public int auction_id;

    public String auction_title;

    public int amount_bid;

    public Timestamp timeOfBid;

    public String customer_number;

    public String customer_email;

    public TransactionHistory(int auction_id, String auction_title, int amount_bid, Timestamp timeOfBid, String customer_number, String customer_email) {
        this.auction_id = auction_id;
        this.auction_title = auction_title;
        this.amount_bid = amount_bid;
        this.timeOfBid = timeOfBid;
        this.customer_number = customer_number;
        this.customer_email = customer_email;
    }
}
