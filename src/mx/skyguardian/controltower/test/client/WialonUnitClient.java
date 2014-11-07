package mx.skyguardian.controltower.test.client;

import java.io.IOException;
import java.util.Properties;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.GeoPosition;
import mx.skyguardian.controltower.http.remoting.AbstractUser;
import mx.skyguardian.controltower.http.remoting.IControlTowerManager;
import mx.skyguardian.controltower.http.remoting.IWialonHTTPRequestExecutor;
import mx.skyguardian.controltower.http.remoting.SimpleWialonHTTPRequestExecutor;
import mx.skyguardian.controltower.http.remoting.SkyGuardianControlTowerManager;
import mx.skyguardian.controltower.http.remoting.WialonSession;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class WialonUnitClient {

	private Properties properties;

	public Properties getProperties() {
		return this.properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public static void main(String[] args) throws IOException{
		RestTemplate restTemplate = getTemplate();
		
		//listXML(restTemplate);
		
		//postEmployee(restTemplate);
		
		//updateEmployee(restTemplate);
		
		//listAtom(restTemplate);
		
		//removeEmployee(restTemplate);
		/*IWialonHTTPRequestExecutor httpExec = getHTTPExecFromContext();
		GeoPosition geoPos = new GeoPosition();
		geoPos.setLongitud(-99.154648);
		geoPos.setLatitud(19.406721);*/
		
		//JSONObject geoPosDescObj = new JSONObject(geoPosDesc);
		//Object obj = JSONObject.wrap(geoPosDesc);
		IControlTowerManager CTManager = getControlTowerManagerFromContext();
		AbstractWialonEntity units = CTManager.getUnits("tescobedo", "zxmlOLx22OKp4zEhpHZANA==");
		
		//CTManager.getUnit("tescobedo","060f2db2ea8c34bcc73e5ce2f2b47065e81045c2e3a775a5d791103f4c4d27f2d8d5bb62a3e56523", "879279", "1025");
		
		
		//AbstractUser user = CTManager.findUserByName("tescobedo");
		
		//String geoPosDesc = httpExec.getGeoPositionDescription(geoPos, "879279", user);
		//System.out.println(geoPosDesc);
		/*
		ajaxExec.doLogin(user);
		ajaxExec.getUnitReport(user, "879279", "1025"); */
		
		
		//System.err.println(user.getEid());
		
		//EmployeeClient client = new EmployeeClient();
		//Properties propsBean = client.getProperties();
		
		//listJson(restTemplate);
		//jsonById(restTemplate);
		
		//AbstractUser user = getUserFromContext();
		
		
	}

	
	
	
	private static RestTemplate getTemplate() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
			"WebContent/WEB-INF/SkyGuardianControlTower-servlet.xml", "WebContent/WEB-INF/SkyGuardianControlTower-context.xml");
		RestTemplate template = (RestTemplate) ctx.getBean("restTemplate");
		return template;
	}
	
	private static Properties getPropertiesFromContext() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
			"WebContent/WEB-INF/SkyGuardianControlTower-servlet.xml", "WebContent/WEB-INF/SkyGuardianControlTower-context.xml");
		Properties props = (Properties) ctx.getBean("appProperties");
		return props;
	}
	private static IWialonHTTPRequestExecutor getHTTPExecFromContext() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
			"WebContent/WEB-INF/SkyGuardianControlTower-servlet.xml", "WebContent/WEB-INF/SkyGuardianControlTower-context.xml");
		IWialonHTTPRequestExecutor httpExec = (SimpleWialonHTTPRequestExecutor) ctx.getBean("wialonHTTPExecutor");
		return httpExec;
	}
	
	private static AbstractUser getUserFromContext() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"WebContent/WEB-INF/SkyGuardianControlTower-servlet.xml", "WebContent/WEB-INF/SkyGuardianControlTower-context.xml");
			AbstractUser ajaxExec = (WialonSession) ctx.getBean("avantUser");
			return ajaxExec;
	}
	
	private static IControlTowerManager getControlTowerManagerFromContext() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"WebContent/WEB-INF/SkyGuardianControlTower-servlet.xml", "WebContent/WEB-INF/SkyGuardianControlTower-context.xml");
			IControlTowerManager CTManager = (SkyGuardianControlTowerManager) ctx.getBean("controlTowerManager");
			return CTManager;
	}
	
	private static HttpEntity<String> prepareGet(MediaType type) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}
}
