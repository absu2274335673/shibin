package net.engyne.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import net.engyne.dao.AccountDao;
import net.engyne.po.AccountBindQueryVo;
import net.engyne.po.AccountDefaultQueryVo;

public class AccountDaoImpl extends SqlSessionDaoSupport implements AccountDao{

	public void bind(AccountBindQueryVo abqv) {

		SqlSession sqlSession =  this.getSqlSession();
		sqlSession.update("test.account_bind", abqv);
	}

	public void setDefaultRoute(AccountDefaultQueryVo adqv) {
		// TODO Auto-generated method stub
	
		SqlSession sqlSession =  this.getSqlSession();
		sqlSession.update("test.account_default", adqv);
	}

}
