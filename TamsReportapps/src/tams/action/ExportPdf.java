
package tams.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import tams.formbean.tamsbean;
import tamsdbconnection.tamsdbconnection;
import antlr.collections.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.InputStream;

public class ExportPdf {


    HttpServletResponse response = ServletActionContext.getResponse();
	Document document=new Document();
	tamsdbconnection dbcon;
	Connection con = null;
	Date date = new Date();
	String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
	String zonename=null;
	HttpSession session;
	long sumr1=0;
	long sumr2=0;
	long sumr3=0;
	long sumr4=0;
	long sumr5=0;
	long sumr6=0;
	long sumr7=0;
	long sumr8=0;
	long sumr9=0;
	long sumr10=0;
	long sumr11=0;
	long sumr12=0;
	long sumr13=0;
	long sumr14=0;
	long sumr15=0;
	long sumr16=0;
	long sumr17=0;
	long sumr18=0;
	long sumr19=0;
	long sumr20=0;
	long sumr21 = 0;
	long sumr22 = 0;
	long sumr23 = 0;
	Viewdata view_data_obj = new Viewdata();
	//session.getAttribute("zone_name")
	public void utsunmatchedodpairs( String a, String b, String c, String d, String e)
	{
		
		
	
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		try {
			con = dbcon.getconnect();
		
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		int sum=0;

		Statement stmt = con.createStatement();
		String query=" select station_from,station_from_code,station_upto,station_to_code,via,distance , sum(full_fare)  from tams_uts where mm_bkg_date ="+month+" and yy_bkg_date = "+year
				+ "and apprcnf_flag is null and orgrly = '"+a+"' group by station_from,station_from_code , station_upto,station_to_code,via,distance  "
				+ "order by station_from,station_upto ";
		
		
		
		
		
		ResultSet rs = stmt.executeQuery(query);
		 zonename=fullnameofzone(a);
		 
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
       // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
        my_pdf_report.open(); 
      //  my_pdf_report.addKeywords(b);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font boldFont_nor = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
        Font boldFont_nor_x = new Font(Font.FontFamily.TIMES_ROMAN,8, Font.NORMAL);
        

        Paragraph preface = new Paragraph();
       
        preface.add(new Paragraph("                            UTS Unmatched OD pairs with Via, Distance and Full Fare" , boldFont));
        preface.add(new Paragraph());
        preface.add(new Paragraph("                                              "+zonename ,boldFont));
        preface.add(new Paragraph());
        
        preface.add(new Paragraph("Figures in Units                                             "+d+" - "+e+"                                                 "+modifiedDate ,boldFont_nor));
        preface.setAlignment(4);
        my_pdf_report.add(preface);
        preface.add(new Paragraph(modifiedDate));
       Paragraph preface1 = new Paragraph();
        preface1.add(new Paragraph("***************************************************************************************************************"));
        my_pdf_report.add(preface1);
        PdfPTable my_report_table = new PdfPTable(7);
        my_report_table.setWidthPercentage(100);
        PdfPCell table_cell;
        table_cell=new PdfPCell(new Phrase("Station From",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Station From Code",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Station Upto",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Station To Code",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Via",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Distance",boldFont_nor));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Fare",boldFont_nor));
        my_report_table.addCell(table_cell);

       
        while(rs.next()){
            table_cell=new PdfPCell(new Phrase(rs.getString("station_from"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("station_from_code"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("station_upto"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("station_to_code"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("via"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("distance"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(rs.getString("sum(full_fare)"),boldFont_nor_x));
            my_report_table.addCell(table_cell);
            
            sumr1  = sumr1 + Long.parseLong(rs.getString("sum(full_fare)"));
        	
        }
        
        String totsum1=String.valueOf(sumr1);
        my_pdf_report.add(my_report_table);  
        
        Paragraph preface_11 = new Paragraph();
        
        preface_11.add(new Paragraph());
        my_pdf_report.add(preface_11);
        
        
        PdfPTable my_report_table_total = new PdfPTable(2);
        my_report_table_total.setWidthPercentage(100);
        PdfPCell table_cell_sum;
        table_cell_sum=new PdfPCell(new Phrase("Total Fare" ,boldFont_nor));
        my_report_table_total.addCell(table_cell_sum);
        table_cell_sum=new PdfPCell(new Phrase(totsum1,boldFont_nor));
        my_report_table_total.addCell(table_cell_sum);
        
        my_pdf_report.add(my_report_table_total);  
        
        my_pdf_report.close();
        rs.close();
        stmt.close(); 
        con.close(); 
        
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void  utsdetailsofmutpplateform(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
				
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

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
			
			response.setContentType("application/pdf");
			 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
			
			Document my_pdf_report = new Document();
			 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
	        my_pdf_report.open(); 
			
			 Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
			 Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
			 
			 Font colfont1 = new Font(Font.FontFamily.TIMES_ROMAN, 8 , Font.BOLD);
			    
	        Paragraph preface = new Paragraph();
	       
	        preface.add(new Paragraph("UTS DETAILS OF MUTP, MMTS,CIDCO, MRTS,TC/TTE,PLATFORM ETC. FOR ALL INDIAN RAILWAYS  BY CRIS " ,boldFont));//By CRIS
	        preface.add(new Paragraph(""));
	      //  preface.add(new Paragraph("                                                                                                                                                             BY CRIS " ,colfont1));
	        preface.add(new Paragraph("                                                                                                            "+d+" -  "+e , colfont1));
	        preface.add(new Paragraph("                                                                                                                                                                                                                   Date : "+modifiedDate ,colfont1));
	       
	        preface.add(new Paragraph("                                                                                                                                                                                                                   Figures in Units",colfont1));
	        
	        preface.add(new Paragraph("                                                                                                                   "));
	        preface.add(new Paragraph(""));
			
	        
	        my_pdf_report.add(preface) ;
	        
	        PdfPTable my_report_table = new PdfPTable(11);
	        my_report_table.setWidthPercentage(100);
	        PdfPCell table_cell;
	        table_cell=new PdfPCell(new Phrase("ORGRLY" , colfont1));
	        my_report_table.addCell(table_cell);
	        table_cell=new PdfPCell(new Phrase("TC/TTE Tickets" ,colfont1));
	        my_report_table.addCell(table_cell);
	        table_cell=new PdfPCell(new Phrase("Platform Ticket" ,colfont1));
	        my_report_table.addCell(table_cell);
	      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
	        my_report_table.addCell(table_cell);*/
	        table_cell=new PdfPCell(new Phrase("Reservation slip charges" ,colfont1));
	        my_report_table.addCell(table_cell);
	        table_cell=new PdfPCell(new Phrase("Superfast surcharge slip" , colfont1));
	        my_report_table.addCell(table_cell);
	        table_cell=new PdfPCell(new Phrase("TOURIST Ticket charge" , colfont1));
	        my_report_table.addCell(table_cell);
	        
	        table_cell=new PdfPCell(new Phrase("Platform cum parking (cab road) ticket " , colfont1));
	        my_report_table.addCell(table_cell);
	        
	        table_cell=new PdfPCell(new Phrase("MUTP CHARGES" , colfont1));
	        my_report_table.addCell(table_cell);
	        
	        table_cell=new PdfPCell(new Phrase("MRTS CHARGES" , colfont1));
	        my_report_table.addCell(table_cell);
	        table_cell=new PdfPCell(new Phrase("CIDCO CHARGES" , colfont1));
	        my_report_table.addCell(table_cell);
	        
	        table_cell=new PdfPCell(new Phrase("MMTS CHARGES" , colfont1));
	        my_report_table.addCell(table_cell);
	        
	        
	        
			while(rs.next()){
				
				table_cell=new PdfPCell(new Phrase(rs.getString("orgrly") ,colfont1));
	             my_report_table.addCell(table_cell);
	             table_cell=new PdfPCell(new Phrase(rs.getString("TC_TTE") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr1=sumr1+ Long.parseLong(rs.getString("TC_TTE"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("PLATFORM"),colfont));
	             my_report_table.addCell(table_cell);
	            
	             sumr2=sumr2+ Long.parseLong(rs.getString("PLATFORM"));
	          
	             table_cell=new PdfPCell(new Phrase(rs.getString("RESERVATION_SLIP") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr3=sumr3+ Long.parseLong(rs.getString("RESERVATION_SLIP"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("SUPERFAST"), colfont));
	             my_report_table.addCell(table_cell);
	             sumr4=sumr4+ Long.parseLong(rs.getString("SUPERFAST"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("TOURIST"), colfont));
	             my_report_table.addCell(table_cell);
	             sumr5=sumr5+ Long.parseLong(rs.getString("TOURIST"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("PARKING") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr6=sumr6+ Long.parseLong(rs.getString("PARKING"));
	           
	             table_cell=new PdfPCell(new Phrase(rs.getString("MUTP_CHARGES") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr7=sumr7+ Long.parseLong(rs.getString("MUTP_CHARGES"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("MRTS_CHARGES"), colfont));
	             my_report_table.addCell(table_cell);
	             sumr8=sumr8+ Long.parseLong(rs.getString("MRTS_CHARGES"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("CIDCO_CHARGES") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr9=sumr9+ Long.parseLong(rs.getString("CIDCO_CHARGES"));
	             table_cell=new PdfPCell(new Phrase(rs.getString("MMTS_CHARGES") , colfont));
	             my_report_table.addCell(table_cell);
	             sumr10=sumr10+ Long.parseLong(rs.getString("MMTS_CHARGES"));
	         
				
			}
			     String totsum1=String.valueOf(sumr1); 
		         String totsum2=String.valueOf(sumr2);
		         String totsum3=String.valueOf(sumr3);
		         String totsum4=String.valueOf(sumr4);
		         String totsum5=String.valueOf(sumr5);
		         String totsum6=String.valueOf(sumr6);
		         String totsum7=String.valueOf(sumr7);
		         String totsum8=String.valueOf(sumr8);
		         String totsum9=String.valueOf(sumr9);
		         String totsum10=String.valueOf(sumr10);
		         
		         table_cell=new PdfPCell(new Phrase("Total" , colfont1));
		         my_report_table.addCell(table_cell);
		         
		         table_cell=new PdfPCell(new Phrase(totsum1 , colfont1));
		         my_report_table.addCell(table_cell);
		         
		         
		         table_cell=new PdfPCell(new Phrase(totsum2 , colfont1));
		         my_report_table.addCell(table_cell);
		       
		         table_cell=new PdfPCell(new Phrase(totsum3 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum4 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum5 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum6 , colfont1));
		         my_report_table.addCell(table_cell);
		        
		         table_cell=new PdfPCell(new Phrase(totsum7 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum8 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum9 , colfont1));
		         my_report_table.addCell(table_cell);
		         table_cell=new PdfPCell(new Phrase(totsum10 , colfont1));
		         my_report_table.addCell(table_cell);
		         
		         
		         
		         my_pdf_report.add(my_report_table);                       
		         my_pdf_report.close();
		         rs.close();
		         stmt.close(); 
		         con.close(); 
		        
			
			
		
	}
	
	
	public void PRS_PRTNFILE_REPORT(String a, String b, String c, String d, String e) throws Exception{
	
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

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
				+ "select case when torly is null then '"+a+"'  "
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
				+ "where mm = '"+month+"' and yr = '"+year+"' and orgrly = '"+a+"'   "
				+ "and orgg in ('B','M','N')   "
				+ "group by torly ,orgg  "
				+ "union "
				+ "select distinct torly,orgg,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 "
				+ "from TAMSPRSPRNTFILE m where  "
				+ "not exists (select * from  "
				+ "(select * from tams_prs where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+a+"'   "
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
				+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly = '"+a+"'   "
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
				+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+a+"'   "
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
				+ "where  mm = '"+month+"' and yr = '"+year+"' and orgrly ='"+a+"'   "
				+ "and orgg in ('N')   "
				+ ") a  "
				+ "group by a.torly , a.orgg  "
				+ ") X ,  "
				+ "(select '"+a+"' as orgrly from dual ) b  "
				+ "order by X.torly ,X.orgg ";


		
	System.out.println("strQuery >>>"+strQuery);
		
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
		
		ResultSet rs = stmt.executeQuery(strQuery);
		 
		
		 zonename=fullnameofzone(a);
		 
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
      // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
       my_pdf_report.open(); 
     //  my_pdf_report.addKeywords(b);
       Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
       Font boldFont_nor = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD);
       Font boldFont_nor_z = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
       
       
       Font boldFont_nor_x = new Font(Font.FontFamily.TIMES_ROMAN,4, Font.NORMAL);
       Font boldFont_nor_y = new Font(Font.FontFamily.TIMES_ROMAN,4, Font.BOLD);
       

       Paragraph preface = new Paragraph();
       preface.add(new Paragraph("By CRIS" , boldFont_nor_z));  
       preface.add(new Paragraph("                                                             PRS PRTNFILE REPORT" , boldFont));
       preface.add(new Paragraph());
       preface.add(new Paragraph("                                                              "+zonename ,boldFont));
       preface.add(new Paragraph());
       
       preface.add(new Paragraph("Figures in Units                                   Passenger Apportionment of PRS for the month of "+d+" - "+e+"                                                  Date:"+modifiedDate ,boldFont_nor_z));
       preface.setAlignment(4);
       my_pdf_report.add(preface);
       preface.add(new Paragraph(modifiedDate));
      Paragraph preface1 = new Paragraph();
       preface1.add(new Paragraph("****************************************************************************************************************"));
       my_pdf_report.add(preface1);
       PdfPTable my_report_table = new PdfPTable(22);
       my_report_table.setWidthPercentage(100);
       PdfPCell table_cell;
       table_cell=new PdfPCell(new Phrase("Rly To",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Gauge Code",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Amount",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("CR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("EC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("EO",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("ER",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("KR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NE",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NF",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NW",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SB",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SE",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SW",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("WC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("WR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Total Shared",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("DIFF",boldFont_nor));
       my_report_table.addCell(table_cell);

       
       while (rs.next()) {
   		//tamsbean dataBean = new tamsbean();
   		System.out.println("m is"+m);
   		
   		
   		if((m%3)==0  && m!=0)
   		{
   			
   			

   			System.out.println("k is"+k);
   			
   			table_cell=new PdfPCell(new Phrase("",boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase("TOT CRDT",boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][1]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][2]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][3]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][4]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][5]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][6]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][7]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][8]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][9]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][10]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][11]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][12]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][13]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][14]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][15]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][16]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][17]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][18]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][19]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][20]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            
            
   			
   		}
   		
   		
   		
   		
   		
   		if((m%3)==0) 
   		{
   			
            table_cell=new PdfPCell(new Phrase(rs.getString("torly"),boldFont_nor_y));
            my_report_table.addCell(table_cell);
   		}
   		else 
   			{
   			table_cell=new PdfPCell(new Phrase("",boldFont_nor_x));
            my_report_table.addCell(table_cell);
   			
   			}
   		
   		
   		
   		table_cell=new PdfPCell(new Phrase(rs.getString("orgg"),boldFont_nor_y));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Amount"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("CR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("EC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("EO"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("ER"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("KR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NE"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NF"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NW"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SB"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SE"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SW"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("WC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("WR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Total_shared"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Diff"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        
   		
   		
   		
   		
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
     
       
    table_cell=new PdfPCell(new Phrase("",boldFont_nor_y));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("TOT CRDT ",boldFont_nor_y));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][1]),boldFont_nor_y));
    my_report_table.addCell(table_cell);  
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][2]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][3]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][4]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][5]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][6]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][7]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][8]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][9]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][10]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][11]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][12]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][13]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][14]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][15]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][16]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][17]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][18]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][19]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][20]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
        
		
  //  my_pdf_report.add(my_report_table_total);  
    my_pdf_report.add(my_report_table);
    my_pdf_report.close();
    rs.close();
    stmt.close(); 
    con.close(); 
    
		
	}
	
	
	public void UTS_PRTNFILE_REPORT(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

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
				+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and  orgrly = '"+a+"'  and type_code not in ('9911') "
				+ "group by dstnrly ,FROM_GUAGE  "
				+ "union "
				+ "select distinct dstnrly,FROM_GUAGE,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 "
				+ "from TAMSUTSPRNTFILE m where  "
				+ "not exists (select * from  "
				+ "(select * from tams_uts where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+a+"' and type_code not in ('9911') ) n  "
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
				+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+a+"'  and type_code not in ('9911') "
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
				+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+a+"'  and type_code not in ('9911') "
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
				+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+a+"'  and type_code not in ('9911') "
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
				+ "where mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and orgrly = '"+a+"'  and type_code not in ('9911') "
				+ "and (FROM_GUAGE not in ('B','M','N') or FROM_GUAGE is null) "
				+ ") a "
				+ "group by a.dstnrly , a.FROM_GUAGE  "
				+ ") X,  "
				+ "(select '"+a+"' as orgrly from dual ) b  "
				+ "order by X.dstnrly ,X.FROM_GUAGE ";


		
	System.out.println("strQuery >>>"+strQuery);
		
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
		
		ResultSet rs = stmt.executeQuery(strQuery);
		 
		
		 zonename=fullnameofzone(a);
		 
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
      // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
       my_pdf_report.open(); 
     //  my_pdf_report.addKeywords(b);
       Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
       Font boldFont_nor = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD);
       Font boldFont_nor_z = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
       
       
       Font boldFont_nor_x = new Font(Font.FontFamily.TIMES_ROMAN,4, Font.NORMAL);
       Font boldFont_nor_y = new Font(Font.FontFamily.TIMES_ROMAN,4, Font.BOLD);
       

       Paragraph preface = new Paragraph();
       preface.add(new Paragraph("By CRIS" , boldFont_nor_z));  
       preface.add(new Paragraph("                                                             UTS PRTNFILE REPORT" , boldFont));
       preface.add(new Paragraph());
       preface.add(new Paragraph("                                                             "+zonename ,boldFont));
       preface.add(new Paragraph());
       
       preface.add(new Paragraph("Figures in Units                                Passenger Apportionment of UTS for the month of "+d+" - "+e+"                                                  Date:"+modifiedDate ,boldFont_nor_z));
       preface.setAlignment(4);
       my_pdf_report.add(preface);
       preface.add(new Paragraph(modifiedDate));
      Paragraph preface1 = new Paragraph();
       preface1.add(new Paragraph("****************************************************************************************************************"));
       my_pdf_report.add(preface1);
       PdfPTable my_report_table = new PdfPTable(22);
       my_report_table.setWidthPercentage(100);
       PdfPCell table_cell;
       table_cell=new PdfPCell(new Phrase("Rly To",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Gauge Code",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Amount",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("CR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("EC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("EO",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("ER",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("KR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NE",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NF",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("NW",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SB",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SE",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("SW",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("WC",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("WR",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("Total Shared",boldFont_nor));
       my_report_table.addCell(table_cell);
       table_cell=new PdfPCell(new Phrase("DIFF",boldFont_nor));
       my_report_table.addCell(table_cell);

       
       while (rs.next()) {
   		//tamsbean dataBean = new tamsbean();
   		System.out.println("m is"+m);
   		
   		
   		if((m%4)==0  && m!=0)
   		{
   			
   			

   			System.out.println("k is"+k);
   			
   			table_cell=new PdfPCell(new Phrase("",boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase("TOT CRDT",boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][1]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][2]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][3]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][4]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][5]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][6]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][7]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][8]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][9]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][10]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][11]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][12]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][13]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][14]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][15]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][16]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][17]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][18]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][19]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][20]),boldFont_nor_y));
            my_report_table.addCell(table_cell);
            
            
   			
   		}
   		
   		
   		
   		
   		
   		if((m%4)==0) 
   		{
   			
            table_cell=new PdfPCell(new Phrase(rs.getString("dstnrly"),boldFont_nor_y));
            my_report_table.addCell(table_cell);
   		}
   		else 
   			{
   			table_cell=new PdfPCell(new Phrase("",boldFont_nor_x));
            my_report_table.addCell(table_cell);
   			
   			}
   		
   		
   		
   		table_cell=new PdfPCell(new Phrase(rs.getString("FROM_GUAGE"),boldFont_nor_y));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Amount"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("CR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("EC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("EO"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("ER"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("KR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NE"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NF"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("NW"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SB"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SE"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("SW"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("WC"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("WR"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Total_shared"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(rs.getString("Diff"),boldFont_nor_x));
        my_report_table.addCell(table_cell);
        
   		
   		
   		
   		
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
     
       
    table_cell=new PdfPCell(new Phrase("",boldFont_nor_y));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("TOT CRDT ",boldFont_nor_y));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][1]),boldFont_nor_y));
    my_report_table.addCell(table_cell);  
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][2]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][3]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][4]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][5]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][6]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][7]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][8]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][9]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][10]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][11]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][12]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][13]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][14]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][15]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][16]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][17]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][18]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][19]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
    table_cell=new PdfPCell(new Phrase(String.valueOf(Sum_array[k][20]),boldFont_nor_y));
    my_report_table.addCell(table_cell); 
        
		
  //  my_pdf_report.add(my_report_table_total);  
    my_pdf_report.add(my_report_table);
    my_pdf_report.close();
    rs.close();
    stmt.close(); 
    con.close(); 
    
		
	}	
	

	public void prs_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception
	{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

		Statement stmt = con.createStatement();
		
		String Query="select orgrly,round(sum(crapva/1000),0) cr,round(sum(ecrapva/1000),0) ec,round(sum(ecorapva/1000),0) eo,round(sum(erapva/1000),0) er ,"
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
		

		System.out.println("strQuery >>>" + Query);
		ArrayList retainvalue= new ArrayList();
		ArrayList inwardvalue= new ArrayList();
		ArrayList totalvalue= new ArrayList();
		int i=0;
		ResultSet rs = stmt.executeQuery(Query);
		 
		
		int nCol = rs.getMetaData().getColumnCount();

		
		String[][] dbvalue = new String[17][nCol];
		
		 while(rs.next()){
				 
			 			 
			    	 dbvalue[i][0]= rs.getString("orgrly");//your value
			     	 dbvalue[i][1]= rs.getString("CR");
			    	 dbvalue[i][2]= rs.getString("EC");
			    	 dbvalue[i][3]= rs.getString("EO");
			    	 dbvalue[i][4]= rs.getString("ER");
			    	 dbvalue[i][5]= rs.getString("KR");
			    	 dbvalue[i][6]= rs.getString("NC");
			    	 dbvalue[i][7]= rs.getString("NE");
			    	 dbvalue[i][8]= rs.getString("NF");
			    	 dbvalue[i][9]= rs.getString("NR");
			    	 dbvalue[i][10]= rs.getString("NW");
			    	 dbvalue[i][11]= rs.getString("SB");
			    	 dbvalue[i][12]= rs.getString("SC");
			    	 dbvalue[i][13]= rs.getString("SE");
			    	 dbvalue[i][14]= rs.getString("SR");
			    	 dbvalue[i][15]= rs.getString("SW");
			    	 dbvalue[i][16]= rs.getString("WC");
			    	 dbvalue[i][17]= rs.getString("WR");
			    	 dbvalue[i][18]= rs.getString("OGA");
			    	 dbvalue[i][19]= rs.getString("RETAINED");
			    	 dbvalue[i][20]= rs.getString("Outward");
			    	 
            // table_cell=new PdfPCell(new Phrase(rs.getString("OWNRLY"),colfont));
             
             
             i++;
		
		 }
		
		
		
		 for (int r=0; r< 17; r++) {
		     for (int q=0; q<nCol; q++) {
		    	 System.out.print(dbvalue[r][q] + " ");
		         
		     }
		     System.out.println();
		 }
		
		 
		 System.out.println( "out of for loop");
		
		
		 String[][] dbvalue_trans = new String[nCol][20];
		 for (int wr=0; wr< 20; wr++) {
		     for (int wq=0; wq<nCol; wq++) {
		    	 
		    	 dbvalue_trans[wq][wr] = "" ;
		         
		     }
		 }
		 
		int m = 0;
		 for (int r=0; r< 17; r++) {
		     for (int q=0; q<nCol; q++) {
		    	 
		    	 dbvalue_trans[q][r] = dbvalue[r][q] ;
		         
		     }
		    
		 }	
		 
		 		 
		 
		 // For Retained Share In Second (transpose) array
		 
		 for (int qq=1; qq<18; qq++) {
	    	 
	    	 dbvalue_trans[qq][17] = dbvalue[qq-1][19] ;
	         System.out.println("q ki value - " + qq);
	     }
		
		// For Total Apport Earnings. In Second (transpose) array

		 
		 for(int column=0; column<17 ;column++){
		        sumr1= sumr1+Long.parseLong(dbvalue_trans[1][column]);
		        sumr2= sumr2+Long.parseLong(dbvalue_trans[2][column]);
		        sumr3= sumr3+Long.parseLong(dbvalue_trans[3][column]);
		        sumr4= sumr4+Long.parseLong(dbvalue_trans[4][column]);
		        sumr5= sumr5+Long.parseLong(dbvalue_trans[5][column]);
		        sumr6= sumr6+Long.parseLong(dbvalue_trans[6][column]);
		        sumr7= sumr7+Long.parseLong(dbvalue_trans[7][column]);
		        sumr8= sumr8+Long.parseLong(dbvalue_trans[8][column]);
		        sumr9= sumr9+Long.parseLong(dbvalue_trans[9][column]);
		        sumr10= sumr10+Long.parseLong(dbvalue_trans[10][column]);
		        sumr11= sumr11+Long.parseLong(dbvalue_trans[11][column]);
		        sumr12= sumr12+Long.parseLong(dbvalue_trans[12][column]);
		        sumr13= sumr13+Long.parseLong(dbvalue_trans[13][column]);
		        sumr14= sumr14+Long.parseLong(dbvalue_trans[14][column]);
		        sumr15= sumr15+Long.parseLong(dbvalue_trans[15][column]);
		        sumr16= sumr16+Long.parseLong(dbvalue_trans[16][column]);
		        sumr17= sumr17+Long.parseLong(dbvalue_trans[17][column]);
		        
		       // sumr18= sumr18+Long.parseLong(dbvalue_trans[18][column]);
		        }
		 
		 
		       
		 
				  dbvalue_trans[1][19]=String.valueOf(sumr1);
				  dbvalue_trans[2][19]=String.valueOf(sumr2);
				  dbvalue_trans[3][19]=String.valueOf(sumr3);
				  dbvalue_trans[4][19]=String.valueOf(sumr4);
				  dbvalue_trans[5][19]=String.valueOf(sumr5);
				  dbvalue_trans[6][19]=String.valueOf(sumr6);
				  dbvalue_trans[7][19]=String.valueOf(sumr7);
				  dbvalue_trans[8][19]=String.valueOf(sumr8);
				  dbvalue_trans[9][19]=String.valueOf(sumr9);
				  dbvalue_trans[10][19]=String.valueOf(sumr10);
				  dbvalue_trans[11][19]=String.valueOf(sumr11);
				  dbvalue_trans[12][19]=String.valueOf(sumr12);
				  dbvalue_trans[13][19]=String.valueOf(sumr13);
				  dbvalue_trans[14][19]=String.valueOf(sumr14);
				  dbvalue_trans[15][19]=String.valueOf(sumr15);
				  dbvalue_trans[16][19]=String.valueOf(sumr16);
				  dbvalue_trans[17][19]=String.valueOf(sumr17);
		
		// For Total Apport Earnings. In Second (transpose) array
				  
				  for(int z =1 ; z<18; z++)
				  {
					  dbvalue_trans[z][18]=String.valueOf( Long.parseLong(dbvalue_trans[z][19]) - Long.parseLong(dbvalue_trans[z][17]));
				  }
				  
				  
		
				  	String totinwardsum=null;
				     
			        String totapposum=null;
			        String totretainsum=null;
			        String totoutwardsum= null;
			        String totogasum= null;
			        //sum of retain inward outward total apportionment oga
			        for(int k =1; k< 18 ; k++)//Long.parseLong(dbvalue_trans[18][column]
			    	{
			           sumr18= sumr18 + Long.parseLong(dbvalue_trans[k][17]);
			           sumr19 = sumr19 + Long.parseLong(dbvalue_trans[k][18]);
			           sumr20=sumr20 + Long.parseLong(dbvalue_trans[k][19]);
			           sumr21=sumr21 + Long.parseLong(dbvalue_trans[20][k-1]);
			           sumr22=sumr22 + Long.parseLong(dbvalue_trans[18][k-1]);
			           
			    	}
			        
			        totretainsum=String.valueOf(sumr18);
			        totinwardsum=String.valueOf(sumr19);
			        totapposum=String.valueOf(sumr20);
			        totoutwardsum=String.valueOf(sumr21);
			        totogasum=String.valueOf(sumr22);
			        		  
		  
		  
		  
		 System.out.println( "second array");
		 for (int r=0; r< nCol; r++) {
		     for (int q=0; q<18; q++) {
		    	 System.out.print(dbvalue_trans[r][q] + " ");
		         
		     }
		     System.out.println();
		 }
		 
		 
		 
		 //sum of row
		  
		 response.setContentType("application/pdf");

		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		        
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open();    
         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
         Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 5);
         Font colfont_bold = new Font(Font.FontFamily.TIMES_ROMAN, 5,Font.BOLD);
         Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);

         Paragraph preface = new Paragraph();
        
         
         preface.add(new Paragraph("     SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (PRS)    By CRIS" , boldFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                        "+d+" - "+e+"                                                                        Date -"+modifiedDate ,normalFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                                                                                                                   Figures in thousands" , normalFont));
        // preface.setTextAlignment(TextAlignment.JUSTIFIED);
         my_pdf_report.add(preface);
        Paragraph preface1 = new Paragraph();
         preface1.add(new Paragraph("***************************************************************************************************************"));
         my_pdf_report.add(preface1);
         PdfPTable my_report_table = new PdfPTable(21);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("ZONE",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("CR",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EC",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EO",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("ER" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("KR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NC",colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("NE",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NF",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NW" ,colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("SB" ,colfont_bold));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("SC" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SE" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SW" ,colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("WC",colfont_bold));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("WR",colfont_bold));
         my_report_table.addCell(table_cell);
        
         table_cell=new PdfPCell(new Phrase("Retained Share",colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("Inward Share",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Apport. Earnings",colfont_bold));
         my_report_table.addCell(table_cell);
        
            
         int j =1;

        while(j<18) {
        	
        	
        	table_cell=new PdfPCell(new Phrase(dbvalue[j-1][0],colfont_bold));
            my_report_table.addCell(table_cell);
        	
            
            for(int k =0; k< 20 ; k++)
        	{
            	//
        	table_cell=new PdfPCell(new Phrase(dbvalue_trans[j][k],colfont));
            my_report_table.addCell(table_cell);
            
        	}
            
            
        	
        	j++ ;
        }
       
      
        
        

        
        
        for(int k =0; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        
    	}
        table_cell=new PdfPCell(new Phrase(totretainsum,colfont_bold));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totinwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totapposum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        table_cell=new PdfPCell(new Phrase("OGA",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[18][k],colfont));
        my_report_table.addCell(table_cell);
        
    	}
       
        table_cell=new PdfPCell(new Phrase(totogasum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        table_cell=new PdfPCell(new Phrase("Retained share",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[19][k],colfont));
        my_report_table.addCell(table_cell);
       // totretainsum= String.valueOf(sumretained+Integer.parseInt( dbvalue_trans[19][k]));
        
    	}
        
        table_cell=new PdfPCell(new Phrase(totretainsum,colfont_bold));
        my_report_table.addCell(table_cell);
       
        
        table_cell=new PdfPCell(new Phrase("Outward",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[20][k],colfont));
        my_report_table.addCell(table_cell);
        
    	}
        
        table_cell=new PdfPCell(new Phrase(totoutwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        
       
        table_cell=new PdfPCell(new Phrase("Inward",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =1; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[k][18],colfont));
        my_report_table.addCell(table_cell);
                
    	}
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totinwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        
        table_cell=new PdfPCell(new Phrase("Total Apport Earnings",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =1; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[k][19],colfont));
        my_report_table.addCell(table_cell);
        
    	}
        
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totapposum,colfont_bold));
        my_report_table.addCell(table_cell);
       
        
       
        
      

         my_pdf_report.add(my_report_table); 
         
         
         Paragraph footer = new Paragraph();
        
         
         footer.add(new Paragraph("                   Note: Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data" , normalFont));
         footer.add(new Paragraph("                         Outward Share : Earnings distributed to other Railways" , normalFont));
         footer.add(new Paragraph("                         Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic) " , normalFont));
         footer.add(new Paragraph("                         Inward Share   : Earnings received from all Railways" , normalFont));
         footer.add(new Paragraph("                         Total Apportionment Earnings : Retained Share + Inward Share" , normalFont));
         footer.add(new Paragraph("                  Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings." , normalFont));
         footer.add(new Paragraph("                  Note 2- Catering charges have not been included in apportioned share of railways." , normalFont));
         
         my_pdf_report.add(footer); 
        
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
        // downloadFile(path , filename); 
         
         
         
         
         
		
	}
	
	
	public void uts_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
				
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

		Statement stmt = con.createStatement();
		
		String Query=" select a.orgrly orgrly,a.cr cr, a.ec ec, a.eo eo, a.er er, a.kr kr, a.nc nc, a.ne ne, a.nf nf, a.nr nr, a.nw nw, a.sb sb, a.sc sc, a.se se, a.sr sr, "
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
		

		System.out.println("strQuery >>>" + Query);
		/*ArrayList retainvalue= new ArrayList();
		ArrayList inwardvalue= new ArrayList();
		ArrayList totalvalue= new ArrayList();*/
		int i=0;
		ResultSet rs = stmt.executeQuery(Query);
		 
		
		int nCol = rs.getMetaData().getColumnCount();

		
		String[][] dbvalue = new String[17][nCol];
		
		 while(rs.next()){
				 
			 			 
			    	 dbvalue[i][0]= rs.getString("orgrly");//your value
			     	 dbvalue[i][1]= rs.getString("CR");
			    	 dbvalue[i][2]= rs.getString("EC");
			    	 dbvalue[i][3]= rs.getString("EO");
			    	 dbvalue[i][4]= rs.getString("ER");
			    	 dbvalue[i][5]= rs.getString("KR");
			    	 dbvalue[i][6]= rs.getString("NC");
			    	 dbvalue[i][7]= rs.getString("NE");
			    	 dbvalue[i][8]= rs.getString("NF");
			    	 dbvalue[i][9]= rs.getString("NR");
			    	 dbvalue[i][10]= rs.getString("NW");
			    	 dbvalue[i][11]= rs.getString("SB");
			    	 dbvalue[i][12]= rs.getString("SC");
			    	 dbvalue[i][13]= rs.getString("SE");
			    	 dbvalue[i][14]= rs.getString("SR");
			    	 dbvalue[i][15]= rs.getString("SW");
			    	 dbvalue[i][16]= rs.getString("WC");
			    	 dbvalue[i][17]= rs.getString("WR");
			    	 dbvalue[i][18]= rs.getString("platform");
			    	 dbvalue[i][19]= rs.getString("OGA");
			    	 dbvalue[i][20]= rs.getString("RETAINED");
			    	 dbvalue[i][21]= rs.getString("Outward");			    	 
            // table_cell=new PdfPCell(new Phrase(rs.getString("OWNRLY"),colfont));
             
             
             i++;
		
		 }
		
		
		
		 for (int r=0; r< 17; r++) {
		     for (int q=0; q<nCol; q++) {
		    	 System.out.print(dbvalue[r][q] + " ");
		         
		     }
		     System.out.println();
		 }
		
		 
		 System.out.println( "out of for loop");
		
		
		 String[][] dbvalue_trans = new String[nCol][20];
		 for (int wr=0; wr< 20; wr++) {
		     for (int wq=0; wq<nCol; wq++) {
		    	 
		    	 dbvalue_trans[wq][wr] = "" ;
		         
		     }
		 }
		 
		int m = 0;
		 for (int r=0; r< 17; r++) {
		     for (int q=0; q<nCol; q++) {
		    	 
		    	 dbvalue_trans[q][r] = dbvalue[r][q] ;
		         
		     }
		    
		 }	
		 
		 		 
		 
		 // For Retained Share In Second (transpose) array
		 
		 for (int qq=1; qq<18; qq++) {
	    	 
	    	 dbvalue_trans[qq][17] = dbvalue[qq-1][20] ;
	         System.out.println("q ki value - " + qq);
	     }
		
		// For Total Apport Earnings. In Second (transpose) array

		 
		 for(int column=0; column<17 ;column++){
		        sumr1= sumr1+Long.parseLong(dbvalue_trans[1][column]);
		        sumr2= sumr2+Long.parseLong(dbvalue_trans[2][column]);
		        sumr3= sumr3+Long.parseLong(dbvalue_trans[3][column]);
		        sumr4= sumr4+Long.parseLong(dbvalue_trans[4][column]);
		        sumr5= sumr5+Long.parseLong(dbvalue_trans[5][column]);
		        sumr6= sumr6+Long.parseLong(dbvalue_trans[6][column]);
		        sumr7= sumr7+Long.parseLong(dbvalue_trans[7][column]);
		        sumr8= sumr8+Long.parseLong(dbvalue_trans[8][column]);
		        sumr9= sumr9+Long.parseLong(dbvalue_trans[9][column]);
		        sumr10= sumr10+Long.parseLong(dbvalue_trans[10][column]);
		        sumr11= sumr11+Long.parseLong(dbvalue_trans[11][column]);
		        sumr12= sumr12+Long.parseLong(dbvalue_trans[12][column]);
		        sumr13= sumr13+Long.parseLong(dbvalue_trans[13][column]);
		        sumr14= sumr14+Long.parseLong(dbvalue_trans[14][column]);
		        sumr15= sumr15+Long.parseLong(dbvalue_trans[15][column]);
		        sumr16= sumr16+Long.parseLong(dbvalue_trans[16][column]);
		        sumr17= sumr17+Long.parseLong(dbvalue_trans[17][column]);
		        
		        // sumr18= sumr18+Long.parseLong(dbvalue_trans[19][column]);
		        
		        }
		 
		 
		       
		 
				  dbvalue_trans[1][19]=String.valueOf(sumr1);
				  dbvalue_trans[2][19]=String.valueOf(sumr2);
				  dbvalue_trans[3][19]=String.valueOf(sumr3);
				  dbvalue_trans[4][19]=String.valueOf(sumr4);
				  dbvalue_trans[5][19]=String.valueOf(sumr5);
				  dbvalue_trans[6][19]=String.valueOf(sumr6);
				  dbvalue_trans[7][19]=String.valueOf(sumr7);
				  dbvalue_trans[8][19]=String.valueOf(sumr8);
				  dbvalue_trans[9][19]=String.valueOf(sumr9);
				  dbvalue_trans[10][19]=String.valueOf(sumr10);
				  dbvalue_trans[11][19]=String.valueOf(sumr11);
				  dbvalue_trans[12][19]=String.valueOf(sumr12);
				  dbvalue_trans[13][19]=String.valueOf(sumr13);
				  dbvalue_trans[14][19]=String.valueOf(sumr14);
				  dbvalue_trans[15][19]=String.valueOf(sumr15);
				  dbvalue_trans[16][19]=String.valueOf(sumr16);
				  dbvalue_trans[17][19]=String.valueOf(sumr17);
		
		// For Total Apport Earnings. In Second (transpose) array
				  
				  for(int z =1 ; z<18; z++)
				  {
					  dbvalue_trans[z][18]=String.valueOf( Long.parseLong(dbvalue_trans[z][19]) - Long.parseLong(dbvalue_trans[z][17]));
				  }
				  
				  
		
				  	String totinwardsum=null;
				     
			        String totapposum=null;
			        String totretainsum=null;
			        String totoutwardsum= null;
			        String totogasum= null;
			        String totplatformsum= null;
			        //sum of retain inward outward total apportionment oga
			        for(int k =1; k< 18 ; k++)//Long.parseLong(dbvalue_trans[18][column]
			    	{
			           sumr18= sumr18 + Long.parseLong(dbvalue_trans[k][17]);
			           sumr19 = sumr19 + Long.parseLong(dbvalue_trans[k][18]);
			           sumr20=sumr20 + Long.parseLong(dbvalue_trans[k][19]);
			           sumr21=sumr21 + Long.parseLong(dbvalue_trans[21][k-1]);
			           sumr22=sumr22 + Long.parseLong(dbvalue_trans[19][k-1]);
			           sumr23=sumr23 + Long.parseLong(dbvalue_trans[18][k-1]);
			           
			    	}
			        
			        totretainsum=String.valueOf(sumr18);
			        totinwardsum=String.valueOf(sumr19);
			        totapposum=String.valueOf(sumr20);
			        totoutwardsum=String.valueOf(sumr21);
			        totogasum=String.valueOf(sumr22);
			        totplatformsum=String.valueOf(sumr23);
			        		  
		  
		  
		  
		 System.out.println( "second array");
		 for (int r=0; r< nCol; r++) {
		     for (int q=0; q<18; q++) {
		    	 System.out.print(dbvalue_trans[r][q] + " ");
		         
		     }
		     System.out.println();
		 }
		 
		 
		 
		 //sum of row
		  

		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");       
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open();    
         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
         Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 5);
         Font colfont_bold = new Font(Font.FontFamily.TIMES_ROMAN, 5,Font.BOLD);
         Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);

         Paragraph preface = new Paragraph();
        
         
         preface.add(new Paragraph("     SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)    By CRIS" , boldFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                        "+d+" - "+e+"                                                                        Date -"+modifiedDate ,normalFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                                                                                                                   Figures in thousands" , normalFont));
        // preface.setTextAlignment(TextAlignment.JUSTIFIED);
         my_pdf_report.add(preface);
        Paragraph preface1 = new Paragraph();
         preface1.add(new Paragraph("***************************************************************************************************************"));
         my_pdf_report.add(preface1);
         PdfPTable my_report_table = new PdfPTable(21);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("ZONE",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("CR",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EC",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EO",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("ER" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("KR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NC",colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("NE",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NF",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NW" ,colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("SB" ,colfont_bold));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("SC" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SE" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SR" ,colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SW" ,colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("WC",colfont_bold));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("WR",colfont_bold));
         my_report_table.addCell(table_cell);
        
         table_cell=new PdfPCell(new Phrase("Retained Share",colfont_bold));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("Inward Share",colfont_bold));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Apport. Earnings",colfont_bold));
         my_report_table.addCell(table_cell);
        
            
         int j =1;

        while(j<18) {
        	
        	
        	table_cell=new PdfPCell(new Phrase(dbvalue[j-1][0],colfont_bold));
            my_report_table.addCell(table_cell);
        	
            
            for(int k =0; k< 20 ; k++)
        	{
            	//
        	table_cell=new PdfPCell(new Phrase(dbvalue_trans[j][k],colfont));
            my_report_table.addCell(table_cell);
            
        	}
            
            
        	
        	j++ ;
        }
       
      
        
        

        
        
        for(int k =0; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        
    	}
        table_cell=new PdfPCell(new Phrase(totretainsum,colfont_bold));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totinwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totapposum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        
        table_cell=new PdfPCell(new Phrase("Platform Tickets",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[18][k],colfont));
        my_report_table.addCell(table_cell);
        
    	}
       
        table_cell=new PdfPCell(new Phrase(totplatformsum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        
        
        
        table_cell=new PdfPCell(new Phrase("OGA",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[19][k],colfont));
        my_report_table.addCell(table_cell);
        
    	}
       
        table_cell=new PdfPCell(new Phrase(totogasum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        table_cell=new PdfPCell(new Phrase("Retained share",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[20][k],colfont));
        my_report_table.addCell(table_cell);
       // totretainsum= String.valueOf(sumretained+Integer.parseInt( dbvalue_trans[19][k]));
        
    	}
        
        table_cell=new PdfPCell(new Phrase(totretainsum,colfont_bold));
        my_report_table.addCell(table_cell);
       
        
        table_cell=new PdfPCell(new Phrase("Outward",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =0; k< 19 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[21][k],colfont));
        my_report_table.addCell(table_cell);
        
    	}
        
        table_cell=new PdfPCell(new Phrase(totoutwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        
       
        table_cell=new PdfPCell(new Phrase("Inward",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =1; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[k][18],colfont));
        my_report_table.addCell(table_cell);
                
    	}
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totinwardsum,colfont_bold));
        my_report_table.addCell(table_cell);
        
        
        
        table_cell=new PdfPCell(new Phrase("Total Apport Earnings",colfont_bold));
        my_report_table.addCell(table_cell);
        
        for(int k =1; k< 18 ; k++)
    	{
    	table_cell=new PdfPCell(new Phrase(dbvalue_trans[k][19],colfont));
        my_report_table.addCell(table_cell);
        
    	}
        
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("",colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase(totapposum,colfont_bold));
        my_report_table.addCell(table_cell);
       
        
       
        
      

         my_pdf_report.add(my_report_table); 
         
         
         Paragraph footer = new Paragraph();
        
         
         footer.add(new Paragraph("                   Note: Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data" , normalFont));
         footer.add(new Paragraph("                         Outward Share : Earnings distributed to other Railways" , normalFont));
         footer.add(new Paragraph("                         Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic) " , normalFont));
         footer.add(new Paragraph("                         Inward Share   : Earnings received from all Railways" , normalFont));
         footer.add(new Paragraph("                         Total Apportionment Earnings : Retained Share + Inward Share" , normalFont));
         footer.add(new Paragraph("                  Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings." , normalFont));
         footer.add(new Paragraph("                  Note 2- MUTP,MRTS,MMTS,CIDCO charges, Reservation slip, superfast surcharge slip charges are included in the apportioned share of orginating railway(Home Railway)." , normalFont));
         
         my_pdf_report.add(footer); 
        
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
        // downloadFile(path , filename); 
         
         
         
         
         
		
	}

	
	public void exportcateringchgzonewisetrainwise(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		int sum=0;

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
				+ " from tams_prs WHERE mm = '"+month+"' and yr = '"+year+"' and ownrly ='"+a+"' GROUP BY ownrly ,trainno ) a WHERE a.catchg <> 0 ORDER BY a.trainno ";


		System.out.println("strQuery >>>" + strQuery);

		ResultSet rs = stmt.executeQuery(strQuery);
		 zonename=fullnameofzone(a);
		 
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
         //PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open(); 
       //  my_pdf_report.addKeywords(b);
         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
         Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
         Paragraph preface = new Paragraph();
        
         
         preface.add(new Paragraph("PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE    By CRIS" , boldFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                             " +zonename ,normalFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                             "+d+" "+e+"                           "+"                            Date -   "+modifiedDate ,normalFont));
        // preface.setTextAlignment(TextAlignment.JUSTIFIED);
         preface.setAlignment(4);
         my_pdf_report.add(preface);
         preface.add(new Paragraph(modifiedDate));
        Paragraph preface1 = new Paragraph();
         preface1.add(new Paragraph("************************************************************************************************************"));
         my_pdf_report.add(preface1);
         PdfPTable my_report_table = new PdfPTable(3);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("SNO"));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Train Number"));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Catering Charge"));
         my_report_table.addCell(table_cell);
        // Paragraph preface2 = new Paragraph();
        // preface2.add(new Paragraph("**************************************************************************************"));
         // preface.setAlignment(Element.ALIGN_RIGHT);
         // my_pdf_report.add(preface2);
         while(rs.next()){
             table_cell=new PdfPCell(new Phrase(rs.getString("S_No")));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("TRAINNO")));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("CATCHG")));
             my_report_table.addCell(table_cell);
         	sum =sum+Integer.parseInt(rs.getString("CATCHG"));
         }
         String totsum=String.valueOf(sum);
         table_cell=new PdfPCell(new Phrase(""));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Amount"));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum));
         my_report_table.addCell(table_cell);
         
         
         my_pdf_report.add(my_report_table);                       
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
      //   downloadFile(path , filename);
         
         
	}
	
	
	/*public void prscateringmatrix(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		int sum=0;

		Statement stmt = con.createStatement();
		
		String Query="select a.ownrly, "
				+ " a.catchg CR,b.catchg EC,c.catchg EO,d.catchg ER,e.catchg KR,f.catchg NC,g.catchg NE,h.catchg NF,i.catchg NR,j.catchg NW,k.catchg SB, "
				+ "l.catchg SC,m.catchg SE,n.catchg SR,o.catchg SW,p.catchg WC,q.catchg WR,r.catchg Retained, "
				+ "((a.catchg + b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  + "
				+ "m.catchg  +n.catchg  +o.catchg  +p.catchg  + q.catchg ) - r.catchg) inward, "
				+ "(a.catchg +b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  +m.catchg + "
				+ "n.catchg  +o.catchg  +p.catchg  + q.catchg ) Total "
				+ "from  "
				+ "(select ownrly, "
				+ "round(sum(to_number(na)),0) catchg  "
				+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' "
				+ "group by ownrly "
				+ "order by ownrly ) a, "
				+ "(select ownrly,catchg from "
				+ "(select ownrly, "
				+ "round(sum(to_number(na)),0) catchg  "
				+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EC'   "
				+ "group by ownrly   "
				+ "order by ownrly )  "
				+ "union   "
				+ " select distinct ownrly,0 as catgchg   "
				+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
				+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EC')) b,    "
				+ "(select ownrly,catchg from      "
				+ "(select ownrly,   "
				+ "round(sum(to_number(na)),0) catchg   "
				+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EO'   "
		    	+ "group by ownrly   "
		    	+ "order by ownrly )  "
		    	+ "union  "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly    "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EO')) c,   "
		    	+ "(select ownrly,catchg from   "
				+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'ER'  "
				+ "group by ownrly   "
		    	+ "order by ownrly )   "
		    	+ "union    "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly    "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'ER')) d,  "
		    	+ "(select ownrly,catchg from   "
		   		+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'KR'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly )  "
		    	+ "union   "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'KR')) e,   "
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NC'  "
		    	+ "group by ownrly   "
		    	+ "order by ownrly )  "
		    	+ "union  "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NC')) f,"
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,   "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NE'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly )  "
		    	+ "union  "
		    	+ " select distinct ownrly,0 as catgchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NE')) g,  "
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NF'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly )  "
		    	+ "union  "
		    	+ "select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NF')) h, "
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NR' "
		    	+ "group by ownrly "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ "select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NR')) i,"
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NW' "
		    	+ "group by ownrly "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ " select distinct ownrly,0 as catgchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NW')) j, "
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SB' "
		    	+ "group by ownrly "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ "select distinct ownrly,0 as catgchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SB')) k, "
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SC' "
		    	+ "group by ownrly "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ "select distinct ownrly,0 as catgchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SC')) l, "
		    	+ "(select ownrly,catchg from  "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SE'  "
		    	+ "group by ownrly "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ "select distinct ownrly,0 as catgchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SE')) m,  "
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SR' "
		    	+ "group by ownrly  "
		    	+ "order by ownrly )  "
		    	+ "union "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SR')) n, "
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SW' "
		    	+ "group by ownrly  "
		    	+ "order by ownrly )  "
		    	+ "union  "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SW')) o, "
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WC'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly ) "
		    	+ "union "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WC')) p, "
		    	+ "(select ownrly,catchg from   "
		    	+ "(select ownrly,  "
		   		+ "round(sum(to_number(na)),0) catchg  "
		   		+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WR' "
		 		+ "group by ownrly  "
		    	+ "order by ownrly ) "
		    	+ "union  "
		    	+ " select distinct ownrly,0 as catgchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly not in (select distinct ownrly   "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WR')) q, "
		    	+ "(select ownrly,catchg from "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'CR' and ownrly='CR'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union    "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EC' and ownrly='EC'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union  "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'EO' and ownrly='EO'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'ER' and ownrly='ER' "
		    	+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union  "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'KR' and ownrly='KR' "
		    	+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union  "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NC' and ownrly='NC' "
		   		+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union  "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NE' and ownrly='NE' "
		    	+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union  "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NF' and ownrly='NF' "
		   		+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union "
		    	+ "select ownrly,catchg Retained from "
		    	+ "(select ownrly, "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NR' and ownrly='NR'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'NW' and ownrly='NW'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SB' and ownrly='SB'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SC' and ownrly='SC'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SE' and ownrly='SE'  "
		    	+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SR' and ownrly='SR'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'SW' and ownrly='SW'  "
		    	+ "group by ownrly "
		    	+ "order by ownrly) "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WC' and ownrly='WC'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly) "
		    	+ "union   "
		    	+ "select ownrly,catchg Retained from  "
		    	+ "(select ownrly,  "
		    	+ "round(sum(to_number(na)),0) catchg  "
		    	+ "from tams_prs where  mm = '"+month+"' and  yr = '"+year+"'  and     orgrly = 'WR' and ownrly='WR'  "
		    	+ "group by ownrly  "
		    	+ "order by ownrly)  "
		    	+ ") r  "
		    	+ "Where   "
		    	+ "a.ownrly = b.ownrly   "
		    	+ "and b.ownrly = c.ownrly  "
		    	+ "and c.ownrly = d.ownrly  "
		    	+ "and d.ownrly = e.ownrly   "
		    	+ "and e.ownrly = f.ownrly  "
		    	+ "and f.ownrly = g.ownrly  "
		    	+ "and g.ownrly = h.ownrly   "
		    	+ "and h.ownrly = i.ownrly   "
		    	+ "and i.ownrly = j.ownrly  "
		    	+ "and j.ownrly = k.ownrly   "
		    	+ "and k.ownrly = l.ownrly  "
		    	+ "and l.ownrly = m.ownrly   "
		    	+ "and m.ownrly = n.ownrly  "
		    	+ "and n.ownrly = o.ownrly   "
		    	+ "and o.ownrly = p.ownrly   "
		    	+ "and p.ownrly = q.ownrly  "
		    	+ "and q.ownrly = r.ownrly"  ;
		
		

		System.out.println("strQuery >>>" + Query);
		ArrayList<Integer> retainvalue= new ArrayList<Integer>();
		ArrayList inwardvalue= new ArrayList();
		ArrayList totalvalue= new ArrayList();
		int i=0;

		ResultSet rs = stmt.executeQuery(Query);
		 zonename=fullnameofzone(a);
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open();    
         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
         Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 5);
         Font colfont_bold = new Font(Font.FontFamily.TIMES_ROMAN, 5,Font.BOLD);
         Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);

         Paragraph preface = new Paragraph();
        
         
         preface.add(new Paragraph("     SUMMARY OF  APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS (PRS)   By CRIS" , boldFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                        "+d+" - "+e+"                                                                        Date -"+modifiedDate ,normalFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                                                                                                                                                                   Figures in Units" , normalFont));
        // preface.setTextAlignment(TextAlignment.JUSTIFIED);
         preface.add(new Paragraph("                                                                                                                                                                                             "));
         my_pdf_report.add(preface);
         PdfPTable my_report_table = new PdfPTable(21);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("ZONE",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("CR",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EC",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("EO",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("ER" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("KR" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NC",colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("NE",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NF",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NR" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("NW" ,colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("SB" ,colfont));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("SC" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SE" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SR" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("SW" ,colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("WC",colfont));
         my_report_table.addCell(table_cell);   
         table_cell=new PdfPCell(new Phrase("WR",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Retained Share",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Inward Share",colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Apport. Earnings",colfont));
         my_report_table.addCell(table_cell);
         while(rs.next()){
             table_cell=new PdfPCell(new Phrase(rs.getString("OWNRLY"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("CR"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("EC"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("EO"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("ER"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("KR"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("NC"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("NE"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("NF"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("NR"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("NW"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("SB"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("SC"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("SE"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("SR"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("SW"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("WC"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("WR"),colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("RETAINED"),colfont));
             my_report_table.addCell(table_cell);
             int aa= Integer.parseInt(rs.getString("RETAINED"));
             retainvalue.add(i,aa );
            // retainvalue.add(i, Integer.parseInt(rs.getString("RETAINED")));
             table_cell=new PdfPCell(new Phrase(rs.getString("INWARD"),colfont));
             my_report_table.addCell(table_cell);
             inwardvalue.add(i, rs.getString("INWARD"));
             table_cell=new PdfPCell(new Phrase(rs.getString("TOTAL"),colfont));
             my_report_table.addCell(table_cell);
             totalvalue.add(i, rs.getString("TOTAL"));
             i++;
          
          	sumr1=sumr1+Long.parseLong(rs.getString("CR"));
            sumr2=sumr2+Long.parseLong(rs.getString("EC"));
            sumr3=sumr3+Long.parseLong(rs.getString("EO"));
            sumr4=sumr4+Long.parseLong(rs.getString("ER"));
            sumr5=sumr5+Long.parseLong(rs.getString("KR"));
            sumr6=sumr6+Long.parseLong(rs.getString("NC"));
            sumr7=sumr7+Long.parseLong(rs.getString("NE"));
            sumr8=sumr8+Long.parseLong(rs.getString("NF"));
            sumr9=sumr9+Long.parseLong(rs.getString("NR"));
            sumr10=sumr10+Long.parseLong(rs.getString("NW"));
            sumr11=sumr11+Long.parseLong(rs.getString("SB"));
            sumr12=sumr12+Long.parseLong(rs.getString("SC"));
            sumr13=sumr13+Long.parseLong(rs.getString("SE"));
            sumr14=sumr14+Long.parseLong(rs.getString("SR"));
            sumr15=sumr15+Long.parseLong(rs.getString("SW"));
            sumr16=sumr16+Long.parseLong(rs.getString("WC"));
            sumr17=sumr17+Long.parseLong(rs.getString("WR"));
            sumr18=sumr18+Long.parseLong(rs.getString("RETAINED"));
            sumr19=sumr19+Long.parseLong(rs.getString("INWARD"));
            sumr20=sumr20+Long.parseLong(rs.getString("TOTAL"));
         }
         
         
         String totsum1=String.valueOf(sumr1); 
         
         String totsum2=String.valueOf(sumr2);
         String totsum3=String.valueOf(sumr3);
         String totsum4=String.valueOf(sumr4);
         String totsum5=String.valueOf(sumr5);
         String totsum6=String.valueOf(sumr6);
         String totsum7=String.valueOf(sumr7);
         String totsum8=String.valueOf(sumr8);
         String totsum9=String.valueOf(sumr9);
         String totsum10=String.valueOf(sumr10);
         String totsum11=String.valueOf(sumr11);
         String totsum12=String.valueOf(sumr12);
         String totsum13=String.valueOf(sumr13);
         String totsum14=String.valueOf(sumr14);
         String totsum15=String.valueOf(sumr15);
         String totsum16=String.valueOf(sumr16);
         String totsum17=String.valueOf(sumr17);
         String totsum18=String.valueOf(sumr18);
         String totsum19=String.valueOf(sumr19);
         String totsum20=String.valueOf(sumr20);
         
         long outward1 = sumr1 - retainvalue.get(0);
         System.out.println("outward1"+outward1);
         long outward2 = sumr2 -  retainvalue.get(1);
         System.out.println("outward1"+outward2);
         long outward3 = sumr3 -   retainvalue.get(2);
         System.out.println("outward1"+outward3);
         
         long outward4 = sumr4 - retainvalue.get(3);
         System.out.println("outward1"+outward4);
         long outward5 = sumr5 - retainvalue.get(4);
         System.out.println("outward1"+outward5);
         long outward6 = sumr6 -   retainvalue.get(5);
         System.out.println("outward1"+outward6);
         long outward7 = sumr7 - retainvalue.get(6);
         System.out.println("outward1"+outward7);
         long outward8 = sumr8 - retainvalue.get(7);
         System.out.println("outward1"+outward8);
         long outward9 = sumr9 -  retainvalue.get(8);
         System.out.println("outward1"+outward9);
         long outward10 = sumr10 -   retainvalue.get(9);
         System.out.println("outward1"+outward10);
         long outward11 = sumr11 -  retainvalue.get(10);
         System.out.println("outward1"+outward11);
         long outward12 = sumr12 -   retainvalue.get(11);
         System.out.println("outward1"+outward12);
         long outward13 = sumr13 -  retainvalue.get(12);
         System.out.println("outward1"+outward13);
         long outward14 = sumr14 -  retainvalue.get(13);
         System.out.println("outward1"+outward14);
         long outward15 = sumr15 -   retainvalue.get(14);
         System.out.println("outward1"+outward15);
         long outward16 = sumr16 -  retainvalue.get(15);
         System.out.println("outward1"+outward16);
         long outward17 = sumr17 -   retainvalue.get(16);
         long outwardsum=outward1+outward2+outward3+outward4+outward5+outward6+outward7+outward8+outward9+outward10+outward11+outward12+outward13+outward14+outward15+outward16+outward17;
         String totsum=String.valueOf(outwardsum);
         String col1 =String.valueOf(outward1);
         String col2 =String.valueOf(outward2);
         String col3 =String.valueOf(outward3);
         String col4 =String.valueOf(outward4);
         String col5 =String.valueOf(outward5);
         String col6 =String.valueOf(outward6);
         String col7 =String.valueOf(outward7);
         String col8 =String.valueOf(outward8);
         String col9 =String.valueOf(outward9);
         String col10 =String.valueOf(outward10);
         String col11 =String.valueOf(outward11);
         String col12 =String.valueOf(outward12);
         String col13 =String.valueOf(outward13);
         String col14 =String.valueOf(outward14);
         String col15 =String.valueOf(outward15);
         String col16 =String.valueOf(outward16);
         String col17 =String.valueOf(outward17);
         
         
         
         table_cell=new PdfPCell(new Phrase("Unmatched Share" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("0" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum18 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum19 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum20 ,colfont));
         my_report_table.addCell(table_cell);
         
         
         
         
         
         
         //Orignating Amount row
         table_cell=new PdfPCell(new Phrase("Orignating Amount" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum1 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum2 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum3 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum4 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum5 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum6 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum7 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum8 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum9 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum10 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum11 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum12 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum13 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum14 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum15 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum16 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum17 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum20 ,colfont));
         my_report_table.addCell(table_cell);
        
         
         
         
         table_cell=new PdfPCell(new Phrase("Outward Share" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col1 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col2 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col3 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col4 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col5 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col6 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col7 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col8 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col9 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col10 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col11 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col12 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col13 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col14 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col15 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col16 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(col17 ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum ,colfont));
         my_report_table.addCell(table_cell);
         
         
         table_cell=new PdfPCell(new Phrase("Retained Share " , colfont));
         my_report_table.addCell(table_cell);
         for(int s=0 ;s<retainvalue.size(); s++){
         table_cell=new PdfPCell(new Phrase(String.valueOf(retainvalue.get(s)) , colfont));
         my_report_table.addCell(table_cell);
         }
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum18,colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase("Inward " , colfont));
         my_report_table.addCell(table_cell);
         for(int s=0 ;s<retainvalue.size(); s++){
         table_cell=new PdfPCell(new Phrase((String) inwardvalue.get(s) , colfont));
         my_report_table.addCell(table_cell);
         }
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum19,colfont));
         my_report_table.addCell(table_cell);
         
         
         table_cell=new PdfPCell(new Phrase("Total Apport Earnings" , colfont));
         my_report_table.addCell(table_cell);
         for(int s=0 ;s<retainvalue.size(); s++){
         table_cell=new PdfPCell(new Phrase((String) totalvalue.get(s) , colfont));
         my_report_table.addCell(table_cell);
         }
         
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(" "));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum20,colfont));
         my_report_table.addCell(table_cell);
         
         
         
         my_pdf_report.add(my_report_table);                       
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
         //downloadFile(path , filename); 
         
         
         
         
         
		
	}
	
*/	
	
	public void prscateringmatrix(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		int sum=0;

		Statement stmt = con.createStatement();
		
		
		
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
	         
	         int i1=0;
	         int j=0;
	         int m=0;
	         while(rs.next()){
				 
	 			 if(m%17==0)
		    	 dbvalue[i1][0]= rs.getString("ORGRLY");//your value
	 			
		     	 dbvalue[j][i1+1]= rs.getString("CATCHG");
		     	 j++;
		     	 
		     	 m++;
		     	
		     	 if(m%17==0){
		     		 j=0;
		     		 i1++;
		     		
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
	    
	    
    
    response.setContentType("application/pdf");
	 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
	 Document my_pdf_report = new Document();
	 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
   // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
    my_pdf_report.open();    
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 5);
    Font colfont_bold = new Font(Font.FontFamily.TIMES_ROMAN, 5,Font.BOLD);
    Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC);

    Paragraph preface = new Paragraph();
   
    
    preface.add(new Paragraph("     SUMMARY OF  APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS (PRS)   By CRIS" , boldFont));
    preface.add(new Paragraph());
    preface.add(new Paragraph("                                                                                                        "+d+" - "+e+"                                                                        Date -"+modifiedDate ,normalFont));
    preface.add(new Paragraph());
    preface.add(new Paragraph("                                                                                                                                                                                                   Figures in Units" , normalFont));
   // preface.setTextAlignment(TextAlignment.JUSTIFIED);
    preface.add(new Paragraph("                                                                                                                                                                                             "));
    my_pdf_report.add(preface);
    PdfPTable my_report_table = new PdfPTable(21);
    my_report_table.setWidthPercentage(100);
    PdfPCell table_cell;
    table_cell=new PdfPCell(new Phrase("ZONE",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("CR",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("EC",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("EO",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("ER" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("KR" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("NC",colfont_bold));
    my_report_table.addCell(table_cell);
    
    table_cell=new PdfPCell(new Phrase("NE",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("NF",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("NR" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("NW" ,colfont_bold));
    my_report_table.addCell(table_cell);
    
    table_cell=new PdfPCell(new Phrase("SB" ,colfont_bold));
    my_report_table.addCell(table_cell);   
    table_cell=new PdfPCell(new Phrase("SC" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("SE" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("SR" ,colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("SW" ,colfont_bold));
    my_report_table.addCell(table_cell);
    
    table_cell=new PdfPCell(new Phrase("WC",colfont_bold));
    my_report_table.addCell(table_cell);   
    table_cell=new PdfPCell(new Phrase("WR",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("Retained Share",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("Inward Share",colfont_bold));
    my_report_table.addCell(table_cell);
    table_cell=new PdfPCell(new Phrase("Total Apport. Earnings",colfont_bold));
    my_report_table.addCell(table_cell);
    
    
    for(int rowno=0 ;rowno<23 ;rowno++){ 
		  for(int col=0 ;col<21 ;col++){
			if(col==0){
				table_cell=new PdfPCell(new Phrase(dbvalue[rowno][col],colfont_bold));
			    my_report_table.addCell(table_cell);
			} 
		  else{
			  table_cell=new PdfPCell(new Phrase(dbvalue[rowno][col],colfont));
			    my_report_table.addCell(table_cell);
		  }
			    
			    
		  }
	   }

    
    my_pdf_report.add(my_report_table);                       
    my_pdf_report.close();
    rs.close();
    stmt.close(); 
    con.close();

    
    
  }
	public void prsstnnotintrainroute(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		int sum=0;

		Statement stmt = con.createStatement();
		String strQuery = "select distinct case when  orgrly = 'CR'  	then 'Central Railway' when  orgrly = 'ER' then 'Eastern Railway' when  orgrly = 'EC' 	then 'East Central Railway'"
				+ " when  orgrly = 'EO' 	then 'East Coast Railway' when  orgrly = 'KR'  	then 'Konkan Railway' when  orgrly = 'NC'  then 'North Central Railway'"
				+ "when  orgrly = 'NE' then 'North Eastern Railway' when  orgrly = 'NF'  then 'North Frontier Railway'  when  orgrly = 'NR'  then 'Northern Railway' when  orgrly = 'NW' then 'North Western Railway'  "
				+ "when  orgrly = 'SB'  	then 'South East Central Railway' when  orgrly = 'SC'  then 'South Central Railway' when  orgrly = 'SE'	then 'South Eastern Railway' when  orgrly = 'SR'  	then 'Southern Railway' "
				+ "when  orgrly = 'SW'  then 'South Western Railway' when  orgrly = 'WC' then 'West Central Railway'  when orgrly =  'WR'  then 'Western  Railway' "
				+ "end orgrly_name,trainno,sstn,sstn9,dstn,destn9 ,case when entstn = '99' then 'null' else entstn end entstn ,dist , sum (tfare - to_number(na)) fare "
				+ "from tams_prs where mm = '"+month+"'  and yr = '"+year+"'  and orgrly='"+a+"' and  apprcnf_flag is null   "
						+ "group by orgrly,trainno,sstn,sstn9,dstn,destn9,entstn , dist "
						+ "order by trainno,sstn,dstn";
		
		

		System.out.println("strQuery >>>" + strQuery);

		ResultSet rs = stmt.executeQuery(strQuery);
		 zonename=fullnameofzone(a);
		 
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		 
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        // PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open(); 
       //  my_pdf_report.addKeywords(b);
         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
         Font boldFont_nor = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
         Font boldFont_nor_x = new Font(Font.FontFamily.TIMES_ROMAN,8, Font.NORMAL);
         

         Paragraph preface = new Paragraph();
        
         preface.add(new Paragraph("                                        PRS List of Stations not lying inTrain Route" , boldFont));
         preface.add(new Paragraph());
         preface.add(new Paragraph("                                                       "+zonename ,boldFont));
         preface.add(new Paragraph());
         
         preface.add(new Paragraph("Figures in Units                                                        "+d+" - "+e+"                                                 "+modifiedDate ,boldFont_nor));
        // preface.setTextAlignment(TextAlignment.JUSTIFIED);
         preface.setAlignment(4);
         my_pdf_report.add(preface);
         preface.add(new Paragraph(modifiedDate));
        Paragraph preface1 = new Paragraph();
         preface1.add(new Paragraph("***************************************************************************************************************"));
         my_pdf_report.add(preface1);
         PdfPTable my_report_table = new PdfPTable(8);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("Train Number",boldFont_nor));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("From Station",boldFont_nor));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("From Station Code",boldFont_nor));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("To Station",boldFont_nor));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("To Station Code",boldFont_nor));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Enroute Station",boldFont_nor));
         my_report_table.addCell(table_cell);

         table_cell=new PdfPCell(new Phrase("Distance",boldFont_nor));
         my_report_table.addCell(table_cell);

         table_cell=new PdfPCell(new Phrase("Fare",boldFont_nor));
         my_report_table.addCell(table_cell);

        // Paragraph preface2 = new Paragraph();
        // preface2.add(new Paragraph("**************************************************************************************"));
         // preface.setAlignment(Element.ALIGN_RIGHT);
         // my_pdf_report.add(preface2);
         while(rs.next()){
             table_cell=new PdfPCell(new Phrase(rs.getString("trainno"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("sstn"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("sstn9"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("dstn"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("destn9"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("entstn"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("dist"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("fare"),boldFont_nor_x));
             my_report_table.addCell(table_cell);
             
             sumr1  = sumr1 + Long.parseLong(rs.getString("fare"));
         	
         }
         
         String totsum1=String.valueOf(sumr1);
         my_pdf_report.add(my_report_table);  
         
         Paragraph preface_11 = new Paragraph();
         
         preface_11.add(new Paragraph());
         my_pdf_report.add(preface_11);
         
         
         PdfPTable my_report_table_total = new PdfPTable(2);
         my_report_table_total.setWidthPercentage(100);
         PdfPCell table_cell_sum;
         table_cell_sum=new PdfPCell(new Phrase("Total Fare" ,boldFont_nor));
         my_report_table_total.addCell(table_cell_sum);
         table_cell_sum=new PdfPCell(new Phrase(totsum1,boldFont_nor));
         my_report_table_total.addCell(table_cell_sum);
         
         my_pdf_report.add(my_report_table_total);  
         
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
       //  downloadFile(path , filename);     
	}
	
	//export pdf for PRS Statement 6A part1
	
	public void prsmatchunmatch(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		//int sum=0;
		
		
		Statement stmt = con.createStatement();
		String strQuery = "select a.orgrly rly,"
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
		

		System.out.println("strQuery >>>" + strQuery);

		ResultSet rs = stmt.executeQuery(strQuery);
		
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		
		 Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
       //  PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
         my_pdf_report.open(); 
		
		 Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		 Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 6);
		 
		 Font colfont1 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
		    
         Paragraph preface = new Paragraph();
        
         preface.add(new Paragraph("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (PRS)     By CRIS " ,boldFont));
         preface.add(new Paragraph(""));
         preface.add(new Paragraph("                                                                                                            "+d+" -  "+e , colfont1));
         preface.add(new Paragraph("                                                                                                                                                                                                                   Date : "+modifiedDate ,colfont1));
         
         preface.add(new Paragraph("                                                                                                                                                                                                                   Figures in Units",colfont1));
         
         preface.add(new Paragraph("                                                                                                                   "));
         preface.add(new Paragraph(""));
       //  PdfPTable my_report_tableindex = new PdfPTable(3);
         PdfPTable my_report_tableindex = new PdfPTable(new float[] {7 , 31,31,31 });
         my_report_tableindex.setWidthPercentage(100);
         
         
         PdfPCell table_cell1;
         
         table_cell1=new PdfPCell(new Phrase("" , colfont));
         my_report_tableindex.addCell(table_cell1);
         table_cell1=new PdfPCell(new Phrase("                                                        INPUT RECORDS" , colfont));
         my_report_tableindex.addCell(table_cell1);
         table_cell1=new PdfPCell(new Phrase("                                                         MATCH RECORDS" , colfont));
         my_report_tableindex.addCell(table_cell1);
         table_cell1=new PdfPCell(new Phrase("                                                         UNMATCH RECORDS" , colfont));
         my_report_tableindex.addCell(table_cell1);
         preface.setAlignment(4);
         my_pdf_report.add(preface);
         
         my_pdf_report.add(my_report_tableindex); 
        
        Paragraph preface1 = new Paragraph();
        // preface1.add(new Paragraph("**************************************************************************************************************************"));
        // my_pdf_report.add(preface1);
         PdfPTable my_report_table = new PdfPTable(13);
         my_report_table.setWidthPercentage(100);
         PdfPCell table_cell;
         table_cell=new PdfPCell(new Phrase("RLY" , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Records" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Fare Excl. catchg" ,colfont));
         my_report_table.addCell(table_cell);
       /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
         my_report_table.addCell(table_cell);*/
         table_cell=new PdfPCell(new Phrase("Other Charges" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Catering Chg" , colfont));
         my_report_table.addCell(table_cell);
   
         table_cell=new PdfPCell(new Phrase("Records" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Fare Excl. catchg" ,colfont));
         my_report_table.addCell(table_cell);
       /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
         my_report_table.addCell(table_cell);*/
         table_cell=new PdfPCell(new Phrase("Other Charges" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Catering Chg" , colfont));
         my_report_table.addCell(table_cell);
        
         table_cell=new PdfPCell(new Phrase("Records" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Total Fare Excl. catchg" ,colfont));
         my_report_table.addCell(table_cell);
       /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
         my_report_table.addCell(table_cell);*/
         table_cell=new PdfPCell(new Phrase("Other Charges" ,colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase("Catering Chg" , colfont));
         my_report_table.addCell(table_cell);
         
         
         while(rs.next()){
        	 
             table_cell=new PdfPCell(new Phrase(rs.getString("RLY") ,colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_FULLFARE"),colfont));
             my_report_table.addCell(table_cell);
            
             sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
          
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_TOTAL"), colfont));
             my_report_table.addCell(table_cell);
             sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
           
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr9=sumr9+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
         
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_OTHERCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr14=sumr14+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_CATGCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
         	
         }
     
        String totsum1=String.valueOf(sumr1); 
       
         String totsum2=String.valueOf(sumr2);
      //   String totsum3=String.valueOf(sumr3);
         String totsum4=String.valueOf(sumr4);
         String totsum5=String.valueOf(sumr5);
         String totsum6=String.valueOf(sumr6);
         String totsum7=String.valueOf(sumr7);
       //  String totsum8=String.valueOf(sumr8);
         String totsum9=String.valueOf(sumr9);
         String totsum10=String.valueOf(sumr10);
         String totsum11=String.valueOf(sumr11);
         String totsum12=String.valueOf(sumr12);
      //   String totsum13=String.valueOf(sumr13);
         String totsum14=String.valueOf(sumr14);
         String totsum15=String.valueOf(sumr15);
         
         table_cell=new PdfPCell(new Phrase("Total" , colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase(totsum1 , colfont));
         my_report_table.addCell(table_cell);
         
         
         table_cell=new PdfPCell(new Phrase(totsum2 , colfont));
         my_report_table.addCell(table_cell);
       
         table_cell=new PdfPCell(new Phrase(totsum4 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum5 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum6 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum7 , colfont));
         my_report_table.addCell(table_cell);
        
         table_cell=new PdfPCell(new Phrase(totsum9 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum10 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum11 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum12 , colfont));
         my_report_table.addCell(table_cell);
       
         table_cell=new PdfPCell(new Phrase(totsum14 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum15 , colfont));
         my_report_table.addCell(table_cell);
         
         
         my_pdf_report.add(my_report_table);                       
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
        // downloadFile(path ,filename); 
		
		
	}
	
	
	
	
	//export uts match un match 
	
	
	
	public void UtsMatchUnmatchOrgrlywise(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
		
		Statement stmt = con.createStatement();
		String strQuery = "select a.orgrly rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
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
		

		System.out.println("strQuery >>>" + strQuery);

		ResultSet rs = stmt.executeQuery(strQuery);
		
		
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		
		Document my_pdf_report = new Document();
		PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        //PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
        my_pdf_report.open(); 
		
		 Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		 Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 6);
		 
		 Font colfont1 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
		    
        Paragraph preface = new Paragraph();
       
        preface.add(new Paragraph("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)     By CRIS " ,boldFont));
        preface.add(new Paragraph(""));
        preface.add(new Paragraph("                                                                                                  (Originating zone wise)",colfont1));
        preface.add(new Paragraph("                                                                                                            "+d+" -  "+e , colfont1));
        preface.add(new Paragraph("                                                                                                                                                                                                                   Date : "+modifiedDate ,colfont1));
        
        preface.add(new Paragraph("                                                                                                                                                                                                                   Figures in Units",colfont1));
        
        preface.add(new Paragraph("                                                                                                                   "));
        preface.add(new Paragraph(""));
      //  PdfPTable my_report_tableindex = new PdfPTable(3);
        PdfPTable my_report_tableindex = new PdfPTable(new float[] {7 , 31,31,31 });
        my_report_tableindex.setWidthPercentage(100);
        
        
        PdfPCell table_cell1;
        
        table_cell1=new PdfPCell(new Phrase("" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                        INPUT RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                         MATCH RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                         UNMATCH RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        preface.setAlignment(4);
        my_pdf_report.add(preface);
        
        my_pdf_report.add(my_report_tableindex); 
       
       Paragraph preface1 = new Paragraph();
       // preface1.add(new Paragraph("**************************************************************************************************************************"));
       // my_pdf_report.add(preface1);
        PdfPTable my_report_table = new PdfPTable(13);
        my_report_table.setWidthPercentage(100);
        PdfPCell table_cell;
        table_cell=new PdfPCell(new Phrase("RLY" , colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
  
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
       
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
         
         
         while(rs.next()){
        	 
             table_cell=new PdfPCell(new Phrase(rs.getString("RLY") ,colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_FULLFARE"),colfont));
             my_report_table.addCell(table_cell);
            
             sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));*/
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_TOTAL"), colfont));
             my_report_table.addCell(table_cell);
             sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));*/
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_OTHERCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_CATGCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));*/
         	
         }
     
         String totsum1=String.valueOf(sumr1); 
         String totsum2=String.valueOf(sumr2);
         String totsum3=String.valueOf(sumr3);
         String totsum4=String.valueOf(sumr4);
        
         String totsum5=String.valueOf(sumr5);
         String totsum6=String.valueOf(sumr6);
         String totsum7=String.valueOf(sumr7);
         String totsum8=String.valueOf(sumr8);
         
         String totsum9=String.valueOf(sumr9);
         String totsum10=String.valueOf(sumr10);
         String totsum11=String.valueOf(sumr11);
         String totsum12=String.valueOf(sumr12);
        
         
         table_cell=new PdfPCell(new Phrase("Total" , colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase(totsum1 , colfont));
         my_report_table.addCell(table_cell);
         
         
         table_cell=new PdfPCell(new Phrase(totsum2 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum3 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum4 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum5 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum6 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum7 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum8 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum9 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum10 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum11 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum12 , colfont));
         my_report_table.addCell(table_cell);
         /*table_cell=new PdfPCell(new Phrase(totsum15 , colfont));
         my_report_table.addCell(table_cell);*/
         
         
         my_pdf_report.add(my_report_table);                       
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
      //   downloadFile(path ,filename); 
		
		
	}

//************************************************Method of UTS Match Unmatch Booking zone wise************************************************************************
	

	public void UtsMatchUnmatchBookingwise(String a, String b, String c, String d, String e) throws Exception
	{
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".pdf";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		Statement stmt = con.createStatement();
		String strQuery = "select a.zone rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
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
		System.out.println("strQuery >>>" + strQuery);
		ResultSet rs = stmt.executeQuery(strQuery);
		
		
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
		
		Document my_pdf_report = new Document();
		 PdfWriter.getInstance(my_pdf_report, response.getOutputStream());
        my_pdf_report.open(); 
		
		 Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		 Font colfont = new Font(Font.FontFamily.TIMES_ROMAN, 6);
		 
		 Font colfont1 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
		    
        Paragraph preface = new Paragraph();
       
        preface.add(new Paragraph("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)     By CRIS " ,boldFont));
        preface.add(new Paragraph(""));
        preface.add(new Paragraph("                                                                                                           (Booking zone wise)",colfont1));
        preface.add(new Paragraph("                                                                                                            "+d+" -  "+e , colfont1));
        preface.add(new Paragraph("                                                                                                                                                                                                                   Date : "+modifiedDate ,colfont1));
        
        preface.add(new Paragraph("                                                                                                                                                                                                                   Figures in Units",colfont1));
        
        preface.add(new Paragraph("                                                                                                                   "));
        preface.add(new Paragraph(""));
      //  PdfPTable my_report_tableindex = new PdfPTable(3);
        PdfPTable my_report_tableindex = new PdfPTable(new float[] {7 , 31,31,31 });
        my_report_tableindex.setWidthPercentage(100);
        
        
        PdfPCell table_cell1;
        
        table_cell1=new PdfPCell(new Phrase("" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                        INPUT RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                         MATCH RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        table_cell1=new PdfPCell(new Phrase("                                                         UNMATCH RECORDS" , colfont));
        my_report_tableindex.addCell(table_cell1);
        preface.setAlignment(4);
        my_pdf_report.add(preface);
        
        my_pdf_report.add(my_report_tableindex); 
       
       Paragraph preface1 = new Paragraph();
       // preface1.add(new Paragraph("**************************************************************************************************************************"));
       // my_pdf_report.add(preface1);
        PdfPTable my_report_table = new PdfPTable(13);
        my_report_table.setWidthPercentage(100);
        PdfPCell table_cell;
        table_cell=new PdfPCell(new Phrase("RLY" , colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
  
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
       
        table_cell=new PdfPCell(new Phrase("Records" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Full Fare" ,colfont));
        my_report_table.addCell(table_cell);
      /*  table_cell=new PdfPCell(new Phrase("SAFETY CHARGES" ,colfont));
        my_report_table.addCell(table_cell);*/
        table_cell=new PdfPCell(new Phrase("Safety Charges" ,colfont));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("Other Charges" , colfont));
        my_report_table.addCell(table_cell);
         
         
         while(rs.next()){
        	 
             table_cell=new PdfPCell(new Phrase(rs.getString("RLY") ,colfont));
             my_report_table.addCell(table_cell);
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_FULLFARE"),colfont));
             my_report_table.addCell(table_cell);
            
             sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("INPUT_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));*/
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_TOTAL"), colfont));
             my_report_table.addCell(table_cell);
             sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_OTHERCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("MATCH_CATGCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));*/
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_TOTAL") , colfont));
             my_report_table.addCell(table_cell);
             sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_FULLFARE") , colfont));
             my_report_table.addCell(table_cell);
             sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_SAFETYCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
             table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_OTHERCHG"), colfont));
             my_report_table.addCell(table_cell);
             sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
             /*table_cell=new PdfPCell(new Phrase(rs.getString("UNMATCH_CATGCHG") , colfont));
             my_report_table.addCell(table_cell);
             sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));*/
         	
         }
     
         String totsum1=String.valueOf(sumr1); 
         String totsum2=String.valueOf(sumr2);
         String totsum3=String.valueOf(sumr3);
         String totsum4=String.valueOf(sumr4);
        
         String totsum5=String.valueOf(sumr5);
         String totsum6=String.valueOf(sumr6);
         String totsum7=String.valueOf(sumr7);
         String totsum8=String.valueOf(sumr8);
         
         String totsum9=String.valueOf(sumr9);
         String totsum10=String.valueOf(sumr10);
         String totsum11=String.valueOf(sumr11);
         String totsum12=String.valueOf(sumr12);
        
         
         table_cell=new PdfPCell(new Phrase("Total" , colfont));
         my_report_table.addCell(table_cell);
         
         table_cell=new PdfPCell(new Phrase(totsum1 , colfont));
         my_report_table.addCell(table_cell);
         
         
         table_cell=new PdfPCell(new Phrase(totsum2 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum3 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum4 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum5 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum6 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum7 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum8 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum9 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum10 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum11 , colfont));
         my_report_table.addCell(table_cell);
         table_cell=new PdfPCell(new Phrase(totsum12 , colfont));
         my_report_table.addCell(table_cell);
         /*table_cell=new PdfPCell(new Phrase(totsum15 , colfont));
         my_report_table.addCell(table_cell);*/
         
         
         my_pdf_report.add(my_report_table);                       
         my_pdf_report.close();
         rs.close();
         stmt.close(); 
         con.close(); 
         
       //  downloadFile(path ,filename); 
		
		
	}

	
	public String fullnameofzone(String zone){
		
		if(zone.equalsIgnoreCase("CR")){
			zone="Central Railway";
		}
		if(zone.equalsIgnoreCase("NR")){
			zone="Northen Railway";
		}
		
		if(zone.equalsIgnoreCase("KR")){
			zone="Konkan Railway";
		}
		
		if(zone.equalsIgnoreCase("WR")){
			zone="Western Railway";
		}
		
		if(zone.equalsIgnoreCase("SR")){
			zone="Southern Railway";
		}
		
		if(zone.equalsIgnoreCase("ER")){
			zone="Eastern Railway";
		}
		
		if(zone.equalsIgnoreCase("EO")){
			zone="East Coast Railway";
		}
		
		if(zone.equalsIgnoreCase("EC")){
			zone="East Central Railway";
		}
		
		
		if(zone.equalsIgnoreCase("NC")){
			zone="North Central Railway";
		}
		
		if(zone.equalsIgnoreCase("NE")){
			zone="North Eastern Railway";
		}
		
		if(zone.equalsIgnoreCase("NF")){
			zone="Northeast Frontier Railway";
		}
		
		if(zone.equalsIgnoreCase("NW")){
			zone="North Western Railway";
		}
		
		if(zone.equalsIgnoreCase("SB")){
			zone="South East Central Railway";
		}
		
		if(zone.equalsIgnoreCase("SC")){
			zone="South Central Railway";
		}
		
		if(zone.equalsIgnoreCase("SE")){
			zone="South Eastern Railway";
		}
		
		if(zone.equalsIgnoreCase("SR")){
			zone="Southern Railway";
		}
		
		if(zone.equalsIgnoreCase("SW")){
			zone="South Western Railway";
		}
		
		if(zone.equalsIgnoreCase("SR")){
			zone="Southern Railway";
		}
		
		if(zone.equalsIgnoreCase("WC")){
			zone="West Central Railway";
		}
		
		
		
		
		
		return zone;
		
	}
	
	
	@SuppressWarnings("resource")
	public void downloadFile(String path , String filename ) throws MalformedURLException, IOException{
		
		
		
	  File my_file = new File(path); // We are downloa check - check.doc
	    
        HttpServletResponse response = ServletActionContext.getResponse();
       response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".pdf\"");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
         in.close();
        out.flush();
				 
        
        if(my_file.delete()){
			System.out.println("folder is deleted");
		}
        
        
        
	}
}
