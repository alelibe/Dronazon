package ClientAmministratore;

import ServerAmministratore.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientAmm {
    public static void main(String[] args)  {
        //Client client = Client.create();
        ClientResponse clientResponse;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(clientConfig);

        System.out.println("I servizi offerti dal server sono i seguenti:\n"
                    + "Elenco dei droni presenti nella rete, premere E\n"
                    + "Ultime n statistiche globali, premere S\n"
                    + "Media del numero di consegne effettuate dai droni tra due timestamp t1 e t2, premere C\n"
                    + "Media dei km percorsi dai droni tra due timestamp t1 e t2, premere K\n"
                    + "*************************************\n"
                    + "Scrivere quit per uscire");


        while(true) {
            System.out.println(">>>SCEGLIERE UNO DEI SERVIZI<<<<");


            Scanner scanner = new Scanner(System.in);

            String servizio = scanner.nextLine();
            if (servizio.equals("E")) {
                String getPath = "/drone/";
                WebResource webResource = client.resource("http://localhost:1337" + getPath);
                clientResponse = webResource.type("application/json").get(ClientResponse.class);
                DroneList droneList = clientResponse.getEntity(DroneList.class);
                for (DroneListElement d : droneList.getDroneList()) {
                    System.out.println(d.toString());
                }

            }

            if (servizio.equals("S")) {
                System.out.println("Inserire n");
                String n = scanner.nextLine();
                String getPath = "/stat/get/" + n;
                WebResource webResource = client.resource("http://localhost:1337" + getPath);
                //clientResponse = webResource.path(n).type("application/json").get(ClientResponse.class);
                clientResponse = webResource.type("application/json").get(ClientResponse.class);
                if(clientResponse.getStatus() == 400)
                    System.out.println("BAD-REQUEST");
                else {
                    ArrayList<Statistics> prova = clientResponse.getEntity(new GenericType<ArrayList<Statistics>>(){});

                    System.out.println("Le ultime " + n + " statistiche sono le seguenti:\n");
                    for (Statistics s : prova) {
                        System.out.println(s.toString());
                    }
                }
            }

            if (servizio.equals("C")) {
                System.out.println("Inserire timestamp t1");
                String t1 = scanner.nextLine();
                System.out.println("Inserire timestamp t2");
                String t2 = scanner.nextLine();

                String getPath = "/stat/get_delivery/";
                WebResource webResource = client.resource("http://localhost:1337" + getPath);
                clientResponse = webResource.queryParam("from",t1).queryParam("to",t2).type("text/plain").get(ClientResponse.class);
                String result = clientResponse.getEntity(String.class);
                if(clientResponse.getStatus() == 400)
                    System.out.println("BAD-REQUEST");
                else
                    System.out.println("La media delle consegne è: " + result);

            }

            if(servizio.equals("K")){
                System.out.println("Inserire timestamp t1");
                String t1 = scanner.nextLine();
                System.out.println("Inserire timestamp t2");
                String t2 = scanner.nextLine();

                String getPath = "/stat/get_km/";
                WebResource webResource = client.resource("http://localhost:1337" + getPath);
                clientResponse = webResource.queryParam("from",t1).queryParam("to",t2).type("text/plain").get(ClientResponse.class);
                String result = clientResponse.getEntity(String.class);
                if(clientResponse.getStatus() == 400)
                    System.out.println("BAD-REQUEST");
                else
                    System.out.println("La media dei km è: " + result);
            }

            if(servizio.equals("quit"))
                break;
        }



    }
}
