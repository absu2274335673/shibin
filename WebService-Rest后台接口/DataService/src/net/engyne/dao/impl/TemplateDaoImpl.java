package net.engyne.dao.impl;

import java.util.List;

import net.engyne.dao.TemplateDao;
import net.engyne.po.Area;
import net.engyne.po.RealArea;
import net.engyne.po.RuleQueryVo;
import net.engyne.po.TemplateQueryVo;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TemplateDaoImpl extends SqlSessionDaoSupport implements TemplateDao {

	public  String templateQuery(TemplateQueryVo tqv)
	{
	
		// 通过工厂得到SqlSession
		SqlSession sqlSession =  this.getSqlSession();
		// list中的user和映射文件中resultType所指定的类型一致
		List<Area> areas = sqlSession.selectList("test.findTemplateCount",tqv);
		System.out.println(areas);
		RealArea ra=CommonUtils.ConvertArea(areas);
		 JSONObject jsonObject = JSONObject.fromBean(ra); 
		 return jsonObject.toString();

	}
}
