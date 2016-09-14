package net.engyne.communication.service.message;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.AccountDao;
import net.engyne.dao.VerifyCodeDao;
import net.engyne.po.VerifyCode;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;

@Path("Verification")
public class SendVerification {

	@POST
	@Path("sendVerificationCode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String sendVerificationCode(String queryData)
	{
		JSONObject json=JSONObject.fromString(queryData);
		
		String phonenumber="";
		if(json.has("phoneNumber"))
		{
			phonenumber=json.getString("phoneNumber");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		json.put("projectID", "S1A1v");
		String code=RandomCodeGenerator.random(6);
		long time=System.currentTimeMillis()/1000;
		long expire=time+60;
		json.put("code",code);
		json.put("time", "1");
		VerifyCodeDao verifyCodeDao=(VerifyCodeDao)ApplicationContextHelper.getBean("verifyCodeDao");
		VerifyCode vc=new VerifyCode();
		vc.setPhonenumber(phonenumber);
		vc.setExpire(expire);
		vc.setCode(code);
		verifyCodeDao.update(vc);
		MessageXSendDemo.sendMessage(json.toString());
		return new JSONObject().put("resutl", "SUCCESS").toString();
		
	}
	@POST
	@Path("checkVerifyCode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String checkVerifyCode(String queryData)
	{
		System.out.println(queryData);
		
		JSONObject json=JSONObject.fromString(queryData);
		
		String phoneNumber="";
		String code="";
		if(json.has("phoneNumber"))
		{
			phoneNumber=json.getString("phoneNumber");
			
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
			
		}
		if(json.has("code"))
		{
			code=json.getString("code");
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
		
		VerifyCodeDao verifyCodeDao=(VerifyCodeDao)ApplicationContextHelper.getBean("verifyCodeDao");
		VerifyCode vc=new VerifyCode();
		vc.setPhonenumber(phoneNumber);
		vc.setCode(code);
		vc.setExpire(System.currentTimeMillis()/1000);
		boolean flag=verifyCodeDao.check(vc);
		if(flag)
		{
			return new JSONObject().put("result", "SUCCESS").toString();
			
		}
		else
		{
			return new JSONObject().put("result", "FAIL").toString();
		}
	}
}
