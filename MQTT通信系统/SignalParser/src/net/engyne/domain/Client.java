package net.engyne.domain;

public class Client
{
	private String clientid;
	private String nickname;
	public Client(String clientid, String nickname) {
		super();
		this.clientid = clientid;
		this.nickname = nickname;
	}
	public Client() {
		
	}
	@Override
	public String toString() {
		return "Client [clientid=" + clientid + ", nickname=" + nickname + "]";
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
