package mx.skyguardian.controltower.json;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.http.remoting.AbstractUser;

import org.json.JSONObject;

public abstract class AbsctractJSONDeserializer {
	public abstract AbstractWialonEntity getGEOPosition(JSONObject jsonObj, AbstractUser user);
	public abstract AbstractWialonEntity getLastMsgReport(JSONObject jsonObj);
}
