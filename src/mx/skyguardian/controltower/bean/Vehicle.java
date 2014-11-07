package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement(name="vehicle", namespace="http://mx.skyguardian.controltower")

public class Vehicle extends AbstractWialonEntity {
	private Long vehicleId = -1L;
	private String vehiclePlate = StringUtils.EMPTY;
	
	public Vehicle() {
		
	}
	
	public Vehicle(Long vehicleId, String vehiclePlate) {
		super();
		this.vehicleId = vehicleId;
		this.vehiclePlate = vehiclePlate;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	
	
	
}
