package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.util.TxQueryRunner;

public class ConversationDao
{
	private static Logger logger = Logger.getLogger(ConversationDao.class); 
	public static void updateClient(String lastmsgtime,String convid) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="UPDATE conversation SET lastmsgtime= ? WHERE convid = ?" ;
		Object[] params = { lastmsgtime,convid };
		try {
			logger.info("dddddddddddddddd");
			int number= qr.update(sql, params);
			logger.info("更新影响的行数为"+number);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("AccountDao查询异常",e);
		}

	}
}
