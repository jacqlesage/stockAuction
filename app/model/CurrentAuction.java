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
import java.util.List;

/**
 * Created by james on 24/04/17.
 */
@Entity
@Table(name = "currentAuction")
public class CurrentAuction extends Model {
    @Id
    public int Id;

    @Constraints.Required(message="validation.required")
    public String currentAUctiontitle;
    @Constraints.Required(message="validation.required")
    public String currentAuctionSalesDescription;
    @Constraints.Required(message="validation.required")
    public String URLForCurrentAuctionSpecs;
    @Constraints.Required(message="validation.required")
    public String URLForCurrentAuctionImages;
    @Constraints.Required(message="validation.required")
    public String currentAuctionItemLocation;
    @Constraints.Required(message="validation.required")
    public int currentAuctionReservePrice; //never shown I think admin should be able to setup the auction with higher management to add reserve
    @Constraints.Required(message="validation.required")
    public int currentAuctionTotalBids;

    public static Finder<Integer, CurrentAuction> find = new Finder<Integer,CurrentAuction>(CurrentAuction.class);

   public static void addAuction(CurrentAuction currentAuction){

       currentAuction.save();

   }

   public static JsonNode getCurrentAuction(){

      // List<CurrentAuction> currentAuctions = new Model.Finder<Integer, CurrentAuction>(CurrentAuction.class).all();

       return Json.toJson(find.all());

   }

}
