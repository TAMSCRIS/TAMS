package tams.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import tams.formbean.tamsbean;
import tamsdbconnection.tamsdbconnection;

import com.opensymphony.xwork2.ActionSupport;

public class Viewdata extends ActionSupport {
	private static final long serialVersionUID = 1L;
	ArrayList<tamsbean> dataList = null;
	private ArrayList<tamsbean> dataList_new = null;
	HttpSession session;
	
	ResultSet rs = null;
	String fetchData = null;
	tamsdbconnection dbcon;
	Connection con = null;
	private String zone_name;
	String tamsmonth;
	String tamsproj;
	String tamsrptlist;
	String tamsrlylist;
	private String a;
	private String b;
	private String c;
	
	int i=0;
	long sumr1=0 , sumr2=0 , sumr3=0 , sumr4=0 ,sumr5=0 , sumr6=0 , sumr7=0 , sumr8=0 ,sumr9=0 , sumr10=0 , sumr11=0 , sumr12=0 , sumr13=0 , sumr14=0 , sumr15=0 , sumr16=0 , sumr17=0 , sumr18=0 ,sumr19=0 , sumr20=0 ;
	
	long cr=0 , ec=0, eo=0, er=0, kr=0, nc=0, ne=0 ,nf=0, nr=0 , nw=0 ,sb=0 ,sc=0,se=0, sr=0, sw=0 , wc=0 ,wr=0;
	private long outward_sum =0;
	private long oga_sum = 0;
    private long platform_sum=0;
	
	public long getPlatform_sum() {
		return platform_sum;
	}

	public void setPlatform_sum(long platform_sum) {
		this.platform_sum = platform_sum;
	}
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public long getSumr1() {
		return sumr1;
	}

	public void setSumr1(long sumr1) {
		this.sumr1 = sumr1;
	}

	public long getSumr2() {
		return sumr2;
	}
	long outwardsum=0;

	public long getOutwardsum() {
		return outwardsum;
	}

	public void setOutwardsum(long outwardsum) {
		this.outwardsum = outwardsum;
	}

	public void setSumr2(long sumr2) {
		this.sumr2 = sumr2;
	}

	public long getSumr3() {
		return sumr3;
	}

	public void setSumr3(long sumr3) {
		this.sumr3 = sumr3;
	}

	public long getSumr4() {
		return sumr4;
	}

	public void setSumr4(long sumr4) {
		this.sumr4 = sumr4;
	}

	public long getSumr5() {
		return sumr5;
	}

	public void setSumr5(long sumr5) {
		this.sumr5 = sumr5;
	}

	public long getSumr6() {
		return sumr6;
	}

	public void setSumr6(long sumr6) {
		this.sumr6 = sumr6;
	}

	public long getSumr7() {
		return sumr7;
	}

	public void setSumr7(long sumr7) {
		this.sumr7 = sumr7;
	}

	public long getSumr8() {
		return sumr8;
	}

	public void setSumr8(long sumr8) {
		this.sumr8 = sumr8;
	}

	public long getSumr9() {
		return sumr9;
	}

	public void setSumr9(long sumr9) {
		this.sumr9 = sumr9;
	}

	public long getSumr10() {
		return sumr10;
	}

	public void setSumr10(long sumr10) {
		this.sumr10 = sumr10;
	}

	public long getSumr11() {
		return sumr11;
	}

	public void setSumr11(long sumr11) {
		this.sumr11 = sumr11;
	}

	public long getSumr12() {
		return sumr12;
	}

	public void setSumr12(long sumr12) {
		this.sumr12 = sumr12;
	}

	public long getSumr13() {
		return sumr13;
	}

	public void setSumr13(long sumr13) {
		this.sumr13 = sumr13;
	}

	public long getSumr14() {
		return sumr14;
	}

	public void setSumr14(long sumr14) {
		this.sumr14 = sumr14;
	}

	public long getSumr15() {
		return sumr15;
	}

	public void setSumr15(long sumr15) {
		this.sumr15 = sumr15;
	}

	public long getSumr16() {
		return sumr16;
	}

	public void setSumr16(long sumr16) {
		this.sumr16 = sumr16;
	}

	public long getSumr17() {
		return sumr17;
	}

	public void setSumr17(long sumr17) {
		this.sumr17 = sumr17;
	}

	public long getSumr18() {
		return sumr18;
	}

	public void setSumr18(long sumr18) {
		this.sumr18 = sumr18;
	}

	public long getSumr19() {
		return sumr19;
	}

	public void setSumr19(long sumr19) {
		this.sumr19 = sumr19;
	}

	public long getSumr20() {
		return sumr20;
	}

	public void setSumr20(long sumr20) {
		this.sumr20 = sumr20;
	}
	long sum17;

	public long getCr() {
		return cr;
	}

	public void setCr(long cr) {
		this.cr = cr;
	}

	public long getEc() {
		return ec;
	}

	public void setEc(long ec) {
		this.ec = ec;
	}

	public long getEo() {
		return eo;
	}

	public void setEo(long eo) {
		this.eo = eo;
	}

	public long getEr() {
		return er;
	}

	public void setEr(long er) {
		this.er = er;
	}

	public long getKr() {
		return kr;
	}

	public void setKr(long kr) {
		this.kr = kr;
	}

	public long getNc() {
		return nc;
	}

	public void setNc(long nc) {
		this.nc = nc;
	}

	public long getNe() {
		return ne;
	}

	public void setNe(long ne) {
		this.ne = ne;
	}

	public long getNf() {
		return nf;
	}

	public void setNf(long nf) {
		this.nf = nf;
	}

	public long getNr() {
		return nr;
	}

	public void setNr(long nr) {
		this.nr = nr;
	}

	public long getNw() {
		return nw;
	}

	public void setNw(long nw) {
		this.nw = nw;
	}

	public long getSb() {
		return sb;
	}

	public void setSb(long sb) {
		this.sb = sb;
	}

	public long getSc() {
		return sc;
	}

	public void setSc(long sc) {
		this.sc = sc;
	}
	
	public long getSe() {
		return se;
	}

	public void setSe(long se) {
		this.se = se;
	}


	public long getSr() {
		return sr;
	}

	public void setSr(long sr) {
		this.sr = sr;
	}

	public long getSw() {
		return sw;
	}

	public void setSw(long sw) {
		this.sw = sw;
	}

	public long getWc() {
		return wc;
	}

	public void setWc(long wc) {
		this.wc = wc;
	}

	public long getWr() {
		return wr;
	}

	public void setWr(long wr) {
		this.wr = wr;
	}

	public String getFullZoneName() {
		return FullZoneName;
	}

	public void setFullZoneName(String fullZoneName) {
		FullZoneName = fullZoneName;
	}

	public ArrayList getRetainvalue() {
		return retainvalue;
	}

	public void setRetainvalue(ArrayList retainvalue) {
		this.retainvalue = retainvalue;
	}
	private String FullZoneName ;
	private long sum1;
	private long sum2;
	private long sum3;
	private long sum4;
	public long getSum1() {
		return sum1;
	}

	public void setSum1(long sum1) {
		this.sum1 = sum1;
	}

	public long getSum2() {
		return sum2;
	}

	public void setSum2(long sum2) {
		this.sum2 = sum2;
	}

	public long getSum3() {
		return sum3;
	}

	public void setSum3(long sum3) {
		this.sum3 = sum3;
	}

	public long getSum4() {
		return sum4;
	}

	public void setSum4(long sum4) {
		this.sum4 = sum4;
	}

	public long getSum5() {
		return sum5;
	}

	public void setSum5(long sum5) {
		this.sum5 = sum5;
	}

	public long getSum6() {
		return sum6;
	}

	public void setSum6(long sum6) {
		this.sum6 = sum6;
	}

	public long getSum7() {
		return sum7;
	}

	public void setSum7(long sum7) {
		this.sum7 = sum7;
	}

	public long getSum8() {
		return sum8;
	}

	public void setSum8(long sum8) {
		this.sum8 = sum8;
	}

	public long getSum9() {
		return sum9;
	}
	

	public void setSum9(long sum9) {
		this.sum9 = sum9;
	}

	public long getSum10() {
		return sum10;
	}

	public void setSum10(long sum10) {
		this.sum10 = sum10;
	}

	public long getSum11() {
		return sum11;
	}

	public void setSum11(long sum11) {
		this.sum11 = sum11;
	}

	public long getSum12() {
		return sum12;
	}

	public void setSum12(long sum12) {
		this.sum12 = sum12;
	}

	public long getSum13() {
		return sum13;
	}

	public void setSum13(long sum13) {
		this.sum13 = sum13;
	}

	public long getSum14() {
		return sum14;
	}

	public void setSum14(long sum14) {
		this.sum14 = sum14;
	}

	public long getSum15() {
		return sum15;
	}

	public void setSum15(long sum15) {
		this.sum15 = sum15;
	}

	public long getSum16() {
		return sum16;
	}

	public void setSum16(long sum16) {
		this.sum16 = sum16;
	}

	public long getSum17() {
		return sum17;
	}

	public void setSum17(long sum17) {
		this.sum17 = sum17;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	ArrayList retainvalue = null;
	private long sum5;
	private long sum6;
	private long sum7;
	private long sum8;
	private long sum9;
	private long sum10;
	private long sum11;
	private long sum12;
	private long sum13;
	private long sum14;
	private long sum15;
	private long sum16;
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

	public String getTamsmonth() {
		return tamsmonth;
	}

	public void setTamsmonth(String tamsmonth) {
		this.tamsmonth = tamsmonth;
	}

	public String getTamsproj() {
		return tamsproj;
	}

	public ArrayList<tamsbean> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<tamsbean> dataList) {
		this.dataList = dataList;
	}
	public void setTamsproj(String tamsproj) {
		this.tamsproj = tamsproj;
	}

	public String getTamsrptlist() {
		return tamsrptlist;
	}

	public void setTamsrptlist(String tamsrptlist) {
		this.tamsrptlist = tamsrptlist;
	}

	public String getTamsrlylist() {
		return tamsrlylist;
	}

	public void setTamsrlylist(String tamsrlylist) {
		this.tamsrlylist = tamsrlylist;
	}

	public String getTamsrpt() {
		return tamsrpt;
	}

	public void setTamsrpt(String tamsrpt) {
		this.tamsrpt = tamsrpt;
	}

	public String getGaugevalue() {
		return gaugevalue;
	}

	public void setGaugevalue(String gaugevalue) {
		this.gaugevalue = gaugevalue;
	}

	String tamsrpt;
	String gaugevalue;
	
	public ArrayList<tamsbean> getDataList_new() {
		return dataList_new;
	}

	public void setDataList_new(ArrayList<tamsbean> dataList_new) {
		this.dataList_new = dataList_new;
	}

	public long getOutward_sum() {
		return outward_sum;
	}

	public void setOutward_sum(long outward_sum) {
		this.outward_sum = outward_sum;
	}

	public long getOga_sum() {
		return oga_sum;
	}

	public void setOga_sum(long oga_sum) {
		this.oga_sum = oga_sum;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}	
	
	
	
	
	
	
//------------------------------------------------------------------------------------------------	
	
	
	
	
	

	
	
public String execute() throws Exception{
		
		//System.out.println("zonename in session"+session.getAttribute("zone_name"));
		
		
		System.out.println("11111111111111111111111"+zone_name);
		String result = null;
		System.out.println("report type is "+tamsproj);
		System.out.println("report name is"+tamsrptlist);
	if(tamsproj.equalsIgnoreCase("PRS"))
		{
		  if(tamsrptlist.equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE")){
			  
			 result= viewcateringchgzonewisetrainwise();
			  
		  }
		  
		  
		 if(tamsrptlist.equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE")){
			  
				 result= prsstationnotintrainroute();
				  
			  }
			
		 if(tamsrptlist.equalsIgnoreCase("PRS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS")){
			  
			 result= prsmatchunmatchreport();
			  
		  }
		 
			
		 if(tamsrptlist.equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT")){
			  System.out.println("inside report");
			 result= Prscateringmatrix();
			  
		  }
		 
		 if(tamsrptlist.equalsIgnoreCase("PRS DISTANCE BREAKUP")){
			  System.out.println("inside report");
			// result= Prscateringmatrix();
			  
		  }
		 
		 
		 if(tamsrptlist.equalsIgnoreCase("PRS Apportionment Report")){
			  System.out.println("inside report");
			 result= apportionmentmatrix();
			  
		  }
		 

		 if(tamsrptlist.equalsIgnoreCase("PRS PRTNFILE REPORT")){
			  System.out.println("inside report");
			 result= PRS_PRTNFILE_REPORT();
			  
		  }
		 
		 
			
		}
	
	
	
	
		else{
			
			
			if(tamsrptlist.equalsIgnoreCase("UTS DETAILS OF MUTP/MMTS/CIDCO/MRTS,TC/TTE,PLATFORM etc. FOR ALL INDIAN RAILWAYS")){
				  
				 result= utsdetailsofmutpplateform();
				  
			} //UTS Unmatched OD pairs with Via, Distance and Full Fare
			
			
			if(tamsrptlist.equalsIgnoreCase("UTS Unmatched OD pairs with Via, Distance and Full Fare")){
				  
				 result= utsunmatchedodpairs();
				  
			}
			
			
			if(tamsrptlist.equalsIgnoreCase("UTS Apportionment Report")){
				  
				 result= UtsApportionmentMatrix();
				  
			} 
			
			 if(tamsrptlist.equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Originating)")){
				  
				 result= UtsmatchUnmatchreport();
				  
			  }
			 
			 
			 if(tamsrptlist.equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Booking)")){
				  
				 result= UtsBookingwisematchUnmatchreport();
				  
			  }
			 
			 if(tamsrptlist.equalsIgnoreCase("UTS PRTNFILE REPORT")){
				  System.out.println("inside report");
				 result= UTS_PRTNFILE_REPORT();
				  
			  }
			
		}
		
		return result;
	}


/// UTS Reports

