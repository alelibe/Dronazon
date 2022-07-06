package Drone;


import ServerAmministratore.DroneListElement;
import com.example.grpc.ProtocolMessage;
import com.example.grpc.ProtocolMessageServiceGrpc;
import com.example.grpc.ProtocolMessageServiceGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/*
Implementa l'invio in broadcast quando sono un nuovo drone e
devo presentarmi alla rete
 */
public class grpcBroadcast extends Thread{
    private ViewDroneNetwork viewDroneNetwork;

    public grpcBroadcast(ViewDroneNetwork viewDroneNetwork){
        this.viewDroneNetwork = viewDroneNetwork;
    }

    public void run() {

        for(DroneListElement d : viewDroneNetwork.getDroniInNetwork()){

            final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+d.getPorta()).usePlaintext().build();

            //System.out.println("[BROADCAST CLIENT] Connected!");

            ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

            ProtocolMessage.EntryMessage entryMessage = ProtocolMessage.EntryMessage.newBuilder().setId(viewDroneNetwork.getMyId()).setPorta(viewDroneNetwork.getPorta())
                    .setPosition(ProtocolMessage.EntryMessage.Posizione.newBuilder().setX(viewDroneNetwork.getPosition().get(0))
                            .setY(viewDroneNetwork.getPosition().get(1))).build();

            stub.newEntry(entryMessage, new StreamObserver<ProtocolMessage.ResponseEntryMessage>() {
                @Override
                public void onNext(ProtocolMessage.ResponseEntryMessage value) {
                    //se mi ha risposto un drone che è master mi salvo il suo id
                    if(value.getMaster()) {
                        viewDroneNetwork.setId_master(value.getId());
                    }
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
                channel.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("il master è: " + viewDroneNetwork.get_master());


        if(viewDroneNetwork.get_master() == null){
            startElection startElection = new startElection(viewDroneNetwork);
            startElection.start();
        }


    }
}
