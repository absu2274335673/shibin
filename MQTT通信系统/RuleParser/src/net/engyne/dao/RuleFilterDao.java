package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Medium;
import net.engyne.mqqt.MqttServer;
import net.engyne.util.TxQueryRunner;

public class RuleFilterDao {
	

	private static Logger logger = Logger.getLogger(MqttServer.class); 
	public static List<Medium> findFilters(String ruleIndex) {
		
		QueryRunner qr = new TxQueryRunner();
		String sql ="SELECT filter_key, filter_type , filter_value , begin , end FROM view_rule_filter WHERE rule_index=?";
		Object[] params = { ruleIndex };
		List<Medium> mediums = null;
		try {
			mediums = qr.query(sql, new BeanListHandler<Medium>(Medium.class),
					params);
		} catch (SQLException e) {
			logger.error("RuleFilterDao查询异常",e);
		}

		return mediums;
	}
}
