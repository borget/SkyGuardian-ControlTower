package mx.skyguardian.controltower.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lastMsgReport")
public class LastMsgReport extends AbstractWialonEntity {

	private Integer reportId = -1;
	private Double odometer = -1D;
	private Double hdop = -1D;
	private Double adc1 = 0.0D;
	private Double temp1 = 0D;
	private Double temp2 = 0D;
	private Integer dl = -1;
	private Integer tw = -1;
	private Integer mt = -1;
	private Integer ip = -1;
	private Integer ps = -1;
	private Integer ss = -1;
	private Integer ha = -1;
	private Integer hb = -1;
	private Integer hc = -1;
	private Integer jd = -1;
	private Integer bl = -1;
	private Integer eg = -1;
	private Integer mv = -1;
	private Integer rd = -1;
	private Integer op = -1;
	private Integer in0 = -1;
	private Integer in1 = -1;
	private Integer od = -1;
	
	public LastMsgReport () {}
	
	public LastMsgReport(Integer reportId, Double odometer, Double hdop,
			Double adc1, Double temp1, Double temp2, Integer dl, Integer tw,
			Integer mt, Integer ip, Integer ps, Integer ss, Integer ha,
			Integer hb, Integer hc, Integer jd, Integer bl, Integer eg,
			Integer mv, Integer rd, Integer op, Integer in, Integer in1,
			Integer od) {
		super();
		this.reportId = reportId;
		this.odometer = odometer;
		this.hdop = hdop;
		this.adc1 = adc1;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.dl = dl;
		this.tw = tw;
		this.mt = mt;
		this.ip = ip;
		this.ps = ps;
		this.ss = ss;
		this.ha = ha;
		this.hb = hb;
		this.hc = hc;
		this.jd = jd;
		this.bl = bl;
		this.eg = eg;
		this.mv = mv;
		this.rd = rd;
		this.op = op;
		this.in1 = in1;
		this.od = od;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Double getOdometer() {
		return odometer;
	}
	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}
	public Double getHdop() {
		return hdop;
	}
	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}
	public Double getAdc1() {
		return adc1;
	}
	public void setAdc1(Double adc1) {
		this.adc1 = adc1;
	}
	public Double getTemp1() {
		return temp1;
	}

	public void setTemp1(Double temp1) {
		this.temp1 = temp1;
	}

	public Double getTemp2() {
		return temp2;
	}

	public void setTemp2(Double temp2) {
		this.temp2 = temp2;
	}

	public Integer getDl() {
		return dl;
	}
	public void setDl(Integer dl) {
		this.dl = dl;
	}
	public Integer getTw() {
		return tw;
	}
	public void setTw(Integer tw) {
		this.tw = tw;
	}
	public Integer getMt() {
		return mt;
	}
	public void setMt(Integer mt) {
		this.mt = mt;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	public Integer getSs() {
		return ss;
	}
	public void setSs(Integer ss) {
		this.ss = ss;
	}
	public Integer getHa() {
		return ha;
	}
	public void setHa(Integer ha) {
		this.ha = ha;
	}
	public Integer getHb() {
		return hb;
	}
	public void setHb(Integer hb) {
		this.hb = hb;
	}
	public Integer getHc() {
		return hc;
	}
	public void setHc(Integer hc) {
		this.hc = hc;
	}
	public Integer getJd() {
		return jd;
	}
	public void setJd(Integer jd) {
		this.jd = jd;
	}
	public Integer getBl() {
		return bl;
	}
	public void setBl(Integer bl) {
		this.bl = bl;
	}
	public Integer getEg() {
		return eg;
	}
	public void setEg(Integer eg) {
		this.eg = eg;
	}
	public Integer getMv() {
		return mv;
	}
	public void setMv(Integer mv) {
		this.mv = mv;
	}
	public Integer getRd() {
		return rd;
	}
	public void setRd(Integer rd) {
		this.rd = rd;
	}
	public Integer getOp() {
		return op;
	}
	public void setOp(Integer op) {
		this.op = op;
	}
	public Integer getIn1() {
		return in1;
	}
	public void setIn1(Integer in1) {
		this.in1 = in1;
	}
	public Integer getOd() {
		return od;
	}
	public void setOd(Integer od) {
		this.od = od;
	}

	public Integer getIn0() {
		return in0;
	}

	public void setIn0(Integer in0) {
		this.in0 = in0;
	}
	
}
