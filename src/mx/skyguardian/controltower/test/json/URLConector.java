package mx.skyguardian.controltower.test.json;

import java.util.Map;

import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;


public class URLConector {
	public class Login {
		public String xxx; 
		public Long yyy;
//		public String getEid () {
//			return this.eid;
//		}
//		 @Override
//	        public String toString() {
//	            return "login{" +
//	                    "eid='" + eid + '\'' +
//	                    "tm='" + tm + "\'}";
//	        }
//		public Login(String eid, Long tm) {
//			super();
//			this.eid = eid;
//			this.tm = tm;
//		}
	}
	public class Sensor {
		public String $4; 
		 @Override
	        public String toString() {
	            return "login{" +
	                    "4='" + $4 + '\''+ "\'}";
	        }
	}
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper =  JsonFactory.create();
//		String jsonString = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:%22serviacero%22,%22password%22:%22000000%22}", null);
        Login root = mapper.readValue(HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:%22serviacero%22,%22password%22:%22000000%22}", null), Login.class);
        System.out.println(root.toString());
        
//        String interval = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=messages/load_interval&params={%22itemId%22:12011118,%22timeFrom%22:1417068000,%22timeTo%22:1417154399,%22flags%22:0x0000,%22flagsMask%22:0xFF00,%22loadCount%22:0xffffffff}&sid="+root.getEid(), null);
//		System.out.println(interval.substring(0, 15));
//		
//		String sensors = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=unit/calc_sensors&params={%22source%22:%22%22,%22indexFrom%22:0,%22indexTo%22:0xffffffff,%22unitId%22:12011118,%22sensorId%22:4}&sid="+root.getEid(), null);
//		System.out.println(sensors);
//		Map<String, Object> sensor = mapper.readValue(sensors, Map.class);
//		System.err.println("blbllb");
		
	}
	
//	public static void main(String[] args) throws Exception {
//////		ObjectMapper objMapper = new ObjectMapper();
//////		URL loginURL = new URL("https://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:%22serviacero%22,%22password%22:%22000000%22}");
//////		Login loginObj = objMapper.readValue(loginURL, Login.class);
//////		System.out.println(loginObj);
////		
////		org.boon.json.ObjectMapper mapper =  JsonFactory.create();
//////	String jsonString = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:%22serviacero%22,%22password%22:%22000000%22}", null);
////    Login root = mapper.readValue(HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:%22serviacero%22,%22password%22:%22000000%22}", null), Login.class);
////    System.out.println(root.toString());
////    
////    String interval = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=messages/load_interval&params={%22itemId%22:12011118,%22timeFrom%22:1417586400,%22timeTo%22:1417672799,%22flags%22:0x0000,%22flagsMask%22:0xFF00,%22loadCount%22:0xffffffff}&sid="+root.getEid(), null);
////	System.out.println(interval.substring(0, 15));
////	
////	String sensors = HTTP.getJSON("https://hst-api.wialon.com/wialon/ajax.html?svc=unit/calc_sensors&params={%22source%22:%22%22,%22indexFrom%22:0,%22indexTo%22:0xffffffff,%22unitId%22:12011118,%22sensorId%22:4}&sid="+root.getEid(), null);
////
////	ObjectMapper objMapper = new ObjectMapper();
////	
////	List<Map<Object, Object>> sensorObj = objMapper.readValue(sensors,  objMapper.getTypeFactory().constructCollectionType(List.class, LinkedHashMap.class));
////
////	for( int i = sensorObj.size() -1; i >= 0 ; i --){
////		Map<Object, Object> sensorMapItem = sensorObj.get(i);
////		
////		System.out.println(sensorMapItem.get("4"));
////	}
////	
////	}
////		ObjectMapper objMapper = new ObjectMapper();
////
////		Map<Object, Object> sensorObj = objMapper.readValue("{\"4\":1}",
////				Map.class);
////		System.out.println(sensorObj.get("4"));
//	}
	
}
