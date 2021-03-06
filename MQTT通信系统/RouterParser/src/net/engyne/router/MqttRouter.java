package net.engyne.router;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import net.engyne.accountdao.AccountDao;
import net.engyne.clientdao.ClientDao;
import net.engyne.common.Constant;
import net.engyne.common.CustomService;
import net.engyne.common.MsgOperation;
import net.engyne.common.RemoteOperation;
import net.engyne.conversationdao.ConversationDao;
import net.engyne.domain.Account;
import net.engyne.domain.Conversation;
import net.engyne.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MqttRouter
{
	 private static Logger logger = Logger.getLogger(MqttRouter.class);  
//   public static final String HOST = "tcp://dev.engyne.net:1883";
//   private static final String clientid = "_robot_rule";
	 public static final String HOST = "tcp://"+FileUtil.getProperty("mqtt_ip")+":"+FileUtil.getProperty("mqtt_port");
	 private static final String CLIENTID = FileUtil.getProperty("router_clientid");
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
   public MqttRouter() throws MqttException 
   {
       //MemoryPersistence设置clientid的保存形式，默认为以内存保存
       client = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
       callback = new PushCallback();
       client.setCallback(callback);
       options = new MqttConnectOptions();
       options.setCleanSession(false);
       options.setKeepAliveInterval(60);
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
    * @param message
    * @throws MqttPersistenceException
    * @throws MqttException
    * 用于向特定topic发送message
    */
   public  void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
           MqttException 
   {
       MqttDeliveryToken token = topic.publish(message);
       token.waitForCompletion();
       logger.info("message is published completely! "+ token.isComplete());  
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
				subscribe(Constant.TOPIC_MQTT_ROUTER, 2);
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
		
		@Override
		/**
		 * 这个是主要的消息处理函数，在我们订阅的topic接收到消息时候，函数会执行到这里，我们在这个函数
		 * 进行各种复杂的事件处理，这也是我们这个功能的主要实现目标，下面整体的业务逻辑是这样，首先我们
		 * 根据接收到的事件信令消息，根据信令中的pageid和event去查表rule，找到相关规则时候，我们根据
		 * 这个规则去查找这些规则所对应的filter过滤条件，结合clientid，这个clientid也是信令消息里面
		 * 的一个属性，clientid结合filter去查找view_user_full，如果能找到满足条件的用户，我们就找到
		 * 这个规则对应的消息模板，同时看清楚这个once表示是第一次发还是每次都发。至此完成整个业务逻辑，上诉任何条件
		 * 不满足，则不会发送消息
		 */
		public void messageArrived(String topic, MqttMessage message) throws Exception
				{
					logger.info(topic);
			    	SimpleDateFormat sdf= new SimpleDateFormat(Constant.DATE_FORMAT_MDYHMS);
			    	logger.info(sdf.format(System.currentTimeMillis()));
			    	logger.info("接收消息主题 : " + topic);  
			    	logger.info("接收消息Qos : " + message.getQos());  
			    	logger.info("接收消息内容 : " + new String(message.getPayload()));
				     //1 抽取事件信令消息
			    	logger.info("标记点1："+System.currentTimeMillis());
				    String messagejudge=new String(message.getPayload());
			    	logger.info("路由模块只处理非客服的握手消息");				    	
			    	JSONObject jo=new JSONObject();
					try 
					{
						 jo=JSONObject.fromObject(messagejudge);		
					} catch (Exception e) 
					{							
						e.printStackTrace();
					}
					String type=jo.getString("type");
					logger.info("type"+type);
					if(!jo.toString().contains("robot")&&!jo.toString().contains("offline"))
					{
						logger.info("处理非系统消息");
						if(type.equals("shakehand"))
						{
							logger.info("处理shakehand消息");
							String admin="doyounkowwhy";
							if(jo.has("admin"))
							{
								admin=jo.getString("admin");
							}
							logger.info("取得admin 如果为1定义为客服，否则为普通用户 admin为"+admin);
							if(admin.equals("0"))
							{
								logger.info("处理普通用户的握手消息,首先获得在线客服信息，然后将该消息随机分配给一个客服");
								logger.info("获得最近在线的客服消息");
								ArrayList<String> usernames=new ArrayList<String>();
								String teamid=topic.split("/")[2];
								//usernames=CustomService.getOnlineService(teamid);
								logger.info("通过mysql查询在线客服信息 teamid 为"+teamid);
								List<Account> accounts=AccountDao.getStaffList(teamid, 1);
								for(int i=0;i<accounts.size();i++)
								{
									logger.info(accounts.get(i).getUsername());
									usernames.add(accounts.get(i).getUsername());
								}
								logger.info(usernames.size());
								String convid="";
								String from="";
								try {
									convid = jo.getString("convid");
									from = jo.getString("from");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if(usernames.size()==0)
								{
									logger.info("没有一个在线客服，发送模板消息");
									logger.info("客服都不在线的话就交给默认客服处理。首先找到这个teamid对应的默认客服");
									String username=AccountDao.getDefaultStaff(teamid, 1).get(0).getUsername();
									logger.info("最后分配的默认客服为"+username);
									ConversationDao.updateConversation(convid, username);
								}
								else
								{
									logger.info("确定用户的usertype");
									String usertype=ClientDao.getUsertype(from).get(0).getUsertype();
									MsgOperation.MsgToRandomCustomerService( accounts, convid,usertype,teamid);
								}
							}
						}
						else if(type.equals("text")||type.equals("image"))
						{
							logger.info("接收到用户的消息后向客服发送一个系统通知消息");
							String admin="doyounkowwhy";
							if(jo.toString().contains("admin"))
							{
								admin=jo.getJSONObject("extra").getString("admin");
							}
							logger.info("取得admin 如果为1定义为客服，否则为普通用户 admin为"+admin);
							if(admin.equals("0"))
							{
								logger.info("处理普通用户的文本图片消息，转发给对应的客服");
								logger.info("首先获得该消息的convid，然后找到对应客服的username，然后转发给该客服");
								String convid="";
								try {
									convid = jo.getString("convid");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								List<Conversation> conversations=ConversationDao.findConversation(convid);
								String username=conversations.get(0).getUsername();
								String teamid=conversations.get(0).getTeamid();
								logger.info("拼接topic");
								String arr[]=topic.split("/");
								String topic2="/engyne/"+teamid+"/"+arr[3]+"/"+username;
								logger.info(topic2);
								MqttTopic retopic=client.getTopic(topic2);
								logger.info("向该客服发送这条通知消息");
								MsgOperation.sendNotifyMsgToService(retopic, jo, username);
							}
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
  	 	MqttRouter router = new MqttRouter();
        router.message = new MqttMessage();
       /**
       server.message.setQos(2);
       server.message.setRetained(false);
       server.message.setPayload("给客户端124推送的信息".getBytes()); 
       server.subscribe("/engyne/1/7/169573fcbc96a816281192222", 2);
       */
       router.subscribe(Constant.TOPIC_MQTT_ROUTER, 2);
       logger.info(router.message.isRetained() + "------ratained状态");
	}
}
