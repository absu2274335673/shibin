package net.engyne.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.AccountTypeDao;
import net.engyne.dao.TelLogDao;
import net.engyne.po.RealTelLog;
import net.engyne.po.TelLogQueryVo;
import net.engyne.service.TelLogService;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;

@Path("TelLogService")
public class TelLogServiceImpl implements TelLogService
{

	@POST
	@Path("getCallLog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCallLog(String queryData) {
		
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String clientid="";
		if(json.has("clientid"))
		{
			clientid=json.getString("clientid");
		}
		else
		{
			RealTelLog rtl=new RealTelLog();
			rtl.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rtl); 
			return jsonObject.toString();
		}
		TelLogQueryVo tlqv=new TelLogQueryVo();
		tlqv.setClientid(clientid);
		TelLogDao telLogDao=(TelLogDao)ApplicationContextHelper.getBean("telLogDao");
		String result=telLogDao.getCallLog(tlqv);
		return result;
		
	
	}

	
}
