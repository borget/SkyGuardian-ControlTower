package mx.skyguardian.controltower.http.remoting;

public abstract class AbstractSession {
	private String eid = "";
	private Long tm = 0L;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Long getTm() {
		return tm;
	}

	public void setTm(Long tm) {
		this.tm = tm;
	}
}
