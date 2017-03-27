import org.junit.*;

import play.db.evolutions.Evolution;
import play.db.evolutions.Evolutions;
import play.mvc.*;
import play.test.*;
import javax.inject.Inject;
import play.db.NamedDatabase;
import play.db.Database;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

//import play.db.Database;
import play.db.*;
//import play.db.evolutions.*;
import java.sql.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {


//    private Database database;

    Database database = Databases.createFrom(
            "playDollar",
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/playDollar?user=root&password=password"

    );


//    @Inject
//     public IntegrationTest(@NamedDatabase("dollarDatabase") Database db) {
//        this.database = db;
//
//    }

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

//    @Test
//    public void testDatabase() throws Exception{
//
//
//        if (!database.getConnection().isClosed()){
//            assertTrue("database is connected",!database.getConnection().isClosed());
//            System.out.println(database.getConnection() + " Hello private DB");
//
//        }else{
//
//            assertFalse("database is closed",database.getConnection().isClosed());
//        }
//
//    }

    @Test
    public void testDatabaseTable() throws Exception {

        Connection connection = database.getConnection();
        connection.prepareStatement("insert into test value (1, 'testing')").execute();

        assertTrue(
                connection.prepareStatement("select * from test where id = 10")
                        .executeQuery().next());


    }


    @Before
    public void setupDatabase() {


//        Database database = Databases.createFrom(
//                "playDollar",
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/playDollar?user=root&password=password"
//
//
//        );
        Evolutions.applyEvolutions(database, Evolutions.forDefault(new Evolution(
                1,
                "create table test (id bigint not null, name varchar(255));",
                "drop table test;"
        )));

    }
    @After
    public void shutdownDatabase() {
        Evolutions.cleanupEvolutions(database);
        database.shutdown();
    }

        @Test
        public void testDatabase () throws Exception {
            Connection connection = database.getConnection();
            connection.prepareStatement("insert into test values (125)").execute();

            assertTrue(
                    connection.prepareStatement("select * from test")
                            .executeQuery().next()
            );
        }
    }