public String UtsBookingwisematchUnmatchreport() throws Exception{
		
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
	int month_1 = Integer.parseInt(month);
	int year_1 = Integer.parseInt(year);
	
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println("inside UtsmatchUnmatchreport method");
		dataList=new ArrayList<tamsbean>();
		Statement stmt = con.createStatement();
		String query="select a.zone rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
				+ "b.total_records Match_total,b.full_fare Match_fullfare,b.safety_charge Match_safetychg,b.other_charge Match_otherchg,   "
				+ "c.total_records Unmatch_total,c.full_fare Unmatch_fullfare,c.safety_charge Unmatch_safetychg,c.other_charge Unmatch_otherchg  "
				+ "from  "
				+ "(select zone,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge   "
				+ "from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" group by zone order by zone)  a ,   "
				+ "(select zone,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge  "
				+ "from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag = 'Y' group by zone  "
				+ "union   "
				+ "select distinct zone,0 total_Records, 0 full_fare,0 safety_charge,0 other_charge from tams_uts  where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and zone not  in   "
				+ "(select  distinct zone from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag = 'Y' ) order by zone)  b,  "
				+ "(select zone,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge  "
				+ "from tams_uts  where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag is null or apprcnf_flag not in ('Y') group by zone   "
				+ "union  "
				+ "select distinct zone,0 total_Records, 0 full_fare,0 safety_charge,0 other_charge from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and zone not  in  "
				+ "(select  distinct zone from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag is null or apprcnf_flag not in ('Y') ) order by zone)  c "
				+ "where a.zone = b.zone and b.zone = c.zone";
		
		
		
	      System.out.println("strQuery >>>"+query);
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				tamsbean dataBean = new tamsbean();
				//System.out.println("hellooooooo"+rs.getString(2));
				//dataBean.setName(rs.getString("2"));
				dataBean.setRly(rs.getString("rly"));
				dataBean.setItotalrecords(rs.getString("Input_total"));
				sum1=sum1+Long.parseLong(rs.getString("Input_total"));
				dataBean.setIfare(rs.getString("Input_fullfare"));
				sum2=sum2+Long.parseLong(rs.getString("Input_fullfare"));
				dataBean.setIsafetycharges(rs.getString("Input_safetychg"));
				sum3=sum3+Long.parseLong(rs.getString("Input_safetychg"));
				dataBean.setIothercharges(rs.getString("Input_otherchg"));
				sum4=sum4+Long.parseLong(rs.getString("Input_otherchg"));
				dataBean.setMtotalrecords(rs.getString("Match_total"));
				sum5=sum5+Long.parseLong(rs.getString("Match_total"));
				dataBean.setMfare(rs.getString("Match_fullfare"));
				sum6=sum6+Long.parseLong(rs.getString("Match_fullfare"));
				dataBean.setMsafetycharges(rs.getString("Match_safetychg"));
				sum7=sum7+Long.parseLong(rs.getString("Match_safetychg"));
				dataBean.setMothercharges(rs.getString("Match_otherchg"));
				sum8=sum8+Long.parseLong(rs.getString("Match_otherchg"));
				dataBean.setUtotalrecords(rs.getString("Unmatch_total"));
				sum9=sum9+Long.parseLong(rs.getString("Unmatch_total"));
				dataBean.setUfare(rs.getString("Unmatch_fullfare"));
				sum10=sum10+Long.parseLong(rs.getString("Unmatch_fullfare"));
				dataBean.setUsafetycharges(rs.getString("Unmatch_safetychg"));
				sum11=sum11+Long.parseLong(rs.getString("Unmatch_safetychg"));
				dataBean.setUothercharges(rs.getString("Unmatch_otherchg"));
				sum12=sum12+Long.parseLong(rs.getString("Unmatch_otherchg"));
				dataList.add(dataBean);
		
			}
		con.close();
		return "successfound";
	}


public String utsunmatchedodpairs() throws Exception{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	String zone=getTamsrlylist();
	ExportPdf epdf =new  ExportPdf();
     FullZoneName=epdf.fullnameofzone(zone);
     
	int month_1 = Integer.parseInt(month);
	int year_1 = Integer.parseInt(year);
	
	tamsdbconnection dbcon =new tamsdbconnection();
	con=dbcon.getconnect();
	System.out.println();
	dataList=new ArrayList<tamsbean>();
	
	
	Statement stmt = con.createStatement();
	String query=" select station_from,station_from_code,station_upto,station_to_code,via,distance , sum(full_fare)  from tams_uts where mm_bkg_date ="+month+" and yy_bkg_date = "+year
			+ "and apprcnf_flag is null and orgrly = '"+zone+"' group by station_from,station_from_code , station_upto,station_to_code,via,distance  "
			+ "order by station_from,station_upto ";
	
	
	System.out.println("strQuery >>>"+query);
	ResultSet rs = stmt.executeQuery(query);
	String rlyzone;
	HttpServletRequest req = ServletActionContext.getRequest();
	session = req.getSession();
	session.setAttribute("resultset",rs);
	while (rs.next()) {
	
	tamsbean dataBean = new tamsbean();
	//dataBean.setTrainno(rs.getString("trainno"));
	dataBean.setFromStn(rs.getString("station_from"));
	dataBean.setFromstncode(rs.getString("station_from_code"));
	dataBean.setTostn(rs.getString("station_upto"));
	dataBean.setTostncode(rs.getString("station_to_code"));
	dataBean.setEntstn(rs.getString("via"));
	dataBean.setDist(rs.getString("distance"));
	dataBean.setFare(rs.getString("sum(full_fare)"));
	
	sumr1 = sumr1 + Long.parseLong(rs.getString("sum(full_fare)"));
	//dataBean.setFromstncode(rs.getString(3));
	dataList.add(dataBean);
	
	
	
	}
	
	
	con.close();
	return "successget";
}


	
public String utsdetailsofmutpplateform() throws Exception{

		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println();
		dataList=new ArrayList<tamsbean>();
		
		
		Statement stmt = con.createStatement();
String query=" select p.orgrly orgrly,q.TC_TTE TC_TTE,q.platform platform,q.reservation_slip reservation_slip,q.superfast superfast,q.tourist tourist,q.parking parking,  "
			+ "p.mutp_charges mutp_charges,p.mrts_charges mrts_charges,p.cidco_charges cidco_charges,p.mmts_charges mmts_charges  "
			+ "from  "
			+ "(select orgrly,round(sum(mutp_charges/1000),0) mutp_charges,round(sum(mrts_charges/1000),0) mrts_charges,  "
			+ "round(sum(cidco_charges/1000),0) cidco_charges,round(sum(mmts_charges/1000),0) mmts_charges  "
			+ "from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "group by orgrly) P ,  "
			+ "(select a.orgrly orgrly,a.total TC_TTE,b.total platform,c.total reservation_slip,d.total superfast,e.total tourist,f.total parking  "
			+ "from  "
			+ "( "
			+ "select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and   "
			+ "(type_code = '2951' or type_code = '1951') group by orgrly "
			+ "union "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not in  "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "and (type_code = '2951' or type_code = '1951'))) a, "
			+ "(select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "and  type_code = '9911' group by orgrly "
			+ "union "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not in  "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9911')) b, "
			+ "(select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "and  type_code = '9922' group by orgrly  "
			+ "union   "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not in   "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9922')) c,  "
			+ "(select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"   "
			+ "and type_code = '9933' group by orgrly "
			+ "union "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not in  "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and  type_code = '9933')) d, "
			+ "(select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "and type_code = '9944' group by orgrly "
			+ "union "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not in "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9944')) e,"
			+ "(select orgrly,round(sum(full_fare)/1000,0) total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+"  "
			+ "and type_code = '9955' group by orgrly  "
			+ "union  "
			+ "select distinct orgrly,0 total from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and  orgrly not in   "
			+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9955')) f   "
			+ "where a.orgrly =b.orgrly and  b.orgrly =c.orgrly and c.orgrly =d.orgrly and d.orgrly =e.orgrly and e.orgrly =f.orgrly  "
			+ ") q  "
			+ "where q.orgrly = p.orgrly   "
			+ "order by p.orgrly  "; 
		
		System.out.println("strQuery >>>"+query);
		ResultSet rs = stmt.executeQuery(query);
		String rlyzone;
		HttpServletRequest req = ServletActionContext.getRequest();
		session = req.getSession();
		session.setAttribute("resultset",rs);
		while (rs.next()) {
		
		tamsbean dataBean = new tamsbean();
		//tamsbean dataBean_new = new tamsbean();
		rlyzone=rs.getString("orgrly");
        dataBean.setZone(rs.getString("orgrly"));
        dataBean.setTc_tte(rs.getString("TC_TTE"));
        sumr1=sumr1+Long.parseLong(rs.getString("TC_TTE"));
        dataBean.setPlatform(rs.getString("PLATFORM"));
        sumr2=sumr2+Long.parseLong(rs.getString("PLATFORM"));
        dataBean.setReservation_slip(rs.getString("RESERVATION_SLIP"));
        sumr3=sumr3+Long.parseLong(rs.getString("RESERVATION_SLIP"));
        dataBean.setSuperfast(rs.getString("SUPERFAST"));
        sumr4=sumr4+Long.parseLong(rs.getString("SUPERFAST"));
        dataBean.setTourist(rs.getString("TOURIST"));
        sumr5=sumr5+Long.parseLong(rs.getString("TOURIST"));
        dataBean.setParking(rs.getString("PARKING"));
        sumr6=sumr6+Long.parseLong(rs.getString("PARKING"));
        dataBean.setMutp_chg(rs.getString("MUTP_CHARGES"));
        sumr7=sumr7+Long.parseLong(rs.getString("MUTP_CHARGES"));
        dataBean.setMrts_chg(rs.getString("MRTS_CHARGES"));
        sumr8=sumr8+Long.parseLong(rs.getString("MRTS_CHARGES"));
        dataBean.setCidco_chg(rs.getString("CIDCO_CHARGES"));
        sumr9=sumr9+Long.parseLong(rs.getString("CIDCO_CHARGES"));
        dataBean.setMmts_chg(rs.getString("MMTS_CHARGES"));
        sumr10=sumr10+Long.parseLong(rs.getString("MMTS_CHARGES"));
        dataList.add(dataBean);
        
        
		
		}
		con.close();
		return "getsuccess";
		
	}
	
	
