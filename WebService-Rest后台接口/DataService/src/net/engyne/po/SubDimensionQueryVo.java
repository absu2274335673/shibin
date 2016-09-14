package net.engyne.po;

public class SubDimensionQueryVo {

	private String table;
	private String xaxis;
	private String ymode;
	private String target;
	private String filter;
	private Double start;
	private Double end;
	private Double step;
	private String groupby;
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
	public Double getStep() {
		return step;
	}
	public void setStep(Double step) {
		this.step = step;
	}
	public String getGroupby() {
		return groupby;
	}
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	@Override
	public String toString() {
		return "SubDimensionQueryVo [table=" + table + ", xaxis=" + xaxis
				+ ", ymode=" + ymode + ", target=" + target + ", filter="
				+ filter + ", start=" + start + ", end=" + end + ", step="
				+ step + ", groupby=" + groupby + "]";
	}
	
	
}
