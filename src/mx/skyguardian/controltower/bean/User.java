package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement(name="user")
public class User {
	private String id = StringUtils.EMPTY;
	private String token = StringUtils.EMPTY;
	private String user = StringUtils.EMPTY;
	private String password = StringUtils.EMPTY;

	public User() {
		
	}
	
	public User(String id, String token, String user, String password) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

