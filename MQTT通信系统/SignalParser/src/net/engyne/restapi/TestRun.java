package net.engyne.restapi;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;


public class TestRun {
	private static Logger logger = Logger.getLogger(TestRun.class);
    public  void start(String caller,String callee) throws HttpException, IOException {
//        System.out.println("[trigger]--enter");
//
//        String result = "Failed";
//        
//        //server
//        String server = "205.177.226.80:8543";
//
//
//        
//        String num1 = callee;//"+8618730459198";  //phone number of the caller, please fill the real number
//        String num2 = caller;//"+8613163359533";  //phone number of the callee, please fill the real number
//        String display = callee;//"8675528783238";  //display number
//        
//        //app detals
//      //  String appKey = "wfbVlIqjuT4miYMFxmqhMSn2Bsoa";
//        String appKey = "cqMIkoEd9AUrktTP7EMWNykTwlka";
//        
//        //SSO login info
//        String fastloginUsername = "+8675566552003";
//        String fastloginPassword = "mdx3600@HW";
//        
//        //parameters to be sent to app script
//        num1 = new RegulateNumber().process(num1);
//        num2 = new RegulateNumber().process(num2);
//        System.out.println(num1+"   "+num2);
//        if(num1 != null && 0 < num1.length() && num2 != null && 0 < num2.length()) {
//            try {
//                //initialize a OmpRestClient with app settings
//                OmpRestClient client = new OmpRestClient(server, appKey);
//
//                //acquire a token to trigger the app 
//                String token = client.getFastloginToken(fastloginUsername, fastloginPassword);
//                
//                //trigger app
//                result = client.triggerClick2call(token, num1, num2, display);
//            } catch(Exception e) {
//                System.out.println("[trigger]--catch exception: " + e.toString());
//                result = e.toString();
//            }
//        } else {
//            result = "Invalid Phone Number";
//        }
//        System.out.println("[trigger]--exit with result: " + result);
//    }
//    
//    class RegulateNumber {
//        
//        //process phone number, change +861xxx, 00861xxx 1xxx formats to 861xxxxxx
//        String process(String phone) {
//            
//            if(null == phone || phone.length() < 1) {
//                return phone;
//            }
//            //remove +, -, etc
//            phone = phone.replaceAll("[+]", "");
//            phone = phone.replaceAll(" ", "");
//            phone = phone.replaceAll("-", "");
//            //remove 00, e.g. 008619912345678 -> 8619912345678
//            if(null != phone && phone.length() == 15 && phone.startsWith("00")) {
//                phone = phone.substring(2);
//            }
//            //add 86, e.g. 19912345678 -> 8619912345678 
//            if(null != phone && phone.length() == 11 && phone.startsWith("1")) {
//                phone = "86" + phone;
//            }
//            
//            return phone;
//        }
    	String data="<Request>"
				+ "<Head>"
				+ "<MethodName>Dial</MethodName>"
				+ "<Spid>559228</Spid>"
				+ "<Appid>343</Appid>"
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
		remoteGetCall("http://open.fjii.com:8088/httpIntf/dealIntf?postData="+data);
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
    /**
     * @param args
     * @throws IOException 
     * @throws HttpException 
     */
    public static void main(String[] args) throws HttpException, IOException {
        new TestRun().start("13163359533","13163359533");
    }
    
    

}
