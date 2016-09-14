package net.engyne.mqtt;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
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
import com.mongodb.client.result.UpdateResult;

import net.engyne.MongoDBDao.MongoDBDao;
import net.engyne.common.Constant;
import net.engyne.dao.AccountDao;
import net.engyne.dao.ClientDao;
import net.engyne.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MqttStatus
{
	private static Logger logger = Logger.getLogger(MqttStatus.class);                
//    public static final String HOST = "tcp://dev.engyne.net:1883";
//    private static final String CLIENTID= "_robot_status";
	public static final String HOST = "tcp://"+FileUtil.getProperty("mqtt_ip")+":"+FileUtil.getProperty("mqtt_port");
	 private static final String CLIENTID = FileUtil.getProperty("status_clientid");
    private MqttClient client;
    private MqttConnectOptions options = new MqttConnectOptions();
    //private String userName = "admin";
    //private String passWord = "public";
    public MqttMessage message;
    private PushCallback callback;
    /**
     * 用于初始化mqttclient客户端，设置回调函数，同时连接mqtt服务器
     * @throws MqttException
     */
       public MqttStatus() throws MqttException 
       {
           //MemoryPersistence设置clientid的保存形式，默认为以内存保存
           client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
           callback = new PushCallback();
           client.setCallback(callback);
           options = new MqttConnectOptions();
           options.setCleanSession(true);
           options.setKeepAliveInterval(60);
//           options.setUserName("_robot");
           connect();
       }
       /**
        * 连接mqtt消息服务器，同时设置了断开重连的功能
        */
       private void connect() 
       {
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
               pause();
             }
           }
       }
       private void pause() {
   	    try {
   	      Thread.sleep(1000);
   	    } catch (InterruptedException e) {
   	      // Error handling goes here...
   	    }
   	  }
       /**
        *  
        * @param topic
        * @param qos
        * @throws MqttPersistenceException
        * @throws MqttException
        * 订阅相关主题
        */
       public void subscribe(String topic , int qos) throws MqttPersistenceException,
       		MqttException 
       {
      	 client.subscribe(topic, qos);    	
       }
       /**
        * 
        * @throws MqttPersistenceException
        * @throws MqttException
        * 断开连接服务器
        */
       public void disconnect() throws MqttPersistenceException,
  		MqttException 
  	 {
      	 client.disconnect();
       }
       /**
        * 
        * @author binshi
        *实现mqttcallback接口，主要用于接收消息后的处理方法
        */
       private class PushCallback implements MqttCallback { 
      	 /**
      	  * 断开后 系统会自动调用这个函数，同时在这个函数里进行重连操作
      	  */
   	    public void connectionLost(Throwable cause) {  
   	        // 连接丢失后，一般在这里面进行重连  
   	    	logger.info("连接断开，可以做重连");  
   	        connect();
   	        try {
				subscribe(Constant.TOPIC_MQTT_CONNECTED, 2);
				subscribe(Constant.TOPIC_MQTT_DISCONNECTED, 2);
			} catch (MqttPersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	        
   	    } 
   	    /**
   	     * 消息成功传送后，系统会自动调用此函数，表明成功向topic发送消息
   	     */
   		@Override
  		public void deliveryComplete(IMqttDeliveryToken arg0) {
  			// TODO Auto-generated method stub
   			logger.info("deliveryComplete---------" + arg0.isComplete());
  		}
   		
   		
   		
   		
   		public JSONObject ipParser(String ipaddress) 
   		{
   			logger.info("开始解析时间"+System.currentTimeMillis()/1000);
   			HttpClient httpClient = new HttpClient(); 
   	    	GetMethod method =null ;
   	   	    // String url = "http://dev.engyne.net/core/index.php/" +"Client/bindUser?clientid=" + clientid + "&appid=" +appid +"&username=" + username;
   	    	String url=Constant.IP_MQTT_URL+ipaddress;
   	    	method=new GetMethod(url);
   	    	int retcode=0;
			try {
				retcode = httpClient.executeMethod(method);
			} catch (HttpException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   	    	if (retcode != HttpStatus.SC_OK)
   	    	{// 发送不成功  
   	         logger.info("远程调用出错");  
   	          return null;
   	        } 
   	    	else 
   	    	{  
   	        	String body=null;
				try {
					body = method.getResponseBodyAsString();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
   	        	logger.info(body+"远程调用ip解析成功");
   	        	  String messagejudge=new String(body);
   				   JSONObject jo=new JSONObject();
   					try {
   						 jo=JSONObject.fromObject(messagejudge);		
   					} catch (Exception e) {							
   						e.printStackTrace();
   					}
   					logger.info("解析完毕时间"+System.currentTimeMillis()/1000);
   					return jo;
   	        }
   		}
   		public void messageArrived(String topic, MqttMessage message) throws Exception
   				{
   					logger.info(topic);
   			    	SimpleDateFormat sdf= new SimpleDateFormat(Constant.DATE_FORMAT_MDYHMS);
   			    	logger.info(sdf.format(System.currentTimeMillis()));
   			    	logger.info("接收消息主题 : " + topic);  
   			    	logger.info("接收消息Qos : " + message.getQos());  
   			    	logger.info("接收消息内容 : " + new String(message.getPayload()));
   			    	//1 抽取事件信令消息
   			    	logger.info("标记点1:"+System.currentTimeMillis());
 				    String messagejudge=new String(message.getPayload());
 				   JSONObject jo=new JSONObject();
					try {
						 jo=JSONObject.fromObject(messagejudge);		
					} catch (Exception e) {							
						e.printStackTrace();
					}
					String clientid=jo.getString("clientid");
					logger.info("clientid "+clientid);
					//存储除了robot之外的其他消息
					logger.info("标记点2:"+System.currentTimeMillis());
					if(!clientid.contains("robot"))
					{
						//1处理connected消息
						if(topic.contains("disconnected"))
						{
							String reason="";
							String ts="";
							String database="";
							String collection="";
							try {
								reason = jo.getString("reason");
								ts = jo.getString("ts");
								database = "dolina";
								collection = "connection";
							} catch (Exception e) {
								e.printStackTrace();
							}
					   	    try {
					   	    	logger.info("标记点3:"+System.currentTimeMillis());
								MongoDBDao.getMongoDBDaoInstance().saveMongoDisconnection( database,collection, clientid, reason, ts);
							} catch (Exception e) {
								e.printStackTrace();
							}
					   	 logger.info("标记点4:"+System.currentTimeMillis());
					   	    if(clientid.startsWith("_webadmin"))
		   					{
					   	     logger.info("更新mysql数据库的在线信息");
		   						AccountDao.updateAccount(clientid, 0);
		   					}
					   	    else
					   	    {
					   	    	logger.info("更新client表 online字段 的离线状态");
					   	    	ClientDao.updateClientOnline(clientid, 0);
					   	    }
					   	 logger.info("标记点5:"+System.currentTimeMillis());
						}
						else
						{
							String username="";
							String ipaddress="";
							String session="";
							String protocol="";
							String connack="";
							String ts="";
							try {
								username = jo.getString("username");
								ipaddress = jo.getString("ipaddress");
								session = jo.getString("session");
								protocol = jo.getString("protocol");
								connack = jo.getString("connack");
								ts = jo.getString("ts");
							} catch (Exception e) {
								e.printStackTrace();
							}
							logger.info("存入connection连接消息");
							String database="dolina";
							String collection="connection";
							logger.info("标记点6:"+System.currentTimeMillis());
					   	    try {
								MongoDBDao.getMongoDBDaoInstance().saveMongoConnection( database,collection, clientid, username, ipaddress, session, protocol, connack, ts);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
					   	 logger.info("标记点7:"+System.currentTimeMillis());
					   	    logger.info("解析ip地址更新用户信息");
					   	    JSONObject json=ipParser(ipaddress);
					   	    JSONArray ja=json.getJSONArray("location");
					   	    String country=ja.get(0).toString();
		   					String province=ja.get(1).toString();
		   					String city=ja.get(2).toString();
		   					logger.info(clientid+country+province+city);
		   					logger.info("如果是后台客服的话不需要更新");
		   					logger.info("标记点8:"+System.currentTimeMillis());
		   					if(!clientid.startsWith("_webadmin"))
		   					{
		   						logger.info("不是后台客服 是普通用户 需要更新国家省份城市时间在线状态");
		   						ClientDao.updateClient(clientid, country, province, city,System.currentTimeMillis()/1000+"",1);
		   					}
		   					
		   					if(clientid.startsWith("_webadmin"))
		   					{
		   						logger.info("更新客服account表的在线online状态");
		   						AccountDao.updateAccount(clientid, 1);
		   					}
		   					logger.info("标记点9:"+System.currentTimeMillis());
		   					
						}
					}
					
 				   
   				}
   	}
       /**
        * 
        * @param args
        * @throws MqttException
        * 整个工程从这里开始执行，生成可执行jar包，这个设置为主类。
        */
       public static void main(String[] args) throws MqttException 
       {  
    	  String path=new File("").getAbsoluteFile().getParentFile().toString();
   		  System.out.println("path:"+path);
   		  PropertyConfigurator.configure(path+"/config/log4j.properties");
      	   MqttStatus status = new MqttStatus();
           status.message = new MqttMessage();
           /**
           server.message.setQos(2);
           server.message.setRetained(false);
           server.message.setPayload("给客户端124推送的信息".getBytes()); 
           server.subscribe("/engyne/1/7/169573fcbc96a816281192222", 2);
           */
           status.subscribe(Constant.TOPIC_MQTT_CONNECTED, 0);
           status.subscribe(Constant.TOPIC_MQTT_DISCONNECTED, 0);
           logger.info(status.message.isRetained() + "------ratained状态");
       }
}
