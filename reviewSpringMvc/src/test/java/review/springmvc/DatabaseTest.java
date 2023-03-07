package review.springmvc;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.fail;

public class DatabaseTest {
    @Test
    public void testConnection(){
        try(Connection con = DriverManager.getConnection("jdbc:mariadb://152.67.198.30:3306/practice",
                "root","1q2w3e4r")) {
        } catch (SQLException e) {
            fail(e.getMessage());
        } ;
    }
}
