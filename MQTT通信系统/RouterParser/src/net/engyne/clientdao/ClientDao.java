package net.engyne.clientdao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Account;
import net.engyne.domain.Client;
import net.engyne.util.TxQueryRunner;

public class ClientDao {
	 	private static Logger logger = Logger.getLogger(ClientDao.class); 
	 	public static List<Client> getUsertype(String clientid) {
		QueryRunner qr = new TxQueryRunner();
		String sql ="SELECT clientid,usertype FROM client WHERE clientid=?";
		Object[] params = { clientid };
		List<Client> clients = null;
		try {
			clients = qr.query(sql, new BeanListHandler<Client>(Client.class),
					params);
		} catch (SQLException e) {
			logger.error("ConversationDao查询异常",e);
		}

		return clients;
	}
}
