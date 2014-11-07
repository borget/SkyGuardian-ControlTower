package mx.skyguardian.controltower.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vehicle", namespace="http://mx.skyguardian.controltower")
public class VehicleHistory extends AbstractWialonEntity {
	
	private Long vehicleId = 0L;
	private List <VehicleHistoryItem> history;
	
	public VehicleHistory() {
		
	}
	
	public VehicleHistory(Long vehicleId, List<VehicleHistoryItem> history) {
		super();
		this.vehicleId = vehicleId;
		this.history = history;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	@XmlElement
	public List<VehicleHistoryItem> getHistory() {
		return history;
	}

	public void setHistory(List<VehicleHistoryItem> history) {
		this.history = history;
	}
	
	
}
