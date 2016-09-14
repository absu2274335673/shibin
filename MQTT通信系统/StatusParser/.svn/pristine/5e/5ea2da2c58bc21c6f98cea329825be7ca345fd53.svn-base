package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.util.TxQueryRunner;

public class AccountDao 
{
	private static Logger logger = Logger.getLogger(AccountDao.class); 
	public static void updateAccount(String clientid,int status) {
		QueryRunner qr = new TxQueryRunner();
		String sql ="UPDATE account SET  online=? WHERE username = ?" ;
		Object[] params = { status,clientid };
		try {
			int number= qr.update(sql, params);
			logger.info("更新影响的行数为"+number);
		} catch (SQLException e) {
			logger.error("AccounttDao查询异常",e);
		}

	}
}
