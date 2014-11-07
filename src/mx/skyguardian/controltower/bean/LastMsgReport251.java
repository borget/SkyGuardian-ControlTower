package mx.skyguardian.controltower.bean;

public class LastMsgReport251 extends LastMsgReport{
	private Integer engineRpm = -1;
	private Integer j1939Speed = -1;
	private Integer fuelCons = -1;
	private Integer j1939FuelLevel = -1;
	private Integer axle1 = -1;
	private Integer axle2 = -1;
	private Integer axle3 = -1;
	private Integer axle4 = -1;
	private Integer engBoostPressure = -1;
	private Integer coolantTemp = -1;
	private Integer accelPos = -1;
	private Integer brakePos = -1;
	private Integer ptAirPressure = -1;
	private Integer brakePressure1 = -1;
	private Integer brakePressure2 = -1;
	
	public LastMsgReport251(Integer engineRpm, Integer j1939Speed,
			Integer fuelCons, Integer j1939FuelLevel, Integer axle1,
			Integer axle2, Integer axle3, Integer axle4,
			Integer engBoostPressure, Integer coolantTemp, Integer accelPos,
			Integer brakePos, Integer ptAirPressure, Integer brakePressure1,
			Integer brakePressure2) {
		super();
		this.engineRpm = engineRpm;
		this.j1939Speed = j1939Speed;
		this.fuelCons = fuelCons;
		this.j1939FuelLevel = j1939FuelLevel;
		this.axle1 = axle1;
		this.axle2 = axle2;
		this.axle3 = axle3;
		this.axle4 = axle4;
		this.engBoostPressure = engBoostPressure;
		this.coolantTemp = coolantTemp;
		this.accelPos = accelPos;
		this.brakePos = brakePos;
		this.ptAirPressure = ptAirPressure;
		this.brakePressure1 = brakePressure1;
		this.brakePressure2 = brakePressure2;
	}
	
	public LastMsgReport251 () {
		
	}
	
	public Integer getEngineRpm() {
		return engineRpm;
	}
	public void setEngineRpm(Integer engineRpm) {
		this.engineRpm = engineRpm;
	}
	public Integer getJ1939Speed() {
		return j1939Speed;
	}
	public void setJ1939Speed(Integer j1939Speed) {
		this.j1939Speed = j1939Speed;
	}
	public Integer getFuelCons() {
		return fuelCons;
	}
	public void setFuelCons(Integer fuelCons) {
		this.fuelCons = fuelCons;
	}
	public Integer getJ1939FuelLevel() {
		return j1939FuelLevel;
	}
	public void setJ1939FuelLevel(Integer j1939FuelLevel) {
		this.j1939FuelLevel = j1939FuelLevel;
	}
	public Integer getAxle1() {
		return axle1;
	}
	public void setAxle1(Integer axle1) {
		this.axle1 = axle1;
	}
	public Integer getAxle2() {
		return axle2;
	}
	public void setAxle2(Integer axle2) {
		this.axle2 = axle2;
	}
	public Integer getAxle3() {
		return axle3;
	}
	public void setAxle3(Integer axle3) {
		this.axle3 = axle3;
	}
	public Integer getAxle4() {
		return axle4;
	}
	public void setAxle4(Integer axle4) {
		this.axle4 = axle4;
	}
	public Integer getEngBoostPressure() {
		return engBoostPressure;
	}
	public void setEngBoostPressure(Integer engBoostPressure) {
		this.engBoostPressure = engBoostPressure;
	}
	public Integer getCoolantTemp() {
		return coolantTemp;
	}
	public void setCoolantTemp(Integer coolantTemp) {
		this.coolantTemp = coolantTemp;
	}
	public Integer getAccelPos() {
		return accelPos;
	}
	public void setAccelPos(Integer accelPos) {
		this.accelPos = accelPos;
	}
	public Integer getBrakePos() {
		return brakePos;
	}
	public void setBrakePos(Integer brakePos) {
		this.brakePos = brakePos;
	}
	public Integer getPtAirPressure() {
		return ptAirPressure;
	}
	public void setPtAirPressure(Integer ptAirPressure) {
		this.ptAirPressure = ptAirPressure;
	}
	public Integer getBrakePressure1() {
		return brakePressure1;
	}
	public void setBrakePressure1(Integer brakePressure1) {
		this.brakePressure1 = brakePressure1;
	}
	public Integer getBrakePressure2() {
		return brakePressure2;
	}
	public void setBrakePressure2(Integer brakePressure2) {
		this.brakePressure2 = brakePressure2;
	}
}