public String UtsApportionmentMatrix() throws Exception{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
	
	
	int month_1 = Integer.parseInt(month);
	int year_1 = Integer.parseInt(year);
	
	tamsdbconnection dbcon =new tamsdbconnection();
	con=dbcon.getconnect();
	System.out.println();
	dataList=new ArrayList<tamsbean>();
	retainvalue=new ArrayList();
	ArrayList<String> crvalue=new ArrayList<String>();
	ArrayList<String> ecvalue=new ArrayList<String>();
	ArrayList<String> eovalue=new ArrayList<String>();
	ArrayList<String> ervalue=new ArrayList<String>();
	ArrayList<String> krvalue=new ArrayList<String>();
	ArrayList<String> ncvalue=new ArrayList<String>();
	ArrayList<String> nevalue=new ArrayList<String>();
	ArrayList<String> nfvalue=new ArrayList<String>();
	ArrayList<String> nrvalue=new ArrayList<String>();
	ArrayList<String> nwvalue=new ArrayList<String>();
	ArrayList<String> sbvalue=new ArrayList<String>();
	ArrayList<String> scvalue=new ArrayList<String>();
	ArrayList<String> sevalue=new ArrayList<String>();
	ArrayList<String> srvalue=new ArrayList<String>();
	ArrayList<String> swvalue=new ArrayList<String>();
	ArrayList<String> wcvalue=new ArrayList<String>();
	ArrayList<String> wrvalue=new ArrayList<String>();
	//ArrayList crvalue=new ArrayList();
	int i=0;
	
	//String filename = "D://TAMSJASPERREPORTS//"+a+" "+b+" "+d+" "+e+".xls";
	
						Statement stmt = con.createStatement();
	
						String query=" select a.orgrly orgrly,a.cr cr, a.ec ec, a.eo eo, a.er er, a.kr kr, a.nc nc, a.ne ne, a.nf nf, a.nr nr, a.nw nw, a.sb sb, a.sc sc, a.se se, a.sr sr, "
								+ "a.sw sw, a.wc wc,a.wr wr, b.platform platform, a.OGA + b.platform OGA, a.RETAINED RETAINED, a.Outward Outward "
								+ " from   "
								+ "(select orgrly,round(sum(crapva/1000),0) cr,round(sum(ecrapva/1000),0) ec,round(sum(ecorapva/1000),0) eo,round(sum(erapva/1000),0) er ,round(sum(krapva/1000),0) kr,   "
								+ "round(sum(ncrapva/1000),0) nc,round(sum(nerapva/1000),0) ne,round(sum(nfrapva/1000),0) nf,  round(sum(nrapva/1000),0) nr,round(sum(nwrapva/1000),0) nw,   "
								+ "round(sum(secrapva/1000),0) sb,round(sum(scrapva/1000),0) sc,round(sum(serapva/1000),0) se,round(sum(srapva/1000),0) sr,round(sum(swrapva/1000),0) sw,   "
								+ "round(sum(wcrapva/1000),0) wc,round(sum(wrapva/1000),0) wr,   "
								+ "round(sum(crapva/1000),0) + round(sum(ecrapva/1000),0) + round(sum(ecorapva/1000),0)+ round(sum(erapva/1000),0) + round(sum(krapva/1000),0) +  "
								+ "round(sum(ncrapva/1000),0) + round(sum(nerapva/1000),0)+ round(sum(nfrapva/1000),0) +  round(sum(nrapva/1000),0) + round(sum(nwrapva/1000),0) +   "
								+ "round(sum(secrapva/1000),0) + round(sum(scrapva/1000),0) + round(sum(serapva/1000),0) + round(sum(srapva/1000),0) + round(sum(swrapva/1000),0) +   "
								+ "round(sum(wcrapva/1000),0)+ round(sum(wrapva/1000),0) OGA,  "
								+ "round(sum(RETAINED_AMOUNT/1000),0) RETAINED,   "
								+ "round(sum(crapva/1000),0) + round(sum(ecrapva/1000),0) + round(sum(ecorapva/1000),0)+ round(sum(erapva/1000),0) + round(sum(krapva/1000),0) +   "
								+ "round(sum(ncrapva/1000),0) + round(sum(nerapva/1000),0)+ round(sum(nfrapva/1000),0) +  round(sum(nrapva/1000),0) + round(sum(nwrapva/1000),0) +   "
								+ "round(sum(secrapva/1000),0) + round(sum(scrapva/1000),0) + round(sum(serapva/1000),0) + round(sum(srapva/1000),0) + round(sum(swrapva/1000),0) +  "
								+ "round(sum(wcrapva/1000),0)+ round(sum(wrapva/1000),0)  - round(sum(RETAINED_AMOUNT/1000),0) Outward   "
								+ "from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code not in ('9911') group by orgrly) a,    "
								+ "((select orgrly,round(sum(full_fare)/1000,0) Platform from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9911' group by orgrly   "
								+ "union  "
								+ " select distinct orgrly,0 total from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and  orgrly not in    "
								+ "(select distinct orgrly from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9911')) ) b    "
								+ "  where a.orgrly = b.orgrly order by a.orgrly ";
	
						String rlyzone;
						System.out.println("strQuery >>>"+query);
						ResultSet rs = stmt.executeQuery(query);
						
						System.out.println("After result set strQuery >>>"+query);
						System.out.println("After result set strQuery >>>"+rs);
						
						
						while (rs.next()) {
							
							
							
    					tamsbean dataBean = new tamsbean();
    					//tamsbean dataBean_new = new tamsbean();
    					rlyzone=rs.getString("orgrly");
                        dataBean.setZone(rs.getString("orgrly"));
    					dataBean.setCR(rs.getString("CR"));
    					crvalue.add(i, rs.getString("CR"));
    		 			
    					dataBean.setEC(rs.getString("EC"));
                        ecvalue.add(i, rs.getString("EC"));
    					dataBean.setEO(rs.getString("EO"));
    					  eovalue.add(i, rs.getString("EO"));
    					dataBean.setER(rs.getString("ER"));
    					 ervalue.add(i, rs.getString("ER"));
    					dataBean.setKR(rs.getString("KR"));
   					    krvalue.add(i, rs.getString("KR"));
    					dataBean.setNC(rs.getString("NC"));
   					    ncvalue.add(i, rs.getString("NC"));
    					dataBean.setNE(rs.getString("NE"));
   					    nevalue.add(i, rs.getString("NE"));
    					dataBean.setNF(rs.getString("NF"));
   					    nfvalue.add(i, rs.getString("NF"));
    					dataBean.setNR(rs.getString("NR"));
    					nrvalue.add(i, rs.getString("NR"));
    					dataBean.setNW(rs.getString("NW"));
    					nwvalue.add(i , rs.getString("Nw"));
    					dataBean.setSB(rs.getString("SB"));
    					sbvalue.add(i, rs.getString("SB"));
    					dataBean.setSC(rs.getString("SC"));
    					scvalue.add(i, rs.getString("SC"));
    					dataBean.setSE(rs.getString("SE"));
    					sevalue.add(i, rs.getString("SE"));
    					dataBean.setSR(rs.getString("SR"));
    					srvalue.add(i, rs.getString("SR"));
    					dataBean.setSW(rs.getString("SW"));
    					swvalue.add(i, rs.getString("SW"));
   					    
    					dataBean.setWC(rs.getString("WC"));
    					wcvalue.add(i, rs.getString("WC"));
    					dataBean.setWR(rs.getString("WR"));
    					wrvalue.add(i, rs.getString("WR"));
    					
    					dataBean.setPlatform(rs.getString("Platform"));
    					platform_sum = platform_sum + Integer.parseInt(rs.getString("Platform")) ;
    					
    					dataBean.setOga(rs.getString("OGA"));
    					oga_sum = oga_sum + Integer.parseInt(rs.getString("OGA")) ;
    					
    					dataBean.setOutward(Integer.parseInt(rs.getString("Outward")));
    					outward_sum = outward_sum + Integer.parseInt(rs.getString("Outward")) ;
    					
    					dataBean.setRetained(rs.getInt("RETAINED"));
    					retainvalue.add(i, rs.getInt("RETAINED"));
    					   //dataBean_new.setRetained_new(rs.getInt("RETAINED"));
    			
    					 i++ ;  
    					dataList.add(dataBean);
    					//System.out.println("end of while");
    					}
tamsbean tmb = new tamsbean();
tmb.setDatastored(dataList);


for(int z=0 ;z<crvalue.size() ;z++)
{
	sumr1=sumr1+Integer.parseInt(crvalue.get(z));
	sumr2=sumr2+Integer.parseInt (ecvalue.get(z));
	sumr3=sumr3+Integer.parseInt (eovalue.get(z));
	sumr4=sumr4+Integer.parseInt (ervalue.get(z));
	sumr5=sumr5+Integer.parseInt (krvalue.get(z));
	sumr6=sumr6+Integer.parseInt (ncvalue.get(z));
	sumr7=sumr7+Integer.parseInt (nevalue.get(z));
	sumr8=sumr8+Integer.parseInt (nfvalue.get(z));
	sumr9=sumr9+Integer.parseInt (nrvalue.get(z));
	sumr10=sumr10+Integer.parseInt (nwvalue.get(z));
	sumr11=sumr11+Integer.parseInt (sbvalue.get(z));
	sumr12=sumr12+Integer.parseInt (scvalue.get(z));
	sumr13=sumr13+Integer.parseInt (sevalue.get(z));
	sumr14=sumr14+Integer.parseInt (srvalue.get(z));
	sumr15=sumr15+Integer.parseInt (swvalue.get(z));
	sumr16=sumr16+Integer.parseInt (wcvalue.get(z));
	sumr17=sumr17+Integer.parseInt (wrvalue.get(z));
	sumr18=sumr18+(Integer)retainvalue.get(z);
	
	
	
	
}


cr =sumr1-(Integer) retainvalue.get(0);
ec =sumr2-(Integer)retainvalue.get(1);
eo =sumr3-(Integer)retainvalue.get(2);
er =sumr4-(Integer)retainvalue.get(3);
kr =sumr5-(Integer)retainvalue.get(4);
nc =sumr6-(Integer)retainvalue.get(5);
ne =sumr7-(Integer)retainvalue.get(6);
nf =sumr8-(Integer)retainvalue.get(7);
nr =sumr9-(Integer)retainvalue.get(8);
nw =sumr10-(Integer)retainvalue.get(9);
sb =sumr11-(Integer)retainvalue.get(10);
sc =sumr12-(Integer)retainvalue.get(11);
se =sumr13-(Integer)retainvalue.get(12);
System.out.println("SE value " + se);
sr =sumr14-(Integer)retainvalue.get(13);
sw =sumr15-(Integer)retainvalue.get(14);
wc =sumr16-(Integer)retainvalue.get(15);
wr =sumr17-(Integer)retainvalue.get(16);

sumr19 = sumr1 +sumr2 +sumr3+sumr4+sumr5+sumr6+sumr7+sumr8+sumr9+sumr10+sumr11+sumr12+sumr13+sumr14+sumr15+sumr16+sumr17 ;

sumr20 = sumr19 - sumr18;
con.close();
	return "successviewed";
}
	
	
public String UtsmatchUnmatchreport() throws Exception{
		
		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println("inside UtsmatchUnmatchreport method");
		dataList=new ArrayList<tamsbean>();
		Statement stmt = con.createStatement();
		String query="select a.orgrly rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
				+ "b.total_records Match_total,b.full_fare Match_fullfare,b.safety_charge Match_safetychg,b.other_charge Match_otherchg,   "
				+ "c.total_records Unmatch_total,c.full_fare Unmatch_fullfare,c.safety_charge Unmatch_safetychg,c.other_charge Unmatch_otherchg  "
				+ "from  "
				+ "(select orgrly,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge   "
				+ "from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" group by orgrly order by orgrly)  a ,   "
				+ "(select orgrly,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge  "
				+ "from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag = 'Y' group by orgrly  "
				+ "union   "
				+ "select distinct orgrly,0 total_Records, 0 full_fare,0 safety_charge,0 other_charge from tams_uts  where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not  in   "
				+ "(select  distinct orgrly from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag = 'Y' ) order by orgrly)  b,  "
				+ "(select orgrly,count(*) total_Records, sum(full_fare) full_fare,sum(safety) safety_charge,sum(other_charge) other_charge  "
				+ "from tams_uts  where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag is null or apprcnf_flag not in ('Y') group by orgrly   "
				+ "union  "
				+ "select distinct orgrly,0 total_Records, 0 full_fare,0 safety_charge,0 other_charge from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly not  in  "
				+ "(select  distinct orgrly from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and apprcnf_flag is null or apprcnf_flag not in ('Y') ) order by orgrly)  c "
				+ "where a.orgrly = b.orgrly and b.orgrly = c.orgrly   ";
		
		
		
	      System.out.println("strQuery >>>"+query);
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				tamsbean dataBean = new tamsbean();
				//System.out.println("hellooooooo"+rs.getString(2));
				//dataBean.setName(rs.getString("2"));
				dataBean.setRly(rs.getString("rly"));
				dataBean.setItotalrecords(rs.getString("Input_total"));
				sum1=sum1+Long.parseLong(rs.getString("Input_total"));
				dataBean.setIfare(rs.getString("Input_fullfare"));
				sum2=sum2+Long.parseLong(rs.getString("Input_fullfare"));
				dataBean.setIsafetycharges(rs.getString("Input_safetychg"));
				sum3=sum3+Long.parseLong(rs.getString("Input_safetychg"));
				dataBean.setIothercharges(rs.getString("Input_otherchg"));
				sum4=sum4+Long.parseLong(rs.getString("Input_otherchg"));
				dataBean.setMtotalrecords(rs.getString("Match_total"));
				sum5=sum5+Long.parseLong(rs.getString("Match_total"));
				dataBean.setMfare(rs.getString("Match_fullfare"));
				sum6=sum6+Long.parseLong(rs.getString("Match_fullfare"));
				dataBean.setMsafetycharges(rs.getString("Match_safetychg"));
				sum7=sum7+Long.parseLong(rs.getString("Match_safetychg"));
				dataBean.setMothercharges(rs.getString("Match_otherchg"));
				sum8=sum8+Long.parseLong(rs.getString("Match_otherchg"));
				dataBean.setUtotalrecords(rs.getString("Unmatch_total"));
				sum9=sum9+Long.parseLong(rs.getString("Unmatch_total"));
				dataBean.setUfare(rs.getString("Unmatch_fullfare"));
				sum10=sum10+Long.parseLong(rs.getString("Unmatch_fullfare"));
				dataBean.setUsafetycharges(rs.getString("Unmatch_safetychg"));
				sum11=sum11+Long.parseLong(rs.getString("Unmatch_safetychg"));
				dataBean.setUothercharges(rs.getString("Unmatch_otherchg"));
				sum12=sum12+Long.parseLong(rs.getString("Unmatch_otherchg"));
				dataList.add(dataBean);
		
			}
			con.close();
		return "successedfounded";
	}
	


