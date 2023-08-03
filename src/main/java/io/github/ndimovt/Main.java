package io.github.ndimovt;
import mysqlutilitypackage.DBReading;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBReading.getInformation();

      }catch (SQLException e){
          e.printStackTrace();
       }
    }
    public Scientist createNewScientistAccount(String userName, String password){
        return new Scientist(userName, password);
    }
}