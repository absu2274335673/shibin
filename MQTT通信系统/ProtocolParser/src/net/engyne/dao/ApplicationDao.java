package net.engyne.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Application;
import net.engyne.util.TxQueryRunner;

public class ApplicationDao
{
	private static Logger logger = Logger.getLogger(ApplicationDao.class); 
	public static List<Application> getAppinfo(String appid) {
		QueryRunner qr = new TxQueryRunner();
		String sql ="select appid,teamid,welcomemsg,apptitle from application where appid=?" ;
		Object[] params = { appid };
		List<Application> applications=new ArrayList<Application>();
		try {
			logger.info("dddddddddddddddd");
			applications = qr.query(sql, new BeanListHandler<Application>(
					Application.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("ApplicationDao查询异常",e);
		}
		return applications;
	}
}
