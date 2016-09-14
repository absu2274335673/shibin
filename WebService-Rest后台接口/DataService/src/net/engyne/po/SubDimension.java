package net.engyne.po;

public class SubDimension {

	private Double start;
	private Double end;
	private int count;
	public Double getStart() {
		return start;
	}
	public void setStart(Double start) {
		this.start = start;
	}
	public Double getEnd() {
		return end;
	}
	public void setEnd(Double end) {
		this.end = end;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "SubDimension [start=" + start + ", end=" + end + ", count="
				+ count + "]";
	}
	
}
