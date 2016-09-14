package net.engyne.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.OneDimensionDao;
import net.engyne.dao.SubDimensionDao;
import net.engyne.po.ErrResult;
import net.engyne.po.OneDimensionQueryVo;
import net.engyne.po.RealSubDimension;
import net.engyne.po.SubDimension;
import net.engyne.po.SubDimensionQuery;
import net.engyne.po.SubDimensionQueryVo;
import net.engyne.service.SubDimensionService;
import net.engyne.util.ApplicationContextHelper;
import net.engyne.util.BigDoubleToString;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;
@Path("SubDimensionService")
public class SubDimensionServiceImpl  implements SubDimensionService  {

	private ApplicationContext applicationContext;
//	public SubDimensionServiceImpl()
//	{
////		applicationContext=ContextLoader.getCurrentWebApplicationContext(); 
//		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//	}
	
	@POST
	@Path("getSubDimensionQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubDimensionQuery(  String queryData)
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
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'table' not found")).toString(); 
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
		
		Double start=0.0;
		if(json.has("start"))
		{
			start=json.getDouble("start");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'start' not found")).toString(); 
		}
		Double end=0.0;
		if(json.has("end"))
		{
			end=json.getDouble("end");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'end' not found")).toString(); 
		}
		Double step=json.getDouble("step");
		if(json.has("step"))
		{
			step=json.getDouble("step");
		}
		else
		{
			return  JSONObject.fromBean(new ErrResult("FAIL","param of 'step' not found")).toString(); 
		}
		String groupby="";
		if(json.has("groupby"))
		{
			 groupby=json.getString("groupby");
		}
		SubDimensionQueryVo sdqv=new SubDimensionQueryVo();
		System.out.println(sdqv.toString());
		sdqv.setFilter(filter);
		sdqv.setTable(table);
		sdqv.setTarget(target);
		sdqv.setXaxis(xaxis);
		sdqv.setYmode(ymode);
		sdqv.setStart(start);
		sdqv.setEnd(end);
		sdqv.setStep(step);
		sdqv.setGroupby(groupby);
		System.out.println(sdqv.toString());
		Double days=(end-start)/step;
		System.out.println(days);
		List<SubDimension> subDimensions=new ArrayList<SubDimension>();
		for(int i=0;i<days;i++)
		{
			SubDimensionQuery sd=new SubDimensionQuery();
			sd.setTable(table);
			sd.setTarget(target);
			sd.setXaxis(xaxis);
			sd.setYmode(ymode);
			sd.setFilter(filter);
			sd.setGroupby(groupby);
			sd.setStart(start+i*step);
			sd.setEnd(start+(i+1)*step);
//			SubDimensionDao subDimensionDao=(SubDimensionDao)applicationContext.getBean("subDimensionDao");
			SubDimensionDao subDimensionDao=(SubDimensionDao)ApplicationContextHelper.getBean("subDimensionDao");
			SubDimension subDimension=subDimensionDao.getSubDimensionQuery(sd);
			System.out.println(subDimension);
			subDimensions.add(subDimension);
		}
		RealSubDimension rsd=new RealSubDimension();
		List<List<String>> data=new ArrayList<List<String>>();
		for(int i=0;i<subDimensions.size();i++)
		{
			List<String>  list=new ArrayList<String>();
			list.add(BigDoubleToString.doubleToString(subDimensions.get(i).getStart()));
			list.add(BigDoubleToString.doubleToString(subDimensions.get(i).getEnd()));
			list.add(subDimensions.get(i).getCount()+"");
			data.add(list);
		}
		rsd.setData(data);
		rsd.setResult("SUCCESS");
		 JSONObject jsonObject = JSONObject.fromBean(rsd); 
		 return jsonObject.toString();
	
	}

	
}
