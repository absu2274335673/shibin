package net.engyne.dao.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.engyne.dao.ClientDao;
import net.engyne.po.Area;
import net.engyne.po.RealArea;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
@Path("ClientDao")
public class ClientDaoImpl extends SqlSessionDaoSupport implements ClientDao{

	
	@POST
	@Path("locationQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  String locationQuery()
	{
	
		// ͨ�������õ�SqlSession
		SqlSession sqlSession =  this.getSqlSession();
		// list�е�user��ӳ���ļ���resultType��ָ��������һ��
		List<Area> areas = sqlSession.selectList("test.findAreaCount");
		System.out.println(areas);
		RealArea ra=CommonUtils.ConvertArea(areas);
		 JSONObject jsonObject = JSONObject.fromBean(ra); 
		 return jsonObject.toString();

	}
}
