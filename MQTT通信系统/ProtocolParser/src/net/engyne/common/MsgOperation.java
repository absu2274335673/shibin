package net.engyne.common;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import net.engyne.MongoDBDao.MongoDBDao;
import net.engyne.dao.AccountDao;
import net.engyne.dao.ApplicationDao;
import net.engyne.domain.Account;
import net.engyne.domain.Application;
import net.engyne.mqttprotocol.MqttProtocol;
import net.engyne.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MsgOperation {
	
	private static Logger logger = Logger.getLogger(MqttProtocol.class);

	public static String sendSysMsgToClient(String from, String errorCode, String msgCode, String msg, JSONObject data,
			MqttTopic retopic) throws MqttPersistenceException, MqttException {

		JSONObject jsonObject = new JSONObject();
		JSONObject contentJson = new JSONObject();
		contentJson.put("content", msg);
		jsonObject.put("content", contentJson);
		jsonObject.put("data", data);
		jsonObject.put("type", "system");
		jsonObject.put("msgCode", msgCode);
		jsonObject.put("errorCode", "0");
		jsonObject.put("from", FileUtil.getProperty("protocol_clientid"));
		jsonObject.put("to", from);
		logger.info(jsonObject.toString());
		MqttDeliveryToken mdt=retopic.publish(jsonObject.toString().getBytes(), 0, false);
		logger.info("握手成功消息已经发送");
		return "success";
	}

	public static String sendShakeHandInfo(String from, String convid, String appid, String pageid, MqttTopic retopic)
			throws HttpException, IOException, MqttPersistenceException, MqttException {
		logger.info("标记点5:"+System.currentTimeMillis());
		logger.info("发送最近在线的客服消息");
		List<Account> accounts=AccountDao.getOnlineStaffList(appid);
		
		logger.info("标记点8:"+System.currentTimeMillis());
		logger.info("获得system消息的其他属性 包括apptitle，welcomemsg");
		List<Application> apps=ApplicationDao.getAppinfo(appid);
		logger.info("标记点9:"+System.currentTimeMillis());
		String apptitle="";
		String welcomemsg="";
		try {
			apptitle = apps.get(0).getApptitle();
			welcomemsg = apps.get(0).getWelcomemsg();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("组装新的system消息");
		JSONObject data = new JSONObject();
		
		data.put("stafflist", accounts);
		data.put("team", apptitle);
		data.put("welcome", welcomemsg);
		data.put("appid", appid);
		data.put("pageid", pageid);
		logger.info("data拼装完成" + data + "发送同步握手消息");
		logger.info("标记点10:"+System.currentTimeMillis());
		sendSysMsgToClient(from, "0", "1005", "握手成功", data, retopic);
		logger.info("标记点11:"+System.currentTimeMillis());
		logger.info("发送未读离线消息");
		String database = "dolina";
		String collection = "messages";
		logger.info("标记点12:"+System.currentTimeMillis());
		MongoDBDao.getMongoDBDaoInstance().sendOfflineMsgToClient(from, convid, retopic, database, collection);
		logger.info("标记点17:"+System.currentTimeMillis());
		return "success";
	}
	public static String getTextMsg(String tmpindex, String from, String convid, MqttTopic retopic,String session)
			throws MqttPersistenceException, MqttException {
		//表示向收到的文本图片消息发送确认消息
		logger.info("向发送者回复ack表示系统已经收到消息");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tmpindex", tmpindex);
		jsonObject.put("type", "ack");
		jsonObject.put("from", FileUtil.getProperty("protocol_clientid"));
		jsonObject.put("to", from);
		jsonObject.put("session", session);
		jsonObject.put("convid", convid);
		jsonObject.put("time", (int) (System.currentTimeMillis() / 1000));
		logger.info("发送ack确认消息");
		MqttDeliveryToken token=retopic.publish(jsonObject.toString().getBytes(), 0, false);
		logger.info("发送消息是否完成"+token.isComplete()+"messageid:"+token.getMessageId()+" time:"+System.currentTimeMillis());
		
		return "success";
	}

}
