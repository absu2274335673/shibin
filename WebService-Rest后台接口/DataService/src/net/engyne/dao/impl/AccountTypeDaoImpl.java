package net.engyne.dao.impl;

import java.util.List;

import net.engyne.dao.AccountTypeDao;
import net.engyne.po.AccountType;
import net.engyne.po.AccountTypeQueryVo;
import net.engyne.po.Area;
import net.engyne.po.RealAccountType;
import net.engyne.po.RealArea;
import net.engyne.po.TemplateQueryVo;
import net.engyne.util.CommonUtils;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class AccountTypeDaoImpl extends SqlSessionDaoSupport implements AccountTypeDao {

	
	public void add(AccountTypeQueryVo atqv) {
		// TODO Auto-generated method stub
		// 通过工厂得到SqlSession
		SqlSession sqlSession =  this.getSqlSession();
		sqlSession.update("test.account_type_add", atqv);
	}

	public void delete(AccountTypeQueryVo atqv) {
		// TODO Auto-generated method stub
		SqlSession sqlSession =  this.getSqlSession();
		sqlSession.update("test.account_type_delete", atqv);
		
	}

	public void update(AccountTypeQueryVo atqv) {
		// TODO Auto-generated method stub
		SqlSession sqlSession =  this.getSqlSession();
		sqlSession.update("test.account_type_update", atqv);
	}

	public String queryAll() {
		// TODO Auto-generated method stub
		SqlSession sqlSession =  this.getSqlSession();
		List<AccountType> accountTypes=sqlSession.selectList("test.account_type_queryAll");
		RealAccountType rat=new RealAccountType();
		rat.setResult("SUCCESS");
		rat.setData(accountTypes);
		 JSONObject jsonObject = JSONObject.fromBean(rat); 
		 return jsonObject.toString();
	}

	public String queryOne(AccountTypeQueryVo atqv) {
		// TODO Auto-generated method stub
		SqlSession sqlSession =  this.getSqlSession();
		List<AccountType> accountTypes=sqlSession.selectList("test.account_type_queryOne",atqv);
		RealAccountType rat=new RealAccountType();
		rat.setData(accountTypes);
		rat.setResult("SUCCESS");
		 JSONObject jsonObject = JSONObject.fromBean(rat); 
		 return jsonObject.toString();
		
	}
}
