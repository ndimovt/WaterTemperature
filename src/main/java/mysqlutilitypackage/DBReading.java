package mysqlutilitypackage;

import io.github.ndimovt.WaterInformation;

import java.sql.*;

public class DBReading {
    private static WaterInformation waterInfo;

    public static void getInformation()throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement("""
                    SELECT * FROM city_and_temperature
                    """);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 waterInfo = new WaterInformation(
                        resultSet.getString("date"),
                        resultSet.getString("city"),
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
