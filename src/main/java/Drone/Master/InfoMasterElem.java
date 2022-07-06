package Drone.Master;

import ServerAmministratore.DroneListElement;

import java.util.ArrayList;

public class InfoMasterElem {
    private DroneListElement drone;
    private ArrayList<Integer> position;
    private int battery;
    private int consegna;


    public InfoMasterElem(DroneListElement drone, ArrayList<Integer> position, int battery){
        this.drone = drone;
        this.position = position;
        this.battery = battery;
        this.consegna = 0;
    }

    public DroneListElement getDrone() {
        return drone;
    }

    public synchronized void setConsegna(int consegna) {
        this.consegna = consegna;
    }

    public int getConsegna() {
        return consegna;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public synchronized void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }

    public int getBattery() {
        return battery;
    }

    public synchronized void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "InfoMasterElem{" +
                "drone=" + drone +
                ", position=" + position +
                ", battery=" + battery +
                '}';
    }
}
