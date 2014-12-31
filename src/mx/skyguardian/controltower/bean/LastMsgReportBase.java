package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lastMsgReport")
public class LastMsgReportBase extends AbstractWialonEntity {

	private String report_id = null;
	private String odometer = null;
	private String hdop = null;
	private String adc1 = null;
	private String temp1 = null;
	private String temp2 = null;
	private String dl = null;
	private String tw = null;
	private String MT = null;
	private String ip = null;
	private String ps = null;
	private String ss = null;
	private String ha = null;
	private String hb = null;
	private String hc = null;
	private String jd = null;
	private String bl = null;
	private String EG = null;
	private String BV = null;
	private String MV = null;
	private String rd = null;
	private String op = null;
	private String in0 = null;
	private String in1 = null;
	private String in2 = null;
	private String in3 = null;
	private String od = null;
	private String GQ = null;
	private String GS = null;
	// uncommon parameters
	private String PC = null;
	private String RP = null;
	private String FL = null;
	private String ML = null;
	private String FC = null;
	private String AV = null;
	private String GL = null;
	private String fuel_n = null;
	private String fuel_level = null;
	private String j1708_odo = null;
	private String j1708_speed = null;
	private String j1708_fuel_level = null;
	private String op0 = null;
	private String op1 = null;
	private String op2 = null;
	private String op3 = null;
	private String avl_driver = null;
	
	public LastMsgReportBase() {
		
	}
	
	public LastMsgReportBase(String report_id, String odometer, String hdop,
			String adc1, String temp1, String temp2, String dl, String tw,
			String mT, String ip, String ps, String ss, String ha,
			String hb, String hc, String jd, String bl, String eG,
			String bV, String mV, String rd, String op, String in0,
			String in1, String in2, String in3, String od, String gQ,
			String gS, String pC, String rP, String fL, String mL, String fC,
			String aV, String gL, String fuel_n, String fuel_level,
			String j1708_odo, String j1708_speed, String j1708_fuel_level,
			String op0, String op1, String op2, String op3, String avl_driver) {
		super();
		this.report_id = report_id;
		this.odometer = odometer;
		this.hdop = hdop;
		this.adc1 = adc1;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.dl = dl;
		this.tw = tw;
		MT = mT;
		this.ip = ip;
		this.ps = ps;
		this.ss = ss;
		this.ha = ha;
		this.hb = hb;
		this.hc = hc;
		this.jd = jd;
		this.bl = bl;
		EG = eG;
		BV = bV;
		MV = mV;
		this.rd = rd;
		this.op = op;
		this.in0 = in0;
		this.in1 = in1;
		this.in2 = in2;
		this.in3 = in3;
		this.od = od;
		GQ = gQ;
		GS = gS;
		PC = pC;
		RP = rP;
		FL = fL;
		ML = mL;
		FC = fC;
		AV = aV;
		GL = gL;
		this.fuel_n = fuel_n;
		this.fuel_level = fuel_level;
		this.j1708_odo = j1708_odo;
		this.j1708_speed = j1708_speed;
		this.j1708_fuel_level = j1708_fuel_level;
		this.op0 = op0;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.avl_driver = avl_driver;
	}

	public String getOdometer() {
		return odometer;
	}
	public void setOdometer(String odometer) {
		this.odometer = odometer;
	}
	public String getHdop() {
		return hdop;
	}
	public void setHdop(String hdop) {
		this.hdop = hdop;
	}
	public String getAdc1() {
		return adc1;
	}
	public void setAdc1(String adc1) {
		this.adc1 = adc1;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getTw() {
		return tw;
	}
	public void setTw(String tw) {
		this.tw = tw;
	}
	public String getMT() {
		return MT;
	}
	public void setMT(String mT) {
		MT = mT;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getHa() {
		return ha;
	}
	public void setHa(String ha) {
		this.ha = ha;
	}
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {
		this.hb = hb;
	}
	public String getHc() {
		return hc;
	}
	public void setHc(String hc) {
		this.hc = hc;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getEG() {
		return EG;
	}
	public void setEG(String eG) {
		EG = eG;
	}
	public String getBV() {
		return BV;
	}
	public void setBV(String bV) {
		BV = bV;
	}
	public String getMV() {
		return MV;
	}
	public void setMV(String mV) {
		MV = mV;
	}
	public String getRd() {
		return rd;
	}
	public void setRd(String rd) {
		this.rd = rd;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getIn0() {
		return in0;
	}
	public void setIn0(String in0) {
		this.in0 = in0;
	}
	public String getIn1() {
		return in1;
	}
	public void setIn1(String in1) {
		this.in1 = in1;
	}
	public String getIn2() {
		return in2;
	}
	public void setIn2(String in2) {
		this.in2 = in2;
	}
	public String getIn3() {
		return in3;
	}
	public void setIn3(String in3) {
		this.in3 = in3;
	}
	public String getOd() {
		return od;
	}
	public void setOd(String od) {
		this.od = od;
	}
	public String getGQ() {
		return GQ;
	}
	public void setGQ(String gQ) {
		GQ = gQ;
	}
	public String getGS() {
		return GS;
	}
	public void setGS(String gS) {
		GS = gS;
	}
	public String getPC() {
		return PC;
	}
	public void setPC(String pC) {
		PC = pC;
	}
	public String getRP() {
		return RP;
	}
	public void setRP(String rP) {
		RP = rP;
	}
	public String getFL() {
		return FL;
	}
	public void setFL(String fL) {
		FL = fL;
	}
	public String getML() {
		return ML;
	}
	public void setML(String mL) {
		ML = mL;
	}
	public String getFC() {
		return FC;
	}
	public void setFC(String fC) {
		FC = fC;
	}
	public String getAV() {
		return AV;
	}
	public void setAV(String aV) {
		AV = aV;
	}
	public String getGL() {
		return GL;
	}
	public void setGL(String gL) {
		GL = gL;
	}
	public String getFuel_n() {
		return fuel_n;
	}
	public void setFuel_n(String fuel_n) {
		this.fuel_n = fuel_n;
	}
	public String getFuel_level() {
		return fuel_level;
	}
	public void setFuel_level(String fuel_level) {
		this.fuel_level = fuel_level;
	}
	public String getJ1708_odo() {
		return j1708_odo;
	}
	public void setJ1708_odo(String j1708_odo) {
		this.j1708_odo = j1708_odo;
	}
	public String getJ1708_speed() {
		return j1708_speed;
	}
	public void setJ1708_speed(String j1708_speed) {
		this.j1708_speed = j1708_speed;
	}
	public String getJ1708_fuel_level() {
		return j1708_fuel_level;
	}
	public void setJ1708_fuel_level(String j1708_fuel_level) {
		this.j1708_fuel_level = j1708_fuel_level;
	}
	public String getOp0() {
		return op0;
	}
	public void setOp0(String op0) {
		this.op0 = op0;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getAvl_driver() {
		return avl_driver;
	}
	public void setAvl_driver(String avl_driver) {
		this.avl_driver = avl_driver;
	}

	public String getReport_id() {
		return report_id;
	}

	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
}
