package net.engyne.domain;

import java.util.ArrayList;
import java.util.List;

public class ConvMsg 
{
	private String _id;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<Msg> getMsg() {
		return msg;
	}
	public void setMsg(List<Msg> msg) {
		this.msg = msg;
	}
	List<Msg> msg=new ArrayList<Msg>();
}
