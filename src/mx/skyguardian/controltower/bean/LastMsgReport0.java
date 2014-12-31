package mx.skyguardian.controltower.bean;

public class LastMsgReport0 extends LastMsgReportBase {
	private String report_id = null;
	private String gps_acc = null;
	private String packet_type = null;
	private String mcc = null;
	private String mnc = null;
	private String lac = null;
	private String cell_id = null;
	private String mileage = null;
	private String adc1 = null;
	private String pwr_ext = null;
	private String number = null;
	
	public LastMsgReport0() {
		
	}
	
	public LastMsgReport0(String report_id, String gps_acc, String packet_type,
			String mcc, String mnc, String lac, String cell_id, String mileage,
			String adc1, String pwr_ext, String number) {
		super();
		this.report_id = report_id;
		this.gps_acc = gps_acc;
		this.packet_type = packet_type;
		this.mcc = mcc;
		this.mnc = mnc;
		this.lac = lac;
		this.cell_id = cell_id;
		this.mileage = mileage;
		this.adc1 = adc1;
		this.pwr_ext = pwr_ext;
		this.number = number;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getGps_acc() {
		return gps_acc;
	}
	public void setGps_acc(String gps_acc) {
		this.gps_acc = gps_acc;
	}
	public String getPacket_type() {
		return packet_type;
	}
	public void setPacket_type(String packet_type) {
		this.packet_type = packet_type;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCell_id() {
		return cell_id;
	}
	public void setCell_id(String cell_id) {
		this.cell_id = cell_id;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getAdc1() {
		return adc1;
	}
	public void setAdc1(String adc1) {
		this.adc1 = adc1;
	}
	public String getPwr_ext() {
		return pwr_ext;
	}
	public void setPwr_ext(String pwr_ext) {
		this.pwr_ext = pwr_ext;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