public String UTS_PRTNFILE_REPORT() throws Exception 
{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
	int month_1 = Integer.parseInt(month);
	int year_1 = Integer.parseInt(year);
	
	
	
	tamsdbconnection dbcon =new tamsdbconnection();
	con=dbcon.getconnect();
	System.out.println();
	String zone=getTamsrlylist();
	dataList=new ArrayList<tamsbean>();
	ExportPdf epdf =new  ExportPdf();
    FullZoneName=epdf.fullnameofzone(zone);
	Statement stmt = con.createStatement();
	

	String strQuery = " select b.orgrly orgrly , "
			+ "X.dstnrly dstnrly,X.FROM_GUAGE FROM_GUAGE,  X.Records Records, X.Amount Amount,X.CR CR, X.EC EC, X.EO EO,X.ER ER, X.KR KR, "
			+ "X.NC NC, X.NE NE, X.NF NF, X.NR NR, X.NW NW,X.SB SB, X.SC SC, X.SE SE, X.SR SR, X.SW SW,X.WC WC, X.WR WR, "
			+ "X.Total_shared Total_shared,X.Diff Diff "
			+ "from  "
			+ "( "
			+ "select  "
			+ "a.dstnrly dstnrly , a.FROM_GUAGE FROM_GUAGE,sum(a.Records) Records,  sum(a.Amount) Amount,sum(a.CR) CR, sum(a.EC) EC, sum(a.EO) EO, "
			+ "sum(a.ER) ER, sum(a.KR) KR,sum(a.NC) NC, sum(a.NE) NE, sum(a.NF) NF, sum(a.NR) NR, sum(a.NW) NW,sum(a.SB) SB, sum(a.SC) SC, "
			+ "sum(a.SE) SE, sum(a.SR) SR, sum(a.SW) SW,sum(a.WC) WC, sum(a.WR) WR, "
			+ "sum(a.Total_shared) Total_shared, sum(a.Diff) Diff "
			+ "from "
			+ "( "
			+ "select dstnrly ,  "
			+ "case when (FROM_GUAGE not in ('B','M','N') or FROM_GUAGE is null) then 'Other'  "
			+ "else FROM_GUAGE end FROM_GUAGE ,   "
			+ "count(*) Records,round(sum(full_fare),0) Amount, "
			+ "round(sum(CRAPVA),0) CR,round(sum(ECRAPVA),0) EC,round(sum(ECORAPVA),0) EO,round(sum(ERAPVA),0) ER,round(sum(KRAPVA),0) KR, "
			+ "round(sum(NCRAPVA),0)NC,round(sum(NERAPVA),0) NE,round(sum(NFRAPVA),0) NF,round(sum(NRAPVA),0) NR,round(sum(NWRAPVA),0) NW, "
			+ "round(sum(SECRAPVA),0) SB,round(sum(SCRAPVA),0) SC,round(sum(SERAPVA),0) SE,round(sum(SRAPVA),0) SR,round(sum(SWRAPVA),0) SW, "
			+ "round(sum(WCRAPVA),0) WC,round(sum(WRAPVA),0) WR, "
			+ "round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) Total_shared, "
			+ "round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) Diff "
			+ "from tams_uts  "
			+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and  orgrly = '"+tamsrlylist+"'  and type_code not in ('9911') "
			+ "group by dstnrly ,FROM_GUAGE  "
			+ "union "
			+ "select distinct dstnrly,FROM_GUAGE,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 "
			+ "from TAMSUTSPRNTFILE m where  "
			+ "not exists (select * from  "
			+ "(select * from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+tamsrlylist+"' and type_code not in ('9911') ) n  "
			+ "where m.dstnrly = n.dstnrly and m.from_guage = n.from_guage) "
			+ "UNION  "
			+ "select '_TOTAL' as dstnrly , 'B' as FROM_GUAGE , count(*) Records, "
			+ "case when round(sum(full_fare),0) is null then 0 else round(sum(full_fare),0) end Amount, "
			+ "case when round(sum(CRAPVA),0) is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when round(sum(ECRAPVA),0) is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when round(sum(ECORAPVA),0) is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when round(sum(ERAPVA),0) is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when round(sum(KRAPVA),0) is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when round(sum(NCRAPVA),0) is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when round(sum(NERAPVA),0) is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when round(sum(NFRAPVA),0) is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when round(sum(NRAPVA),0) is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when round(sum(NWRAPVA),0) is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when round(sum(SECRAPVA),0) is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when round(sum(SCRAPVA),0) is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when round(sum(SERAPVA),0) is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when round(sum(SRAPVA),0) is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when round(sum(SWRAPVA),0) is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when round(sum(WCRAPVA),0) is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when round(sum(WRAPVA),0) is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Diff "
			+ "from tams_uts  "
			+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+tamsrlylist+"'  and type_code not in ('9911') "
			+ "and FROM_GUAGE in ('B')  "
			+ "UNION  "
			+ "select '_TOTAL' as dstnrly , 'M' as FROM_GUAGE , count(*) Records, "
			+ "case when round(sum(full_fare),0) is null then 0 else round(sum(full_fare),0) end Amount, "
			+ "case when round(sum(CRAPVA),0) is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when round(sum(ECRAPVA),0) is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when round(sum(ECORAPVA),0) is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when round(sum(ERAPVA),0) is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when round(sum(KRAPVA),0) is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when round(sum(NCRAPVA),0) is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when round(sum(NERAPVA),0) is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when round(sum(NFRAPVA),0) is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when round(sum(NRAPVA),0) is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when round(sum(NWRAPVA),0) is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when round(sum(SECRAPVA),0) is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when round(sum(SCRAPVA),0) is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when round(sum(SERAPVA),0) is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when round(sum(SRAPVA),0) is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when round(sum(SWRAPVA),0) is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when round(sum(WCRAPVA),0) is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when round(sum(WRAPVA),0) is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Diff "
			+ "from tams_uts  "
			+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+tamsrlylist+"'  and type_code not in ('9911') "
			+ "and FROM_GUAGE in ('M')  "
			+ "UNION  "
			+ "select '_TOTAL' as dstnrly ,'N' as  FROM_GUAGE , count(*) Records, "
			+ "case when round(sum(full_fare),0) is null then 0 else round(sum(full_fare),0) end Amount, "
			+ "case when round(sum(CRAPVA),0) is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when round(sum(ECRAPVA),0) is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when round(sum(ECORAPVA),0) is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when round(sum(ERAPVA),0) is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when round(sum(KRAPVA),0) is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when round(sum(NCRAPVA),0) is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when round(sum(NERAPVA),0) is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when round(sum(NFRAPVA),0) is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when round(sum(NRAPVA),0) is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when round(sum(NWRAPVA),0) is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when round(sum(SECRAPVA),0) is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ " case when round(sum(SCRAPVA),0) is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when round(sum(SERAPVA),0) is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when round(sum(SRAPVA),0) is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when round(sum(SWRAPVA),0) is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when round(sum(WCRAPVA),0) is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when round(sum(WRAPVA),0) is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Diff "
			+ "from tams_uts  "
			+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+tamsrlylist+"'  and type_code not in ('9911') "
			+ "and FROM_GUAGE in ('N') "
			+ "UNION  "
			+ "select '_TOTAL' as dstnrly ,'Other' as  FROM_GUAGE , count(*) Records, "
			+ "case when round(sum(full_fare),0) is null then 0 else round(sum(full_fare),0) end Amount, "
			+ "case when round(sum(CRAPVA),0) is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when round(sum(ECRAPVA),0) is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when round(sum(ECORAPVA),0) is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when round(sum(ERAPVA),0) is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when round(sum(KRAPVA),0) is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when round(sum(NCRAPVA),0) is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when round(sum(NERAPVA),0) is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when round(sum(NFRAPVA),0) is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when round(sum(NRAPVA),0) is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when round(sum(NWRAPVA),0) is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when round(sum(SECRAPVA),0) is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when round(sum(SCRAPVA),0) is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when round(sum(SERAPVA),0) is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when round(sum(SRAPVA),0) is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when round(sum(SWRAPVA),0) is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when round(sum(WCRAPVA),0) is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when round(sum(WRAPVA),0) is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) is null then 0  "
			+ "else round(sum(full_fare),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Diff "
			+ "from tams_uts  "
			+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+tamsrlylist+"'  and type_code not in ('9911') "
			+ "and (FROM_GUAGE not in ('B','M','N') or FROM_GUAGE is null) "
			+ ") a "
			+ "group by a.dstnrly , a.FROM_GUAGE  "
			+ ") X,  "
			+ "(select '"+tamsrlylist+"' as orgrly from dual ) b  "
			+ "order by X.dstnrly ,X.FROM_GUAGE ";

	
System.out.println("strQuery >>>"+strQuery);
	
	ResultSet rs = stmt.executeQuery(strQuery);
	 long Sum_array[] []=new long[18][21];
	
	for(int i =0; i<18 ; i++)
	{
		for(int j =0; j<21 ; j++)
		{
			Sum_array[i][j] = 0;
		}
	}
	
	
	int k =0;
	int m =0;
	
	while (rs.next()) {
		tamsbean dataBean = new tamsbean();
		System.out.println("m is"+m);
		
		
		if((m%4)==0  && m!=0)
		{
			tamsbean dataBean1 = new tamsbean();
			

			System.out.println("k is"+k);
			
			dataBean1.setTorly("");
			dataBean1.setOrgg("TOT CRDT");
			
			dataBean1.setAmount(String.valueOf(Sum_array[k][1]));
			dataBean1.setCR(String.valueOf(Sum_array[k][2]));
			dataBean1.setEC(String.valueOf(Sum_array[k][3]));
			dataBean1.setEO(String.valueOf(Sum_array[k][4]));
			dataBean1.setER(String.valueOf(Sum_array[k][5]));
			dataBean1.setKR(String.valueOf(Sum_array[k][6]));
			dataBean1.setNC(String.valueOf(Sum_array[k][7]));
			dataBean1.setNE(String.valueOf(Sum_array[k][8]));
			dataBean1.setNF(String.valueOf(Sum_array[k][9]));
			dataBean1.setNR(String.valueOf(Sum_array[k][10]));
			dataBean1.setNW(String.valueOf(Sum_array[k][11]));
			dataBean1.setSB(String.valueOf(Sum_array[k][12]));
			dataBean1.setSC(String.valueOf(Sum_array[k][13]));
			dataBean1.setSE(String.valueOf(Sum_array[k][14]));
			dataBean1.setSR(String.valueOf(Sum_array[k][15]));
			dataBean1.setSW(String.valueOf(Sum_array[k][16]));
			dataBean1.setWC(String.valueOf(Sum_array[k][17]));
			dataBean1.setWR(String.valueOf(Sum_array[k][18]));
			dataBean1.setTotal_shared(String.valueOf(Sum_array[k][19]));
			dataBean1.setDiff(String.valueOf(Sum_array[k][20]));
			dataList.add(dataBean1);
			
			
		}
		
		
		
		
		
		if((m%4)==0) dataBean.setTorly(rs.getString("dstnrly")); 
		else dataBean.setTorly("");
		
		
		
		
		dataBean.setOrgg(rs.getString("FROM_GUAGE"));
		dataBean.setRecords(rs.getString("Records"));
		dataBean.setAmount(rs.getString("Amount"));
		dataBean.setCR(rs.getString("CR"));
		dataBean.setEC(rs.getString("EC"));
		dataBean.setEO(rs.getString("EO"));
		dataBean.setER(rs.getString("ER"));
		dataBean.setKR(rs.getString("KR"));
		dataBean.setNC(rs.getString("NC"));
		dataBean.setNE(rs.getString("NE"));
		dataBean.setNF(rs.getString("NF"));
		dataBean.setNR(rs.getString("NR"));
		dataBean.setNW(rs.getString("NW"));
		dataBean.setSB(rs.getString("SB"));
		dataBean.setSC(rs.getString("SC"));
		dataBean.setSE(rs.getString("SE"));
		dataBean.setSR(rs.getString("SR"));
		dataBean.setSW(rs.getString("SW"));
		dataBean.setWC(rs.getString("WC"));
		dataBean.setWR(rs.getString("WR"));
		dataBean.setTotal_shared(rs.getString("Total_shared"));
		dataBean.setDiff(rs.getString("Diff"));
		
		dataList.add(dataBean);
		
		
		
		
		
		if((m%4)==0  && m!=0)
		{
			
			k++;
			System.out.println("k is"+k);
		}
		
		
		
		
		Sum_array[k][0] = Sum_array[k][0] + Long.parseLong(rs.getString("Records")) ;
		Sum_array[k][1] = Sum_array[k][1] + Long.parseLong(rs.getString("Amount")) ;
		Sum_array[k][2] = Sum_array[k][2] + Long.parseLong(rs.getString("CR")) ;
		Sum_array[k][3] = Sum_array[k][3] + Long.parseLong(rs.getString("EC")) ;
		Sum_array[k][4] = Sum_array[k][4] + Long.parseLong(rs.getString("EO")) ;
		Sum_array[k][5] = Sum_array[k][5] + Long.parseLong(rs.getString("ER")) ;
		Sum_array[k][6] = Sum_array[k][6] + Long.parseLong(rs.getString("KR")) ;
		Sum_array[k][7] = Sum_array[k][7] + Long.parseLong(rs.getString("NC")) ;
		Sum_array[k][8] = Sum_array[k][8] + Long.parseLong(rs.getString("NE")) ;
		Sum_array[k][9] = Sum_array[k][9] + Long.parseLong(rs.getString("NF")) ;
		Sum_array[k][10] = Sum_array[k][10] + Long.parseLong(rs.getString("NR")) ;
		Sum_array[k][11] = Sum_array[k][11] + Long.parseLong(rs.getString("NW")) ;
		Sum_array[k][12] = Sum_array[k][12] + Long.parseLong(rs.getString("SB")) ;
		Sum_array[k][13] = Sum_array[k][13] + Long.parseLong(rs.getString("SC")) ;
		Sum_array[k][14] = Sum_array[k][14] + Long.parseLong(rs.getString("SE")) ;
		Sum_array[k][15] = Sum_array[k][15] + Long.parseLong(rs.getString("SR")) ;
		Sum_array[k][16] = Sum_array[k][16] + Long.parseLong(rs.getString("SW")) ;
		Sum_array[k][17] = Sum_array[k][17] + Long.parseLong(rs.getString("WC")) ;
		Sum_array[k][18] = Sum_array[k][18] + Long.parseLong(rs.getString("WR")) ;
		Sum_array[k][19] = Sum_array[k][19] + Long.parseLong(rs.getString("Total_shared")) ;
		Sum_array[k][20] = Sum_array[k][20] + Long.parseLong(rs.getString("Diff")) ;
		System.out.println("total shared"+ Sum_array[k][19]);
		
		
		
		
		
		
		
		//m++;
		++m;
		
		
	}
	
	
	tamsbean dataBean1 = new tamsbean();
	
	dataBean1.setTorly("");
	dataBean1.setOrgg("TOT CRDT");
	
	dataBean1.setAmount(String.valueOf(Sum_array[k][1]));
	dataBean1.setCR(String.valueOf(Sum_array[k][2]));
	dataBean1.setEC(String.valueOf(Sum_array[k][3]));
	dataBean1.setEO(String.valueOf(Sum_array[k][4]));
	dataBean1.setER(String.valueOf(Sum_array[k][5]));
	dataBean1.setKR(String.valueOf(Sum_array[k][6]));
	dataBean1.setNC(String.valueOf(Sum_array[k][7]));
	dataBean1.setNE(String.valueOf(Sum_array[k][8]));
	dataBean1.setNF(String.valueOf(Sum_array[k][9]));
	dataBean1.setNR(String.valueOf(Sum_array[k][10]));
	dataBean1.setNW(String.valueOf(Sum_array[k][11]));
	dataBean1.setSB(String.valueOf(Sum_array[k][12]));
	dataBean1.setSC(String.valueOf(Sum_array[k][13]));
	dataBean1.setSE(String.valueOf(Sum_array[k][14]));
	dataBean1.setSR(String.valueOf(Sum_array[k][15]));
	dataBean1.setSW(String.valueOf(Sum_array[k][16]));
	dataBean1.setWC(String.valueOf(Sum_array[k][17]));
	dataBean1.setWR(String.valueOf(Sum_array[k][18]));
	dataBean1.setTotal_shared(String.valueOf(Sum_array[k][19]));
	dataBean1.setDiff(String.valueOf(Sum_array[k][20]));
	dataList.add(dataBean1);
	
	
	
	
	for(int i =0; i<18 ; i++)
	{
		for(int j =0; j<21 ; j++)
		{
			System.out.print(Sum_array[i][j] +" "); 
		}
		System.out.println("");
	}
	con.close();
	//dataBean.setAr(dataList);
	return "success_UTS_PRTN";

}






	
/// PRS Reports 
	
	
/*public String Prscateringmatrix() throws Exception 
	{
		
		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		String rlyzone=null;
		tamsdbconnection dbcon =new tamsdbconnection();
		retainvalue=new ArrayList();
		//tamsbean dataBean = new tamsbean();
		con=dbcon.getconnect();
		System.out.println();
		dataList=new ArrayList<tamsbean>();
		//dataoutwardList=new ArrayList<tamsbean>();
		
		Statement stmt = con.createStatement();
		System.out.println("statement create"+stmt);
		
		
	//	String Query = "select a.ownrly, a.catchg CR,b.catchg EC,c.catchg EO,d.catchg ER,e.catchg KR,f.catchg NC,g.catchg NE,h.catchg NF,i.catchg NR,j.catchg NW,k.catchg SB, l.catchg SC,m.catchg SE,n.catchg SR,o.catchg SW,p.catchg WC,q.catchg WR,r.catchg Retained, ((a.catchg + b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  + m.catchg  +n.catchg  +o.catchg  +p.catchg  + q.catchg ) - r.catchg) inward, (a.catchg +b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  +m.catchg + n.catchg  +o.catchg  +p.catchg  + q.catchg ) Total from  (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'CR' group by ownrly order by ownrly ) a, (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EC' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'EC')) b, (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EO' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'EO')) c, (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'ER' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'ER')) d, (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'KR' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'KR')) e,(select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NC' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'NC')) f,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NE' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NE')) g,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NF' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NF')) h,(select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NR' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'NR')) i,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NW' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NW')) j,(select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SB' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SB')) k,(select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SC' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'SC')) l,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SE' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SE')) m,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SR' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SR')) n,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SW' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'SW')) o,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WC' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'WC')) p,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WR' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'WR')) q,(select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'CR' and ownrly='CR' group by ownrly order by ownrly) union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EC' and ownrly='EC' group by ownrly order by ownrly) union  select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EO' and ownrly='EO' group by ownrly order by ownrly) union  select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'ER' and ownrly='ER' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'KR' and ownrly='KR' group by ownrly order by ownrly) union  select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NC' and ownrly='NC' group by ownrly order by ownrly) union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NE' and ownrly='NE' group by ownrly order by ownrly) union  select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NF' and ownrly='NF' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NR' and ownrly='NR' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NW' and ownrly='NW' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SB' and ownrly='SB' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SC' and ownrly='SC' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SE' and ownrly='SE' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SR' and ownrly='SR' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SW' and ownrly='SW' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WC' and ownrly='WC' group by ownrly order by ownrly)union select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WR' and ownrly='WR' group by ownrly order by ownrly)) rWhere a.ownrly = b.ownrly and b.ownrly = c.ownrly and c.ownrly = d.ownrly and d.ownrly = e.ownrly and e.ownrly = f.ownrly and f.ownrly = g.ownrly and g.ownrly = h.ownrly and h.ownrly = i.ownrly and i.ownrly = j.ownrly and j.ownrly = k.ownrly and k.ownrly = l.ownrly and l.ownrly = m.ownrly and m.ownrly = n.ownrly and n.ownrly = o.ownrly and o.ownrly = p.ownrly and p.ownrly = q.ownrly and q.ownrly = r.ownrly";			
		String Query=" select a.orgrly orgrly , a.ownrly ownrly , a.catchg catchg "
				   + "from "
				   + "( "
				   + "select orgrly,ownrly, round(sum(to_number(na)),0) catchg  "
				   + "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'   "
				   + "group by orgrly,ownrly "
				   + "union "
				   + "select distinct orgrly,torly,0 as catchg  "
				   + "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and (orgrly,torly) not in  "
				   + "(select distinct orgrly,ownrly from tams_prs where  mm = '"+month+"' and  yr = '"+year+"') "
				   + ") a "
				   + "order by a.orgrly,a.ownrly "  ;
		
		
		
		
		System.out.println("strQuery >>>"+Query);
	        ResultSet rs = stmt.executeQuery(Query);
	         while (rs.next()) {
	        					tamsbean dataBean = new tamsbean();
	        					 rlyzone=rs.getString("OWNRLY");
	                           dataBean.setZone(rs.getString("OWNRLY"));
	                         
	                          
	        					dataBean.setCR(rs.getString("CR"));
	        					 sumr1=sumr1+Long.parseLong(rs.getString("CR"));
	        					dataBean.setEC(rs.getString("EC"));
	        					 sumr2=sumr2+Long.parseLong(rs.getString("EC"));
	        					dataBean.setEO(rs.getString("EO"));
	       					    sumr3=sumr3+Long.parseLong(rs.getString("EO"));
	        					dataBean.setER(rs.getString("ER"));
	       					    sumr4=sumr4+Long.parseLong(rs.getString("ER"));
	        					dataBean.setKR(rs.getString("KR"));
	       					    sumr5=sumr5+Long.parseLong(rs.getString("KR"));
	        					dataBean.setNC(rs.getString("NC"));
	       					    sumr6=sumr6+Long.parseLong(rs.getString("NC"));
	        					dataBean.setNE(rs.getString("NE"));
	       					    sumr7=sumr7+Long.parseLong(rs.getString("NE"));
	        					dataBean.setNF(rs.getString("NF"));
	       					    sumr8=sumr8+Long.parseLong(rs.getString("NF"));
	        					dataBean.setNR(rs.getString("NR"));
	        					int q=Integer.parseInt(rs.getString("NR"));
	       					    sumr9=sumr9+Long.parseLong(rs.getString("NR"));
	        					dataBean.setNW(rs.getString("NW"));
	        					int r=Integer.parseInt(rs.getString("NW"));
	       					    sumr10=sumr10+Long.parseLong(rs.getString("NW"));
	        					dataBean.setSB(rs.getString("SB"));
	        					int s=Integer.parseInt(rs.getString("SB"));
	       					    sumr11=sumr11+Long.parseLong(rs.getString("SB"));
	        					dataBean.setSC(rs.getString("SC"));
	        					int t=Integer.parseInt(rs.getString("SC"));
	       					    sumr12=sumr12+Long.parseLong(rs.getString("SC"));
	        					dataBean.setSE(rs.getString("SE"));
	        					int u=Integer.parseInt(rs.getString("SE"));
	       					    sumr13=sumr13+Long.parseLong(rs.getString("SE"));
	        					
	        					dataBean.setSR(rs.getString("SR"));
	        					int v=Integer.parseInt(rs.getString("SR"));
	       					    sumr14=sumr14+Long.parseLong(rs.getString("SR"));
	        					dataBean.setSW(rs.getString("SW"));
	        					int w=Integer.parseInt(rs.getString("SW"));
	       					    sumr15=sumr15+Long.parseLong(rs.getString("SW"));
	        					dataBean.setWC(rs.getString("WC"));
	        					int x=Integer.parseInt(rs.getString("WC"));
	       					    sumr16=sumr16+Long.parseLong(rs.getString("WC"));
	        					dataBean.setWR(rs.getString("WR"));
	        					int y=Integer.parseInt(rs.getString("WR"));
	       					    sumr17=sumr17+Long.parseLong(rs.getString("WR"));
	        					dataBean.setRetained(rs.getInt("RETAINED"));
	        					sumr18=sumr18+Long.parseLong(rs.getString("RETAINED"));
	        					retainvalue.add(i ,rs.getInt("RETAINED"));
	        					++i;
	        					System.out.println("retained value is");
	        					dataBean.setInward(rs.getInt("INWARD"));
	        					
	        					
	        					sumr19=sumr19+Long.parseLong(rs.getString("INWARD"));
	        					dataBean.setTotalappotioned(rs.getInt("TOTAL"));
	        					sumr20=sumr20+Long.parseLong(rs.getString("TOTAL"));
	        					dataList.add(dataBean);
	        					
	        					
	        					
	        					
	        					
			       
		
	}
	   System.out.println("retained value is"+retainvalue);   
	   System.out.println("retained array length is "+retainvalue.size());
	   tamsbean tmb = new tamsbean();
	   tmb.setDatastored(dataList);
	   
	   
	   




	 for(int aa=0; aa<=retainvalue.size() ; aa++){
	     
	       switch(aa)
	       {                                   //CR
						       case 0:{
						    	   int a1=(Integer) retainvalue.get(aa);
						    	   sum1=sumr1 - a1;
						    	   outwardsum=outwardsum+sum1;
						           System.out.println("cr sum value aayi"+sumr1); 
						           break;
						       }
						       //EC
						       case 1:{
						
						           int b= (Integer) retainvalue.get(aa);
						           sum2=sumr2 - b; 
						           outwardsum=outwardsum+sum2;
						           System.out.println("outward of of WR"+sumr2);
						           break;
						       }
						       //EO
						       case 2:{
						
						           int b= (Integer) retainvalue.get(aa);
						           sum3=sumr3 - b; 
						           outwardsum=outwardsum+sum3;
						           System.out.println("outward of of WR"+sumr3);
						           break;
						       }
						       //ER
						       case 3:{
						
						           int b= (Integer) retainvalue.get(aa);
						           sum4=sumr4 - b; 
						           outwardsum=outwardsum+sum4;
						           System.out.println("outward of of WR"+sumr4);
						           break;
						       }
						       //KR
						       case 4:{
						
						           int b= (Integer) retainvalue.get(aa);
						           sum5=sumr5 - b; 
						           outwardsum=outwardsum+sum5;
						           System.out.println("outward of of WR"+sumr5);
						           break;
						       }
						       //NC
						       case 5:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum6=sumr6 - b; 
						           outwardsum=outwardsum+sum6;
						           System.out.println("outward of of WR"+sumr6);
						           break;
						       }//NE
						       case 6:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum7=sumr7 - b; 
						           outwardsum=outwardsum+sum7;
						           System.out.println("outward of of WR"+sumr7);
						           break;
						       }
						       
						       //NF
						       case 7:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum8=sumr8 - b; 
						           outwardsum=outwardsum+sum8;
						           System.out.println("outward of of WR"+sumr8);
						           break;
						       }
						       //NR
						       case 8:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum9=sumr9 - b; 
						           outwardsum=outwardsum+sum9;
						           System.out.println("outward of of WR"+sumr9);
						           break;
						       }
						       //NW
						       case 9:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum10=sumr10 - b; 
						           outwardsum=outwardsum+sum10;
						           System.out.println("outward of of WR"+sumr10);
						           break;
						       }
						       //SB
						       case 10:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum11=sumr11 - b; 
						           outwardsum=outwardsum+sum11;
						           System.out.println("outward of of WR"+sumr11);
						           break;
						       }
						       //SC
						       case 11:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum12=sumr12 - b; 
						           outwardsum=outwardsum+sum12;
						           System.out.println("outward of of WR"+sumr12);
						           break;
						       }
						       //SE
						       case 12:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum13=sumr13 - b; 
						           outwardsum=outwardsum+sum13;
						           System.out.println("outward of of WR"+sumr13);
						           break;
						       }
						       //SR
						       case 13:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum14=sumr14 - b; 
						           outwardsum=outwardsum+sum14;
						           System.out.println("outward of of WR"+sumr14);
						           break;
						       }
						       //SW
						       case 14:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum15=sumr15 - b;
						           outwardsum=outwardsum+sum15;
						           System.out.println("outward of of WR"+sumr15);
						           break;
						       }
						       //WC
						       case 15:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum16=sumr16 - b; 
						           outwardsum=outwardsum+sum16;
						           System.out.println("outward of of WR"+sumr16);
						           
						           break;
						       }
						       //wr
						       case 16:{
									
						           int b= (Integer) retainvalue.get(aa);
						           sum17=sumr17 - b;
						           outwardsum=outwardsum+sum17;
						           System.out.println("outward of of WR"+sumr17);
						           break;
						       }
						       
						     
						     
	     }
	       System.out.println("sum value aayi"+sumr1); 
		   







	  }
	  
	             
	          
	               int b= (Integer) retainvalue.get(1);
	               sumr2=sumr2 - b;
	               int c= (Integer) retainvalue.get(2);
	               sumr3=sumr3 - c;
	               int d= (Integer) retainvalue.get(3);
	               sumr4=sumr4 - d;
	               int e= (Integer) retainvalue.get(4);
	               sumr5=sumr5- e;
	               int f= (Integer) retainvalue.get(5);
	               sumr6=sumr6 - f;
	               int g= (Integer) retainvalue.get(6);
	               sumr7=sumr7 - g;
	               int h= (Integer) retainvalue.get(7);
	               sumr8=sumr8 - h;
	               int j= (Integer) retainvalue.get(8);
	               sumr9=sumr9 - j;
	               int k= (Integer) retainvalue.get(9);
	               sumr10=sumr10 - k;
	               int l= (Integer) retainvalue.get(10);
	               sumr11=sumr11 - l;
	               int m= (Integer) retainvalue.get(11);
	               sumr12=sumr12 - m;
	               int n= (Integer) retainvalue.get(12);
	               sumr13=sumr13 - n;
	               int o= (Integer) retainvalue.get(13);
	               sumr14=sumr14 - o;
	               int p= (Integer) retainvalue.get(14);
	               sumr15=sumr15 - p;
	               int q= (Integer) retainvalue.get(15);
	               sumr16=sumr16 - q;
	               int r= (Integer) retainvalue.get(16);
	               sumr17=sumr17 - r;
	               System.out.println("sum value aayi"+sumr17);
	        	 
	               con.close();
	        return "successsfind";
	        
		}
*/

