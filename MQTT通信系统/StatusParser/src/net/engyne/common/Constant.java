package net.engyne.common;

import net.engyne.util.FileUtil;

public class Constant
{
	public static final String DATE_FORMAT_MDYHMS="MM/dd/yyyy HH:mm:ss";
	public static final String FIELD_MQTT_TEXT="text"; 
	public static final String FIELD_MQTT_TITLE="title";
	public static final String FIELD_MQTT_CONTENT="content"; 
	public static final String FIELD_MQTT_HEADIMGURL="headimgurl"; 
	public static final String FIELD_MQTT_NICKNAME="nickname"; 
	public static final String FIELD_MQTT_ADMIN="admin"; 
	public static final String FIELD_MQTT_EXTRA="extra"; 
	public static final String FIELD_MQTT_TYPE="type"; 
	public static final String FIELD_MQTT_FROM="from"; 
	public static final String FIELD_MQTT_CONVID="convid"; 
	public static final String FIELD_MQTT_TMPINDEX="tmpindex"; 
	public static final String FIELD_MQTT_TIME="time"; 
	public static final String FIELD_MQTT_DESC="desc"; 
	public static final String FIELD_MQTT_TEMPLATE="template"; 
	public static final String TOPIC_MQTT_CONNECTED="$SYS/brokers/+/clients/+/connected";
	public static final String TOPIC_MQTT_DISCONNECTED="$SYS/brokers/+/clients/+/disconnected";
//	public static final String MONGO_MQTT_URL="mongo.100000q.com";
//	public static final String MONGO_MQTT_USER="root";
//	public static final String MONGO_MQTT_PASSWORD="Redrocks-dolina";
//	public static final String MONGO_MQTT_DATABASE="dolina";
	public static final String MONGO_URL=FileUtil.getProperty("mongo_url");//"mongo.100000q.com";
	public static final String MONGO_USER=FileUtil.getProperty("mongo_user");//"root";
	public static final String MONGO_PASSWORD=FileUtil.getProperty("mongo_password");//"Redrocks-dolina";
	public static final String MONGO_DATABASE=FileUtil.getProperty("mongo_database");//"dolina";
	public static final String IP_MQTT_URL=FileUtil.getProperty("service_url_prefix")+"/Client/getIpLocation/?ip=";
	
}
