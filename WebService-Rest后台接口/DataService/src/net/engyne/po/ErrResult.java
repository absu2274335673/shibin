package net.engyne.po;

public class ErrResult {
	private String result;
	private String errMsg;
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public ErrResult(String result, String errMsg) {
	
		this.result = result;
		this.errMsg = errMsg;
	}
	public ErrResult() {
		
		
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ErrResult [result=" + result + ", errMsg=" + errMsg + "]";
	}
}
