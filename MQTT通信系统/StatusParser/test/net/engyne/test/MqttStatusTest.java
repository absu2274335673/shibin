package net.engyne.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

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

import net.engyne.common.Constant;
import net.engyne.dao.AccountDao;
import net.engyne.dao.ClientDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MqttStatusTest 
{
	@org.junit.Test
	public void testConnection()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "connection";
		 ServerAddress serverAddress = new ServerAddress(url, port);
      List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
      serverAddresses.add(serverAddress);
      MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
      List<MongoCredential> credentials = new ArrayList<MongoCredential>();
      credentials.add(credential);
      MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
      MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
      MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
      BasicDBObject query = new BasicDBObject();
      query.put("_id", "DDBA70E1-7FF4-FF1C-E04A-D5AEFCD18566");
      FindIterable iterable;
      
//       iterable = mongoCollection.find();
//      MongoCursor cursor = iterable.iterator();
//      while (cursor.hasNext()) {
//          System.out.println(cursor.next());
//      }
//      System.out.println(iterable.first());
      iterable = mongoCollection.find(query);
      System.out.println("更新之前"+iterable.first());
      String _idobj="DDBA70E1-7FF4-FF1C-E04A-D5AEFCD18566";
			Bson filter = Filters.eq("_id", _idobj);
			
			Document newdoc = new Document();
			
			newdoc.put("clientid", "DDBA70E1-7FF4-FF1C-E04A-D5AEFCD18566");
			newdoc.put("username", "shibin");
			newdoc.put("ipaddress", "666.666.666.666");
			newdoc.put("session", "false");
			newdoc.put("protocol", "6");
			newdoc.put("connack", "0");
			newdoc.put("ts", "999999999999");
			Document doc = new Document();
			doc.put("online", newdoc);
		//	Document ndoc = new Document();
//			doc.put("status", "1");
//			doc.put("ts", "99999999999");
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
			Document newDocument =new Document();
			//newDocument.put("_id", "_idobj"); 
			newDocument.put("ts", "99999999111");
			newDocument.put("status", 0);
			mongoCollection.updateOne(filter, new Document("$set", newDocument));
		
	         iterable = mongoCollection.find(query);
	         System.out.println("更新之后"+iterable.first());
	}
	@org.junit.Test
	public void taobaoIpTest() throws HttpException, IOException
	{
		String ipaddress="111.206.166.27";
		 HttpClient httpClient = new HttpClient(); 
	    	GetMethod method =null ;
	   	   // String url = "http://dev.engyne.net/core/index.php/" +"Client/bindUser?clientid=" + clientid + "&appid=" +appid +"&username=" + username;
	    	String url="http://ip.taobao.com/service/getIpInfo.php?ip="+ipaddress;
	    	method=new GetMethod(url);
	    	int retcode = httpClient.executeMethod(method);
	    	if (retcode != HttpStatus.SC_OK)
	    	{// 发送不成功  
	          System.out.println("远程调用出错");  
	        } else {  
	        	String body = method.getResponseBodyAsString();  
	        	System.out.println(body+"远程调用taobtaoip成功");
	        	  String messagejudge=new String(body);
				   JSONObject jo=new JSONObject();
					try {
						 jo=JSONObject.fromObject(messagejudge);		
					} catch (Exception e) {							
						e.printStackTrace();
					}
					String country=jo.getJSONObject("data").getString("country");
					String province=jo.getJSONObject("data").getString("region");
					String city=jo.getJSONObject("data").getString("city");
					System.out.println(country+province+city);
					
	       } 
	       if (method != null) 
	       {  
	           method.releaseConnection();  
	       }  
	}
	@org.junit.Test
	public void testClientDao()
	{
		//ClientDao.updateClient("820B24F7-C745-3E58-307C-C5EF01A224EA", "中国", "湖北", "武汉");
	}
	@org.junit.Test
	public void connectionTest()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "connection";
		 ServerAddress serverAddress = new ServerAddress(url, port);
   List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
   serverAddresses.add(serverAddress);
   MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
   List<MongoCredential> credentials = new ArrayList<MongoCredential>();
   credentials.add(credential);
   MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
   MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
   MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		String clientid="435EA9E8-51FC-5B5E-065A-CC26075630D4";
		String username="undefined";
		String ipaddress="111.206.166.27";
		String session ="false";
		String protocol="4";
		String connack="0";
		String ts="1466578999";
	       String _idobj=clientid;
	       query.put("_id", _idobj);
	       FindIterable iterable;
	       iterable = mongoCollection.find(query);
	       System.out.println("更新connection之前"+iterable.first());
			Bson filter = Filters.eq("_id", _idobj);
			Document newdoc = new Document();
			newdoc.put("clientid", clientid);
			newdoc.put("username", username);
			newdoc.put("ipaddress", ipaddress);
			newdoc.put("session", session);
			newdoc.put("protocol", protocol);
			newdoc.put("connack",connack);
			newdoc.put("ts", ts);
			Document doc = new Document();
			doc.put("online", newdoc);
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
			Document newDocument =new Document();
			newDocument.put("ts", ts);
			newDocument.put("status", 1);
			mongoCollection.updateOne(filter, new Document("$set", newDocument));
	         iterable = mongoCollection.find(query);
	         System.out.println("更新connection之后"+iterable.first());
	}
	@org.junit.Test
	public void testDisconnection()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "connection";
		 ServerAddress serverAddress = new ServerAddress(url, port);
