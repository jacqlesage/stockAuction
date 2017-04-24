package model;

import java.sql.Timestamp;

/**
 * Created by james on 21/04/17.
 */
public interface BaseHistoryInterface {
    public String customerFullName = null;
    public Timestamp timeStamp = null;
    public String customerEmail = null;
    public int customerBidAmount = 0;
   
}
