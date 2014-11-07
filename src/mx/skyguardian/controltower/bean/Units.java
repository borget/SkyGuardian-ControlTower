package mx.skyguardian.controltower.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="units", namespace="http://mx.skyguardian.controltower")
public class Units extends AbstractWialonEntity {
	
	private List <Unit> unit ;
	
	public Units() {
		this.unit = new ArrayList<Unit>();
	}
	
	@XmlElement
	public List<Unit> getUnit() {
		return unit;
	}

	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}
	
	
}
