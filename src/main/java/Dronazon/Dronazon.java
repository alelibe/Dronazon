package Dronazon;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;


public class Dronazon {
    public static void main(String[] args) {
        MqttClient dronazon;
        String broker = "tcp://localhost:1883";
        String dronazonId = MqttClient.generateClientId();
        String topic = "dronazon/smartcity/orders";
        int qos = 1;

        try {
            dronazon = new MqttClient(broker, dronazonId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Connect the client
            System.out.println(dronazonId + " Connecting Broker " + broker);
            dronazon.connect(connOpts);
            System.out.println(dronazonId + " Connected");

            // Callback
            dronazon.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage message) {
                    // Not used Here
                }

                public void connectionLost(Throwable cause) {
                    System.out.println(dronazonId + " Connectionlost! cause:" + cause.getMessage());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Until the delivery is completed, messages with QoS 1 or 2 are retained from the client
                    // Delivery for a message is completed when all acknowledgments have been received
                    // When the callback returns from deliveryComplete to the main thread, the client removes the retained messages with QoS 1 or 2.
                    if (token.isComplete()) {
                        System.out.println(dronazonId + " Message delivered - Thread PID: " + Thread.currentThread().getId());
                    }
                }
            });


            while (true){
                //creo un ordine
                Random random = new Random();
                int idRandom = random.nextInt();

                int min = 1;
                int max = 10;


                int xPickUp = random.nextInt((max - min) + 1) + min;
                int yPickUp = random.nextInt((max - min) + 1) + min;

                int xDelivery = random.nextInt((max - min) + 1) + min;
                int yDelivery = random.nextInt((max - min) + 1) + min;


                Ordine ordine = new Ordine(idRandom,xPickUp,yPickUp,xDelivery,yDelivery);
                Gson gson = new Gson();
                String jsonOrdine = gson.toJson(ordine);

                MqttMessage message = new MqttMessage(jsonOrdine.getBytes());

                // Set the QoS on the Message
                message.setQos(qos);
                System.out.println(dronazonId + " Publishing message: " + jsonOrdine );
                dronazon.publish(topic, message);
                System.out.println(dronazonId + " Message published");


                Thread.sleep(5000);
            }



            /* if (dronazon.isConnected())
                dronazon.disconnect();
            System.out.println("Publisher " + dronazonId + " disconnected");*/


        }catch (MqttException  | InterruptedException me){
            //System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }


    }
}
