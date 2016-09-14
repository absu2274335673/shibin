package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.util.TxQueryRunner;

public class RuleDao
{

	private static Logger logger = Logger.getLogger(RuleDao.class); 
	public static int updateRuleCount(String _index) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "update rule set count=count+1 WHERE _index=?";
		Object[] params = {_index };
		int result=0;
		try {
			result = qr.update(sql, params);

		} catch (SQLException e) {
			logger.error("RuleDao查询异常",e);
			
		}

		return result;
	}
}
