package tams.formbean;

import java.util.ArrayList;

public class tamsbean {
	
	private int ir_flag;
	
	private String tc_tte;
	private String reservation_slip;
	private String Superfast;
	private String tourist;
	private String parking;
	private String mutp_chg;
	private String mrts_chg;
	private String cidco_chg;
	private String mmts_chg;
	
	private int inward;
	private int  retained;
	private int  retained_new;
	private int totalappotioned;
	private int outward;
	
	private String oga;
	private String platform;
	
	private String CR;
	private String EC;
	private String ER;
	private String EO;
	private String KR;
	private String NC;
	private String NE;
	private String NF;
	private String NR;
	private String NW;
	private String SB;
	private String SC;
	private String SE;
	private String SR;
	private String SW;
	private String WC;
	private String WR;
	
	
	private String month;
	private String year;
	private String reportype;
	private String format;
	private String trainno;
	private String fromStn;
	private String fromstncode;
	private String tostn;
	private String tostncode;
	
	private String entstn;
	private String dist;
	private String fare;
	
	
	private String rly;
	private String itotalrecords;
	private String  ifare;
	private String isafetycharges;
	
	
	private String iothercharges;
	private String icatgchg;
	private String mtotalrecords;
	private String mfare;
	private String msafetycharges;
	private String mothercharges;
	private String mcatgchg;
	private String utotalrecords;
	private String ufare;
	private String usafetycharges;
	private String uothercharges;
	private String ucatgchg;
	
	private String gauge;
	
	private String a;
	private String b;
	private String c;
	private String d;
	
	private String username;
	private String zone_name ;
	
	private String password;
	private String reportid;
	private String reportyear;
	private String reportmonth;
	private String earnprojecttype;
	private String reporttype;
	private String reportname;
	
	
	private String reportconverttype;
	ArrayList yearlist;
	ArrayList<tamsbean>monthlist;
	ArrayList<tamsbean>reportlist;
	ArrayList<tamsbean>projectlist;
	ArrayList<tamsbean>tamsrailaylist;
	ArrayList<tamsbean>tamsconvertreportlist;
	
	ArrayList<tamsbean> datastored =new ArrayList<tamsbean>();
	private ArrayList<tamsbean> datastored_new =new ArrayList<tamsbean>();
	
