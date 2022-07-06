package Dronazon;

import java.util.ArrayList;

public class Ordine {
    private int id;
    private ArrayList<Integer> pickupPoint; //position[0] = x, position[1] = y
    private ArrayList<Integer> deliveryPoint; //position[0] = x, position[1] = y



    public Ordine(int idRandom, int xPickUp, int yPickUp, int xDelivery, int yDelivery){
        id = idRandom;

        pickupPoint = new ArrayList<>(2);
        deliveryPoint = new ArrayList<>(2);

        pickupPoint.add(xPickUp);
        pickupPoint.add(yPickUp);

        deliveryPoint.add(xDelivery);
        deliveryPoint.add(yDelivery);

    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getPickupPoint() {
        return pickupPoint;
    }

    public ArrayList<Integer> getDeliveryPoint() {
        return deliveryPoint;
    }
}
