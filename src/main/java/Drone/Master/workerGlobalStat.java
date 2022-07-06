package Drone.Master;

import Drone.Drone;
import ServerAmministratore.Statistics;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class workerGlobalStat extends Thread{
    private GlobalStat globalStat;
    private InfoMaster infoMaster;

    public workerGlobalStat(GlobalStat globalStat,InfoMaster infoMaster){
        this.globalStat = globalStat;
        this.infoMaster = infoMaster;
    }


    public void run() {

        while(Drone.sendStat == 0){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int num_droni = infoMaster.getInfoMaster().size();
            float average_delivery;
            float average_km;
            float average_battery;

            if(num_droni != 0) {
                average_delivery = globalStat.getNum_delivery() / num_droni;
                average_km = (globalStat.getKm() / num_droni);
                average_battery = globalStat.getBattery() / num_droni;
            }
            else {
                average_delivery = globalStat.getNum_delivery();
                average_km = globalStat.getKm();
                average_battery = globalStat.getBattery();
            }

            float average_pollution = 0;
            float somma = 0;
            if(globalStat.getPollution().size() > 0) {
                for (int i = 0; i < globalStat.getPollution().size(); i++) {
                    somma += globalStat.getPollution().get(i);
                }

                average_pollution = somma / globalStat.getPollution().size();
            }


            long timestamp = System.currentTimeMillis();


            //invio le statistiche al server amministratore
            Client client = Client.create();
            ClientResponse clientResponse = null;

            //POST - Chiedo al server amministratore di aggiungere le statistiche
            String postPath = "/stat/add";
            Statistics statistics = new Statistics(timestamp,average_delivery,average_km,average_pollution,average_battery);
            WebResource webResource = client.resource("http://localhost:1337"+postPath);
            String input = new Gson().toJson(statistics);

            clientResponse = webResource.type("application/json").post(ClientResponse.class,input);
            if(clientResponse.getStatus() == 200)
                System.out.println(">>>STATISTICHE AGGIUNTE NEL SERVER<<<");



        }



    }
}
