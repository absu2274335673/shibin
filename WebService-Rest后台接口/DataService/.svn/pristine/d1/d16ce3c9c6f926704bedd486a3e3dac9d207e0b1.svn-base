package net.engyne.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.ClientDao;
import net.engyne.dao.RuleDao;
import net.engyne.po.RuleQueryVo;
import net.engyne.service.RuleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Path("RuleService")
public class RuleServiceImpl implements RuleService {

	private ApplicationContext applicationContext;
	public RuleServiceImpl()
	{
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@POST
	@Path("getRuleQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRuleQuery(  String ids)
	{
		System.out.println(ids);
		JSONObject json=JSONObject.fromString(ids);
		String str=json.getString("ids");
		System.out.println(str);
		JSONArray ja=JSONArray.fromString(str);
		System.out.println(ja.get(0));
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<ja.length();i++)
		{
			System.out.println(ja.get(i));
			list.add((Integer) ja.get(i));
			
		}
		RuleQueryVo rqv=new RuleQueryVo();
		rqv.setIds(list);
		System.out.println(rqv.toString());
		RuleDao ruleDao=(RuleDao)applicationContext.getBean("ruleDao");
		String result=ruleDao.ruleQuery(rqv);
		return result;
	}
//	public static void main(String[] args) {
//		RuleService rs=new RuleService();
//		RuleQueryVo rqv=new RuleQueryVo();
//		List<Integer> list=new ArrayList<Integer>();
//		list.add(3);
//		list.add(4);
//		rqv.setIds(list);
//		String str=rs.getRuleQuery(rqv);
//		System.out.println(str);
//	}
}
