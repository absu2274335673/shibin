package net.engyne.restapi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;



/**
 * @author lingxi
 *
 */
public class OmpRestClient {
	/**
	 * DEFAULT_SERVER = "223.87.12.241:443";
	 */
	public static final String DEFAULT_SERVER = "223.87.12.241:443";
	
	private static final String HTTPS_PREFIX = "https://";
	private static final String OAUTH_FASTLOGIN_API = "/rest/fastlogin/v1.0?";
	private static final String OAUTH_TRIGGER_API = "/rest/httpsessions/v1.0?";
	private static final String OAUTH_TEST_TRIGGER_API = "/sandbox/rest/httpsessions/v1.0?";
	private static final String CLICK_TO_CALL_API = "/sandbox/rest/httpsessions/click2Call/v1.0?";
	
	private String fastloginUrl = "";
	private String triggerUrl = "";
	private String testTriggerUrl = "";
	private String click2callUrl = "";
	private String appKey = "";
	
	private int timeout = 30000;
	
	public OmpRestClient(String serverAddress, String appKey) {
		this.fastloginUrl = HTTPS_PREFIX + serverAddress + OAUTH_FASTLOGIN_API;
		this.triggerUrl = HTTPS_PREFIX + serverAddress + OAUTH_TRIGGER_API;
		this.testTriggerUrl = HTTPS_PREFIX + serverAddress + OAUTH_TEST_TRIGGER_API;
		this.click2callUrl = HTTPS_PREFIX + serverAddress + CLICK_TO_CALL_API;
		this.appKey = appKey;
	}
	
	public OmpRestClient(String appKey) {
		this(DEFAULT_SERVER, appKey);
	}	

