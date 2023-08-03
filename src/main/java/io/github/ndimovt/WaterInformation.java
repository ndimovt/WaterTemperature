package io.github.ndimovt;


public class WaterInformation {
    private String date;
    private String city;
    private double temperature;

    public WaterInformation(String date, String city, double temperature) {
        this.date = date;
        this.city = city;
        this.temperature = temperature;
    }
    @Override
    public String toString() {
        return  date +"/"+ temperature +"/"+ city;
    }
    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }
}
