package net.engyne.mongodao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import net.engyne.common.Constant;
import net.engyne.mqtt.MqttSignal;
import net.engyne.util.MD5Util;
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
     * 作者：zhouyh 
     */  
    private MongoDBDao(){  
        if(mongoClient == null){  
        	String url = Constant.MONGO_URL;
    	    String user = Constant.MONGO_USER;
    	    String password = Constant.MONGO_PASSWORD;
    	    String database = Constant.MONGO_DATABASE;
    	    int port = 27017;
    	    ServerAddress serverAddress = new ServerAddress(url, port);
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            serverAddresses.add(serverAddress);
            MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            mongoClient = new MongoClient(serverAddresses, credentials);
            System.out.println(mongoClient);
            System.out.println("初始化client完成");
        }
    }  
      
    /********单例模式声明开始，采用饿汉式方式生成，保证线程安全********************/  
      
    //类初始化时，自行实例化，饿汉式单例模式  
    private static final MongoDBDao mongoDBDao = new MongoDBDao();  
    /** 
     *  
     * 方法名：getMongoDBDaoImplInstance 
     * 作者：zhouyh 
     * 创建时间：2014-8-30 下午04:29:26 
     * 描述：单例的静态工厂方法 
     * @return 
     */  
    public static MongoDBDao getMongoDBDaoInstance(){  
        return mongoDBDao;  
    }  
    public void saveMongoEvent(String clientid,String event,String pageid,String time,JSONObject data,String appid,String database,String collection)
		{
    		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
	        MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
	        BasicDBObject query = new BasicDBObject();
	        query.put(Constant.FIELD_MONGO_ID, clientid);
	        FindIterable iterable;
	        iterable = mongoCollection.find(query);
	        logger.info("更新event之前的值"+iterable.first());
			Bson filter = Filters.eq(Constant.FIELD_MONGO_ID, clientid);
			Document newdoc = new Document();
			newdoc.put(Constant.FIELD_MONGO_EVENT, event);
			
			newdoc.put(Constant.FIELD_MONGO_DATA, data.toString());
			
			newdoc.put(Constant.FIELD_MONGO_PAGEID, pageid);
			newdoc.put(Constant.FIELD_MONGO_TIME, time);
			Document doc = new Document();
			doc.put(appid, newdoc);
			doc.put(Constant.FIELD_MONGO_APPIDS, appid);
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
	        iterable = mongoCollection.find(query);
	        logger.info("更新event之后的值"+iterable.first());
		}
    public void saveMongoInput(String clientid,String time,String result,String template,String database,String collection)
		{
    		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
    		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
	       BasicDBObject query = new BasicDBObject();
	       query.put(Constant.FIELD_MONGO_ID, clientid);
	       FindIterable iterable;
	       iterable = mongoCollection.find(query);
	       logger.info("更新input之前"+iterable.first());
			Bson filter = Filters.eq(Constant.FIELD_MONGO_ID, clientid);
			Document newdoc = new Document();
			System.out.println(Constant.FIELD_MONGO_RESULT+result);
			newdoc.put(Constant.FIELD_MONGO_RESULT, result);
			newdoc.put(Constant.FIELD_MONGO_TIME, time);
			Document doc = new Document();
			doc.put(template, newdoc);
			UpdateOptions updateOptions=new UpdateOptions();
			updateOptions.upsert(true);
			mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
	         iterable = mongoCollection.find(query);
	         logger.info("更新input之后"+iterable.first());
			}
    public void saveMongoVote(String clientid,int result,String time,String type,String template,String database,String collection)
		{
    	MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
       BasicDBObject query = new BasicDBObject();
       String _idobj=MD5Util.MD5(template);
       logger.info("获得template对应的md5 值为"+_idobj);
       query.put("_id", _idobj);
       FindIterable iterable;
       iterable = mongoCollection.find(query);
       logger.info("更新vote之前"+iterable.first());
 	   Bson filter = Filters.eq(Constant.FIELD_MONGO_ID,_idobj );
 	   Document newdoc = new Document();
 		newdoc.put(Constant.FIELD_MONGO_CLIENTID, clientid);
 		newdoc.put(Constant.FIELD_MONGO_RESULT, result);
 		newdoc.put(Constant.FIELD_MONGO_TIME, time);
 		newdoc.put(Constant.FIELD_MONGO_TYPE, type);
		Document doc = new Document();
		doc.put(Constant.FIELD_MONGO_LOG, newdoc);
		UpdateOptions updateOptions=new UpdateOptions();
		updateOptions.upsert(true);
		mongoCollection.updateOne(filter, new Document("$addToSet", doc), updateOptions);
		if(result==0)
		{
			mongoCollection.updateOne(new BasicDBObject().append("_id", _idobj),new BasicDBObject().append("$inc",new BasicDBObject().append("stats.0", 1)) );
 			mongoCollection.updateOne(new BasicDBObject().append("_id", _idobj),new BasicDBObject().append("$inc",new BasicDBObject().append("stats.total", 1)) );
		}
		else
		{
			mongoCollection.updateOne(new BasicDBObject().append("_id", _idobj),new BasicDBObject().append("$inc",new BasicDBObject().append("stats.1", 1)) );
 			mongoCollection.updateOne(new BasicDBObject().append("_id",_idobj),new BasicDBObject().append("$inc",new BasicDBObject().append("stats.total", 1)) );
		}
		iterable = mongoCollection.find(query);
        logger.info("更新vote之后"+iterable.first());
		}
    
}
