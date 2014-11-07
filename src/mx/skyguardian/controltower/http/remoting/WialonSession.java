package mx.skyguardian.controltower.http.remoting;

public class WialonSession extends AbstractUser {
	private String userName = null;
	private String password = null;

	public WialonSession () {
		
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
