package mx.skyguardian.controltower.rest;

import java.io.IOException;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.EmptyUnit;
import mx.skyguardian.controltower.bean.Encryptor;
import mx.skyguardian.controltower.bean.User;
import mx.skyguardian.controltower.bean.Units;
import mx.skyguardian.controltower.bean.VehicleHistory;
import mx.skyguardian.controltower.bean.Vehicles;
import mx.skyguardian.controltower.exception.WialonBadRequestException;
import mx.skyguardian.controltower.exception.WialonInternalServerError;
import mx.skyguardian.controltower.http.remoting.IControlTowerManager;
import mx.skyguardian.controltower.security.JasyptEncryptor;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WialonUnitController {
	private static Logger log = Logger.getLogger(WialonUnitController.class);
	
	
	private IControlTowerManager controlTowerManager = null;
	private Jaxb2Marshaller jaxb2Mashaller;

	// //////////////////////// @ResponseBody ////////////////////////

	@RequestMapping(method = RequestMethod.GET, value = "/unit/{id}", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody AbstractWialonEntity getUnitById(
			@PathVariable String id, 
			@RequestParam("userName") String userName, 
			@RequestParam("password") String password) throws WialonInternalServerError, Exception {
		
		try {
			return (AbstractWialonEntity) controlTowerManager.getUnit(userName, password, id);
		} catch (JSONException je) {
			log.error("JSONException Catch");
			je.printStackTrace();
		}
		
		return new EmptyUnit();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/units", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody Units getUnits(
			@RequestParam("userName") String userName, 
			@RequestParam("password") String password) {
		try {
			return (Units) controlTowerManager.getUnits(userName, password);
		} catch (JSONException je) {
			log.error("JSONException Catch");
			je.printStackTrace();
		} catch (IOException ioe) {
			log.error("IOException");
			ioe.printStackTrace();
		}
		
		return new Units();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/vehicles", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody Vehicles getVehicles(
			@RequestParam("userName") String userName, 
			@RequestParam("password") String password) {
		try {
			return (Vehicles) controlTowerManager.getVehicles(userName, password);
		} catch (IOException e) {
			log.error("IOException Catch");
			e.printStackTrace();
		} catch (JSONException je) {
			log.error("JSONException Catch");
			je.printStackTrace();
		}
		
		return new Vehicles();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vehicle/history/{vehiculeId}", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody VehicleHistory getVehiculeHistory(
			@PathVariable String vehiculeId, 
			@RequestParam("interval") String interval, 
			@RequestParam("loadCount") String loadCount, 
			@RequestParam("userName") String userName, 
			@RequestParam("password") String password) {
		try {
			Integer.valueOf(interval);
			Integer.valueOf(loadCount);
			return (VehicleHistory) controlTowerManager.getVehiculeHistory(vehiculeId, interval, loadCount, userName, password);
		} catch (IOException e) {
			log.error("IOException Catch");
			e.printStackTrace();
		} catch (JSONException je) {
			log.error("JSONException Catch");
			je.printStackTrace();
		} catch(NumberFormatException nfe) {
			throw new WialonBadRequestException();
		}
		
		return new VehicleHistory();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/encryptor/{password}", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody Encryptor getEncryptor(@PathVariable String password) {
		Encryptor encryptor = new Encryptor();
		encryptor.setEncryptedText(JasyptEncryptor.encryptWithPBE(password));
		
		return encryptor;
	}
	
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}
	
	
	
	public void setControlTowerManager(IControlTowerManager controlTowerManager) {
		this.controlTowerManager = controlTowerManager;
	}

}
