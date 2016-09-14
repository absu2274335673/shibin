package net.engyne.test;






import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;

import net.sf.json.JSONFunction;
import net.sf.json.JSONObject;

/**
 * Created by shaobin on 16/4/19.
 */
public class MongoOperation {

    /**
     * 无密码验证登录方式
     * @param url 服务器地址
     * @param port 服务器端口
     * @param database 数据库名称
     * @return mongodatabase 为数据库链接
     */
    protected static MongoDatabase noPassWordLogin(String url, int port, String database) {
        MongoDatabase mongoDatabase = new MongoDatabase() {
        

			
			@Override
			public <TDocument> MongoCollection<TDocument> getCollection(String arg0, Class<TDocument> arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ReadConcern getReadConcern() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ReadPreference getReadPreference() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public WriteConcern getWriteConcern() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoIterable<String> listCollectionNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ListCollectionsIterable<Document> listCollections() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <TResult> ListCollectionsIterable<TResult> listCollections(Class<TResult> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Document runCommand(Bson arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Document runCommand(Bson arg0, ReadPreference arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <TResult> TResult runCommand(Bson arg0, Class<TResult> arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <TResult> TResult runCommand(Bson arg0, ReadPreference arg1, Class<TResult> arg2) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoDatabase withCodecRegistry(CodecRegistry arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoDatabase withReadConcern(ReadConcern arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoDatabase withReadPreference(ReadPreference arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoDatabase withWriteConcern(WriteConcern arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void createCollection(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void createCollection(String arg0, CreateCollectionOptions arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drop() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public CodecRegistry getCodecRegistry() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MongoCollection<Document> getCollection(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
        };
        try {
            MongoClient mongoClient = new MongoClient(url , port);
            mongoDatabase = mongoClient.getDatabase(database);
            System.out.println("Connect database success!");
        }
        catch (Exception e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
        return mongoDatabase;
    }

    /**
     * 有密码验证登录方式
     * @param url 服务器地址
     * @param port 服务器端口
     * @param database 数据库名称
     * @param user 用户名
     * @param password 密码
     * @return mongodatabase 为数据库链接
     */
    protected static MongoDatabase PassWordLogin(String url, int port, String database, String user, String password) {        //有密码验证登陆
        MongoDatabase mongoDatabase = new MongoDatabase() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public CodecRegistry getCodecRegistry() {
                return null;
            }

            @Override
            public ReadPreference getReadPreference() {
                return null;
            }

            @Override
            public WriteConcern getWriteConcern() {
                return null;
            }

            @Override
            public ReadConcern getReadConcern() {
                return null;
            }

            @Override
            public MongoDatabase withCodecRegistry(CodecRegistry codecRegistry) {
                return null;
            }

            @Override
            public MongoDatabase withReadPreference(ReadPreference readPreference) {
                return null;
            }

            @Override
            public MongoDatabase withWriteConcern(WriteConcern writeConcern) {
                return null;
            }

            @Override
            public MongoDatabase withReadConcern(ReadConcern readConcern) {
                return null;
            }

            @Override
            public MongoCollection<Document> getCollection(String s) {
                return null;
            }

            @Override
            public <TDocument> MongoCollection<TDocument> getCollection(String s, Class<TDocument> aClass) {
                return null;
            }

            @Override
            public Document runCommand(Bson bson) {
                return null;
            }

            @Override
            public Document runCommand(Bson bson, ReadPreference readPreference) {
                return null;
            }

            @Override
            public <TResult> TResult runCommand(Bson bson, Class<TResult> aClass) {
                return null;
            }

            @Override
            public <TResult> TResult runCommand(Bson bson, ReadPreference readPreference, Class<TResult> aClass) {
                return null;
            }

            @Override
            public void drop() {

            }

            @Override
            public MongoIterable<String> listCollectionNames() {
                return null;
            }

            @Override
            public ListCollectionsIterable<Document> listCollections() {
                return null;
            }

            @Override
            public <TResult> ListCollectionsIterable<TResult> listCollections(Class<TResult> aClass) {
                return null;
            }

            @Override
            public void createCollection(String s) {

            }

            @Override
            public void createCollection(String s, CreateCollectionOptions createCollectionOptions) {

            }
        };
        try {
            ServerAddress serverAddress = new ServerAddress(url, port);
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            serverAddresses.add(serverAddress);

            MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            MongoClient mongoClient = new MongoClient(serverAddresses, credentials);
            
            mongoDatabase = mongoClient.getDatabase(database);
            System.out.println("Connect to database successfully!");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
        return mongoDatabase;
    }

    /**
     * 查找mongo数据库
     * @param collection 表单名
     * @param key 查找属性名
     * @param value 查找属性值
     * @return cursor 为查找结果集
     */
    protected static MongoCursor seach(MongoCollection collection, String key, String value) {
        BasicDBObject query = new BasicDBObject();
        FindIterable iterable;
        if (key.equals("") && value.equals("")) {
            iterable = collection.find();
        }
        else {
            query.put(key, value);
            iterable = collection.find(query);
        }
        MongoCursor cursor = iterable.iterator();
        return cursor;
    }

    /**
     *查询结果展示
     * @param cursor 结果集合游标
     */
    protected static void showData(MongoCursor cursor) {
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }


    /**
     *
     * @param id 要查询的用户ID
     * @return 返回为json串，是数组格式
     */
    public static String findID(String id) {

        String url1 = "mongo.100000q.com";
        String user1 = "root";
        String password1 = "Redrocks-dolina";
        String database = "dolina";
        int port = 27017;
        String collection = "event";
        MongoDatabase mongoDatabase = PassWordLogin(url1, port, database, user1, password1);
        MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
        MongoCursor cursor = seach(mongoCollection, "", "");
        String []t = new String[20];
        int i = 0;
        while (cursor.hasNext()) {
            JSONObject jsonObject = JsonOperation.ToJson(cursor.next());
            String json = jsonObject.toString();
            Object clientidOpt = jsonObject.opt("clientid");
            String clientidStr = clientidOpt.toString();
            if (clientidStr.equals(id)) {
            	String temp = "{";
                Object eventOpt = jsonObject.opt("event");
                String eventStr = eventOpt.toString();
                temp = temp + "\"event\":\"" + eventStr +"\",";
                Object dataOpt = jsonObject.opt("data");
                String dataStr = dataOpt.toString();
                temp = temp + "\"data\":\"" + dataStr +"\",";
                Object timeOpt = jsonObject.opt("time");
                String timeStr = timeOpt.toString();
                temp = temp + "\"time\":\"" + timeStr +"\"}";
//                long time = Long.valueOf(timeStr);
//                Date date = CommonOperation.unixtimeToDate(time);
//                temp = temp + "\"time\":\"" + date.toString() +"\"｝";
                t[i++] = temp;
//                System.out.println(t[i - 1]);
            }
            if (i == 20) break;
        }
        String ans = "{\"ans\":[";
        for (int j = 0; j < i; j++) {
            if (j == i - 1) {
                ans = ans + t[j];
            }else {
                ans = ans + t[j] + ",";
            }
        }
        ans = ans + "]}";
        return ans;
    }


}
