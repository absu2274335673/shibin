package net.engyne.dao.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import net.engyne.dao.OneDimensionDao;
import net.engyne.po.Area;
import net.engyne.po.OneDimension;
import net.engyne.po.OneDimensionQueryVo;
import net.engyne.po.RealArea;
import net.engyne.po.RealOneDimension;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONObject;
@Path("OneDimensionDao")
public class OneDimensionDaoImpl extends SqlSessionDaoSupport implements OneDimensionDao {

	@POST
	@Path("getOndeDimensionQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  String getOndDimensionQuery(OneDimensionQueryVo odqv)
	{
	
		// 通过工厂得到SqlSession
		SqlSession sqlSession =  this.getSqlSession();
		// list中的user和映射文件中resultType所指定的类型一致
		List<OneDimension> oneDimensions = sqlSession.selectList("test.findOneDimensionCount",odqv);
		System.out.println(oneDimensions);
		RealOneDimension rod=CommonUtils.ConvertOneDimension(oneDimensions);
		 JSONObject jsonObject = JSONObject.fromBean(rod); 
		 return jsonObject.toString();

	}
	

	
}
