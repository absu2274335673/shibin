package net.engyne.accountdao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.conversationdao.ConversationDao;
import net.engyne.domain.Account;
import net.engyne.domain.Conversation;
import net.engyne.util.TxQueryRunner;

public class AccountDao
{
	private static Logger logger = Logger.getLogger(ConversationDao.class); 
	public static List<Account> getStaffList(String teamid,int status) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="SELECT username,account_type FROM account WHERE teamid=? and online=?";
		Object[] params = { teamid,status };
		List<Account> accounts = null;
		try {
			accounts = qr.query(sql, new BeanListHandler<Account>(Account.class),
					params);
		} catch (SQLException e) {
			logger.error("ConversationDao查询异常",e);
		}

		return accounts;
	}
	public static List<Account> getDefaultStaff(String teamid,int defaultn) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="SELECT username FROM account WHERE teamid=? and _default=?";
		Object[] params = { teamid,defaultn };
		List<Account> accounts = null;
		try {
			accounts = qr.query(sql, new BeanListHandler<Account>(Account.class),
					params);
		} catch (SQLException e) {
			logger.error("AccountDao查询异常",e);
		}

		return accounts;
	}
}
