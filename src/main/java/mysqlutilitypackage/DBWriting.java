package mysqlutilitypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBWriting {
    public static void addCityAndTemperatureToDB(String city, double temperature) throws SQLException {
        PreparedStatement pst = null;
        try{
            pst = DBConnection.getConnection().prepareStatement("INSERT INTO city_and_temperature(city, date, water_temperature) VALUES(?,?,?)");
            pst.setString(1,city);
            pst.setString(2,date());
            pst.setDouble(3,temperature);
            pst.executeUpdate();
        }finally {
            closeConnection(pst, DBConnection.getConnection());
        }
    }
    private static String date(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        return localDate.format(format);
    }
    private static void closeConnection(PreparedStatement preparedStatement, Connection connection) throws SQLException{
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
