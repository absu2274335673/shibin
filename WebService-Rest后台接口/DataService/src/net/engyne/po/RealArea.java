package net.engyne.po;

import java.util.ArrayList;
import java.util.List;

public class RealArea {

	private List<Area>  list=new ArrayList<Area>();
	private int total;
	private String result;
	public List<Area> getList() {
		return list;
	}
	public void setList(List<Area> areas) {
		this.list = areas;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int count) {
		this.total= count;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "RealArea [list=" + list + ", total=" + total + ", result="
				+ result + "]";
	}
	
}
