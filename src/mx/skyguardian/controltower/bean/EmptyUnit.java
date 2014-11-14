package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="unit", namespace="http://mx.skyguardian.controltower")
public class EmptyUnit extends AbstractWialonEntity {
	public EmptyUnit() {
	}
}