package mysqlutilitypackage;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.*;

public class DBWritingTest {
    private static PreparedStatement statement;
    private static Connection connection;
    private static int record;
    private static final String DBQUERRY = "CREATE TABLE city_and_temperature ( city VARCHAR(255), date DATE, water_temperature DOUBLE)";
    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DBConnectionTest.testGetConnection();
        statement = connection.prepareStatement(DBQUERRY);
        statement.executeUpdate();
    }
    @Test
    public void testAddCityAndTemperatureToDB() throws SQLException {
        try {
            String city1 = "New Old York";
            double temperature1 = 25.4;
            String date = "2023-08-05";
            statement = DBConnectionTest.testGetConnection().prepareStatement("INSERT INTO city_and_temperature(city, date, water_temperature) VALUES(?,?,?)");
            statement.setString(1, city1);
            statement.setString(2, date);
            statement.setDouble(3, temperature1);
            statement.executeUpdate();
            record++;
        } finally {

            assertEquals(5, record);
        }
    }
    @Test
    @DisplayName("Testing date functionality")
    public void testDate() {
        String realDate = "2023-08-17";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String testDate = localDate.format(format);
        assertEquals(testDate, realDate);
    }
    @AfterAll
    @DisplayName("ClosingConnections")
    static void testCloseConnection(){
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            assertTrue(statement.isClosed());
            assertTrue(connection.isClosed());
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
    }
}