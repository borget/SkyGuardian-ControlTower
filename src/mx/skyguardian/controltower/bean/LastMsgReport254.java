package mx.skyguardian.controltower.bean;

public class LastMsgReport254 extends LastMsgReport{
	
	private Integer vehicleId = -1;
	private Integer serviceDist = -1;
	private Integer engHours = -1;
	private Integer engStarter = -1;
	private Integer absActive = -1;
	
	public LastMsgReport254() {
		
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getServiceDist() {
		return serviceDist;
	}

	public void setServiceDist(Integer serviceDist) {
		this.serviceDist = serviceDist;
	}

	public Integer getEngHours() {
		return engHours;
	}

	public void setEngHours(Integer engHours) {
		this.engHours = engHours;
	}

	public Integer getEngStarter() {
		return engStarter;
	}

	public void setEngStarter(Integer engStarter) {
		this.engStarter = engStarter;
	}

	public Integer getAbsActive() {
		return absActive;
	}

	public void setAbsActive(Integer absActive) {
		this.absActive = absActive;
	}
	
}
