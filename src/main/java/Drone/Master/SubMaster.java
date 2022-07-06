package Drone.Master;

import Dronazon.Ordine;
import Drone.Master.QueueOrdini;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.Timestamp;

public class SubMaster extends Thread{
    private QueueOrdini queueOrdini;
    private MqttClient client;

    public SubMaster(QueueOrdini queueOrdini){
       this.queueOrdini = queueOrdini;
    }

    public void run() {
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smartcity/orders";
        int qos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Connect the client
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);


            // Callback
            client.setCallback(new MqttCallback() {

                public void messageArrived(String topic, MqttMessage message) {
                    // Called when a message arrives from the server that matches any subscription made by the client
                    String time = new Timestamp(System.currentTimeMillis()).toString();
                    String receivedMessage = new String(message.getPayload());
                    String ordine = receivedMessage.toString();
                    Gson gson = new Gson();

                    Ordine newOrdine = gson.fromJson(ordine,Ordine.class);
                    queueOrdini.put(newOrdine);


                }

                public void connectionLost(Throwable cause) {
                    System.out.println(clientId + " Connectionlost! cause:" + cause.getMessage() + "-  Thread PID: " + Thread.currentThread().getId());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used here
                }

            });


            client.subscribe(topic,qos);



        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }


    public void waitExit(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
