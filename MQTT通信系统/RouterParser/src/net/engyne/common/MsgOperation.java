package net.engyne.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import net.engyne.accountdao.AccountDao;
import net.engyne.conversationdao.ConversationDao;
import net.engyne.domain.Account;
import net.engyne.domain.Conversation;
import net.sf.json.JSONObject;

public class MsgOperation
{
	 private static Logger logger = Logger.getLogger(MsgOperation.class);  
	public static void sendTextMsgToClient(MqttTopic retopic)
	{
		JSONObject jsonObject = new JSONObject();
		JSONObject contentJson = new JSONObject();
		contentJson.put("content", "您好 请留言，客服处于离线状态");
		jsonObject.put("content", contentJson);
		jsonObject.put("type", "text");
		jsonObject.put("errorCode", "0");
		jsonObject.put("from", "_robot_router");
		jsonObject.put("time", System.currentTimeMillis()/1000);
		JSONObject extra=new JSONObject();
		extra.put("headimgurl", "");
		extra.put("nickname", "");
		extra.put("admin", "2");
		jsonObject.put("extra", extra);
		logger.info(jsonObject.toString());
		try {
			MqttDeliveryToken mdt=retopic.publish(jsonObject.toString().getBytes(), 0, false);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("无客服在线消息已经发送");
	}
	public static void MsgToRandomCustomerService(List<Account> accounts,String convid,String usertype,String teamid)
	{
		logger.info("首先查找这个convid对应的记录，如果记录中username存在且目前在线 就什么都不做");
		ArrayList<String> usernames=new ArrayList<String>();
		for(int i=0;i<accounts.size();i++)
		{
			logger.info(accounts.get(i).getUsername());
			usernames.add(accounts.get(i).getUsername());
		}
		List<Conversation> conversations=ConversationDao.findConversation(convid);
		String usernameIn=null;
		if(conversations.size()>0)
		{
		 usernameIn=conversations.get(0).getUsername();
		}
		if(usernameIn!=null&&!usernameIn.equals("")&&usernames.toString().contains(usernameIn))
		{
			logger.info("已经为这个会话分配过客服且目前客服在线");
		}
		else
		{
			if(usertype.equals("0"))
			{
				logger.info("Assign customer service to visitors and the customer belongs to 'befor sale' or 'all the same' ");
				List<Account> account4Visitor=new ArrayList<Account>();
				for(int i=0;i<accounts.size();i++)
				{
					//1 before sale,2 after sale,3 same
					String account_type=accounts.get(i).getAccount_type();
					if(account_type.equals("1")||account_type.equals("3"))
					{
						account4Visitor.add(accounts.get(i));
					}
				}
				if(account4Visitor.size()==0)
				{
					String username=AccountDao.getDefaultStaff(teamid, 1).get(0).getUsername();
					logger.info("最后分配的默认客服为"+username);
					ConversationDao.updateConversation(convid, username);
				}
				else
				{
					Random rand = new Random();
					int randNum = rand.nextInt(account4Visitor.size());
					String usernameRand=account4Visitor.get(randNum).getUsername();
					logger.info("最后分配的客服为"+usernameRand);
					ConversationDao.updateConversation(convid, usernameRand);
				}
				
				
			}
			else
			{
				logger.info("Assign customer service to registers and the customer belongs to 'after sale' or 'all the same' ");

				List<Account> account4Register=new ArrayList<Account>();
				for(int i=0;i<accounts.size();i++)
				{
					//1 before sale,2 after sale,3 same
					String account_type=accounts.get(i).getAccount_type();
					if(account_type.equals("2")||account_type.equals("3"))
					{
						account4Register.add(accounts.get(i));
					}
				}
				if(account4Register.size()==0)
				{
					String username=AccountDao.getDefaultStaff(teamid, 1).get(0).getUsername();
					logger.info("最后分配的默认客服为"+username);
					ConversationDao.updateConversation(convid, username);
				}
				else
				{
					Random rand = new Random();
					int randNum = rand.nextInt(account4Register.size());
					String usernameRand=account4Register.get(randNum).getUsername();
					logger.info("最后分配的客服为"+usernameRand);
					ConversationDao.updateConversation(convid, usernameRand);
				}
			}
			
			
		}
		
	}
	public static void sendNotifyMsgToService(MqttTopic retopic,JSONObject data,String username)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "system");
		jsonObject.put("from","_robot_router");
		jsonObject.put("to", username);
		jsonObject.put("errorCode", "0");
		jsonObject.put("msgCode", "1006");
		jsonObject.put("data", data);
		logger.info(jsonObject.toString());
		try {
			MqttDeliveryToken mdt=retopic.publish(jsonObject.toString().getBytes(), 0, false);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("客服通知消息消息已经发送");
	}
}
