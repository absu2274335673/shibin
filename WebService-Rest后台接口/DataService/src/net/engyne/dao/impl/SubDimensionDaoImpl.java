package net.engyne.dao.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import net.engyne.dao.SubDimensionDao;
import net.engyne.po.OneDimension;
import net.engyne.po.OneDimensionQueryVo;
import net.engyne.po.RealOneDimension;
import net.engyne.po.SubDimension;
import net.engyne.po.SubDimensionQuery;
import net.engyne.po.SubDimensionQueryVo;
import net.engyne.util.BigDoubleToString;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONObject;

public class SubDimensionDaoImpl extends SqlSessionDaoSupport implements SubDimensionDao {

	
	public  SubDimension getSubDimensionQuery(SubDimensionQuery sdq)
	{
	
		// 通过工厂得到SqlSession
		SqlSession sqlSession =  this.getSqlSession();
		Double start=sdq.getStart();
		Double end=sdq.getEnd();
		String xaxis=sdq.getXaxis();
		String filter1=""+xaxis+">="+BigDoubleToString.doubleToString(start)+"  and  "+xaxis+"<"+BigDoubleToString.doubleToString(end)+"";
		String filternew="";
		if(!sdq.getFilter().equals(""))
		{
			filternew=filter1+" and " +sdq.getFilter();
		}
		else
		{
			filternew=filter1;
		}
		sdq.setFilter(filternew);
		// list中的user和映射文件中resultType所指定的类型一致
		SubDimension subDimension = sqlSession.selectOne("test.findSubDimensionCount",sdq);
		subDimension.setEnd(end);
		subDimension.setStart(start);
		System.out.println(subDimension);
//		RealOneDimension rod=CommonUtils.ConvertOneDimension(oneDimensions);
//		 JSONObject jsonObject = JSONObject.fromBean(rod); 
		 return subDimension;

	}
	

}
