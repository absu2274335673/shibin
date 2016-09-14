package net.engyne.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import net.engyne.domain.Template;
import net.engyne.mqqt.MqttServer;
import net.engyne.util.TxQueryRunner;

public class TemplateDao {
	
	private static Logger logger = Logger.getLogger(MqttServer.class); 
	public static List<Template> findTemplate(String rule_index) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "SELECT appid,template_index,template_name,template_type,template_title,template_desc,photo FROM view_rule_template WHERE rule_index=?";
		Object[] params = { rule_index };
		List<Template> templates=null;
		try {
			templates = qr.query(sql, new BeanListHandler<Template>(
					Template.class), params);

		} catch (SQLException e) {
			logger.error("TemplateDao查询异常",e);
			
		}

		return templates;
	}
	public static int updateTemplateCount(String _index) {
		QueryRunner qr = new TxQueryRunner();
		String sql = "update template set count=count+1 WHERE _index=?";
		Object[] params = { _index };
		int result=0;
		try {
			result = qr.update(sql, params);

		} catch (SQLException e) {
			logger.error("TemplateDao查询异常",e);
			
		}

		return result;
	}
}