	public String getFastloginToken(String username, String password) {
		InputStream inputStream=null;
		DataOutputStream out= null;
		ByteArrayOutputStream baos = null;
		String data = "";
		String accessToken = "";
		try {
			//prepare post url
			String userName = username.replace("+", "%2b");
			String url = fastloginUrl + "app_key=" + appKey 
				+ "&username=" + userName + "&format=json";
			System.out.println("[OmpRestClient.fastLogin] post url: " + url);
			
			//get https connection
			HttpsURLConnection conn = getHttpsPostConnection(url);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setRequestProperty("Authorization", password);
			conn.connect();

			int responseCode = conn.getResponseCode();
			
			if(responseCode == 200) {
				inputStream = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*4];
				int readSize = -1;
				while((readSize = inputStream.read(buffer)) != -1){
					baos.write(buffer, 0, readSize);
				}
				data = baos.toString();
				System.out.println("[OmpRestClient.fastLogin] response data: " + data);
				if(null != data && !data.trim().equals("")) {
					System.out.println("[OmpRestClient.fastLogin] parse response data");
					accessToken = parseJsonForToken(data);
					System.out.println("[OmpRestClient.fastLogin] parsed token: " + accessToken);
				} else {
					System.out.println("[OmpRestClient.fastLogin] 200 response with empty data");
				}
			} else {
				inputStream = conn.getErrorStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*4];
				int readSize = -1;
				while((readSize = inputStream.read(buffer)) != -1){
					baos.write(buffer, 0, readSize);
				}
				data = baos.toString();
				System.out.println("[OmpRestClient.fastLogin] " + responseCode + " response with data: " + data);
			}
		} catch (Exception e) {
			System.out.println("[OmpRestClient.fastLogin] exception: " + e.toString());
		} finally{
			try {
				if(inputStream!=null)
					inputStream.close();
				if(out != null)
					out.close();
				if(baos!=null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("[OmpRestClient.fastLogin] Exit with token: " + accessToken);
		return accessToken;
	}
	
	public String triggerApp(String token, String appData) {
		return triggerAppBase(token, appData, triggerUrl);
	}
	
	public String triggerTestApp(String token, String appData) {
		return triggerAppBase(token, appData, testTriggerUrl);
	}
	
	private String triggerAppBase(String token, String appData, String url) {
		if(null == token || token.equals("") 
				|| null == url || url.equals("") 
				|| null == appKey || appKey.equals("")) {
			return "Invalid token/App param";
		}
		InputStream inputStream=null;
		DataOutputStream out= null;
		ByteArrayOutputStream baos = null;
		String data = "Failed";
		try {
			//prepare request url
			String requestUrl = url + "app_key=" + appKey + "&access_token=" + token 
					 + "&format=json";
			System.out.println("[OmpRestClient.triggerApp] requestUrl: " + requestUrl);
			
			//prepare request body
			String requestBody = "{ \"servicedata\":\"action=create" + appData + "\" }";
			System.out.println("[OmpRestClient.triggerApp] requestBody: " + requestBody);
			
		    //start http connection
			HttpsURLConnection conn = getHttpsPostConnection(requestUrl);
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			System.out.println("[OmpRestClient.triggerApp] conn.setRequestProperty Content-Type: application/json;charset=UTF-8");
			byte[] sendData = requestBody.getBytes();

			conn.connect();
			out = new DataOutputStream(conn.getOutputStream());
			out.write(sendData);
			
			int responseCode = conn.getResponseCode();
			System.out.println("[OmpRestClient.triggerApp] responseCode: " + responseCode);
			if(responseCode == 200) {
				inputStream = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*4];
				int readSize = -1;
				while((readSize = inputStream.read(buffer)) != -1){
					baos.write(buffer, 0, readSize);
				}
				data = baos.toString();
				System.out.println("[OmpRestClient.triggerApp] 200 data: " + data);
				data = "Success";
			} else {
				inputStream = conn.getErrorStream();
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024*4];
				int readSize = -1;
				while((readSize = inputStream.read(buffer)) != -1){
					baos.write(buffer, 0, readSize);
				}
				data = baos.toString();
				System.out.println("[OmpRestClient.triggerApp] " + responseCode + " data: " + data);
				data = "Failed";
			}
		} catch (Exception e) {
			System.out.println("[OmpRestClient.triggerApp] exception: " + e.toString());
			data = e.toString();
		} finally{
			try {
				if(inputStream!=null)
					inputStream.close();
				if(out != null)
					out.close();
				if(baos!=null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[OmpRestClient.triggerApp] EXIT with: " + data);
		return data;
		
	}
	
	public String triggerClick2call(String token, String caller, String callee, String display) {
        if(null == token || token.equals("") 
                || null == click2callUrl || click2callUrl.equals("") 
                || null == appKey || appKey.equals("")) {
            return "Invalid token / app param";
        }
        
        if(null == caller || caller.equals("") 
                || null == callee || callee.equals("") 
                || null == display || display.equals("")) {
            return "Invalid number(s)";
        }
        
        InputStream inputStream=null;
        DataOutputStream out= null;
        ByteArrayOutputStream baos = null;
        String data = "Failed";
        try {
            //prepare request url
            String requestUrl = click2callUrl + "app_key=" + appKey + "&access_token=" + token + "&format=json";
            System.out.println("[OmpRestClient.triggerClick2call] requestUrl: " + requestUrl);
            
            //prepare request body
            JSONObject rootObject = new JSONObject();
            rootObject.element("callRecord", "0");
            rootObject.element("callerNbr", caller);
            rootObject.element("calleeNbr", callee);
            rootObject.element("displayNbr", display);
            rootObject.element("languageType", "0");
            
            String requestBody = rootObject.toString();
            System.out.println("[OmpRestClient.triggerClick2call] requestBody: " + requestBody);
            
            //start http connection
            HttpsURLConnection conn = getHttpsPostConnection(requestUrl);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            System.out.println("[OmpRestClient.triggerClick2call] conn.setRequestProperty Content-Type: application/json;charset=UTF-8");
            byte[] sendData = requestBody.getBytes();

            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            out.write(sendData);
            
            int responseCode = conn.getResponseCode();
            System.out.println("[OmpRestClient.triggerClick2call] responseCode: " + responseCode);
            if(responseCode == 200) {
                inputStream = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024*4];
                int readSize = -1;
                while((readSize = inputStream.read(buffer)) != -1){
                    baos.write(buffer, 0, readSize);
                }
                data = baos.toString();
                System.out.println("[OmpRestClient.triggerClick2call] 200 data: " + data);
                data = "Success";
            } else {
                inputStream = conn.getErrorStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024*4];
                int readSize = -1;
                while((readSize = inputStream.read(buffer)) != -1){
                    baos.write(buffer, 0, readSize);
                }
                data = baos.toString();
                System.out.println("[OmpRestClient.triggerClick2call] " + responseCode + " data: " + data);
                data = "Failed";
            }
        } catch (Exception e) {
            System.out.println("[OmpRestClient.triggerClick2call] exception: " + e.toString());
            data = e.toString();
        } finally{
            try {
                if(inputStream!=null)
                    inputStream.close();
                if(out != null)
                    out.close();
                if(baos!=null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[OmpRestClient.triggerClick2call] EXIT with: " + data);
        return data;
        
    }
	
	public String parseJsonForToken(String jsonString) {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			String accessToken = jsonObject.getString("access_token");
			return accessToken;
	}
	
	private void setupSSL() throws Exception {
		SSLContext sc = SSLContext.getInstance("TLS");
	    sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
	}
	
	private HttpsURLConnection getHttpsPostConnection(String url) throws Exception {
		System.out.println("[OmpRestClient.getHttpsPostConnection] Verify SSL");
		//SSL
		setupSSL();
		
		URL ul = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) ul.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(getServerTimeout());
		conn.setReadTimeout(getServerTimeout());
		conn.setDoInput(true);
		conn.setDoOutput(true);
		System.out.println("[OmpRestClient.getHttpsPostConnection] connect timeout = " + getServerTimeout());
		return conn;
	}
	
	public int getServerTimeout() {
		return this.timeout;
	}
	
	public boolean setServerTimeout(int value) {
		if(value < 5000 || value > 120000) {
			return false;
		}
		this.timeout = value;
		return true;
	}

	 private class MyHostnameVerifier implements HostnameVerifier{

         @Override
         public boolean verify(String hostname, SSLSession session) {
        	 return true;
         }

	 }

	 private class MyTrustManager implements X509TrustManager{

         @Override
         public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
         }
         
         @Override
         public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
         }

         @Override
         public X509Certificate[] getAcceptedIssuers() {
            return null;
         }        

	 }

}
