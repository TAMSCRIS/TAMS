package tams.action;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;











import tams.databean.tamsdao;
import tams.formbean.tamsbean;











import com.opensymphony.xwork2.ActionSupport;
public class tamsaction extends ActionSupport {

private static final long serialVersionUID = 1L;
private String username;
private String zone_name;
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
HttpSession session;
ArrayList yearlist;
ArrayList monthlist;
ArrayList projectlist;
ArrayList reportlist;
ArrayList tamsrailaylist;
ArrayList tamsconvertreportlist;

public ArrayList getProjectlist() {
	return projectlist;
}
public void setProjectlist(ArrayList projectlist) {
	this.projectlist = projectlist;
}
public ArrayList getReportlist() {
	return reportlist;
}
public void setReportlist(ArrayList reportlist) {
	this.reportlist = reportlist;
}
public ArrayList getTamsrailaylist() {
	return tamsrailaylist;
}
public void setTamsrailaylist(ArrayList tamsrailaylist) {
	this.tamsrailaylist = tamsrailaylist;
}
public ArrayList getTamsconvertreportlist() {
	return tamsconvertreportlist;
}
public void setTamsconvertreportlist(ArrayList tamsconvertreportlist) {
	this.tamsconvertreportlist = tamsconvertreportlist;
}
public tamsdao getDao() {
	return dao;
}
public void setDao(tamsdao dao) {
	this.dao = dao;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

private String tamsyearcode;
private String tamscode;
private String tamsmonthcode;
private String rptname;
public String getRptname() {
	return rptname;
}
public void setRptname(String rptname) {
	this.rptname = rptname;
}
public String getRptformat() {
	return rptformat;
}
public void setRptformat(String rptformat) {
	this.rptformat = rptformat;
}
public InputStream getFileInputStream() {
	return fileInputStream;
}
public void setFileInputStream(InputStream fileInputStream) {
	this.fileInputStream = fileInputStream;
}

private String rptformat;
private InputStream fileInputStream;

public String getTamsmonthcode() {
	return tamsmonthcode;
}
public void setTamsmonthcode(String tamsmonthcode) {
	this.tamsmonthcode = tamsmonthcode;
}
public String getTamsyearcode(){
	return tamsyearcode;
}
public void setTamsyearcode(String tamsyearcode) {
	this.tamsyearcode = tamsyearcode;
}
//private String tamsyearcode;
private String tamsprojcode;
private String tamslist;
private String trlylist;
private String convertlist;
public String getConvertlist() {
	return convertlist;
}
public void setConvertlist(String convertlist) {
	this.convertlist = convertlist;
}
public String getTrlylist() {
	return trlylist;
}
public void setTrlylist(String trlylist) {
	this.trlylist = trlylist;
}
public String getTamslist() {
	return tamslist;
}
public void setTamslist(String tamslist) {
	this.tamslist = tamslist;
}
public String getTamsprojcode() {
	return tamsprojcode;
}
public void setTamsprojcode(String tamsprojcode) {
	this.tamsprojcode = tamsprojcode;
}
public String getTamscode() {
	return tamscode;
}
public void setTamscode(String tamscode) {
	this.tamscode = tamscode;
}
public ArrayList<tamsbean> getMonthlist() {
	return monthlist;
}
public void setMonthlist(ArrayList<tamsbean> monthlist) {
	this.monthlist = monthlist;
}
public ArrayList<tamsbean> getYearlist() {
	return yearlist;
}
public void setYearlist(ArrayList<tamsbean> yearlist) {
	this.yearlist = yearlist;
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
private String password;

tamsdao dao;
	public String execute() throws Exception 
	{
		HttpServletRequest req = ServletActionContext.getRequest();
		setUsername(req.getParameter("username"));
		setPassword(req.getParameter("password"));
		setZone_name(req.getParameter("zone_name"));
		session = req.getSession();
		zone_name = (String)req.getAttribute("zone_name");
		session.setAttribute("zone_name",zone_name);
		
		
		session.setAttribute("username",getUsername());
		
		
		System.out.println("zonename in session"+session.getAttribute("zone_name"));
		System.out.println("username in session"+session.getAttribute("username"));
		
		tamsdao dao = new tamsdao();
		
		boolean b = dao.checkLogin(getUsername(),getPassword(),getZone_name()) ;
		
		if(b ==true){
		System.out.println("getUsername()"+getUsername());
		System.out.println("getPassword()"+getPassword());
		String result= getyear();
		return result;
		} else{
			 addFieldError("loginpass", "Wrong username/password/zone");
		return "error";
		}
		
		
		
		
		
		}
		//return "success";
	
	
	
	
	
	public String logout(){  
		
		HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    
		
		System.out.println("IN logout method");
		
	    session.invalidate();  
	    
	    System.out.println("IN logout method down");
		
	    return "success";  
	}  
	

public String getyear() throws Exception 
{
	
		//String ss = execute();
		
		/*if( ss.equalsIgnoreCase("success"))
		{*/
	    dao=new tamsdao();
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession(); 
	    username=session.getAttribute("username").toString();
	    	
	    	yearlist=dao.getreportyearDetail(username);
		    System.out.println("print of year list" +yearlist+zone_name);
	        return SUCCESS;
	   
	    
	    
        
		/*}
		
		else
		{
			return ss ;
		}*/
}

public String getmonth() throws Exception 
{
	    dao=new tamsdao();
	    
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    username=session.getAttribute("username").toString();
	    
	   //String year=(String)yearlist.get[0].toString();
	    yearlist=dao.getreportyearDetail(username);
	    System.out.println("year print "+tamsyearcode);
	    monthlist=dao.getreportmonthDetail(username,tamsyearcode);
        return SUCCESS;
}

public String getproject() throws Exception 
{
	    dao=new tamsdao();
	    
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    username=session.getAttribute("username").toString();
	    
	    yearlist=dao.getreportyearDetail(username);
	    monthlist=dao.getreportmonthDetail(username,tamsyearcode);
	    projectlist=dao.getprojectDetail(tamsyearcode,tamsmonthcode);
	    System.out.println("print of year list" +projectlist);
        return SUCCESS;
}

public String getreport() throws Exception 
{
	    dao=new tamsdao();
	    
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    username=session.getAttribute("username").toString();
	    
	    yearlist=dao.getreportyearDetail(username);
	    monthlist=dao.getreportmonthDetail(username,tamsyearcode);
	    projectlist=dao.getprojectDetail(tamsyearcode,tamsmonthcode);
	    reportlist=dao.getreportDetail(tamsprojcode);
	    System.out.println("print of year list" +reportlist);
        return SUCCESS;
}

public String getrlyreport() throws Exception 
{
	    dao=new tamsdao();
	    
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    username=session.getAttribute("username").toString();
	    
	    yearlist=dao.getreportyearDetail(username);
	    monthlist=dao.getreportmonthDetail(username,tamsyearcode);
	    projectlist=dao.getprojectDetail(tamsyearcode,tamsmonthcode);
	    reportlist=dao.getreportDetail(tamsprojcode);
	    int ir=0;
	    System.out.println("tamslist is "+tamslist);
	    ir=dao.getIR(tamslist);
		System.out.println( "ir value is is"+ir);
	 /*   for(int i=0 ; i<=reportlist.size()-1 ; i++){
	    	
	    	
	    if(tamslist==(String)((tamsbean) reportlist.get(i)).getReporttype()){
	    	System.out.println( "ArrayLIst cvalue is"+((tamsbean) reportlist.get(i)).getIr_flag());
	    	 ir=(Integer) ((tamsbean) reportlist.get(i)).getIr_flag();
	    
	    	 System.out.println("ir value is"+ir);
	    
	    }
	    }*/
	    
	 /*   int ir=(Integer) ((tamsbean) reportlist.get(0)).getIr_flag();//list.get(0).getAge();*/
	    System.out.println("IR value is"+ir);
	    System.out.println("xxxxxxxxxxxxcccccc"+zone_name);
	    
	    tamsrailaylist=dao.getrlyDetail(ir , getZone_name());
	    System.out.println("print of year list" +tamsrailaylist);
        return SUCCESS;
        
}


public String getconverttamsReport() throws Exception 
{
	    dao=new tamsdao();
	    
	    HttpServletRequest req = ServletActionContext.getRequest();
	    session = req.getSession();
	    username=session.getAttribute("username").toString();
	    
	    yearlist=dao.getreportyearDetail(username);
	    monthlist=dao.getreportmonthDetail(username,tamsyearcode);
	    projectlist=dao.getprojectDetail(tamsyearcode,tamsmonthcode);
	    reportlist=dao.getreportDetail(tamsprojcode);
	    int ir=0;
	    System.out.println("reportlist size  is ");
	    System.out.println("tamslist is "+tamslist);
	    System.out.println("reportlist size  is "); 
	 /*   for(int i=0 ; i<=reportlist.size()-1 ; i++){
	    	
	    	
	    if(tamslist==(String)((tamsbean) reportlist.get(i)).getReporttype()){
	    	 ir=(Integer) ((tamsbean) reportlist.get(i)).getIr_flag();
	    
	    	 System.out.println("ir value is"+ir);
	    
	    }
	    }*/
	    ir=dao.getIR(tamslist);
	    System.out.println("IR value is"+ir);
	    System.out.println("xxxxxxxxxxxxcccccc");
	    
	    
	    tamsrailaylist=dao.getrlyDetail(ir,getZone_name());
	    tamsconvertreportlist=dao.getconvertreport();
	    
	    System.out.println("print of year list" +tamsconvertreportlist);
        return SUCCESS;
}
public String getZone_name() {
	return zone_name;
}
public void setZone_name(String zone_name) {
	this.zone_name = zone_name;
}


}
	

