import org.junit.*;

import play.mvc.*;
import play.test.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

//import play.db.Database;
import play.db.*;
//import play.db.evolutions.*;
import java.sql.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

 Database database = Databases.createFrom(
            "com.mysql.jdbc.Driver",
             "jdbc:mysql://localhost:3306/?user=root&password=password"

            );

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333");
            assertTrue(browser.pageSource().contains("Your new application is ready."));
        });
    }

    @Test
    public void testDatabase() throws Exception{


        if (!database.getConnection().isClosed()){
            assertTrue("database is connected",!database.getConnection().isClosed());

        }else{

            assertFalse("database is closed",database.getConnection().isClosed());
        }

    }

}