public String Prscateringmatrix() throws Exception 
{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
	
	String rlyzone=null;
	tamsdbconnection dbcon =new tamsdbconnection();
	retainvalue=new ArrayList();
	//tamsbean dataBean = new tamsbean();
	con=dbcon.getconnect();
	System.out.println();
	dataList=new ArrayList<tamsbean>();
	//dataoutwardList=new ArrayList<tamsbean>();
	
	Statement stmt = con.createStatement();
	System.out.println("statement create"+stmt);
	
	

	String Query="select orgrly,ownrly, round(sum(to_number(na)),0) catchg  from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'   "
			+ "group by orgrly,ownrly union select distinct orgrly,ownrly,0 as catchg  from TAMSPRSOWNRLYORGRLY where  (orgrly,ownrly) not in  "
			+ "(select distinct orgrly,ownrly from tams_prs where  mm = '"+month+"' and  yr = '"+year+"') "
			+ "order by orgrly,ownrly"  ;
     
     System.out.println("quer is"+Query);
     ResultSet rs = stmt.executeQuery(Query);
     
     String[][] dbvalue = new String[24][21];
     for (int r=0; r< 24; r++) {
	     for (int q=0; q<21; q++) {
	    	dbvalue[r][q]  = "0";
	     }
	 }
     
     int i=0;
     int j=0;
     int m=0;
     while(rs.next()){
		 
			 if(m%17==0)
    	 dbvalue[i][0]= rs.getString("ORGRLY");//your value
			
     	 dbvalue[j][i+1]= rs.getString("CATCHG");
     	 j++;
     	 
     	 m++;
     	
     	 if(m%17==0){
     		 j=0;
     		 i++;
     		
     	 }
     	 
    	 
     }
     //Retained value
     int comm=0;
	 for (int r=0; r< 17; r++) {
		 
		 dbvalue[r][18]=dbvalue[comm][comm+1];
		 comm++;
		 
	 }
	//Total apportionment 
	 for(int rowname=0 ; rowname<17; rowname++ ){
		 for(int colname=1 ; colname<18; colname++ ){
		 dbvalue[rowname][20]=String.valueOf(Long.parseLong(dbvalue[rowname][20])+Long.parseLong(dbvalue[rowname][colname]));
		 }
	 }
 
//INWARD SHARE	 
for (int r=0; r< 17; r++) {
		 
		 dbvalue[r][19]=String.valueOf(Long.parseLong(dbvalue[r][20]) - Long.parseLong(dbvalue[r][18]));
		
		 
	 }
	//Unmatch share
dbvalue[17][0]="Unmatch Share";
for(int row=0 ; row<17 ;row++){
	
	 dbvalue[17][18]=String.valueOf(Long.parseLong(dbvalue[17][18]) + Long.parseLong(dbvalue[row][18]));
	 dbvalue[17][19]=String.valueOf(Long.parseLong(dbvalue[17][19]) + Long.parseLong(dbvalue[row][19]));
	 dbvalue[17][20]=String.valueOf(Long.parseLong(dbvalue[17][20]) + Long.parseLong(dbvalue[row][20]));
	}

	
//Originating Amount
//dbvalue[18][0]=null;
dbvalue[18][0]="Originating";
for(int row=0 ; row<17 ;row++){
	for (int col=1; col< 18; col++) {
		 dbvalue[18][col]=String.valueOf(Long.parseLong(dbvalue[18][col]) + Long.parseLong(dbvalue[row][col]));
		} 
	 }
for(int col=1; col<18 ;col++)
{
	dbvalue[18][20]=String.valueOf(Long.parseLong(dbvalue[18][20])+Long.parseLong(dbvalue[18][col]));
}


//retained horizontal
dbvalue[20][0]="Retained ";
for(int var=0 ;var<17 ; var++){
	dbvalue[20][var+1]=dbvalue[var][18];
}
dbvalue[20][20]=dbvalue[17][18];

//Outward Share
dbvalue[19][0]="Outward Share ";
//  dbvalue[19][0]="Outward Share";
for(int var=1 ;var<18 ; var++){
	dbvalue[19][var]=String.valueOf(Long.parseLong(dbvalue[18][var])-Long.parseLong(dbvalue[20][var]));
}

for(int col=1; col<18 ;col++)
{
	dbvalue[19][20]=String.valueOf(Long.parseLong(dbvalue[19][20])+Long.parseLong(dbvalue[19][col]));
}

//INward Horizontal
dbvalue[21][0]="Inward";
for(int var=0 ;var<17 ; var++){
	dbvalue[21][var+1]=dbvalue[var][19];
}
dbvalue[21][20]=dbvalue[17][19];

dbvalue[22][0]="Tot. appor. Earnings";
for(int var=0 ;var<17 ; var++){
	dbvalue[22][var+1]=dbvalue[var][20];
}
dbvalue[22][20]=dbvalue[17][20];



     
   for (int r=0; r<23; r++) {
	     for (int q=0; q<21; q++) {
	    	 System.out.print(dbvalue[r][q] + " ");
	         
	     }
	     System.out.println();
	 }
	for(int row=0 ; row<23 ;row++){
       tamsbean dataBean1 = new tamsbean();
	
		dataBean1.setZone(String.valueOf(dbvalue[row][0]));
		dataBean1.setCR(String.valueOf(dbvalue[row][1]));
		dataBean1.setEC(String.valueOf(dbvalue[row][2]));
		dataBean1.setEO(String.valueOf(dbvalue[row][3]));
		dataBean1.setER(String.valueOf(dbvalue[row][4]));
		dataBean1.setKR(String.valueOf(dbvalue[row][5]));
		dataBean1.setNC(String.valueOf(dbvalue[row][6]));
		dataBean1.setNE(String.valueOf(dbvalue[row][7]));
		dataBean1.setNF(String.valueOf(dbvalue[row][8]));
		dataBean1.setNR(String.valueOf(dbvalue[row][9]));
		dataBean1.setNW(String.valueOf(dbvalue[row][10]));
		dataBean1.setSB(String.valueOf(dbvalue[row][11]));
		dataBean1.setSC(String.valueOf(dbvalue[row][12]));
		dataBean1.setSE(String.valueOf(dbvalue[row][13]));
		dataBean1.setSR(String.valueOf(dbvalue[row][14]));
		dataBean1.setSW(String.valueOf(dbvalue[row][15]));
		dataBean1.setWC(String.valueOf(dbvalue[row][16]));
		dataBean1.setWR(String.valueOf(dbvalue[row][17]));
		dataBean1.setRetained(Integer.parseInt(dbvalue[row][18]));
		dataBean1.setInward(Integer.parseInt(String.valueOf(dbvalue[row][19])));
		dataBean1.setTotalappotioned(Integer.parseInt(String.valueOf(dbvalue[row][20])));
		dataList.add(dataBean1);
	}
	
	return "successsfind";
        
	}

