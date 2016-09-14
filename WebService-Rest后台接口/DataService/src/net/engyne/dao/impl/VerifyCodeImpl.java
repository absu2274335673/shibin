package net.engyne.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import net.engyne.dao.VerifyCodeDao;
import net.engyne.po.VerifyCode;

public class VerifyCodeImpl extends SqlSessionDaoSupport implements VerifyCodeDao{

	public void update(VerifyCode vc) {
		
		SqlSession sqlSession =  this.getSqlSession();
		List<VerifyCode> verifyCodes=sqlSession.selectList("test.verifycode_find", vc);
		if(verifyCodes.size()>0)
		{
			sqlSession.update("test.verifycode_update", vc);
		}
		else
		{
			sqlSession.insert("test.verifycode_insert", vc);
		}
		
	}

	public boolean check(VerifyCode vc) {
		
		SqlSession sqlSession =  this.getSqlSession();
		List<VerifyCode> verifyCodes=sqlSession.selectList("test.verifycode_check", vc);
		if(verifyCodes.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
