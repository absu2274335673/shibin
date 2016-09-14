package net.engyne.domain;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User 
{
	private int userid;
	private String  username;
	private String nickname;
	private int appindex;
	private String usersex;
	private String userage;
	private String usertype;
	private int registtime;
	private int lastseetime;
	private int lastpageindex;
	private int provinceid;
	private int channelid;
	private int terminalid;
	private String email;
	private String phonenumber;
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", nickname=" + nickname + ", appindex=" + appindex
				+ ", usersex=" + usersex + ", userage=" + userage
				+ ", usertype=" + usertype + ", registtime=" + registtime
				+ ", lastseetime=" + lastseetime + ", lastpageindex="
				+ lastpageindex + ", provinceid=" + provinceid + ", channelid="
				+ channelid + ", terminalid=" + terminalid + ", email=" + email
				+ ", phonenumber=" + phonenumber + "]";
	}
	public User(int userid, String username, String nickname, int appindex,
			String usersex, String userage, String usertype, int registtime,
			int lastseetime, int lastpageindex, int provinceid, int channelid,
			int terminalid, String email, String phonenumber) {
		super();
		this.userid = userid;
		this.username = username;
		this.nickname = nickname;
		this.appindex = appindex;
		this.usersex = usersex;
		this.userage = userage;
		this.usertype = usertype;
		this.registtime = registtime;
		this.lastseetime = lastseetime;
		this.lastpageindex = lastpageindex;
		this.provinceid = provinceid;
		this.channelid = channelid;
		this.terminalid = terminalid;
		this.email = email;
		this.phonenumber = phonenumber;
	}
	public User() {
		
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAppindex() {
		return appindex;
	}
	public void setAppindex(int appindex) {
		this.appindex = appindex;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getUserage() {
		return userage;
	}
	public void setUserage(String userage) {
		this.userage = userage;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public int getRegisttime() {
		return registtime;
	}
	public void setRegisttime(int registtime) {
		this.registtime = registtime;
	}
	public int getLastseetime() {
		return lastseetime;
	}
	public void setLastseetime(int lastseetime) {
		this.lastseetime = lastseetime;
	}
	public int getLastpageindex() {
		return lastpageindex;
	}
	public void setLastpageindex(int lastpageindex) {
		this.lastpageindex = lastpageindex;
	}
	public int getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	public int getChannelid() {
		return channelid;
	}
	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}
	public int getTerminalid() {
		return terminalid;
	}
	public void setTerminalid(int terminalid) {
		this.terminalid = terminalid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
