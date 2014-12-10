package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.EmptyUnit;
import mx.skyguardian.controltower.bean.GeoPosition;
import mx.skyguardian.controltower.bean.LastMsgReportBase;
import mx.skyguardian.controltower.bean.Unit;
import mx.skyguardian.controltower.bean.Units;
import mx.skyguardian.controltower.bean.Vehicle;
import mx.skyguardian.controltower.bean.VehicleHistory;
import mx.skyguardian.controltower.bean.VehicleHistoryItem;
import mx.skyguardian.controltower.bean.Vehicles;
import mx.skyguardian.controltower.exception.WialonAccessDeniedException;
import mx.skyguardian.controltower.exception.WialonInternalServerError;
import mx.skyguardian.controltower.json.AbsctractJSONDeserializer;
import mx.skyguardian.controltower.util.AppUtils;
import mx.skyguardian.controltower.util.Constants;

import org.apache.log4j.Logger;
import org.boon.HTTP;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkyGuardianControlTowerManager implements IControlTowerManager {
	
	private static Logger log = Logger.getLogger(SkyGuardianControlTowerManager.class);
	private IWialonHTTPRequestExecutor httpReqExecutor = null;
	private AbsctractJSONDeserializer jsonDeserializer = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	public AbstractWialonEntity getUnit(String userName, String password, String unitId) throws WialonInternalServerError, IOException {
		AbstractSession wialonSession = this.httpReqExecutor.doLogin(userName, password);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("unitId", unitId);
		properties.put("eid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

		String unitUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.unit.url"),
				properties);
		log.debug("SkyGuardianControlTowerManager.getUnit()-JSON=" + unitUrl);
		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitUrl);
		
		if (!itemObj.isNull("item")) {
			JSONObject jsonItem = (JSONObject) itemObj.get("item");
			
			JSONObject pObj = getPObject(jsonItem);
			if (pObj != null) {
				Unit unit = new Unit();
				unit.setUnitId(Long.parseLong((jsonItem.isNull("id")) ? "0"
								: jsonItem.get("id").toString()));
				unit.setUnitName((jsonItem.isNull("nm")) ? "unknown unit"
						: jsonItem.get("nm").toString());
				unit.setGeoPosition(jsonDeserializer.getGEOPosition(
						jsonItem, wialonSession));
				unit.setLastMsgReport((jsonDeserializer
						.getLastMsgReport(pObj)));
				
				AbstractWialonEntity reportBase = unit.getLastMsgReport();
				((LastMsgReportBase)reportBase).setFuel_level(this.tryToGetFuelSensor(unitId, wialonSession));
				
				return unit;
			}
		}

		return new EmptyUnit();
	}
	
	public AbstractWialonEntity getUnits(String userName, String password) throws IOException {
		AbstractSession wialonSession = this.httpReqExecutor.doLogin(userName, password);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

		String unitsUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.units.url"),
				properties);

		log.debug("SkyGuardianControlTowerManager.getUnits()-unitsUrl="
				+ unitsUrl);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitsUrl);
		log.debug("SkyGuardianControlTowerManager.getUnits()-JSON="
				+ itemObj.toString());
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
						Unit unit = new Unit();
						unit.setUnitId(Long.parseLong((jsonItem.isNull("id")) ? "0" : jsonItem.get("id").toString()));
						unit.setUnitName((jsonItem.isNull("nm")) ? "unknown unit" : jsonItem.get("nm").toString());
						unit.setGeoPosition(jsonDeserializer.getGEOPosition(jsonItem, wialonSession));
						unit.setLastMsgReport((jsonDeserializer.getLastMsgReport(pObj)));
						
						AbstractWialonEntity reportBase = unit.getLastMsgReport();
						((LastMsgReportBase)reportBase).setFuel_level(null);
						unitsList.add((Unit) unit);
					}
				}
				units.setUnit(unitsList);
				return units;
			}
		}

		return new Units();
	}
	
	@Override
	public AbstractWialonEntity getVehicles(String userName, String password) throws WialonAccessDeniedException, IOException {
		AbstractSession wialonSession = this.httpReqExecutor.doLogin(userName, password);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

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
		AbstractSession wialonSession = this.httpReqExecutor.doLogin(userName, password);
		
		Map<String, String> properties = new HashMap<String, String>();
		Long serverTime = wialonSession.getTm();
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
	
	private String tryToGetFuelLevelValue(Map<Object, Object> sensorObj) {
		String fuelLevel = null;
		try {
			Double value = sensorObj.get(Constants.SENSORS_FUEL_LEVEL) != null ?
					Double.valueOf(sensorObj.get(Constants.SENSORS_FUEL_LEVEL).toString()):null;
			if (value != null && value == Math.abs(value)) {
				fuelLevel = value.toString();
			}
		} catch (Exception e) {
			log.error("Exception getting FUEL_SENSOR_4: "+e.getMessage());
		}	
		return fuelLevel;
	}
	
	private String loadMessagesByInterval (String unitId, AbstractSession wialonSession) throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("unitId", unitId);
		properties.put("timeTo", String.valueOf(AppUtils.getTimeTo(appProperties.getProperty("mx.skyguardian.controltower.time.zone.MX"))));
		properties.put("timeFrom", String.valueOf(AppUtils.getTimeFrom(appProperties.getProperty("mx.skyguardian.controltower.time.zone.MX"))));
		properties.put("maxIndex",Constants.FLAGS_MAX_INDEX);
		properties.put("sid", wialonSession.getEid());
		
		String loadByIntervalUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.load.messages.by.interval"), properties);
		log.debug(loadByIntervalUrl);
		
		return HTTP.getJSON(loadByIntervalUrl, null);
	}
	
	private String calculateSensors (String unitId, AbstractSession wialonSession, String sensorId) throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("unitId", unitId);
		properties.put("maxIndex", Constants.FLAGS_MAX_INDEX);
		properties.put("sensorId", sensorId);
		properties.put("sid", wialonSession.getEid());
		
		String sensorsUrl = AppUtils.getURL(appProperties.getProperty("mx.skyguardian.controltower.calculate.sensors"), properties);
		log.debug(sensorsUrl);
		return HTTP.getJSON(sensorsUrl, null);
	}
	
	private String tryToGetFuelSensor(String unitId, AbstractSession wialonSession) {
		String fuelSensor = null;
		try {
			this.loadMessagesByInterval(unitId, wialonSession);
			String sensorJSON = calculateSensors(unitId, wialonSession, Constants.SENSORS_FUEL_LEVEL);
			ObjectMapper objMapper = new ObjectMapper();
			if (sensorJSON != null && sensorJSON.startsWith("[")) {
				List<Map<Object, Object>> sensorList = objMapper.readValue(sensorJSON, objMapper.getTypeFactory().constructCollectionType(List.class, LinkedHashMap.class));

				for( int i = sensorList.size() -1; i >= 0 ; i --) {
					Map<Object, Object> sensorItem = sensorList.get(i);
					String fuelLevelValue = this.tryToGetFuelLevelValue(sensorItem);
					if (fuelLevelValue != null) {
						fuelSensor = fuelLevelValue;
						break;
					}
				}
			} else {
				@SuppressWarnings("unchecked")
				Map<Object, Object> sensorObj = objMapper.readValue(sensorJSON,  Map.class);
				fuelSensor = this.tryToGetFuelLevelValue(sensorObj);
			}
			return fuelSensor;
			
		} catch(Exception e) {
			log.error("Exception setting FUEL_SENSOR: "+e.getMessage());
			return fuelSensor;
		}
	}
}
