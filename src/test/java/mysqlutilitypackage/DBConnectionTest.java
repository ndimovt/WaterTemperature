package mysqlutilitypackage;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class DBConnectionTest {
    private DBConnection dbcon;
    @Test
    public void getConnection() throws SQLException {
        assertNotNull(dbcon.getConnection());
    }
}