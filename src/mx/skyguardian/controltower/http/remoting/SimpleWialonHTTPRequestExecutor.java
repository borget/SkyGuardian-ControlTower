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

import mx.skyguardian.controltower.exception.WialonInternalServerError;

import org.apache.log4j.Logger;
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
			connection.setRequestProperty("Content-Type",
					"application/json; charset=utf-8");

			//connection.setDoOutput(true);

			inputStream = new BufferedInputStream(connection.getInputStream());

			ByteArrayOutputStream dataCache = new ByteArrayOutputStream();

			// Fully read data
			byte[] buff = new byte[1024];
			int len;
			while ((len = inputStream.read(buff)) >= 0) {
				dataCache.write(buff, 0, len);
			}

			// Close streams
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
}
