package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;

import mx.skyguardian.controltower.exception.WialonInternalServerError;

import org.json.JSONObject;

public interface IWialonHTTPRequestExecutor {
	
	public JSONObject getHTTPRequest(String urlString) throws WialonInternalServerError, IOException;

}
