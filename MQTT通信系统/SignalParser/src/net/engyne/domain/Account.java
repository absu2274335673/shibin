package net.engyne.domain;

public class Account 
{
	private String username;
	private String phonenumber;
	private String devicetoken;
	
	public Account(String username, String phonenumber, String devicetoken) {
		super();
		this.username = username;
		this.phonenumber = phonenumber;
		this.devicetoken = devicetoken;
	}
	public Account() {
		
	}
	public String getDevicetoken() {
		return devicetoken;
	}
	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", phonenumber=" + phonenumber + ", devicetoken=" + devicetoken + "]";
	}
	
	
}
