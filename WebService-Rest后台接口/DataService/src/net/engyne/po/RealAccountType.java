package net.engyne.po;

import java.util.List;

public class RealAccountType {

	private String result;
	private List<AccountType> data;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<AccountType> getData() {
		return data;
	}
	public void setData(List<AccountType> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "RealAccountType [result=" + result + ", data=" + data + "]";
	}
	
}
