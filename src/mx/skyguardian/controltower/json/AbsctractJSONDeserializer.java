package mx.skyguardian.controltower.json;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.http.remoting.AbstractSession;

import org.json.JSONObject;

public abstract class AbsctractJSONDeserializer {
	public abstract AbstractWialonEntity getGEOPosition(JSONObject jsonObj, AbstractSession user);
	public abstract AbstractWialonEntity getLastMsgReport(JSONObject jsonObj);
}
