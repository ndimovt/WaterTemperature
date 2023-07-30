package io.github.ndimovt;
import mysqlutilitypackage.DBWriting;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBWriting db = new DBWriting();
        try {
            db.addRecordToDB("varna", 26.3);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Scientist createNewScientistAccount(String userName, String password){
        return new Scientist(userName, password);
    }
}