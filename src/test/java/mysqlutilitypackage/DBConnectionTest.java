package mysqlutilitypackage;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnectionTest {
    @Test
    protected static Connection testGetConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test","sa", "");
        return connection;
    }
}