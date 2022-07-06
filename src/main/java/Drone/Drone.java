package Drone;

import Drone.Master.*;
import Drone.SimulatorePM10.*;
import ServerAmministratore.DroneListElement;
import ServerAmministratore.InfoDrone;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;


import java.util.ArrayList;
import java.util.Scanner;

public class Drone {
    private static final String SERVER_AMM = "http://localhost:1337";
    public static volatile int exit = 0;
    public static volatile int sendStat = 0;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci id del drone");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci porta di ascolto del drone");
        int porta = Integer.parseInt(scanner.nextLine());



        Client clientDrone = Client.create();
        ClientResponse clientResponse = null;

        //POST - Chiedo al server amministratore di aggiungermi alla rete
        String postPath = "/drone/add";
        DroneListElement droneListElement = new DroneListElement(id,"localhost", porta);
        clientResponse = postRequest(clientDrone,SERVER_AMM+postPath,droneListElement);

        if(clientResponse == null) {
            System.out.println("Server non disponibile");
            return;
        }

        //Se è già presente un drone con lo stesso id allora rifaccio la richiesta aumentando l'id di 1
        while(clientResponse.getStatus() == 409){
            id = id +1;
            DroneListElement tentativo = new DroneListElement(id,"localhost", porta);
            clientResponse = postRequest(clientDrone,SERVER_AMM+postPath,tentativo);
        }

        System.out.println("ID e porta del drone: " + id + ", " + porta);

        InfoDrone infoDrone = clientResponse.getEntity(InfoDrone.class);



        ArrayList<DroneListElement> droniIn = infoDrone.getInfoDrone().getDroneList(); //contiene i droni presenti nella rete
        ArrayList<Integer> position = infoDrone.getPosition(); //indica la posizione iniziale del drone appena connesso

        boolean master = false;
        DroneListElement mySucc = null;
        DroneListElement drone_master = null;
        ViewDroneNetwork viewDroneNetwork;
        InfoMaster infoMaster = new InfoMaster();
        GlobalStat globalStat = new GlobalStat();
        grpcBroadcast broadcast;




        //se la lista ottenuta dal server ha dimensione 1 vuol dire che ci sono solo io dentro
        if(droniIn.size() == 1){
            master = true;
            mySucc = droniIn.get(0); //se sono l'unico nella rete il mio successore sono io
            drone_master = droniIn.get(0);
            viewDroneNetwork = new ViewDroneNetwork(id,porta,drone_master,mySucc,master,droniIn,position,infoMaster,globalStat);
            InfoMasterElem infoMasterElem = new InfoMasterElem(droniIn.get(0),position,100);
            infoMaster.add(infoMasterElem);


            viewDroneNetwork.startMasterThreads();
        } else {
            for(DroneListElement d : droniIn){
                if(d.getID() > id) {
                    mySucc = d;
                    break;
                }
            }
            //se non c'è nessun drone nella lista che ha id maggiore del mio allora mi collegato al primo
            if(mySucc == null)
                mySucc = droniIn.get(0);

            viewDroneNetwork = new ViewDroneNetwork(id,porta,drone_master,mySucc,master,droniIn,position,infoMaster,globalStat);


            System.out.println("Inizio l'invio del messaggio di presentazione a tutti..");
            broadcast = new grpcBroadcast(viewDroneNetwork);
            broadcast.start();

        }

        //avvio thread che stampa le stat
        printStat printStat = new printStat(viewDroneNetwork);
        printStat.start();


        //avvio il simulatore
        Buffer buffer = new BufferImpl();
        PM10Simulator pm10Simulator = new PM10Simulator(buffer);
        pm10Simulator.start();

        //avvio il server grpc
        grpcServerNetwork serverNetwork = new grpcServerNetwork(porta, viewDroneNetwork, infoMaster,globalStat,buffer);
        serverNetwork.start();
        //avvio il ping
        grpcPing ping = new grpcPing(viewDroneNetwork,infoMaster);
        ping.start();

        //avvio il controllo dello stdin
        DroneExit droneExit = new DroneExit(viewDroneNetwork);
        droneExit.start();


        droneExit.join();


        //se sono il master chiudo i thread relativi agli ordini
        if(viewDroneNetwork.isMaster()){
            viewDroneNetwork.closeMasterThreads();
        }

        pm10Simulator.stopMeGently();

        ping.join();
        serverNetwork.interrupt();


        String serverAddress = "http://localhost:1337";
        ClientResponse clientResponseDelete = null;
        String path = "/drone/delete/";
        clientResponseDelete = deleteRequest(clientDrone,serverAddress+path, viewDroneNetwork.getMyId());
        if(clientResponseDelete.getStatus() == 200)
            System.out.println("Drone eliminato ");



        System.exit(0);
    }



    public static ClientResponse postRequest(Client client, String url, DroneListElement droneListElement){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(droneListElement);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            return null;
        }
    }

    public static ClientResponse deleteRequest(Client client, String url, int idDrone){
        WebResource webResource = client.resource(url);
        String id = String.valueOf(idDrone);
        return  webResource.path(id).delete(ClientResponse.class);
    }

}
