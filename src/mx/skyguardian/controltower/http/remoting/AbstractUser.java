package mx.skyguardian.controltower.http.remoting;

public abstract class AbstractUser {
	private String eid = "";
	private Long serverTime = 0L;
	private String userId = "";

	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
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