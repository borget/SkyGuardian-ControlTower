package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;

import mx.skyguardian.controltower.bean.User;
import mx.skyguardian.controltower.exception.WialonInternalServerError;

import org.json.JSONObject;

public interface IWialonHTTPRequestExecutor {
	
	JSONObject getHTTPRequest(String urlString) throws WialonInternalServerError, IOException;
	AbstractSession doLogin(String userName, String password);
	AbstractSession oAuthLogin(String userName, String password, User user);
	String getAddressByCoordinates(String longitud, String latitud, String userId);
}
