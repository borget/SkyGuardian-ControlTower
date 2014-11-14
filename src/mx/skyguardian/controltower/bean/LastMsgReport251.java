package mx.skyguardian.controltower.bean;

public class LastMsgReport251 extends LastMsgReportBase {
	private String engine_rpm = null;
	private String fuel_cons = null;
	private String j1939_fuel_level = null;
	
	public LastMsgReport251() {
		
	}
	
	public LastMsgReport251(String engine_rpm, String fuel_cons,
			String j1939_fuel_level) {
		super();
		this.engine_rpm = engine_rpm;
		this.fuel_cons = fuel_cons;
		this.j1939_fuel_level = j1939_fuel_level;
	}
	public String getEngine_rpm() {
		return engine_rpm;
	}
	public void setEngine_rpm(String engine_rpm) {
		this.engine_rpm = engine_rpm;
	}
	public String getFuel_cons() {
		return fuel_cons;
	}
	public void setFuel_cons(String fuel_cons) {
		this.fuel_cons = fuel_cons;
	}
	public String getJ1939_fuel_level() {
		return j1939_fuel_level;
	}
	public void setJ1939_fuel_level(String j1939_fuel_level) {
		this.j1939_fuel_level = j1939_fuel_level;
	}
	
	
}
