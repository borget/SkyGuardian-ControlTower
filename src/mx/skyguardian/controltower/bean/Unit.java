package mx.skyguardian.controltower.bean;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement(name="unit", namespace="http://mx.skyguardian.controltower")
public class Unit extends AbstractWialonEntity {
	private String unitName = StringUtils.EMPTY;
	private Long unitId = -1L;
	private AbstractWialonEntity geoPosition = new GeoPosition();
	private AbstractWialonEntity lastMsgReport = new LastMsgReport();

	public Unit() {
		
	}

	public Unit(String unitName, Long unitId, AbstractWialonEntity geoPosition) {
		super();
		this.unitName = unitName;
		this.unitId = unitId;
		this.geoPosition = geoPosition;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	
	@XmlElement
	public AbstractWialonEntity getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(AbstractWialonEntity geoPosition) {
		this.geoPosition = geoPosition;
	}
	
	@XmlElement
	public AbstractWialonEntity getLastMsgReport() {
		return lastMsgReport;
	}

	public void setLastMsgReport(AbstractWialonEntity lastMsgReport) {
		this.lastMsgReport = lastMsgReport;
	}

}
