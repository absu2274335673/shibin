package net.engyne.domain;

public class Account
{
	private String username;
	private String account_type;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", account_type=" + account_type + "]";
	}

	
}
