package net.engyne.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;

import net.engyne.dao.AccountTypeDao;
import net.engyne.dao.OneDimensionDao;
import net.engyne.po.AccountTypeQueryVo;
import net.engyne.po.RealAccountType;
import net.engyne.service.AccountTypeService;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;
@Path("AccountTypeService")
public class AccountTypeServiceImpl implements AccountTypeService{

	private ApplicationContext applicationContext;
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String add(String queryData) {
		// TODO Auto-generated method stub
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String _index="";
		String account_type_name="";
		if(json.has("name"))
		{
			account_type_name=json.getString("name");
		}
		else
		{
			RealAccountType rat=new RealAccountType();
			rat.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rat); 
			return jsonObject.toString();
		}
		AccountTypeDao accountTypeDao=(AccountTypeDao)ApplicationContextHelper.getBean("accountTypeDao");
		AccountTypeQueryVo atqv=new AccountTypeQueryVo();
		atqv.set_index(_index);
		atqv.setAccount_type_name(account_type_name);
		accountTypeDao.add(atqv);
		RealAccountType rat=new RealAccountType();
		rat.setResult("SUCCESS");
		JSONObject jsonObject = JSONObject.fromBean(rat); 
		return jsonObject.toString();
	}

	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(String queryData) {
		// TODO Auto-generated method stub
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String _index="";
		if(json.has("_index"))
		{
			_index=json.getString("_index");
		}
		else
		{
			RealAccountType rat=new RealAccountType();
			rat.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rat); 
			return jsonObject.toString();
		}
		String account_type_name="";
		AccountTypeDao accountTypeDao=(AccountTypeDao)ApplicationContextHelper.getBean("accountTypeDao");
		AccountTypeQueryVo atqv=new AccountTypeQueryVo();
		atqv.set_index(_index);
		atqv.setAccount_type_name(account_type_name);
		accountTypeDao.delete(atqv);
		RealAccountType rat=new RealAccountType();
		rat.setResult("SUCCESS");
		JSONObject jsonObject = JSONObject.fromBean(rat); 
		return jsonObject.toString();
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String queryData) {
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String _index="";
		if(json.has("_index"))
		{
			_index=json.getString("_index");
		}
		else
		{
			RealAccountType rat=new RealAccountType();
			rat.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rat); 
			return jsonObject.toString();
		}
		String account_type_name="";
		if(json.has("name"))
		{
			account_type_name=json.getString("name");
		}
		else
		{

			RealAccountType rat=new RealAccountType();
			rat.setResult("FAIL");
			JSONObject jsonObject = JSONObject.fromBean(rat); 
			return jsonObject.toString();
		}
		AccountTypeDao accountTypeDao=(AccountTypeDao)ApplicationContextHelper.getBean("accountTypeDao");
		AccountTypeQueryVo atqv=new AccountTypeQueryVo();
		atqv.set_index(_index);
		atqv.setAccount_type_name(account_type_name);
		accountTypeDao.update(atqv);
		RealAccountType rat=new RealAccountType();
		rat.setResult("SUCCESS");
		JSONObject jsonObject = JSONObject.fromBean(rat); 
		return jsonObject.toString();
	}

	@POST
	@Path("query")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String query(String queryData) {
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String _index="";
		if(json.has("_index"))
		{
			_index=json.getString("_index");
		}
		String account_type_name="";
		AccountTypeDao accountTypeDao=(AccountTypeDao)ApplicationContextHelper.getBean("accountTypeDao");
		AccountTypeQueryVo atqv=new AccountTypeQueryVo();
		atqv.set_index(_index);
		atqv.setAccount_type_name(account_type_name);
		if(_index.equals(""))
		{
			String result=accountTypeDao.queryAll();
			return result;
		}
		else
		{
			String result=accountTypeDao.queryOne(atqv);
			return result;
		}
	}
	
	

}
