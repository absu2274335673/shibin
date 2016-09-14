package net.engyne.test;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import net.engyne.common.Constant;
import net.engyne.mqqt.MqttServer;

public class MqttTest 
{
	private static Logger logger = Logger.getLogger(MqttServer.class); 
	@org.junit.Test
	public void mqttTest() throws MqttException
	{
		MqttServer server = new MqttServer();
        server.message = new MqttMessage();
        /**
        server.message.setQos(2);
        server.message.setRetained(false);
        server.message.setPayload("给客户端124推送的信息".getBytes()); 
        server.subscribe("/engyne/1/7/169573fcbc96a816281192222", 2);
        */
        server.subscribe(Constant.TOPIC_MQTT_SIGNAL, 2);
        logger.info(server.message.isRetained() + "------ratained状态");
	}
}
