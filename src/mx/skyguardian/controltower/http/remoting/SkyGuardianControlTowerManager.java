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
import mx.skyguardian.controltower.bean.GeoPosition;
import mx.skyguardian.controltower.bean.LastMsgReport0;
import mx.skyguardian.controltower.bean.LastMsgReport251;
import mx.skyguardian.controltower.bean.LastMsgReport252;
import mx.skyguardian.controltower.bean.LastMsgReport253;
import mx.skyguardian.controltower.bean.LastMsgReport254;
import mx.skyguardian.controltower.bean.LastMsgReportBase;
import mx.skyguardian.controltower.bean.User;
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
	private SkyGuardianControlTowerManagerHelper helper = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	@Resource(name = "users")
	private Map<String, User> users;

	public AbstractWialonEntity getUnit(String userName, String password, String unitId) throws WialonInternalServerError, IOException {
		User user = users.get(userName);
		AbstractSession wialonSession = this.httpReqExecutor.oAuthLogin(userName, password, user);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("unitId", unitId);
		properties.put("eid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

		String unitUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.unit.url"),
				properties);
		//log.debug("SkyGuardianControlTowerManager.getUnit()-JSON=" + unitUrl);
		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitUrl);
		
		Unit unit = new Unit();
		if (!itemObj.isNull("item")) {
			JSONObject jsonItem = (JSONObject) itemObj.get("item");
			this.setCommonUnitData(unit, wialonSession, jsonItem);
			JSONObject pObj = getPObject(jsonItem);
			if(pObj != null){
				unit.setLastMsgReport((this.jsonDeserializer.getLastMsgReport(pObj)));
			} else {
				unit.setLastMsgReport(this.getLastKnownPrms(jsonItem.toString()));
			}
			AbstractWialonEntity reportBase = unit.getLastMsgReport();
			((LastMsgReportBase)reportBase).setFuel_level(this.tryToGetFuelSensor(unitId, wialonSession));
		}
