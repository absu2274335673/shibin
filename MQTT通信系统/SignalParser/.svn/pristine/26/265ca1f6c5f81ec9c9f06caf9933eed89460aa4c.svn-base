package net.engyne.common;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class RemoteOperation {
	
	private static Logger logger = Logger.getLogger(RemoteOperation.class);

	public static JSONObject remotePostCall(String url) throws HttpException, IOException {
		
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"); 
		NameValuePair[] param = { new NameValuePair("calleeNbr","+8613163359533"),
                new NameValuePair("callerNbr","+867556666663036"),
                new NameValuePair("displayNbr","+867556666663036"),
                new NameValuePair("languageType","1") } ;
        post.setRequestBody(param);
		int retcode = httpClient.executeMethod(post);
		if (retcode != HttpStatus.SC_OK) {// 发送不成功
			logger.info("远程调用出错");
			return null;
		} else {
			String body = post.getResponseBodyAsString();
			logger.info(body + "远程调用php成功");
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = JSONObject.fromObject(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (post != null) {
				post.releaseConnection();
			}
			return jsonObject;
		}

	}
public static JSONObject remoteGetCall(String url) throws HttpException, IOException {
		
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("Accept", "text/html, application/xhtml+xml, */*");;
		int retcode = httpClient.executeMethod(method);
		if (retcode != HttpStatus.SC_OK) {// 发送不成功
			logger.info("远程调用出错");
			return null;
		} else {
			String body = method.getResponseBodyAsString();
			logger.info(body + "远程调用php成功");
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject = JSONObject.fromObject(body);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (method != null) {
				method.releaseConnection();
			}
			return jsonObject;
		}


	}
	public static void main(String args[])
	{
		
	}
}
