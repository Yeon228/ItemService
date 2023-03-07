package review.springmvc.basic.db;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DatabaseConnectionTest {

    private final DataSource dataSource = new MariaDbDataSource();

    public boolean testConnection() throws SQLException {
        boolean result = true;
        dataSource.getConnection();
        return result;
    }
}