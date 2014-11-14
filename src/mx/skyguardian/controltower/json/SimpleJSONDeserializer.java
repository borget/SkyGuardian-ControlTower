package mx.skyguardian.controltower.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.GeoPosition;
import mx.skyguardian.controltower.bean.LastMsgReport0;
import mx.skyguardian.controltower.bean.LastMsgReportBase;
import mx.skyguardian.controltower.bean.LastMsgReport251;
import mx.skyguardian.controltower.bean.LastMsgReport252;
import mx.skyguardian.controltower.bean.LastMsgReport253;
import mx.skyguardian.controltower.bean.LastMsgReport254;
import mx.skyguardian.controltower.http.remoting.AbstractUser;
import mx.skyguardian.controltower.http.remoting.IWialonHTTPRequestExecutor;
import mx.skyguardian.controltower.util.AppUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleJSONDeserializer extends AbsctractJSONDeserializer {
	private IWialonHTTPRequestExecutor httpReqExecutor = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	private static Logger log = Logger.getLogger(SimpleJSONDeserializer.class);

	@Override
	public AbstractWialonEntity getGEOPosition(JSONObject jsonObj, AbstractUser user) throws JSONException {
		AbstractWialonEntity geoPosition = null;
		
		if (jsonObj != null) {
			if (!jsonObj.isNull("pos")) {
				JSONObject geoPos = jsonObj.getJSONObject("pos");
				geoPosition = new GeoPosition(
						Long.parseLong((geoPos.isNull("t"))?"21600":geoPos.get("t").toString()),
						Double.parseDouble((geoPos.isNull("y"))?"0.0":geoPos.get("y").toString()),
						Double.parseDouble((geoPos.isNull("x"))?"0.0":geoPos.get("x").toString()),
						Double.parseDouble((geoPos.isNull("z"))?"0":geoPos.get("z").toString()),
						Integer.parseInt((geoPos.isNull("s"))?"0":geoPos.get("s").toString()),
						Integer.parseInt((geoPos.isNull("c"))?"0":geoPos.get("c").toString()),
						Integer.parseInt((geoPos.isNull("sc"))?"0":geoPos.get("sc").toString()));
				
				((GeoPosition)geoPosition).setDateTime(AppUtils.getFormattedDate(appProperties.getProperty(
						"mx.skyguardian.controltower.geoposition.datetime.format"),((GeoPosition)geoPosition).getTimeUTC()));
				
				try {
					
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("longitud", (((GeoPosition) geoPosition).getLongitud()).toString());
					properties.put("latitud", (((GeoPosition) geoPosition).getLatitud()).toString());
					properties.put("userId", user.getUserId());

					
					String geoPosUrl = AppUtils.getURL(
							appProperties.getProperty("mx.skyguardian.controltower.address.by.coordinates"), properties);
					
					JSONObject geoPosDesc = httpReqExecutor.getHTTPRequest(geoPosUrl);
			
					if(geoPosDesc.length()!=0){
						if (!geoPosDesc.isNull("jsonArray") && geoPosDesc.get("jsonArray").toString().length() > 4){
							String geoPosStr = geoPosDesc.get("jsonArray").toString();
							geoPosStr = geoPosStr.substring(2, geoPosStr.length()-2);
							byte[] latin1 = geoPosStr.getBytes("ISO-8859-1");
							((GeoPosition)geoPosition).setPositionDesc(new String (latin1, "UTF-8"));
							((GeoPosition)geoPosition).setPositionDesc(geoPosStr);
						}
					}

				} catch (IOException e) {
					log.error("There was an error retriving the Geo Position Description.");
					e.printStackTrace();
				} 
				return geoPosition;
			}
			
		}
		
		return new GeoPosition();
	}
	
	@Override
	public AbstractWialonEntity getLastMsgReport(JSONObject pObject) throws JSONException {
		int reportID = Integer.parseInt((pObject.isNull("report_id"))?"0":pObject.get("report_id").toString());
		AbstractWialonEntity report = null;
		switch (reportID) {
			case 0:
				report = new LastMsgReport0();
				setReport0(report, pObject);
				return report;
			case 2:
				report = new LastMsgReportBase();
				setReport2(report, pObject);
				return report;
				
			case 251:
				AbstractWialonEntity report251 = new LastMsgReport251();
				setReport2(report251, pObject);
				setReport251(report251, pObject);
				return report251;
			case 252:
				AbstractWialonEntity report252 = new LastMsgReport252();
				setReport2(report252, pObject);
				setReport252(report252, pObject);
				return report252;
				
			case 253:
				AbstractWialonEntity report253 = new LastMsgReport253();
				setReport2(report253, pObject);
				setReport253(report253, pObject);
				return report253;
				
			case 254:
				AbstractWialonEntity report254 = new LastMsgReport254();
				setReport2(report254, pObject);
				setReport254(report254, pObject);
				return report254;
			default:
				report = new LastMsgReportBase();
				setReport2(report, pObject);
				return report;
		}
	}
	
	private void setReport0(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReport0) report).setReport_id((jsonReport.isNull("report_id")) ? StringUtils.EMPTY :jsonReport.get("report_id").toString());
		((LastMsgReport0) report).setGps_acc((jsonReport.isNull("gps_acc")) ? StringUtils.EMPTY :jsonReport.get("gps_acc").toString());
		((LastMsgReport0) report).setPacket_type((jsonReport.isNull("packet_type")) ? StringUtils.EMPTY :jsonReport.get("packet_type").toString());
		((LastMsgReport0) report).setMcc((jsonReport.isNull("mcc")) ? StringUtils.EMPTY :jsonReport.get("mcc").toString());
		((LastMsgReport0) report).setMnc((jsonReport.isNull("mnc")) ? StringUtils.EMPTY :jsonReport.get("mnc").toString());
		((LastMsgReport0) report).setLac((jsonReport.isNull("lac")) ? StringUtils.EMPTY :jsonReport.get("lac").toString());
		((LastMsgReport0) report).setCell_id((jsonReport.isNull("cell_id")) ? StringUtils.EMPTY :jsonReport.get("cell_id").toString());
		((LastMsgReport0) report).setMileage((jsonReport.isNull("mileage")) ? StringUtils.EMPTY :jsonReport.get("mileage").toString());
		((LastMsgReport0) report).setAdc1((jsonReport.isNull("adc1")) ? StringUtils.EMPTY :jsonReport.get("adc1").toString());
		((LastMsgReport0) report).setPwr_ext((jsonReport.isNull("pwr_ext")) ? StringUtils.EMPTY :jsonReport.get("pwr_ext").toString());
		((LastMsgReport0) report).setNumber((jsonReport.isNull("number")) ? StringUtils.EMPTY :jsonReport.get("number").toString());
	}
	
	private void setReport2(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReportBase) report).setReportId((jsonReport.isNull("report_id"))? StringUtils.EMPTY :jsonReport.get("report_id").toString());			
		((LastMsgReportBase) report).setOdometer((jsonReport.isNull("odometer"))? StringUtils.EMPTY :jsonReport.get("odometer").toString());				
		((LastMsgReportBase) report).setHdop((jsonReport.isNull("hdop"))? StringUtils.EMPTY :jsonReport.get("hdop").toString());			   
		((LastMsgReportBase) report).setAdc1((jsonReport.isNull("adc1"))? StringUtils.EMPTY :jsonReport.get("adc1").toString());			   
		((LastMsgReportBase) report).setTemp1((jsonReport.isNull("temp1"))? StringUtils.EMPTY :jsonReport.get("temp1").toString());			  
		((LastMsgReportBase) report).setTemp2((jsonReport.isNull("temp2"))? StringUtils.EMPTY :jsonReport.get("temp2").toString());			  
		((LastMsgReportBase) report).setDl((jsonReport.isNull("dl"))? StringUtils.EMPTY :jsonReport.get("dl").toString());			     
		((LastMsgReportBase) report).setTw((jsonReport.isNull("tw"))? StringUtils.EMPTY :jsonReport.get("tw").toString());			     
		((LastMsgReportBase) report).setMT((jsonReport.isNull("MT"))? StringUtils.EMPTY :jsonReport.get("MT").toString());			     
		((LastMsgReportBase) report).setIp((jsonReport.isNull("ip"))? StringUtils.EMPTY :jsonReport.get("ip").toString());			     
		((LastMsgReportBase) report).setPs((jsonReport.isNull("ps"))? StringUtils.EMPTY :jsonReport.get("ps").toString());			     
		((LastMsgReportBase) report).setSs((jsonReport.isNull("ss"))? StringUtils.EMPTY :jsonReport.get("ss").toString());			     
		((LastMsgReportBase) report).setHa((jsonReport.isNull("ha"))? StringUtils.EMPTY :jsonReport.get("ha").toString());			     
		((LastMsgReportBase) report).setHb((jsonReport.isNull("hb"))? StringUtils.EMPTY :jsonReport.get("hb").toString());			     
		((LastMsgReportBase) report).setHc((jsonReport.isNull("hc"))? StringUtils.EMPTY :jsonReport.get("hc").toString());			     
		((LastMsgReportBase) report).setJd((jsonReport.isNull("jd"))? StringUtils.EMPTY :jsonReport.get("jd").toString());			     
		((LastMsgReportBase) report).setBl((jsonReport.isNull("bl"))? StringUtils.EMPTY :jsonReport.get("bl").toString());			     
		((LastMsgReportBase) report).setEG((jsonReport.isNull("EG"))? StringUtils.EMPTY :jsonReport.get("EG").toString());			     
		((LastMsgReportBase) report).setBV((jsonReport.isNull("BV"))? StringUtils.EMPTY :jsonReport.get("BV").toString());			     
		((LastMsgReportBase) report).setMV((jsonReport.isNull("MV"))? StringUtils.EMPTY :jsonReport.get("MV").toString());			     
		((LastMsgReportBase) report).setRd((jsonReport.isNull("rd"))? StringUtils.EMPTY :jsonReport.get("rd").toString());			     
		((LastMsgReportBase) report).setOp((jsonReport.isNull("op"))? StringUtils.EMPTY :jsonReport.get("op").toString());			     
		((LastMsgReportBase) report).setIn0((jsonReport.isNull("in0"))? StringUtils.EMPTY :jsonReport.get("in0").toString());			    
		((LastMsgReportBase) report).setIn1((jsonReport.isNull("in1"))? StringUtils.EMPTY :jsonReport.get("in1").toString());			    
		((LastMsgReportBase) report).setIn2((jsonReport.isNull("in2"))? StringUtils.EMPTY :jsonReport.get("in2").toString());			    
		((LastMsgReportBase) report).setIn3((jsonReport.isNull("in3"))? StringUtils.EMPTY :jsonReport.get("in3").toString());			    
		((LastMsgReportBase) report).setOd((jsonReport.isNull("od"))? StringUtils.EMPTY :jsonReport.get("od").toString());			     
		((LastMsgReportBase) report).setGQ((jsonReport.isNull("GQ"))? StringUtils.EMPTY :jsonReport.get("GQ").toString());			     
		((LastMsgReportBase) report).setGS((jsonReport.isNull("GS"))? StringUtils.EMPTY :jsonReport.get("GS").toString());			     

		((LastMsgReportBase) report).setPC((jsonReport.isNull("PC"))? StringUtils.EMPTY :jsonReport.get("PC").toString());			      
		((LastMsgReportBase) report).setRP((jsonReport.isNull("RP"))? StringUtils.EMPTY :jsonReport.get("RP").toString());			      
		((LastMsgReportBase) report).setFL((jsonReport.isNull("FL"))? StringUtils.EMPTY :jsonReport.get("FL").toString());			      
		((LastMsgReportBase) report).setML((jsonReport.isNull("ML"))? StringUtils.EMPTY :jsonReport.get("ML").toString());			      
		((LastMsgReportBase) report).setFC((jsonReport.isNull("FC"))? StringUtils.EMPTY :jsonReport.get("FC").toString());			      
		((LastMsgReportBase) report).setAV((jsonReport.isNull("AV"))? StringUtils.EMPTY :jsonReport.get("AV").toString());			      
		((LastMsgReportBase) report).setGL((jsonReport.isNull("GL"))? StringUtils.EMPTY :jsonReport.get("GL").toString());			      
		((LastMsgReportBase) report).setFuel_n((jsonReport.isNull("fuel_n"))? StringUtils.EMPTY :jsonReport.get("fuel_n").toString());			  
		((LastMsgReportBase) report).setFuel_level((jsonReport.isNull("fuel_level"))? StringUtils.EMPTY :jsonReport.get("fuel_level").toString());
		((LastMsgReportBase) report).setJ1708_odo((jsonReport.isNull("j1708_odo"))? StringUtils.EMPTY :jsonReport.get("j1708_odo").toString());				
		((LastMsgReportBase) report).setJ1708_speed((jsonReport.isNull("j1708_speed"))? StringUtils.EMPTY :jsonReport.get("j1708_speed").toString()); 
		((LastMsgReportBase) report).setJ1708_fuel_level((jsonReport.isNull("j1708_fuel_level"))? StringUtils.EMPTY :jsonReport.get("j1708_fuel_level").toString());
		((LastMsgReportBase) report).setOp0((jsonReport.isNull("op0"))? StringUtils.EMPTY :jsonReport.get("op0").toString());				     
		((LastMsgReportBase) report).setOp1((jsonReport.isNull("op1"))? StringUtils.EMPTY :jsonReport.get("op1").toString());				     
		((LastMsgReportBase) report).setOp2((jsonReport.isNull("op2"))? StringUtils.EMPTY :jsonReport.get("op2").toString());				     
		((LastMsgReportBase) report).setOp3((jsonReport.isNull("op3"))? StringUtils.EMPTY :jsonReport.get("op3").toString());				     
		((LastMsgReportBase) report).setAvl_driver((jsonReport.isNull("avl_driver"))? StringUtils.EMPTY :jsonReport.get("avl_driver").toString()); 
	}
	
	
	private void setReport251(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReport251) report).setEngine_rpm((jsonReport.isNull("engine_rpm"))? StringUtils.EMPTY :jsonReport.get("engine_rpm").toString());
		((LastMsgReport251) report).setFuel_cons((jsonReport.isNull("fuel_cons"))? StringUtils.EMPTY :jsonReport.get("fuel_cons").toString());
		((LastMsgReport251) report).setJ1939_fuel_level((jsonReport.isNull("j1939_fuel_level"))? StringUtils.EMPTY :jsonReport.get("j1939_fuel_level").toString());
	}
	
	private void setReport252(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReport252) report).setJ1939_odo((jsonReport.isNull("j1939_odo"))? StringUtils.EMPTY :jsonReport.get("j1939_odo").toString());
		((LastMsgReport252) report).setTotal_fuel((jsonReport.isNull("total_fuel"))? StringUtils.EMPTY :jsonReport.get("total_fuel").toString());
	}
	
	private void setReport253(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReport253) report).setDrivers_state((jsonReport.isNull("drivers_state"))? StringUtils.EMPTY :jsonReport.get("drivers_state").toString());
		((LastMsgReport253) report).setDriver1_card((jsonReport.isNull("driver1_card"))? StringUtils.EMPTY :jsonReport.get("driver1_card").toString());
		((LastMsgReport253) report).setDriver2_card((jsonReport.isNull("driver2_card"))? StringUtils.EMPTY :jsonReport.get("driver2_card").toString());
		((LastMsgReport253) report).setTachograph_status((jsonReport.isNull("tachograph_status"))? StringUtils.EMPTY :jsonReport.get("tachograph_status").toString());
		((LastMsgReport253) report).setTachograph_speed((jsonReport.isNull("tachograph_speed"))? StringUtils.EMPTY :jsonReport.get("tachograph_speed").toString());
		((LastMsgReport253) report).setTacho_ext_info((jsonReport.isNull("tacho_ext_info"))? StringUtils.EMPTY :jsonReport.get("tacho_ext_info").toString());
	}
	
	private void setReport254(AbstractWialonEntity report, JSONObject jsonReport) {
		((LastMsgReport254) report).setVehicle_id((jsonReport.isNull("vehicle_id"))? StringUtils.EMPTY :jsonReport.get("vehicle_id").toString());
		((LastMsgReport254) report).setService_dist((jsonReport.isNull("service_dist"))? StringUtils.EMPTY :jsonReport.get("service_dist").toString());
		((LastMsgReport254) report).setEng_hours((jsonReport.isNull("eng_hours"))? StringUtils.EMPTY :jsonReport.get("eng_hours").toString());
		((LastMsgReport254) report).setEng_starter((jsonReport.isNull("eng_starter"))? StringUtils.EMPTY :jsonReport.get("eng_starter").toString());
		((LastMsgReport254) report).setAbs_active((jsonReport.isNull("abs_active"))? StringUtils.EMPTY :jsonReport.get("abs_active").toString());
	}

	public void setHttpReqExecutor(IWialonHTTPRequestExecutor httpReqExecutor) {
		this.httpReqExecutor = httpReqExecutor;
	}

	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}

}
