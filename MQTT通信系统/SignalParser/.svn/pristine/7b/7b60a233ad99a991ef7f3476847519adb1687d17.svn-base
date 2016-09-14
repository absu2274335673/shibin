package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Account;
import net.engyne.domain.Conversation;
import net.engyne.util.TxQueryRunner;

public class AccountDao 
{
	private static Logger logger = Logger.getLogger(AccountDao.class); 
	public static List<Account> findByUsername(String username) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "SELECT phonenumber,username,devicetoken from account WHERE username=?";
		Object[] params = { username };
		List<Account> accounts=null;
		try {
			accounts = qr.query(sql, new BeanListHandler<Account>(
					Account.class), params);

		} catch (SQLException e) {
			logger.error("AccountDao查询异常",e);
		}
		return accounts;
	}
	public static List<Account> findByLevel(int  level) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "SELECT phonenumber,username,devicetoken from account WHERE level=?";
		Object[] params = { level };
		List<Account> accounts=null;
		try {
			accounts = qr.query(sql, new BeanListHandler<Account>(
					Account.class), params);

		} catch (SQLException e) {
			logger.error("AccountDao查询异常",e);
			
		}

		return accounts;
	}

}
