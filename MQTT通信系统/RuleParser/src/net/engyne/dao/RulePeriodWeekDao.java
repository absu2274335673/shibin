package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Rule;
import net.engyne.domain.RuleIndex;
import net.engyne.mqqt.MqttServer;
import net.engyne.util.TxQueryRunner;

public class RulePeriodWeekDao 
{
	private static Logger logger = Logger.getLogger(MqttServer.class); 
	public static List<RuleIndex> findRules(String weekday, String rule_index,int offset) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "SELECT DISTINCT(rule_index) FROM view_rule_period_week where weekday=? AND rule_index=?  AND  ?>period_begin AND ?<period_end ";
		Object[] params = { weekday, rule_index,offset,offset};
		List<RuleIndex> ruleIndexs=null;
		try {
			ruleIndexs = qr
					.query(sql, new BeanListHandler<RuleIndex>(RuleIndex.class), params);
		} catch (SQLException e) {
			logger.error("RuleperiodWeekDao查询异常",e);
			
		}	
		return ruleIndexs;
	}
}
