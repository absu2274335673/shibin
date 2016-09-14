package net.engyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import sun.print.resources.serviceui;

import net.engyne.common.Constant;
import net.engyne.po.Vote;
import net.engyne.util.MD5Util;
import net.sf.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDao {

	private MongoClient mongoClient = null;

	private MongoDao() {
		if (mongoClient == null) {
			ServerAddress serverAddress = new ServerAddress(Constant.MONGO_URL,
					Constant.MONGO_PORT);
			List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
			serverAddresses.add(serverAddress);
			MongoCredential credential = MongoCredential.createCredential(
					Constant.MONGO_USER, Constant.MONGO_DATABASE,
					Constant.MONGO_PASSWORD.toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);
			mongoClient = new MongoClient(serverAddresses, credentials);
		}
	}

	private static final MongoDao mongoDao = new MongoDao();

	public static MongoDao getMongoDaoInstance() {
		return mongoDao;
	}
	public List<Vote> getVoteData(String database,String collection,List<Integer> ids)
	{
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
		List<String> idsMD5=new ArrayList<String>();
		for(int i=0;i<ids.size();i++)
		{
			String str=MD5Util.MD5(ids.get(i)+"");
			idsMD5.add(str);
			System.out.println(ids.get(i)+"----"+str);
		}
//		BasicDBObject query = new BasicDBObject();
		List<Vote> votes=new ArrayList<Vote>();
		BasicDBList condList = new BasicDBList(); 
		for(int i=0;i<idsMD5.size();i++)
		{
			 BasicDBObject cond = new BasicDBObject();
			 cond.put("_id", idsMD5.get(i));
			 condList.add(cond);
		}
		 BasicDBObject searchCond = new BasicDBObject();
		 searchCond.put("$or", condList);
		FindIterable<Document> iterable = null;
		iterable = mongoCollection.find(searchCond);
		MongoCursor<Document> cursor= iterable.iterator();
		int k=0;
		while(cursor.hasNext())
		{
			Document doc=cursor.next();
			String docjson=doc.toJson();
			JSONObject jo = JSONObject.fromObject(docjson);
			System.out.println(docjson);
			Vote vote=new Vote();
			vote.setTempindex(ids.get(k));
			k++;
			JSONObject stats=jo.getJSONObject("stats");
			String approval="0";
			String opposition="0";
			if(stats.has("1"))
				approval=stats.getString("1");
			if(stats.has("0"))
				opposition=stats.getString("0");
			vote.setApproval(approval);
			vote.setOpposition(opposition);
			votes.add(vote);
			
		}
		return votes;
	}

	
}
