package net.engyne.domain;

public class Client {
	private String clientid;
	private String usertype;
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return "Client [clientid=" + clientid + ", usertype=" + usertype + "]";
	}
	
}
