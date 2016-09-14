package net.engyne.domain;

import java.io.Serializable;

public class Rule implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rule_index;
	private String appid;
	private String rule_title;
	private String rule_desc;
	private String sent;
	private String read;
	private String once;
	private String foralltime;
	public String getForalltime() {
		return foralltime;
	}
	public void setForalltime(String foralltime) {
		this.foralltime = foralltime;
	}
	public Rule() {
		
	}
	
	
	public Rule(String rule_index, String appid, String rule_title, String rule_desc, String sent, String read,
			String once, String foralltime) {
		super();
		this.rule_index = rule_index;
		this.appid = appid;
		this.rule_title = rule_title;
		this.rule_desc = rule_desc;
		this.sent = sent;
		this.read = read;
		this.once = once;
		this.foralltime = foralltime;
	}
	@Override
	public String toString() {
		return "Rule [rule_index=" + rule_index + ", appid=" + appid + ", rule_title=" + rule_title + ", rule_desc="
				+ rule_desc + ", sent=" + sent + ", read=" + read + ", once=" + once + ", foralltime=" + foralltime
				+ "]";
	}
	public String getRule_index() {
		return rule_index;
	}
	public void setRule_index(String rule_index) {
		this.rule_index = rule_index;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getRule_title() {
		return rule_title;
	}
	public void setRule_title(String rule_title) {
		this.rule_title = rule_title;
	}
	public String getRule_desc() {
		return rule_desc;
	}
	public void setRule_desc(String rule_desc) {
		this.rule_desc = rule_desc;
	}
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getOnce() {
		return once;
	}
	public void setOnce(String once) {
		this.once = once;
	}
	

}
