package net.engyne.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.engyne.accountdao.AccountDao;
import net.engyne.domain.Account;
import net.engyne.mongodao.MongoDBDao;
import net.engyne.router.MqttRouter;

public class CustomService 
{
	private static Logger logger = Logger.getLogger(CustomService.class);  
//	public static ArrayList<String> getOnlineService(String teamid)
//	{
//		logger.info("首先根据teamid在account里面找到所有客服列表");
//		List<Account> accounts=AccountDao.getStaffList(teamid);
//		logger.info("获得所有的username列表");
//		ArrayList<String> usernames=new ArrayList<String>();
//		for(int i=0;i<accounts.size();i++)
//		{
//			usernames.add(accounts.get(i).getUsername());
//		}
//		logger.info("从mongo数据库中的connection中取得对应username的status");
//		for(int i=0;i<usernames.size();i++)
//		{
//			boolean flag=MongoDBDao.getMongoDBDaoInstance().isOnline("dolina", "connection", usernames.get(i));
//			if(flag)
//			{
//				logger.info(usernames.get(i)+"这个用户在线");
//			}
//			else
//			{
//				logger.info(usernames.get(i)+"这个用户不在线 需要移除这个用户从有效用户列表中");
//				usernames.remove(i);
//				i--;
//			}
//		}
//		return usernames;
//	}
}
