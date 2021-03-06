package net.engyne.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import net.engyne.MongoDBDao.MongoDBDao;
import net.engyne.common.Constant;
import net.engyne.dao.ClientDao;
import net.engyne.mqttprotocol.MqttProtocol;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test 
{
	private static Logger logger = Logger.getLogger(ProtocolTest.class);  
	@org.junit.Test
	public void test1() throws MqttException
	{
	     String HOST = "tcp://dev.engyne.net:1883";
	 String CLIENTID = "_robot_protocol";
	     MqttClient client;
	    MqttConnectOptions options = new MqttConnectOptions();
	    //private String userName = "admin";
	    //private String passWord = "public";
	     MqttMessage message;
	 
           //MemoryPersistence设置clientid的保存形式，默认为以内存保存
           client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
           options = new MqttConnectOptions();
           options.setCleanSession(false);
           options.setKeepAliveInterval(60);
       
      	 SimpleDateFormat sdf= new SimpleDateFormat(Constant.DATE_FORMAT_MDYHMS);
      	 logger.info(sdf.format(System.currentTimeMillis()));
           boolean tryConnecting = true;
           while (tryConnecting) {
             try {
               client.connect(options);
             } catch (Exception e1) {
          	   logger.info("Connection attempt failed with '"+e1.getCause()+
                    "'. Retrying.");
             }
             if (client.isConnected()) {
          	   logger.info("Connected.");
               tryConnecting = false;
             } else {
              
             }
           }
       
     String database="dolina";
     String collection="messages";
		 String convid = "169574424c181626473982382";
		 String  from = "DC6479CA-28B2-0040-EDE9-824850EA8473";
		 String topic =" /engyne/1/3/169574424c181626473982382";
		 MqttTopic retopic=client.getTopic(topic);
		MongoDBDao.getMongoDBDaoInstance().sendOfflineMsgToClient(from, convid, retopic, database, collection);
	}
	@org.junit.Test
	public void test2()
	{
		String tmpindex="1467016148604ab5";
		String convid="169576b6ef65af4b870834331";
		String from="B2B95FE5-2F51-9159-7416-FD10E1F26FA6";
		String database="dolina";
		String collection="messages";
		MongoDBDao.getMongoDBDaoInstance().getAck(tmpindex, convid, from, database, collection);
	}
	@org.junit.Test
	public void test3()
	{
		System.out.println("cccccc");
		JSONObject json1 = new JSONObject();
		json1.put("channel", "web5");
		JSONObject json2 = new JSONObject();
		json2.put("os", "win101");
		json2.put("navigator","chrome111");
		json1.put("referrer", "www.baidu.com");
		json1.put("deviceinfo", json2);
		json1.remove("referrer");
		Document docc=Document.parse(json1.toString());
		System.out.println("aaaaaa");
		System.out.println(json1.toString());
		MongoDBDao.getMongoDBDaoInstance().updateLoginfo("dolina", "loginfo", "admin", "", "2", docc);
	}
	@org.junit.Test
	public void test4()
	{
		JSONObject jo=new JSONObject();
		jo.put("name", "zhangfei");
		String name="aaa";
		try {
			 name=jo.getString("nam");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("kokokokoooo");
		System.out.println(name);
	}
	@org.junit.Test
	public void test5()
	{
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		System.out.println(ja);
	}
	@org.junit.Test
	public void test6()
	{
//		{"type":"text","from":"7C250CB0-18D8-106E-CED3-7C111160CEE8",
//		"session":"7C250CB0-18D8-106E-CED3-7C111160CEE8","convid":"16957a1abab2d720964585772",
//		"tmpindex":"1470367477684R2c","time":1470367478,"content":{"content":"nnnnnnnnnnnnnnnnnnnnnnn"},
//		"extra":{"headimgurl":"","nickname":"","admin":0}}
		JSONObject jo=new JSONObject();
		jo.put("type", "text");
		jo.put("from", "7C250CB0-18D8-106E-CED3-7C111160CEE8");
		jo.put("session", "7C250CB0-18D8-106E-CED3-7C111160CEE8");
		jo.put("convid", "16957a1abab2d720964585772");
		jo.put("tmpindex", "1470367477684R2i");
		jo.put("time", "1470367478");
		JSONObject content=new JSONObject();
		content.put("content", "888888888888888888");
		jo.put("content", content);
		JSONObject extra=new JSONObject();
		extra.put("headimgurl", "");
		extra.put("nickname", "");
		extra.put("admin", "0");
		jo.put("extra", extra);
		MongoDBDao.getMongoDBDaoInstance().saveTextMsg("dolina", "messages", jo);
		
	}
	
}
