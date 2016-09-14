package net.engyne.dao.impl;

import java.util.List;

import net.engyne.dao.TelLogDao;
import net.engyne.po.RealTelLog;
import net.engyne.po.TelLog;
import net.engyne.po.TelLogQueryVo;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TelLogDaoImpl extends SqlSessionDaoSupport implements TelLogDao{

	public String getCallLog(TelLogQueryVo tlqv) {

		SqlSession sqlSession =  this.getSqlSession();
		List<TelLog> telLogs=sqlSession.selectList("test.tellogquery", tlqv);
		RealTelLog rtl=new RealTelLog();
		rtl.setData(telLogs);
		rtl.setResult("SUCCESS");
	    JSONObject jsonObject = JSONObject.fromBean(rtl); 
		return jsonObject.toString();
	}

	
}
