package net.engyne.communication.service.telephone;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

@Path("CallService")
public class CallService {

	 private static Logger logger = Logger.getLogger(CallService.class);
		@POST
		@Path("start")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
	 public  String start(String queryData) throws HttpException, IOException {
		 
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData); 
		String spid="";
		String appid="";
		String caller="";
		String callee="";
		if(json.has("spid"))
		{
			spid=json.getString("spid");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		if(json.has("appid"))
		{
			appid=json.getString("appid");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		if(json.has("caller"))
		{
			caller=json.getString("caller");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		if(json.has("callee"))
		{
			callee=json.getString("callee");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		
    	String data="<Request>"
				+ "<Head>"
				+ "<MethodName>Dial</MethodName>"
				+ "<Spid>"+spid+"</Spid>"
				+ "<Appid>"+appid+"</Appid>"
				+ "<Passwd>c3811d59d035798eecae5d5fe4c22cf0b1fccfcc</Passwd>"
				+ "</Head>"
				+ "<Body>"
				+ "<ChargeNbr>8659522947632</ChargeNbr>"
				+ "<Key>38337476</Key>"
				+ "<DisplayNbr>8659522947632</DisplayNbr>"
				+ "<CallerNbr>"+caller+"</CallerNbr>"
				+ "<CalleeNbr>"+callee+"</CalleeNbr>"
				+ "<Record></Record>"
				+ "</Body>"
				+ "</Request>";
    	System.out.println(data);
		remoteGetCall("http://open.fjii.com:8088/httpIntf/dealIntf?postData="+data);
		return new JSONObject().put("result", "SUCCESS").toString();
   }
   public static String remoteGetCall(String url) throws HttpException, IOException {
		String body="";
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "text/html, application/xhtml+xml, */*");;
		int retcode = httpClient.executeMethod(method);
		if (retcode != HttpStatus.SC_OK) {// 发送不成功
			logger.info("远程调用出错");
			return null;
		} else {
			 body = method.getResponseBodyAsString();
			logger.info(body + "远程调用php成功");
			
			if (method != null) {
				method.releaseConnection();
			}
			return body;
		}
	}
}