public String apportionmentmatrix() throws Exception{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
		
	
	tamsdbconnection dbcon =new tamsdbconnection();
	con=dbcon.getconnect();
	System.out.println();
	dataList=new ArrayList<tamsbean>();
	retainvalue=new ArrayList();
	ArrayList<String> crvalue=new ArrayList<String>();
	ArrayList<String> ecvalue=new ArrayList<String>();
	ArrayList<String> eovalue=new ArrayList<String>();
	ArrayList<String> ervalue=new ArrayList<String>();
	ArrayList<String> krvalue=new ArrayList<String>();
	ArrayList<String> ncvalue=new ArrayList<String>();
	ArrayList<String> nevalue=new ArrayList<String>();
	ArrayList<String> nfvalue=new ArrayList<String>();
	ArrayList<String> nrvalue=new ArrayList<String>();
	ArrayList<String> nwvalue=new ArrayList<String>();
	ArrayList<String> sbvalue=new ArrayList<String>();
	ArrayList<String> scvalue=new ArrayList<String>();
	ArrayList<String> sevalue=new ArrayList<String>();
	ArrayList<String> srvalue=new ArrayList<String>();
	ArrayList<String> swvalue=new ArrayList<String>();
	ArrayList<String> wcvalue=new ArrayList<String>();
	ArrayList<String> wrvalue=new ArrayList<String>();
	//ArrayList crvalue=new ArrayList();
	int i=0;
	
	//String filename = "D://TAMSJASPERREPORTS//"+a+" "+b+" "+d+" "+e+".xls";
	
	Statement stmt = con.createStatement();
	
	
	String query="select orgrly,round(sum(crapva/1000),0) cr,round(sum(ecrapva/1000),0) ec,round(sum(ecorapva/1000),0) eo,round(sum(erapva/1000),0) er ,"
			+ "round(sum(krapva/1000),0) kr,round(sum(ncrapva/1000),0) nc,round(sum(nerapva/1000),0) ne,round(sum(nfrapva/1000),0) nf, round(sum(nrapva/1000),0) nr,"
			+ "round(sum(nwrapva/1000),0) nw,round(sum(secrapva/1000),0) sb,round(sum(scrapva/1000),0) sc,round(sum(serapva/1000),0) se,round(sum(srapva/1000),0) sr,"
			+ "round(sum(swrapva/1000),0) sw,round(sum(wcrapva/1000),0) wc,round(sum(wrapva/1000),0) wr,"
			+ "round(sum(crapva/1000),0) + round(sum(ecrapva/1000),0) + round(sum(ecorapva/1000),0)+ round(sum(erapva/1000),0) + round(sum(krapva/1000),0) +"
			+ " round(sum(ncrapva/1000),0) + round(sum(nerapva/1000),0)+ round(sum(nfrapva/1000),0) +  round(sum(nrapva/1000),0) + round(sum(nwrapva/1000),0) + "
			+ "round(sum(secrapva/1000),0) + round(sum(scrapva/1000),0) + round(sum(serapva/1000),0) + round(sum(srapva/1000),0) + round(sum(swrapva/1000),0) + "
			+ "round(sum(wcrapva/1000),0)+ round(sum(wrapva/1000),0) OGA,"
			+ "round(sum(RETAINED_AMOUNT/1000),0) RETAINED,"
			+ "round(sum(crapva/1000),0) + round(sum(ecrapva/1000),0) + round(sum(ecorapva/1000),0)+ round(sum(erapva/1000),0) + round(sum(krapva/1000),0) + "
			+ "round(sum(ncrapva/1000),0) + round(sum(nerapva/1000),0)+ round(sum(nfrapva/1000),0) +  round(sum(nrapva/1000),0) + round(sum(nwrapva/1000),0) + "
			+ "round(sum(secrapva/1000),0) + round(sum(scrapva/1000),0) + round(sum(serapva/1000),0) + round(sum(srapva/1000),0) + round(sum(swrapva/1000),0) + "
			+ "round(sum(wcrapva/1000),0)+ round(sum(wrapva/1000),0) -"
			+ "round(sum(RETAINED_AMOUNT/1000),0) Outward "
			+ " from tams_prs  where mm = '"+month+"' and yr = '"+year+"'   "
			+ "group by orgrly "
			+ " order by orgrly";
	
	String rlyzone;
	
	System.out.println("strQuery >>>"+query);
    ResultSet rs = stmt.executeQuery(query);
     while (rs.next()) {
    					tamsbean dataBean = new tamsbean();
    					tamsbean dataBean_new = new tamsbean();
    					rlyzone=rs.getString("orgrly");
                        dataBean.setZone(rs.getString("orgrly"));
    					dataBean.setCR(rs.getString("CR"));
    					crvalue.add(i, rs.getString("CR"));
    					
    					dataBean.setEC(rs.getString("EC"));
                        ecvalue.add(i, rs.getString("EC"));
    					dataBean.setEO(rs.getString("EO"));
    					  eovalue.add(i, rs.getString("EO"));
    					dataBean.setER(rs.getString("ER"));
    					 ervalue.add(i, rs.getString("ER"));
    					dataBean.setKR(rs.getString("KR"));
   					    krvalue.add(i, rs.getString("KR"));
    					dataBean.setNC(rs.getString("NC"));
   					    ncvalue.add(i, rs.getString("NC"));
    					dataBean.setNE(rs.getString("NE"));
   					    nevalue.add(i, rs.getString("NE"));
    					dataBean.setNF(rs.getString("NF"));
   					    nfvalue.add(i, rs.getString("NF"));
    					dataBean.setNR(rs.getString("NR"));
    					nrvalue.add(i, rs.getString("NR"));
    					dataBean.setNW(rs.getString("NW"));
    					nwvalue.add(i , rs.getString("Nw"));
    					dataBean.setSB(rs.getString("SB"));
    					sbvalue.add(i, rs.getString("SB"));
    					dataBean.setSC(rs.getString("SC"));
    					scvalue.add(i, rs.getString("SC"));
    					dataBean.setSE(rs.getString("SE"));
    					sevalue.add(i, rs.getString("SE"));
    					dataBean.setSR(rs.getString("SR"));
    					srvalue.add(i, rs.getString("SR"));
    					dataBean.setSW(rs.getString("SW"));
    					swvalue.add(i, rs.getString("SW"));
   					    
    					dataBean.setWC(rs.getString("WC"));
    					wcvalue.add(i, rs.getString("WC"));
    					dataBean.setWR(rs.getString("WR"));
    					wrvalue.add(i, rs.getString("WR"));
    					
    					dataBean.setOga(rs.getString("OGA"));
    					oga_sum = oga_sum + Integer.parseInt(rs.getString("OGA")) ;
    					
    					dataBean.setOutward(Integer.parseInt(rs.getString("Outward")));
    					outward_sum = outward_sum + Integer.parseInt(rs.getString("Outward")) ;
    					
    					dataBean.setRetained(rs.getInt("RETAINED"));
    					retainvalue.add(i, rs.getInt("RETAINED"));
    					   //dataBean_new.setRetained_new(rs.getInt("RETAINED"));
    			
    					 i++ ;  
    					dataList.add(dataBean);
    					//System.out.println("end of while");
    					}
tamsbean tmb = new tamsbean();
tmb.setDatastored(dataList);


for(int z=0 ;z<crvalue.size() ;z++)
{
	sumr1=sumr1+Integer.parseInt(crvalue.get(z));
	sumr2=sumr2+Integer.parseInt (ecvalue.get(z));
	sumr3=sumr3+Integer.parseInt (eovalue.get(z));
	sumr4=sumr4+Integer.parseInt (ervalue.get(z));
	sumr5=sumr5+Integer.parseInt (krvalue.get(z));
	sumr6=sumr6+Integer.parseInt (ncvalue.get(z));
	sumr7=sumr7+Integer.parseInt (nevalue.get(z));
	sumr8=sumr8+Integer.parseInt (nfvalue.get(z));
	sumr9=sumr9+Integer.parseInt (nrvalue.get(z));
	sumr10=sumr10+Integer.parseInt (nwvalue.get(z));
	sumr11=sumr11+Integer.parseInt (sbvalue.get(z));
	sumr12=sumr12+Integer.parseInt (scvalue.get(z));
	sumr13=sumr13+Integer.parseInt (sevalue.get(z));
	sumr14=sumr14+Integer.parseInt (srvalue.get(z));
	sumr15=sumr15+Integer.parseInt (swvalue.get(z));
	sumr16=sumr16+Integer.parseInt (wcvalue.get(z));
	sumr17=sumr17+Integer.parseInt (wrvalue.get(z));
	sumr18=sumr18+(Integer)retainvalue.get(z);
	
	
	
	
}


cr =sumr1-(Integer) retainvalue.get(0);
ec =sumr2-(Integer)retainvalue.get(1);
eo =sumr3-(Integer)retainvalue.get(2);
er =sumr4-(Integer)retainvalue.get(3);
kr =sumr5-(Integer)retainvalue.get(4);
nc =sumr6-(Integer)retainvalue.get(5);
ne =sumr7-(Integer)retainvalue.get(6);
nf =sumr8-(Integer)retainvalue.get(7);
nr =sumr9-(Integer)retainvalue.get(8);
nw =sumr10-(Integer)retainvalue.get(9);
sb =sumr11-(Integer)retainvalue.get(10);
sc =sumr12-(Integer)retainvalue.get(11);
se =sumr13-(Integer)retainvalue.get(12);
System.out.println("SE value " + se);
sr =sumr14-(Integer)retainvalue.get(13);
sw =sumr15-(Integer)retainvalue.get(14);
wc =sumr16-(Integer)retainvalue.get(15);
wr =sumr17-(Integer)retainvalue.get(16);

sumr19 = sumr1 +sumr2 +sumr3+sumr4+sumr5+sumr6+sumr7+sumr8+sumr9+sumr10+sumr11+sumr12+sumr13+sumr14+sumr15+sumr16+sumr17 ;

sumr20 = sumr19 - sumr18;
con.close();
return "successview";
}
		
	
public String viewcateringchgzonewisetrainwise() throws Exception 
	{
		
		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println();
		dataList=new ArrayList<tamsbean>();
		
		//String filename = "D://TAMSJASPERREPORTS//"+a+" "+b+" "+d+" "+e+".xls";
		
		Statement stmt = con.createStatement();
		
	
		String strQuery = "SELECT row_number() over ( order by trainno ) AS S_NO, a.trainno trainno,a.catchg catchg,"
				+ " ownrly_name FROM (SELECT trainno, SUM(to_number(na)) catchg, case when ownrly = 'CR' THEN 'Central Railway ' "
				+ " WHEN ownrly = 'ER' THEN 'Eastern Railway' WHEN ownrly = 'EC' THEN 'East Central Railway "
				+ "'WHEN ownrly = 'EO' THEN 'East Coast Railway' WHEN ownrly = 'KR' THEN 'Konkan Railway' "
				+ " WHEN ownrly = 'NC' THEN 'North Central Railway' WHEN ownrly = 'NE' THEN 'North Eastern Railway' "
				+ " WHEN ownrly = 'NF' THEN 'North Frontier Railway' WHEN ownrly = 'NR' THEN 'Northern Railway' "
				+ " WHEN ownrly = 'NW' THEN 'North Western Railway' WHEN ownrly = 'SB' THEN 'South East Central Railway'"
				+ " WHEN ownrly = 'SC' THEN 'South Central Railway' WHEN ownrly = 'SE' THEN 'South Eastern Railway' "
				+ " WHEN ownrly = 'SR' THEN 'Southern Railway' WHEN ownrly = 'SW' THEN 'South Western Railway' "
				+ " WHEN ownrly = 'WC' THEN 'West Central Railway' WHEN ownrly = 'WR' THEN 'Western  Railway' END ownrly_name "
				+ " from tams_prs WHERE mm = '"+month+"' and yr = '"+year+"' and ownrly ='"+tamsrlylist+"' GROUP BY ownrly ,trainno ) a WHERE a.catchg <> 0 ORDER BY a.trainno ";


		
	System.out.println("strQuery >>>"+strQuery);
		
		ResultSet rs = stmt.executeQuery(strQuery);
		while (rs.next()) {
			tamsbean dataBean = new tamsbean();
			System.out.println("hellooooooo"+rs.getString(2));
			//dataBean.setName(rs.getString("2"));
			dataBean.setA(rs.getString("S_NO"));
			dataBean.setB(rs.getString("trainno"));
			dataBean.setC(rs.getString("catchg"));
			sumr1=sumr1+Long.parseLong(rs.getString("catchg"));
			dataBean.setD(rs.getString("ownrly_name"));
			
			System.out.println("ownrly name  -  "+ rs.getString("ownrly_name") );
			dataList.add(dataBean);

		}
		System.out.println("arrayList is"+dataList);
		//dataBean.setAr(dataList);
		con.close();
		return "success";
	
	}

	
