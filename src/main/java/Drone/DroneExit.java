package Drone;

import java.util.Scanner;

public class DroneExit extends Thread{
    private ViewDroneNetwork infoDrone;

    public DroneExit(ViewDroneNetwork infoDrone){
        this.infoDrone = infoDrone;
    }


    public void run() {
        Scanner input = new Scanner(System.in);

        while(Drone.exit == 0){

                String exit = input.nextLine();
                if(exit.equals("quit")){

                    synchronized (infoDrone){
                        while (infoDrone.isElection()){
                            try {
                                infoDrone.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    synchronized (infoDrone){
                        while (infoDrone.isConsegna()){
                            try {
                                infoDrone.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }



                    Drone.exit = 1;

                    break;
                }

        }

       /* Client client = Client.create();
        String serverAddress = "http://localhost:1337";
        ClientResponse clientResponse = null;
        String path = "/drone/delete/";*/

       // WebResource webResource = client.resource(serverAddress+path);
       // DroneListElement drone = new DroneListElement(infoDrone.getMyId(), "localhost",infoDrone.getPorta());
        //String droneToRemove = new Gson().toJson(drone);
        //webResource.type("application/json").post(ClientResponse.class, input);
        /*if(status == 200)
            System.out.println("eliminato");*/

        /*String id = String.valueOf(infoDrone.getMyId());
        clientResponse = webResource.path(id).delete(ClientResponse.class);
        if(clientResponse.getStatus() == 200)
            System.out.println("drone eliminato");*/

    }
}
