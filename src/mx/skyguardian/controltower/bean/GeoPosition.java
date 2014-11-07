package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="geoPosition")
public class GeoPosition extends AbstractWialonEntity {
	
	private String positionDesc = "";
	private Long timeUTC = 0L;
	private Double latitud = 0D;
	private Double longitud = 0D;
	private Double altitud = 0D;
	private Integer speed = 0;
	private Integer course = 0;
	private Integer sateliteCount = 0;
	private String dateTime = "";

	public GeoPosition(Long timeUTC, Double latitud, Double longitud,
			Double altitud, Integer speed, Integer course,
			Integer sateliteCount) {
		super();
		this.timeUTC = timeUTC;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
		this.speed = speed;
		this.course = course;
		this.sateliteCount = sateliteCount;
	}


	public GeoPosition() {
	}

	public String getPositionDesc() {
		return positionDesc;
	}


	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}
	
	public Long getTimeUTC() {
		return timeUTC;
	}


	public void setTimeUTC(Long timeUTC) {
		this.timeUTC = timeUTC;
	}


	public Double getLatitud() {
		return latitud;
	}


	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}


	public Double getLongitud() {
		return longitud;
	}


	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}


	public Double getAltitud() {
		return altitud;
	}


	public void setAltitud(Double altitud) {
		this.altitud = altitud;
	}


	public Integer getSpeed() {
		return speed;
	}


	public void setSpeed(Integer speed) {
		this.speed = speed;
	}


	public Integer getCourse() {
		return course;
	}


	public void setCourse(Integer course) {
		this.course = course;
	}


	public Integer getSateliteCount() {
		return sateliteCount;
	}


	public void setSateliteCount(Integer sateliteCount) {
		this.sateliteCount = sateliteCount;
	}
	
	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
