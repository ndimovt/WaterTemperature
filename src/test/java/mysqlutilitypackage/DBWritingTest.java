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
    private static final String DBURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static final String DBQUERRY = "CREATE TABLE city_and_temperature ( city VARCHAR(255), date DATE, water_temperature DOUBLE)";
    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(DBURL, DB_USERNAME, DB_PASSWORD);
        statement = connection.prepareStatement(DBQUERRY);
        statement.executeUpdate();
    }
    @Test
    public void testAddRecordToDB(String city, double temp) throws SQLException {
        try {
            String city1 = "New Old York";
            double temperature1 = 25.4;
            statement = DBConnectionTest.testGetConnection().prepareStatement("INSERT INTO city_and_temperature(city, date, water_temperature) VALUES(?,?,?)");
            statement.setString(1,city1);
            statement.setString(2,date());
            statement.setDouble(3,temperature1);
            statement.executeUpdate();
            record++;
        } finally {
            //testCloseConnection();
        }
        assertEquals(1, record);
    }
    @Test
    @DisplayName("Testing date functionality")
    public String date() {
        String realDate = "2023-08-03";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String testDate = localDate.format(format);
        assertEquals(testDate, realDate);
        return testDate;
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