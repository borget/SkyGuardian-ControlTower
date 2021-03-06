package mx.skyguardian.controltower.http.remoting;

public class WialonSession extends AbstractSession {
	private User user = null;

	public WialonSession () {
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
	public class User {
		private String nm;
		private String password;
		private String id;
		
		public String getNm() {
			return nm;
		}
		
		public void setNm(String nm) {
			this.nm = nm;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
	}
}
