/*package tams.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;


import com.ibm.xtq.ast.nodes.Param;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import tams.databean.tamsdao;
import tams.formbean.tamsbean;
//import connection.DBConnection;
import tamsdbconnection.tamsdbconnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class generatereport extends ActionSupport implements Action,
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, String> parameters = new HashMap<String, String>();
	String tamsrpt;
	String gaugevalue;

	public String getGaugevalue() {
		return gaugevalue;
	}

	public void setGaugevalue(String gaugevalue) {
		this.gaugevalue = gaugevalue;
	}

	public String getTamsrpt() {
		return tamsrpt;
	}

	public void setTamsrpt(String tamsrpt) {
		this.tamsrpt = tamsrpt;
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

	public void setTamsproj(String tamsproj) {
		this.tamsproj = tamsproj;
	}

	String tamsmonth;
	String tamsproj;
	String tamsrptlist;
	String tamsrlylist;
	// DataSource ds;

	// HttpSession session;
	String RepPath = "E://TAMSJASPERREPORTS";

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

	tamsdao dao;
	tamsdbconnection dbcon;
	Connection con = null;
	String reportconevertType = "";
	private String tamsconvertlist;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	private InputStream fileInputStream;
	HttpServletRequest request;
	HttpServletResponse response;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTamsconvertlist() {
		return tamsconvertlist;
	}

	public void setTamsconvertlist(String tamsconvertlist) {
		this.tamsconvertlist = tamsconvertlist;
	}

	public HttpServletResponse processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("connection aa raha hai");
		

		try {

			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();

			System.out.println("con before calling getCompiledReport : " + con);
		

			synchronized (this) {
		
				System.out.println("tamsconvertlist" + tamsconvertlist);
				if (tamsconvertlist.equalsIgnoreCase("pdf")) {
					
					generatepdfsheet();
					
				} else if (tamsconvertlist.equalsIgnoreCase("excel")) {

					generateexcelsheet();
				} else if (tamsconvertlist.equalsIgnoreCase("TEXT")) {
					
					generatetextsheet();
				} else {
					//generatetextsheet();
				}

			}

			System.out.println("RepPath:" + RepPath);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
				}
			}
		}
		return null;
	}

	private JasperReport getCompiledReport(HttpServletRequest request)
			throws JRException {
		ServletContext context = ServletActionContext.getServletContext();

		System.setProperty(
				"jasper.reports.compile.class.path",
				context.getRealPath("/WEB-INF/lib/jasperreports-3.0.0.jar")
						+ System.getProperty("path.separator")
						+ context.getRealPath("/WEB-INF/classes/"));
		System.setProperty("jasper.reports.compile.temp",
				context.getRealPath("/jasperreport/"));

		System.out
				.println("check0"
						+ context.getRealPath("/jasperreport/" + tamsrptlist
								+ ".jrxml"));
		File reportFile = new File(context.getRealPath("/jasperreport/"
				+ tamsrptlist + ".jasper"));
		System.out.println("check2" + reportFile.getPath());
		JasperReport jasperReport = null;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(reportFile
					.getPath());
			System.out.println("jasperReport=" + jasperReport);
		} catch (Exception e) {
			System.out.println("e" + e.getMessage());
		}



		return jasperReport;
	}

	
	public void generatepdfsheet() throws Exception {
		ExportPdf exe = new ExportPdf();
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT"))
		{
				exe.prscateringmatrix(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{

		    exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE"))
		{
			
			exe.prsstnnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS MATCH UNMATCH REPORT"))
		{
			
			exe.prsmatchunmatch(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}


	}
	
	
	
	public void generateexcelsheet() throws Exception {
		ExportExcel exe = new ExportExcel();
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE"))
		{
			exe.exportprsstationnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS MATCH UNMATCH REPORT"))
		{

		   exe.prsmatchunmatch(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{
			exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT"))
		{
			exe.prscateringmatrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
	}
	
	
	
	public void generatetextsheet() throws Exception {
		ExportText exe = new ExportText();
		System.out.println("inside generate text  sheet");
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{

		exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE")){
			exe.prsstnnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));	
			
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS MATCH UNMATCH REPORT")){
			exe.prsmatchunmatch(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
	}



	private synchronized void generatePDFOutput(HttpServletResponse resp,
			Map parameters, JasperReport jasperReport) throws JRException,
			NamingException, SQLException, IOException {
		// dbcon = new DBConnection(session);
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			byte[] bytes = null;
			System.out.println("Inside generatePDFOutput");
			System.out.println("con : " + con);
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters,
					con);
			System.out.println("in pdf" + parameters.get("tamsrptlist"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	private void generateRTFOutput(JasperPrint jasperPrint,
			HttpServletRequest req, HttpServletResponse response,
			Map<String, String> parameters) throws JRException,
			NamingException, SQLException, IOException {
		System.out.println("RTF Report output");
		byte bytes[] = new byte[10];
		ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
		JRRtfExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				outputByteArray);
		exporter.exportReport();
		System.out.println("RTF Report generate");
		bytes = outputByteArray.toByteArray();

		outputByteArray.close();

		OutputStream outputfile = new java.io.FileOutputStream(new File(RepPath
				+ "/" + parameters.get("zone") + " "
				+ parameters.get("tamsrptlist") + " "
				+ parameters.get("rptMonth") + " " + parameters.get("rptyear")
				+ ".docs"));
		outputfile.write(bytes);
		outputfile.flush();
		outputfile.close();

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public String generateRpt() throws FileNotFoundException {
	
		
		 tamsrpt = getTamsrpt();
		//System.out.println("hello choi" + tamsrpt);
		 tamsmonth = getTamsmonth();
		
		//System.out.println("hello choi" + tamsmonth);
		 tamsproj = getTamsproj();
		
		//System.out.println("hello choi" + tamsproj);
		tamsrptlist = getTamsrptlist();
		
		System.out.println("hello list" + tamsconvertlist);
		 tamsrlylist = getTamsrlylist();
		
		// System.out.println("hello choi" + tamsrlylist);
		// tamsconvertlist = getTamsconvertlist();
		// System.out.println("hello gauge" + getGaugevalue());

		try {

			parameters.put("rptyear", tamsrpt);
			parameters.put("rptMonth", tamsmonth);
			parameters.put("tamsproj", tamsproj);
			parameters.put("tamsrptlist", tamsrptlist);
			parameters.put("zone", tamsrlylist);
			parameters.put("tamsconvertlist", tamsconvertlist);
			parameters.put("gauge", gaugevalue);
			response = processRequest(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filename="D:\\TAMSJASPERREPORTS\\"+tamsrlylist+" "+tamsrptlist+" "+tamsmonth+" "+tamsrpt+".txt";
		
		fileInputStream = new FileInputStream(new File(filename));
	    return SUCCESS;
		
		
	}
}
*/




