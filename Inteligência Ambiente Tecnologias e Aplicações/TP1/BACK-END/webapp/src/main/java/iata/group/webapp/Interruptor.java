package iata.group.webapp;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Interruptor {

    private final static String ADAFRUIT_USERNAME = "pawinha";
    private final static String ADAFRUIT_AIO_KEY = "aio_iInz38bRHEiD0I1Y0NfBfT15AmYn";
    MqttClient mqtt_client;
    String topic;
    int state;

    private List<String> interruptor = new ArrayList<String>();



    public Interruptor(String numero)  {
        topic = ADAFRUIT_USERNAME + "/feeds/Interruptor" + numero;
        int qos = 1; //QoS: 0 - at most once, 1 - at least once, 2 - exactly once
        String broker = "tcp://io.adafruit.com:1883"; //Adafruit IO broker
        String client_id = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttConnectOptions connOpts = new MqttConnectOptions();

            mqtt_client = new MqttClient("tcp://io.adafruit.com:1883", "Int",persistence);
            MqttCallback callback = new MqttCallback() {

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println(message);
                    state = Integer.parseInt(message.toString());
                    System.out.println(interruptor.size());
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

    public void changeState(){
        try {
            String msg_content;
            if (state == 1) {
                state = 0;
                msg_content = "0";
            } else {
                state = 1;
                msg_content = "1";
            }
            MqttMessage message = new MqttMessage(msg_content.getBytes());
            message.setQos(1);
            mqtt_client.publish(topic, message);
        }
        catch (Exception e){}
    }

    public int getState(){
        return state;
    }




}