	private String torly;
	private String orgg;
	private String records;
	private String amount;
	private String total_shared;
	private String diff;
	
	
	
	
	public String getTorly() {
		return torly;
	}
	public void setTorly(String torly) {
		this.torly = torly;
	}
	public String getOrgg() {
		return orgg;
	}
	public void setOrgg(String orgg) {
		this.orgg = orgg;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTotal_shared() {
		return total_shared;
	}
	public void setTotal_shared(String total_shared) {
		this.total_shared = total_shared;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}
	
	
	
	public String getTc_tte() {
		return tc_tte;
	}
	public void setTc_tte(String tc_tte) {
		this.tc_tte = tc_tte;
	}

	public String getReservation_slip() {
		return reservation_slip;
	}
	public void setReservation_slip(String reservation_slip) {
		this.reservation_slip = reservation_slip;
	}
	public String getSuperfast() {
		return Superfast;
	}
	public void setSuperfast(String superfast) {
		Superfast = superfast;
	}
	public String getTourist() {
		return tourist;
	}
	public void setTourist(String tourist) {
		this.tourist = tourist;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getMutp_chg() {
		return mutp_chg;
	}
	public void setMutp_chg(String mutp_chg) {
		this.mutp_chg = mutp_chg;
	}
	public String getMrts_chg() {
		return mrts_chg;
	}
	public void setMrts_chg(String mrts_chg) {
		this.mrts_chg = mrts_chg;
	}
	public String getCidco_chg() {
		return cidco_chg;
	}
	public void setCidco_chg(String cidco_chg) {
		this.cidco_chg = cidco_chg;
	}
	public String getMmts_chg() {
		return mmts_chg;
	}
	public void setMmts_chg(String mmts_chg) {
		this.mmts_chg = mmts_chg;
	}
	
	public int getIr_flag() {
		return ir_flag;
	}
	public void setIr_flag(int ir_flag) {
		this.ir_flag = ir_flag;
	}
	

	public String getPlatform() {
			return platform;
		}
		public void setPlatform(String platform) {
		   this.platform=platform;
		}
	
	public String getOga() {
		return oga;
	}
	public void setOga(String oga) {
		this.oga = oga;
	}
	
	
	
	public int getInward() {
		return inward;
	}
	public void setInward(int inward) {
		this.inward = inward;
	}
	public int getRetained() {
		return retained;
	}
	public void setRetained(int retained) {
		this.retained = retained;
	}
	public int getTotalappotioned() {
		return totalappotioned;
	}
	public void setTotalappotioned(int totalappotioned) {
		this.totalappotioned = totalappotioned;
	}
	public int getOutward() {
		return outward;
	}
	public void setOutward(int outward) {
		this.outward = outward;
	}
	public String getCR() {
		return CR;
	}
	public void setCR(String cR) {
		CR = cR;
	}
	public String getEC() {
		return EC;
	}
	public void setEC(String eC) {
		EC = eC;
	}
	public String getER() {
		return ER;
	}
	public void setER(String eR) {
		ER = eR;
	}
	public String getEO() {
		return EO;
	}
	public void setEO(String eO) {
		EO = eO;
	}
	public String getKR() {
		return KR;
	}
	public void setKR(String kR) {
		KR = kR;
	}
	public String getNC() {
		return NC;
	}
	public void setNC(String nC) {
		NC = nC;
	}
	public String getNE() {
		return NE;
	}
	public void setNE(String nE) {
		NE = nE;
	}
	public String getNF() {
		return NF;
	}
	public void setNF(String nF) {
		NF = nF;
	}
	public String getNR() {
		return NR;
	}
	public void setNR(String nR) {
		NR = nR;
	}
	public String getNW() {
		return NW;
	}
	public void setNW(String nW) {
		NW = nW;
	}
	public String getSB() {
		return SB;
	}
	public void setSB(String sB) {
		SB = sB;
	}
	public String getSC() {
		return SC;
	}
	public void setSC(String sC) {
		SC = sC;
	}
	public String getSE() {
		return SE;
	}
	public void setSE(String sE) {
		SE = sE;
	}
	public String getSR() {
		return SR;
	}
	public void setSR(String sR) {
		SR = sR;
	}
	public String getSW() {
		return SW;
	}
	public void setSW(String sW) {
		SW = sW;
	}
	public String getWC() {
		return WC;
	}
	public void setWC(String wC) {
		WC = wC;
	}
	public String getWR() {
		return WR;
	}
	public void setWR(String wR) {
		WR = wR;
	}
	
	
	public String getEntstn() {
		return entstn;
	}
	public void setEntstn(String entstn) {
		this.entstn = entstn;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	public String getRly() {
		return rly;
	}
	public void setRly(String rly) {
		this.rly = rly;
	}
	public String getItotalrecords() {
		return itotalrecords;
	}
	public void setItotalrecords(String itotalrecords) {
		this.itotalrecords = itotalrecords;
	}
	public String getIfare() {
		return ifare;
	}
	public void setIfare(String ifare) {
		this.ifare = ifare;
	}
	public String getIsafetycharges() {
		return isafetycharges;
	}
	public void setIsafetycharges(String isafetycharges) {
		this.isafetycharges = isafetycharges;
	}
	public String getIothercharges() {
		return iothercharges;
	}
	public void setIothercharges(String iothercharges) {
		this.iothercharges = iothercharges;
	}
	public String getIcatgchg() {
		return icatgchg;
	}
	public void setIcatgchg(String icatgchg) {
		this.icatgchg = icatgchg;
	}
	public String getMtotalrecords() {
		return mtotalrecords;
	}
	public void setMtotalrecords(String mtotalrecords) {
		this.mtotalrecords = mtotalrecords;
	}
	public String getMfare() {
		return mfare;
	}
	public void setMfare(String mfare) {
		this.mfare = mfare;
	}
	public String getMsafetycharges() {
		return msafetycharges;
	}
	public void setMsafetycharges(String msafetycharges) {
		this.msafetycharges = msafetycharges;
	}
	public String getMothercharges() {
		return mothercharges;
	}
	public void setMothercharges(String mothercharges) {
		this.mothercharges = mothercharges;
	}
	public String getMcatgchg() {
		return mcatgchg;
	}
	public void setMcatgchg(String mcatgchg) {
		this.mcatgchg = mcatgchg;
	}
	public String getUtotalrecords() {
		return utotalrecords;
	}
	public void setUtotalrecords(String utotalrecords) {
		this.utotalrecords = utotalrecords;
	}
	public String getUfare() {
		return ufare;
	}
	public void setUfare(String ufare) {
		this.ufare = ufare;
	}
	public String getUsafetycharges() {
		return usafetycharges;
	}
	public void setUsafetycharges(String usafetycharges) {
		this.usafetycharges = usafetycharges;
	}
	public String getUothercharges() {
		return uothercharges;
	}
	public void setUothercharges(String uothercharges) {
		this.uothercharges = uothercharges;
	}
	public String getUcatgchg() {
		return ucatgchg;
	}
	public void setUcatgchg(String ucatgchg) {
		this.ucatgchg = ucatgchg;
	}
	
	
	
	public String getTrainno() {
		return trainno;
	}
	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}
	public String getFromStn() {
		return fromStn;
	}
	public void setFromStn(String fromStn) {
		this.fromStn = fromStn;
	}
	public String getFromstncode() {
		return fromstncode;
	}
	public void setFromstncode(String fromstncode) {
		this.fromstncode = fromstncode;
	}
	public String getTostn() {
		return tostn;
	}
	public void setTostn(String tostn) {
		this.tostn = tostn;
	}
	public String getTostncode() {
		return tostncode;
	}
	public void setTostncode(String tostncode) {
		this.tostncode = tostncode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getReportype() {
		return reportype;
	}
	public void setReportype(String reportype) {
		this.reportype = reportype;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		format = format;
	}
	

	public String getGauge() {
	return gauge;
}
public void setGauge(String gauge) {
	this.gauge = gauge;
}
	
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String zone;
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	
	
	public ArrayList<tamsbean> getTamsconvertreportlist() {
		return tamsconvertreportlist;
	}
	public void setTamsconvertreportlist(ArrayList<tamsbean> tamsconvertreportlist) {
		this.tamsconvertreportlist = tamsconvertreportlist;
	}
	public ArrayList<tamsbean> getTamsrailaylist() {
		return tamsrailaylist;
	}
	public void setTamsrailaylist(ArrayList<tamsbean> tamsrailaylist) {
		this.tamsrailaylist = tamsrailaylist;
	}
	public ArrayList<tamsbean> getProjectlist() {
		return projectlist;
	}
	public void setProjectlist(ArrayList<tamsbean> projectlist) {
		this.projectlist = projectlist;
	}
	public ArrayList<tamsbean> getReportlist() {
		return reportlist;
	}
	public void setReportlist(ArrayList<tamsbean> reportlist) {
		this.reportlist = reportlist;
	}
	public ArrayList<tamsbean> getMonthlist() {
		return monthlist;
	}
	public void setMonthlist(ArrayList<tamsbean> monthlist) {
		this.monthlist = monthlist;
	}
	
	public ArrayList getYearlist() {
		return yearlist;
	}
	public void setYearlist(ArrayList yearlist) {
		this.yearlist = yearlist;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getReportyear() {
		return reportyear;
	}
	public void setReportyear(String reportyear) {
		this.reportyear = reportyear;
	}
	public String getReportmonth() {
		return reportmonth;
	}
	public void setReportmonth(String reportmonth) {
		this.reportmonth = reportmonth;
	}
	public String getEarnprojecttype() {
		return earnprojecttype;
	}
	public void setEarnprojecttype(String earnprojecttype) {
		this.earnprojecttype = earnprojecttype;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getReportconverttype() {
		return reportconverttype;
	}
	public void setReportconverttype(String reportconverttype) {
		this.reportconverttype = reportconverttype;
	}
	public int getRetained_new() {
		return retained_new;
	}
	public void setRetained_new(int retained_new) {
		this.retained_new = retained_new;
	}
	
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getZone_name() {
		return zone_name;
	}
	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}
	public ArrayList<tamsbean> getDatastored_new() {
		return datastored_new;
	}
	public void setDatastored_new(ArrayList<tamsbean> datastored_new) {
		this.datastored_new = datastored_new;
	}

	
	
	public ArrayList getDatastored() {
		return datastored;
	}
	public void setDatastored(ArrayList datastored) {
		this.datastored = datastored;
	}
	
	}
	
	

