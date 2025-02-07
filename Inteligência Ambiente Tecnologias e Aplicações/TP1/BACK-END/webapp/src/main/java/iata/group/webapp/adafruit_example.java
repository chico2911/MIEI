package iata.group.webapp;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class adafruit_example {

	private final static String ADAFRUIT_USERNAME = "pawinha";
	private final static String ADAFRUIT_AIO_KEY = "aio_iInz38bRHEiD0I1Y0NfBfT15AmYn";

	private List<String> temperaturas = new ArrayList<String>();

	public void adafruit(String[] args) throws Exception{String topic = ADAFRUIT_USERNAME + "/feeds/IATATemperatura";
		String msg_content = "Hello from java (not the island)!";
		int qos = 1; //QoS: 0 - at most once, 1 - at least once, 2 - exactly once
		String broker = "tcp://io.adafruit.com:1883"; //Adafruit IO broker
		String client_id = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			MqttClient mqtt_client = new MqttClient(broker, client_id, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName(ADAFRUIT_USERNAME);
			connOpts.setPassword(ADAFRUIT_AIO_KEY.toCharArray());
			System.out.println("Connecting to broker: " + broker);
			mqtt_client.connect(connOpts);
			System.out.println("Connected. Publishing message: " + msg_content);
			MqttMessage message = new MqttMessage(msg_content.getBytes());
			message.setQos(qos);
			mqtt_client.publish(topic, message);
			System.out.println("Message published");
			mqtt_client.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		}
		catch(MqttException me) {
			System.out.println("reason: " + me.getReasonCode());
			System.out.println("msg: " + me.getMessage());
			System.out.println("loc: " + me.getLocalizedMessage());
			System.out.println("cause: " + me.getCause());
			System.out.println("excep: " + me);
			me.printStackTrace();
		}
	}
}