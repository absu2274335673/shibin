package net.engyne.communication.service.message;

import java.util.Iterator;

import net.sf.json.JSONObject;



public class MessageXSendDemo {

	
	public static String sendMessage(String queryData)
	{
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String phoneNumber="";
		String projectID="";
		if(json.has("phoneNumber"))
		{
			phoneNumber=json.getString("phoneNumber");
			json.remove("phoneNumber");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		if(json.has("projectID"))
		{
			projectID=json.getString("projectID");
			json.remove("projectID");
		}
		System.out.println("aaaaaaaaaaaaa");
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MESSAGEXsend submail = new MESSAGEXsend(config);
		System.out.println(phoneNumber+projectID);
		submail.addTo(phoneNumber);
		submail.setProject(projectID);
        Iterator iterator = json.keys();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            System.out.println("key:"+key);
            String value = json.getString(key);
            System.out.println("value:"+value);
            submail.addVar(key, value);
        }
		submail.xsend();
		return new JSONObject().put("result", "SUCCESS").toString();
	}
	public static void main(String[] args) {
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MESSAGEXsend submail = new MESSAGEXsend(config);
		submail.addTo("13163359533");
		submail.setProject("S1A1v");
		submail.addVar("code", "nnn");
		submail.addVar("time", "60");
		submail.xsend();
	}	
}
