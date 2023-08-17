package io.github.ndimovt;


public class WaterInformation {
    private String date;
    private double temperature;

    public WaterInformation(String date,  double temperature) {
        this.date = date;
        this.temperature = temperature;
    }
    @Override
    public String toString() {
        return  date +"/"+ temperature;
    }

}
