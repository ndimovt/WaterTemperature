package mysqlutilitypackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBWriting {
    public void addRecordToDB(String city, double temperature) throws SQLException {
        PreparedStatement pst = null;
        try{
            pst = DBConnection.getConnection().prepareStatement("INSERT INTO city_and_temperature(city, date, temperature) VALUES(?,?,?)");
            pst.setString(1,city);
            pst.setString(2,date());
            pst.setDouble(3,temperature);
            pst.executeUpdate();
        }finally {
            closeConnection(pst, null);
        }
    }
    private String date(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        return localDate.format(format);
    }
    private void closeConnection(PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException{
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(resultSet != null){
            resultSet.close();
        }
    }
}
