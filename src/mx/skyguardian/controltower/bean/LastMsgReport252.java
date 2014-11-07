package mx.skyguardian.controltower.bean;

public class LastMsgReport252 extends LastMsgReport {
	private Integer j1939Odo = -1;
	private Integer totalFuel = -1;
	private Integer brakeTimes = -1;
	private Integer clutchTimes = -1;
	private Integer cruiseTime = -1;
	private Integer ptoTime = -1;
	
	public LastMsgReport252 () {
		
	}
	
	public Integer getJ1939Odo() {
		return j1939Odo;
	}
	public void setJ1939Odo(Integer j1939Odo) {
		this.j1939Odo = j1939Odo;
	}
	public Integer getTotalFuel() {
		return totalFuel;
	}
	public void setTotalFuel(Integer totalFuel) {
		this.totalFuel = totalFuel;
	}
	public Integer getBrakeTimes() {
		return brakeTimes;
	}
	public void setBrakeTimes(Integer brakeTimes) {
		this.brakeTimes = brakeTimes;
	}
	public Integer getClutchTimes() {
		return clutchTimes;
	}
	public void setClutchTimes(Integer clutchTimes) {
		this.clutchTimes = clutchTimes;
	}
	public Integer getCruiseTime() {
		return cruiseTime;
	}
	public void setCruiseTime(Integer cruiseTime) {
		this.cruiseTime = cruiseTime;
	}
	public Integer getPtoTime() {
		return ptoTime;
	}
	public void setPtoTime(Integer ptoTime) {
		this.ptoTime = ptoTime;
	}

}
