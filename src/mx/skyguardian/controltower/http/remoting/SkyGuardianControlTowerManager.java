package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.EmptyUnit;
import mx.skyguardian.controltower.bean.GeoPosition;
import mx.skyguardian.controltower.bean.Unit;
import mx.skyguardian.controltower.bean.Units;
import mx.skyguardian.controltower.bean.Vehicle;
import mx.skyguardian.controltower.bean.VehicleHistory;
import mx.skyguardian.controltower.bean.VehicleHistoryItem;
import mx.skyguardian.controltower.bean.Vehicles;
import mx.skyguardian.controltower.exception.WialonAccessDeniedException;
import mx.skyguardian.controltower.exception.WialonInternalServerError;
import mx.skyguardian.controltower.json.AbsctractJSONDeserializer;
import mx.skyguardian.controltower.security.JasyptEncryptor;
import mx.skyguardian.controltower.util.AppUtils;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkyGuardianControlTowerManager implements IControlTowerManager {
	
	private static Logger log = Logger.getLogger(SkyGuardianControlTowerManager.class);
	
	private IWialonHTTPRequestExecutor httpReqExecutor = null;
	
	private AbsctractJSONDeserializer jsonDeserializer = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	public AbstractWialonEntity getUnit(String userName, String password, String unitId) throws WialonInternalServerError, IOException {
		log.info("SkyGuardianControlTowerManager.getUnit()="+userName+"::"+password);
		WialonSession wialonSession = new WialonSession();
		wialonSession.setUserName(userName);
		wialonSession.setPassword(JasyptEncryptor.decryptPBEText(password));
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("user", wialonSession.getUserName());
		properties.put("password", wialonSession.getPassword());
		
		String loginUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getUnit()-loginUrl="+loginUrl);
				
		JSONObject loginJSONObj = httpReqExecutor.getHTTPRequest(loginUrl);
		
		this.setWialonSession(loginJSONObj, wialonSession);
		
		properties.clear();
		properties.put("unitId", unitId);
		properties.put("eid", wialonSession.getEid());

		String unitUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.searchbyid.url"),
				properties);

		log.debug("SkyGuardianControlTowerManager.getUnit()-unitUrl=" + unitUrl);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitUrl);
		if (!itemObj.isNull("item")) {
			JSONObject jsonItem = (JSONObject) itemObj.get("item");
			
			JSONObject pObj = getPObject(jsonItem);
			if (pObj != null) {
				AbstractWialonEntity unit = new Unit();
				((Unit) unit)
						.setUnitId(Long.parseLong((jsonItem.isNull("id")) ? "0"
								: jsonItem.get("id").toString()));
				((Unit) unit).setUnitName((jsonItem.isNull("nm")) ? "invalidUnit"
						: jsonItem.get("nm").toString());
				((Unit) unit).setGeoPosition(jsonDeserializer.getGEOPosition(
						jsonItem, wialonSession));
				((Unit) unit).setLastMsgReport((jsonDeserializer
						.getLastMsgReport(pObj)));
				return unit;
			}
		}

		return new EmptyUnit();
	}
	
	public AbstractWialonEntity getUnits(String userName, String password) throws IOException {
		
		WialonSession wialonSession = new WialonSession();
		wialonSession.setUserName(userName);
		wialonSession.setPassword(JasyptEncryptor.decryptPBEText(password));
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("user", wialonSession.getUserName());
		properties.put("password", wialonSession.getPassword());
		
		String loginUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getUnits()-loginUrl="+loginUrl);
		
		JSONObject loginJSONObj = httpReqExecutor.getHTTPRequest(loginUrl);
		
		this.setWialonSession(loginJSONObj, wialonSession);
		
		properties.clear();
		properties.put("sid", wialonSession.getEid());

		String unitsUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.units.url"),
				properties);

		log.debug("SkyGuardianControlTowerManager.getUnits()-unitsUrl="
				+ unitsUrl);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitsUrl);
		//JSONObject itemObj = new JSONObject("{\"searchSpec\":{\"itemsType\":\"avl_unit\",\"propName\":\"sys_name\",\"propValueMask\":\"*\",\"sortType\":\"sys_name\",\"propType\":\"propitemname\"},\"dataFlags\":1025,\"totalItemsCount\":110,\"indexFrom\":0,\"indexTo\":0,\"items\":[{\"nm\":\"60186\",\"cls\":2,\"id\":6527054,\"mu\":0,\"pos\":{\"t\":1415319033,\"y\":18.073986,\"x\":-94.283291,\"z\":0,\"s\":8,\"c\":77,\"sc\":255},\"lmsg\":{\"t\":1415319033,\"f\":7,\"tp\":\"ud\",\"pos\":{\"y\":18.073986,\"x\":-94.283291,\"z\":0,\"s\":8,\"c\":77,\"sc\":255},\"i\":9,\"o\":0,\"p\":{\"gps_tm\":1415319034,\"rtc_tm\":1415319033,\"snd_tm\":1415319055,\"report_id\":251,\"odometer\":71714.9,\"hdop\":0.8,\"adc1\":0,\"temp1\":200,\"temp2\":200,\"dl\":0,\"tw\":0,\"motion\":1,\"ip\":0,\"ps\":0,\"ss\":0,\"ha\":0,\"hb\":0,\"hc\":0,\"jd\":0,\"bl\":0,\"engine\":1,\"pwr_ext\":14,\"rd\":\"251\",\"op\":0,\"in0\":0,\"in1\":0,\"in2\":1,\"od\":\"717149\",\"gsm\":24,\"gsm_status\":9,\"engine_rpm\":1136,\"j1939_speed\":8,\"fuel_cons\":0,\"j1939_fuel_level\":90,\"axle1\":0,\"axle2\":0,\"axle3\":0,\"axle4\":0,\"eng_boost_pressure\":0,\"coolant_temp\":80,\"accel_pos\":19,\"brake_pos\":102,\"pt_air_pressure\":0,\"brake_pressure1\":608,\"brake_pressure2\":600,\"DL\":0,\"TW\":0,\"MT\":1,\"IP\":0,\"PS\":0,\"SS\":0,\"HA\":0,\"HB\":0,\"HC\":0,\"JD\":0,\"BL\":0,\"EG\":1,\"MV\":140,\"RD\":251,\"OP\":0,\"IN0\":0,\"IN1\":0,\"IN2\":1,\"OD\":717149,\"GQ\":24,\"GS\":9}},\"uacl\":281474976710655}]}");	
		if (!itemObj.isNull("searchSpec")) {
			JSONArray jsonArray = (JSONArray) itemObj.getJSONArray("items");
			if (jsonArray.length() > 0) {
				Units units = new Units();
				List<Unit> unitsList = new ArrayList<Unit>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = (JSONObject) jsonArray.get(i);
					JSONObject pObj = getPObject(jsonItem);
					if (pObj != null) {
						AbstractWialonEntity unit = new Unit();
						((Unit) unit).setUnitId(Long.parseLong((jsonItem
								.isNull("id")) ? "0" : jsonItem.get("id")
								.toString()));
						((Unit) unit)
								.setUnitName((jsonItem.isNull("nm")) ? "invalidUnit"
										: jsonItem.get("nm").toString());
						((Unit) unit).setGeoPosition(jsonDeserializer
								.getGEOPosition(jsonItem, wialonSession));
						((Unit) unit).setLastMsgReport((jsonDeserializer
								.getLastMsgReport(pObj)));
						unitsList.add((Unit) unit);
					}
				}
				units.setUnit(unitsList);
				return units;
			}
		}

		return new Units();
	}
	
	private JSONObject getPObject (JSONObject jsonItem) {
		if (jsonItem  != null) {
			JSONObject lmsgObj = jsonItem.optJSONObject("lmsg");
			JSONObject pObj = jsonItem.optJSONObject("p");
			if (lmsgObj != null) {
				JSONObject pInLmsgObj = lmsgObj.optJSONObject("p");
				if(!pInLmsgObj.isNull("report_id")){
					return pInLmsgObj;
				}
			} else if (pObj != null) {
				if (!pObj.isNull("report_id")){
					return pObj;
				}
			}
		}
		return null;
	}
	
	@Override
	public AbstractWialonEntity getVehicles(String userName, String password) throws WialonAccessDeniedException, IOException {
		
		WialonSession wialonSession = new WialonSession();
		wialonSession.setUserName(userName);
		wialonSession.setPassword(JasyptEncryptor.decryptPBEText(password));
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("user", wialonSession.getUserName());
		properties.put("password", wialonSession.getPassword());
		
		String loginUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getVehicles()-loginUrl="+loginUrl);
		
		JSONObject loginJSONObj = httpReqExecutor.getHTTPRequest(loginUrl);
		
		this.setWialonSession(loginJSONObj, wialonSession);

		properties.clear();
		properties.put("sid", wialonSession.getEid());

		String unitsUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.units.url"),
				properties);

		log.debug("SkyGuardianControlTowerManager.getVehicles()-unitsUrl="
				+ unitsUrl);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitsUrl);

		if (!itemObj.isNull("searchSpec")) {
			JSONArray jsonArray = (JSONArray) itemObj.getJSONArray("items");
			if (jsonArray.length() > 0) {
				Vehicles vehicles = new Vehicles();
				List<Vehicle> vehicleList = new ArrayList<Vehicle>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = (JSONObject) jsonArray.get(i);
					AbstractWialonEntity vehicle = new Vehicle();
					((Vehicle) vehicle).setVehicleId(Long.parseLong((jsonItem
							.isNull("id")) ? "0" : jsonItem.get("id")
							.toString()));
					((Vehicle) vehicle)
							.setVehiclePlate((jsonItem.isNull("nm")) ? "unknown unit"
									: jsonItem.get("nm").toString());
					vehicleList.add((Vehicle) vehicle);
				}
				vehicles.setVehicle(vehicleList);
				return vehicles;
			}
		}

		return new Vehicles();
	}
	
	public AbstractWialonEntity getVehiculeHistory(String vehicleId,String interval, String loadCount, String userName, String password) throws WialonAccessDeniedException, IOException {
		WialonSession wialonSession = new WialonSession();
		wialonSession.setUserName(userName);
		wialonSession.setPassword(JasyptEncryptor.decryptPBEText(password));
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("user", wialonSession.getUserName());
		properties.put("password", wialonSession.getPassword());
		
		String loginUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getVehiculeHistory()-loginUrl="+loginUrl);
		
		JSONObject loginJSONObj = httpReqExecutor.getHTTPRequest(loginUrl);
		
		this.setWialonSession(loginJSONObj, wialonSession);

		properties.clear();

		Integer serverTime = wialonSession.getServerTime();
		String timeFrom = AppUtils.getTimeFrom(serverTime, interval);

		properties.put("vehiculeId", vehicleId);
		properties.put("timeFrom", timeFrom);
		properties.put("timeTo", String.valueOf(serverTime));
		properties.put("loadCount", loadCount);
		properties.put("sid", wialonSession.getEid());

		String vehiculeHURL = AppUtils.getURL(appProperties.
								getProperty("mx.skyguardian.controltower.vehicule.history.url"),
										properties);

		log.debug("SkyGuardianControlTowerManager.getVehiculeHistory()-vehiculeHURL=" + vehiculeHURL);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(vehiculeHURL);
		if (!itemObj.isNull("messages")) {
			JSONArray jsonArray = (JSONArray) itemObj.getJSONArray("messages");
			if (jsonArray.length() > 0) {
				VehicleHistory vehicleHistory = new VehicleHistory();
				vehicleHistory.setVehicleId(Long.valueOf(vehicleId));

				List<VehicleHistoryItem> vehicleHistoryItemList = new ArrayList<VehicleHistoryItem>();

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = (JSONObject) jsonArray.get(i);
					
					JSONObject pObj = getPObject(jsonItem);
					if (pObj != null) {
						Long dateTimeSec = Long
								.parseLong((jsonItem.isNull("t")) ? "0" : jsonItem
										.get("t").toString());

						AbstractWialonEntity vehicleHistoryItem = new VehicleHistoryItem();

						((VehicleHistoryItem) vehicleHistoryItem)
								.setGeoPosition(jsonDeserializer.getGEOPosition(
										jsonItem, wialonSession));

						((GeoPosition) ((VehicleHistoryItem) vehicleHistoryItem)
								.getGeoPosition()).setTimeUTC(dateTimeSec);

						((GeoPosition) ((VehicleHistoryItem) vehicleHistoryItem)
								.getGeoPosition())
								.setDateTime(AppUtils.getFormattedDate(
										appProperties
												.getProperty("mx.skyguardian.controltower.geoposition.datetime.format"),
										dateTimeSec));

						((VehicleHistoryItem) vehicleHistoryItem)
								.setLastMsgReport((jsonDeserializer
										.getLastMsgReport(pObj)));

						vehicleHistoryItemList
								.add((VehicleHistoryItem) vehicleHistoryItem);
					}
				}

				vehicleHistory.setHistory(vehicleHistoryItemList);
				return vehicleHistory;
			}
		}

		return new VehicleHistory();
	}
	
	public void setHttpReqExecutor(IWialonHTTPRequestExecutor httpReqExecutor) {
		this.httpReqExecutor = httpReqExecutor;
	}

	public void setJsonDeserializer(AbsctractJSONDeserializer jsonDeserializer) {
		this.jsonDeserializer = jsonDeserializer;
	}

	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}

	private void setWialonSession(JSONObject loginObj, WialonSession wialonSessionObj){
		JSONObject userObj = loginObj.optJSONObject("user");
		Object eid = loginObj.opt("eid");
		Object tm = loginObj.get("tm");
		
		if ((userObj != null && userObj.opt("id") != null) && eid != null && tm != null) {
			wialonSessionObj.setUserId(userObj.opt("id").toString());
			wialonSessionObj.setEid(loginObj.get("eid").toString());
			wialonSessionObj.setServerTime(Integer.valueOf(loginObj.get("tm").toString()));
		} else {
			throw new WialonInternalServerError();
		}
	}
	
}
