package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by james on 20/04/17.
 */
@Entity
@Table(name = "auction_bid_history")
public class AuctionBidHistory extends Model{

    @Id
    public int id;

    public int auction_id;

    public String auction_title;

    public int amount_bid;

    public Timestamp timeOfBid;

    public String customer_id;

    public String customer_email;

    public AuctionBidHistory(int auction_id, String auction_title, int amount_bid, Timestamp timeOfBid, String customer_id, String customer_email) {
        this.auction_id = auction_id;
        this.auction_title = auction_title;
        this.amount_bid = amount_bid;
        this.timeOfBid = timeOfBid;
        this.customer_id = customer_id;
        this.customer_email = customer_email;
    }

    /**
     * A method which places a bit onto the auciton and saves the bid into the auction_bid_history
     * table.
     * @param auctionBidHistory - Takes the object from the bid form. The object is simply the form values needed for identifying the bid.
     */
    public void placeBid(AuctionBidHistory auctionBidHistory){
        //save bid to table
        auctionBidHistory.save();
        //check to see if reserve has been hit by checking current auction details
        //if won
            // stop auction : hide bid buttons
            //congratulate user
            //notify company of winner
                //note - we must check timestamps for actual winner
            //excess bids get paid back or asked to contribute to cause
            //post to users facebook - the win?
        //else
            //update pool of funds (i.e + bid)
            //remove funds from account (i.e - bid)


    }

    /**
     *A method which checks if the bid has won the auction
     * @param amount_bid - amount customer bid
     * @param auction_id - the auction id they just placed a bid on
     * @return A boolean; yes or no to show if the auction has been won
     */
    public boolean auctionWon(int amount_bid, int auction_id){
        CurrentAuction currentAuction = null;

        currentAuction = currentAuction.find.byId(auction_id);
        //get the total amount in the pool
        int currentReservePrice = currentAuction.current_auction_reserve_price;
        //add the bid to reserve
        int updatedReserveFigure = currentReservePrice + amount_bid;
        //if the above is >= currentAuction reserve then auction won
        if(updatedReserveFigure >= currentReservePrice)
            return true;
        //Else it's not won
        return false;
    }

    /**
     * a method which updates the total funds towards the auction
     */
    public void updateCurrentAuctionPool(){

    }
}
