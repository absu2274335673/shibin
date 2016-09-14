package net.engyne.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import net.engyne.dao.RealUserDao;
import net.engyne.dao.RuleDao;
import net.engyne.dao.RuleFilterDao;
import net.engyne.dao.RulePageEventDao;
import net.engyne.dao.TemplateDao;
import net.engyne.domain.Medium;
import net.engyne.domain.Rule;
import net.engyne.domain.Template;
import net.engyne.domain.User;

public class DaoTest
{
	@org.junit.Test
	public void realUserDaoTest()
	{
		List<Medium> mediums=new ArrayList<Medium>();
		Medium medium1=new Medium();
		medium1.setFilter_key("nickname");
		medium1.setFilter_type("fuzzy");
		medium1.setFilter_value("温有方");
		mediums.add(medium1);
		Medium medium2=new Medium();
		medium2.setFilter_key("province");
		medium2.setFilter_type("fuzzy");
		medium2.setFilter_value("北京");
		mediums.add(medium2);
		Medium medium3=new Medium();
		medium3.setFilter_key("registtime");
		medium3.setFilter_type("range");
		medium3.setBegin("1471163110");
		medium3.setEnd("1471163120");
		mediums.add(medium3);
		Medium medium4=new Medium();
		medium4.setFilter_key("clientid");
		medium4.setFilter_type("value");
		medium4.setFilter_value("62559C5E-4046-C0F5-CAA6-FBFD25F0A36B");
		mediums.add(medium4);
		Medium medium5=new Medium();
		medium5.setFilter_key("userage");
		medium5.setFilter_type("range");
		medium5.setEnd("25");
		mediums.add(medium5);
		Medium medium6=new Medium();
		medium6.setFilter_key("tagid");
		medium6.setFilter_type("multi");
		medium6.setFilter_value("029BB779A15CC4CE45595D237708D540");
		mediums.add(medium6);
		Medium medium7=new Medium();
		medium7.setFilter_key("tagid");
		medium7.setFilter_type("multi");
		medium7.setFilter_value("1C8FD510BECEC2CF4CCE794A43DFD666");
		mediums.add(medium7);
		List<User> users=RealUserDao.fuzzyQuery( mediums);
		//String str="[User [userid=26, username=1463798730452Nd64ZSSc, nickname=石斌, appindex=7, usersex=1, userage=23, usertype=0, registtime=1463798729, lastseetime=1464589789, lastpageindex=0, provinceid=1, channelid=1, terminalid=0, email=null, phonenumber=null]]";
		System.out.println(users.toString());
		//Assert.assertEquals(str, users.toString());
	}
	@org.junit.Test
	public void ruleFilterDaoTest()
	{
	List<Medium> mediums=RuleFilterDao.findFilters("106");
	String str="[Medium [filter_key=province, filter_type=fuzzy, filter_value=北京, begin=null, end=null]]";
	//System.out.println(mediums.toString());
	Assert.assertEquals(str, mediums.toString());
	}
	@org.junit.Test
	public void rulePageEventDaoTest()
	{
	List<Rule> rules=RulePageEventDao.findRules("openchat", "8",null);
	//System.out.println(rules.toString());
	String str="[Rule [rule_index=106, appid=null, rule_title=medical规则22222, rule_desc=描述！, sent=null, read=null, once=1]]";
	Assert.assertEquals(str, rules.toString());
	}
	@org.junit.Test
	public void templateDaoTest()
	{
	List<Template> templates=TemplateDao.findTemplate("106");
	//System.out.println(templates.toString());
	String str="[Template [template_index=97, appid=medical, template_name=openchatTest_6_14, template_type=news, template_desc=<p>openchat</p>, template_title=asdf, photo=http://7xrgmq.com1.z0.glb.clouddn.com/o_1al6p38l215om23i15mt1uclej7g.jpeg]]";
	Assert.assertEquals(str, templates.toString());
	}
	@org.junit.Test
	public void countTest()
	{
		RuleDao.updateRuleCount("160");
		TemplateDao.updateTemplateCount("89");
		
	}
}
