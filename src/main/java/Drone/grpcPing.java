package Drone;

import Drone.Master.InfoMaster;
import Drone.Master.InfoMasterElem;
import ServerAmministratore.DroneListElement;
import com.example.grpc.ProtocolMessage;
import com.example.grpc.ProtocolMessageServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/*
Implementa il sistema di ping per accorgermi quando il mio successivo è uscito
e di conseguenza mantenere una vista consistente della rete di droni
 */
public class grpcPing extends Thread {
    private ViewDroneNetwork infoDrone;
    private InfoMaster infoMaster;
    private long tempoDiPing = 5000;
    private int responseReceived = 0;
    private int election = 0;


    public grpcPing(ViewDroneNetwork infoDrone, InfoMaster infoMaster) {
        this.infoDrone = infoDrone;
        this.infoMaster = infoMaster;
    }

    public void run() {
        while (Drone.exit == 0) {
            responseReceived = 0;

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+ infoDrone.get_mySucc().getPorta()).usePlaintext().build();

            ProtocolMessageServiceGrpc.ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

            ProtocolMessage.PingHello pingHello = ProtocolMessage.PingHello.newBuilder().setHello("Hello").build();


            stub.ping(pingHello, new StreamObserver<ProtocolMessage.Ack>() {
                @Override
                public void onNext(ProtocolMessage.Ack value) {
                    responseReceived = 1;
                    String helloTo = value.getHelloTo();
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    channel.shutdown();
                }
            });


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(election == 1 && responseReceived == 1){
                startElection startElection = new startElection(infoDrone);
                startElection.start();
                System.out.println(">>>>>>> START ELECTION <<<<<<");
                election = 0;
            }

            if(responseReceived == 0){
                channel.shutdown();


                //se il mio successivo è il master e non risponde più
                if(infoDrone.get_mySucc().getID() == infoDrone.get_master().getID()){


                    infoDrone.removeDrone(infoDrone.get_mySucc());
                    infoDrone.set_mySucc(infoDrone.findMyNewSucc());


                    //se è vero vuol dire che sono rimasto solo e quindi mi autoproclamo master
                    if(infoDrone.get_mySucc().getID() == infoDrone.getMyId()) {
                        infoDrone.setId_master(infoDrone.getMyId());
                        infoDrone.setMaster(true);

                        DroneListElement drone = new DroneListElement(infoDrone.getMyId(),"localhost",infoDrone.getPorta());
                        InfoMasterElem infoMasterElem = new InfoMasterElem(drone,infoDrone.getPosition(),infoDrone.getBattery());
                        infoMaster.add(infoMasterElem);
                        infoDrone.startMasterThreads();
                        System.out.println("il nuovo master è: " + infoDrone.get_master());
                    } else {
                        //Devo iniziare una elezione ma prima di mandare il messaggio al mio succ
                        //controllo che sia vivo
                        election = 1;
                    }
                }
                else {

                    //se sono il master lo rimuovo dalla mia struttura dati
                    if(infoDrone.isMaster()){
                        infoMaster.removeDrone(infoDrone.get_mySucc().getID());
                    }

                    infoDrone.removeDrone(infoDrone.get_mySucc());
                    infoDrone.set_mySucc(infoDrone.findMyNewSucc());


                    System.out.println("il mio nuovo succ è: " + infoDrone.get_mySucc());
                }

            }


        }
    }
}
