package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.exception.WialonAccessDeniedException;
import mx.skyguardian.controltower.exception.WialonInternalServerError;

public interface IControlTowerManager {
	public AbstractWialonEntity getUnit(String userName, String password, String unitId) throws WialonInternalServerError, IOException;
	public AbstractWialonEntity getUnits(String userName, String password) throws IOException;
	public AbstractWialonEntity getVehicles(String userName, String password) throws WialonAccessDeniedException, IOException;
	public AbstractWialonEntity getVehiculeHistory(String vehiculeId,String interval, String loadCount, String userName, String password) throws WialonAccessDeniedException, IOException;
}
