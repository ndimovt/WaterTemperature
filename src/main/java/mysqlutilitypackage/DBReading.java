package mysqlutilitypackage;

import io.github.ndimovt.Scientist;
import io.github.ndimovt.WaterInformation;

import java.sql.*;

public class DBReading {
    private static WaterInformation waterInfo;
    private static Scientist scientist;
    public static Scientist getAccountInformation() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement("call water_temperature_information.getFullData()");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                scientist = new Scientist(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
            return scientist;
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        } finally {
            closeConnection(preparedStatement,resultSet,DBConnection.getConnection());
        }
        return null;
    }

    public static void getWaterInformation(String city) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = DBConnection.getConnection().prepareStatement("call water_temperature_information.getTownReadings(?)");
            preparedStatement.setString(1,city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 waterInfo = new WaterInformation(
                        resultSet.getString("m_date"),
                        resultSet.getDouble("water_temperature")
                );
                System.out.println(waterInfo);
            }
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        }
        finally {
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