public String prsstationnotintrainroute() throws Exception 
	{
		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println();
		dataList=new ArrayList<tamsbean>();
		
		//String filename = "D://TAMSJASPERREPORTS//"+a+" "+b+" "+d+" "+e+".xls";
		
		Statement stmt = con.createStatement();
		String strQuery = "select distinct case when  orgrly = 'CR'  	then 'Central Railway' when  orgrly = 'ER' then 'Eastern Railway' when  orgrly = 'EC' 	then 'East Central Railway'"
				+ " when  orgrly = 'EO' 	then 'East Coast Railway' when  orgrly = 'KR'  	then 'Konkan Railway' when  orgrly = 'NC'  then 'North Central Railway'"
				+ "when  orgrly = 'NE' then 'North Eastern Railway' when  orgrly = 'NF'  then 'North Frontier Railway'  when  orgrly = 'NR'  then 'Northern Railway' when  orgrly = 'NW' then 'North Western Railway'  "
				+ "when  orgrly = 'SB'  	then 'South East Central Railway' when  orgrly = 'SC'  then 'South Central Railway' when  orgrly = 'SE'	then 'South Eastern Railway' when  orgrly = 'SR'  	then 'Southern Railway' "
				+ "when  orgrly = 'SW'  then 'South Western Railway' when  orgrly = 'WC' then 'West Central Railway'  when orgrly =  'WR'  then 'Western  Railway' "
				+ "end orgrly_name,trainno,sstn,sstn9,dstn,destn9 ,case when entstn = '99' then 'null' else entstn end entstn ,dist , sum (tfare - to_number(na)) fare "
				+ "from tams_prs where mm = '"+month+"'  and yr = '"+year+"'  and orgrly='"+tamsrlylist+"' and  apprcnf_flag is null   "
						+ "group by orgrly,trainno,sstn,sstn9,dstn,destn9,entstn , dist "
						+ "order by trainno,sstn,dstn";
		
		
      System.out.println("strQuery >>>"+strQuery);
		
		ResultSet rs = stmt.executeQuery(strQuery);
		while (rs.next()) {
			tamsbean dataBean = new tamsbean();
			//System.out.println("hellooooooo"+rs.getString(2));
			//dataBean.setName(rs.getString("2"));
			dataBean.setTrainno(rs.getString("trainno"));
			dataBean.setFromStn(rs.getString("sstn"));
			dataBean.setFromstncode(rs.getString("sstn9"));
			dataBean.setTostn(rs.getString("dstn"));
			dataBean.setTostncode(rs.getString("destn9"));
			dataBean.setEntstn(rs.getString("entstn"));
			dataBean.setDist(rs.getString("dist"));
			dataBean.setFare(rs.getString("fare"));
			
			dataBean.setD(rs.getString("orgrly_name"));
			
			sumr1 = sumr1 + Long.parseLong(rs.getString("fare"));
			dataList.add(dataBean);
			

		}
		con.close();
		return "successed";
	}
	
		
public String prsmatchunmatchreport() throws Exception{
		
		String month = "";
		month = ChangeMonth_format(getTamsmonth());
		String year = "";
		year = getTamsrpt();
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon =new tamsdbconnection();
		con=dbcon.getconnect();
		System.out.println("inside prsmatchunmatchreport method");
		dataList=new ArrayList<tamsbean>();
		
		
		//String filename = "D://TAMSJASPERREPORTS//"+a+" "+b+" "+d+" "+e+".xls";
		
		Statement stmt = con.createStatement();
		
		
		String query="select a.orgrly rly,"
				+ "				a.total_records Input_total ,a.tfare Input_fullfare,a.catgchg Input_catgchg,a.saf Input_safetychg,a.chg Input_otherchg,"
				+ "				b.total_records Match_total,b.tfare Match_fullfare,a.catgchg Match_catgchg,b.saf Match_safetychg,b.chg Match_otherchg,"
				+ "				c.total_records Unmatch_total,c.tfare Unmatch_fullfare,0 as Unmatch_catgchg,c.saf Unmatch_safetychg,c.chg Unmatch_otherchg "
				+ "				from "
				+ "				(select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg "
				+ "				from tams_prs where mm = '"+month+"' and yr = '"+year+"'  group by orgrly order by orgrly)  a , "
				+ "				(select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg  "
				+ "				from tams_prs where mm = '"+month+"' and yr = '"+year+"' and apprcnf_flag = 'Y' group by orgrly "
				+ "				union "
				+ "				select distinct orgrly,0 total_Records, 0 tfare,0 catgchg,0 saf,0 chg "
				+ "				from tams_prs  where mm = '"+month+"' and yr = '"+year+"' and  "
				+ "				orgrly not  in (select  distinct orgrly from tams_prs where mm = '"+month+"' and yr = '"+year+"' and apprcnf_flag = 'Y' )"
				+ "				order by orgrly)  b ,  "
				+ "				(select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg "
				+ "				from tams_prs where mm = '"+month+"' and yr = '"+year+"' and apprcnf_flag is null or apprcnf_flag not in ('Y') group by orgrly "
				+ "				union "
				+ "				select distinct orgrly,0 total_Records, 0 tfare,0 catgchg,0 saf,0 chg "
				+ "				from tams_prs  where  mm = '"+month+"' and yr = '"+year+"' and "
			    + "				orgrly not  in (select  distinct orgrly from tams_prs  where mm = '"+month+"' and yr = '"+year+"' and apprcnf_flag is null or apprcnf_flag not in ('Y'))"
				+ "				order by orgrly)  c "
				+ "				where a.orgrly = b.orgrly and b.orgrly = c.orgrly  ";
		
		
		
	      System.out.println("strQuery >>>"+query);
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				tamsbean dataBean = new tamsbean();
				//System.out.println("hellooooooo"+rs.getString(2));
				//dataBean.setName(rs.getString("2"));
				dataBean.setRly(rs.getString("rly"));
				dataBean.setItotalrecords(rs.getString("Input_total"));
				sum1=sum1+Long.parseLong(rs.getString("Input_total"));
				dataBean.setIfare(rs.getString("Input_fullfare"));
				sum2=sum2+Long.parseLong(rs.getString("Input_fullfare"));
				dataBean.setIsafetycharges(rs.getString("Input_safetychg"));
				sum3=sum3+Long.parseLong(rs.getString("Input_safetychg"));
				dataBean.setIothercharges(rs.getString("Input_otherchg"));
				sum4=sum4+Long.parseLong(rs.getString("Input_otherchg"));
				dataBean.setIcatgchg(rs.getString("Input_catgchg"));
				sum5=sum5+Long.parseLong(rs.getString("Input_catgchg"));
				dataBean.setMtotalrecords(rs.getString("Match_total"));
				sum6=sum6+Long.parseLong(rs.getString("Match_total"));
				dataBean.setMfare(rs.getString("Match_fullfare"));
				sum7=sum7+Long.parseLong(rs.getString("Match_fullfare"));
				dataBean.setMsafetycharges(rs.getString("Match_safetychg"));
				sum8=sum8+Long.parseLong(rs.getString("Match_safetychg"));
				dataBean.setMothercharges(rs.getString("Match_otherchg"));
				sum9=sum9+Long.parseLong(rs.getString("Match_otherchg"));
				dataBean.setMcatgchg(rs.getString("Match_catgchg"));
				sum10=sum10+Long.parseLong(rs.getString("Match_catgchg"));
				dataBean.setUtotalrecords(rs.getString("Unmatch_total"));
				sum11=sum11+Long.parseLong(rs.getString("Unmatch_total"));
				dataBean.setUfare(rs.getString("Unmatch_fullfare"));
				sum12=sum12+Long.parseLong(rs.getString("Unmatch_fullfare"));
				dataBean.setUsafetycharges(rs.getString("Unmatch_safetychg"));
				sum13=sum13+Long.parseLong(rs.getString("Unmatch_safetychg"));
				dataBean.setUothercharges(rs.getString("Unmatch_otherchg"));
				sum14=sum14+Long.parseLong(rs.getString("Unmatch_otherchg"));
				dataBean.setUcatgchg(rs.getString("Unmatch_catgchg"));
				sum15=sum15+Long.parseLong(rs.getString("Unmatch_catgchg"));
				dataList.add(dataBean);
		
			}
		
		
		
			con.close();
		return "successedfound";
	}