package tams.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;



import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;



/*import com.ibm.xtq.ast.nodes.Param;*/
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import tams.databean.tamsdao;
import tams.formbean.tamsbean;
//import connection.DBConnection;
import tamsdbconnection.tamsdbconnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class generatereport extends ActionSupport implements Action,
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, String> parameters = new HashMap<String, String>();
	String tamsrpt;
	String gaugevalue;

	public String getGaugevalue() {
		return gaugevalue;
	}

	public void setGaugevalue(String gaugevalue) {
		this.gaugevalue = gaugevalue;
	}

	public String getTamsrpt() {
		return tamsrpt;
	}

	public void setTamsrpt(String tamsrpt) {
		this.tamsrpt = tamsrpt;
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

	public void setTamsproj(String tamsproj) {
		this.tamsproj = tamsproj;
	}

	String tamsmonth;
	String tamsproj;
	String tamsrptlist;
	String tamsrlylist;
	// DataSource ds;

	// HttpSession session;
	//String RepPath = "E://TAMSJASPERREPORTS";

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

	tamsdao dao;
	tamsdbconnection dbcon;
	Connection con = null;
	String reportconevertType = "";
	private String tamsconvertlist;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	private InputStream fileInputStream;
	HttpServletRequest request;
	HttpServletResponse response;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTamsconvertlist() {
		return tamsconvertlist;
	}

	public void setTamsconvertlist(String tamsconvertlist) {
		this.tamsconvertlist = tamsconvertlist;
	}

	public HttpServletResponse processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("connection aa raha hai");
		

		try {

			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();

			System.out.println("con before calling getCompiledReport : " + con);
		

			synchronized (this) {
		
				System.out.println("tamsconvertlist" + tamsconvertlist);
				if (tamsconvertlist.equalsIgnoreCase("pdf")) {
					
					generatepdfsheet();
					
				} else if (tamsconvertlist.equalsIgnoreCase("excel")) {

					generateexcelsheet();
				} else if (tamsconvertlist.equalsIgnoreCase("TEXT")) {
					
					generatetextsheet();
				} else {
					//generatetextsheet();
				}

			}

			//System.out.println("RepPath:" + RepPath);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (con != null) {
				try {
					con.close();
				}
				catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
		return null;
	}


	
	public void generatepdfsheet() throws Exception {
		ExportPdf exe = new ExportPdf();
		
		
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS PRTNFILE REPORT")){
			  System.out.println("inside report");
			   exe.PRS_PRTNFILE_REPORT(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			  
		  }
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT"))
		{
				exe.prscateringmatrix(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{

		    exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE"))
		{
			
			exe.prsstnnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS"))
		{
			
			exe.prsmatchunmatch(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS APPORTIONMENT REPORT")){
			exe.prs_apportionment_matrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS APPORTIONMENT REPORT")){
			exe.uts_apportionment_matrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
			
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Originating)"))
		{
			
			exe.UtsMatchUnmatchOrgrlywise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Booking)"))
		{
			
			exe.UtsMatchUnmatchBookingwise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		 
		 
			if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS DETAILS OF MUTP/MMTS/CIDCO/MRTS,TC/TTE,PLATFORM etc. FOR ALL INDIAN RAILWAYS")){
				exe.utsdetailsofmutpplateform(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
				
			
			
			if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS Unmatched OD pairs with Via, Distance and Full Fare")){
				exe.utsunmatchedodpairs(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
			
			if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS PRTNFILE REPORT")){
				exe.UTS_PRTNFILE_REPORT(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}

	}
	
	
	
	public void generateexcelsheet() throws Exception {
		ExportExcel exe = new ExportExcel();

		
		
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS PRTNFILE REPORT")){
			  System.out.println("inside report");
			   exe.PRS_PRTNFILE_REPORT(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			  
		  }
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE"))
		{
			exe.exportprsstationnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS"))
		{

		   exe.prsmatchunmatch(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{
			exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT"))
		{
			exe.prscateringmatrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Originating)"))
		{
			
			exe.UtsMatchUnmatchrlywise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}

		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Booking)"))
		{
			
			exe.UtsMatchUnmatchBookingzonewise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}//prs_apportionment_matrix
		 
		  
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS DETAILS OF MUTP/MMTS/CIDCO/MRTS,TC/TTE,PLATFORM etc. FOR ALL INDIAN RAILWAYS")){
				exe.utsdetailsofmutpplateform(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
		 
		 
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS APPORTIONMENT REPORT"))
			{
				
				exe.prs_apportionment_matrix(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
		 
		 
		 
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS APPORTIONMENT REPORT"))//uts_apportionment_matrix
			{
				
				exe.uts_apportionment_matrix(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
		 
			if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS Unmatched OD pairs with Via, Distance and Full Fare")){
				exe.utsunmatchedodpairs(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
			
			if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS PRTNFILE REPORT")){
				exe.UTS_PRTNFILE_REPORT(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
			
	}
	
	
	
	public void generatetextsheet() throws Exception {
		ExportText exe = new ExportText();
		System.out.println("inside generate text  sheet");
		
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS PRTNFILE REPORT")){
			  System.out.println("inside report");
			   exe.PRS_PRTNFILE_REPORT(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			  
		  }
		
		
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS Unmatched OD pairs with Via, Distance and Full Fare")){
			exe.utsunmatchedodpairs(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHG ZONE WISE TRAIN WISE"))
		{

		exe.exportcateringchgzonewisetrainwise(parameters.get("zone"),
				parameters.get("tamsrptlist"), parameters.get("tamsproj"),
				parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATIONS NOT IN TRAIN ROUTE")){
			exe.prsstnnotintrainroute(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));	
			
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS")){
			exe.prsmatchunmatch(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS CATERING CHARGES APPORTIONMENT REPORT")){
			exe.prscateringmatrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Originating)"))
		{
			
			exe.UtsMatchUnmatchOrgrlywise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		 if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (Booking)"))
		{
			
			exe.UtsMatchUnmatchBookingZonewise(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		if(parameters.get("tamsrptlist").equalsIgnoreCase("PRS APPORTIONMENT REPORT")){
			exe.prs_apportionment_matrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
		if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS APPORTIONMENT REPORT"))//uts_apportionment_matrix
		{
			
			exe.uts_apportionment_matrix(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		
	   if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS DETAILS OF MUTP/MMTS/CIDCO/MRTS,TC/TTE,PLATFORM etc. FOR ALL INDIAN RAILWAYS")){
				exe.utsdetailsofmutpplateform(parameters.get("zone"),
						parameters.get("tamsrptlist"), parameters.get("tamsproj"),
						parameters.get("rptMonth"), parameters.get("rptyear"));
			}
	   
	   
	   if(parameters.get("tamsrptlist").equalsIgnoreCase("UTS PRTNFILE REPORT")){
			exe.UTS_PRTNFILE_REPORT(parameters.get("zone"),
					parameters.get("tamsrptlist"), parameters.get("tamsproj"),
					parameters.get("rptMonth"), parameters.get("rptyear"));
		}
		 
		
	}




	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public String generateRpt() throws FileNotFoundException {
	
		
		 tamsrpt = getTamsrpt();
		 tamsmonth = getTamsmonth();
		
		 tamsproj = getTamsproj();
		
		tamsrptlist = getTamsrptlist();
		
		System.out.println("hello list" + tamsconvertlist);
		 tamsrlylist = getTamsrlylist();
		
		try {

			parameters.put("rptyear", tamsrpt);
			parameters.put("rptMonth", tamsmonth);
			parameters.put("tamsproj", tamsproj);
			parameters.put("tamsrptlist", tamsrptlist);
			parameters.put("zone", tamsrlylist);
			parameters.put("tamsconvertlist", tamsconvertlist);
			parameters.put("gauge", gaugevalue);
			response = processRequest(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filename="D:\\TAMSJASPERREPORTS\\"+tamsrlylist+" "+tamsrptlist+" "+tamsmonth+" "+tamsrpt+".txt";
		
		fileInputStream = new FileInputStream(new File(filename));
	    return SUCCESS;
		
		
	}
}
