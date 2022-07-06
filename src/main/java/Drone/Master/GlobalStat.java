package Drone.Master;

import java.util.ArrayList;


public class GlobalStat {
    private int num_delivery;
    private float km;
    private int battery;
    private ArrayList<Double> pollution;



    public GlobalStat(){
        num_delivery = 0;
        km = 0;
        battery = 0;
        pollution = new ArrayList<>();
    }

    public void addMedia(double m){
        pollution.add(m);
    }

    public void setPollution(ArrayList<Double> pollution) {
        this.pollution = pollution;
    }

    public ArrayList<Double> getPollution() {
        return pollution;
    }

    public void setNum_delivery(){
        this.num_delivery = this.num_delivery + 1;
    }

    public void setKm(float km) {
        this.km = this.km + km;
    }

    public void setBattery(int battery) {
        this.battery = this.battery + battery;
    }

    public int getNum_delivery() {
        return num_delivery;
    }

    public float getKm() {
        return km;
    }

    public int getBattery() {
        return battery;
    }
}
