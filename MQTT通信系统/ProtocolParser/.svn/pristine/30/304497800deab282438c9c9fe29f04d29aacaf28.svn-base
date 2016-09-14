package net.engyne.common;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import net.engyne.mqttprotocol.MqttProtocol;
import net.sf.json.JSONObject;

public class RemoteOperation {
	
	private static Logger logger = Logger.getLogger(MqttProtocol.class);

	public static JSONObject remoteCall(String url) throws HttpException, IOException {
		
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		int retcode = httpClient.executeMethod(method);
		if (retcode != HttpStatus.SC_OK) {// 发送不成功
			logger.info("远程调用出错");
			return null;
		} else {
			String body = method.getResponseBodyAsString();
			logger.info(body + "远程调用php成功");
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = JSONObject.fromObject(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (method != null) {
				method.releaseConnection();
			}
			return jsonObject;
		}

	}
}
