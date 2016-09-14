package net.engyne.mongodao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import net.engyne.common.Constant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MongoDBDao
{
	private static Logger logger = Logger.getLogger(MongoDBDao.class);
	/** 
     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可 
     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo 
     */  
    private MongoClient mongoClient = null;  
    /** 
     *  
     * 私有的构造函数 
     * 作者：shibin
     */  
    private MongoDBDao(){  
        if(mongoClient == null){  
        	
    	    ServerAddress serverAddress = new ServerAddress(Constant.MONGO_URL, Constant.MONGO_PORT);
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            serverAddresses.add(serverAddress);
            MongoCredential credential = MongoCredential.createCredential(Constant.MONGO_USER, Constant.MONGO_DATABASE, Constant.MONGO_PASSWORD.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            mongoClient = new MongoClient(serverAddresses, credentials);
            logger.info(mongoClient);
            logger.info("初始化client完成");
        }
    }  
      
    /********单例模式声明开始，采用饿汉式方式生成，保证线程安全********************/  
      
    //类初始化时，自行实例化，饿汉式单例模式  
    private static final MongoDBDao mongoDBDao = new MongoDBDao();  
    /** 
     *  
     * 方法名：getMongoDBDaoImplInstance 
     * 作者：shibin 
     * 创建时间：2014-8-30 下午04:29:26 
     * 描述：单例的静态工厂方法 
     * @return 
     */  
    public static MongoDBDao getMongoDBDaoInstance(){  
        return mongoDBDao;  
    } 
	@SuppressWarnings("rawtypes")
	public void saveTextMsg(String database, String collection, JSONObject jo) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		String convid = jo.getString("convid");
		query.put("_id", convid);
//		FindIterable iterable;
//		iterable = mongoCollection.find(query);
//		logger.info("更新message之前的值" + iterable.first());
		Bson filter = Filters.eq("_id", convid);
		Document content = new Document();
		String type = jo.getString("type");
		if (type.equals("text")) {
			String contentMsg = jo.getJSONObject("content").getString("content");
			content.put("content", contentMsg);
		} else {
			String url = jo.getJSONObject("content").getString("url");
			content.put("url", url);
		}
		String admin="";
		String headimgurl="";
		String nickname="";
		String from="";
		String tmpindex="";
		try {
			admin = jo.getJSONObject("extra").getString("admin");
			headimgurl = jo.getJSONObject("extra").getString("headimgurl");
			nickname = jo.getJSONObject("extra").getString("nickname");
			from = jo.getString("from");
			tmpindex = jo.getString("tmpindex");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document extra = new Document();
		extra.put("admin", admin);
		extra.put("headimgurl", headimgurl);
		extra.put("nickname", nickname);
		Document doc = new Document();
		doc.put("content", content);
		doc.put("extra", extra);
		doc.put("convid", convid);
		doc.put("from", from);
		ArrayList<String> read = new ArrayList<String>();
		doc.put("read", read);
		doc.put("time", (int) (System.currentTimeMillis() / 1000));
		doc.put("tmpindex", tmpindex);
		doc.put("type", type);
		Document tdoc = new Document();
		tdoc.put("msg", doc);
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		try {
			UpdateResult ur=mongoCollection.updateOne(filter, new Document("$addToSet", tdoc), updateOptions);
			System.out.println(ur.getModifiedCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		iterable = mongoCollection.find(query);
//		logger.info("更新message之后的值" + iterable.first());
	}
    
	@SuppressWarnings("rawtypes")
	public void getAck(String tmpindex, String convid, String from, String database, String collection) {
		logger.info("接收到ack消息后更新message中的read字段");
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", convid);
		query.put("msg.tmpindex", tmpindex);
		BasicDBObject query1 = new BasicDBObject();
		query1.put("_id", convid);
		FindIterable iterable;
		FindIterable iterable2;
		//iterable = mongoCollection.find(query1);
		iterable2 = mongoCollection.find(query);
		//logger.info("更新message满足id过滤条件之前的值" + iterable.first());
		//logger.info("更新message满足id和tmpindex过滤条件之前的值" + iterable2);
		if (iterable2.first() != null) {
			Document doc = new Document();
			doc.put("msg.$.read", from);
			UpdateOptions updateOptions = new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(query, new Document("$addToSet", doc), updateOptions);
		}

		//iterable = mongoCollection.find(query1);
		//logger.info("更新messages之后的值" + iterable.first());
	}
	public void updateLoginfo(String database,String collection,String clientid,String appid,String pageid,Document clientinfo)
	{
		logger.info("getdatabase1:"+System.currentTimeMillis());
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		logger.info("getdatabase2:"+System.currentTimeMillis());
		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", clientid);
		Document doc = new Document();
		doc.put("clientid", clientid);
		doc.put("appid", appid);
		doc.put("pageid", pageid);
		doc.put("time", System.currentTimeMillis()/1000);
		doc.put("clientinfo", clientinfo);
		Document docn = new Document();
		docn.put("log", doc);
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		mongoCollection.updateOne(query, new Document("$addToSet", docn), updateOptions);
		Document newDocument =new Document();
		newDocument.put("time", System.currentTimeMillis()/1000);
		mongoCollection.updateOne(query, new Document("$set", newDocument));
		
	}
}
