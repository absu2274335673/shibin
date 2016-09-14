package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Conversation;
import net.engyne.util.TxQueryRunner;

public class ConversationDao
{
	private static Logger logger = Logger.getLogger(ConversationDao.class); 
	public static List<Conversation> findUsername(String convid) {
		logger.info("cccccccc");
		QueryRunner qr = new TxQueryRunner();
		String sql = "SELECT convid,username from conversation WHERE convid=?";
		Object[] params = { convid };
		List<Conversation> conversations=null;
		try {
			logger.info("ddddddddd");
			conversations = qr.query(sql, new BeanListHandler<Conversation>(
					Conversation.class), params);
			logger.info("ffffffff");
		} catch (SQLException e) {
			logger.error("TemplateDao查询异常",e);
			
		}

		return conversations;
	}
}
