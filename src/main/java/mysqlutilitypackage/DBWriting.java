package mysqlutilitypackage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBWriting {
    public static void addCityAndTemperatureToDB(String city, double temperature) {
        try (PreparedStatement pst = DBConnection.getConnection().prepareStatement("call water_temperature_informatio.insertData(?,?,?)")){
            pst.setString(1, city);
            pst.setString(2, date());
            pst.setDouble(3, temperature);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        }
    }
    private static String date() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        return localDate.format(format);
    }
}
