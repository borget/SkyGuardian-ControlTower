package mx.skyguardian.controltower.test.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class URLConector {

	public static void main(String[] args) throws Exception {
		//URL oracle = new URL("http://hst-api.wialon.com/wialon/ajax.html?svc=core/login&params={%22user%22:tescobedo,%20%22password%22:sky2013}");
		
		//URL oracle = new URL("http://hst-api.wialon.com/wialon/ajax.html?svc=core/search_item&params={%22id%22:879279,%20%22flags%22:1025}&sid=be65fbd323a55fb80998101ac355f25b");
		//URL oracle = new URL("http://198.71.59.110:8080/SkyGuardianControlTower/service/unit/1220354?userName=tescobedo");
		URL oracle = new URL("http://localhost:8080/SkyGuardianControlTower/service/units?userName=tescobedo&password=1784b9a317268232a855c009f261e7b67d4e3322fba5968dd7cbd0897962482599cb2c1d35");
		URLConnection yc = oracle.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		
		StringBuilder json = new StringBuilder();
		String inputLine;
		
		while ((inputLine = in.readLine()) != null){
			json.append(inputLine);
		}
		in.close();
		
		System.err.println(json);
		//{"host":"201.157.5.55","eid":"be65fbd323a55fb80998101ac355f25b","au":"tescobedo","tm":1376165957,"user":{"nm":"tescobedo","cls":1,"id":879274,"prp":{"cfmt":"0","city":"Queretaro","dst":"-1","evt_flags":"3","fpnl":"monitoring","locator":"1","mbl_ui_visibility":"1","minimap_zoom_level":"13","mont":"1","monu":"[\"879279\",\"956572\"]","monuexpg":"{\"884271\":1,\"976295\":1}","monugr":"{\"884271\":[879279],\"976295\":[956572]}","monugv":"[\"879279\",\"956572\"]","monuv":"[\"879279\",\"956572\"]","msc":"0","mtg":"1","mtgy":"1","mtve":"1","mu_cmd_btn":"1","mu_delete_from_list":"0","mu_dev_cfg":"1","mu_driver":"1","mu_events":"1","mu_gps":"1","mu_gps_mode":"0","mu_gps_time":"0","mu_loc_mode":"0","mu_location":"0","mu_msgs":"0","mu_photo":"0","mu_sens":"1","mu_tracks":"1","mu_tracks_ival":"0","muf":"1035","muow":"[\"879279\",\"956572\"]","radd":"{\"w\":\"6\",\"c\":0,\"u\":879279,\"a\":0,\"td\":0,\"s\":\"default\"}","show_log":"0","tz":"-100160608","umap":"Google Hybrid","umsp":"-99.157,19.382000000000012,9","ursstp":"0x1f8a","us_addr_fmt":"1255211008_10_5","used_hw":"{\"34889\":1,\"672158\":1}","user_settings_hotkeys":"1","user_unit_cmds":"AT$PMGR=5,4,0,5,60,1\nAT$TRAC=1,40,,,,,2\nAT$TRAC=1,90,,,,,2","vsplit_block_left_panel":"511","vsplit_monitoring_map_target":"200","zlst":"[\"879275_1\"]"},"crt":327207,"bact":879275,"fl":4,"hm":"","uacl":2097795},"classes":{"avl_hw":4,"avl_resource":3,"avl_retranslator":7,"avl_route":6,"avl_unit":2,"avl_unit_group":5,"user":1}}
		//{"item":{"nm":"TESA203","cls":2,"id":879279,"pos":{"t":1376165802,"y":21.291375,"x":-100.523209,"z":0,"s":0,"c":82,"sc":255},"lmsg":{"t":1376165802,"f":7,"tp":"ud","pos":{"y":21.291375,"x":-100.523209,"z":0,"s":0,"c":82,"sc":255},"i":0,"o":0,"p":{"report_id":2,"odometer":32121.5,"hdop":0.8,"adc1":0,"temp1":200,"temp2":200,"DL":0,"TW":0,"MT":0,"IP":0,"PS":0,"SS":0,"HA":0,"HB":0,"HC":0,"JD":0,"BL":0,"EG":1,"MV":129,"RD":2,"OP":0,"IN":0,"IN1":0,"OD":321215}},"uacl":-1},"flags":1025}
		/*
		org.json.JSONObject object = new org.json.JSONObject(json.toString());
		//Object location = object.get("eid");
		
		org.json.JSONObject itemObj = new org.json.JSONObject(json.toString());
		org.json.JSONObject item = (org.json.JSONObject) itemObj.get("item");
		
		org.json.JSONObject lmsg = item.getJSONObject("lmsg");
		org.json.JSONObject report = lmsg.getJSONObject("p");
		
		System.out.println(report);
		System.out.println("testing");
		
		//JSONToBean(json.toString(), PrestoJSONRecordBean.class);
		*/
		
	}

}
