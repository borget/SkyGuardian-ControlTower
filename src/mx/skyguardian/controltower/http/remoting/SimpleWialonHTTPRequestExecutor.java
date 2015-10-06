package mx.skyguardian.controltower.http.remoting;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.User;
import mx.skyguardian.controltower.exception.WialonAccessDeniedException;
import mx.skyguardian.controltower.exception.WialonInternalServerError;
import mx.skyguardian.controltower.security.JasyptEncryptor;
import mx.skyguardian.controltower.util.AppUtils;

import org.apache.log4j.Logger;
import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.json.JSONObject;

public class SimpleWialonHTTPRequestExecutor implements IWialonHTTPRequestExecutor {
	
	@Resource(name = "appProperties")
	private Properties appProperties;

	private static Logger log = Logger.getLogger(SimpleWialonHTTPRequestExecutor.class);
	
	public JSONObject getHTTPRequest(String urlString) throws WialonInternalServerError, IOException {

		InputStream inputStream = null;
		JSONObject jsonObject = new JSONObject();
		try {

			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			inputStream = new BufferedInputStream(connection.getInputStream());

			ByteArrayOutputStream dataCache = new ByteArrayOutputStream();

			byte[] buff = new byte[1024];
			int len;
			while ((len = inputStream.read(buff)) >= 0) {
				dataCache.write(buff, 0, len);
			}

			dataCache.close();

			String jsonString = new String(dataCache.toByteArray()).trim();
			
			if (jsonString !=null) {
				if (jsonString.startsWith("{")) {
					jsonObject = new JSONObject(jsonString);
					boolean error = !jsonObject.isNull("error");
					if (error) {
						log.error(jsonObject.get("error:"+jsonObject.toString()));
						throw new WialonInternalServerError();
					} 
				} else if (jsonString.startsWith("[")) {
					Map<String, String > content = new HashMap<String, String>();
					content.put("jsonArray", jsonString);
					jsonObject = new JSONObject(content);
				}
			}		
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return jsonObject;
	}
	
	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}
	
	@Deprecated
	public AbstractSession doLogin(String userName, String password) {
		AbstractSession wialonSession = null;
		Map<String, String> properties = new HashMap<String, String>();
		String decryptedPassword = JasyptEncryptor.decryptPBEText(password);
		properties.put("user", userName);
		properties.put("password", decryptedPassword);
		String loginUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		try {
			ObjectMapper mapper =  JsonFactory.create();
			wialonSession = mapper.readValue(HTTP.getJSON(loginUrl, null), WialonSession.class);
			((WialonSession)wialonSession).getUser().setPassword(decryptedPassword);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new WialonInternalServerError();
		}
		
		return wialonSession;
	}
	

	@Override
	public AbstractSession oAuthLogin(String userName, String password, User user) {
		String decryptedPassword = JasyptEncryptor.decryptPBEText(password);
		if (!decryptedPassword.equals(user.getPassword())) {
			throw new WialonAccessDeniedException();
		}
		
		AbstractSession wialonSession = null;
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("token", user.getToken());
		String loginUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.oauth.login.url"), properties);
		try {
			ObjectMapper mapper =  JsonFactory.create();
			wialonSession = mapper.readValue(HTTP.getJSON(loginUrl, null), WialonSession.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new WialonInternalServerError();
		}
		
		return wialonSession;
	}
	
	public String getAddressByCoordinates(String longitud, String latitud, String userId) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("longitud", longitud);
		properties.put("latitud", latitud);
		properties.put("userId", userId);
		String geoPosUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.address.by.coordinates"), properties);
		try {
			String geoPosDesc = HTTP.getJSON(geoPosUrl, null);
			if(geoPosDesc != null && 
				!geoPosDesc.isEmpty() && 
				geoPosDesc.startsWith("[\"") &&
				geoPosDesc.endsWith("\"]")){
				return geoPosDesc.substring(2, geoPosDesc.length()-2);
			}
		} catch (Exception e) {
			log.error("Error getting Address by coordinates:"+e.getMessage());
		}
		return new String("Address not available.");
	}

}
