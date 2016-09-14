package net.engyne.domain;

public class Medium 
{
	private String filter_key;
	private String filter_type;
	private String filter_value;
	private String begin;
	private String end;
	public Medium() {
		
	}

	public Medium(String filter_key, String filter_type, String filter_value,
			String begin, String end) {
		super();
		this.filter_key = filter_key;
		this.filter_type = filter_type;
		this.filter_value = filter_value;
		this.begin = begin;
		this.end = end;
	}
	@Override
	public String toString() {
		return "Medium [filter_key=" + filter_key + ", filter_type="
				+ filter_type + ", filter_value=" + filter_value + ", begin="
				+ begin + ", end=" + end + "]";
	}

	public String getFilter_key() {
		return filter_key;
	}

	public void setFilter_key(String filter_key) {
		this.filter_key = filter_key;
	}

	public String getFilter_type() {
		return filter_type;
	}

	public void setFilter_type(String filter_type) {
		this.filter_type = filter_type;
	}

	public String getFilter_value() {
		return filter_value;
	}

	public void setFilter_value(String filter_value) {
		this.filter_value = filter_value;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	
	
}
