package net.engyne.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Account;
import net.engyne.domain.Application;
import net.engyne.util.TxQueryRunner;

public class AccountDao {

	private static Logger logger = Logger.getLogger(AccountDao.class); 
	public static List<Account> getOnlineStaffList(String appid) {
		List<Application> apps=ApplicationDao.getAppinfo(appid);
		String teamid=apps.get(0).getTeamid();
		QueryRunner qr = new TxQueryRunner();
		String sql ="select _index,username,headimageurl,teamid,email,nickname,online,lastseen,phonenumber,bundletoken,devicetoken from account where teamid=? and online=1";
		String sql2="select _index,username,headimageurl,teamid,email,nickname,online,lastseen,phonenumber,bundletoken,devicetoken from account where teamid=? order by lastseen desc";
		Object[] params = { teamid };
		List<Account> accounts=new ArrayList<Account>();
		try {
			accounts = qr.query(sql, new BeanListHandler<Account>(
					Account.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("AccountDao查询异常",e);
		}
		if(accounts.size()==0)
		{
			try {
				accounts = qr.query(sql2, new BeanListHandler<Account>(
						Account.class), params);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("AccountDao查询异常",e);
			}
		}
		while(accounts.size()>3)
		{
			accounts.remove(accounts.size()-1);
		}
		return accounts;
	}
	public static void main(String[] args) {
		List<Account> acs=getOnlineStaffList("shopping");
		System.out.println(acs.get(0).toString());
	}
}
