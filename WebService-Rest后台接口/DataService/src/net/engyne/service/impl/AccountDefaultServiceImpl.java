package net.engyne.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.AccountDao;
import net.engyne.po.AccountDefaultQueryVo;
import net.engyne.po.RealAccountDefault;
import net.engyne.service.AccountDefaultService;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;
@Path("AccountDefaultService")
public class AccountDefaultServiceImpl implements AccountDefaultService {

	@POST
	@Path("setDefault")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String setDefault(String queryData) {
		
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String username="";
		if(json.has("username"))
		{
			username=json.getString("username");
		}
		else
		{
			RealAccountDefault rad=new RealAccountDefault();
			rad.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rad); 
			return jsonObject.toString();
		}
		AccountDefaultQueryVo adqv=new AccountDefaultQueryVo();
		adqv.setUsername(username);
		AccountDao accountDao=(AccountDao)ApplicationContextHelper.getBean("accountDao");
		accountDao.setDefaultRoute(adqv);
		RealAccountDefault rad=new RealAccountDefault();
		rad.setResult("SUCCESS");
		JSONObject jsonObject = JSONObject.fromBean(rad); 
		return jsonObject.toString();
	}

	
}
