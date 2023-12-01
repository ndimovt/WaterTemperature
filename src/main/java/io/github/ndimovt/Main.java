package io.github.ndimovt;
import mysqlutilitypackage.DBReading;
import mysqlutilitypackage.DBWriting;

import java.util.Scanner;

public class Main {
    private static Scanner inn = new Scanner(System.in);
    enum Towns {KAVARNA, BALCHIK, VARNA, BURGAS}

    public static void main(String[] args) {
        System.out.println("Welcome to the water temperature check app");
        System.out.println("Choose option from the menu:");
        System.out.println("1) Enter in the system to write information  2)Check water temperature for town of your choice");
        int choice = inn.nextInt();
        if(choice == 1){
            System.out.println("enter username");
            inn.nextLine();
            String username = inn.nextLine();
            System.out.println("enter password");
            String password = inn.nextLine();
            enterInTheSystem(username, password);
        } else if (choice == 2) {
            System.out.println("Enter town (Kavarna, Balchik, Varna or Burgas)");
            inn.nextLine();
            String town = inn.nextLine();
            searchWaterTempForTown(town);
        }else {
            System.exit(0);
        }
    }
    private static void searchWaterTempForTown(String town){
        if (town.equalsIgnoreCase(String.valueOf(Towns.KAVARNA)) || town.equalsIgnoreCase(String.valueOf(Towns.BALCHIK))
                || town.equalsIgnoreCase(String.valueOf(Towns.VARNA)) || town.equalsIgnoreCase(String.valueOf(Towns.BURGAS))) {
            DBReading.getWaterInformation(town);
        }else {
            System.out.println("No such town exists");
        }
    }
    private static void enterInTheSystem(String username, String password){
        Scientist scientist = DBReading.getAccountInformation();
        if(scientist.getUserName().equals(username) & scientist.getPassword().equals(password)){
            System.out.println("Enter town to add temperature(Kavarna, Balchik, Varna or Burgas)");
            String town = inn.nextLine();
            System.out.println("Enter water temperature in Celsius:");
            double temperature = inn.nextDouble();
            addTemperatureRecord(town, temperature);
        }else {
            System.out.println("Wrong username or password");
        }
    }
    private static void addTemperatureRecord(String town, double temperature){
        if (town.equalsIgnoreCase(String.valueOf(Towns.KAVARNA)) || town.equalsIgnoreCase(String.valueOf(Towns.BALCHIK))
                || town.equalsIgnoreCase(String.valueOf(Towns.VARNA)) || town.equalsIgnoreCase(String.valueOf(Towns.BURGAS))) {
                DBWriting.addCityAndTemperatureToDB(town, temperature);
        }else{
            System.out.println("No such town exists");
        }
    }
}
