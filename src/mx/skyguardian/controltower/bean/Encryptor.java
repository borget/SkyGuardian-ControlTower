package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement(name="encryptor")
public class Encryptor {
	private String encryptedText = StringUtils.EMPTY;


	public Encryptor() {
		
	}

	public Encryptor(String encryptedText) {
		super();
		this.encryptedText = encryptedText;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}
}
