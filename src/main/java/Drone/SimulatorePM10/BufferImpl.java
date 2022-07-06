package Drone.SimulatorePM10;


import ServerAmministratore.Statistics;

import java.util.ArrayList;
import java.util.List;

public class BufferImpl implements Buffer{
    private ArrayList<Measurement> sliding;
    private ArrayList<Measurement> measurements;


    public BufferImpl(){
        sliding = new ArrayList<>();
        this.measurements = new ArrayList<>();
    }

    public void addMeasurement(Measurement m){
        sliding.add(m);
       // System.out.println("Dimensione di sliding: " + sliding.size());
        if(sliding.size() == 8){
            //readAllAndClean();
            double somma = 0;
            double average;
            for(int i = 0; i < 8;i ++){
                somma += sliding.get(i).getValue();
            }

            for(int i = 0; i < 4; i++){
                sliding.remove(i);
            }

            average = somma/8;
            Measurement misura = new Measurement("id","type",average,0);
            synchronized (measurements) {
                measurements.add(misura);
            }
        }

    }

    public List<Measurement> readAllAndClean(){

        ArrayList<Measurement> measurements_copy;
        synchronized (measurements) {
            measurements_copy = new ArrayList<Measurement>(measurements);
        }

        measurements.clear();

        return measurements_copy;

    }

}
