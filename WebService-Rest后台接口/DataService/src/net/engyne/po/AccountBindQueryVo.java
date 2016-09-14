package net.engyne.po;

public class AccountBindQueryVo {

	private String _index;
	private String username;
	public String get_index() {
		return _index;
	}
	public void set_index(String _index) {
		this._index = _index;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "AccountBindQueryVo [_index=" + _index + ", username="
				+ username + "]";
	}
	
}
