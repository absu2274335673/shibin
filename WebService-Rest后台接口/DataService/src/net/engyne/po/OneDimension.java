package net.engyne.po;

public class OneDimension {

	private String grade;
	private int count;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getCount() {
		return count;
	}
	@Override
	public String toString() {
		return "OneDimension [grade=" + grade + ", count=" + count + "]";
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
