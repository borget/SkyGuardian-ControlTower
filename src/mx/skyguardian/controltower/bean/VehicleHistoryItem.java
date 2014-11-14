package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="history", namespace="http://mx.skyguardian.controltower")
public class VehicleHistoryItem extends AbstractWialonEntity{
	private AbstractWialonEntity geoPosition = new GeoPosition();
	private AbstractWialonEntity lastMsgReport = new LastMsgReportBase();
	
	public VehicleHistoryItem() {
		
	}
	
	public VehicleHistoryItem(AbstractWialonEntity geoPosition,
			AbstractWialonEntity lastMsgReport) {
		super();
		this.geoPosition = geoPosition;
		this.lastMsgReport = lastMsgReport;
	}

	public AbstractWialonEntity getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(AbstractWialonEntity geoPosition) {
		this.geoPosition = geoPosition;
	}

	public AbstractWialonEntity getLastMsgReport() {
		return lastMsgReport;
	}

	public void setLastMsgReport(AbstractWialonEntity lastMsgReport) {
		this.lastMsgReport = lastMsgReport;
	}
	
	
}
