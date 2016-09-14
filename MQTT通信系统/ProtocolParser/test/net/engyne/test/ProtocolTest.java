package net.engyne.test;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.w3c.dom.traversal.DocumentTraversal;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import net.engyne.common.Constant;
import net.engyne.mqttprotocol.MqttProtocol;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProtocolTest 
{
	private static Logger logger = Logger.getLogger(ProtocolTest.class);  
	@org.junit.Test
	public void JsonTest()
	{
		String str=null;
		JSONObject jo=new JSONObject();
		
		jo.put("city", "ei");
		String jsonstr=jo.toString();
		if(jsonstr.contains("offline"))
		{
			
		}
		str=jo.getString("pro");
		System.out.println(str);
		
		System.out.println(str+"kk");
	}
	@org.junit.Test
	public void mongoTest()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "messages";
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
      query.put("_id", "1695745803f0bf37039578675");
      FindIterable<Document> iterable;
      BasicDBObject field = new BasicDBObject();
      DBCursor cursor = null;  
      
      field.put("file_list", 1);   
      iterable = mongoCollection.find(query);
      
      String res= iterable.first().toJson();
//      res=res.replaceAll("Document", "");
//     res= res.replaceAll("=", ":");
      System.out.println(res);
      JSONObject jo=new JSONObject();
		try {
			 jo=JSONObject.fromObject(res);		
		} catch (Exception e) {							
			e.printStackTrace();
		}
		System.out.println(jo.toString());
      System.out.println(jo.getJSONArray("msg").getJSONObject(0).getString("read"));
	}
	public JSONObject remoteCall(String url) throws HttpException, IOException
	{
		HttpClient httpClient = new HttpClient(); 
    	GetMethod method =null ;
    	method=new GetMethod(url);
    	int retcode = httpClient.executeMethod(method);
    	if (retcode != HttpStatus.SC_OK)
    	{// 发送不成功  
          logger.info("远程调用出错"); 
          return null;
        }
    	else 
        {  
        	String body = method.getResponseBodyAsString();  
        	logger.info(body+"远程调用php成功");
        	JSONObject jsonObject=new JSONObject();
			try {
				 jsonObject=JSONObject.fromObject(body);		
			} catch (Exception e) {							
				e.printStackTrace();
			}
			 if (method != null) 
		       {  
		           method.releaseConnection();  
		       } 
			return jsonObject;
       } 
      
       
	}
	@org.junit.Test
	public void phpTest() throws HttpException, IOException
	{
		
    	String url = "http://dev.engyne.net/core/index.php/" +"Client/getOnlineStaffList/" + "shopping" ;
    	JSONObject jsonObject=remoteCall(url);
		//String userinfo=jsonObject.getJSONArray("list").getJSONObject(0).getString("userinfo");
		System.out.println(jsonObject.toString());
		JSONArray list=jsonObject.getJSONArray("list");
		ArrayList<String> userinfos=new ArrayList<String>();
		for(int i=0;i<list.length();i++)
		{
			userinfos.add(list.getJSONObject(i).toString());
		}
//		JSONObject js=new JSONObject();
//		js.put("userinfos", list);
//		js.put("name", "ff");
//		System.out.println(js.toString());
		String url2="http://dev.engyne.net/core/index.php/" +"Application/api/get?appid=" + "shopping";
		JSONObject jsonObject2=remoteCall(url2);
		System.out.println(jsonObject2.toString());
			
       } 
	@org.junit.Test
      public void msgUpdateTest()
      {
    	  String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "messages";
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
	        query.put("_id", "1695745803f0bf37039578675");
	        FindIterable iterable;
	        iterable = mongoCollection.find(query);
	        logger.info("更新message之前的值"+iterable.first());
			Bson filter = Filters.eq(Constant.FIELD_MONGO_ID, "1695745803f0bf37039578675");
			Document content = new Document();
			content.put("content", "8888888888888888888");
			Document extra = new Document();
			extra.put("admin", "0");
			extra.put("headimgurl", "0");
			extra.put("nickname", "石斌");
			Document doc = new Document();
			doc.put("content", content);
			doc.put("extra", extra);
			doc.put("convid","666666666");
			doc.put("from", "ssssssssssssss");
			ArrayList<String> read=new ArrayList<String>();
			read.add("shibin");
			doc.put("read", read);
			doc.put("time", "999999999999");
			doc.put("tmpindex", "aaaaaaaaaaaa");
			doc.put("type", "text");
			Document tdoc = new Document();
			tdoc.put("msg", doc);
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", tdoc), updateOptions);
	        iterable = mongoCollection.find(query);
	        logger.info("更新event之后的值"+iterable.first());
      }
	@Test
	public void updateTest()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "messages";
		 ServerAddress serverAddress = new ServerAddress(url, port);
  List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
  serverAddresses.add(serverAddress);
  MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
  List<MongoCredential> credentials = new ArrayList<MongoCredential>();
  credentials.add(credential);
  MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
  MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
  MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
  String convid="169573fcbc96a816281192222";
  String tmpindex="1464162500013x5P";
  BasicDBObject query = new BasicDBObject();
  query.put("_id", convid);
  query.put("msg.tmpindex", tmpindex);
  BasicDBObject query1 = new BasicDBObject();
  query1.put("_id", convid);
  FindIterable iterable;
  iterable = mongoCollection.find(query1);
  logger.info("更新message之前的值"+iterable.first());
	Bson filter = Filters.eq("_id", convid);
	Document doc = new Document();

	doc.put("msg.$.read", "ooooooooooo");
	
	      
	
	UpdateOptions updateOptions=new UpdateOptions();
	updateOptions.upsert(true);
	mongoCollection.updateOne(query, new Document("$addToSet", doc), updateOptions);
  iterable = mongoCollection.find(query1);
  logger.info("更新event之后的值"+iterable.first());

	}
	@org.junit.Test
	public static void test111()
	{
		String url = "mongo.100000q.com";
	       String user = "root";
	       String password = "Redrocks-dolina";
	       String database = "dolina";
	       int port = 27017;
	       String collection = "messages";
		 ServerAddress serverAddress = new ServerAddress(url, port);
List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
serverAddresses.add(serverAddress);
MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
List<MongoCredential> credentials = new ArrayList<MongoCredential>();
credentials.add(credential);
MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		logger.info("接收到ack消息后更新message中的read字段");
	   	BasicDBObject query = new BasicDBObject();
	   	String tmpindex="14666585589914kC";
	   	String from="ooooooooooooo";
	   	String convid="169576b6ef65af4b870834331";
	    query.put("_id", convid);
	    query.put("msg.tmpindex", tmpindex);
	    BasicDBObject query1 = new BasicDBObject();
	   	query1.put("_id", convid);
	   	FindIterable iterable;
	   	iterable = mongoCollection.find(query1);
	   	logger.info("更新message之前的值"+iterable.first());
	   	if(iterable.first()!=null)
	   	{
	   		logger.info("aaaaaaaaaaaaaa");
	   		Document doc = new Document();
		   	doc.put("msg.$.read", from);
		   	UpdateOptions updateOptions=new UpdateOptions();
		   	updateOptions.upsert(true);
		   	logger.info("bbbbbbbbbbbbb");
		   	
		   	try {
				mongoCollection.updateOne(query, new Document("$addToSet", doc), updateOptions);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   	logger.info("ccccccccccccccc");
	   	}
	   	
	   	iterable = mongoCollection.find(query1);
	   	logger.info("更新messages之后的值"+iterable.first());
			
	}
	}