public String PRS_PRTNFILE_REPORT() throws Exception 
{
	
	String month = "";
	month = ChangeMonth_format(getTamsmonth());
	String year = "";
	year = getTamsrpt();
	System.out.println("month -----------> " + month);
	System.out.println("year -----------> " + year);
	
	
	tamsdbconnection dbcon =new tamsdbconnection();
	con=dbcon.getconnect();
	System.out.println();
	String zone=getTamsrlylist();
	dataList=new ArrayList<tamsbean>();
	ExportPdf epdf =new  ExportPdf();
    FullZoneName=epdf.fullnameofzone(zone);
	Statement stmt = con.createStatement();
	

	String strQuery = "select  b.orgrly orgrly,"
			+ " X.torly torly,X.orgg orgg,  X.Records Records, X.Amount Amount,X.CR CR, X.EC EC, X.EO EO,X.ER ER, X.KR KR,"
			+ " X.NC NC, X.NE NE, X.NF NF, X.NR NR, X.NW NW,X.SB SB, X.SC SC, X.SE SE, X.SR SR, X.SW SW,X.WC WC, X.WR WR, "
			+ "X.Total_shared Total_shared,X.Diff Diff "
			+ "from "
			+ " ( "
			+ "select a.torly,a.orgg orgg, sum(a.Records) Records, sum(a.Amount) Amount,sum(a.CR) CR, sum(a.EC) EC, sum(a.EO) EO, "
			+ "sum(a.ER) ER, sum(a.KR) KR,sum(a.NC) NC, sum(a.NE) NE, sum(a.NF) NF, sum(a.NR) NR, sum(a.NW) NW,sum(a.SB) SB, sum(a.SC) SC, "
			+ "sum(a.SE) SE, sum(a.SR) SR, sum(a.SW) SW,sum(a.WC) WC, sum(a.WR) WR, "
			+ "sum(a.Total_shared) Total_shared, sum(a.Diff) Diff   "
			+ "from "
			+ "( "
			+ "select case when torly is null then '"+tamsrlylist+"'  "
			+ "else torly  "
			+ "end  torly,  "
			+ "orgg , count(*) Records,round(sum(tfare - to_number(na)),0) Amount, "
			+ "round(sum(CRAPVA),0) CR,round(sum(ECRAPVA),0) EC,round(sum(ECORAPVA),0) EO,round(sum(ERAPVA),0) ER,round(sum(KRAPVA),0) KR, "
			+ "round(sum(NCRAPVA),0)NC,round(sum(NERAPVA),0) NE,round(sum(NFRAPVA),0) NF,round(sum(NRAPVA),0) NR,round(sum(NWRAPVA),0) NW, "
			+ "round(sum(SECRAPVA),0) SB,round(sum(SCRAPVA),0) SC,round(sum(SERAPVA),0) SE,round(sum(SRAPVA),0) SR,round(sum(SWRAPVA),0) SW, "
			+ "round(sum(WCRAPVA),0) WC,round(sum(WRAPVA),0) WR, "
			+ "round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) Total_shared, "
			+ "round(sum(tfare - to_number(na)),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) Diff "
			+ "from tams_prs  "
			+ "where mm = '"+month+"' and yr = '"+year+"' and orgrly = '"+tamsrlylist+"'   "
			+ "and orgg in ('B','M','N')   "
			+ "group by torly ,orgg  "
			+ "union "
			+ "select distinct torly,orgg,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 "
			+ "from TAMSPRSPRNTFILE m where  "
			+ "not exists (select * from  "
			+ "(select * from tams_prs where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+tamsrlylist+"'   "
			+ "and orgg in ('B','M','N')  ) n  "
			+ "where m.torly = n.torly and m.orgg = n.orgg) "
			+ "UNION  "
			+ "select '_TOTAL' as torly , 'B' as orgg , count(*) Records, "
			+ "case when sum(tfare - to_number(na))is null then 0 else round(sum(tfare - to_number(na)),0) end Amount, "
			+ "case when sum(CRAPVA)is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when sum(ECRAPVA)is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when sum(ECORAPVA)is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when sum(ERAPVA)is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when sum(KRAPVA)is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when sum(NCRAPVA)is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when sum(NERAPVA)is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when sum(NFRAPVA)is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when sum(NRAPVA)is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when sum(NWRAPVA)is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when sum(SECRAPVA)is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when sum(SCRAPVA)is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when sum(SERAPVA)is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when sum(SRAPVA)is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when sum(SWRAPVA)is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when sum(WCRAPVA)is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when sum(WRAPVA)is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA)is null then 0 else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when sum(tfare - to_number(na)) is null then 0 else  "
			+ "round(sum(tfare - to_number(na)),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0)  end Diff "
			+ "from tams_prs  "
			+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly = '"+tamsrlylist+"'   "
			+ "and orgg in ('B')   "
			+ "UNION  "
			+ "select '_TOTAL' as torly , 'M' as orgg , count(*) Records, "
			+ "case when sum(tfare - to_number(na))is null then 0 else round(sum(tfare - to_number(na)),0) end Amount, "
			+ "case when sum(CRAPVA)is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when sum(ECRAPVA)is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when sum(ECORAPVA)is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when sum(ERAPVA)is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when sum(KRAPVA)is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when sum(NCRAPVA)is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when sum(NERAPVA)is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when sum(NFRAPVA)is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when sum(NRAPVA)is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when sum(NWRAPVA)is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when sum(SECRAPVA)is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when sum(SCRAPVA)is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when sum(SERAPVA)is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when sum(SRAPVA)is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when sum(SWRAPVA)is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when sum(WCRAPVA)is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when sum(WRAPVA)is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA)is null then 0 else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when sum(tfare - to_number(na)) is null then 0 else  "
			+ "round(sum(tfare - to_number(na)),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0)  end Diff "
			+ "from tams_prs  "
			+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+tamsrlylist+"'   "
			+ "and orgg in ('M')   "
			+ "UNION  "
			+ "select '_TOTAL' as torly ,'N' as  orgg , count(*) Records, "
			+ "case when sum(tfare - to_number(na))is null then 0 else round(sum(tfare - to_number(na)),0) end Amount, "
			+ "case when sum(CRAPVA)is null then 0 else round(sum(CRAPVA),0) end CR, "
			+ "case when sum(ECRAPVA)is null then 0 else round(sum(ECRAPVA),0) end EC, "
			+ "case when sum(ECORAPVA)is null then 0 else round(sum(ECORAPVA),0) end EO, "
			+ "case when sum(ERAPVA)is null then 0 else round(sum(ERAPVA),0) end ER, "
			+ "case when sum(KRAPVA)is null then 0 else round(sum(KRAPVA),0) end KR, "
			+ "case when sum(NCRAPVA)is null then 0 else round(sum(NCRAPVA),0) end NC, "
			+ "case when sum(NERAPVA)is null then 0 else round(sum(NERAPVA),0) end NE, "
			+ "case when sum(NFRAPVA)is null then 0 else round(sum(NFRAPVA),0) end NF, "
			+ "case when sum(NRAPVA)is null then 0 else round(sum(NRAPVA),0) end NR, "
			+ "case when sum(NWRAPVA)is null then 0 else round(sum(NWRAPVA),0) end NW, "
			+ "case when sum(SECRAPVA)is null then 0 else round(sum(SECRAPVA),0) end SB, "
			+ "case when sum(SCRAPVA)is null then 0 else round(sum(SCRAPVA),0) end SC, "
			+ "case when sum(SERAPVA)is null then 0 else round(sum(SERAPVA),0) end SE, "
			+ "case when sum(SRAPVA)is null then 0 else round(sum(SRAPVA),0) end SR, "
			+ "case when sum(SWRAPVA)is null then 0 else round(sum(SWRAPVA),0) end SW, "
			+ "case when sum(WCRAPVA)is null then 0 else round(sum(WCRAPVA),0) end WC, "
			+ "case when sum(WRAPVA)is null then 0 else round(sum(WRAPVA),0) end WR, "
			+ "case when sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA)is null then 0 else round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0) end Total_shared, "
			+ "case when sum(tfare - to_number(na)) is null then 0 else  "
			+ "round(sum(tfare - to_number(na)),0) - round(sum(CRAPVA+ECORAPVA+ECRAPVA+KRAPVA+NCRAPVA+NERAPVA+NFRAPVA+NRAPVA+NWRAPVA+SCRAPVA+ "
			+ "SECRAPVA+SRAPVA+SWRAPVA+WCRAPVA+WRAPVA+ERAPVA+SERAPVA),0)  end Diff "
			+ "from tams_prs   "
			+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+tamsrlylist+"'   "
			+ "and orgg in ('N')   "
			+ ") a  "
			+ "group by a.torly , a.orgg  "
			+ ") X ,  "
			+ "(select '"+tamsrlylist+"' as orgrly from dual ) b  "
			+ "order by X.torly ,X.orgg ";


	
System.out.println("strQuery >>>"+strQuery);
	
	ResultSet rs = stmt.executeQuery(strQuery);
	 long Sum_array[] []=new long[18][21];
	
	for(int i =0; i<18 ; i++)
	{
		for(int j =0; j<21 ; j++)
		{
			Sum_array[i][j] = 0;
		}
	}
	
	
	int k =0;
	int m =0;
	
	while (rs.next()) {
		tamsbean dataBean = new tamsbean();
		System.out.println("m is"+m);
		
		
		if((m%3)==0  && m!=0)
		{
			tamsbean dataBean1 = new tamsbean();
			

			System.out.println("k is"+k);
			
			dataBean1.setTorly("");
			dataBean1.setOrgg("TOT CRDT");
			
			dataBean1.setAmount(String.valueOf(Sum_array[k][1]));
			dataBean1.setCR(String.valueOf(Sum_array[k][2]));
			dataBean1.setEC(String.valueOf(Sum_array[k][3]));
			dataBean1.setEO(String.valueOf(Sum_array[k][4]));
			dataBean1.setER(String.valueOf(Sum_array[k][5]));
			dataBean1.setKR(String.valueOf(Sum_array[k][6]));
			dataBean1.setNC(String.valueOf(Sum_array[k][7]));
			dataBean1.setNE(String.valueOf(Sum_array[k][8]));
			dataBean1.setNF(String.valueOf(Sum_array[k][9]));
			dataBean1.setNR(String.valueOf(Sum_array[k][10]));
			dataBean1.setNW(String.valueOf(Sum_array[k][11]));
			dataBean1.setSB(String.valueOf(Sum_array[k][12]));
			dataBean1.setSC(String.valueOf(Sum_array[k][13]));
			dataBean1.setSE(String.valueOf(Sum_array[k][14]));
			dataBean1.setSR(String.valueOf(Sum_array[k][15]));
			dataBean1.setSW(String.valueOf(Sum_array[k][16]));
			dataBean1.setWC(String.valueOf(Sum_array[k][17]));
			dataBean1.setWR(String.valueOf(Sum_array[k][18]));
			dataBean1.setTotal_shared(String.valueOf(Sum_array[k][19]));
			dataBean1.setDiff(String.valueOf(Sum_array[k][20]));
			dataList.add(dataBean1);
			
			
		}
		
		
		
		
		
		if((m%3)==0) dataBean.setTorly(rs.getString("torly")); 
		else dataBean.setTorly("");
		
		
		
		
		dataBean.setOrgg(rs.getString("orgg"));
		dataBean.setRecords(rs.getString("Records"));
		dataBean.setAmount(rs.getString("Amount"));
		dataBean.setCR(rs.getString("CR"));
		dataBean.setEC(rs.getString("EC"));
		dataBean.setEO(rs.getString("EO"));
		dataBean.setER(rs.getString("ER"));
		dataBean.setKR(rs.getString("KR"));
		dataBean.setNC(rs.getString("NC"));
		dataBean.setNE(rs.getString("NE"));
		dataBean.setNF(rs.getString("NF"));
		dataBean.setNR(rs.getString("NR"));
		dataBean.setNW(rs.getString("NW"));
		dataBean.setSB(rs.getString("SB"));
		dataBean.setSC(rs.getString("SC"));
		dataBean.setSE(rs.getString("SE"));
		dataBean.setSR(rs.getString("SR"));
		dataBean.setSW(rs.getString("SW"));
		dataBean.setWC(rs.getString("WC"));
		dataBean.setWR(rs.getString("WR"));
		dataBean.setTotal_shared(rs.getString("Total_shared"));
		dataBean.setDiff(rs.getString("Diff"));
		
		dataList.add(dataBean);
		
		
		
		
		
		if((m%3)==0  && m!=0)
		{
			
			k++;
			System.out.println("k is"+k);
		}
		
		
		
		
		Sum_array[k][0] = Sum_array[k][0] + Long.parseLong(rs.getString("Records")) ;
		Sum_array[k][1] = Sum_array[k][1] + Long.parseLong(rs.getString("Amount")) ;
		Sum_array[k][2] = Sum_array[k][2] + Long.parseLong(rs.getString("CR")) ;
		Sum_array[k][3] = Sum_array[k][3] + Long.parseLong(rs.getString("EC")) ;
		Sum_array[k][4] = Sum_array[k][4] + Long.parseLong(rs.getString("EO")) ;
		Sum_array[k][5] = Sum_array[k][5] + Long.parseLong(rs.getString("ER")) ;
		Sum_array[k][6] = Sum_array[k][6] + Long.parseLong(rs.getString("KR")) ;
		Sum_array[k][7] = Sum_array[k][7] + Long.parseLong(rs.getString("NC")) ;
		Sum_array[k][8] = Sum_array[k][8] + Long.parseLong(rs.getString("NE")) ;
		Sum_array[k][9] = Sum_array[k][9] + Long.parseLong(rs.getString("NF")) ;
		Sum_array[k][10] = Sum_array[k][10] + Long.parseLong(rs.getString("NR")) ;
		Sum_array[k][11] = Sum_array[k][11] + Long.parseLong(rs.getString("NW")) ;
		Sum_array[k][12] = Sum_array[k][12] + Long.parseLong(rs.getString("SB")) ;
		Sum_array[k][13] = Sum_array[k][13] + Long.parseLong(rs.getString("SC")) ;
		Sum_array[k][14] = Sum_array[k][14] + Long.parseLong(rs.getString("SE")) ;
		Sum_array[k][15] = Sum_array[k][15] + Long.parseLong(rs.getString("SR")) ;
		Sum_array[k][16] = Sum_array[k][16] + Long.parseLong(rs.getString("SW")) ;
		Sum_array[k][17] = Sum_array[k][17] + Long.parseLong(rs.getString("WC")) ;
		Sum_array[k][18] = Sum_array[k][18] + Long.parseLong(rs.getString("WR")) ;
		Sum_array[k][19] = Sum_array[k][19] + Long.parseLong(rs.getString("Total_shared")) ;
		Sum_array[k][20] = Sum_array[k][20] + Long.parseLong(rs.getString("Diff")) ;
		System.out.println("total shared"+ Sum_array[k][19]);
		
		
		
		
		
		
		
		//m++;
		++m;
		
		
	}
	
	
	tamsbean dataBean1 = new tamsbean();
	
	dataBean1.setTorly("");
	dataBean1.setOrgg("TOT CRDT");
	
	dataBean1.setAmount(String.valueOf(Sum_array[k][1]));
	dataBean1.setCR(String.valueOf(Sum_array[k][2]));
	dataBean1.setEC(String.valueOf(Sum_array[k][3]));
	dataBean1.setEO(String.valueOf(Sum_array[k][4]));
	dataBean1.setER(String.valueOf(Sum_array[k][5]));
	dataBean1.setKR(String.valueOf(Sum_array[k][6]));
	dataBean1.setNC(String.valueOf(Sum_array[k][7]));
	dataBean1.setNE(String.valueOf(Sum_array[k][8]));
	dataBean1.setNF(String.valueOf(Sum_array[k][9]));
	dataBean1.setNR(String.valueOf(Sum_array[k][10]));
	dataBean1.setNW(String.valueOf(Sum_array[k][11]));
	dataBean1.setSB(String.valueOf(Sum_array[k][12]));
	dataBean1.setSC(String.valueOf(Sum_array[k][13]));
	dataBean1.setSE(String.valueOf(Sum_array[k][14]));
	dataBean1.setSR(String.valueOf(Sum_array[k][15]));
	dataBean1.setSW(String.valueOf(Sum_array[k][16]));
	dataBean1.setWC(String.valueOf(Sum_array[k][17]));
	dataBean1.setWR(String.valueOf(Sum_array[k][18]));
	dataBean1.setTotal_shared(String.valueOf(Sum_array[k][19]));
	dataBean1.setDiff(String.valueOf(Sum_array[k][20]));
	dataList.add(dataBean1);
	
	
	
	
	con.close();
	//dataBean.setAr(dataList);
	return "success_PRS_PRTN";

}

public String ChangeMonth_format(String mm){
		
		String month = "";
		if(mm.equalsIgnoreCase("January"))
			month = "1";
		if(mm.equalsIgnoreCase("February"))
			month = "2";
		if(mm.equalsIgnoreCase("March"))
			month = "3";
		if(mm.equalsIgnoreCase("April"))
			month = "4";
		if(mm.equalsIgnoreCase("May"))
			month = "5";
		if(mm.equalsIgnoreCase("June"))
			month = "6";
		if(mm.equalsIgnoreCase("July"))
			month = "7";
		if(mm.equalsIgnoreCase("August"))
			month = "8";
		if(mm.equalsIgnoreCase("September"))
			month = "9";
		if(mm.equalsIgnoreCase("October"))
			month = "10";
		if(mm.equalsIgnoreCase("November"))
			month = "11";
		if(mm.equalsIgnoreCase("December"))
			month = "12";
		
		return month;
	}



}
