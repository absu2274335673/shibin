package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.util.TxQueryRunner;



public class ClientDao
{
	private static Logger logger = Logger.getLogger(ClientDao.class); 
	public static void updateClient(String os,String network,String terminal,String from) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="UPDATE client SET os = ? ,network=?,terminal=? WHERE clientid = ?" ;
		Object[] params = { os,network,terminal,from };
		try {
			logger.info("dddddddddddddddd");
			int number= qr.update(sql, params);
			logger.info("更新影响的行数为"+number);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("ClientDao查询异常",e);
		}

	}
}
