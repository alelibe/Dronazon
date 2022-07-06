package Drone.Master;

import Dronazon.Ordine;

import java.util.ArrayList;

public class QueueOrdini {
    private ArrayList<Ordine> ordini;

    public QueueOrdini(){
        ordini = new ArrayList<>();
    }

    public synchronized void put(Ordine ordine){
        ordini.add(ordine);
        notify();
    }

    public synchronized Ordine take(){
        Ordine ordine = null;

        while(ordini.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(ordini.size() > 0){
            ordine = ordini.get(0);
            ordini.remove(0);
        }

        return ordine;
    }

}
