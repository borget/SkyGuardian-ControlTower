package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;

import mx.skyguardian.controltower.exception.WialonInternalServerError;

import org.json.JSONObject;

public interface IWialonHTTPRequestExecutor {
	
	JSONObject getHTTPRequest(String urlString) throws WialonInternalServerError, IOException;
	AbstractSession doLogin(String userName, String password);
	String getAddressByCoordinates(String longitud, String latitud, String userId);
}
