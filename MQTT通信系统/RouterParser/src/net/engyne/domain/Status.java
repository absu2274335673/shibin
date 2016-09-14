package net.engyne.domain;

public class Status
{
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Status(String status) {
		super();
		this.status = status;
	}
	public Status() {
		
	}

	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}
}