List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
serverAddresses.add(serverAddress);
MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
List<MongoCredential> credentials = new ArrayList<MongoCredential>();
credentials.add(credential);
MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		String clientid="435EA9E8-51FC-5B5E-065A-CC26075630D4";
		String reason="normal";
		String ts="1466578000";
	       String _idobj=clientid;
	       query.put("_id", _idobj);
	       FindIterable iterable;
	       iterable = mongoCollection.find(query);
	       System.out.println("更新disconnection之前"+iterable.first());
			Bson filter = Filters.eq("_id", _idobj);
			Document newdoc = new Document();
			newdoc.put("clientid", clientid);
			newdoc.put("reason", reason);
			newdoc.put("ts", ts);
			Document doc = new Document();
			doc.put("offline", newdoc);
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
			Document newDocument =new Document();
			newDocument.put("ts", ts);
			newDocument.put("status", 0);
			mongoCollection.updateOne(filter, new Document("$set", newDocument));
	         iterable = mongoCollection.find(query);
	         System.out.println("更新disconnection之后"+iterable.first());
	}
	@org.junit.Test
	public void clientidTest()
	{
		System.out.println("91DD0585-6840-6193-14F5-15B0CF96BF6E".length());//36
	}
	@org.junit.Test
	public void IPTest()
	{
		String ipaddress="111.206.166.27";
		System.out.println("开始解析时间"+System.currentTimeMillis()/1000);
  		 HttpClient httpClient = new HttpClient(); 
  	    	GetMethod method =null ;
  	   	   // String url = "http://dev.engyne.net/core/index.php/" +"Client/bindUser?clientid=" + clientid + "&appid=" +appid +"&username=" + username;
  	    	String url="http://dev.engyne.net/core/index.php/Client/getIpLocation/?ip="+ipaddress;
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
  	         System.out.println("远程调用出错");
  	          
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
  	        	System.out.println(body+"远程调用taobtaoip成功");
  	        	  String messagejudge=new String(body);
  				   JSONObject jo=new JSONObject();
  					try {
  						 jo=JSONObject.fromObject(messagejudge);		
  					} catch (Exception e) {							
  						e.printStackTrace();
  					}
  					JSONArray ja=jo.getJSONArray("location");
  					System.out.println(ja.get(1).toString());
  					System.out.println("解析完毕时间"+System.currentTimeMillis()/1000);
  					
  	        }
	}
	@org.junit.Test
	public void test1()
	{
		//ClientDao.updateClient("6ED7F166-A55A-C515-991A-0B226A0E0BCE", "中国", "北京", "北京");
		String s="691D153D-5773-8700-BC65-18AD77B55088RiMC";
		System.out.println(s.length());
	}
	@org.junit.Test
	public void test2()
	{
		//ClientDao.updateClient("6ED7F166-A55A-C515-991A-0B226A0E0BCE", "中国", "北京", "北京");
		String clientid ="admin-1-admin";
		AccountDao.updateAccount(clientid, 1);
	}
	
}
