package Drone.Master;

import Dronazon.Ordine;
import Drone.Drone;
import com.example.grpc.ProtocolMessage.*;
import com.example.grpc.ProtocolMessageServiceGrpc;
import com.example.grpc.ProtocolMessageServiceGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderConsumer extends Thread{
    private QueueOrdini queueOrdini;
    private InfoMaster infoMaster;
    private ArrayList<Ordine> ordiniPending;
    private ArrayList<InfoMasterElem> droniFree;

    public OrderConsumer(QueueOrdini queueOrdini, InfoMaster infoMaster){
        this.queueOrdini = queueOrdini;
        this.infoMaster = infoMaster;
        ordiniPending = new ArrayList<>();
    }

    @Override
    public void run() {
        InfoMasterElem chosenDrone;
        while(Drone.exit == 0) {
            droniFree = new ArrayList<>(); //ci salvo i droni che non sono impegnati in una consegna
            chosenDrone = null; //drone scelto per la consegna


            List<InfoMasterElem> copy_droni;
            synchronized (queueOrdini) {
                copy_droni = new ArrayList<InfoMasterElem>(infoMaster.getInfoMaster());
            }

            for(InfoMasterElem d : copy_droni){
                if(d.getConsegna() == 0){
                    droniFree.add(d);
                    //System.out.println(d);
                }
            }


            Ordine ordine;
            if(droniFree.size() > 0) {
                if (ordiniPending.isEmpty()) {
                    ordine = queueOrdini.take();
                } else {
                    ordine = ordiniPending.get(0);
                    ordiniPending.remove(0);
                }


                double minDistance;
                chosenDrone = droniFree.get(0);
                minDistance = Math.sqrt(Math.pow((chosenDrone.getPosition().get(0) - ordine.getPickupPoint().get(0)), 2) +
                        Math.pow((chosenDrone.getPosition().get(1) - ordine.getPickupPoint().get(1)), 2));
                for (InfoMasterElem drone : droniFree) {

                    int xDrone = drone.getPosition().get(0);
                    int yDrone = drone.getPosition().get(1);


                    int xOrdine = ordine.getPickupPoint().get(0);
                    int yOrdine = ordine.getPickupPoint().get(1);

                    double distance = Math.sqrt(Math.pow((xDrone - xOrdine), 2) + Math.pow((yDrone - yOrdine), 2));
                    if ((distance < minDistance) && chosenDrone.getBattery() < drone.getBattery()) {
                        chosenDrone = drone;
                        minDistance = distance;
                    }

                    if (distance == minDistance && chosenDrone.getBattery() == drone.getBattery()) {
                        if (chosenDrone.getDrone().getID() < drone.getDrone().getID())
                            chosenDrone = drone;
                    }
                }

            }
            else{
                ordine = queueOrdini.take();
                ordiniPending.add(ordine);
            }

            if(chosenDrone != null){
                chosenDrone.setConsegna(1);
                System.out.println("***DRONE SCELTO PER LA CONSEGNA*** : " + chosenDrone.getDrone().getID());

                final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+ chosenDrone.getDrone().getPorta())
                        .usePlaintext().build();


                ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

                Delivery delivery = Delivery.newBuilder().setPickup(Delivery.PickUpPoint.newBuilder().setX(ordine.getPickupPoint().get(0))
                .setY(ordine.getPickupPoint().get(1)).build()).setDelivery(Delivery.DeliveryPoint.newBuilder().setX(ordine.getDeliveryPoint().get(0))
                .setY(ordine.getDeliveryPoint().get(1))).build();


                stub.makeDelivery(delivery, new StreamObserver<Delivery>() {
                    @Override
                    public void onNext(Delivery value) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });

                try {
                    channel.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                channel.shutdown();

            }


        }


        while(ordiniPending.size() > 0){

            List<InfoMasterElem> copy_droni;
            synchronized (queueOrdini) {
                copy_droni = new ArrayList<InfoMasterElem>(infoMaster.getInfoMaster());
            }

            for(InfoMasterElem d : copy_droni){
                if(d.getConsegna() == 0){
                    droniFree.add(d);
                }
            }

            Ordine ordinePending;
            if(droniFree.size() > 0){
                ordinePending = ordiniPending.get(0);
                ordiniPending.remove(0);


                double minDistance;
                chosenDrone = droniFree.get(0);
                minDistance = Math.sqrt(Math.pow((chosenDrone.getPosition().get(0) - ordinePending.getPickupPoint().get(0)), 2) +
                        Math.pow((chosenDrone.getPosition().get(1) - ordinePending.getPickupPoint().get(1)), 2));
                for (InfoMasterElem drone : droniFree) {

                    int xDrone = drone.getPosition().get(0);
                    int yDrone = drone.getPosition().get(1);


                    int xOrdine = ordinePending.getPickupPoint().get(0);
                    int yOrdine = ordinePending.getPickupPoint().get(1);

                    double distance = Math.sqrt(Math.pow((xDrone - xOrdine), 2) + Math.pow((yDrone - yOrdine), 2));
                    if ((distance < minDistance) && chosenDrone.getBattery() < drone.getBattery()) {
                        chosenDrone = drone;
                        minDistance = distance;
                    }

                    if (distance == minDistance && chosenDrone.getBattery() == drone.getBattery()) {
                        if (chosenDrone.getDrone().getID() < drone.getDrone().getID())
                            chosenDrone = drone;
                    }
                }


                chosenDrone.setConsegna(1);
                System.out.println("***DRONE SCELTO PER LA CONSEGNA*** : " + chosenDrone.getDrone().getID());

                final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+ chosenDrone.getDrone().getPorta())
                        .usePlaintext().build();


                ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

                Delivery delivery = Delivery.newBuilder().setPickup(Delivery.PickUpPoint.newBuilder().setX(ordinePending.getPickupPoint().get(0))
                        .setY(ordinePending.getPickupPoint().get(1)).build()).setDelivery(Delivery.DeliveryPoint.newBuilder()
                        .setX(ordinePending.getDeliveryPoint().get(0)).setY(ordinePending.getDeliveryPoint().get(1))).build();


                stub.makeDelivery(delivery, new StreamObserver<Delivery>() {
                    @Override
                    public void onNext(Delivery value) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });

                try {
                    channel.awaitTermination(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                channel.shutdown();


            }

        }




    }



}
