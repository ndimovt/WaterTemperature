package mysqlutilitypackage;

import io.github.ndimovt.Scientist;
import io.github.ndimovt.WaterInformation;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBReading {
    private static WaterInformation waterInfo;
    private static Scientist scientist;
    public static Scientist getAccountInformation() {
        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("call water_temperature_information.getFullData()");
        ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                scientist = new Scientist(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        }
        return scientist;
    }

    public static void getWaterInformation(String city) {
        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("call water_temperature_information.getTownReadings(?)");
        ResultSet resultSet = preparedStatement.executeQuery()){
            preparedStatement.setString(1,city);
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
    }
}
