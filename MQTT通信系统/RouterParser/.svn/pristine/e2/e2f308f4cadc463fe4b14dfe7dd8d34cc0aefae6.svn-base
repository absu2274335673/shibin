package net.engyne.conversationdao;

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
	public static List<Conversation> findConversation(String convid) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="SELECT convid,username,teamid FROM conversation WHERE convid=?";
		Object[] params = { convid };
		List<Conversation> conversations = null;
		try {
			conversations = qr.query(sql, new BeanListHandler<Conversation>(Conversation.class),
					params);
		} catch (SQLException e) {
			logger.error("ConversationDao查询异常",e);
		}

		return conversations;
	}
public static  void updateConversation(String convid,String username) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="update conversation set username=? where convid=? ";
		Object[] params = { username,convid };
		List<Conversation> conversations = null;
		try {
				qr.update(sql, params);
		} catch (SQLException e) {
			logger.error("ConversationDao更新异常",e);
		}

	}
}
