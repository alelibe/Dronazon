package Drone;

import Drone.Master.GlobalStat;
import Drone.Master.InfoMaster;
import Drone.Master.InfoMasterElem;
import Drone.SimulatorePM10.Buffer;
import Drone.SimulatorePM10.Measurement;
import ServerAmministratore.DroneListElement;
import com.example.grpc.ProtocolMessage.*;
import com.example.grpc.ProtocolMessageServiceGrpc;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NetworkServiceImpl extends ProtocolMessageServiceGrpc.ProtocolMessageServiceImplBase{
    private ViewDroneNetwork infoDrone;
    private InfoMaster infoMaster;
    private GlobalStat globalStat;
    private Buffer buffer;

    public NetworkServiceImpl(ViewDroneNetwork infoDrone, InfoMaster infoMaster,GlobalStat globalStat,Buffer buffer){
        this.infoDrone = infoDrone;
        this.infoMaster = infoMaster;
        this.globalStat = globalStat;
        this.buffer = buffer;
    }

    @Override
    public void newEntry(EntryMessage request, StreamObserver<ResponseEntryMessage> responseObserver) {

        synchronized (infoDrone){
            while(infoDrone.isElection()){
                try {
                    infoDrone.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        int id_newDrone = request.getId();

        DroneListElement drone = new DroneListElement(id_newDrone, "localhost", request.getPorta());
        //questo controllo mi serve perchè il messaggio in broadcast arriva anche a me stesso
        if(id_newDrone != infoDrone.getMyId()) {
            infoDrone.addDrone(drone);
        }


        //se sono da solo il mio successore diventa il nuovo entrato
        if(infoDrone.get_mySucc().getID() == infoDrone.getMyId()){
            infoDrone.set_mySucc(drone);
        }
        else {
            //se sono collegato al primo
            if (infoDrone.get_mySucc().getID() < infoDrone.getMyId()) {
                //perchè ci potrebbe essere il link 8->4 ed entra il 3 oppure entra il 9
                if (id_newDrone < infoDrone.get_mySucc().getID() || id_newDrone > infoDrone.getMyId()) {
                    infoDrone.set_mySucc(drone);
                }
                //se l'id del nuovo drone è maggiore del mio e minore del mio successore
            } else if ((id_newDrone > infoDrone.getMyId()) && (id_newDrone < infoDrone.get_mySucc().getID())) {
                infoDrone.set_mySucc(drone);
            }
        }

        //se sono il master mi salvo la posizione del nuovo drone
        if(infoDrone.isMaster()) {
            ArrayList<Integer> position = new ArrayList<>();
            position.add(request.getPosition().getX());
            position.add(request.getPosition().getY());
            InfoMasterElem infoMasterElem = new InfoMasterElem(drone, position, 100);
            infoMaster.add(infoMasterElem);
        }

        //costruisco la risposta comunicando al nuovo drone se sono master o no
        if(infoDrone.isMaster()){
            ResponseEntryMessage response = ResponseEntryMessage.newBuilder().setId(infoDrone.getMyId()).setMaster(true)
                    .build();
            responseObserver.onNext(response);
        } else{
            ResponseEntryMessage response = ResponseEntryMessage.newBuilder().setId(infoDrone.getMyId()).setMaster(false)
                    .build();
            responseObserver.onNext(response);
        }

        System.out.println("Il mio id è: " + infoDrone.getMyId() + ", " + "il mio succ è : " +
                infoDrone.get_mySucc().getID());

        responseObserver.onCompleted();

    }

    @Override
    public void election(Election request, StreamObserver<Election> responseObserver) {

        infoDrone.setElection(true);


        Election election = null;
        Elected elected = null;

        int myBattery = infoDrone.getBattery();
        if(infoDrone.isConsegna()){
            myBattery = infoDrone.getBattery() - 10;
        }

        if(request.getType().equals("election")){
            if(request.getBattery() < myBattery && !(infoDrone.isParticipant())) {
                infoDrone.setParticipant(true);
                election = Election.newBuilder().setType("election").setBattery(myBattery).setId(infoDrone.getMyId()).build();
            }


            if(request.getBattery() > myBattery){
                election = Election.newBuilder().setType("election").setBattery(request.getBattery()).setId(request.getId()).build();
                infoDrone.setParticipant(true);
            }

            if(request.getBattery() == myBattery){
                if(request.getId() < infoDrone.getMyId() && !(infoDrone.isParticipant())){
                    infoDrone.setParticipant(true);
                    election = Election.newBuilder().setType("election").setBattery(myBattery).setId(infoDrone.getMyId()).build();
                }

                if(request.getId() > infoDrone.getMyId()){
                    infoDrone.setParticipant(true);
                    election = Election.newBuilder().setType("election").setBattery(request.getBattery()).setId(request.getId()).build();
                }

            }

            if(request.getId() == infoDrone.getMyId()){
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                infoDrone.setParticipant(false);
                elected = Elected.newBuilder().setType("elected").setId(infoDrone.getMyId()).addInfoDrone(Elected.InfoNetwork.newBuilder()
                .setId(infoDrone.getMyId()).setPorta(infoDrone.getPorta()).setBattery(myBattery).setPosition(Elected.Posizione.newBuilder()
                        .setX(infoDrone.getPosition().get(0)).setY(infoDrone.getPosition().get(1)).build()).build()).build();

                election = null;
                //election = Election.newBuilder().setType("elected").setId(infoDrone.getMyId()).build();
                infoDrone.setId_master(infoDrone.getMyId());
                infoDrone.setMaster(true);
                System.out.println("******SONO IL MASTER******");
            }

        }




        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+infoDrone.get_mySucc().getPorta()).usePlaintext().build();
        ProtocolMessageServiceGrpc.ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

        if(election != null) {
            stub.election(election, new StreamObserver<Election>() {
                @Override
                public void onNext(Election value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    channel.shutdown();
                }
            });
        }
        else{
            stub.elected(elected, new StreamObserver<Elected>() {
                @Override
                public void onNext(Elected value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    channel.shutdown();
                }
            });

        }

        try {
            channel.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.shutdown();


    }

    @Override
    public void elected(Elected request, StreamObserver<Elected> responseObserver) {
        Elected elected = null;
        if(request.getId() != infoDrone.getMyId()) {
            /*try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            infoDrone.setElection(false);
            infoDrone.setParticipant(false);
            infoDrone.setId_master(request.getId());
            System.out.println("IL NUOVO MASTER È: " + infoDrone.get_master());



            elected = Elected.newBuilder().setType("elected").setId(request.getId()).addAllInfoDrone(request.getInfoDroneList()).addInfoDrone(
                    Elected.InfoNetwork.newBuilder().setId(infoDrone.getMyId()).setPorta(infoDrone.getPorta()).setBattery(infoDrone.getBattery())
                            .setPosition(Elected.Posizione.newBuilder().setX(infoDrone.getPosition().get(0)).setY(infoDrone.getPosition().get(1))).build()
            ).build();



        } else {

            infoDrone.setElection(false);

            elected = null;
            System.out.println(">>>>> FINISH ELECTION <<<<<<");


            for(Elected.InfoNetwork d : request.getInfoDroneList()){
                //sono il master e quindi mi devo salvare le posizioni e la batteria di tutti i droni
                DroneListElement drone = new DroneListElement(d.getId(),"localhost",d.getPorta());
                ArrayList<Integer> position = new ArrayList<>();
                position.add(d.getPosition().getX());
                position.add(d.getPosition().getY());
                InfoMasterElem infoMasterElem = new InfoMasterElem(drone,position,d.getBattery());
                infoMaster.add(infoMasterElem);
            }



            infoDrone.startMasterThreads();


        }

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+infoDrone.get_mySucc().getPorta()).usePlaintext().build();
        ProtocolMessageServiceGrpc.ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

        if(elected != null) {
            stub.elected(elected, new StreamObserver<Elected>() {
                @Override
                public void onNext(Elected value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {

                }
            });
        }

        try {
            channel.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.shutdown();

    }




    @Override
    public void makeDelivery(Delivery request, StreamObserver<Delivery> responseObserver) {
        infoDrone.setConsegna(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long timestamp = System.currentTimeMillis();
        infoDrone.setBattery(); //diminuisco la batteria del 10%

        //aggiorno la posizione del drone
        ArrayList<Integer> newPosition = new ArrayList<>();
        newPosition.add(request.getDelivery().getX());
        newPosition.add(request.getDelivery().getY());
        infoDrone.setPosition(newPosition);

        int xDrone = infoDrone.getPosition().get(0);
        int yDrone = infoDrone.getPosition().get(1);

        int xOrdinePickUp = request.getPickup().getX();
        int yOrdinePickUp = request.getPickup().getY();

        double distancePickUP = Math.sqrt(Math.pow((xDrone - xOrdinePickUp), 2) + Math.pow((yDrone - yOrdinePickUp), 2)); //km fino al punto di ritiro

        int xOrdineDelivery = request.getDelivery().getX();
        int yOrdineDelivery = request.getDelivery().getY();

        double distanceDelivery = Math.sqrt(Math.pow((xOrdinePickUp - xOrdineDelivery), 2) + Math.pow((yOrdinePickUp - yOrdineDelivery), 2)); //km fino al punto di consegna


        ArrayList<StatDelivery.Average> averageList = new ArrayList<>();


        ArrayList<Measurement> measurements = (ArrayList<Measurement>) buffer.readAllAndClean();
        for(Measurement m : measurements){
            averageList.add(StatDelivery.Average.newBuilder().setMisura(m.getValue()).build());
        }



        System.out.println(">>>CONSEGNA EFFETTUATA<<<");
        infoDrone.setNum_consegne();
        infoDrone.setKm(distancePickUP+distanceDelivery);

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+infoDrone.get_master().getPorta()).usePlaintext().build();

        ProtocolMessageServiceGrpc.ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);



        StatDelivery statDelivery = StatDelivery.newBuilder().setTimestamp(timestamp).setPosition(StatDelivery.Posizione.newBuilder()
        .setX(infoDrone.getPosition().get(0)).setY(infoDrone.getPosition().get(1)).build()).addAllPollutionAverage(averageList)
                .setKmPickUp(distancePickUP).setKmDelivery(distanceDelivery)
                .setBattery(infoDrone.getBattery()).setId(infoDrone.getMyId()).build();


        stub.statDelivery(statDelivery, new StreamObserver<StatDelivery>() {
            @Override
            public void onNext(StatDelivery value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });

        try {
            channel.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.shutdown();


        infoDrone.setConsegna(false);

        if(infoDrone.getBattery() < 15){
            synchronized (infoDrone){
                while (infoDrone.isElection()){
                    try {
                        infoDrone.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            Client client = Client.create();
            ClientResponse clientResponseDelete = null;
            String serverAddress = "http://localhost:1337";
            String path = "/drone/delete/";
            WebResource webResource = client.resource(serverAddress+path);
            String id = String.valueOf(infoDrone.getMyId());
            clientResponseDelete = webResource.path(id).delete(ClientResponse.class);
            if(clientResponseDelete.getStatus() == 200)
                System.out.println("Drone eliminato");



            System.exit(0);
        }


    }


    @Override
    public void statDelivery(StatDelivery request, StreamObserver<StatDelivery> responseObserver) {
        infoMaster.setConsegna(request.getId(),0);

        ArrayList<Integer> updatePos = new ArrayList<>();
        updatePos.add(request.getPosition().getX());
        updatePos.add(request.getPosition().getY());

        infoMaster.setPosition(request.getId(),updatePos); //aggiorno la posizione del drone
        infoMaster.setBattery(request.getId(),request.getBattery()); //aggiorno la batteria


        //memorizzo le statistiche che mi ha mandato il drone
        globalStat.setNum_delivery();
        globalStat.setKm((float) (request.getKmDelivery()+request.getKmPickUp()));
        globalStat.setBattery(request.getBattery());


        for(StatDelivery.Average a : request.getPollutionAverageList()){
            globalStat.addMedia(a.getMisura());
        }


        System.out.println(">>>>STATISTICHE CHE MI HA MANDATO IL DRONE SALVATE<<<<");


    }



    @Override
    public void ping(PingHello request, StreamObserver<Ack> responseObserver) {
        Ack ack = Ack.newBuilder().setHelloTo("Hello to").build();
        responseObserver.onNext(ack);

        responseObserver.onCompleted();
    }



}
