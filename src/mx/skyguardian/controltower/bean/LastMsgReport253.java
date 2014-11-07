package mx.skyguardian.controltower.bean;

public class LastMsgReport253 extends LastMsgReport {
	private Integer driversState = -1;
	private Integer driver1Card = -1;
	private Integer driver2Card = -1;
	private Integer tachographStatus = -1;
	private Integer tachographSpeed = -1;
	private Integer tachoExtInfo = -1;
	
	private Integer in2 = -1;
	private Integer gq = -1;
	private Integer gs = -1;
	
	public LastMsgReport253() {
		
	}
	
	public LastMsgReport253(Integer driversState, Integer driver1Card,
			Integer driver2Card, Integer tachographStatus,
			Integer tachographSpeed, Integer tachoExtInfo, Integer in2,
			Integer gq, Integer gs) {
		super();
		this.driversState = driversState;
		this.driver1Card = driver1Card;
		this.driver2Card = driver2Card;
		this.tachographStatus = tachographStatus;
		this.tachographSpeed = tachographSpeed;
		this.tachoExtInfo = tachoExtInfo;
		this.in2 = in2;
		this.gq = gq;
		this.gs = gs;
	}
	public Integer getDriversState() {
		return driversState;
	}
	public void setDriversState(Integer driversState) {
		this.driversState = driversState;
	}
	public Integer getDriver1Card() {
		return driver1Card;
	}
	public void setDriver1Card(Integer driver1Card) {
		this.driver1Card = driver1Card;
	}
	public Integer getDriver2Card() {
		return driver2Card;
	}
	public void setDriver2Card(Integer driver2Card) {
		this.driver2Card = driver2Card;
	}
	public Integer getTachographStatus() {
		return tachographStatus;
	}
	public void setTachographStatus(Integer tachographStatus) {
		this.tachographStatus = tachographStatus;
	}
	public Integer getTachographSpeed() {
		return tachographSpeed;
	}
	public void setTachographSpeed(Integer tachographSpeed) {
		this.tachographSpeed = tachographSpeed;
	}
	public Integer getTachoExtInfo() {
		return tachoExtInfo;
	}
	public void setTachoExtInfo(Integer tachoExtInfo) {
		this.tachoExtInfo = tachoExtInfo;
	}
	public Integer getIn2() {
		return in2;
	}
	public void setIn2(Integer in2) {
		this.in2 = in2;
	}
	public Integer getGq() {
		return gq;
	}
	public void setGq(Integer gq) {
		this.gq = gq;
	}
	public Integer getGs() {
		return gs;
	}
	public void setGs(Integer gs) {
		this.gs = gs;
	}
}
