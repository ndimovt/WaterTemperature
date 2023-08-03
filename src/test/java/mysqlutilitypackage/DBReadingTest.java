package mysqlutilitypackage;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
@Disabled

class DBReadingTest {
    private String resultCity;
    private String resultDate;
    private double resultTemp;
    private ResultSet testResultSet;
    private static PreparedStatement statement;
    private static PreparedStatement testStatement2;
    private static Connection connection;
    private static final String DBURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    private static final String DBQUERRY = "CREATE TABLE city_and_temperature ( city VARCHAR(255), date DATE, water_temperature DOUBLE)";

    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(DBURL,DB_USERNAME,DB_PASSWORD);
        statement = connection.prepareStatement(DBQUERRY);
        statement.executeUpdate();
    }

    @Test
    @DisplayName("testing reading functions")
    public void testGetInformation() {
        try{
            String date = "2023-08-03";
            String city = "New Old York";
            double testTemp = 25.4;
            DBWritingTest dbwrt = new DBWritingTest();
            dbwrt.testAddRecordToDB(city,testTemp);
            testStatement2 = DriverManager.getConnection(DBURL,DB_USERNAME,DB_PASSWORD).prepareStatement("""
                    SELECT * FROM city_and_temperature
                    """);
            testResultSet = testStatement2.executeQuery();
            while (testResultSet.next()){
                resultDate = testResultSet.getString("date");
                resultCity = testResultSet.getString("city");
                resultTemp = testResultSet.getDouble("water_temperature");
            }
            assertEquals(date,resultDate);
            assertEquals(city,resultCity);
            assertEquals(testTemp,resultTemp);
        }catch (SQLException sqe){
            sqe.printStackTrace();
        }finally {
            testCloseConnection();
        }
    }

    @AfterAll
    static void testCloseConnection(){
        try{
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            assertTrue(connection.isClosed());
            assertTrue(statement.isClosed());
        }catch (SQLException sqe){
            sqe.printStackTrace();
        }
    }
}