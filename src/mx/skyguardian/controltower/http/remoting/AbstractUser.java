package mx.skyguardian.controltower.http.remoting;

public abstract class AbstractUser {
	private String eid = "";
	private Integer serverTime = 0;
	private String userId = "";

	public Integer getServerTime() {
		return serverTime;
	}

	public void setServerTime(Integer serverTime) {
		this.serverTime = serverTime;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
