package Drone;

import Drone.Master.GlobalStat;
import Drone.Master.InfoMaster;
import Drone.SimulatorePM10.Buffer;
import Drone.SimulatorePM10.Measurement;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class grpcServerNetwork extends Thread{

    private int porta;
    private ViewDroneNetwork viewDroneNetwork;
    private InfoMaster infoMaster;
    private GlobalStat globalStat;
    private Buffer buffer;

    public grpcServerNetwork(int porta, ViewDroneNetwork viewDroneNetwork, InfoMaster infoMaster, GlobalStat globalStat,
                             Buffer buffer){
        this.porta = porta;
        this.viewDroneNetwork = viewDroneNetwork;
        this.infoMaster = infoMaster;
        this.globalStat = globalStat;
        this.buffer = buffer;
       // this.measurements = measurements;
    }


    public void run() {
        //faccio partire il servizio sulla porta 8080
        try {

            Server server = ServerBuilder.forPort(porta).addService(new NetworkServiceImpl(viewDroneNetwork, infoMaster,globalStat,buffer)).build();

            server.start();

            System.out.println("Server started!");

            server.awaitTermination();


        } catch (IOException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {
            System.out.println("drone uscito correttamente");
           // e.printStackTrace();

        }
    }
}
