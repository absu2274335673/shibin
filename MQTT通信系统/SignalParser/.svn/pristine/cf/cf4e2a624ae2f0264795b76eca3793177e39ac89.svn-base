package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Client;
import net.engyne.domain.Conversation;
import net.engyne.util.TxQueryRunner;

public class ClientDao
{
	private static Logger logger = Logger.getLogger(ClientDao.class); 
	public static void updateClient(String clientid,String phonenumber) {
		QueryRunner qr = new TxQueryRunner();
		
		if(clientid.length()!=36)
		{
			clientid=clientid.substring(0, 36);
			logger.info("实际clientid为"+clientid);
		}
		logger.info("clientid"+clientid+"phonenumber"+phonenumber);
		String sql ="UPDATE client SET phonenumber = ? WHERE clientid = ?" ;
		Object[] params = { phonenumber,clientid };
		try {
			int number= qr.update(sql, params);
			logger.info("更新影响的行数为"+number);
		} catch (SQLException e) {
			logger.error("ClientDao更新异常",e);
		}

	}
	public static List<Client> findNickname(String clientid) {
		QueryRunner qr = new TxQueryRunner();
		
		if(clientid.length()!=36)
		{
			clientid=clientid.substring(0, 36);
			logger.info("实际clientid为"+clientid);
		}
		logger.info("clientid"+clientid);
		String sql ="select clientid,nickname from client WHERE clientid = ?" ;
		Object[] params = { clientid };
		List<Client> clients=null;
		try {
			clients = qr.query(sql, new BeanListHandler<Client>(
					Client.class), params);
		} catch (SQLException e) {
			logger.error("ClientDao查询异常",e);
		}
		return clients;
	}
}
