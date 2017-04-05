import model.DatabaseModel;
import org.junit.*;

import play.twirl.api.Content;
import play.db.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertEquals("text/html", html.contentType());
        assertTrue(html.body().contains("Your new application is ready."));
    }

    @Test
    public void testDatabaseModelMyDefaultDb() throws SQLException {

        DatabaseModel dbm = new DatabaseModel();

        assertTrue(!dbm.conn().isClosed());

    }



}
