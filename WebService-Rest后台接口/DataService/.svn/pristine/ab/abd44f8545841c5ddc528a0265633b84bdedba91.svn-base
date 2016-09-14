package net.engyne.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.RuleDao;
import net.engyne.dao.TemplateDao;
import net.engyne.po.RuleQueryVo;
import net.engyne.po.TemplateQueryVo;
import net.engyne.service.TemplateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Path("TemplateService")
public class TemplateServiceImpl implements TemplateService {

	private ApplicationContext applicationContext;
	public TemplateServiceImpl()
	{
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	
	@POST
	@Path("getTemplateQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getTemplateQuery(String ids)
	{
		System.out.println(ids);
		String result="";
		try {
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
			TemplateQueryVo tqv=new TemplateQueryVo();
			tqv.setIds(list);
			System.out.println(tqv.toString());
			TemplateDao templateDao=(TemplateDao)applicationContext.getBean("templateDao");
			result = templateDao.templateQuery(tqv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return new JSONObject().put("result","FAIL").put("errmsg", e.getMessage()).toString();
		}
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