//		unit.setLastMsgReport(this.getLastKnownPrms("{\"id\":12219894,\"cls\":2,\"prms\":{\"fuel_cons\":{\"v\":0,\"at\":1417202451,\"ct\":1417201118},\"out7\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out6\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out9\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out8\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out3\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out2\":{\"v\":0,\"at\":1418402733,\"ct\":1415304185},\"out5\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out4\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out1\":{\"v\":0,\"at\":1418402733,\"ct\":1415303985},\"DL\":{\"v\":0,\"at\":1418402733,\"ct\":1418347237},\"od\":{\"v\":\"24936\",\"at\":1418402733,\"ct\":1418353733},\"posinfo\":{\"v\":{\"c\":359,\"sc\":255,\"z\":582,\"y\":25.810233,\"x\":-100.400996},\"at\":1418402733,\"ct\":1418402733},\"j1939_odo\":{\"v\":23284,\"at\":1417202441,\"ct\":1417198350},\"MV\":{\"v\":128,\"at\":1418402733,\"ct\":1418400233},\"pwr_int\":{\"v\":4.2,\"at\":1418402733,\"ct\":1416013731},\"engine\":{\"v\":0,\"at\":1418402733,\"ct\":1418353733},\"gsm\":{\"v\":16,\"at\":1418402733,\"ct\":1418402733},\"hb\":{\"v\":0,\"at\":1418402733,\"ct\":1417116373},\"hc\":{\"v\":0,\"at\":1418402733,\"ct\":1418313084},\"MT\":{\"v\":0,\"at\":1418402733,\"ct\":1418353226},\"ha\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"IN2\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"IN1\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"accel_pos\":{\"v\":78,\"at\":1417202451,\"ct\":1417202421},\"IN0\":{\"v\":0,\"at\":1418402733,\"ct\":1415304425},\"EG\":{\"v\":0,\"at\":1418402733,\"ct\":1418353733},\"in31\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in30\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"gps_status\":{\"v\":0,\"at\":1415058905,\"ct\":1415058905},\"in32\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"TW\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"gsm_status\":{\"v\":9,\"at\":1418402733,\"ct\":1418313270},\"out28\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out29\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out26\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out27\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out24\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"engine_rpm\":{\"v\":1614,\"at\":1417202451,\"ct\":1417202451},\"out25\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out23\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"brake_pressure1\":{\"v\":800,\"at\":1417202451,\"ct\":1417202451},\"out22\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"brake_pressure2\":{\"v\":800,\"at\":1417202451,\"ct\":1417202451},\"pt_air_pressure\":{\"v\":0,\"at\":1417202451,\"ct\":1415303422},\"out21\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out20\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in29\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in28\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in27\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in26\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in25\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in24\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"snd_tm\":{\"v\":1418402733,\"at\":1418402733,\"ct\":1418402733},\"dl\":{\"v\":0,\"at\":1418402733,\"ct\":1418315082},\"in23\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in22\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"OD\":{\"v\":24936,\"at\":1418402733,\"ct\":1418353733},\"in21\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in20\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"brake_pos\":{\"v\":102,\"at\":1417202451,\"ct\":1415303422},\"HB\":{\"v\":0,\"at\":1418402733,\"ct\":1417116373},\"HC\":{\"v\":0,\"at\":1418402733,\"ct\":1418313084},\"HA\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"IMSI\":{\"v\":\"334020520994657\",\"at\":1415058905,\"ct\":1415058905},\"out32\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in19\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"j1939_fuel_level\":{\"v\":100,\"at\":1417202451,\"ct\":1417195929},\"out31\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in18\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"GS\":{\"v\":9,\"at\":1418402733,\"ct\":1418347237},\"in15\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"GQ\":{\"v\":16,\"at\":1418402733,\"ct\":1418402733},\"in14\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"tw\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"out30\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in17\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in16\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in11\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in10\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in13\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"cruise_time\":{\"v\":40,\"at\":1417202441,\"ct\":1417202081},\"in12\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out11\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out12\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out10\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"eng_boost_pressure\":{\"v\":18,\"at\":1417202451,\"ct\":1417202451},\"out19\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out18\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out17\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out16\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out15\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"out14\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"bl\":{\"v\":0,\"at\":1418402733,\"ct\":1415304145},\"out13\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"axle2\":{\"v\":0,\"at\":1417202451,\"ct\":1415303422},\"axle3\":{\"v\":0,\"at\":1417202451,\"ct\":1415303422},\"ss\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"firmware_ver\":{\"v\":\"Rev.2.10\",\"at\":1415058905,\"ct\":1415058905},\"axle1\":{\"v\":0,\"at\":1417202451,\"ct\":1415303422},\"motion\":{\"v\":0,\"at\":1418402733,\"ct\":1418353226},\"axle4\":{\"v\":0,\"at\":1417202451,\"ct\":1415303422},\"pto_time\":{\"v\":40,\"at\":1417202441,\"ct\":1417202081},\"AT\":{\"v\":582,\"at\":1418402733,\"ct\":1418402733},\"text\":{\"v\":\"$EGNS=131,10,130,10,1\r\",\"at\":1415316457,\"ct\":1415316457},\"IP\":{\"v\":0,\"at\":1418402733,\"ct\":1417648048},\"odometer\":{\"v\":2493.6,\"at\":1418402733,\"ct\":1418353733},\"gsm_level\":{\"v\":-79,\"at\":1415058905,\"ct\":1415058905},\"sats\":{\"v\":0,\"at\":1415058905,\"ct\":1415058905},\"form_state\":{\"v\":\"\",\"at\":1418403055,\"ct\":1415058188},\"PS\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"report_id\":{\"v\":2,\"at\":1418402733,\"ct\":1418347237},\"total_fuel\":{\"v\":6000,\"at\":1417202441,\"ct\":1417197950},\"JD\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"BV\":{\"v\":42,\"at\":1418402733,\"ct\":1416013731},\"rtc_tm\":{\"v\":1418402733,\"at\":1418402733,\"ct\":1418402733},\"hdop\":{\"v\":0.9,\"at\":1418402733,\"ct\":1418399733},\"model_name\":{\"v\":\"AT1Pro\",\"at\":1415058905,\"ct\":1415058905},\"gps_tm\":{\"v\":1418402733,\"at\":1418402733,\"ct\":1418402733},\"CID\":{\"v\":\"8952020013457546524\",\"at\":1415058905,\"ct\":1415058905},\"temp1\":{\"v\":200,\"at\":1418402733,\"ct\":1407421734},\"temp2\":{\"v\":200,\"at\":1418402733,\"ct\":1407421734},\"SS\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"coolant_temp\":{\"v\":89,\"at\":1417202451,\"ct\":1417202391},\"clutch_times\":{\"v\":0,\"at\":1417202441,\"ct\":1415303422},\"j1939_speed\":{\"v\":8,\"at\":1417202451,\"ct\":1417202451},\"BL\":{\"v\":0,\"at\":1418402733,\"ct\":1415304145},\"in2\":{\"v\":0,\"at\":1418402733,\"ct\":1415304421},\"in1\":{\"v\":0,\"at\":1418402733,\"ct\":1418353233},\"in4\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in3\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"speed\":{\"v\":0,\"at\":1418402733,\"ct\":1418394233},\"brake_times\":{\"v\":0,\"at\":1417202441,\"ct\":1415303422},\"in0\":{\"v\":0,\"at\":1418402733,\"ct\":1415304425},\"pwr_ext\":{\"v\":12.8,\"at\":1418402733,\"ct\":1418400233},\"adc1\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in\":{\"v\":0,\"at\":1418402733,\"ct\":1418353733},\"ip\":{\"v\":0,\"at\":1418402733,\"ct\":1417648048},\"jd\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"IMEI\":{\"v\":\"352964053429123\",\"at\":1415058905,\"ct\":1415058905},\"in9\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in5\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in6\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"in7\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734},\"ps\":{\"v\":0,\"at\":1418402733,\"ct\":1407421798},\"out\":{\"v\":0,\"at\":1418402733,\"ct\":1415304185},\"in8\":{\"v\":0,\"at\":1418402733,\"ct\":1407421734}},\"mu\":0,\"uacl\":-1,\"lmsg\":{\"f\":0,\"t\":1418403055,\"p\":{\"form_state\":\"\"},\"tp\":\"ud\",\"pos\":null},\"pos\":{\"t\":1418402733,\"s\":0,\"c\":359,\"sc\":255,\"z\":582,\"y\":25.810233,\"x\":-100.400996},\"nm\":\"DTO1401\"}"));

		return unit;
	}
	
	private void setCommonUnitData(Unit unit, AbstractSession wialonSession, JSONObject jsonItem) {
		unit.setUnitId(Long.parseLong((jsonItem.isNull("id")) ? "0" : jsonItem.get("id").toString()));
		unit.setUnitName((jsonItem.isNull("nm")) ? "Unknown Unit": jsonItem.get("nm").toString());
		unit.setGeoPosition(jsonDeserializer.getGEOPosition(jsonItem, wialonSession));
	}

	@SuppressWarnings("unchecked")
	private AbstractWialonEntity getLastKnownPrms(String prms){
		org.boon.json.ObjectMapper objMapper =  org.boon.json.JsonFactory.create();
		Map<Object, Object> sensorObj = objMapper.readValue(prms,  Map.class);
		AbstractWialonEntity report = null;
		if (sensorObj != null){
			if (sensorObj.get("prms") instanceof Map) {
				Map<Object, Object> prmsMap = (Map<Object, Object>)sensorObj.get("prms");
				String reportId = helper.getParameterValue(prmsMap, "report_id");
				if (reportId != null) {					
					switch (reportId) {
					case "0":
						report = new LastMsgReport0();
						helper.setLastMsgReportBase(prmsMap, report);
						break;
					case "2":
						report = new LastMsgReportBase();
						helper.setLastMsgReportBase(prmsMap, report);
						break;
					case "251":
						report = new LastMsgReport251();
						helper.setLastMsgReport251(prmsMap, report);
						break;
					case "252":
						report = new LastMsgReport252();
						helper.setLastMsgReport252(prmsMap, report);
						break;
					case "253":
						report = new LastMsgReport253();
						helper.setLastMsgReport253(prmsMap, report);
						break;
					case "254":
						report = new LastMsgReport254();
						helper.setLastMsgReport254(prmsMap, report);
						break;
					default:
						report = new LastMsgReportBase();
						helper.setLastMsgReportBase(prmsMap, report);
						break;
					}
				}
			}			                                           
		}
		return report != null ? report : new LastMsgReportBase();
	}
	
	public AbstractWialonEntity getUnits(String userName, String password) throws IOException {
		User user = users.get(userName);
		AbstractSession wialonSession = this.httpReqExecutor.oAuthLogin(userName, password, user);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

		String unitsUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.units.url"),
				properties);

		log.debug("SkyGuardianControlTowerManager.getUnits()-unitsUrl="
				+ unitsUrl);

		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitsUrl);
		//log.debug("SkyGuardianControlTowerManager.getUnits()-JSON=" + itemObj.toString());
		//JSONObject itemObj = new JSONObject("{\"searchSpec\":{\"itemsType\":\"avl_unit\",\"propName\":\"sys_name\",\"propValueMask\":\"*\",\"sortType\":\"sys_name\",\"propType\":\"propitemname\"},\"dataFlags\":1025,\"totalItemsCount\":110,\"indexFrom\":0,\"indexTo\":0,\"items\":[{\"nm\":\"60186\",\"cls\":2,\"id\":6527054,\"mu\":0,\"pos\":{\"t\":1415319033,\"y\":18.073986,\"x\":-94.283291,\"z\":0,\"s\":8,\"c\":77,\"sc\":255},\"lmsg\":{\"t\":1415319033,\"f\":7,\"tp\":\"ud\",\"pos\":{\"y\":18.073986,\"x\":-94.283291,\"z\":0,\"s\":8,\"c\":77,\"sc\":255},\"i\":9,\"o\":0,\"p\":{\"gps_tm\":1415319034,\"rtc_tm\":1415319033,\"snd_tm\":1415319055,\"report_id\":251,\"odometer\":71714.9,\"hdop\":0.8,\"adc1\":0,\"temp1\":200,\"temp2\":200,\"dl\":0,\"tw\":0,\"motion\":1,\"ip\":0,\"ps\":0,\"ss\":0,\"ha\":0,\"hb\":0,\"hc\":0,\"jd\":0,\"bl\":0,\"engine\":1,\"pwr_ext\":14,\"rd\":\"251\",\"op\":0,\"in0\":0,\"in1\":0,\"in2\":1,\"od\":\"717149\",\"gsm\":24,\"gsm_status\":9,\"engine_rpm\":1136,\"j1939_speed\":8,\"fuel_cons\":0,\"j1939_fuel_level\":90,\"axle1\":0,\"axle2\":0,\"axle3\":0,\"axle4\":0,\"eng_boost_pressure\":0,\"coolant_temp\":80,\"accel_pos\":19,\"brake_pos\":102,\"pt_air_pressure\":0,\"brake_pressure1\":608,\"brake_pressure2\":600,\"DL\":0,\"TW\":0,\"MT\":1,\"IP\":0,\"PS\":0,\"SS\":0,\"HA\":0,\"HB\":0,\"HC\":0,\"JD\":0,\"BL\":0,\"EG\":1,\"MV\":140,\"RD\":251,\"OP\":0,\"IN0\":0,\"IN1\":0,\"IN2\":1,\"OD\":717149,\"GQ\":24,\"GS\":9}},\"uacl\":281474976710655}]}");	
		if (!itemObj.isNull("searchSpec")) {
			JSONArray jsonArray = (JSONArray) itemObj.getJSONArray("items");
			if (jsonArray.length() > 0) {
				Units units = new Units();
				List<Unit> unitsList = new ArrayList<Unit>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = (JSONObject) jsonArray.get(i);
					JSONObject pObj = getPObject(jsonItem);
					Unit unit = new Unit();
					this.setCommonUnitData(unit, wialonSession, jsonItem);
					if (pObj != null) {
						unit.setLastMsgReport((this.jsonDeserializer.getLastMsgReport(pObj)));
					} else {
						unit.setLastMsgReport(this.getLastKnownPrms(jsonItem.toString()));
					}
					unitsList.add((Unit) unit);
				}
				units.setUnit(unitsList);
				log.debug("GET_UNITS_SERVICE=[" + units.getUnit().size() + "]");
				return units;
			}
		}
		
		return new Units();
	}
	
	@Override
	public AbstractWialonEntity getVehicles(String userName, String password) throws WialonAccessDeniedException, IOException {
		User user = users.get(userName);
		AbstractSession wialonSession = this.httpReqExecutor.oAuthLogin(userName, password, user);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", wialonSession.getEid());
		properties.put("flags", Constants.FLAGS_0x00100401);

		String unitsUrl = AppUtils.getURL(appProperties
				.getProperty("mx.skyguardian.controltower.search.units.url"),
				properties);

		//log.debug("SkyGuardianControlTowerManager.getVehicles()-unitsUrl=" + unitsUrl);

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
		User user = users.get(userName);
		AbstractSession wialonSession = this.httpReqExecutor.oAuthLogin(userName, password, user);
		
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

	public SkyGuardianControlTowerManagerHelper getHelper() {
		return helper;
	}

	public void setHelper(SkyGuardianControlTowerManagerHelper helper) {
		this.helper = helper;
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
	
	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
}
