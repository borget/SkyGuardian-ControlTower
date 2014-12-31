package mx.skyguardian.controltower.http.remoting;

import java.util.Map;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.LastMsgReport0;
import mx.skyguardian.controltower.bean.LastMsgReport251;
import mx.skyguardian.controltower.bean.LastMsgReport252;
import mx.skyguardian.controltower.bean.LastMsgReport253;
import mx.skyguardian.controltower.bean.LastMsgReport254;
import mx.skyguardian.controltower.bean.LastMsgReportBase;

public class SkyGuardianControlTowerManagerHelper {
	public void setLastMsgReportBase(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		LastMsgReportBase report = (LastMsgReportBase)reportEntity;
		report.setReport_id(this.getParameterValue(prmsMap, "report_id"));
		report.setOdometer(this.getParameterValue(prmsMap, "odometer"));
		report.setHdop(this.getParameterValue(prmsMap, "hdop"));    
		report.setAdc1(this.getParameterValue(prmsMap, "adc1"));    			
		report.setTemp1(this.getParameterValue(prmsMap, "temp1"));   
		report.setTemp2(this.getParameterValue(prmsMap, "temp2"));   
		report.setDl(this.getParameterValue(prmsMap, "dl"));      
		report.setTw(this.getParameterValue(prmsMap, "tw"));      
		report.setMT(this.getParameterValue(prmsMap, "MT"));      
		report.setIp(this.getParameterValue(prmsMap, "ip"));      
		report.setPs(this.getParameterValue(prmsMap, "ps"));      
		report.setSs(this.getParameterValue(prmsMap, "ss"));      			
		report.setHa(this.getParameterValue(prmsMap, "ha"));      
		report.setHb(this.getParameterValue(prmsMap, "hb"));      
		report.setHc(this.getParameterValue(prmsMap, "hc"));      
		report.setJd(this.getParameterValue(prmsMap, "jd"));      
		report.setBl(this.getParameterValue(prmsMap, "bl"));      
		report.setEG(this.getParameterValue(prmsMap, "EG"));      
		report.setBV(this.getParameterValue(prmsMap, "BV"));      
		report.setMV(this.getParameterValue(prmsMap, "MV"));      			
		report.setRd(this.getParameterValue(prmsMap, "rd"));      
		report.setOp(this.getParameterValue(prmsMap, "op"));      
		report.setIn0(this.getParameterValue(prmsMap, "in0"));     
		report.setIn1(this.getParameterValue(prmsMap, "in1"));     
		report.setIn2(this.getParameterValue(prmsMap, "in2"));     
		report.setIn3(this.getParameterValue(prmsMap, "in3"));     
		report.setOd(this.getParameterValue(prmsMap, "od"));      
		report.setGQ(this.getParameterValue(prmsMap, "GQ"));      			
		report.setGS(this.getParameterValue(prmsMap, "GS"));            
		report.setPC(this.getParameterValue(prmsMap, "PC"));      
		report.setRP(this.getParameterValue(prmsMap, "RP"));      
		report.setFL(this.getParameterValue(prmsMap, "FL"));      
		report.setML(this.getParameterValue(prmsMap, "ML"));      
		report.setFC(this.getParameterValue(prmsMap, "FC"));      
		report.setAV(this.getParameterValue(prmsMap, "AV"));      			
		report.setGL(this.getParameterValue(prmsMap, "GL"));      
		report.setFuel_n(this.getParameterValue(prmsMap, "fuel_n"));  
		report.setJ1708_odo(this.getParameterValue(prmsMap, "j1708_odo"));
		report.setJ1708_speed(this.getParameterValue(prmsMap, "j1708_speed"));
		report.setJ1708_fuel_level(this.getParameterValue(prmsMap, "j1708_fuel_level"));			
		report.setOp0(this.getParameterValue(prmsMap, "op0"));     
		report.setOp1(this.getParameterValue(prmsMap, "op1"));     
		report.setOp2(this.getParameterValue(prmsMap, "op2"));     
		report.setOp3(this.getParameterValue(prmsMap, "op3"));
		report.setAvl_driver(this.getParameterValue(prmsMap, "avl_driver"));
	};
	
