package mysqlutilitypackage;

import io.github.ndimovt.ScientistTest;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DBReadingTest {
    private String resultCity;
    private String resultDate;
    private double resultTemp;
    private ResultSet testResultSet;
    private ScientistTest scientistTest;
    private static PreparedStatement statement;
    private static Connection connection;
    private static final String DBQUERRY1 = "CREATE TABLE city_and_temperature ( city VARCHAR(255), date DATE, water_temperature DOUBLE)";
    private static final String DBQUERRY2 = "CREATE TABLE account_info ( username VARCHAR(255), password VARCHAR(255))";

    @Test
    @DisplayName("testing scientist reading information")
    public void testGetAccountInformation()throws SQLException{
        try{
            accountInfoTable();
            statement = DBConnectionTest.testGetConnection().prepareStatement("INSERT INTO account_info (username, password) VALUES(?,?)");
            statement.setString(1,"testaccount");
            statement.setString(2,"testpass");
            statement.executeUpdate();
            statement = DBConnectionTest.testGetConnection().prepareStatement("SELECT * FROM account_info");
            testResultSet = statement.executeQuery();
            while(testResultSet.next()){
                scientistTest = new ScientistTest(
                        testResultSet.getString("username"),
                        testResultSet.getString("password"));
            }
            assertEquals("testaccount",scientistTest.getUserName());
            assertEquals("testpass", scientistTest.getPassword());
        }finally {
            testCloseConnection();
        }
    }
    private Statement accountInfoTable()throws SQLException{
        connection = DBConnectionTest.testGetConnection();
        statement = connection.prepareStatement(DBQUERRY2);
        statement.executeUpdate();
        return statement;
    }
    private Statement cityAndTempTable()throws SQLException{
        connection = DBConnectionTest.testGetConnection();
        statement = connection.prepareStatement(DBQUERRY1);
        statement.executeUpdate();
        return statement;
    }

    @Test
    @DisplayName("testing reading functions")
    public void testGetWaterInformation() {
        try{
            cityAndTempTable();
            String date = "2023-08-05";
            String city = "New Old York";
            double testTemp = 25.4;
            statement = DBConnectionTest.testGetConnection().prepareStatement("INSERT INTO city_and_temperature(city, date, water_temperature) VALUES(?,?,?)");
            statement.setString(1, city);
            statement.setString(2, date);
            statement.setDouble(3, testTemp);
            statement.executeUpdate();
            statement = DBConnectionTest.testGetConnection().prepareStatement("""
                    SELECT * FROM city_and_temperature
                    """);
            testResultSet = statement.executeQuery();
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