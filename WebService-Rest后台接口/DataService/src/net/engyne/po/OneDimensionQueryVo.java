package net.engyne.po;

public class OneDimensionQueryVo {

	private String table;
	private String xaxis;
	private String ymode;
	private String target;
	private String filter;
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getXaxis() {
		return xaxis;
	}
	public void setXaxis(String xaxis) {
		this.xaxis = xaxis;
	}
	public String getYmode() {
		return ymode;
	}
	public void setYmode(String ymode) {
		this.ymode = ymode;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	@Override
	public String toString() {
		return "OneDimensionQueryVo [table=" + table + ", xaxis=" + xaxis
				+ ", ymode=" + ymode + ", target=" + target + ", filter="
				+ filter + "]";
	}
	
}