	public void setLastMsgReport0(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		this.setLastMsgReportBase(prmsMap, reportEntity);  
		LastMsgReport0 report = (LastMsgReport0)reportEntity;
		report.setReport_id(this.getParameterValue(prmsMap, "report_id"));
		report.setGps_acc(this.getParameterValue(prmsMap, "gps_acc"));      
		report.setPacket_type(this.getParameterValue(prmsMap, "packet_type"));      
		report.setMcc(this.getParameterValue(prmsMap, "mcc"));      
		report.setMnc(this.getParameterValue(prmsMap, "mnc"));      
		report.setLac(this.getParameterValue(prmsMap, "lac"));      			
		report.setCell_id(this.getParameterValue(prmsMap, "cell_id"));      
		report.setMileage(this.getParameterValue(prmsMap, "mileage"));      
		report.setAdc1(this.getParameterValue(prmsMap, "adc1"));      
		report.setPwr_ext(this.getParameterValue(prmsMap, "pwr_ext"));      
		report.setNumber(this.getParameterValue(prmsMap, "number"));        
	}
	
	public void setLastMsgReport251(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		LastMsgReport251 report = (LastMsgReport251)reportEntity;
		this.setLastMsgReportBase(prmsMap, reportEntity);      
		report.setEngine_rpm(this.getParameterValue(prmsMap, "engine_rpm"));      
		report.setFuel_cons(this.getParameterValue(prmsMap, "fuel_cons"));      			
		report.setJ1939_fuel_level(this.getParameterValue(prmsMap, "j1939_fuel_level"));
	}
	
	public void setLastMsgReport252(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		LastMsgReport252 report = (LastMsgReport252)reportEntity;
		this.setLastMsgReportBase(prmsMap, reportEntity);  
		report.setJ1939_odo(this.getParameterValue(prmsMap, "j1939_odo"));      
		report.setTotal_fuel(this.getParameterValue(prmsMap, "total_fuel"));      			
	}
	
	public void setLastMsgReport253(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		LastMsgReport253 report = (LastMsgReport253)reportEntity;
		this.setLastMsgReportBase(prmsMap, reportEntity);  
		report.setDrivers_state(this.getParameterValue(prmsMap, "drivers_state"));      
		report.setDriver1_card(this.getParameterValue(prmsMap, "driver1_card"));      			
		report.setDriver2_card(this.getParameterValue(prmsMap, "driver2_card"));            
		report.setTachograph_status(this.getParameterValue(prmsMap, "tachograph_status"));      
		report.setTachograph_speed(this.getParameterValue(prmsMap, "tachograph_speed"));      
		report.setTacho_ext_info(this.getParameterValue(prmsMap, "tacho_ext_info"));      
	}
	
	public void setLastMsgReport254(Map<Object, Object> prmsMap, AbstractWialonEntity reportEntity) {
		LastMsgReport254 report = (LastMsgReport254)reportEntity;
		this.setLastMsgReportBase(prmsMap, reportEntity);  
		report.setVehicle_id(this.getParameterValue(prmsMap, "vehicle_id"));      			
		report.setService_dist(this.getParameterValue(prmsMap, "service_dist"));            
		report.setEng_hours(this.getParameterValue(prmsMap, "eng_hours"));      
		report.setEng_starter(this.getParameterValue(prmsMap, "eng_starter"));      
		report.setAbs_active(this.getParameterValue(prmsMap, "abs_active"));
	}
	
	@SuppressWarnings("unchecked")
	public String getParameterValue(Map<Object, Object> prms, String parameter) {
		String value = null;
		if (prms.get(parameter) instanceof Map) {
			value = ((Map<Object, Object>)prms.get(parameter)).get("v") != null ?
					((Map<Object, Object>)prms.get(parameter)).get("v").toString():
					null; 
		}
		return value;
	}
}
