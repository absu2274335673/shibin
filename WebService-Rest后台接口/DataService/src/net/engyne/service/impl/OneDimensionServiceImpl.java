package net.engyne.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.engyne.dao.OneDimensionDao;
import net.engyne.po.ErrResult;
import net.engyne.po.OneDimensionQueryVo;
import net.engyne.service.OneDimensionService;
import net.engyne.util.ApplicationContextHelper;
import net.sf.json.JSONObject;
@Path("OneDimensionService")
public class OneDimensionServiceImpl implements OneDimensionService {

	private ApplicationContext applicationContext;
//	public OneDimensionServiceImpl()
//	{
//		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//	}
	@POST
	@Path("getOneDimensionQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getOneDimensionQuery(  String queryData)
	{
		System.out.println(queryData);
		JSONObject json=JSONObject.fromString(queryData);
		String table="";
		if(json.has("table"))
		{
			 table=json.getString("table");
		}
		else
		{
			System.out.println(new ErrResult("FAIL","param of 'table' not found"));
			return  JSONObject.fromBean(new ErrResult("Fail","param of 'table' not found")).toString(); 
		}
		String xaxis="";
		if(json.has("xaxis"))
		{
			xaxis=json.getString("xaxis");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'xaxis' not found")).toString(); 
		}
		String ymode="";
		if(json.has("ymode"))
		{
			ymode=json.getString("ymode");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'ymode' not found")).toString(); 
		}
		String target="";
		if(json.has("target"))
		{
			target=json.getString("target");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'target' not found")).toString(); 
		}
		
		
		String filter="";
		if(json.has("filter"))
		{
		   filter=json.getString("filter");
		}
		
		OneDimensionQueryVo odqv=new OneDimensionQueryVo();
		System.out.println(odqv.toString());
		odqv.setFilter(filter);
		odqv.setTable(table);
		odqv.setTarget(target);
		odqv.setXaxis(xaxis);
		odqv.setYmode(ymode);
		System.out.println(odqv.toString());
		System.out.println(applicationContext);
		//OneDimensionDao oneDimensionDao=(OneDimensionDao)applicationContext.getBean("oneDimensionDao");
		OneDimensionDao oneDimensionDao=(OneDimensionDao)ApplicationContextHelper.getBean("oneDimensionDao");
		String result=oneDimensionDao.getOndDimensionQuery(odqv);
		return result;
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext=arg0;
	}
	
	

}
