package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.util.TxQueryRunner;

public class LoginfoDao 
{
	private static Logger logger = Logger.getLogger(LoginfoDao.class); 
	public static void updateLoginfo(String clientid,String username,int count,String time,String caller,String callee) {
		QueryRunner qr = new TxQueryRunner();
		
		if(clientid.length()!=36)
		{
			clientid=clientid.substring(0, 36);
			logger.info("实际clientid为"+clientid);
		}
		logger.info("clientid"+clientid+"username"+username+"time"+time);
		String sql ="insert into loginfo_telephone (clientid,username,count,time,caller,callee) values(?,?,?,?,?,?)" ;
		Object[] params = { clientid,username,count,time,caller,callee };
		try {
			int number= qr.update(sql, params);
			logger.info("更新影响的行数为"+number);
		} catch (SQLException e) {
			logger.error("ClientDao更新异常",e);
		}

	}
}
