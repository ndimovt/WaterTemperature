package mysqlutilitypackage;

import io.github.ndimovt.Scientist;
import io.github.ndimovt.WaterInformation;

import java.sql.*;

public class DBReading {
    private static WaterInformation waterInfo;
    private static Scientist scientist;
    public static Scientist getAccountInformation()throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement("""
                    SELECT * FROM account_info
                    """);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                scientist = new Scientist(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }finally {
            closeConnection(preparedStatement,resultSet,DBConnection.getConnection());
        }
        return scientist;
    }

    public static void getWaterInformation(String city)throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement("""
                    SELECT * FROM city_and_temperature
                    WHERE city = ?
                    """);
            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 waterInfo = new WaterInformation(
                        resultSet.getString("date"),
                        resultSet.getDouble("water_temperature")
                );
                System.out.println(waterInfo);
            }
        }finally {
            closeConnection(preparedStatement,resultSet,DBConnection.getConnection());
        }
    }
    private static void closeConnection(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection){
        try{
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (SQLException sqe){
            sqe.printStackTrace();
        }
    }

}
