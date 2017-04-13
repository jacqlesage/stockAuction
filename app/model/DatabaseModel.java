package model;
import akka.io.UdpConnected;
import play.db.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by james on 5/04/17.
 */
public class DatabaseModel {

    /**
     * At this stage I cannot get the default DB so I have tp create this class to connect to
     * it. The class will also hold the insert methods for the tables in my auction.
     *
     */
     public static play.db.Database database = Databases.createFrom(
            "playDollar",
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/playDollar?user=root&password=password"
    );

    /**
     * A method to connect to the database
     *
     * @return returns the database connection: DB is connected and ready for inserts etc
     */
   public static Connection conn() {

              Connection connection = database.getConnection();

       return connection;
   }

    /**
     * Disconnects from the database
     *
     * @throws SQLException should it not be connected
     */
    public static void closeConnection() throws SQLException {

        Connection connection = database.getConnection();

        connection.close();
    }



}
