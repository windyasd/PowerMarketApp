package comv.example.sunshine.powermarketapp.util;

/**
 * Created by Sunshine on 2018/11/15.
 */

public class Plant_price {
    private String plantName;
    private double morning_price;
    private double  afternoon_price;
    private double night_price;

    public Plant_price(String plantName,double morning_price,double afternoon_price,double night_price){
        this.plantName=plantName;
        this.morning_price=morning_price;
        this.afternoon_price=afternoon_price;
        this.night_price=night_price;
    }

    public String getPlantName(){
        return plantName;
    }

    public double getMorning_price() {
        return morning_price;
    }

    public double getAfternoon_price() {
        return afternoon_price;
    }

    public double getNight_price() {
        return night_price;
    }
}
