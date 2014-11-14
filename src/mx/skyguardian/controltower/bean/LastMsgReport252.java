package mx.skyguardian.controltower.bean;

public class LastMsgReport252 extends LastMsgReportBase {
	private String j1939_odo = null;
	private String total_fuel = null;
	
	public LastMsgReport252() {
		
	}
	
	public LastMsgReport252(String j1939_odo, String total_fuel) {
		super();
		this.j1939_odo = j1939_odo;
		this.total_fuel = total_fuel;
	}
	public String getJ1939_odo() {
		return j1939_odo;
	}
	public void setJ1939_odo(String j1939_odo) {
		this.j1939_odo = j1939_odo;
	}
	public String getTotal_fuel() {
		return total_fuel;
	}
	public void setTotal_fuel(String total_fuel) {
		this.total_fuel = total_fuel;
	}
	
	
}

