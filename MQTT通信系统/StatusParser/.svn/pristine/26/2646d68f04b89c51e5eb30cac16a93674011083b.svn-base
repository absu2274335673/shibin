package net.engyne.MongoDBDao;

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
    public void saveMongoConnection(String database,String collection,String clientid,String username,String ipaddress,String session,String protocol,String connack,String ts)
		{
    	MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
        BasicDBObject query = new BasicDBObject();
        String _idobj=clientid;
        //query.put("_id", _idobj);
       // FindIterable iterable;
       // iterable = mongoCollection.find(query);
        //logger.info("更新connection之前"+iterable.first());
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
	       // iterable = mongoCollection.find(query);
	        //logger.info("更新connection之后"+iterable.first());
		}
		public void saveMongoDisconnection(String database,String collection,String clientid,String reason,String ts)
		{
			MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
	        MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
	       BasicDBObject query = new BasicDBObject();
	       String _idobj=clientid;
//	       query.put("_id", _idobj);
//	       FindIterable iterable;
//	       iterable = mongoCollection.find(query);
//	       logger.info("更新disconnection之前"+iterable.first());
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
			newDocument.put("status", 0);
			mongoCollection.updateOne(filter, new Document("$set", newDocument));
//	        iterable = mongoCollection.find(query);
//	        logger.info("更新disconnection之后"+iterable.first());
		}
}
