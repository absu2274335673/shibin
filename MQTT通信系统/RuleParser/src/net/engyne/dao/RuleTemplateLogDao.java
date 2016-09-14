package net.engyne.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import net.engyne.mqqt.MqttServer;
import net.engyne.util.TxQueryRunner;

public class RuleTemplateLogDao
{
	private static Logger logger = Logger.getLogger(MqttServer.class); 
	public static int updateRuleTemplateLog(String ruleindex,String templateindex,String clientid,String time) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "insert into rule_template_log(rule_template_log_id,rule_index,template_index,clientid,time) values(uuid(),?,?,?,?);";
		Object[] params = { ruleindex,templateindex,clientid,time };
		int result=0;
		try {
			result = qr.update(sql, params);

		} catch (SQLException e) {
			logger.error("TemplateDao查询异常",e);
			
		}

		return result;
	}
	public static void main(String[] args) {
		updateRuleTemplateLog("1", "2", "asd", "123223434");
	}
}
