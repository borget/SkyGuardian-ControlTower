package mx.skyguardian.controltower.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vehicles", namespace = "http://mx.skyguardian.controltower")
public class Vehicles extends AbstractWialonEntity {

	private List<Vehicle> vehicle;

	public Vehicles() {
		this.vehicle = new ArrayList<Vehicle>();
	}

	@XmlElement
	public List<Vehicle> getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

}
