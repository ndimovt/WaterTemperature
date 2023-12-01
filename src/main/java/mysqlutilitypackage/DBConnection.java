package mysqlutilitypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static Connection connection;
    private static final String DBURL = "jdbc:mysql://localhost:3306/water_temperature_information";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sheeuser123456@";
    protected static Connection getConnection(){
      try{
        connection = DriverManager.getConnection(DBURL,USERNAME, PASSWORD);
        return connection;
      }catch (SQLException sqe){
        sqe.printStackTrace();
      }
      return null;
    }
}
