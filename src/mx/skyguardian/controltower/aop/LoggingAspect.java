package mx.skyguardian.controltower.aop;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import mx.skyguardian.controltower.http.remoting.AbstractSession;
import mx.skyguardian.controltower.http.remoting.WialonSession;
import mx.skyguardian.controltower.security.JasyptEncryptor;
import mx.skyguardian.controltower.util.AppUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

@Aspect
public class LoggingAspect {

	private AbstractSession wialonSession = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	@Pointcut("execution(* mx.skyguardian.controltower.http.remoting.SkyGuardianControlTowerManager.getUnit(String, String, String)) && args(userName, password, unitId)")
	//MARKER METHOD
	public void unitRequest(String userName, String password, String unitId) {}
	
	@Before("unitRequest(userName, password, unitId)")
	public void interceptUnitRequest(String userName, String password, String unitId) {
		this.doLogin(userName, password);
	}

	public void doLogin(String userName, String password) {
		Map<String, String> properties = new HashMap<String, String>();
		String decryptedPassword = JasyptEncryptor.decryptPBEText(password);
		properties.put("user", userName);
		properties.put("password", decryptedPassword);
		String loginUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		ObjectMapper mapper =  JsonFactory.create();
		this.wialonSession = mapper.readValue(HTTP.getJSON(loginUrl, null), WialonSession.class);
		((WialonSession)this.wialonSession).getUser().setPassword(decryptedPassword);
	}
	
	public AbstractSession getWialonSession() {
		return wialonSession;
	}

	public void setWialonSession(AbstractSession wialonSession) {
		this.wialonSession = wialonSession;
	}
	
	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}
}
