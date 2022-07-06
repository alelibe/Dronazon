package Drone;

import com.example.grpc.ProtocolMessage.*;
import com.example.grpc.ProtocolMessageServiceGrpc;
import com.example.grpc.ProtocolMessageServiceGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class startElection extends Thread{
    private ViewDroneNetwork infoDrone;

    public startElection(ViewDroneNetwork infoDrone){
        this.infoDrone = infoDrone;
    }


    public void run() {

        infoDrone.setElection(true);

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost"+":"+infoDrone.get_mySucc().getPorta()).usePlaintext().build();

        ProtocolMessageServiceStub stub = ProtocolMessageServiceGrpc.newStub(channel);

        Election election = Election.newBuilder().setType("election").setBattery(infoDrone.getBattery()).setId(infoDrone.getMyId()).build();
        infoDrone.setParticipant(true);

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


        try {
            channel.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.shutdown();

    }
}
