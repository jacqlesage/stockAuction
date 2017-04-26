package model;

import akka.stream.impl.io.OutputStreamSourceStage;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import play.data.validation.Constraints;

import play.libs.Json;
import play.mvc.Result;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Constraint;
import java.util.List;

/**
 * Created by james on 24/04/17.
 */
@Entity
@Table(name = "current_auction")
public class CurrentAuction extends Model {
    @Id
    public int Id;

    @Constraints.Required
    public String current_auction_title;


    public String current_auction_sales_description;


    public String urlfor_current_auction_specs;


    public String urlfor_current_auction_images;


    public String current_auction_item_location;


    public int current_auction_reserve_price; //never shown I think admin should be able to setup the auction with higher management to add reserve


    public int current_auction_total_bids;


    public int active;


    public static Finder<Integer, CurrentAuction> find = new Finder<Integer,CurrentAuction>(CurrentAuction.class);

   public static void addAuction(CurrentAuction currentAuction){

       currentAuction.save();

   }

   public static JsonNode getCurrentAuction(){

      // List<CurrentAuction> currentAuctions = new Model.Finder<Integer, CurrentAuction>(CurrentAuction.class).all();

       return Json.toJson(find.all());

   }

}
