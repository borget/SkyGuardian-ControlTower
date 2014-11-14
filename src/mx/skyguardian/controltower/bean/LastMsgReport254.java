package mx.skyguardian.controltower.bean;

public class LastMsgReport254 extends LastMsgReportBase {
	
	private String vehicle_id = null;
	private String service_dist = null;
	private String eng_hours = null;
	private String eng_starter = null;
	private String abs_active = null;
	
	public LastMsgReport254() {
		
	}
	
	public LastMsgReport254(String vehicle_id, String service_dist,
			String eng_hours, String eng_starter, String abs_active) {
		super();
		this.vehicle_id = vehicle_id;
		this.service_dist = service_dist;
		this.eng_hours = eng_hours;
		this.eng_starter = eng_starter;
		this.abs_active = abs_active;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getService_dist() {
		return service_dist;
	}
	public void setService_dist(String service_dist) {
		this.service_dist = service_dist;
	}
	public String getEng_hours() {
		return eng_hours;
	}
	public void setEng_hours(String eng_hours) {
		this.eng_hours = eng_hours;
	}
	public String getEng_starter() {
		return eng_starter;
	}
	public void setEng_starter(String eng_starter) {
		this.eng_starter = eng_starter;
	}
	public String getAbs_active() {
		return abs_active;
	}
	public void setAbs_active(String abs_active) {
		this.abs_active = abs_active;
	}
	
	
}
