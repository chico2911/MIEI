package iata.group.webapp;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Autoclismo {

    private final static String ADAFRUIT_USERNAME = "pawinha";
    private final static String ADAFRUIT_AIO_KEY = "aio_iInz38bRHEiD0I1Y0NfBfT15AmYn";

    private List<String> autoclismo = new ArrayList<String>();



    public Autoclismo()  {String topic = ADAFRUIT_USERNAME + "/feeds/autoclismo";
        String msg_content = "Hello from java (not the island)!";
        int qos = 1; //QoS: 0 - at most once, 1 - at least once, 2 - exactly once
        String broker = "tcp://io.adafruit.com:1883"; //Adafruit IO broker
        String client_id = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttConnectOptions connOpts = new MqttConnectOptions();

            MqttClient mqtt_client = new MqttClient("tcp://io.adafruit.com:1883", "Aut",persistence);
            MqttCallback callback = new MqttCallback() {

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println(message);
                    if(message.equals("reset"))
                        autoclismo = new ArrayList<>();
                    autoclismo.add(message.toString());
                    System.out.println(autoclismo.size());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }

                @Override
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                }
            };



            connOpts.setCleanSession(true);
            connOpts.setUserName(ADAFRUIT_USERNAME);
            connOpts.setPassword(ADAFRUIT_AIO_KEY.toCharArray());
            System.out.println("Connecting to broker: " + broker);
            mqtt_client.connect(connOpts);
            mqtt_client.subscribe(topic);
            mqtt_client.setCallback(callback);
        }
        catch(MqttException me) {

            System.out.println("msg: " + me.getMessage());
            System.out.println("loc: " + me.getLocalizedMessage());
            System.out.println("cause: " + me.getCause());
            System.out.println("excep: " + me);
            me.printStackTrace();
        }
    }

    public int totaldescargas(){
        return autoclismo.size();
    }

    public int totalgasto(){
        return autoclismo.size()*7;
    }


}