package net.engyne.po;

public class Area {
	private String province;
	private int count;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public int getCount() {
		return count;
	}
	@Override
	public String toString() {
		return "Area [province=" + province + ", count=" + count + "]";
	}
	public void setCount(int count) {
		this.count = count;
	}
}
