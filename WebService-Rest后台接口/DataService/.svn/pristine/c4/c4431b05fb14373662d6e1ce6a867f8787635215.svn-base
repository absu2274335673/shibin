package net.engyne.service.impl;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.engyne.dao.AccountDao;
import net.engyne.po.AccountBindQueryVo;
import net.engyne.po.RealAccountBind;
import net.engyne.service.AccountBindService;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;
/**
 * 
 * @author binshi
 * @version：http://host:8080/DataService/services/AccountBindService
 *
 */
@Path("AccountBindService")
public class AccountBindServiceImpl implements AccountBindService {

	/**
	 * @author binshi：客服和客服类型的绑定
	 * @param：需要提供参数为 username, _index 分别表示客服的用户名，客服类别的索引
	 */
	@POST
	@Path("bind")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String bind(String queryData) {

		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String _index="";
		String username="";
		if(json.has("_index"))
		{
			_index=json.getString("_index");
		}
		else
		{
			RealAccountBind rab=new RealAccountBind();
			rab.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rab); 
			return jsonObject.toString();
		}
		
		if(json.has("username"))
		{
			username=json.getString("username");
		}
		else
		{
			RealAccountBind rab=new RealAccountBind();
			rab.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rab); 
			return jsonObject.toString();
		}
		AccountDao accountDao=(AccountDao)ApplicationContextHelper.getBean("accountDao");
		AccountBindQueryVo abqv=new AccountBindQueryVo();
		abqv.set_index(_index);
		abqv.setUsername(username);
		accountDao.bind(abqv);
		RealAccountBind rab=new RealAccountBind();
		rab.setResult("SUCCESS");
		JSONObject jsonObject = JSONObject.fromBean(rab); 
		return jsonObject.toString();
	}

}
