/*

package tams.action;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import tamsdbconnection.tamsdbconnection;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class ExportExcel {
	HttpServletResponse response = ServletActionContext.getResponse();
	
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
	long sumr20=0 , sumr21=0 , sumr22=0,sumr23 =0;
	String zonename;
	
	Viewdata view_data_obj = new Viewdata();
	
	tamsdbconnection dbcon;
	Connection con = null;
	String serachtext;
	
	Date date = new Date();
	String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);

	@SuppressWarnings("deprecation")
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
				+ " X.Total_shared Total_shared,X.Diff Diff "
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
		 
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
			sheet.autoSizeColumn((short) 0);
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			rowhead1.createCell((short) 1).setCellValue("PRS PRTNFILE ");//PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE By CRIS
			sheet.autoSizeColumn((short) 1);
			rowhead1.createCell((short) 2).setCellValue("REPORT");
			sheet.autoSizeColumn((short) 2);
			rowhead1.createCell((short) 5).setCellValue(zonename);
			sheet.autoSizeColumn((short) 5);
			rowhead1.createCell((short) 8).setCellValue("Date");
			rowhead1.createCell((short) 9).setCellValue(modifiedDate);
			
			
			
			HSSFRow rowhead2 = sheet.createRow((short) 2);
			rowhead2.createCell((short) 1).setCellValue("Figures in");
			sheet.autoSizeColumn((short) 1);
			rowhead2.createCell((short) 2).setCellValue("Units");
			rowhead2.createCell((short) 4).setCellValue("PASSENGER");//PASSENGER APPORTIONMENT OF PRS FOR THE MONTH OF
			rowhead2.createCell((short) 5).setCellValue("APPORTIONMENT");
			sheet.autoSizeColumn((short) 5);
			rowhead2.createCell((short) 7).setCellValue("OF PRS FOR");
			sheet.autoSizeColumn((short) 7);
			rowhead2.createCell((short) 8).setCellValue("THE MONTH OF");
			sheet.autoSizeColumn((short) 8);
			rowhead2.createCell((short) 9).setCellValue(d+"-");
			rowhead2.createCell((short) 10).setCellValue(e);
			
			rowhead2.createCell((short) 12).setCellValue("BY CRIS");
			sheet.autoSizeColumn((short) 12);
			int index = 5;
			int sno = 0;
			String name = "";
			
			
			  HSSFRow rowhead =sheet.createRow((short) 4); 
			  rowhead.createCell((short) 0).setCellValue("RLY TO"); //Gauge Code
			  rowhead.createCell((short)1).setCellValue("Gauge Code"); 
			  rowhead.createCell((short)2).setCellValue("Amount");
			  rowhead.createCell((short)3).setCellValue("CR"); 
			  rowhead.createCell((short)4).setCellValue("EC");
			  rowhead.createCell((short) 5).setCellValue("EO"); 
			  rowhead.createCell((short)6).setCellValue("ER"); 
			  rowhead.createCell((short)7).setCellValue("KR");
			  rowhead.createCell((short)8).setCellValue("NC"); 
			  rowhead.createCell((short)9).setCellValue("NE");
			  rowhead.createCell((short)10).setCellValue("NF"); 
			  rowhead.createCell((short)11).setCellValue("NR");
			  rowhead.createCell((short)12).setCellValue("NW"); 
			  rowhead.createCell((short)13).setCellValue("SB");
			  rowhead.createCell((short) 14).setCellValue("SC"); 
			  rowhead.createCell((short)15).setCellValue("SE"); 
			  rowhead.createCell((short)16).setCellValue("SR");
			  rowhead.createCell((short)17).setCellValue("SW"); 
			  rowhead.createCell((short)18).setCellValue("WC");
			  rowhead.createCell((short)19).setCellValue("WR");
			  rowhead.createCell((short)20).setCellValue("TOTAL SHARED");
			  rowhead.createCell((short)21).setCellValue("DIFF");
			  
		
			while (rs.next()) {
				
				System.out.println("m is"+m);
			
		   		
		   		if((m%3)==0  && m!=0)
		   		{
		   			int in=index;
		   			index++;
		   			HSSFRow rowtr= sheet.createRow((short) in);
		   		
		   			rowtr.createCell((short) 0).setCellValue("");
		   			rowtr.createCell((short) 1).setCellValue("TOT CRDT");
		   			rowtr.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
		   			rowtr.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
		   			rowtr.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
		   			rowtr.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
		   			rowtr.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
		   			rowtr.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
		   			rowtr.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
		   			rowtr.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
		   			rowtr.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
		   			rowtr.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
		   			rowtr.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
		   			rowtr.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
		   			rowtr.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
		   			rowtr.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
		   			rowtr.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
		   			rowtr.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
		   			rowtr.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
		   			rowtr.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
		   			rowtr.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
		   			rowtr.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));	
				
		   		}
		   		
		   		
		   		
		   		
		   		
		   		HSSFRow row = sheet.createRow((short) index);

				if((m%3)==0) row.createCell((short) 0).setCellValue(rs.getString("torly")); 
				else row.createCell((short) 0).setCellValue((""));
		   		
				
		   		row.createCell((short) 1).setCellValue(rs.getString("orgg"));
	   			row.createCell((short) 2).setCellValue(rs.getString("Amount"));
	   			row.createCell((short) 3).setCellValue(rs.getString("CR"));
	   			row.createCell((short) 4).setCellValue(rs.getString("EC"));
	   			row.createCell((short) 5).setCellValue(rs.getString("EO"));
	   			row.createCell((short) 6).setCellValue(rs.getString("ER"));
	   			row.createCell((short) 7).setCellValue(rs.getString("KR"));
	   			row.createCell((short) 8).setCellValue(rs.getString("NC"));
	   			row.createCell((short) 9).setCellValue(rs.getString("NE"));
	   			row.createCell((short) 10).setCellValue(rs.getString("NF"));
	   			row.createCell((short) 11).setCellValue(rs.getString("NR"));
	   			row.createCell((short) 12).setCellValue(rs.getString("NW"));
	   			row.createCell((short) 13).setCellValue(rs.getString("SB"));
	   			row.createCell((short) 14).setCellValue(rs.getString("SC"));
	   			row.createCell((short) 15).setCellValue(rs.getString("SE"));
	   			row.createCell((short) 16).setCellValue(rs.getString("SR"));
	   			row.createCell((short) 17).setCellValue(rs.getString("SW"));
	   			row.createCell((short) 18).setCellValue(rs.getString("WC"));
	   			row.createCell((short) 19).setCellValue(rs.getString("WR"));
	   			row.createCell((short) 20).setCellValue(rs.getString("Total_shared"));
	   			row.createCell((short) 21).setCellValue(rs.getString("Diff"));
				
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
		   		
		   		index++;
		   		++m;
		   		
		   		
			}
			
			
			
			

			HSSFRow rowtotal = sheet.createRow((short) index);
			
			
			rowtotal.createCell((short) 0).setCellValue("");
			rowtotal.createCell((short) 1).setCellValue("TOT CRDT");
			rowtotal.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
			rowtotal.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
			rowtotal.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
			rowtotal.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
			rowtotal.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
			rowtotal.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
			rowtotal.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
			rowtotal.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
			rowtotal.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
			rowtotal.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
			rowtotal.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
			rowtotal.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
			rowtotal.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
			rowtotal.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
			rowtotal.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
			rowtotal.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
			rowtotal.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
			rowtotal.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
			rowtotal.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
			rowtotal.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));
        
            
			con.close();
			OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
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
		 
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
			sheet.autoSizeColumn((short) 0);
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			rowhead1.createCell((short) 1).setCellValue("UTS PRTNFILE ");//PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE By CRIS
			sheet.autoSizeColumn((short) 1);
			rowhead1.createCell((short) 2).setCellValue("REPORT");
			sheet.autoSizeColumn((short) 2);
			rowhead1.createCell((short) 5).setCellValue(zonename);
			sheet.autoSizeColumn((short) 5);
			rowhead1.createCell((short) 8).setCellValue("Date");
			rowhead1.createCell((short) 9).setCellValue(modifiedDate);
			
			
			
			HSSFRow rowhead2 = sheet.createRow((short) 2);
			rowhead2.createCell((short) 1).setCellValue("Figures in");
			sheet.autoSizeColumn((short) 1);
			rowhead2.createCell((short) 2).setCellValue("Units");
			rowhead2.createCell((short) 4).setCellValue("PASSENGER");//PASSENGER APPORTIONMENT OF PRS FOR THE MONTH OF
			rowhead2.createCell((short) 5).setCellValue("APPORTIONMENT");
			sheet.autoSizeColumn((short) 5);
			rowhead2.createCell((short) 7).setCellValue("OF UTS FOR");
			sheet.autoSizeColumn((short) 7);
			rowhead2.createCell((short) 8).setCellValue("THE MONTH OF");
			sheet.autoSizeColumn((short) 8);
			rowhead2.createCell((short) 9).setCellValue(d+"-");
			rowhead2.createCell((short) 10).setCellValue(e);
			
			rowhead2.createCell((short) 12).setCellValue("BY CRIS");
			sheet.autoSizeColumn((short) 12);
			int index = 5;
			int sno = 0;
			String name = "";
			
			
			  HSSFRow rowhead =sheet.createRow((short) 4); 
			  rowhead.createCell((short) 0).setCellValue("RLY TO"); //Gauge Code
			  rowhead.createCell((short)1).setCellValue("Gauge Code"); 
			  rowhead.createCell((short)2).setCellValue("Amount");
			  rowhead.createCell((short)3).setCellValue("CR"); 
			  rowhead.createCell((short)4).setCellValue("EC");
			  rowhead.createCell((short) 5).setCellValue("EO"); 
			  rowhead.createCell((short)6).setCellValue("ER"); 
			  rowhead.createCell((short)7).setCellValue("KR");
			  rowhead.createCell((short)8).setCellValue("NC"); 
			  rowhead.createCell((short)9).setCellValue("NE");
			  rowhead.createCell((short)10).setCellValue("NF"); 
			  rowhead.createCell((short)11).setCellValue("NR");
			  rowhead.createCell((short)12).setCellValue("NW"); 
			  rowhead.createCell((short)13).setCellValue("SB");
			  rowhead.createCell((short) 14).setCellValue("SC"); 
			  rowhead.createCell((short)15).setCellValue("SE"); 
			  rowhead.createCell((short)16).setCellValue("SR");
			  rowhead.createCell((short)17).setCellValue("SW"); 
			  rowhead.createCell((short)18).setCellValue("WC");
			  rowhead.createCell((short)19).setCellValue("WR");
			  rowhead.createCell((short)20).setCellValue("TOTAL SHARED");
			  rowhead.createCell((short)21).setCellValue("DIFF");
			  
		
			while (rs.next()) {
				
				System.out.println("m is"+m);
			
		   		
		   		if((m%4)==0  && m!=0)
		   		{
		   			int in=index;
		   			index++;
		   			HSSFRow rowtr= sheet.createRow((short) in);
		   		
		   			rowtr.createCell((short) 0).setCellValue("");
		   			rowtr.createCell((short) 1).setCellValue("TOT CRDT");
		   			rowtr.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
		   			rowtr.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
		   			rowtr.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
		   			rowtr.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
		   			rowtr.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
		   			rowtr.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
		   			rowtr.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
		   			rowtr.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
		   			rowtr.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
		   			rowtr.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
		   			rowtr.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
		   			rowtr.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
		   			rowtr.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
		   			rowtr.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
		   			rowtr.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
		   			rowtr.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
		   			rowtr.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
		   			rowtr.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
		   			rowtr.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
		   			rowtr.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));	
				
		   		}
		   		
		   		
		   		
		   		
		   		
		   		HSSFRow row = sheet.createRow((short) index);

				if((m%4)==0) row.createCell((short) 0).setCellValue(rs.getString("dstnrly")); 
				else row.createCell((short) 0).setCellValue((""));
		   		
				
		   		row.createCell((short) 1).setCellValue(rs.getString("FROM_GUAGE"));
	   			row.createCell((short) 2).setCellValue(rs.getString("Amount"));
	   			row.createCell((short) 3).setCellValue(rs.getString("CR"));
	   			row.createCell((short) 4).setCellValue(rs.getString("EC"));
	   			row.createCell((short) 5).setCellValue(rs.getString("EO"));
	   			row.createCell((short) 6).setCellValue(rs.getString("ER"));
	   			row.createCell((short) 7).setCellValue(rs.getString("KR"));
	   			row.createCell((short) 8).setCellValue(rs.getString("NC"));
	   			row.createCell((short) 9).setCellValue(rs.getString("NE"));
	   			row.createCell((short) 10).setCellValue(rs.getString("NF"));
	   			row.createCell((short) 11).setCellValue(rs.getString("NR"));
	   			row.createCell((short) 12).setCellValue(rs.getString("NW"));
	   			row.createCell((short) 13).setCellValue(rs.getString("SB"));
	   			row.createCell((short) 14).setCellValue(rs.getString("SC"));
	   			row.createCell((short) 15).setCellValue(rs.getString("SE"));
	   			row.createCell((short) 16).setCellValue(rs.getString("SR"));
	   			row.createCell((short) 17).setCellValue(rs.getString("SW"));
	   			row.createCell((short) 18).setCellValue(rs.getString("WC"));
	   			row.createCell((short) 19).setCellValue(rs.getString("WR"));
	   			row.createCell((short) 20).setCellValue(rs.getString("Total_shared"));
	   			row.createCell((short) 21).setCellValue(rs.getString("Diff"));
				
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
		   		
		   		index++;
		   		++m;
		   		
		   		
			}
			
			
			
			

			HSSFRow rowtotal = sheet.createRow((short) index);
			
			
			rowtotal.createCell((short) 0).setCellValue("");
			rowtotal.createCell((short) 1).setCellValue("TOT CRDT");
			rowtotal.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
			rowtotal.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
			rowtotal.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
			rowtotal.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
			rowtotal.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
			rowtotal.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
			rowtotal.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
			rowtotal.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
			rowtotal.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
			rowtotal.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
			rowtotal.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
			rowtotal.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
			rowtotal.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
			rowtotal.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
			rowtotal.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
			rowtotal.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
			rowtotal.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
			rowtotal.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
			rowtotal.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
			rowtotal.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));
        
            
			
			OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
			  
	}
	
	
	
	public void exportcateringchgzonewisetrainwise(String a, String b, String c, String d, String e) throws Exception {

		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		tamsdbconnection dbcon = new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone is " + a);
		 zonename=fullnameofzone(a);
	//String filename = "/ARMSFILES/sheet/" + a + " " + b + " " + d	+ " " + e + ".xls";
		String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
	


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
		
		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet sheet = hwb.createSheet("new sheet");
		sheet.autoSizeColumn((short) 0);
		HSSFRow rowhead1 = sheet.createRow((short) 1);
		rowhead1.createCell((short) 3).setCellValue("PRS CATERING");//PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE By CRIS
		sheet.autoSizeColumn((short) 3);
		rowhead1.createCell((short) 4).setCellValue("CHARGE APPORTIONMENT");
		sheet.autoSizeColumn((short) 4);
		rowhead1.createCell((short) 5).setCellValue("REPORT ZONE");
		sheet.autoSizeColumn((short) 5);
		rowhead1.createCell((short) 6).setCellValue("WISE TRAIN WISE");
		sheet.autoSizeColumn((short) 6);
		rowhead1.createCell((short) 9).setCellValue("BY CRIS");
		sheet.autoSizeColumn((short) 9);
		
		

		HSSFRow rowhead2 = sheet.createRow((short) 2);
		rowhead2.createCell((short) 5).setCellValue(d+"-");
		rowhead2.createCell((short) 6).setCellValue(e);
		rowhead2.createCell((short) 8).setCellValue("Date");
		rowhead2.createCell((short) 9).setCellValue(modifiedDate);
		
		
		rowhead1.createCell((short) 4).setCellValue(a);
	
		HSSFRow rowhead = sheet.createRow((short) 3);
		rowhead.createCell((short) 3).setCellValue("S_No");
		rowhead.createCell((short) 4).setCellValue("Train Number");
		sheet.autoSizeColumn((short) 4);
		rowhead.createCell((short) 5).setCellValue("CatChg");
		sheet.autoSizeColumn((short) 5);

		int index = 4;
		int sno = 0;
		String name = "";
	
		while (rs.next()) {
			System.out.println("inside while is" + rs.getString(1));
			sno++;

			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 3).setCellValue(rs.getString(1));
			row.createCell((short) 4).setCellValue(rs.getString(2));
			System.out.println("train no is" + rs.getString(1));
			row.createCell((short) 5).setCellValue(rs.getString(3));
			sumr1=sumr1+Long.parseLong(rs.getString(3));
			System.out.println("catering no is" + rs.getString(2));
			index++;
			
			
			
		}


		HSSFRow rowtot = sheet.createRow((short) index);
		rowtot.createCell((short) 4).setCellValue("TOTAL");
		rowtot.createCell((short) 5).setCellValue(sumr1);
		
		
		
		
		OutputStream outObject = response.getOutputStream();
		 response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
		//write this workbook to an Outputstream.
		hwb.write(outObject);
		outObject.flush();
		outObject.close();
		con.close();
		
		
		  
		  
			//downloadFile(filename , b);
		  
	}
	
	public void  prs_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		
		ArrayList<Integer> retainevalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> inwardvalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> totalvalue =new 	ArrayList<Integer>() ;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
			
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
			

			System.out.println("strQuery >>>" + query);
			
			  ResultSet rs = stmt.executeQuery(query);
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
				 System.out.println("sum1"+sumr1);
				 System.out.println("sum2"+sumr2);
				  System.out.println("sum3"+sumr3);
				   System.out.println("sum4"+sumr4);
				   
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
					  dbvalue_trans[z][18]=String.valueOf(Long.parseLong(dbvalue_trans[z][19]) - Long.parseLong(dbvalue_trans[z][17]));
				  }
				  
				   for(int k =1; k< 18 ; k++)//Long.parseLong(dbvalue_trans[18][column]
			    	{
			           sumr18= sumr18 + Long.parseLong(dbvalue_trans[k][17]);
			           sumr19=sumr19 + Long.parseLong(dbvalue_trans[k][18]);
			           sumr20=sumr20 + Long.parseLong(dbvalue_trans[k][19]);
			           sumr21=sumr21 + Long.parseLong(dbvalue_trans[20][k-1]);
			           sumr22=sumr22 + Long.parseLong(dbvalue_trans[18][k-1]);
			           
			    	}
				   
				   //printing second array (Transpose array)
					 System.out.println( "second array");
					 for (int r=0; r< nCol; r++) {
					     for (int q=0; q<18; q++) {
					    	 System.out.print(dbvalue_trans[r][q] + " ");
					         
					     }
					     System.out.println();
					 }
					 
				 
				 
			  
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
				HSSFRow rowhead1 = sheet.createRow((short) 1);
				HSSFCellStyle style = hwb.createCellStyle();
				HSSFFont font = hwb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setFontHeightInPoints((short)10);
				font.setBoldweight((short) 10);//.setBold(true);
				style.setFont(font);
				rowhead1.createCell((short) 3).setCellValue("SUMMARY OF");//APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS            By CRIS"
				sheet.autoSizeColumn((short) 3);
				rowhead1.createCell((short) 4).setCellValue("PASSENGER");//SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS UTS   
				sheet.autoSizeColumn((short) 4);
				rowhead1.createCell((short) 5).setCellValue("APPORTIONMENT");
				sheet.autoSizeColumn((short) 5);
				rowhead1.createCell((short) 6).setCellValue(" FOR ALL");
				sheet.autoSizeColumn((short) 6);
				rowhead1.createCell((short) 7).setCellValue("INDIAN");
				rowhead1.createCell((short) 8).setCellValue("RAILWAYS");
				rowhead1.createCell((short) 9).setCellValue("RAILWAYS");
				sheet.autoSizeColumn((short) 9);
				rowhead1.createCell((short) 10).setCellValue("PRS");
				rowhead1.createCell((short) 11).setCellValue("");
				rowhead1.createCell((short) 12).setCellValue("");
				rowhead1.createCell((short) 13).setCellValue("");
				rowhead1.createCell((short) 14).setCellValue("");
				rowhead1.createCell((short) 15).setCellValue("");
				rowhead1.createCell((short) 16).setCellValue("");
				rowhead1.createCell((short) 17).setCellValue("");
				rowhead1.createCell((short) 18).setCellValue("");
				rowhead1.createCell((short) 19).setCellValue("BY");
				rowhead1.createCell((short) 20).setCellValue("CRIS");
				//rowhead1.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowhead11 =sheet.createRow((short)2); 
				 rowhead11.createCell((short) 8).setCellValue(d);
				 rowhead11.createCell((short) 9).setCellValue(e);
				 
				 rowhead11.createCell((short) 19).setCellValue("Date");
				 
				 rowhead11.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowheadfig =sheet.createRow((short)3); 
				 rowheadfig.createCell((short) 19).setCellValue("Figures in");
				 rowheadfig.createCell((short) 20).setCellValue("000s");
				  HSSFRow rowhead =sheet.createRow((short)4); 
				  rowhead.createCell((short) 0).setCellValue("ZONE"); 
				  rowhead.createCell((short)1).setCellValue("CR"); 
				  rowhead.createCell((short)2).setCellValue("EC");
				  rowhead.createCell((short)3).setCellValue("EO"); 
				  rowhead.createCell((short)4).setCellValue("ER");
				  rowhead.createCell((short) 5).setCellValue("KR"); 
				  rowhead.createCell((short)6).setCellValue("NC"); 
				  rowhead.createCell((short)7).setCellValue("NE");
				  rowhead.createCell((short)8).setCellValue("NF"); 
				  rowhead.createCell((short)9).setCellValue("NR");
				  rowhead.createCell((short)10).setCellValue("NW"); 
				  rowhead.createCell((short)11).setCellValue("SB");
				  rowhead.createCell((short)12).setCellValue("SC"); 
				  rowhead.createCell((short)13).setCellValue("SE");
				  rowhead.createCell((short) 14).setCellValue("SR"); 
				  rowhead.createCell((short)15).setCellValue("SW"); 
				  rowhead.createCell((short)16).setCellValue("WC");
				  rowhead.createCell((short)17).setCellValue("WR"); 
				  rowhead.createCell((short)18).setCellValue("Retained Share");
				  rowhead.createCell((short)19).setCellValue("Inward Share");
				  rowhead.createCell((short)20).setCellValue("Total Apport. Earning");
				  
				  int index=6; int sno=0; String name="";
				  
				  int jj=1;
				 
				    while(jj<18) {
				    	 int colindex=1;
				    	
				    	 HSSFRow row = sheet.createRow((short)index); 
				    	 row.createCell((short) 0).setCellValue(dbvalue[jj-1][0]);
				    	
				    	 
				    	 for(int kk =0; kk< 20 ; kk++)
				        	{
				    		 String vlaue=dbvalue_trans[jj][kk];
				    		 System.out.println("value is"+ vlaue);
				    		 row.createCell((short) colindex).setCellValue(vlaue);
				    		 colindex++;
				        	}
				    	 
				    	 jj++;
				    	 index++;
				    	
				    }
				    
				    
				    
				    HSSFRow rowus = sheet.createRow((short) 24);
				    for(int k3 =0; k3< 18 ; k3++)
			    	{
				    	
						 rowus.createCell((short)k3).setCellValue(""); 
						 
			    	}
				    rowus.createCell((short)18).setCellValue(sumr18); 
				    rowus.createCell((short)19).setCellValue(sumr19); 
				    rowus.createCell((short)20).setCellValue(sumr20); 
				
				
				    
				    
				    //OGA ROW
				    HSSFRow rowoga = sheet.createRow((short) 25);
				    rowoga.createCell((short) 0).setCellValue("OGA");
				    
				    int cindex=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowoga.createCell((short) cindex).setCellValue(dbvalue_trans[18][k4]); 
			        	cindex++;
			    	}
			        rowoga.createCell((short) cindex).setCellValue(sumr22);
			        
			        
			        //
			        
			        HSSFRow rowretain = sheet.createRow((short) 26);
			        rowretain.createCell((short) 0).setCellValue("Retained");
				    int zz=1;
				    for(int k6 =0; k6< 19 ; k6++)
			    	{
				    	rowretain.createCell((short) zz).setCellValue(dbvalue_trans[19][k6]);
				    	 zz++;
			    	}
				    
				    //rowretain.createCell((short) zz--).setCellValue("");
				    //rowretain.createCell((short) zz++).setCellValue("");
				    rowretain.createCell((short) zz).setCellValue(sumr18);
			        
				    
				    HSSFRow rowoutward = sheet.createRow((short) 27);
				    rowoutward.createCell((short) 0).setCellValue("OUTWARD");
				    int tt=1;
				    for(int k =0; k< 19 ; k++)
			    	{
				    	rowoutward.createCell((short) tt).setCellValue(dbvalue_trans[20][k]);
				    	tt++;
				    	
			    	}
				    
				    //rowoutward.createCell((short) tt++).setCellValue("");
				   // rowoutward.createCell((short) tt++).setCellValue("");
				    rowoutward.createCell((short) tt).setCellValue(sumr21);
				    
				    
				    
				    HSSFRow rowinward = sheet.createRow((short) 28);
				    rowinward.createCell((short) 0).setCellValue("INWARD");
				    int ss=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowinward.createCell((short) ss).setCellValue(dbvalue_trans[k][18]);
				    	ss++;
			    	}
				    
				   rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue(sumr19);
			        
				    HSSFRow rowtot = sheet.createRow((short) 29);
				    rowtot.createCell((short) 0).setCellValue("Total Apport Earnings");
				    int uu=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowtot.createCell((short) uu).setCellValue(dbvalue_trans[k][19]);
				    	uu++;
			    	}
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue(sumr20);
			        
			        
			        
			        
			        OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
			
	}
	
	public void utsunmatchedodpairs(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		 zonename=fullnameofzone(a);
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
		  filename = filename.replace(",", "");
		  Statement stmt = con.createStatement();

		  String query=" select station_from,station_from_code,station_upto,station_to_code,via,distance , sum(full_fare)  from tams_uts where mm_bkg_date ="+month+" and yy_bkg_date = "+year
					+ "and apprcnf_flag is null and orgrly = '"+a+"' group by station_from,station_from_code , station_upto,station_to_code,via,distance  "
					+ "order by station_from,station_upto ";
			
		
	     System.out.println("strQuery >>>"+query);
		  ResultSet rs = stmt.executeQuery(query);
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		 
			HSSFRow rowhead18 = sheet.createRow((short) 1);
			
			rowhead18.createCell((short) 0).setCellValue("UTS Unmatched");
			 sheet.autoSizeColumn((short) 0);
			 rowhead18.createCell((short) 1).setCellValue("OD pairs");
			 rowhead18.createCell((short) 2).setCellValue("with Via");
			 rowhead18.createCell((short) 3).setCellValue("Distance and");
			 sheet.autoSizeColumn((short) 3);
			 rowhead18.createCell((short) 4).setCellValue("Full Fare");
			
			 rowhead18.createCell((short) 6).setCellValue("By CRIS");
			 
			 

             HSSFRow rowhead2 = sheet.createRow((short) 2);
             rowhead2.createCell((short) 3).setCellValue(zonename);
			
			HSSFRow rowhead3 =sheet.createRow((short) 3);
			rowhead3.createCell((short) 0).setCellValue("Figures" );
			rowhead3.createCell((short) 1).setCellValue("in Units");
			rowhead3.createCell((short) 3).setCellValue(d );
			rowhead3.createCell((short) 4).setCellValue(" - " + e);
			
			rowhead3.createCell((short) 5).setCellValue("Date - ");
			rowhead3.createCell((short) 6).setCellValue(modifiedDate);
			
			
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  rowhead.createCell((short) 0).setCellValue("Station From"); 
		  sheet.autoSizeColumn((short) 0);
		  rowhead.createCell((short)1).setCellValue("Station From code");
		  sheet.autoSizeColumn((short) 1);
		  rowhead.createCell((short)2).setCellValue("Station Upto");
		  sheet.autoSizeColumn((short) 2);
		  rowhead.createCell((short)3).setCellValue("Station To code");
		  sheet.autoSizeColumn((short) 3);
		  rowhead.createCell((short)4).setCellValue("Via");
		  sheet.autoSizeColumn((short) 4);
		  rowhead.createCell((short)5).setCellValue("Distance"); 
		  sheet.autoSizeColumn((short) 5);
		  rowhead.createCell((short)6).setCellValue("Fare");
		  sheet.autoSizeColumn((short) 6);
		
		  int index=6; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("station_from"));
				row.createCell((short) 1).setCellValue(rs.getString("station_from_code"));
				row.createCell((short) 2).setCellValue(rs.getString("station_upto"));
				row.createCell((short) 3).setCellValue(rs.getString("station_to_code"));
				row.createCell((short) 4).setCellValue(rs.getString("via"));
			    sheet.autoSizeColumn((short) 4);
				
				row.createCell((short) 5).setCellValue(rs.getString("distance"));
				row.createCell((short) 6).setCellValue(rs.getString("sum(full_fare)"));
			  
				
				  sumr1=sumr1+ Long.parseLong(rs.getString("sum(full_fare)"));
				 
				index++;
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 5).setCellValue("Total Fare");
		    sheet.autoSizeColumn((short) 5);
			row.createCell((short) 6).setCellValue(sumr1);

			
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
	
		 
		
		HSSFWorkbook my_workbook = new HSSFWorkbook();
        HSSFSheet my_sheet = my_workbook.createSheet("Merge Cells");
        
        HSSFRow row = my_sheet.createRow((short) 1);
        HSSFCell cell = row.createCell((short) 1);
        cell.setCellValue("Merge Data with Apache POI");
        my_sheet.addMergedRegion(null);//.addMergedRegion(new CellRangeAddress(1,1,4,1));
        FileOutputStream out = new FileOutputStream(new File("D:\\Merge_Across_Columns.xls"));
        my_workbook.write(out);
        out.close();
		
		
		 
	}
	
	
	
	public void  uts_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path = "/ARMSFILES/sheet/" + a + " " + b + " " + d	+ " " + e + ".pdf";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d	+ " " + e + ".pdf";
		int sum=0;
		String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
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
		 
		 
		 
				 
				 
			  
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
				HSSFRow rowhead1 = sheet.createRow((short) 1);
				HSSFCellStyle style = hwb.createCellStyle();
				HSSFFont font = hwb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setFontHeightInPoints((short)10);
				font.setBoldweight((short) 10);//.setBold(true);
				style.setFont(font);
				rowhead1.createCell((short) 3).setCellValue("SUMMARY OF");//APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS            By CRIS"
				sheet.autoSizeColumn((short) 3);
				rowhead1.createCell((short) 4).setCellValue("PASSENGER");//SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS UTS   
				sheet.autoSizeColumn((short) 4);
				rowhead1.createCell((short) 5).setCellValue("APPORTIONMENT");
				sheet.autoSizeColumn((short) 5);
				rowhead1.createCell((short) 6).setCellValue(" FOR ALL");
				sheet.autoSizeColumn((short) 6);
				rowhead1.createCell((short) 7).setCellValue("INDIAN");
				rowhead1.createCell((short) 8).setCellValue("RAILWAYS");
				rowhead1.createCell((short) 9).setCellValue("RAILWAYS");
				sheet.autoSizeColumn((short) 9);
				rowhead1.createCell((short) 10).setCellValue("UTS");
				rowhead1.createCell((short) 11).setCellValue("");
				rowhead1.createCell((short) 12).setCellValue("");
				rowhead1.createCell((short) 13).setCellValue("");
				rowhead1.createCell((short) 14).setCellValue("");
				rowhead1.createCell((short) 15).setCellValue("");
				rowhead1.createCell((short) 16).setCellValue("");
				rowhead1.createCell((short) 17).setCellValue("");
				rowhead1.createCell((short) 18).setCellValue("");
				rowhead1.createCell((short) 19).setCellValue("BY");
				rowhead1.createCell((short) 20).setCellValue("CRIS");
				//rowhead1.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowhead11 =sheet.createRow((short)2); 
				 rowhead11.createCell((short) 8).setCellValue(d);
				 rowhead11.createCell((short) 9).setCellValue(e);
				 
				 rowhead11.createCell((short) 19).setCellValue("Date");
				 
				 rowhead11.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowheadfig =sheet.createRow((short)3); 
				 rowheadfig.createCell((short) 19).setCellValue("Figures in");
				 rowheadfig.createCell((short) 20).setCellValue("000s");
				  HSSFRow rowhead =sheet.createRow((short)4); 
				  rowhead.createCell((short) 0).setCellValue("ZONE"); 
				  rowhead.createCell((short)1).setCellValue("CR"); 
				  rowhead.createCell((short)2).setCellValue("EC");
				  rowhead.createCell((short)3).setCellValue("EO"); 
				  rowhead.createCell((short)4).setCellValue("ER");
				  rowhead.createCell((short) 5).setCellValue("KR"); 
				  rowhead.createCell((short)6).setCellValue("NC"); 
				  rowhead.createCell((short)7).setCellValue("NE");
				  rowhead.createCell((short)8).setCellValue("NF"); 
				  rowhead.createCell((short)9).setCellValue("NR");
				  rowhead.createCell((short)10).setCellValue("NW"); 
				  rowhead.createCell((short)11).setCellValue("SB");
				  rowhead.createCell((short)12).setCellValue("SC"); 
				  rowhead.createCell((short)13).setCellValue("SE");
				  rowhead.createCell((short) 14).setCellValue("SR"); 
				  rowhead.createCell((short)15).setCellValue("SW"); 
				  rowhead.createCell((short)16).setCellValue("WC");
				  rowhead.createCell((short)17).setCellValue("WR"); 
				  rowhead.createCell((short)18).setCellValue("Retained Share");
				  rowhead.createCell((short)19).setCellValue("Inward Share");
				  rowhead.createCell((short)20).setCellValue("Total Apport. Earning");
				  
				  int index=6; int sno=0; String name="";
				  
				  int jj=1;
				 
				    while(jj<18) {
				    	 int colindex=1;
				    	
				    	 HSSFRow row = sheet.createRow((short)index); 
				    	 row.createCell((short) 0).setCellValue(dbvalue[jj-1][0]);
				    	
				    	 
				    	 for(int kk =0; kk< 20 ; kk++)
				        	{
				    		 String vlaue=dbvalue_trans[jj][kk];
				    		 System.out.println("value is"+ vlaue);
				    		 row.createCell((short) colindex).setCellValue(vlaue);
				    		 colindex++;
				        	}
				    	 
				    	 jj++;
				    	 index++;
				    	
				    }
				    
				    System.out.println("J value is "+ jj);
				    
				    
				    HSSFRow rowus = sheet.createRow((short) 23);
				    for(int k3 =0; k3< 18 ; k3++)
			    	{
				    	
						 rowus.createCell((short)k3).setCellValue(""); 
						 
			    	}
				    rowus.createCell((short)18).setCellValue(sumr18); 
				    rowus.createCell((short)19).setCellValue(sumr19); 
				    rowus.createCell((short)20).setCellValue(sumr20); 
				
				
				    
				    
				    
				    
				    
				  //Platform ROW
				    HSSFRow rowplateform = sheet.createRow((short) 24);
				    rowplateform.createCell((short) 0).setCellValue("PlateForm");
				    
				    int cindexplateform=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowplateform.createCell((short) cindexplateform).setCellValue(dbvalue_trans[18][k4]); 
			        	cindexplateform++;
			    	}
			        rowplateform.createCell((short) cindexplateform).setCellValue(sumr23);
			        

				    
				    //OGA ROW
				    HSSFRow rowoga = sheet.createRow((short) 25);
				    rowoga.createCell((short) 0).setCellValue("OGA");
				    
				    int cindex=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowoga.createCell((short) cindex).setCellValue(dbvalue_trans[19][k4]); 
			        	cindex++;
			    	}
			        rowoga.createCell((short) cindex).setCellValue(sumr22);
			        
			        
			        //
			        
			        HSSFRow rowretain = sheet.createRow((short) 26);
			        rowretain.createCell((short) 0).setCellValue("Retained");
				    int zz=1;
				    for(int k6 =0; k6< 19 ; k6++)
			    	{
				    	rowretain.createCell((short) zz).setCellValue(dbvalue_trans[20][k6]);
				    	 zz++;
			    	}
				    
				    //rowretain.createCell((short) zz--).setCellValue("");
				    //rowretain.createCell((short) zz++).setCellValue("");
				    rowretain.createCell((short) zz).setCellValue(sumr18);
			        
				    
				    HSSFRow rowoutward = sheet.createRow((short) 27);
				    rowoutward.createCell((short) 0).setCellValue("OUTWARD");
				    int tt=1;
				    for(int k =0; k< 19 ; k++)
			    	{
				    	rowoutward.createCell((short) tt).setCellValue(dbvalue_trans[21][k]);
				    	tt++;
				    	
			    	}
				    
				    //rowoutward.createCell((short) tt++).setCellValue("");
				   // rowoutward.createCell((short) tt++).setCellValue("");
				    rowoutward.createCell((short) tt).setCellValue(sumr21);
				    
				    
				    
				    HSSFRow rowinward = sheet.createRow((short) 28);
				    rowinward.createCell((short) 0).setCellValue("INWARD");
				    int ss=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowinward.createCell((short) ss).setCellValue(dbvalue_trans[k][18]);
				    	ss++;
			    	}
				    
				   rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue(sumr19);
			        
				    HSSFRow rowtot = sheet.createRow((short) 29);
				    rowtot.createCell((short) 0).setCellValue("Total Apport Earnings");
				    int uu=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowtot.createCell((short) uu).setCellValue(dbvalue_trans[k][19]);
				    	uu++;
			    	}
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue(sumr20);
			        
			        
			        
			        
			        OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
			
	}
	
	public void  prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		ArrayList<Integer> retainevalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> inwardvalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> totalvalue =new 	ArrayList<Integer>() ;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  //String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
			  Statement stmt = con.createStatement();
		  
			//Statement stmt = con.createStatement();
			
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

			  ResultSet rs = stmt.executeQuery(Query);	
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
				HSSFRow rowhead1 = sheet.createRow((short) 1);
				HSSFCellStyle style = hwb.createCellStyle();
				HSSFFont font = hwb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setFontHeightInPoints((short)10);
				font.setBoldweight((short) 10);//.setBold(true);
				style.setFont(font);
				rowhead1.createCell((short) 3).setCellValue("SUMMARY OF");//APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS            By CRIS"
				sheet.autoSizeColumn((short) 3);
				rowhead1.createCell((short) 4).setCellValue("APPORTIONMENT");
				sheet.autoSizeColumn((short) 4);
				rowhead1.createCell((short) 5).setCellValue("OF CATERING");
				sheet.autoSizeColumn((short) 5);
				rowhead1.createCell((short) 6).setCellValue("CHARGES");
				sheet.autoSizeColumn((short) 6);
				rowhead1.createCell((short) 7).setCellValue("FOR ALL");
				rowhead1.createCell((short) 8).setCellValue("INDIAN");
				rowhead1.createCell((short) 9).setCellValue("RAILWAYS");
				sheet.autoSizeColumn((short) 9);
				rowhead1.createCell((short) 10).setCellValue("PRS");
				rowhead1.createCell((short) 11).setCellValue("");
				rowhead1.createCell((short) 12).setCellValue("");
				rowhead1.createCell((short) 13).setCellValue("");
				rowhead1.createCell((short) 14).setCellValue("");
				rowhead1.createCell((short) 15).setCellValue("");
				rowhead1.createCell((short) 16).setCellValue("");
				rowhead1.createCell((short) 17).setCellValue("");
				rowhead1.createCell((short) 18).setCellValue("");
				rowhead1.createCell((short) 19).setCellValue("BY");
				rowhead1.createCell((short) 20).setCellValue("CRIS");
				//rowhead1.createCell((short) 20).setCellValue(modifiedDate);
				HSSFRow rowhead11 =sheet.createRow((short)2); 
				 rowhead11.createCell((short) 8).setCellValue(d);
				 rowhead11.createCell((short) 9).setCellValue(e);
				 
				 rowhead11.createCell((short) 19).setCellValue("Date");
				 
				 rowhead11.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowheadfig =sheet.createRow((short)3); 
				 rowheadfig.createCell((short) 19).setCellValue("Figures in");
				 rowheadfig.createCell((short) 20).setCellValue("Units");
				  HSSFRow rowhead =sheet.createRow((short)4); 
				  rowhead.createCell((short) 0).setCellValue("ZONE"); 
				  rowhead.createCell((short)1).setCellValue("CR"); 
				  rowhead.createCell((short)2).setCellValue("EC");
				  rowhead.createCell((short)3).setCellValue("EO"); 
				  rowhead.createCell((short)4).setCellValue("ER");
				  rowhead.createCell((short) 5).setCellValue("KR"); 
				  rowhead.createCell((short)6).setCellValue("NC"); 
				  rowhead.createCell((short)7).setCellValue("NE");
				  rowhead.createCell((short)8).setCellValue("NF"); 
				  rowhead.createCell((short)9).setCellValue("NR");
				  rowhead.createCell((short)10).setCellValue("NW"); 
				  rowhead.createCell((short)11).setCellValue("SB");
				  rowhead.createCell((short)12).setCellValue("SC"); 
				  rowhead.createCell((short)13).setCellValue("SE");
				  rowhead.createCell((short) 14).setCellValue("SR"); 
				  rowhead.createCell((short)15).setCellValue("SW"); 
				  rowhead.createCell((short)16).setCellValue("WC");
				  rowhead.createCell((short)17).setCellValue("WR"); 
				  rowhead.createCell((short)18).setCellValue("Retained Share");
				  rowhead.createCell((short)19).setCellValue("Inward Share");
				  rowhead.createCell((short)20).setCellValue("Total Apport. Earning");
				  for(int index=0 ; index<=20 ;index++){
					  rowhead.getCell((short) index).setCellStyle((HSSFCellStyle) style);
					  
				  }
				  int index=6; int sno=0; String name="";
				  while(rs.next()) {
				
					  HSSFRow row = sheet.createRow((short)index); 
					  row.createCell((short)0).setCellValue(rs.getString("OWNRLY")); 
					  row.createCell((short) 1).setCellValue(rs.getString("CR"));
					  //System.out.println("train no is"+rs.getString("EC")); 
					  row.createCell((short)2).setCellValue(rs.getString("EC"));
					  row.createCell((short)3).setCellValue(rs.getString("EO"));
					  row.createCell((short)4).setCellValue(rs.getString("ER"));
					  row.createCell((short)5).setCellValue(rs.getString("KR"));
					  row.createCell((short)6).setCellValue(rs.getString("NC"));
					  row.createCell((short)7).setCellValue(rs.getString("NE"));
					  
					  row.createCell((short)8).setCellValue(rs.getString("NF"));
					  row.createCell((short)9).setCellValue(rs.getString("NR"));
					  row.createCell((short)10).setCellValue(rs.getString("NW"));
					  
					  row.createCell((short)11).setCellValue(rs.getString("SB"));
					  row.createCell((short)12).setCellValue(rs.getString("SC"));
					  row.createCell((short)13).setCellValue(rs.getString("SE"));
					  
					  row.createCell((short)14).setCellValue(rs.getString("SR"));
					  row.createCell((short)15).setCellValue(rs.getString("SW"));
					  row.createCell((short)16).setCellValue(rs.getString("WC"));
					  
					  row.createCell((short)17).setCellValue(rs.getString("WR"));
					  row.createCell((short)18).setCellValue(rs.getString("RETAINED"));
					  int a1=Integer.parseInt(rs.getString("RETAINED"));
					  retainevalue.add(i, a1);
					  row.createCell((short)19).setCellValue(rs.getString("INWARD"));
					  int b1=Integer.parseInt(rs.getString("INWARD"));
					  inwardvalue.add(i, b1);
					  row.createCell((short)20).setCellValue(rs.getString("TOTAL"));
					  int c1=Integer.parseInt(rs.getString("TOTAL"));
					  totalvalue.add(i, c1);
					  
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
			             index++ ;
			             i++;
					  
					  
					  }
				  
				  HSSFRow rowus = sheet.createRow((short) index+1);
				  rowus.createCell((short)0).setCellValue("Unmatched Share"); 
				  rowus.createCell((short) 1).setCellValue("0");
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowus.createCell((short)2).setCellValue("0");
				  rowus.createCell((short)3).setCellValue("0");
				  rowus.createCell((short)4).setCellValue("0");
				  rowus.createCell((short)5).setCellValue("0");
				  rowus.createCell((short)6).setCellValue("0");
				  rowus.createCell((short)7).setCellValue("0");
				  
				  rowus.createCell((short)8).setCellValue("0");
				  rowus.createCell((short)9).setCellValue("0");
				  rowus.createCell((short)10).setCellValue("0");
				  
				  rowus.createCell((short)11).setCellValue("0");
				  rowus.createCell((short)12).setCellValue("0");
				  rowus.createCell((short)13).setCellValue("0");
				  
				  rowus.createCell((short)14).setCellValue("0");
				  rowus.createCell((short)15).setCellValue("0");
				  rowus.createCell((short)16).setCellValue("0");
				  
				  rowus.createCell((short)17).setCellValue("0");
				  rowus.createCell((short)18).setCellValue(sumr18);
				  rowus.createCell((short)19).setCellValue(sumr19);
				  rowus.createCell((short)20).setCellValue(sumr20);
				  
				  
				  HSSFRow rowoa = sheet.createRow((short) index+2);
				  rowoa.createCell((short)0).setCellValue("Orignating Amount"); 
				  rowoa.createCell((short) 1).setCellValue(sumr1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowoa.createCell((short)2).setCellValue(sumr2);
				  rowoa.createCell((short)3).setCellValue(sumr3);
				  rowoa.createCell((short)4).setCellValue(sumr4);
				  rowoa.createCell((short)5).setCellValue(sumr5);
				  rowoa.createCell((short)6).setCellValue(sumr6);
				  rowoa.createCell((short)7).setCellValue(sumr7);
				  
				  rowoa.createCell((short)8).setCellValue(sumr8);
				  rowoa.createCell((short)9).setCellValue(sumr9);
				  rowoa.createCell((short)10).setCellValue(sumr10);
				  
				  rowoa.createCell((short)11).setCellValue(sumr11);
				  rowoa.createCell((short)12).setCellValue(sumr12);
				  rowoa.createCell((short)13).setCellValue(sumr13);
				  
				  rowoa.createCell((short)14).setCellValue(sumr14);
				  rowoa.createCell((short)15).setCellValue(sumr15);
				  rowoa.createCell((short)16).setCellValue(sumr16);
				  
				  rowoa.createCell((short)17).setCellValue(sumr17);
				  rowoa.createCell((short)18).setCellValue("");
				  rowoa.createCell((short)19).setCellValue("");
				  rowoa.createCell((short)20).setCellValue(sumr20);
				  
				  HSSFRow rowos = sheet.createRow((short) index+3);//retainevalue
				  long col1= sumr1-retainevalue.get(0);
				  long col2= sumr2-retainevalue.get(1);
				  long col3= sumr3-retainevalue.get(2);
				  long col4= sumr4-retainevalue.get(3);
				  long col5= sumr5-retainevalue.get(4);
				  long col6= sumr6-retainevalue.get(5);
				  long col7= sumr7-retainevalue.get(6);
				  long col8= sumr8-retainevalue.get(7);
				  long col9= sumr9-retainevalue.get(8);
				  long col10= sumr10-retainevalue.get(9);
				  long col11= sumr11-retainevalue.get(10);
				  long col12= sumr12-retainevalue.get(11);
				  long col13= sumr13-retainevalue.get(12);
				  long col14= sumr14-retainevalue.get(13);
				  long col15= sumr15-retainevalue.get(14);
				  long col16= sumr16-retainevalue.get(15);
				  long col17= sumr17-retainevalue.get(16);
				  long col18= sumr1-retainevalue.get(17);
				  long col19= sumr1-retainevalue.get(18);
				  long col20= sumr1-retainevalue.get(19);
				  System.out.println( col1 +""+col2+""+col3+""+col4+"");
				  long sum = col1+col2+col3+col4+col5+col6+col7+col8+col9+col10+col11+col12+col13+col14+col15+col16+col17;
				  
				  rowos.createCell((short)0).setCellValue("Outward Share"); 
				  rowos.createCell((short) 1).setCellValue(col1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowos.createCell((short)2).setCellValue(col2);
				  rowos.createCell((short)3).setCellValue(col3);
				  rowos.createCell((short)4).setCellValue(col4);
				  rowos.createCell((short)5).setCellValue(col5);
				  rowos.createCell((short)6).setCellValue(col6);
				  rowos.createCell((short)7).setCellValue(col7);
				  
				  rowos.createCell((short)8).setCellValue(col8);
				  rowos.createCell((short)9).setCellValue(col9);
				  rowos.createCell((short)10).setCellValue(col10);
				  
				  rowos.createCell((short)11).setCellValue(col11);
				  rowos.createCell((short)12).setCellValue(col12);
				  rowos.createCell((short)13).setCellValue(col13);
				  
				  rowos.createCell((short)14).setCellValue(col14);
				  rowos.createCell((short)15).setCellValue(col15);
				  rowos.createCell((short)16).setCellValue(col16);
				  
				  rowos.createCell((short)17).setCellValue(col17);
				  rowos.createCell((short)18).setCellValue("");
				  rowos.createCell((short)19).setCellValue("");
				  rowos.createCell((short)20).setCellValue(sum);
				  
				  
				 //retaine row 
				  
				  HSSFRow rowretain = sheet.createRow((short) index+4);
				  rowretain.createCell((short)0).setCellValue("Retained"); 
				  long sumofretain=0;
				  int k0=1;
				  for(int j=0 ;j<retainevalue.size(); j++){
					  
					  int aa=retainevalue.get(j);
					  
					  rowretain.createCell((short)k0).setCellValue(aa);
					  ++k0;
					  sumofretain=sumofretain+aa;
					  
				  }
				  rowretain.createCell((short) 18).setCellValue(""); 
				  rowretain.createCell((short) 19).setCellValue(""); 
				  rowretain.createCell((short)  20).setCellValue(sumofretain); 
				  
				  //INwARD ROW
				  
				  
				  HSSFRow rowinward = sheet.createRow((short) index+5);
				  rowinward.createCell((short)0).setCellValue("Inward"); 
				  long suminwrad=0;
				  int k=1;
				  for(int j=0 ;j<inwardvalue.size(); j++){
					  
					  int aa=inwardvalue.get(j);
					  
					  rowinward.createCell((short) k).setCellValue(aa);
					  k++;
					  suminwrad=suminwrad+aa;
					  
				  }
				  rowinward.createCell((short) 18).setCellValue(""); 
				  rowinward.createCell((short) 19).setCellValue(""); 
				  rowinward.createCell((short) 20).setCellValue(suminwrad); 
				  
				  
				  //Total Apport. Earnings
				  HSSFRow rowtotal = sheet.createRow((short) index+6);
				  rowtotal.createCell((short)0).setCellValue("Total Apport. Earnings"); 
				  long sumtot=0;
				  int k3=1;
				  for(int j=0 ;j<totalvalue.size(); j++){
					 
					  int aa=totalvalue.get(j);
					  
					  rowtotal.createCell((short)k3).setCellValue(aa);
					  
					 sumtot=sumtot+aa;
					  k3++;
					  
				  }
				  rowtotal.createCell((short) 18).setCellValue(""); 
				  rowtotal.createCell((short) 19).setCellValue(""); 
				  rowtotal.createCell((short) 20).setCellValue(sumtot); 
				  
				  
				  
				  OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
				  
				  
		  
	
	}
	
	public void exportprsstationnotintrainroute(String a, String b , String c, String d , String e ) throws Exception{
		  
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);  
		
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  zonename=fullnameofzone(a);
		 // String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
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
		  
		  System.out.println("strQuery >>>"+strQuery);
		  
		  ResultSet rs = stmt.executeQuery(strQuery);	
		    HSSFWorkbook hwb = new HSSFWorkbook(); 
		    HSSFSheet sheet =hwb.createSheet("new sheet"); 
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			rowhead1.createCell((short) 3).setCellValue("PRS List of");//PRS List of Stations not lying inTrain Route
			sheet.autoSizeColumn((short) 3);
			rowhead1.createCell((short) 4).setCellValue("Stations not");
			sheet.autoSizeColumn((short) 4);
			rowhead1.createCell((short) 5).setCellValue("lying inTrain ");
			sheet.autoSizeColumn((short) 5);
			rowhead1.createCell((short) 6).setCellValue("Route");
			
			
			//rowhead1.createCell((short) 6).setCellValue(modifiedDate);
			
			HSSFRow rowheadtwo = sheet.createRow((short) 2);
			rowheadtwo.createCell((short) 4).setCellValue(zonename);
			sheet.autoSizeColumn((short) 4);
			rowheadtwo.createCell((short) 7).setCellValue("Date -");
			sheet.autoSizeColumn((short) 7);
			rowheadtwo.createCell((short) 8).setCellValue(modifiedDate);
			sheet.autoSizeColumn((short) 8);
		
			
			HSSFRow rowheadthree = sheet.createRow((short) 3);
			rowheadthree.createCell((short) 5).setCellValue(d+"-");
			sheet.autoSizeColumn((short) 5);
			rowheadthree.createCell((short) 6).setCellValue(e);
			sheet.autoSizeColumn((short) 6);
			
			
			
		  HSSFRow rowhead =sheet.createRow((short) 4); 
		  rowhead.createCell((short) 0).setCellValue("Train Number"); 
		  rowhead.createCell((short)1).setCellValue("From Station"); 
		  rowhead.createCell((short)2).setCellValue("From Station Code");
		  rowhead.createCell((short)3).setCellValue("To Station"); 
		  rowhead.createCell((short)4).setCellValue("To Station Code");
		  rowhead.createCell((short)5).setCellValue("Enroute Station");
		  rowhead.createCell((short)6).setCellValue("Distance"); 
		  rowhead.createCell((short)7).setCellValue("Fare");
		  int index=5; int sno=0; String name="";
		  //System.out.println("traisdffffffffffffffffn no is"+rs.getString(1));
		  while(rs.next()) {
		  System.out.println("inside while is"+rs.getString(1)); 
		  sno++;
		  
		  HSSFRow row = sheet.createRow((short)index); 
		  row.createCell((short)0).setCellValue(rs.getString("trainno")); 
		  row.createCell((short) 1).setCellValue(rs.getString("sstn"));
		  //System.out.println("train no is"+rs.getString(1)); 
		  row.createCell((short)2).setCellValue(rs.getString("sstn9"));
		  row.createCell((short)3).setCellValue(rs.getString("dstn"));
		  row.createCell((short)4).setCellValue(rs.getString("destn9"));
		  row.createCell((short)5).setCellValue(rs.getString("entstn"));
		  row.createCell((short)6).setCellValue(rs.getString("dist"));
		  row.createCell((short)7).setCellValue(rs.getString("fare"));
		  sumr1=sumr1+Long.parseLong(rs.getString("fare"));
		  //System.out.println("catering no is"+rs.getString(2)); index++; }
		  
		  
		  FileOutputStream fileOut = new FileOutputStream(filename);
		  hwb.write(fileOut); fileOut.close();
	
		index++;
		  
		  }

		  HSSFRow rowtot = sheet.createRow((short)index); 
		  rowtot.createCell((short)6).setCellValue("Total Fare"); 
		  rowtot.createCell((short) 7).setCellValue(sumr1);
		
		  FileOutputStream fileOut = new FileOutputStream(filename);
		  hwb.write(fileOut); fileOut.close();
		  
		  
		  downloadFile(filename,b);
		  
		  OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
	}
	
	public void prsmatchunmatch(String a, String b , String c, String d , String e ) throws Exception{
		
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		  
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		//  String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;	  
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
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  HSSFFont my_font=hwb.createFont();

		  
		  
		  
		  
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			
			rowhead1.createCell((short) 3).setCellValue("STATISTICS ");
			rowhead1.createCell((short) 4).setCellValue(" OF PASSENGER ");
			rowhead1.createCell((short) 5).setCellValue(" APPORTIONMENT ");
			rowhead1.createCell((short) 6).setCellValue(" FOR ALL INDIAN ");
			rowhead1.createCell((short) 7).setCellValue(" RAILWAYS (PRS)");
			
			rowhead1.createCell((short) 10).setCellValue("By CRIS");
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			rowhead3.createCell((short) 0).setCellValue("Figures" );
			rowhead3.createCell((short) 1).setCellValue("in Units");
			rowhead3.createCell((short) 5).setCellValue(d );
			rowhead3.createCell((short) 6).setCellValue(" - " + e);
			
			rowhead3.createCell((short) 11).setCellValue("Date - ");
			rowhead3.createCell((short) 12).setCellValue(modifiedDate);
			
			 HSSFRow rowhead2 =sheet.createRow((short) 3);
			 rowhead2.createCell((short) 2).setCellValue("INPUT");
			 rowhead2.createCell((short) 3).setCellValue("RECORDS");

			 
			 rowhead2.createCell((short) 6).setCellValue("MATCH");
			 rowhead2.createCell((short) 7).setCellValue("RECORDS");
			 
			 rowhead2.createCell((short) 10).setCellValue("UNMATCH");
			 rowhead2.createCell((short) 11).setCellValue("RECORDS");
			 
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  rowhead.createCell((short) 0).setCellValue("RLY"); 
		  sheet.autoSizeColumn((short) 0);
		  rowhead.createCell((short)1).setCellValue("Records");
		  sheet.autoSizeColumn((short) 1);
		  rowhead.createCell((short)2).setCellValue("Total Fare Excl. catchg");
		  sheet.autoSizeColumn((short) 2);
		  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)3).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 3);
		  rowhead.createCell((short)4).setCellValue("Catering Chg");
		  sheet.autoSizeColumn((short) 4);
		  rowhead.createCell((short)5).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 5);
		  rowhead.createCell((short)6).setCellValue("Total Fare Excl. catchg");
		  sheet.autoSizeColumn((short) 6);
		  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)7).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 7);
		  rowhead.createCell((short)8).setCellValue("Catering Chg");
		  sheet.autoSizeColumn((short) 8);
		  rowhead.createCell((short)9).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 9);
		  rowhead.createCell((short)10).setCellValue("Total Fare Excl. catchg");
		  sheet.autoSizeColumn((short) 10);
		  rowhead.createCell((short)13).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)11).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 11);
		  rowhead.createCell((short)12).setCellValue("Catering Chg");
		  sheet.autoSizeColumn((short) 12);
		  int index=7; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("rly"));
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_OTHERCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_SAFETYCHG"));
				row.createCell((short) 7).setCellValue(rs.getString("MATCH_OTHERCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				row.createCell((short) 13).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				  sumr13=sumr13+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr14=sumr14+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
 				index++;

 				FileOutputStream fileOut = new FileOutputStream(filename);
 				hwb.write(fileOut);
 				fileOut.close();
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 0).setCellValue("Total");
			//row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 2).setCellValue(sumr2);
			row.createCell((short) 3).setCellValue(sumr3);
			row.createCell((short) 3).setCellValue(sumr4);
			row.createCell((short) 4).setCellValue(sumr5);
			row.createCell((short) 5).setCellValue(sumr6);
			row.createCell((short) 6).setCellValue(sumr7);
			row.createCell((short) 8).setCellValue(sumr8);
			row.createCell((short) 7).setCellValue(sumr9);
			row.createCell((short) 8).setCellValue(sumr10);
			row.createCell((short) 9).setCellValue(sumr11);
			row.createCell((short) 10).setCellValue(sumr12);
			row.createCell((short) 13).setCellValue(sumr13);
			row.createCell((short) 11).setCellValue(sumr14);
			row.createCell((short) 12).setCellValue(sumr15);
			//row.createCell((short)15).setCellValue(rs.getString("UNMATCH_CATGCHG"));
			
		
			 
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
		
	}
	
	public void UtsMatchUnmatchrlywise(String a, String b , String c, String d , String e ) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  //String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
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
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  HSSFFont my_font=hwb.createFont();

		  
		  
		  
		  
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			
			rowhead1.createCell((short) 3).setCellValue("STATISTICS ");
			rowhead1.createCell((short) 4).setCellValue(" OF PASSENGER ");
			rowhead1.createCell((short) 5).setCellValue(" APPORTIONMENT ");
			rowhead1.createCell((short) 6).setCellValue(" FOR ALL INDIAN ");
			rowhead1.createCell((short) 7).setCellValue(" RAILWAYS (UTS)");
			rowhead1.createCell((short) 8).setCellValue(" (Originating ");
			rowhead1.createCell((short) 9).setCellValue(" zone wise)");

			rowhead1.createCell((short) 10).setCellValue("By CRIS");
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			rowhead3.createCell((short) 0).setCellValue("Figures" );
			rowhead3.createCell((short) 1).setCellValue("in Units");
			rowhead3.createCell((short) 5).setCellValue(d );
			rowhead3.createCell((short) 6).setCellValue(" - " + e);
			
			rowhead3.createCell((short) 11).setCellValue("Date - ");
			rowhead3.createCell((short) 12).setCellValue(modifiedDate);
			
			 HSSFRow rowhead2 =sheet.createRow((short) 3);
			 rowhead2.createCell((short) 2).setCellValue("INPUT");
			 rowhead2.createCell((short) 3).setCellValue("RECORDS");

			 
			 rowhead2.createCell((short) 6).setCellValue("MATCH");
			 rowhead2.createCell((short) 7).setCellValue("RECORDS");
			 
			 rowhead2.createCell((short) 10).setCellValue("UNMATCH");
			 rowhead2.createCell((short) 11).setCellValue("RECORDS");
			 
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  rowhead.createCell((short) 0).setCellValue("RLY"); 
		  sheet.autoSizeColumn((short) 0);
		  rowhead.createCell((short)1).setCellValue("Records");
		  sheet.autoSizeColumn((short) 1);
		  rowhead.createCell((short)2).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 2);
		  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)3).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 3);
		  rowhead.createCell((short)4).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 4);
		  rowhead.createCell((short)5).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 5);
		  rowhead.createCell((short)6).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 6);
		  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)7).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 7);
		  rowhead.createCell((short)8).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 8);
		  rowhead.createCell((short)9).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 9);
		  rowhead.createCell((short)10).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 10);
		  rowhead.createCell((short)13).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)11).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 11);
		  rowhead.createCell((short)12).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 12);
		  int index=7; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("rly"));
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_OTHERCHG"));
				//row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
			    row.createCell((short) 7).setCellValue(rs.getString("MATCH_SAFETYCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_OTHERCHG"));
				//row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				//row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  //sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  //sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  //sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
				index++;

				FileOutputStream fileOut = new FileOutputStream(filename);
				hwb.write(fileOut);
				fileOut.close();
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 0).setCellValue("Total");
			//row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 2).setCellValue(sumr2);
		    row.createCell((short) 3).setCellValue(sumr3);
			row.createCell((short) 4).setCellValue(sumr4);
			//row.createCell((short) 4).setCellValue(sumr5);
			row.createCell((short) 5).setCellValue(sumr5);
			row.createCell((short) 6).setCellValue(sumr6);
			row.createCell((short) 7).setCellValue(sumr7);
			row.createCell((short) 8).setCellValue(sumr8);
			//row.createCell((short) 8).setCellValue(sumr10);
			row.createCell((short) 9).setCellValue(sumr9);
			row.createCell((short) 10).setCellValue(sumr10);
			row.createCell((short) 11).setCellValue(sumr11);
			row.createCell((short) 12).setCellValue(sumr12);
			//row.createCell((short) 12).setCellValue(sumr15);
			//row.createCell((short)15).setCellValue(rs.getString("UNMATCH_CATGCHG"));
		
			FileOutputStream fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated");
          downloadFile(filename,b);	
			
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
		
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
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path = "/ARMSFILES/sheet/" + a + " " + b + " " + d	+ " " + e + ".pdf";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d	+ " " + e + ".pdf";
		int sum=0;
		String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		 filename = filename.replace(",", "");
		
		Statement stmt = con.createStatement();
		
		  String query="select p.orgrly orgrly,q.TC_TTE TC_TTE,q.platform platform,q.reservation_slip reservation_slip,q.superfast superfast,q.tourist tourist,q.parking parking,  "
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
					+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9944')) e, "
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
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  HSSFFont my_font=hwb.createFont();
		  
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			
			rowhead1.createCell((short) 2).setCellValue("UTS DETAILS");
			rowhead1.createCell((short) 3).setCellValue("OF MUTP");
			rowhead1.createCell((short) 4).setCellValue("MMTS, CIDCO");
			rowhead1.createCell((short) 5).setCellValue("MRTS,TC/TTE");
			rowhead1.createCell((short) 6).setCellValue("PLATFORM ETC.");
			rowhead1.createCell((short) 7).setCellValue("(FOR ALL");
			rowhead1.createCell((short) 8).setCellValue("INDIAN)");
			rowhead1.createCell((short) 9).setCellValue("RAILWAYS)");
			rowhead1.createCell((short) 10).setCellValue("By CRIS");
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			rowhead3.createCell((short) 3).setCellValue("FOR THE" );
			rowhead3.createCell((short) 4).setCellValue("MONTH");
			rowhead3.createCell((short) 5).setCellValue(d );
			rowhead3.createCell((short) 6).setCellValue(" - " + e);
			
			
			HSSFRow rowhead4 =sheet.createRow((short) 3);
			rowhead4.createCell((short) 8).setCellValue("Date - ");
			rowhead4.createCell((short) 9).setCellValue(modifiedDate);
			

			HSSFRow rowhead5 =sheet.createRow((short) 4);
			rowhead5.createCell((short) 8).setCellValue("Figures" );
			rowhead5.createCell((short) 9).setCellValue("in Units");
			
			
			
			  HSSFRow rowhead =sheet.createRow((short) 6);
			  
			  rowhead.createCell((short) 0).setCellValue("ORGRLY"); 
			  sheet.autoSizeColumn((short) 0);
			  rowhead.createCell((short)1).setCellValue("TC/TTE ");
			  sheet.autoSizeColumn((short) 1);
			  rowhead.createCell((short)2).setCellValue("Platform ");
			  sheet.autoSizeColumn((short) 2);
			  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
			  rowhead.createCell((short)3).setCellValue("Reservation slip");
			  sheet.autoSizeColumn((short) 3);
			  rowhead.createCell((short)4).setCellValue("Superfast surcharge");
			  sheet.autoSizeColumn((short) 4);
			  rowhead.createCell((short)5).setCellValue("TOURIST Ticket "); 
			  sheet.autoSizeColumn((short) 5);
			  rowhead.createCell((short)6).setCellValue("Platform cum parking ");
			  sheet.autoSizeColumn((short) 6);
			  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
			  rowhead.createCell((short)7).setCellValue("MUTP ");
			  sheet.autoSizeColumn((short) 7);
			  rowhead.createCell((short)8).setCellValue("MRTS ");
			  sheet.autoSizeColumn((short) 8);
			  rowhead.createCell((short)9).setCellValue("CIDCO "); 
			  sheet.autoSizeColumn((short) 9);
			  rowhead.createCell((short)10).setCellValue("MMTS ");
			  sheet.autoSizeColumn((short) 10);
			  
			  HSSFRow rowrest =sheet.createRow((short) 7);
			  rowrest.createCell((short) 0).setCellValue(""); 
			  sheet.autoSizeColumn((short) 0);
			  rowrest.createCell((short)1).setCellValue(" Tickets");
			  sheet.autoSizeColumn((short) 1);
			  rowrest.createCell((short)2).setCellValue("Ticket");
			  sheet.autoSizeColumn((short) 2);
			  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
			  rowrest.createCell((short)3).setCellValue(" charges");
			  sheet.autoSizeColumn((short) 3);
			  rowrest.createCell((short)4).setCellValue(" slip");
			  sheet.autoSizeColumn((short) 4);
			  rowrest.createCell((short)5).setCellValue(" charge"); 
			  sheet.autoSizeColumn((short) 5);
			  rowrest.createCell((short)6).setCellValue("(cab road) ticket ");
			  sheet.autoSizeColumn((short) 6);
			  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
			  rowrest.createCell((short)7).setCellValue("Charges");
			  sheet.autoSizeColumn((short) 7);
			  rowrest.createCell((short)8).setCellValue(" Charges");
			  sheet.autoSizeColumn((short) 8);
			  rowrest.createCell((short)9).setCellValue(" Charges"); 
			  sheet.autoSizeColumn((short) 9);
			  rowrest.createCell((short)10).setCellValue(" Charges");
			  sheet.autoSizeColumn((short) 10);
			  
			  
			  int index=8; int sno=0; String name="";
			  
		while(rs.next()){
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 0).setCellValue(rs.getString("orgrly"));
			row.createCell((short) 1).setCellValue(rs.getString("TC_TTE"));
			row.createCell((short) 2).setCellValue(rs.getString("PLATFORM"));
			row.createCell((short) 3).setCellValue(rs.getString("RESERVATION_SLIP"));
			row.createCell((short) 4).setCellValue(rs.getString("SUPERFAST"));
			row.createCell((short) 5).setCellValue(rs.getString("TOURIST"));
			row.createCell((short) 6).setCellValue(rs.getString("PARKING"));
		    row.createCell((short) 7).setCellValue(rs.getString("MUTP_CHARGES"));
			row.createCell((short) 8).setCellValue(rs.getString("MRTS_CHARGES"));
			row.createCell((short) 9).setCellValue(rs.getString("CIDCO_CHARGES"));
			row.createCell((short) 10).setCellValue(rs.getString("MMTS_CHARGES"));
			sumr1=sumr1+Long.parseLong(rs.getString("TC_TTE"));
			sumr2=sumr2+Long.parseLong(rs.getString("PLATFORM"));
			sumr3=sumr3+Long.parseLong(rs.getString("RESERVATION_SLIP"));
			sumr4=sumr4+Long.parseLong(rs.getString("SUPERFAST"));
			sumr5=sumr5+Long.parseLong(rs.getString("TOURIST"));
			sumr6=sumr6+Long.parseLong(rs.getString("PARKING"));
			sumr7=sumr7+Long.parseLong(rs.getString("MUTP_CHARGES"));
			sumr8=sumr8+Long.parseLong(rs.getString("MRTS_CHARGES"));
			sumr9=sumr9+Long.parseLong(rs.getString("CIDCO_CHARGES"));
			sumr10=sumr10+Long.parseLong(rs.getString("MMTS_CHARGES"));
			index++;
			
		}
		System.out.println("year ---jdfhjdhdjfhdjfhdjfhjsdfh--------> " + year);
		
		HSSFRow row = sheet.createRow((short) index);
		row.createCell((short) 0).setCellValue("Total");
		row.createCell((short) 1).setCellValue(sumr1);
		row.createCell((short) 2).setCellValue(sumr2);
	    row.createCell((short) 3).setCellValue(sumr3);
		row.createCell((short) 4).setCellValue(sumr4);
		row.createCell((short) 5).setCellValue(sumr5);
		row.createCell((short) 6).setCellValue(sumr6);
		row.createCell((short) 7).setCellValue(sumr7);
		row.createCell((short) 8).setCellValue(sumr8);
		row.createCell((short) 9).setCellValue(sumr9);
		row.createCell((short) 10).setCellValue(sumr10);
		
		System.out.println("year ------12111121212-----> " + year);
		   OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			 hwb.write(outObject);
			 outObject.flush();
			 outObject.close();
			 con.close();
		
	
			
	}
	//**************************************Method of Uts Match Unmatch Booking zone wise Excel Method**************************************	

	public void UtsMatchUnmatchBookingzonewise(String a, String b , String c, String d , String e ) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		  tamsdbconnection dbcon =new tamsdbconnection(); 
		  con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		 // String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
		  Statement stmt = con.createStatement();
		  
		  String query= "select a.zone rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
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
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  HSSFFont my_font=hwb.createFont();

		  
		  
		  
		  
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			
			rowhead1.createCell((short) 3).setCellValue("STATISTICS ");
			rowhead1.createCell((short) 4).setCellValue(" OF PASSENGER ");
			rowhead1.createCell((short) 5).setCellValue(" APPORTIONMENT ");
			rowhead1.createCell((short) 6).setCellValue(" FOR ALL INDIAN ");
			rowhead1.createCell((short) 7).setCellValue(" RAILWAYS (UTS)");
			rowhead1.createCell((short) 8).setCellValue(" (Booking ");
			rowhead1.createCell((short) 9).setCellValue(" zone wise)");

			rowhead1.createCell((short) 10).setCellValue("By CRIS");
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			rowhead3.createCell((short) 0).setCellValue("Figures" );
			rowhead3.createCell((short) 1).setCellValue("in Units");
			rowhead3.createCell((short) 5).setCellValue(d );
			rowhead3.createCell((short) 6).setCellValue(" - " + e);
			
			rowhead3.createCell((short) 11).setCellValue("Date - ");
			rowhead3.createCell((short) 12).setCellValue(modifiedDate);
			
			 HSSFRow rowhead2 =sheet.createRow((short) 3);
			 rowhead2.createCell((short) 2).setCellValue("INPUT");
			 rowhead2.createCell((short) 3).setCellValue("RECORDS");

			 
			 rowhead2.createCell((short) 6).setCellValue("MATCH");
			 rowhead2.createCell((short) 7).setCellValue("RECORDS");
			 
			 rowhead2.createCell((short) 10).setCellValue("UNMATCH");
			 rowhead2.createCell((short) 11).setCellValue("RECORDS");
			 
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  rowhead.createCell((short) 0).setCellValue("RLY"); 
		  sheet.autoSizeColumn((short) 0);
		  rowhead.createCell((short)1).setCellValue("Records");
		  sheet.autoSizeColumn((short) 1);
		  rowhead.createCell((short)2).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 2);
		  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)3).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 3);
		  rowhead.createCell((short)4).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 4);
		  rowhead.createCell((short)5).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 5);
		  rowhead.createCell((short)6).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 6);
		  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)7).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 7);
		  rowhead.createCell((short)8).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 8);
		  rowhead.createCell((short)9).setCellValue("Records"); 
		  sheet.autoSizeColumn((short) 9);
		  rowhead.createCell((short)10).setCellValue("Full Fare");
		  sheet.autoSizeColumn((short) 10);
		  rowhead.createCell((short)13).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)11).setCellValue("Safety Charges");
		  sheet.autoSizeColumn((short) 11);
		  rowhead.createCell((short)12).setCellValue("Other Charges");
		  sheet.autoSizeColumn((short) 12);
		  int index=7; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("rly"));
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_OTHERCHG"));
				//row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
			    row.createCell((short) 7).setCellValue(rs.getString("MATCH_SAFETYCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_OTHERCHG"));
				//row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				//row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  //sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  //sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  //sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
				index++;

				FileOutputStream fileOut = new FileOutputStream(filename);
				hwb.write(fileOut);
				fileOut.close();
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 0).setCellValue("Total");
			//row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 2).setCellValue(sumr2);
		    row.createCell((short) 3).setCellValue(sumr3);
			row.createCell((short) 4).setCellValue(sumr4);
			//row.createCell((short) 4).setCellValue(sumr5);
			row.createCell((short) 5).setCellValue(sumr5);
			row.createCell((short) 6).setCellValue(sumr6);
			row.createCell((short) 7).setCellValue(sumr7);
			row.createCell((short) 8).setCellValue(sumr8);
			//row.createCell((short) 8).setCellValue(sumr10);
			row.createCell((short) 9).setCellValue(sumr9);
			row.createCell((short) 10).setCellValue(sumr10);
			row.createCell((short) 11).setCellValue(sumr11);
			row.createCell((short) 12).setCellValue(sumr12);
			//row.createCell((short) 12).setCellValue(sumr15);
			//row.createCell((short)15).setCellValue(rs.getString("UNMATCH_CATGCHG"));
		
			FileOutputStream fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated");
        downloadFile(filename,b);	
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
		
	}

	
	@SuppressWarnings("resource")
	public void downloadFile(String path ,String filename ) throws MalformedURLException, IOException{
		System.out.println("fle name is"+path);
		
		
       File my_file = new File(path ); // We are downloading .txt file, in the format of doc with name check - check.doc
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".xls\"");
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
	
	
	
	
	//zone full name
	
	
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



	
	
}
*/







/*package tams.action;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Font;

import tamsdbconnection.tamsdbconnection;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class ExportExcel {
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

	tamsdbconnection dbcon;
	Connection con = null;
	String serachtext;
	
	Date date = new Date();
	String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);

	@SuppressWarnings("deprecation")
	public void exportcateringchgzonewisetrainwise(String a, String b, String c, String d, String e) throws Exception {

		tamsdbconnection dbcon = new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone is " + a);

		String filename = "/ARMSFILES/sheet/" + a + " " + b + " " + d
				+ " " + e + ".xls";
		
		

		String filename = "D:\\TAMSJASPERREPORTS\\" + a + " " + b + " " + d
				+ " " + e + ".xls";

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
				+ " from taedapprprjan2018 WHERE ownrly ='"
				+ a
				+ "' GROUP BY ownrly ,trainno ) a WHERE a.catchg <> 0 ORDER BY a.trainno ";

		System.out.println("strQuery >>>" + strQuery);

		ResultSet rs = stmt.executeQuery(strQuery);

		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet sheet = hwb.createSheet("new sheet");
		sheet.autoSizeColumn((short) 0);
		HSSFRow rowhead1 = sheet.createRow((short) 1);
		rowhead1.createCell((short) 3).setCellValue(b);
		sheet.autoSizeColumn((short) 3);
		rowhead1.createCell((short) 4).setCellValue(" ");
		rowhead1.createCell((short) 5).setCellValue(" ");
		rowhead1.createCell((short) 6).setCellValue("Date");
		rowhead1.createCell((short) 7).setCellValue(modifiedDate);
		
		
		HSSFRow rowhead2 = sheet.createRow((short) 2);
		rowhead1.createCell((short) 4).setCellValue(a);
	
		HSSFRow rowhead = sheet.createRow((short) 3);
		rowhead.createCell((short) 3).setCellValue("S_No");
		rowhead.createCell((short) 4).setCellValue("Train Number");
		sheet.autoSizeColumn((short) 4);
		rowhead.createCell((short) 5).setCellValue("CatChg");
		sheet.autoSizeColumn((short) 5);

		int index = 4;
		int sno = 0;
		String name = "";
	
		while (rs.next()) {
			System.out.println("inside while is" + rs.getString(1));
			sno++;

			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 3).setCellValue(rs.getString(1));
			row.createCell((short) 4).setCellValue(rs.getString(2));
			System.out.println("train no is" + rs.getString(1));
			row.createCell((short) 5).setCellValue(rs.getString(3));
			System.out.println("catering no is" + rs.getString(2));
			index++;
			
			
			
		}

		FileOutputStream fileOut = new FileOutputStream(filename);
		hwb.write(fileOut);
		fileOut.close();
		System.out.println("Your excel file has been generated");

		
		
	
		
		
		  
		  
			downloadFile(filename , b);
		  
	}
	public void  prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		ArrayList<Integer> retainevalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> inwardvalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> totalvalue =new 	ArrayList<Integer>() ;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  
		  Statement stmt = con.createStatement();
		  
			//Statement stmt = con.createStatement();
			
			String Query=" select a.ownrly, a.catchg CR,b.catchg EC,c.catchg EO,d.catchg ER,e.catchg KR,f.catchg NC,g.catchg NE,h.catchg NF,i.catchg NR,j.catchg NW,k.catchg SB, l.catchg SC,m.catchg SE,n.catchg SR,o.catchg SW,p.catchg WC,q.catchg WR,"
					+ " r.catchg Retained, ((a.catchg + b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  + m.catchg  +n.catchg  +o.catchg  +p.catchg  + q.catchg ) - r.catchg) inward, "
					+ " (a.catchg +b.catchg +c.catchg +d.catchg +e.catchg +f.catchg  +g.catchg +h.catchg +i.catchg  +j.catchg +k.catchg +l.catchg  +m.catchg + n.catchg  +o.catchg  +p.catchg  + q.catchg ) Total "
					+ " from  (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'CR' group by ownrly order by ownrly ) a,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EC' group by ownrly order by ownrly ) "
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'EC')) b, "
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EO' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'EO')) c, "
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'ER' group by ownrly order by ownrly ) "
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'ER')) d, "
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'KR' group by ownrly order by ownrly ) "
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'KR')) e,"
					+ " (select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NC' group by ownrly order by ownrly ) union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'NC')) f,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NE' group by ownrly order by ownrly ) union"
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NE')) g,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NF' group by ownrly order by ownrly ) "
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NF')) h,"
					+ " (select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NR' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'NR')) i,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NW' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'NW')) j,"
					+ " (select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SB' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SB')) k,"
					+ " (select ownrly,catchg from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SC' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'SC')) l,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SE' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SE')) m,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SR' group by ownrly order by ownrly )"
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'SR')) n,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SW' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly from taedapprprjan2018 where orgrly = 'SW')) o,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WC' group by ownrly order by ownrly ) "
					+ " union select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'WC')) p,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WR' group by ownrly order by ownrly ) union "
					+ " select distinct ownrly,0 as catgchg from taedapprprjan2018 where orgrly = 'CR' and ownrly not in (select distinct ownrly  from taedapprprjan2018 where orgrly = 'WR')) q,"
					+ " (select ownrly,catchg from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'CR' and ownrly='CR' group by ownrly order by ownrly) union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EC' and ownrly='EC' group by ownrly order by ownrly) union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'EO' and "
					+ " ownrly='EO' group by ownrly order by ownrly) union  select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'ER' and ownrly='ER' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'KR' and ownrly='KR' group by ownrly order by ownrly) union  "
					+ " select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NC' and ownrly='NC' group by ownrly order by ownrly) union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NE' and ownrly='NE' group by ownrly order by ownrly) union  "
					+ " select ownrly,catchg Retained from(select ownrly,round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NF' and ownrly='NF' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NR' and ownrly='NR' group by ownrly order by ownrly)union"
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'NW' and ownrly='NW' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SB' and ownrly='SB' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SC' and ownrly='SC' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SE' and ownrly='SE' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SR' and ownrly='SR' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'SW' and ownrly='SW' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WC' and ownrly='WC' group by ownrly order by ownrly)union "
					+ " select ownrly,catchg Retained from (select ownrly, round(sum(to_number(na)),0) catchg from taedapprprjan2018 where orgrly = 'WR' and ownrly='WR' group by ownrly order by ownrly)) r  Where"
					+ " a.ownrly = b.ownrly and b.ownrly = c.ownrly and c.ownrly = d.ownrly and d.ownrly = e.ownrly and e.ownrly = f.ownrly and f.ownrly = g.ownrly and g.ownrly = h.ownrly and h.ownrly = i.ownrly and i.ownrly = j.ownrly and j.ownrly = k.ownrly and k.ownrly = l.ownrly and l.ownrly = m.ownrly and m.ownrly = n.ownrly and n.ownrly = o.ownrly and o.ownrly = p.ownrly and p.ownrly = q.ownrly and q.ownrly = r.ownrly";

			

			System.out.println("strQuery >>>" + Query);

			  ResultSet rs = stmt.executeQuery(Query);	
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
				HSSFRow rowhead1 = sheet.createRow((short) 1);
				HSSFCellStyle style = hwb.createCellStyle();
				HSSFFont font = hwb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setFontHeightInPoints((short)10);
				font.setBoldweight((short) 10);//.setBold(true);
				style.setFont(font);
				rowhead1.createCell((short) 3).setCellValue("SUMMARY OF");//APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS            By CRIS"
				rowhead1.createCell((short) 4).setCellValue("APPORTIONMENT");
				rowhead1.createCell((short) 5).setCellValue("OF CATERING");
				rowhead1.createCell((short) 6).setCellValue("CHARGES");
				rowhead1.createCell((short) 7).setCellValue("FOR ALL");
				rowhead1.createCell((short) 8).setCellValue("INDIAN");
				rowhead1.createCell((short) 9).setCellValue("RAILWAYS");
				rowhead1.createCell((short) 10).setCellValue("PRS");
				rowhead1.createCell((short) 11).setCellValue("");
				rowhead1.createCell((short) 12).setCellValue("");
				rowhead1.createCell((short) 13).setCellValue("");
				rowhead1.createCell((short) 14).setCellValue("");
				rowhead1.createCell((short) 15).setCellValue("");
				rowhead1.createCell((short) 16).setCellValue("");
				rowhead1.createCell((short) 17).setCellValue("");
				rowhead1.createCell((short) 18).setCellValue("");
				rowhead1.createCell((short) 19).setCellValue("BY");
				rowhead1.createCell((short) 20).setCellValue("CRIS");
				//rowhead1.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowhead11 =sheet.createRow((short)4); 
				 rowhead11.createCell((short) 19).setCellValue("Date");
				 rowhead11.createCell((short) 20).setCellValue(modifiedDate);
				  HSSFRow rowhead =sheet.createRow((short)4); 
				  rowhead.createCell((short) 0).setCellValue("ZONE"); 
				  rowhead.createCell((short)1).setCellValue("CR"); 
				  rowhead.createCell((short)2).setCellValue("EC");
				  rowhead.createCell((short)3).setCellValue("EO"); 
				  rowhead.createCell((short)4).setCellValue("ER");
				  rowhead.createCell((short) 5).setCellValue("KR"); 
				  rowhead.createCell((short)6).setCellValue("NC"); 
				  rowhead.createCell((short)7).setCellValue("NE");
				  rowhead.createCell((short)8).setCellValue("NF"); 
				  rowhead.createCell((short)9).setCellValue("NR");
				  rowhead.createCell((short)10).setCellValue("NW"); 
				  rowhead.createCell((short)11).setCellValue("SB");
				  rowhead.createCell((short)12).setCellValue("SC"); 
				  rowhead.createCell((short)13).setCellValue("SE");
				  rowhead.createCell((short) 14).setCellValue("SR"); 
				  rowhead.createCell((short)15).setCellValue("SW"); 
				  rowhead.createCell((short)16).setCellValue("WC");
				  rowhead.createCell((short)17).setCellValue("WR"); 
				  rowhead.createCell((short)18).setCellValue("Retained Share");
				  rowhead.createCell((short)19).setCellValue("Inward Share");
				  rowhead.createCell((short)20).setCellValue("Total Apport. Earning");
				  for(int index=0 ; index<=20 ;index++){
					  rowhead.getCell((short) index).setCellStyle((HSSFCellStyle) style);
					  
				  }
				  int index=6; int sno=0; String name="";
				  while(rs.next()) {
				
					  HSSFRow row = sheet.createRow((short)index); 
					  row.createCell((short)0).setCellValue(rs.getString("OWNRLY")); 
					  row.createCell((short) 1).setCellValue(rs.getString("CR"));
					  //System.out.println("train no is"+rs.getString("EC")); 
					  row.createCell((short)2).setCellValue(rs.getString("EC"));
					  row.createCell((short)3).setCellValue(rs.getString("EO"));
					  row.createCell((short)4).setCellValue(rs.getString("ER"));
					  row.createCell((short)5).setCellValue(rs.getString("KR"));
					  row.createCell((short)6).setCellValue(rs.getString("NC"));
					  row.createCell((short)7).setCellValue(rs.getString("NE"));
					  
					  row.createCell((short)8).setCellValue(rs.getString("NF"));
					  row.createCell((short)9).setCellValue(rs.getString("NR"));
					  row.createCell((short)10).setCellValue(rs.getString("NW"));
					  
					  row.createCell((short)11).setCellValue(rs.getString("SB"));
					  row.createCell((short)12).setCellValue(rs.getString("SC"));
					  row.createCell((short)13).setCellValue(rs.getString("SE"));
					  
					  row.createCell((short)14).setCellValue(rs.getString("SR"));
					  row.createCell((short)15).setCellValue(rs.getString("SW"));
					  row.createCell((short)16).setCellValue(rs.getString("WC"));
					  
					  row.createCell((short)17).setCellValue(rs.getString("WR"));
					  row.createCell((short)18).setCellValue(rs.getString("RETAINED"));
					  int a1=Integer.parseInt(rs.getString("RETAINED"));
					  retainevalue.add(i, a1);
					  row.createCell((short)19).setCellValue(rs.getString("INWARD"));
					  int b1=Integer.parseInt(rs.getString("INWARD"));
					  inwardvalue.add(i, b1);
					  row.createCell((short)20).setCellValue(rs.getString("TOTAL"));
					  int c1=Integer.parseInt(rs.getString("TOTAL"));
					  totalvalue.add(i, c1);
					  
					  	sumr1=sumr1+Long.parseLong(rs.getString("CR"));
			             sumr2=sumr1+Long.parseLong(rs.getString("EC"));
			             sumr3=sumr1+Long.parseLong(rs.getString("EO"));
			             sumr4=sumr1+Long.parseLong(rs.getString("ER"));
			             sumr5=sumr1+Long.parseLong(rs.getString("KR"));
			             sumr6=sumr1+Long.parseLong(rs.getString("NC"));
			             sumr7=sumr1+Long.parseLong(rs.getString("NE"));
			             sumr8=sumr1+Long.parseLong(rs.getString("NF"));
			             sumr9=sumr1+Long.parseLong(rs.getString("NR"));
			             sumr10=sumr1+Long.parseLong(rs.getString("NW"));
			             sumr11=sumr1+Long.parseLong(rs.getString("SB"));
			             sumr12=sumr1+Long.parseLong(rs.getString("SC"));
			             sumr13=sumr1+Long.parseLong(rs.getString("SE"));
			             sumr14=sumr1+Long.parseLong(rs.getString("SR"));
			             sumr15=sumr1+Long.parseLong(rs.getString("SW"));
			             sumr16=sumr1+Long.parseLong(rs.getString("WC"));
			             sumr17=sumr1+Long.parseLong(rs.getString("WR"));
			             sumr18=sumr1+Long.parseLong(rs.getString("RETAINED"));
			             sumr19=sumr1+Long.parseLong(rs.getString("INWARD"));
			             sumr20=sumr1+Long.parseLong(rs.getString("TOTAL"));
			             index++ ;
			             i++;
					  
					  
					  }
				  
				  HSSFRow rowus = sheet.createRow((short) index+1);
				  rowus.createCell((short)0).setCellValue("Unmatched Share"); 
				  rowus.createCell((short) 1).setCellValue("0");
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowus.createCell((short)2).setCellValue("0");
				  rowus.createCell((short)3).setCellValue("0");
				  rowus.createCell((short)4).setCellValue("0");
				  rowus.createCell((short)5).setCellValue("0");
				  rowus.createCell((short)6).setCellValue("0");
				  rowus.createCell((short)7).setCellValue("0");
				  
				  rowus.createCell((short)8).setCellValue("0");
				  rowus.createCell((short)9).setCellValue("0");
				  rowus.createCell((short)10).setCellValue("0");
				  
				  rowus.createCell((short)11).setCellValue("0");
				  rowus.createCell((short)12).setCellValue("0");
				  rowus.createCell((short)13).setCellValue("0");
				  
				  rowus.createCell((short)14).setCellValue("0");
				  rowus.createCell((short)15).setCellValue("0");
				  rowus.createCell((short)16).setCellValue("0");
				  
				  rowus.createCell((short)17).setCellValue("0");
				  rowus.createCell((short)18).setCellValue(sumr18);
				  rowus.createCell((short)19).setCellValue(sumr19);
				  rowus.createCell((short)20).setCellValue(sumr20);
				  
				  
				  HSSFRow rowoa = sheet.createRow((short) index+2);
				  rowoa.createCell((short)0).setCellValue("Orignating Amount"); 
				  rowoa.createCell((short) 1).setCellValue(sumr1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowoa.createCell((short)2).setCellValue(sumr2);
				  rowoa.createCell((short)3).setCellValue(sumr3);
				  rowoa.createCell((short)4).setCellValue(sumr4);
				  rowoa.createCell((short)5).setCellValue(sumr5);
				  rowoa.createCell((short)6).setCellValue(sumr6);
				  rowoa.createCell((short)7).setCellValue(sumr7);
				  
				  rowoa.createCell((short)8).setCellValue(sumr8);
				  rowoa.createCell((short)9).setCellValue(sumr9);
				  rowoa.createCell((short)10).setCellValue(sumr10);
				  
				  rowoa.createCell((short)11).setCellValue(sumr11);
				  rowoa.createCell((short)12).setCellValue(sumr12);
				  rowoa.createCell((short)13).setCellValue(sumr13);
				  
				  rowoa.createCell((short)14).setCellValue(sumr14);
				  rowoa.createCell((short)15).setCellValue(sumr15);
				  rowoa.createCell((short)16).setCellValue(sumr16);
				  
				  rowoa.createCell((short)17).setCellValue(sumr17);
				  rowoa.createCell((short)18).setCellValue("");
				  rowoa.createCell((short)19).setCellValue("");
				  rowoa.createCell((short)20).setCellValue(sumr20);
				  
				  HSSFRow rowos = sheet.createRow((short) index+3);//retainevalue
				  long col1= sumr1-retainevalue.get(0);
				  long col2= sumr1-retainevalue.get(1);
				  long col3= sumr1-retainevalue.get(2);
				  long col4= sumr1-retainevalue.get(3);
				  long col5= sumr1-retainevalue.get(4);
				  long col6= sumr1-retainevalue.get(5);
				  long col7= sumr1-retainevalue.get(6);
				  long col8= sumr1-retainevalue.get(7);
				  long col9= sumr1-retainevalue.get(8);
				  long col10= sumr1-retainevalue.get(9);
				  long col11= sumr1-retainevalue.get(10);
				  long col12= sumr1-retainevalue.get(11);
				  long col13= sumr1-retainevalue.get(12);
				  long col14= sumr1-retainevalue.get(13);
				  long col15= sumr1-retainevalue.get(14);
				  long col16= sumr1-retainevalue.get(15);
				  long col17= sumr1-retainevalue.get(16);
				  long col18= sumr1-retainevalue.get(17);
				  long col19= sumr1-retainevalue.get(18);
				  long col20= sumr1-retainevalue.get(19);
				  long sum = col1+col2+col3+col4+col5+col6+col7+col8+col9+col10+col11+col12+col13+col14+col15+col16+col17;
				  
				  rowos.createCell((short)0).setCellValue("Outward Share"); 
				  rowos.createCell((short) 1).setCellValue(col1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowos.createCell((short)2).setCellValue(col2);
				  rowos.createCell((short)3).setCellValue(col3);
				  rowos.createCell((short)4).setCellValue(col4);
				  rowos.createCell((short)5).setCellValue(col5);
				  rowos.createCell((short)6).setCellValue(col6);
				  rowos.createCell((short)7).setCellValue(col7);
				  
				  rowos.createCell((short)8).setCellValue(col8);
				  rowos.createCell((short)9).setCellValue(col9);
				  rowos.createCell((short)10).setCellValue(col10);
				  
				  rowos.createCell((short)11).setCellValue(col11);
				  rowos.createCell((short)12).setCellValue(col12);
				  rowos.createCell((short)13).setCellValue(col13);
				  
				  rowos.createCell((short)14).setCellValue(col14);
				  rowos.createCell((short)15).setCellValue(col15);
				  rowos.createCell((short)16).setCellValue(col16);
				  
				  rowos.createCell((short)17).setCellValue(col17);
				  rowos.createCell((short)18).setCellValue("");
				  rowos.createCell((short)19).setCellValue("");
				  rowos.createCell((short)20).setCellValue(sum);
				  
				  
				 //retaine row 
				  
				  HSSFRow rowretain = sheet.createRow((short) index+4);
				  rowretain.createCell((short)0).setCellValue("Retained"); 
				  long sumofretain=0;
				  int k0=1;
				  for(int j=0 ;j<retainevalue.size(); j++){
					  
					  int aa=retainevalue.get(j);
					  
					  rowretain.createCell((short)k0).setCellValue(aa);
					  ++k0;
					  sumofretain=sumofretain+aa;
					  
				  }
				  rowretain.createCell((short) 18).setCellValue(""); 
				  rowretain.createCell((short) 19).setCellValue(""); 
				  rowretain.createCell((short)  20).setCellValue(sumofretain); 
				  
				  //INwARD ROW
				  
				  
				  HSSFRow rowinward = sheet.createRow((short) index+5);
				  rowinward.createCell((short)0).setCellValue("Inward"); 
				  long suminwrad=0;
				  int k=1;
				  for(int j=0 ;j<inwardvalue.size(); j++){
					  
					  int aa=inwardvalue.get(j);
					  
					  rowinward.createCell((short) k).setCellValue(aa);
					  k++;
					  suminwrad=suminwrad+aa;
					  
				  }
				  rowinward.createCell((short) 18).setCellValue(""); 
				  rowinward.createCell((short) 19).setCellValue(""); 
				  rowinward.createCell((short) 20).setCellValue(suminwrad); 
				  
				  
				  //Total Apport. Earnings
				  HSSFRow rowtotal = sheet.createRow((short) index+6);
				  rowtotal.createCell((short)0).setCellValue("Total Apport. Earnings"); 
				  long sumtot=0;
				  int k3=1;
				  for(int j=0 ;j<totalvalue.size(); j++){
					 
					  int aa=totalvalue.get(j);
					  
					  rowtotal.createCell((short)k3).setCellValue(aa);
					  
					 sumtot=sumtot+aa;
					  k3++;
					  
				  }
				  rowtotal.createCell((short) 18).setCellValue(""); 
				  rowtotal.createCell((short) 19).setCellValue(""); 
				  rowtotal.createCell((short) 20).setCellValue(sumtot); 
				  
				  
				  
				  OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename=prtrlvd.xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					
				  
				  
		  
	
	}
	
	public void exportprsstationnotintrainroute(String a, String b , String c, String d , String e ) throws Exception{
		  
		  
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  HttpServletResponse response = ServletActionContext.getResponse();
		  
		  String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  
		  Statement stmt = con.createStatement();
		  
		  String strQuery = " select distinct case when  orgrly = 'CR'  	then 'Central Railway' when  orgrly = 'ER' then 'Eastern Railway' "
					+ " when  orgrly = 'EC' 	then 'East Central Railway' when  orgrly = 'EO' 	then 'East Coast Railway' "
					+ " when  orgrly = 'KR'  	then 'Konkan Railway' when  orgrly = 'NC'  then 'North Central Railway'"
					+ " when  orgrly = 'NE' then 'North Eastern Railway' when  orgrly = 'NF'  then 'North Frontier Railway' "
					+ " when  orgrly = 'NR'  then 'Northern Railway' when  orgrly = 'NW' then 'North Western Railway' "
					+ " when  orgrly = 'SB'  	then 'South East Central Railway' when  orgrly = 'SC'  then 'South Central Railway' when  orgrly = 'SE'"
					+ "	then 'South Eastern Railway' when  orgrly = 'SR'  	then 'Southern Railway' when  orgrly = 'SW'  then 'South Western Railway' when  orgrly = 'WC' then 'West Central Railway' "
					+ " when orgrly = 'WR'  then 'Western  Railway' "
					+ " end orgrly_name,trainno,sstn,sstn9,dstn,destn9 from taedapprprjan2018 where orgrly='" +a+
					 "' and  apprcnf_flag is null and trainno is not null   order by trainno,sstn,dstn ";
		  
		  System.out.println("strQuery >>>"+strQuery);
		  
		  ResultSet rs = stmt.executeQuery(strQuery);	
		    HSSFWorkbook hwb = new HSSFWorkbook(); 
		    HSSFSheet sheet =hwb.createSheet("new sheet"); 
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			rowhead1.createCell((short) 3).setCellValue(b);
			sheet.autoSizeColumn((short) 3);
			rowhead1.createCell((short) 5).setCellValue("Date");
			rowhead1.createCell((short) 6).setCellValue(modifiedDate);
			
	
		
		  HSSFRow rowhead =sheet.createRow((short)3); 
		  rowhead.createCell((short) 0).setCellValue("Train Number"); 
		  rowhead.createCell((short)1).setCellValue("From Station"); 
		  rowhead.createCell((short)2).setCellValue("From Station Code");
		  rowhead.createCell((short)3).setCellValue("To Station"); 
		  rowhead.createCell((short)4).setCellValue("To Station Code");
		  int index=3; int sno=0; String name="";
		  //System.out.println("traisdffffffffffffffffn no is"+rs.getString(1));
		  while(rs.next()) {
		  System.out.println("inside while is"+rs.getString(1)); 
		  sno++;
		  
		  HSSFRow row = sheet.createRow((short)index); 
		  row.createCell((short)0).setCellValue(rs.getString(1)); 
		  row.createCell((short) 1).setCellValue(rs.getString(2));
		  //System.out.println("train no is"+rs.getString(1)); 
		  row.createCell((short)2).setCellValue(rs.getString(3));
		  row.createCell((short)3).setCellValue(rs.getString(4));
		  row.createCell((short)4).setCellValue(rs.getString(5));
		  //System.out.println("catering no is"+rs.getString(2)); index++; }
		  
		  
		  FileOutputStream fileOut = new FileOutputStream(filename);
		  hwb.write(fileOut); fileOut.close();
		  System.out.println("Your excel file has been generated");
		  OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=prtrlvd.xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
		 // downloadFile(filename,b);
		  
		  }
		

	}
	
	
	public void prsmatchunmatch(String a, String b , String c, String d , String e ) throws Exception{
		
		
		
		
		  
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  
		  Statement stmt = con.createStatement();
		  String query="select a.orgrly rly,a.total_records Input_total ,a.tfare Input_fullfare,a.catgchg Input_catgchg,a.saf Input_safetychg,a.chg Input_otherchg, b.total_records Match_total,b.tfare Match_fullfare,"
					+ " a.catgchg Match_catgchg,b.saf Match_safetychg,b.chg Match_otherchg, c.total_records Unmatch_total,c.tfare Unmatch_fullfare,0 as Unmatch_catgchg,c.saf Unmatch_safetychg,c.chg Unmatch_otherchg from "
					+ " (select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg from taedapprprjan2018  group by orgrly order by orgrly)  a , "
					+ "(select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg   from taedapprprjan2018 where apprcnf_flag = 'Y' group by orgrly order by orgrly)  b ,  "
					+ "(select orgrly,count(*) total_Records, sum(tfare)-sum(to_number(na)) tfare,sum(to_number(na))catgchg ,sum(saf) saf,sum(chg) chg  from taedapprprjan2018  "
					+ "where apprcnf_flag is null or apprcnf_flag not in ('Y') group by orgrly order by orgrly)  c where a.orgrly = b.orgrly and b.orgrly = c.orgrly ";
			
		
	     System.out.println("strQuery >>>"+query);
		  ResultSet rs = stmt.executeQuery(query);
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  HSSFFont my_font=hwb.createFont();

		  
		  
		  
		  
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			
			rowhead1.createCell((short) 3).setCellValue("STATISTICS OF PASSENGER APPORTIONMENT FOR ALL RAILWAYS PRS");
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			rowhead1.createCell((short) 7).setCellValue("BY CRIS");
			rowhead1.createCell((short) 6).setCellValue(modifiedDate);
			
			 HSSFRow rowhead2 =sheet.createRow((short) 3);
			 rowhead2.createCell((short) 2).setCellValue("INPUT RECORDS");
			 rowhead2.createCell((short) 6).setCellValue("MATCH RECORDS");
			 rowhead2.createCell((short) 11).setCellValue("UNMATCH RECORDS");
			 
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  rowhead.createCell((short) 0).setCellValue("RLY"); 
		  rowhead.createCell((short)1).setCellValue("TOTAL RECORDS"); 
		  rowhead.createCell((short)2).setCellValue("FARE");
		  rowhead.createCell((short)3).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)3).setCellValue("OTHER CHARGES");
		  rowhead.createCell((short)4).setCellValue("CATERING CHARGES");
		  rowhead.createCell((short)5).setCellValue("TOTAL RECORDS"); 
		  rowhead.createCell((short)6).setCellValue("FARE");
		  rowhead.createCell((short)8).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)7).setCellValue("OTHER CHARGES");
		  rowhead.createCell((short)8).setCellValue("CATERING CHARGES");
		  rowhead.createCell((short)9).setCellValue("TOTAL RECORDS"); 
		  rowhead.createCell((short)10).setCellValue("FARE");
		  rowhead.createCell((short)13).setCellValue("SAFETY CHARGES"); 
		  rowhead.createCell((short)11).setCellValue("OTHER CHARGES");
		  rowhead.createCell((short)12).setCellValue("CATERING CHARGES");
		  int index=7; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("rly"));
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_OTHERCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_SAFETYCHG"));
				row.createCell((short) 7).setCellValue(rs.getString("MATCH_OTHERCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				row.createCell((short) 13).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				  sumr13=sumr13+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr14=sumr14+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
 				index++;

 				FileOutputStream fileOut = new FileOutputStream(filename);
 				hwb.write(fileOut);
 				fileOut.close();
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 0).setCellValue("Total");
			//row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 2).setCellValue(sumr2);
			row.createCell((short) 3).setCellValue(sumr3);
			row.createCell((short) 3).setCellValue(sumr4);
			row.createCell((short) 4).setCellValue(sumr5);
			row.createCell((short) 5).setCellValue(sumr6);
			row.createCell((short) 6).setCellValue(sumr7);
			row.createCell((short) 8).setCellValue(sumr8);
			row.createCell((short) 7).setCellValue(sumr9);
			row.createCell((short) 8).setCellValue(sumr10);
			row.createCell((short) 9).setCellValue(sumr11);
			row.createCell((short) 10).setCellValue(sumr12);
			row.createCell((short) 13).setCellValue(sumr13);
			row.createCell((short) 11).setCellValue(sumr14);
			row.createCell((short) 12).setCellValue(sumr15);
			//row.createCell((short)15).setCellValue(rs.getString("UNMATCH_CATGCHG"));
			
		
			 

			FileOutputStream fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated");

			
			  downloadFile(filename,b);
			
		
		
	}
	
	
	

	
	@SuppressWarnings("resource")
	public void downloadFile(String path ,String filename ) throws MalformedURLException, IOException{
		System.out.println("fle name is"+path);
		
		
       File my_file = new File(path ); // We are downloading .txt file, in the format of doc with name check - check.doc
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".xls\"");
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
	
	
	
	
	public void deletefile(File filename)
	{
		if(filename.delete()){
			
		}
	}
	
	
}
*/
package tams.action;
/*
import org.apache.poi.xssf.usermodel.XSSFRichTextString;*/
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
/*import org.apache.poi.hssf.util.CellRangeAddress;*/
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import tamsdbconnection.tamsdbconnection;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

/*import javax.faces.context.FacesContext;*/
import javax.servlet.http.HttpServletResponse;

public class ExportExcel {
	HttpServletResponse response = ServletActionContext.getResponse();
	
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
	long sumr20=0 , sumr21=0 , sumr22=0,sumr23 =0;
	String zonename;
	
	Viewdata view_data_obj = new Viewdata();
	
	tamsdbconnection dbcon;
	Connection con = null;
	String serachtext;
	
	Date date = new Date();
	String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);

	@SuppressWarnings("deprecation")
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
				+ " X.Total_shared Total_shared,X.Diff Diff "
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
		 
		 
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  
		  
		  
		  HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
			
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
		        HSSFRow rowzone=sheet.createRow(0); 
		        Cell cel2 = rowzone.getCell(0);  
	            cel2 = rowzone.createCell(0);    
	            cel2.setCellValue("    PRTNFILE REPORT     ");
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 2));
	           
	            
	            Cell cel20 = rowzone.getCell(5);  
	            cel20 = rowzone.createCell(5);    
	            cel20.setCellValue("               "+zonename);
	            cel20.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 5 , 7));
	            
	            
	            

	            Cell cel21 = rowzone.getCell(14);  
	            cel21 = rowzone.createCell(14);    
	            cel21.setCellValue("    Date - "+modifiedDate);
	           // cel21.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 14, 17));
	           

	            HSSFRow rowhead22 =sheet.createRow((short) 1);
				
				  Cell cel22 = rowhead22.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel22 = rowhead22.createCell(4);    
				  cel22.setCellValue("       PASSENGER APPORTIONMENT OF PRS FOR THE MONTH    "+d+"-"+e);
				  cel22.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 4, 12));
	            
				  
				  Cell cel = rowhead22.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel = rowhead22.createCell(13);    
				  cel.setCellValue("      Figures in  Rupess ");
				 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 13, 15));
	            
				  Cell cellast = rowhead22.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cellast = rowhead22.createCell(16);    
				  cellast.setCellValue("      BY CRIS   ");
				  cellast.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 16, 17));
	            
				  
				  
		
			int index = 5;
		
			
			
			  HSSFRow rowhead =sheet.createRow((short) 4); 
			  
			  Cell celcol1 = rowhead.getCell(0);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol1 = rowhead.createCell(0);    
			  celcol1.setCellValue("RLY TO");
			  celcol1.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			  //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 16, 17));
            
			  Cell celcol2 = rowhead.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol2 = rowhead.createCell(1);    
			  celcol2.setCellValue("Gauge Code");
			  celcol2.setCellStyle(cs);
			  
			  Cell celcol3 = rowhead.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol3 = rowhead.createCell(2);    
			  celcol3.setCellValue("Amount");
			  celcol3.setCellStyle(boldStyle);
			  
			  Cell celcol4 = rowhead.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol4 = rowhead.createCell(3);    
			  celcol4.setCellValue("CR");
			  celcol4.setCellStyle(boldStyle);
			  
			  Cell celcol5 = rowhead.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol5 = rowhead.createCell(4);    
			  celcol5.setCellValue("EC");
			  celcol5.setCellStyle(boldStyle);
			  
			  Cell celcol6 = rowhead.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol6 = rowhead.createCell(5);    
			  celcol6.setCellValue("EO");
			  celcol6.setCellStyle(boldStyle);
			  
			  Cell celcol7 = rowhead.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol7 = rowhead.createCell(6);    
			  celcol7.setCellValue("ER");
			  celcol7.setCellStyle(boldStyle);
			  
			  Cell celcol8 = rowhead.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol8 = rowhead.createCell(7);    
			  celcol8.setCellValue("KR");
			  celcol8.setCellStyle(boldStyle);
			  
			  Cell celcol9 = rowhead.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol9 = rowhead.createCell(8);    
			  celcol9.setCellValue("NC");
			  celcol9.setCellStyle(boldStyle);
			  
			  Cell celcol10 = rowhead.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol10 = rowhead.createCell(9);    
			  celcol10.setCellValue("NE");
			  celcol10.setCellStyle(boldStyle);
			  
			  Cell celcol11 = rowhead.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol11 = rowhead.createCell(10);    
			  celcol11.setCellValue("NF");
			  celcol11.setCellStyle(boldStyle);
			  
			  Cell celcol12 = rowhead.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol12 = rowhead.createCell(11);    
			  celcol12.setCellValue("NR");
			  celcol12.setCellStyle(boldStyle);
			  
			  Cell celcol13 = rowhead.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol13 = rowhead.createCell(12);    
			  celcol13.setCellValue("NW");
			  celcol13.setCellStyle(boldStyle);
			  
			  Cell celcol14 = rowhead.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol14 = rowhead.createCell(13);    
			  celcol14.setCellValue("SB");
			  celcol14.setCellStyle(boldStyle);
			  
			  
			  Cell celcol15 = rowhead.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol15 = rowhead.createCell(14);    
			  celcol15.setCellValue("SC");
			  celcol15.setCellStyle(boldStyle);
			  
			  
			  Cell celcol16 = rowhead.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol16 = rowhead.createCell(15);    
			  celcol16.setCellValue("SE");
			  celcol16.setCellStyle(boldStyle);
			  
			  
			  Cell celcol17 = rowhead.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol17 = rowhead.createCell(16);    
			  celcol17.setCellValue("SR");
			  celcol17.setCellStyle(boldStyle);
			  
			  Cell celcol18 = rowhead.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol18 = rowhead.createCell(17);    
			  celcol18.setCellValue("SW");
			  celcol18.setCellStyle(boldStyle);
			  
			  Cell celcol19 = rowhead.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol19 = rowhead.createCell(18);    
			  celcol19.setCellValue("WC");
			  celcol19.setCellStyle(boldStyle);
			  
			  
			  Cell celcol20 = rowhead.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol20 = rowhead.createCell(19);    
			  celcol20.setCellValue("WR");
			  celcol20.setCellStyle(boldStyle);
			  
			  Cell celcol21 = rowhead.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol21 = rowhead.createCell(20);    
			  celcol21.setCellValue("TOTAL SHARED");
			  celcol21.setCellStyle(cs);
			  
			  Cell celcol22 = rowhead.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol22 = rowhead.createCell(21);    
			  celcol22.setCellValue("DIFF");
			  celcol22.setCellStyle(boldStyle);
			  
			while (rs.next()) {
				
				System.out.println("m is"+m);
			
		   		
		   		if((m%3)==0  && m!=0)
		   		{
		   			int in=index;
		   			index++;
		   			HSSFRow rowtr= sheet.createRow((short) in);
		   		
		   			rowtr.createCell((short) 0).setCellValue("");
		   			/*rowtr.createCell((short) 1).setCellValue("TOT CRDT");*/
		   			


					  Cell celtr = rowtr.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr = rowtr.createCell(1);    
					  celtr.setCellValue("TOT CRDT");
					  celtr.setCellStyle(cs);
					  
					  Cell celtr1 = rowtr.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr1 = rowtr.createCell(2);    
					  celtr1.setCellValue(String.valueOf(Sum_array[k][1]));
					  celtr1.setCellStyle(boldStyle);
		   			
					  Cell celtr2 = rowtr.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr2 = rowtr.createCell(3);    
					  celtr2.setCellValue(String.valueOf(Sum_array[k][2]));
					  celtr2.setCellStyle(boldStyle);
					  
					  Cell celtr3 = rowtr.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr3 = rowtr.createCell(4);    
					  celtr3.setCellValue(String.valueOf(Sum_array[k][3]));
					  celtr3.setCellStyle(boldStyle);
					  
					  Cell celtr4 = rowtr.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr4 = rowtr.createCell(5);    
					  celtr4.setCellValue(String.valueOf(Sum_array[k][4]));
					  celtr4.setCellStyle(boldStyle);
					  
					  Cell celtr5 = rowtr.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr5 = rowtr.createCell(6);    
					  celtr5.setCellValue(String.valueOf(Sum_array[k][5]));
					  celtr5.setCellStyle(boldStyle);
					  
					  Cell celtr6 = rowtr.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr6 = rowtr.createCell(7);    
					  celtr6.setCellValue(String.valueOf(Sum_array[k][6]));
					  celtr6.setCellStyle(boldStyle);
					  
					  Cell celtr7 = rowtr.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr7 = rowtr.createCell(8);    
					  celtr7.setCellValue(String.valueOf(Sum_array[k][7]));
					  celtr7.setCellStyle(boldStyle);
					  
					  Cell celtr8 = rowtr.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr8 = rowtr.createCell(9);    
					  celtr8.setCellValue(String.valueOf(Sum_array[k][8]));
					  celtr8.setCellStyle(boldStyle);
					  
					  Cell celtr9 = rowtr.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr9 = rowtr.createCell(10);    
					  celtr9.setCellValue(String.valueOf(Sum_array[k][9]));
					  celtr9.setCellStyle(boldStyle);
					  
					  Cell celtr10 = rowtr.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr10 = rowtr.createCell(11);    
					  celtr10.setCellValue(String.valueOf(Sum_array[k][10]));
					  celtr10.setCellStyle(boldStyle);
					  
					  Cell celtr11 = rowtr.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr11 = rowtr.createCell(12);    
					  celtr11.setCellValue(String.valueOf(Sum_array[k][11]));
					  celtr11.setCellStyle(boldStyle);
					  
					  Cell celtr12 = rowtr.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr12 = rowtr.createCell(13);    
					  celtr12.setCellValue(String.valueOf(Sum_array[k][12]));
					  celtr12.setCellStyle(boldStyle);
					  
					  Cell celtr13 = rowtr.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr13 = rowtr.createCell(14);    
					  celtr13.setCellValue(String.valueOf(Sum_array[k][13]));
					  celtr13.setCellStyle(boldStyle);
					  
					  Cell celtr14 = rowtr.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr14 = rowtr.createCell(15);    
					  celtr14.setCellValue(String.valueOf(Sum_array[k][14]));
					  celtr14.setCellStyle(boldStyle);
					  
					  Cell celtr15 = rowtr.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr15 = rowtr.createCell(16);    
					  celtr15.setCellValue(String.valueOf(Sum_array[k][15]));
					  celtr15.setCellStyle(boldStyle);
					  
					  Cell celtr16 = rowtr.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr16 = rowtr.createCell(17);    
					  celtr16.setCellValue(String.valueOf(Sum_array[k][16]));
					  celtr16.setCellStyle(boldStyle);
					  
					  Cell celtr17 = rowtr.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr17 = rowtr.createCell(18);    
					  celtr17.setCellValue(String.valueOf(Sum_array[k][17]));
					  celtr17.setCellStyle(boldStyle);
					  
					  Cell celtr18 = rowtr.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr18 = rowtr.createCell(19);    
					  celtr18.setCellValue(String.valueOf(Sum_array[k][18]));
					  celtr18.setCellStyle(boldStyle);
					  
					  Cell celtr19 = rowtr.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr19 = rowtr.createCell(20);    
					  celtr19.setCellValue(String.valueOf(Sum_array[k][19]));
					  celtr19.setCellStyle(boldStyle);
					  
					  Cell celtr20 = rowtr.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr20 = rowtr.createCell(21);    
					  celtr20.setCellValue(String.valueOf(Sum_array[k][20]));
					  celtr20.setCellStyle(boldStyle);
		   			
		   			/*rowtr.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
		   			rowtr.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
		   			rowtr.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
		   			rowtr.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
		   			rowtr.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
		   			rowtr.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
		   			rowtr.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
		   			rowtr.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
		   			rowtr.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
		   			rowtr.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
		   			rowtr.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
		   			rowtr.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
		   			rowtr.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
		   			rowtr.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
		   			rowtr.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
		   			rowtr.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
		   			rowtr.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
		   			rowtr.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
		   			rowtr.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
		   			rowtr.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));*/	
				
		   		}
		   		
		   		
		   		
		   		
		   		
		   		HSSFRow row = sheet.createRow((short) index);

				if((m%3)==0)
				{

					  Cell celtorly = row.getCell(0);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtorly = row.createCell(0);    
					  celtorly.setCellValue(rs.getString("torly"));
					  celtorly.setCellStyle(boldStyle);
					
				}
				else row.createCell((short) 0).setCellValue((""));
		   		
				
		   		//row.createCell((short) 1).setCellValue(rs.getString("orgg"));

				  Cell celtorg = row.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  celtorg = row.createCell(1);    
				  celtorg.setCellValue(rs.getString("orgg"));
				  celtorg.setCellStyle(boldStyle);
				  
	   			row.createCell((short) 2).setCellValue(rs.getString("Amount"));
	   			row.createCell((short) 3).setCellValue(rs.getString("CR"));
	   			row.createCell((short) 4).setCellValue(rs.getString("EC"));
	   			row.createCell((short) 5).setCellValue(rs.getString("EO"));
	   			row.createCell((short) 6).setCellValue(rs.getString("ER"));
	   			row.createCell((short) 7).setCellValue(rs.getString("KR"));
	   			row.createCell((short) 8).setCellValue(rs.getString("NC"));
	   			row.createCell((short) 9).setCellValue(rs.getString("NE"));
	   			row.createCell((short) 10).setCellValue(rs.getString("NF"));
	   			row.createCell((short) 11).setCellValue(rs.getString("NR"));
	   			row.createCell((short) 12).setCellValue(rs.getString("NW"));
	   			row.createCell((short) 13).setCellValue(rs.getString("SB"));
	   			row.createCell((short) 14).setCellValue(rs.getString("SC"));
	   			row.createCell((short) 15).setCellValue(rs.getString("SE"));
	   			row.createCell((short) 16).setCellValue(rs.getString("SR"));
	   			row.createCell((short) 17).setCellValue(rs.getString("SW"));
	   			row.createCell((short) 18).setCellValue(rs.getString("WC"));
	   			row.createCell((short) 19).setCellValue(rs.getString("WR"));
	   			row.createCell((short) 20).setCellValue(rs.getString("Total_shared"));
	   			row.createCell((short) 21).setCellValue(rs.getString("Diff"));
				
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
		   		
		   		index++;
		   		++m;
		   		
		   		
			}
			
			
			
			

			HSSFRow rowtotal = sheet.createRow((short) index);
			
			
			rowtotal.createCell((short) 0).setCellValue("");
   			/*rowtotal.createCell((short) 1).setCellValue("TOT CRDT");*/
   			


			  Cell celtr = rowtotal.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr = rowtotal.createCell(1);    
			  celtr.setCellValue("TOT CRDT");
			  celtr.setCellStyle(cs);
			  
			  Cell celtr1 = rowtotal.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr1 = rowtotal.createCell(2);    
			  celtr1.setCellValue(String.valueOf(Sum_array[k][1]));
			  celtr1.setCellStyle(boldStyle);
   			
			  Cell celtr2 = rowtotal.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr2 = rowtotal.createCell(3);    
			  celtr2.setCellValue(String.valueOf(Sum_array[k][2]));
			  celtr2.setCellStyle(boldStyle);
			  
			  Cell celtr3 = rowtotal.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr3 = rowtotal.createCell(4);    
			  celtr3.setCellValue(String.valueOf(Sum_array[k][3]));
			  celtr3.setCellStyle(boldStyle);
			  
			  Cell celtr4 = rowtotal.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr4 = rowtotal.createCell(5);    
			  celtr4.setCellValue(String.valueOf(Sum_array[k][4]));
			  celtr4.setCellStyle(boldStyle);
			  
			  Cell celtr5 = rowtotal.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr5 = rowtotal.createCell(6);    
			  celtr5.setCellValue(String.valueOf(Sum_array[k][5]));
			  celtr5.setCellStyle(boldStyle);
			  
			  Cell celtr6 = rowtotal.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr6 = rowtotal.createCell(7);    
			  celtr6.setCellValue(String.valueOf(Sum_array[k][6]));
			  celtr6.setCellStyle(boldStyle);
			  
			  Cell celtr7 = rowtotal.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr7 = rowtotal.createCell(8);    
			  celtr7.setCellValue(String.valueOf(Sum_array[k][7]));
			  celtr7.setCellStyle(boldStyle);
			  
			  Cell celtr8 = rowtotal.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr8 = rowtotal.createCell(9);    
			  celtr8.setCellValue(String.valueOf(Sum_array[k][8]));
			  celtr8.setCellStyle(boldStyle);
			  
			  Cell celtr9 = rowtotal.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr9 = rowtotal.createCell(10);    
			  celtr9.setCellValue(String.valueOf(Sum_array[k][9]));
			  celtr9.setCellStyle(boldStyle);
			  
			  Cell celtr10 = rowtotal.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr10 = rowtotal.createCell(11);    
			  celtr10.setCellValue(String.valueOf(Sum_array[k][10]));
			  celtr10.setCellStyle(boldStyle);
			  
			  Cell celtr11 = rowtotal.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr11 = rowtotal.createCell(12);    
			  celtr11.setCellValue(String.valueOf(Sum_array[k][11]));
			  celtr11.setCellStyle(boldStyle);
			  
			  Cell celtr12 = rowtotal.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr12 = rowtotal.createCell(13);    
			  celtr12.setCellValue(String.valueOf(Sum_array[k][12]));
			  celtr12.setCellStyle(boldStyle);
			  
			  Cell celtr13 = rowtotal.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr13 = rowtotal.createCell(14);    
			  celtr13.setCellValue(String.valueOf(Sum_array[k][13]));
			  celtr13.setCellStyle(boldStyle);
			  
			  Cell celtr14 = rowtotal.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr14 = rowtotal.createCell(15);    
			  celtr14.setCellValue(String.valueOf(Sum_array[k][14]));
			  celtr14.setCellStyle(boldStyle);
			  
			  Cell celtr15 = rowtotal.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr15 = rowtotal.createCell(16);    
			  celtr15.setCellValue(String.valueOf(Sum_array[k][15]));
			  celtr15.setCellStyle(boldStyle);
			  
			  Cell celtr16 = rowtotal.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr16 = rowtotal.createCell(17);    
			  celtr16.setCellValue(String.valueOf(Sum_array[k][16]));
			  celtr16.setCellStyle(boldStyle);
			  
			  Cell celtr17 = rowtotal.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr17 = rowtotal.createCell(18);    
			  celtr17.setCellValue(String.valueOf(Sum_array[k][17]));
			  celtr17.setCellStyle(boldStyle);
			  
			  Cell celtr18 = rowtotal.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr18 = rowtotal.createCell(19);    
			  celtr18.setCellValue(String.valueOf(Sum_array[k][18]));
			  celtr18.setCellStyle(boldStyle);
			  
			  Cell celtr19 = rowtotal.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr19 = rowtotal.createCell(20);    
			  celtr19.setCellValue(String.valueOf(Sum_array[k][19]));
			  celtr19.setCellStyle(boldStyle);
			  
			  Cell celtr20 = rowtotal.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr20 = rowtotal.createCell(21);    
			  celtr20.setCellValue(String.valueOf(Sum_array[k][20]));
			  celtr20.setCellStyle(boldStyle);
   			
			
			/*rowtotal.createCell((short) 0).setCellValue("");
			rowtotal.createCell((short) 1).setCellValue("TOT CRDT");
			rowtotal.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
			rowtotal.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
			rowtotal.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
			rowtotal.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
			rowtotal.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
			rowtotal.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
			rowtotal.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
			rowtotal.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
			rowtotal.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
			rowtotal.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
			rowtotal.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
			rowtotal.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
			rowtotal.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
			rowtotal.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
			rowtotal.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
			rowtotal.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
			rowtotal.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
			rowtotal.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
			rowtotal.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
			rowtotal.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));
        */
            
			con.close();
			OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
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
		
		/*int sum=0;*/

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
		 
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
		/*	sheet.autoSizeColumn((short) 0);
			HSSFRow rowhead1 = sheet.createRow((short) 1);
			rowhead1.createCell((short) 1).setCellValue("UTS PRTNFILE ");//PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE By CRIS
			sheet.autoSizeColumn((short) 1);
			rowhead1.createCell((short) 2).setCellValue("REPORT");
			sheet.autoSizeColumn((short) 2);
			rowhead1.createCell((short) 5).setCellValue(zonename);
			sheet.autoSizeColumn((short) 5);
			rowhead1.createCell((short) 8).setCellValue("Date");
			rowhead1.createCell((short) 9).setCellValue(modifiedDate);
			
			
			
			HSSFRow rowhead2 = sheet.createRow((short) 2);
			rowhead2.createCell((short) 1).setCellValue("Figures in");
			sheet.autoSizeColumn((short) 1);
			rowhead2.createCell((short) 2).setCellValue("Units");
			rowhead2.createCell((short) 4).setCellValue("PASSENGER");//PASSENGER APPORTIONMENT OF PRS FOR THE MONTH OF
			rowhead2.createCell((short) 5).setCellValue("APPORTIONMENT");
			sheet.autoSizeColumn((short) 5);
			rowhead2.createCell((short) 7).setCellValue("OF UTS FOR");
			sheet.autoSizeColumn((short) 7);
			rowhead2.createCell((short) 8).setCellValue("THE MONTH OF");
			sheet.autoSizeColumn((short) 8);
			rowhead2.createCell((short) 9).setCellValue(d+"-");
			rowhead2.createCell((short) 10).setCellValue(e);
			
			rowhead2.createCell((short) 12).setCellValue("BY CRIS");
			sheet.autoSizeColumn((short) 12);*/
			
			 HSSFFont boldFont = hwb.createFont();
				boldFont.setFontHeightInPoints((short)12);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				HSSFFont boldFont2 = hwb.createFont();
				boldFont.setFontHeightInPoints((short)10);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				CellStyle cs =hwb.createCellStyle();
				 cs.setWrapText(true);
				 cs.setFont(boldFont);
				
			        HSSFCellStyle boldStyle = hwb.createCellStyle();
			        boldStyle.setFont(boldFont);
			        
			        HSSFRow rowzone=sheet.createRow(0); 
			        Cell cel2 = rowzone.getCell(0);  
		            cel2 = rowzone.createCell(0);    
		            cel2.setCellValue("    PRTNFILE REPORT     ");
		            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 2));
		           
		            
		            Cell cel20 = rowzone.getCell(5);  
		            cel20 = rowzone.createCell(5);    
		            cel20.setCellValue("               "+zonename);
		            cel20.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 5 , 7));
		            
		            
		            

		            Cell cel21 = rowzone.getCell(14);  
		            cel21 = rowzone.createCell(14);    
		            cel21.setCellValue("    Date - "+modifiedDate);
		           // cel21.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 14, 17));
		           

		            HSSFRow rowhead22 =sheet.createRow((short) 1);
					
					  Cell cel22 = rowhead22.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  cel22 = rowhead22.createCell(4);    
					  cel22.setCellValue("       PASSENGER APPORTIONMENT OF UTS FOR THE MONTH    "+d+"-"+e);
					  cel22.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
					  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 4, 12));
		            
					  
					  Cell cel = rowhead22.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  cel = rowhead22.createCell(13);    
					  cel.setCellValue("      Figures in  Rupees ");
					 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
					  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 13, 15));
		            
					  Cell cellast = rowhead22.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  cellast = rowhead22.createCell(16);    
					  cellast.setCellValue("      BY CRIS   ");
					  cellast.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
					  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 16, 17));
		            
					  
					  
			

			int index = 5;
			int sno = 0;
			String name = "";
			
			/*
			  HSSFRow rowhead =sheet.createRow((short) 4); 
			  rowhead.createCell((short) 0).setCellValue("RLY TO"); //Gauge Code
			  rowhead.createCell((short)1).setCellValue("Gauge Code"); 
			  rowhead.createCell((short)2).setCellValue("Amount");
			  rowhead.createCell((short)3).setCellValue("CR"); 
			  rowhead.createCell((short)4).setCellValue("EC");
			  rowhead.createCell((short) 5).setCellValue("EO"); 
			  rowhead.createCell((short)6).setCellValue("ER"); 
			  rowhead.createCell((short)7).setCellValue("KR");
			  rowhead.createCell((short)8).setCellValue("NC"); 
			  rowhead.createCell((short)9).setCellValue("NE");
			  rowhead.createCell((short)10).setCellValue("NF"); 
			  rowhead.createCell((short)11).setCellValue("NR");
			  rowhead.createCell((short)12).setCellValue("NW"); 
			  rowhead.createCell((short)13).setCellValue("SB");
			  rowhead.createCell((short) 14).setCellValue("SC"); 
			  rowhead.createCell((short)15).setCellValue("SE"); 
			  rowhead.createCell((short)16).setCellValue("SR");
			  rowhead.createCell((short)17).setCellValue("SW"); 
			  rowhead.createCell((short)18).setCellValue("WC");
			  rowhead.createCell((short)19).setCellValue("WR");
			  rowhead.createCell((short)20).setCellValue("TOTAL SHARED");
			  rowhead.createCell((short)21).setCellValue("DIFF");
			  */
			
			
			
  HSSFRow rowhead =sheet.createRow((short) 4); 
			  
			  Cell celcol1 = rowhead.getCell(0);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol1 = rowhead.createCell(0);    
			  celcol1.setCellValue("RLY TO");
			  celcol1.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			  //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 16, 17));
            
			  Cell celcol2 = rowhead.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol2 = rowhead.createCell(1);    
			  celcol2.setCellValue("Gauge Code");
			  celcol2.setCellStyle(cs);
			  
			  Cell celcol3 = rowhead.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol3 = rowhead.createCell(2);    
			  celcol3.setCellValue("Amount");
			  celcol3.setCellStyle(boldStyle);
			  
			  Cell celcol4 = rowhead.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol4 = rowhead.createCell(3);    
			  celcol4.setCellValue("CR");
			  celcol4.setCellStyle(boldStyle);
			  
			  Cell celcol5 = rowhead.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol5 = rowhead.createCell(4);    
			  celcol5.setCellValue("EC");
			  celcol5.setCellStyle(boldStyle);
			  
			  Cell celcol6 = rowhead.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol6 = rowhead.createCell(5);    
			  celcol6.setCellValue("EO");
			  celcol6.setCellStyle(boldStyle);
			  
			  Cell celcol7 = rowhead.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol7 = rowhead.createCell(6);    
			  celcol7.setCellValue("ER");
			  celcol7.setCellStyle(boldStyle);
			  
			  Cell celcol8 = rowhead.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol8 = rowhead.createCell(7);    
			  celcol8.setCellValue("KR");
			  celcol8.setCellStyle(boldStyle);
			  
			  Cell celcol9 = rowhead.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol9 = rowhead.createCell(8);    
			  celcol9.setCellValue("NC");
			  celcol9.setCellStyle(boldStyle);
			  
			  Cell celcol10 = rowhead.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol10 = rowhead.createCell(9);    
			  celcol10.setCellValue("NE");
			  celcol10.setCellStyle(boldStyle);
			  
			  Cell celcol11 = rowhead.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol11 = rowhead.createCell(10);    
			  celcol11.setCellValue("NF");
			  celcol11.setCellStyle(boldStyle);
			  
			  Cell celcol12 = rowhead.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol12 = rowhead.createCell(11);    
			  celcol12.setCellValue("NR");
			  celcol12.setCellStyle(boldStyle);
			  
			  Cell celcol13 = rowhead.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol13 = rowhead.createCell(12);    
			  celcol13.setCellValue("NW");
			  celcol13.setCellStyle(boldStyle);
			  
			  Cell celcol14 = rowhead.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol14 = rowhead.createCell(13);    
			  celcol14.setCellValue("SB");
			  celcol14.setCellStyle(boldStyle);
			  
			  
			  Cell celcol15 = rowhead.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol15 = rowhead.createCell(14);    
			  celcol15.setCellValue("SC");
			  celcol15.setCellStyle(boldStyle);
			  
			  
			  Cell celcol16 = rowhead.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol16 = rowhead.createCell(15);    
			  celcol16.setCellValue("SE");
			  celcol16.setCellStyle(boldStyle);
			  
			  
			  Cell celcol17 = rowhead.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol17 = rowhead.createCell(16);    
			  celcol17.setCellValue("SR");
			  celcol17.setCellStyle(boldStyle);
			  
			  Cell celcol18 = rowhead.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol18 = rowhead.createCell(17);    
			  celcol18.setCellValue("SW");
			  celcol18.setCellStyle(boldStyle);
			  
			  Cell celcol19 = rowhead.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol19 = rowhead.createCell(18);    
			  celcol19.setCellValue("WC");
			  celcol19.setCellStyle(boldStyle);
			  
			  
			  Cell celcol20 = rowhead.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol20 = rowhead.createCell(19);    
			  celcol20.setCellValue("WR");
			  celcol20.setCellStyle(boldStyle);
			  
			  Cell celcol21 = rowhead.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol21 = rowhead.createCell(20);    
			  celcol21.setCellValue("TOTAL SHARED");
			  celcol21.setCellStyle(cs);
			  
			  Cell celcol22 = rowhead.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celcol22 = rowhead.createCell(21);    
			  celcol22.setCellValue("DIFF");
			  celcol22.setCellStyle(boldStyle);
		
			while (rs.next()) {
				
				System.out.println("m is"+m);
			
		   		
		   		if((m%4)==0  && m!=0)
		   		{
		   			int in=index;
		   			index++;
		   			HSSFRow rowtr= sheet.createRow((short) in);
		   		
		   			//rowtr.createCell((short) 0).setCellValue("");

					  Cell celtr = rowtr.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr = rowtr.createCell(1);    
					  celtr.setCellValue("TOT CRDT");
					  celtr.setCellStyle(cs);
					  
					  Cell celtr1 = rowtr.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr1 = rowtr.createCell(2);    
					  celtr1.setCellValue(String.valueOf(Sum_array[k][1]));
					  celtr1.setCellStyle(boldStyle);
		   			
					  Cell celtr2 = rowtr.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr2 = rowtr.createCell(3);    
					  celtr2.setCellValue(String.valueOf(Sum_array[k][2]));
					  celtr2.setCellStyle(boldStyle);
					  
					  Cell celtr3 = rowtr.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr3 = rowtr.createCell(4);    
					  celtr3.setCellValue(String.valueOf(Sum_array[k][3]));
					  celtr3.setCellStyle(boldStyle);
					  
					  Cell celtr4 = rowtr.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr4 = rowtr.createCell(5);    
					  celtr4.setCellValue(String.valueOf(Sum_array[k][4]));
					  celtr4.setCellStyle(boldStyle);
					  
					  Cell celtr5 = rowtr.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr5 = rowtr.createCell(6);    
					  celtr5.setCellValue(String.valueOf(Sum_array[k][5]));
					  celtr5.setCellStyle(boldStyle);
					  
					  Cell celtr6 = rowtr.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr6 = rowtr.createCell(7);    
					  celtr6.setCellValue(String.valueOf(Sum_array[k][6]));
					  celtr6.setCellStyle(boldStyle);
					  
					  Cell celtr7 = rowtr.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr7 = rowtr.createCell(8);    
					  celtr7.setCellValue(String.valueOf(Sum_array[k][7]));
					  celtr7.setCellStyle(boldStyle);
					  
					  Cell celtr8 = rowtr.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr8 = rowtr.createCell(9);    
					  celtr8.setCellValue(String.valueOf(Sum_array[k][8]));
					  celtr8.setCellStyle(boldStyle);
					  
					  Cell celtr9 = rowtr.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr9 = rowtr.createCell(10);    
					  celtr9.setCellValue(String.valueOf(Sum_array[k][9]));
					  celtr9.setCellStyle(boldStyle);
					  
					  Cell celtr10 = rowtr.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr10 = rowtr.createCell(11);    
					  celtr10.setCellValue(String.valueOf(Sum_array[k][10]));
					  celtr10.setCellStyle(boldStyle);
					  
					  Cell celtr11 = rowtr.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr11 = rowtr.createCell(12);    
					  celtr11.setCellValue(String.valueOf(Sum_array[k][11]));
					  celtr11.setCellStyle(boldStyle);
					  
					  Cell celtr12 = rowtr.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr12 = rowtr.createCell(13);    
					  celtr12.setCellValue(String.valueOf(Sum_array[k][12]));
					  celtr12.setCellStyle(boldStyle);
					  
					  Cell celtr13 = rowtr.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr13 = rowtr.createCell(14);    
					  celtr13.setCellValue(String.valueOf(Sum_array[k][13]));
					  celtr13.setCellStyle(boldStyle);
					  
					  Cell celtr14 = rowtr.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr14 = rowtr.createCell(15);    
					  celtr14.setCellValue(String.valueOf(Sum_array[k][14]));
					  celtr14.setCellStyle(boldStyle);
					  
					  Cell celtr15 = rowtr.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr15 = rowtr.createCell(16);    
					  celtr15.setCellValue(String.valueOf(Sum_array[k][15]));
					  celtr15.setCellStyle(boldStyle);
					  
					  Cell celtr16 = rowtr.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr16 = rowtr.createCell(17);    
					  celtr16.setCellValue(String.valueOf(Sum_array[k][16]));
					  celtr16.setCellStyle(boldStyle);
					  
					  Cell celtr17 = rowtr.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr17 = rowtr.createCell(18);    
					  celtr17.setCellValue(String.valueOf(Sum_array[k][17]));
					  celtr17.setCellStyle(boldStyle);
					  
					  Cell celtr18 = rowtr.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr18 = rowtr.createCell(19);    
					  celtr18.setCellValue(String.valueOf(Sum_array[k][18]));
					  celtr18.setCellStyle(boldStyle);
					  
					  Cell celtr19 = rowtr.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr19 = rowtr.createCell(20);    
					  celtr19.setCellValue(String.valueOf(Sum_array[k][19]));
					  celtr19.setCellStyle(boldStyle);
					  
					  Cell celtr20 = rowtr.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celtr20 = rowtr.createCell(21);    
					  celtr20.setCellValue(String.valueOf(Sum_array[k][20]));
					  celtr20.setCellStyle(boldStyle);
		   			
		   			
		   			/*rowtr.createCell((short) 1).setCellValue("TOT CRDT");
		   			rowtr.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
		   			rowtr.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
		   			rowtr.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
		   			rowtr.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
		   			rowtr.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
		   			rowtr.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
		   			rowtr.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
		   			rowtr.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
		   			rowtr.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
		   			rowtr.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
		   			rowtr.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
		   			rowtr.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
		   			rowtr.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
		   			rowtr.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
		   			rowtr.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
		   			rowtr.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
		   			rowtr.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
		   			rowtr.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
		   			rowtr.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
		   			rowtr.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));	
				*/
		   		}
		   		
		   		
		   		
		   		
		   		
		   		HSSFRow row = sheet.createRow((short) index);

				if((m%4)==0)  
				{
					//row.createCell((short) 0).setCellValue(rs.getString("dstnrly"));
					 Cell celt0 = row.getCell(0);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  celt0 = row.createCell(0);    
					  celt0.setCellValue(String.valueOf(rs.getString("dstnrly")));
					  celt0.setCellStyle(boldStyle);
		   			
				}
				else row.createCell((short) 0).setCellValue((""));
		   		
				
		   		row.createCell((short) 1).setCellValue(rs.getString("FROM_GUAGE"));
	   			row.createCell((short) 2).setCellValue(rs.getString("Amount"));
	   			row.createCell((short) 3).setCellValue(rs.getString("CR"));
	   			row.createCell((short) 4).setCellValue(rs.getString("EC"));
	   			row.createCell((short) 5).setCellValue(rs.getString("EO"));
	   			row.createCell((short) 6).setCellValue(rs.getString("ER"));
	   			row.createCell((short) 7).setCellValue(rs.getString("KR"));
	   			row.createCell((short) 8).setCellValue(rs.getString("NC"));
	   			row.createCell((short) 9).setCellValue(rs.getString("NE"));
	   			row.createCell((short) 10).setCellValue(rs.getString("NF"));
	   			row.createCell((short) 11).setCellValue(rs.getString("NR"));
	   			row.createCell((short) 12).setCellValue(rs.getString("NW"));
	   			row.createCell((short) 13).setCellValue(rs.getString("SB"));
	   			row.createCell((short) 14).setCellValue(rs.getString("SC"));
	   			row.createCell((short) 15).setCellValue(rs.getString("SE"));
	   			row.createCell((short) 16).setCellValue(rs.getString("SR"));
	   			row.createCell((short) 17).setCellValue(rs.getString("SW"));
	   			row.createCell((short) 18).setCellValue(rs.getString("WC"));
	   			row.createCell((short) 19).setCellValue(rs.getString("WR"));
	   			row.createCell((short) 20).setCellValue(rs.getString("Total_shared"));
	   			row.createCell((short) 21).setCellValue(rs.getString("Diff"));
				
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
		   		
		   		index++;
		   		++m;
		   		
		   		
			}
			
	HSSFRow rowtotal = sheet.createRow((short) index);
			
			
			rowtotal.createCell((short) 0).setCellValue("");
   			/*rowtotal.createCell((short) 1).setCellValue("TOT CRDT");*/
   			


			  Cell celtr = rowtotal.getCell(1);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr = rowtotal.createCell(1);    
			  celtr.setCellValue("TOT CRDT");
			  celtr.setCellStyle(cs);
			  
			  Cell celtr1 = rowtotal.getCell(2);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr1 = rowtotal.createCell(2);    
			  celtr1.setCellValue(String.valueOf(Sum_array[k][1]));
			  celtr1.setCellStyle(boldStyle);
   			
			  Cell celtr2 = rowtotal.getCell(3);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr2 = rowtotal.createCell(3);    
			  celtr2.setCellValue(String.valueOf(Sum_array[k][2]));
			  celtr2.setCellStyle(boldStyle);
			  
			  Cell celtr3 = rowtotal.getCell(4);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr3 = rowtotal.createCell(4);    
			  celtr3.setCellValue(String.valueOf(Sum_array[k][3]));
			  celtr3.setCellStyle(boldStyle);
			  
			  Cell celtr4 = rowtotal.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr4 = rowtotal.createCell(5);    
			  celtr4.setCellValue(String.valueOf(Sum_array[k][4]));
			  celtr4.setCellStyle(boldStyle);
			  
			  Cell celtr5 = rowtotal.getCell(6);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr5 = rowtotal.createCell(6);    
			  celtr5.setCellValue(String.valueOf(Sum_array[k][5]));
			  celtr5.setCellStyle(boldStyle);
			  
			  Cell celtr6 = rowtotal.getCell(7);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr6 = rowtotal.createCell(7);    
			  celtr6.setCellValue(String.valueOf(Sum_array[k][6]));
			  celtr6.setCellStyle(boldStyle);
			  
			  Cell celtr7 = rowtotal.getCell(8);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr7 = rowtotal.createCell(8);    
			  celtr7.setCellValue(String.valueOf(Sum_array[k][7]));
			  celtr7.setCellStyle(boldStyle);
			  
			  Cell celtr8 = rowtotal.getCell(9);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr8 = rowtotal.createCell(9);    
			  celtr8.setCellValue(String.valueOf(Sum_array[k][8]));
			  celtr8.setCellStyle(boldStyle);
			  
			  Cell celtr9 = rowtotal.getCell(10);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr9 = rowtotal.createCell(10);    
			  celtr9.setCellValue(String.valueOf(Sum_array[k][9]));
			  celtr9.setCellStyle(boldStyle);
			  
			  Cell celtr10 = rowtotal.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr10 = rowtotal.createCell(11);    
			  celtr10.setCellValue(String.valueOf(Sum_array[k][10]));
			  celtr10.setCellStyle(boldStyle);
			  
			  Cell celtr11 = rowtotal.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr11 = rowtotal.createCell(12);    
			  celtr11.setCellValue(String.valueOf(Sum_array[k][11]));
			  celtr11.setCellStyle(boldStyle);
			  
			  Cell celtr12 = rowtotal.getCell(13);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr12 = rowtotal.createCell(13);    
			  celtr12.setCellValue(String.valueOf(Sum_array[k][12]));
			  celtr12.setCellStyle(boldStyle);
			  
			  Cell celtr13 = rowtotal.getCell(14);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr13 = rowtotal.createCell(14);    
			  celtr13.setCellValue(String.valueOf(Sum_array[k][13]));
			  celtr13.setCellStyle(boldStyle);
			  
			  Cell celtr14 = rowtotal.getCell(15);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr14 = rowtotal.createCell(15);    
			  celtr14.setCellValue(String.valueOf(Sum_array[k][14]));
			  celtr14.setCellStyle(boldStyle);
			  
			  Cell celtr15 = rowtotal.getCell(16);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr15 = rowtotal.createCell(16);    
			  celtr15.setCellValue(String.valueOf(Sum_array[k][15]));
			  celtr15.setCellStyle(boldStyle);
			  
			  Cell celtr16 = rowtotal.getCell(17);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr16 = rowtotal.createCell(17);    
			  celtr16.setCellValue(String.valueOf(Sum_array[k][16]));
			  celtr16.setCellStyle(boldStyle);
			  
			  Cell celtr17 = rowtotal.getCell(18);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr17 = rowtotal.createCell(18);    
			  celtr17.setCellValue(String.valueOf(Sum_array[k][17]));
			  celtr17.setCellStyle(boldStyle);
			  
			  Cell celtr18 = rowtotal.getCell(19);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr18 = rowtotal.createCell(19);    
			  celtr18.setCellValue(String.valueOf(Sum_array[k][18]));
			  celtr18.setCellStyle(boldStyle);
			  
			  Cell celtr19 = rowtotal.getCell(20);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr19 = rowtotal.createCell(20);    
			  celtr19.setCellValue(String.valueOf(Sum_array[k][19]));
			  celtr19.setCellStyle(boldStyle);
			  
			  Cell celtr20 = rowtotal.getCell(21);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  celtr20 = rowtotal.createCell(21);    
			  celtr20.setCellValue(String.valueOf(Sum_array[k][20]));
			  celtr20.setCellStyle(boldStyle);
			
			

			/*HSSFRow rowtotal = sheet.createRow((short) index);
			
			
			rowtotal.createCell((short) 0).setCellValue("");
			rowtotal.createCell((short) 1).setCellValue("TOT CRDT");
			rowtotal.createCell((short) 2).setCellValue(String.valueOf(Sum_array[k][1]));
			rowtotal.createCell((short) 3).setCellValue(String.valueOf(Sum_array[k][2]));
			rowtotal.createCell((short) 4).setCellValue(String.valueOf(Sum_array[k][3]));
			rowtotal.createCell((short) 5).setCellValue(String.valueOf(Sum_array[k][4]));
			rowtotal.createCell((short) 6).setCellValue(String.valueOf(Sum_array[k][5]));
			rowtotal.createCell((short) 7).setCellValue(String.valueOf(Sum_array[k][6]));
			rowtotal.createCell((short) 8).setCellValue(String.valueOf(Sum_array[k][7]));
			rowtotal.createCell((short) 9).setCellValue(String.valueOf(Sum_array[k][8]));
			rowtotal.createCell((short) 10).setCellValue(String.valueOf(Sum_array[k][9]));
			rowtotal.createCell((short) 11).setCellValue(String.valueOf(Sum_array[k][10]));
			rowtotal.createCell((short) 12).setCellValue(String.valueOf(Sum_array[k][11]));
			rowtotal.createCell((short) 13).setCellValue(String.valueOf(Sum_array[k][12]));
			rowtotal.createCell((short) 14).setCellValue(String.valueOf(Sum_array[k][13]));
			rowtotal.createCell((short) 15).setCellValue(String.valueOf(Sum_array[k][14]));
			rowtotal.createCell((short) 16).setCellValue(String.valueOf(Sum_array[k][15]));
			rowtotal.createCell((short) 17).setCellValue(String.valueOf(Sum_array[k][16]));
			rowtotal.createCell((short) 18).setCellValue(String.valueOf(Sum_array[k][17]));
			rowtotal.createCell((short) 19).setCellValue(String.valueOf(Sum_array[k][18]));
			rowtotal.createCell((short) 20).setCellValue(String.valueOf(Sum_array[k][19]));
			rowtotal.createCell((short) 21).setCellValue(String.valueOf(Sum_array[k][20]));
        
            */
			
			OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
			  
	}
	
	public void exportcateringchgzonewisetrainwise(String a, String b, String c, String d, String e) throws Exception {

		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		tamsdbconnection dbcon = new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone is " + a);
		 zonename=fullnameofzone(a);
	//String filename = "/ARMSFILES/sheet/" + a + " " + b + " " + d	+ " " + e + ".xls";
		String filename = a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
	


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
		
		
		HSSFWorkbook hwb = new HSSFWorkbook(); //or new HSSFWorkbook();
		
		HSSFFont boldFont = hwb.createFont();
		boldFont.setFontHeightInPoints((short)12);
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFFont boldFont2 = hwb.createFont();
		boldFont.setFontHeightInPoints((short)10);
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		
		
	        HSSFSheet sheet = hwb.createSheet("new sheet");
	        HSSFCellStyle boldStyle = hwb.createCellStyle();
	        boldStyle.setFont(boldFont);
	        
	        HSSFRow rowzone=sheet.createRow(0); 
	        rowzone.createCell((short) 4).setCellValue(zonename);
	        
	        
	        
	        HSSFRow rowhead1 = sheet.createRow((short) 1);
	       
	        Cell cell = rowhead1.getCell(3);  
            cell = rowhead1.createCell(3);    
            cell.setCellValue("PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE By CRIS");
            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1 , 2 , 3 , 11));
           
	

		HSSFRow rowhead2 = sheet.createRow((short) 4);
		rowhead2.createCell((short) 3).setCellValue(d+"-");
		rowhead2.createCell((short) 4).setCellValue(e);
		rowhead2.createCell((short) 6).setCellValue("Date");
		rowhead2.createCell((short) 7).setCellValue(modifiedDate);
		
		
		rowhead1.createCell((short) 4).setCellValue(a);
	
		HSSFRow rowhead = sheet.createRow((short) 5);
		
		Cell cell1 = rowhead.getCell(3);  
        cell1 = rowhead.createCell(3);
        cell1.setCellValue("S_No");
        cell1.setCellStyle(boldStyle);
        
        Cell cell2 = rowhead.getCell(4);  
        cell2 = rowhead.createCell(4);
        cell2.setCellValue("Train Number");
        cell2.setCellStyle(boldStyle);
        sheet.autoSizeColumn(4);
        
        Cell cell3 = rowhead.getCell(5);  
        cell3 = rowhead.createCell(5);
        cell3.setCellValue("CatChg");
        cell3.setCellStyle(boldStyle);
        
        
	

		int index = 6;
		int sno = 0;
		String name = "";
	
		while (rs.next()) {
			System.out.println("inside while is" + rs.getString(1));
			sno++;

			HSSFRow row = sheet.createRow((short) index);
			row.createCell((short) 3).setCellValue(rs.getString(1));
			row.createCell((short) 4).setCellValue(rs.getString(2));
			System.out.println("train no is" + rs.getString(1));
			row.createCell((short) 5).setCellValue(rs.getString(3));
			sumr1=sumr1+Long.parseLong(rs.getString(3));
			System.out.println("catering no is" + rs.getString(2));
			index++;
			
		}
		HSSFRow rowtot = sheet.createRow((short) index);
		Cell celltot = rowtot.getCell(4);  
		celltot = rowtot.createCell(4);
		celltot.setCellValue("TOTAL");
		celltot.setCellStyle(boldStyle);
        
		Cell celltotnum = rowtot.getCell(5);  
		celltot = rowtot.createCell(5);
		celltot.setCellValue(sumr1);
		celltot.setCellStyle(boldStyle);
     
		
		
		OutputStream outObject = response.getOutputStream();
		 response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
		//write this workbook to an Outputstream.
		hwb.write(outObject);
		outObject.flush();
		outObject.close();
		con.close();
		
		
		  
		  
			//downloadFile(filename , b);
		  
	}
	
	public void  prs_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
			
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
			

			System.out.println("strQuery >>>" + query);
			
			  ResultSet rs = stmt.executeQuery(query);
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
				 System.out.println("sum1"+sumr1);
				 System.out.println("sum2"+sumr2);
				  System.out.println("sum3"+sumr3);
				   System.out.println("sum4"+sumr4);
				   
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
					  dbvalue_trans[z][18]=String.valueOf(Long.parseLong(dbvalue_trans[z][19]) - Long.parseLong(dbvalue_trans[z][17]));
				  }
				  
				   for(int k =1; k< 18 ; k++)//Long.parseLong(dbvalue_trans[18][column]
			    	{
			           sumr18= sumr18 + Long.parseLong(dbvalue_trans[k][17]);
			           sumr19=sumr19 + Long.parseLong(dbvalue_trans[k][18]);
			           sumr20=sumr20 + Long.parseLong(dbvalue_trans[k][19]);
			           sumr21=sumr21 + Long.parseLong(dbvalue_trans[20][k-1]);
			           sumr22=sumr22 + Long.parseLong(dbvalue_trans[18][k-1]);
			           
			    	}
				   
				   //printing second array (Transpose array)
					 System.out.println( "second array");
					 for (int r=0; r< nCol; r++) {
					     for (int q=0; q<18; q++) {
					    	 System.out.print(dbvalue_trans[r][q] + " ");
					         
					     }
					     System.out.println();
					 }
					 
				 
				 
					  
			   HSSFWorkbook hwb = new HSSFWorkbook();


				HSSFFont boldFont = hwb.createFont();
				boldFont.setFontHeightInPoints((short)12);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				HSSFFont boldFont2 = hwb.createFont();
				boldFont.setFontHeightInPoints((short)10);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				CellStyle cs =hwb.createCellStyle();
				 cs.setWrapText(true);
				 cs.setFont(boldFont);
				
				
				
			        HSSFSheet sheet = hwb.createSheet("new sheet");
			        HSSFCellStyle boldStyle = hwb.createCellStyle();
			        boldStyle.setFont(boldFont);
			        
			        HSSFRow rowzone=sheet.createRow(0); 
			        rowzone.createCell((short) 4).setCellValue(zonename);
		           HSSFRow rowhead1 = sheet.createRow((short) 1);
			       
			        Cell cell = rowhead1.getCell(0);  
		            cell = rowhead1.createCell(0);    
		            cell.setCellValue("                                                             SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (PRS)                                               By CRIS");
		            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 0 , 20));
		            
		           
			



			           HSSFRow rowhead2 = sheet.createRow((short) 3);
				       
				        Cell cel2 = rowhead2.getCell(0);  
			            cel2 = rowhead2.createCell(0);    
			            cel2.setCellValue("                                                                          "+d+"-"+e+"                                                                                                                                Date-"+ modifiedDate);
			            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 0 , 20));
			           
			            
			            
		                  HSSFRow rowheadfig =sheet.createRow((short) 4); 
						
						   Cell celf = rowheadfig.getCell(14);  
						   celf = rowheadfig.createCell(14);    
						   celf.setCellValue(" Figures in 000's");
						   celf.setCellStyle(boldStyle);
						   
				           sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 4 , 4, 14 , 15));
				           
		                                  HSSFRow rowhead =sheet.createRow((short)5);
		                                   Cell cell1 = rowhead .getCell(0);  
		                                   cell1 = rowhead .createCell(0);
		                                   cell1.setCellValue("Zone");
		                                   cell1.setCellStyle(boldStyle);
		                                      
		                                   Cell cell2 = rowhead .getCell(1);  
		                                   cell2 = rowhead .createCell(1);
		                                   cell2.setCellValue("CR");
		                                   cell2.setCellStyle(boldStyle);


		                                   Cell cell3 = rowhead .getCell(2);  
		                                   cell3 = rowhead .createCell(2);
		                                   cell3.setCellValue("EC");
		                                   cell3.setCellStyle(boldStyle);

		                                   Cell cell4 = rowhead .getCell(3);  
		                                   cell4 = rowhead .createCell(3);
		                                   cell4.setCellValue("EO");
		                                   cell4.setCellStyle(boldStyle);

		                                   Cell cell5 = rowhead .getCell(4);  
		                                   cell5 = rowhead .createCell(4);
		                                   cell5.setCellValue("ER");
		                                   cell5.setCellStyle(boldStyle);

		                                   Cell cell6 = rowhead .getCell(5);  
		                                   cell6 = rowhead .createCell(5);
		                                   cell6.setCellValue("KR");
		                                   cell6.setCellStyle(boldStyle);

		                                   Cell cell7 = rowhead .getCell(6);  
		                                   cell7 = rowhead .createCell(6);
		                                   cell7.setCellValue("NC");
		                                   cell7.setCellStyle(boldStyle);

		                                   Cell cell8 = rowhead .getCell(7);  
		                                   cell8 = rowhead .createCell(7);
		                                   cell8.setCellValue("NE");
		                                   cell8.setCellStyle(boldStyle);

		                                   Cell cell9 = rowhead .getCell(8);  
		                                   cell9 = rowhead .createCell(8);
		                                   cell9.setCellValue("NF");
		                                   cell9.setCellStyle(boldStyle);

		                                   Cell cell10 = rowhead .getCell(9);  
		                                   cell10 = rowhead .createCell(9);
		                                   cell10.setCellValue("NR");
		                                   cell10.setCellStyle(boldStyle);

		                                   Cell cell11 = rowhead .getCell(10);  
		                                   cell11 = rowhead .createCell(10);
		                                   cell11.setCellValue("NW");
		                                   cell11.setCellStyle(boldStyle);


		                                   Cell cell12 = rowhead .getCell(11);  
		                                   cell12 = rowhead .createCell(11);
		                                   cell12.setCellValue("SB");
		                                   cell12.setCellStyle(boldStyle);

		                                   Cell cell13 = rowhead .getCell(12);  
		                                   cell13 = rowhead .createCell(12);
		                                   cell13.setCellValue("SC");
		                                   cell13.setCellStyle(boldStyle);

		                                   Cell cell14 = rowhead .getCell(13);  
		                                   cell14 = rowhead .createCell(13);
		                                   cell14.setCellValue("SE");
		                                   cell14.setCellStyle(boldStyle);

		                                   Cell cell15= rowhead .getCell(14);  
		                                   cell15 = rowhead .createCell(14);
		                                   cell15.setCellValue("SR");
		                                   cell15.setCellStyle(boldStyle);

						                  Cell cell16 = rowhead .getCell(15);  
		                                   cell16  = rowhead .createCell(15);
		                                   cell16 .setCellValue("SW");
		                                   cell16 .setCellStyle(boldStyle);


		                                   Cell cell17 = rowhead .getCell(16);  
		                                   cell17  = rowhead .createCell(16);
		                                   cell17.setCellValue("WC");
		                                   cell17.setCellStyle(boldStyle);


		                                   Cell cell18 = rowhead .getCell(17);  
		                                   cell18  = rowhead .createCell(17);
		                                   cell18.setCellValue("WR");
		                                   cell18.setCellStyle(boldStyle);

		                                   Cell cell19 = rowhead .getCell(18);  
		                                   cell19  = rowhead .createCell(18);
		                                   cell19.setCellValue("Retained Share");
		                                   cell19.setCellStyle(cs);
		                                   //cell19.setCellStyle(boldStyle);

		                                   Cell cell20 = rowhead .getCell(19);  
		                                   cell20  = rowhead .createCell(19);
		                                   cell20.setCellValue("Inward Share");
		                                   cell20.setCellStyle(cs);
		                                   //cell20.setCellStyle(boldStyle);

						                   Cell cell21 = rowhead .getCell(20);  
		                                   cell21 = rowhead .createCell(20);
		                                   cell21.setCellValue("Total Apport. Earning");
		                                   cell21.setCellStyle(boldStyle);
		                                   cell21.setCellStyle(cs);
						  
						  int index=6; int sno=0; String name="";;
				  
				  int jj=1;
				 
				    while(jj<18) {
				    	 int colindex=1;
				    	
				    	 HSSFRow row = sheet.createRow((short)index); 
				    	  Cell cellfirstindex = row .getCell(0);  
				    	  cellfirstindex  = row .createCell(0);
				    	  cellfirstindex.setCellValue(dbvalue[jj-1][0]);
				    	  cellfirstindex.setCellStyle(boldStyle);
				    	 //row.createCell((short) 0).setCellValue(dbvalue[jj-1][0]);
				    	
				    	 
				    	 for(int kk =0; kk< 20 ; kk++)
				        	{
				    		 String vlaue=dbvalue_trans[jj][kk];
				    		 System.out.println("value is"+ vlaue);
				    		 row.createCell((short) colindex).setCellValue(vlaue);
				    		 colindex++;
				        	}
				    	 
				    	 jj++;
				    	 index++;
				    	
				    }
				    
				    
				    HSSFRow rowus =sheet.createRow((short)24);
                    
                       
				    //HSSFRow rowus = sheet.createRow((short) 24);
				    for(int k3 =0; k3< 18 ; k3++)
			    	{
				    	
				    	Cell cellsumb = rowus .getCell(k3);  
				    	cellsumb = rowus .createCell(k3);
				    	cellsumb.setCellValue("");
	                   // cell1.setCellStyle(boldStyle);//owus.createCell((short)k3).setCellValue(""); 
						 
			    	}
				    
				    Cell cellsum19 = rowus .getCell(18);  
				    cellsum19  = rowus .createCell(18);
				    cellsum19.setCellValue(""+sumr18);
				    cellsum19.setCellStyle(boldStyle);
				    
				    
				    Cell cellsum20 = rowus .getCell(19);  
				    cellsum20  = rowus .createCell(19);
				    cellsum20.setCellValue(""+sumr19);
				    cellsum20.setCellStyle(boldStyle);
				    
				    
				    Cell cellsumr20 = rowus .getCell(20);  
				    cellsumr20  = rowus .createCell(20);
				    cellsumr20.setCellValue(""+sumr20);
				    cellsumr20.setCellStyle(boldStyle);
				    
				 
				
				    
				    
				    //OGA ROW
				    HSSFRow rowoga = sheet.createRow((short) 25);
				    //rowoga.createCell((short) 0).setCellValue("OGA");
				    

				    Cell celloga = rowoga .getCell(0);  
				    celloga  = rowoga .createCell(0);
				    celloga.setCellValue("OGA");
				    celloga.setCellStyle(boldStyle);
				    
				    int cindex=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowoga.createCell((short) cindex).setCellValue(dbvalue_trans[18][k4]); 
			        	cindex++;
			    	}
			       // rowoga.createCell((short) cindex).setCellValue(sumr22);
			        Cell cellsumroga = rowoga .getCell(cindex);  
			        cellsumroga  = rowoga .createCell(cindex);
			        cellsumroga.setCellValue(""+sumr22);
			        cellsumroga.setCellStyle(boldStyle);
			        
			        HSSFRow rowretain = sheet.createRow((short) 26);
			       // rowretain.createCell((short) 0).setCellValue("Retained");
			        

				    Cell retainedcol = rowretain .getCell(0);  
				    retainedcol  = rowretain .createCell(0);
				    retainedcol.setCellValue("Retained");
				    retainedcol.setCellStyle(boldStyle);
				    
			        
			        
				    int zz=1;
				    for(int k6 =0; k6< 19 ; k6++)
			    	{
				    	rowretain.createCell((short) zz).setCellValue(dbvalue_trans[19][k6]);
				    	 zz++;
			    	}
				    
				    
				    Cell cellsumretain = rowretain .getCell(zz);  
			        cellsumroga  = rowretain .createCell(zz);
			        cellsumroga.setCellValue(""+sumr18);
			        cellsumroga.setCellStyle(boldStyle);
				    
				  //  rowretain.createCell((short) zz).setCellValue(sumr18);
			        
				    
				    HSSFRow rowoutward = sheet.createRow((short) 27);
				    //rowoutward.createCell((short) 0).setCellValue("OUTWARD");
				    Cell celloutward = rowoutward .getCell(0);  
				    celloutward  = rowoutward .createCell(0);
				    celloutward.setCellValue("Outward");
				    celloutward.setCellStyle(boldStyle);
				    
				    
				    int tt=1;
				    for(int k =0; k< 19 ; k++)
			    	{
				    	rowoutward.createCell((short) tt).setCellValue(dbvalue_trans[20][k]);
				    	tt++;
				    	
			    	}
				    
				   // rowoutward.createCell((short) tt).setCellValue(sumr21);
				    
				    
				    Cell cellsumroutward = rowoutward .getCell(cindex);  
				    cellsumroutward  = rowoutward .createCell(cindex);
				    cellsumroutward.setCellValue(""+sumr21);
				    cellsumroutward.setCellStyle(boldStyle);
				    
				    
				    HSSFRow rowinward = sheet.createRow((short) 28);
				    //rowinward.createCell((short) 0).setCellValue("INWARD");
				    
				    Cell cellinward = rowinward .getCell(0);  
				    cellinward  = rowinward .createCell(0);
				    cellinward.setCellValue("Inward");
				    cellinward.setCellStyle(boldStyle);
				    
				    
				    int ss=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowinward.createCell((short) ss).setCellValue(dbvalue_trans[k][18]);
				    	ss++;
			    	}
				    
				   rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue("");
			

				    Cell cellsumrinward = rowinward .getCell(ss);  
				    cellsumrinward  = rowinward .createCell(ss);
				    cellsumrinward.setCellValue(""+sumr19);
				    cellsumrinward.setCellStyle(boldStyle);
				    
				    
				    
				    
				    HSSFRow rowtot = sheet.createRow((short) 29);
				    
				    Cell celltotheading = rowtot .getCell(0);  
				    celltotheading = rowtot .createCell(0);
				    celltotheading.setCellValue("Total Apport. Earning");
				    celltotheading.setCellStyle(cs);
				    //celltotheading.setCellStyle(boldStyle);
				    //celltotheading.setCellStyle(cs);
				    
				   /* rowtot.createCell((short) 0).setCellValue("Total Apport Earnings");
				    cell21.setCellStyle(cs);*/
				    int uu=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowtot.createCell((short) uu).setCellValue(dbvalue_trans[k][19]);
				    	uu++;
			    	}
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue("");
				   // rowtot.createCell((short) uu++).setCellValue(sumr20);
			       

				    Cell cellsumrtot = rowtot .getCell(uu);  
				    cellsumrinward  = rowtot .createCell(uu);
				    cellsumrinward.setCellValue(""+sumr20);
				    cellsumrinward.setCellStyle(boldStyle);
				    
				    HSSFRow rowfooter = sheet.createRow((short) 31);
				       
			        Cell cellfooter = rowfooter.getCell(3);  
			        cellfooter = rowfooter.createCell(3);    
			        cellfooter.setCellValue("     Note: Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data         ");
			      //  cellfooter.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 31, 31 , 3 , 19));
		            
		            HSSFRow rowfooter2 = sheet.createRow((short) 32);  
			        Cell cellfooter2 = rowfooter2.getCell(3);  
			        cellfooter2 = rowfooter2.createCell(3);    
			        cellfooter2.setCellValue("                   Outward Share : Earnings distributed to other Railways        ");
			       // cellfooter2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 32, 32 , 3 , 19));
		            
		            HSSFRow rowfooter3 = sheet.createRow((short) 33);  
			        Cell cellfooter3 = rowfooter3.getCell(3);  
			        cellfooter3 = rowfooter3.createCell(3);    
			        cellfooter3.setCellValue("     Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic)         ");
			       // cellfooter3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 33, 33 , 3 , 19));
		            
		            
		            
		            HSSFRow rowfooter4 = sheet.createRow((short) 34);  
			        Cell cellfooter4 = rowfooter4.getCell(3);  
			        cellfooter4 = rowfooter4.createCell(3);    
			        cellfooter4.setCellValue("                        Inward Share   : Earnings received from all Railways       ");
			       // cellfooter4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 34, 34 , 3 , 19));
		            
				    
		            
		            HSSFRow rowfooter5 = sheet.createRow((short) 35);  
			        Cell cellfooter5 = rowfooter5.getCell(3);  
			        cellfooter5 = rowfooter5.createCell(3);    
			        cellfooter5.setCellValue("          Total Apportionment Earnings : Retained Share + Inward Share ");
			        //cellfooter5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 35, 35 , 3 , 19));
		            
				    
		            
		            
		            HSSFRow rowfooter6 = sheet.createRow((short) 36);  
			        Cell cellfooter6 = rowfooter6.getCell(2);  
			        cellfooter6 = rowfooter6.createCell(2);    
			        cellfooter6.setCellValue(" Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings. ");
			        cellfooter6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 36 , 36 , 2 , 19));
		            
			        
		            
		            HSSFRow rowfooter7 = sheet.createRow((short) 37);  
			        Cell cellfooter7 = rowfooter7.getCell(2);  
			        cellfooter7 = rowfooter7.createCell(2);    
			        cellfooter7.setCellValue(" Note 2- Catering charges have not been included in apportioned share of railways. ");
			        cellfooter7.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 37, 37 , 2 , 19));
		            
			        
		            
		            
			        
			        OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
			
	}
	
	public void utsunmatchedodpairs(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		 zonename=fullnameofzone(a);
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
		  filename = filename.replace(",", "");
		  Statement stmt = con.createStatement();

		  String query=" select station_from,station_from_code,station_upto,station_to_code,via,distance , sum(full_fare)  from tams_uts where mm_bkg_date ="+month+" and yy_bkg_date = "+year
					+ "and apprcnf_flag is null and orgrly = '"+a+"' group by station_from,station_from_code , station_upto,station_to_code,via,distance  "
					+ "order by station_from,station_upto ";
			
		
	     System.out.println("strQuery >>>"+query);
		  ResultSet rs = stmt.executeQuery(query);
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 	  
		  
		  HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
			
			
			
		        HSSFSheet sheet = hwb.createSheet("new sheet");
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
	           HSSFRow rowhead1 = sheet.createRow((short) 0);
		       
		        Cell cell = rowhead1.getCell(0);  
	            cell = rowhead1.createCell(0);    
	            cell.setCellValue("           UTS Unmatched OD pairs with Via Distance and Full Fare           By CRIS");
	            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 7));
	            
	           


             HSSFRow rowhead2 = sheet.createRow((short) 1);
               Cell cel2 = rowhead2.getCell(3);  
               cel2 = rowhead2.createCell(3);    
               cel2.setCellValue("     "+zonename);
               cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 3 , 5));
	            
	      
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			Cell cel3 = rowhead3.getCell(0);  
			cel3 = rowhead3.createCell(0);    
			cel3.setCellValue("    Figures in Units");
			cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	         sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 0 , 2));
	            
			
	            Cell cel31 = rowhead3.getCell(3);  
	            cel31 = rowhead3.createCell(3);    
	            cel31.setCellValue(d+"-"+e);
	            cel31.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 3 , 4));
		           
		        
		        
		        Cell cel32 = rowhead3.getCell(5);  
		        cel32 = rowhead3.createCell(5);    
		        cel32.setCellValue("Date - "+modifiedDate);
		        cel32.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,5 , 6));
		           
			
			/*rowhead3.createCell((short) 5).setCellValue("Date - ");
			rowhead3.createCell((short) 6).setCellValue(modifiedDate);
			*/
			
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  Cell celcol1 = rowhead.getCell(0);  
		  celcol1 = rowhead.createCell(0);    
		  celcol1.setCellValue("Station From");
		  celcol1.setCellStyle(cs);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	       
	      Cell celcol2 = rowhead.getCell(1);  
	      celcol2 = rowhead.createCell(1);    
	      celcol2.setCellValue("Station From code");
	      celcol2.setCellStyle(cs);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      
	      Cell celcol3 = rowhead.getCell(2);  
	      celcol3 = rowhead.createCell(2);    
	      celcol3.setCellValue("Station Upto");
	      celcol3.setCellStyle(cs);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      
	      Cell celcol4 = rowhead.getCell(3);  
	      celcol4 = rowhead.createCell(3);    
	      celcol4.setCellValue("Station To code");
	      celcol4.setCellStyle(cs);//.setCellStyle(boldStyle);
	     // sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      
	      Cell celcol5 = rowhead.getCell(4);  
	      celcol5 = rowhead.createCell(4);    
	      celcol5.setCellValue("Via");
	      celcol5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      
	      Cell celcol6 = rowhead.getCell(5);  
	      celcol6 = rowhead.createCell(5);    
	      celcol6.setCellValue("Distance");
	      celcol6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      
	      Cell celcol7 = rowhead.getCell(6);  
	      celcol7 = rowhead.createCell(6);    
	      celcol7.setCellValue("Fare");
	      celcol7.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	      //sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 ,6 , 7));
	      /*
		  
		  rowhead.createCell((short) 0).setCellValue("Station From"); 
		  sheet.autoSizeColumn((short) 0);
		  rowhead.createCell((short)1).setCellValue("Station From code");
		  sheet.autoSizeColumn((short) 1);
		  rowhead.createCell((short)2).setCellValue("Station Upto");
		  sheet.autoSizeColumn((short) 2);
		  rowhead.createCell((short)3).setCellValue("Station To code");
		  sheet.autoSizeColumn((short) 3);
		  rowhead.createCell((short)4).setCellValue("Via");
		  sheet.autoSizeColumn((short) 4);
		  rowhead.createCell((short)5).setCellValue("Distance"); 
		  sheet.autoSizeColumn((short) 5);
		  rowhead.createCell((short)6).setCellValue("Fare");
		  sheet.autoSizeColumn((short) 6);
		*/
		  int index=6; int sno=0; String name="";
		  
		  
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString("station_from"));
				row.createCell((short) 1).setCellValue(rs.getString("station_from_code"));
				row.createCell((short) 2).setCellValue(rs.getString("station_upto"));
				row.createCell((short) 3).setCellValue(rs.getString("station_to_code"));
				row.createCell((short) 4).setCellValue(rs.getString("via"));
			    sheet.autoSizeColumn((short) 4);
				
				row.createCell((short) 5).setCellValue(rs.getString("distance"));
				row.createCell((short) 6).setCellValue(rs.getString("sum(full_fare)"));
			  
				
				  sumr1=sumr1+ Long.parseLong(rs.getString("sum(full_fare)"));
				 
				index++;
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			/*row.createCell((short) 5).setCellValue("Total Fare");
		    sheet.autoSizeColumn((short) 5);
			row.createCell((short) 6).setCellValue(sumr1);*/
			
			Cell celcol8 = row.getCell(5);  
			celcol8 = row.createCell(5);    
			celcol8.setCellValue("Total Fare");
			celcol8.setCellStyle(cs);

			Cell celcol9 = row.getCell(6);  
			celcol9 = row.createCell(6);    
			celcol9.setCellValue(sumr1);
			celcol9.setCellStyle(boldStyle);
			
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
	
		 
		
	/*	HSSFWorkbook my_workbook = new HSSFWorkbook();
        HSSFSheet my_sheet = my_workbook.createSheet("Merge Cells");
        
        HSSFRow row = my_sheet.createRow((short) 1);
        HSSFCell cell = row.createCell((short) 1);
        cell.setCellValue("Merge Data with Apache POI");
        my_sheet.addMergedRegion(null);//.addMergedRegion(new CellRangeAddress(1,1,4,1));
        FileOutputStream out = new FileOutputStream(new File("D:\\Merge_Across_Columns.xls"));
        my_workbook.write(out);
        out.close();*/
		
		
		 
	}
	
    public void  uts_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path = "/ARMSFILES/sheet/" + a + " " + b + " " + d	+ " " + e + ".pdf";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d	+ " " + e + ".pdf";
		int sum=0;
		String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		
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
		 
		 
		 
				 
				 
			  
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFFont boldFont = hwb.createFont();
				boldFont.setFontHeightInPoints((short)12);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				HSSFFont boldFont2 = hwb.createFont();
				boldFont.setFontHeightInPoints((short)10);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				CellStyle cs =hwb.createCellStyle();
				 cs.setWrapText(true);
				 cs.setFont(boldFont);
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
			    HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
			    
			    HSSFRow rowzone=sheet.createRow(0); 
		        rowzone.createCell((short) 4).setCellValue(zonename);
	            HSSFRow rowhead1 = sheet.createRow((short) 1);
		       
	            Cell cell = rowhead1.getCell(0);  
	            cell = rowhead1.createCell(0);    
	            cell.setCellValue("                                                             SUMMARY OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)                                                 By CRIS");
	            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 0 , 20));
	            
	            HSSFRow rowhead2 = sheet.createRow((short) 3);
			       
		        Cell cel2 = rowhead2.getCell(0);  
	            cel2 = rowhead2.createCell(0);    
	            cel2.setCellValue("                                                                          "+d+"-"+e+"                                                                                                                                 Date-"+ modifiedDate);
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 0 , 20));
	           
	            HSSFRow rowheadfig =sheet.createRow((short) 4); 
				
				   Cell celf = rowheadfig.getCell(14);  
				   celf = rowheadfig.createCell(14);    
				   celf.setCellValue(" Figures in 000's");
				   celf.setCellStyle(boldStyle);
		           sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 4 , 4, 14 , 15));
		           
		           HSSFRow rowhead =sheet.createRow((short)5);
                   Cell cell1 = rowhead .getCell(0);  
                   cell1 = rowhead .createCell(0);
                   cell1.setCellValue("Zone");
                   cell1.setCellStyle(boldStyle);
                      
                   Cell cell2 = rowhead .getCell(1);  
                   cell2 = rowhead .createCell(1);
                   cell2.setCellValue("CR");
                   cell2.setCellStyle(boldStyle);


                   Cell cell3 = rowhead .getCell(2);  
                   cell3 = rowhead .createCell(2);
                   cell3.setCellValue("EC");
                   cell3.setCellStyle(boldStyle);

                   Cell cell4 = rowhead .getCell(3);  
                   cell4 = rowhead .createCell(3);
                   cell4.setCellValue("EO");
                   cell4.setCellStyle(boldStyle);

                   Cell cell5 = rowhead .getCell(4);  
                   cell5 = rowhead .createCell(4);
                   cell5.setCellValue("ER");
                   cell5.setCellStyle(boldStyle);

                   Cell cell6 = rowhead .getCell(5);  
                   cell6 = rowhead .createCell(5);
                   cell6.setCellValue("KR");
                   cell6.setCellStyle(boldStyle);

                   Cell cell7 = rowhead .getCell(6);  
                   cell7 = rowhead .createCell(6);
                   cell7.setCellValue("NC");
                   cell7.setCellStyle(boldStyle);

                   Cell cell8 = rowhead .getCell(7);  
                   cell8 = rowhead .createCell(7);
                   cell8.setCellValue("NE");
                   cell8.setCellStyle(boldStyle);

                   Cell cell9 = rowhead .getCell(8);  
                   cell9 = rowhead .createCell(8);
                   cell9.setCellValue("NF");
                   cell9.setCellStyle(boldStyle);

                   Cell cell10 = rowhead .getCell(9);  
                   cell10 = rowhead .createCell(9);
                   cell10.setCellValue("NR");
                   cell10.setCellStyle(boldStyle);

                   Cell cell11 = rowhead .getCell(10);  
                   cell11 = rowhead .createCell(10);
                   cell11.setCellValue("NW");
                   cell11.setCellStyle(boldStyle);


                   Cell cell12 = rowhead .getCell(11);  
                   cell12 = rowhead .createCell(11);
                   cell12.setCellValue("SB");
                   cell12.setCellStyle(boldStyle);

                   Cell cell13 = rowhead .getCell(12);  
                   cell13 = rowhead .createCell(12);
                   cell13.setCellValue("SC");
                   cell13.setCellStyle(boldStyle);

                   Cell cell14 = rowhead .getCell(13);  
                   cell14 = rowhead .createCell(13);
                   cell14.setCellValue("SE");
                   cell14.setCellStyle(boldStyle);

                   Cell cell15= rowhead .getCell(14);  
                   cell15 = rowhead .createCell(14);
                   cell15.setCellValue("SR");
                   cell15.setCellStyle(boldStyle);

                  Cell cell16 = rowhead .getCell(15);  
                   cell16  = rowhead .createCell(15);
                   cell16 .setCellValue("SW");
                   cell16 .setCellStyle(boldStyle);


                   Cell cell17 = rowhead .getCell(16);  
                   cell17  = rowhead .createCell(16);
                   cell17.setCellValue("WC");
                   cell17.setCellStyle(boldStyle);


                   Cell cell18 = rowhead .getCell(17);  
                   cell18  = rowhead .createCell(17);
                   cell18.setCellValue("WR");
                   cell18.setCellStyle(boldStyle);

                   Cell cell19 = rowhead .getCell(18);  
                   cell19  = rowhead .createCell(18);
                   cell19.setCellValue("Retained Share");
                   cell19.setCellStyle(cs);
                   //cell19.setCellStyle(boldStyle);

                   Cell cell20 = rowhead .getCell(19);  
                   cell20  = rowhead .createCell(19);
                   cell20.setCellValue("Inward Share");
                   cell20.setCellStyle(cs);
                   //cell20.setCellStyle(boldStyle);

                   Cell cell21 = rowhead .getCell(20);  
                   cell21 = rowhead .createCell(20);
                   cell21.setCellValue("Total Apport. Earning");
                   cell21.setCellStyle(boldStyle);
                   cell21.setCellStyle(cs);
  
				  int index=6; int sno=0; String name="";
				  
				  int jj=1;
				 
				    while(jj<18) {
				    	 int colindex=1;
				    	
				    	 HSSFRow row = sheet.createRow((short)index); 
				    	// row.createCell((short) 0).setCellValue(dbvalue[jj-1][0]);
				    	 
				    	 Cell cellfirstcol = row .getCell(0);  
				    	 cellfirstcol = row .createCell(0);
				    	 cellfirstcol.setCellValue(dbvalue[jj-1][0]);
				    	 cellfirstcol.setCellStyle(boldStyle);
		                   //cell21.setCellStyle(cs);
		  
				    	 
				    	
				    	 
				    	 for(int kk =0; kk< 20 ; kk++)
				        	{
				    		 String vlaue=dbvalue_trans[jj][kk];
				    		 System.out.println("value is"+ vlaue);
				    		 row.createCell((short) colindex).setCellValue(vlaue);
				    		 colindex++;
				        	}
				    	 
				    	 jj++;
				    	 index++;
				    	
				    }
				    
				    System.out.println("J value is "+ jj);
				    
				    
				    HSSFRow rowus = sheet.createRow((short) 23);
				    for(int k3 =0; k3< 18 ; k3++)
			    	{
				    	Cell cellsumb = rowus .getCell(k3);  
				    	cellsumb = rowus .createCell(k3);
				    	cellsumb.setCellValue("");
						 //rowus.createCell((short)k3).setCellValue(""); 
						 
			    	}
				    /*rowus.createCell((short)18).setCellValue(sumr18); 
				    rowus.createCell((short)19).setCellValue(sumr19); 
				    rowus.createCell((short)20).setCellValue(sumr20); 
				*/
				    
				    Cell cellsum19 = rowus .getCell(18);  
				    cellsum19  = rowus .createCell(18);
				    cellsum19.setCellValue(""+sumr18);
				    cellsum19.setCellStyle(boldStyle);
				    
				    
				    Cell cellsum20 = rowus .getCell(19);  
				    cellsum20  = rowus .createCell(19);
				    cellsum20.setCellValue(""+sumr19);
				    cellsum20.setCellStyle(boldStyle);
				    
				    
				    Cell cellsumr20 = rowus .getCell(20);  
				    cellsumr20  = rowus .createCell(20);
				    cellsumr20.setCellValue(""+sumr20);
				    cellsumr20.setCellStyle(boldStyle);
				    
				 
				
				    
				    
				    
				    
				    
				  //Platform ROW
				    HSSFRow rowplateform = sheet.createRow((short) 24);
				   // rowplateform.createCell((short) 0).setCellValue("PlateForm");
				    Cell cellplateForm = rowplateform .getCell(0);  
				    cellplateForm  = rowplateform .createCell(0);
				    cellplateForm.setCellValue("Plateform");
				    cellplateForm.setCellStyle(boldStyle);
				    
				    int cindexplateform=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowplateform.createCell((short) cindexplateform).setCellValue(dbvalue_trans[18][k4]); 
			        	cindexplateform++;
			    	}
			        
			        
			        Cell cellplateFormtot = rowplateform .getCell(cindexplateform);  
			        cellplateFormtot  = rowplateform .createCell(cindexplateform);
			        cellplateFormtot.setCellValue(""+sumr23);
			        cellplateFormtot.setCellStyle(boldStyle);
			        
			        
			        //rowplateform.createCell((short) cindexplateform).setCellValue(sumr23);
			        

				    
				    //OGA ROW
				    HSSFRow rowoga = sheet.createRow((short) 25);
				    rowoga.createCell((short) 0).setCellValue("OGA");

			        Cell celloga = rowoga .getCell(0);  
			        celloga  = rowoga .createCell(0);
			        celloga.setCellValue("OGA");
			        celloga.setCellStyle(boldStyle);
				    
				    int cindex=1;
			        for(int k4 =0; k4< 19 ; k4++)
			    	{
			        	rowoga.createCell((short) cindex).setCellValue(dbvalue_trans[19][k4]); 
			        	cindex++;
			    	}
			        rowoga.createCell((short) cindex).setCellValue(sumr22);
			        

			        Cell cellogatot = rowoga .getCell(cindex);  
			        cellogatot  = rowoga .createCell(cindex);
			        cellogatot.setCellValue(sumr22);
			        cellogatot.setCellStyle(boldStyle);
			        
			        
			        
			        //
			        
			        HSSFRow rowretain = sheet.createRow((short) 26);
			        //rowretain.createCell((short) 0).setCellValue("Retained");
			        

			        Cell cellretain = rowretain .getCell(0);  
			        cellretain  = rowretain .createCell(0);
			        cellretain.setCellValue("Retained");
			        cellretain.setCellStyle(boldStyle);
			        
			        
				    int zz=1;
				    for(int k6 =0; k6< 19 ; k6++)
			    	{
				    	rowretain.createCell((short) zz).setCellValue(dbvalue_trans[20][k6]);
				    	 zz++;
			    	}
				    
				    //rowretain.createCell((short) zz--).setCellValue("");
				    //rowretain.createCell((short) zz++).setCellValue("");
				   // rowretain.createCell((short) zz).setCellValue(sumr18);
				    
				    Cell cellretaintot = rowretain .getCell(zz);  
				    cellretaintot  = rowretain .createCell(zz);
				    cellretaintot.setCellValue(sumr18);
				    cellretaintot.setCellStyle(boldStyle);
			        
				    
				    HSSFRow rowoutward = sheet.createRow((short) 27);
				    //rowoutward.createCell((short) 0).setCellValue("OUTWARD");
				    
				    

			        Cell celloutward = rowoutward .getCell(0);  
			        celloutward  = rowoutward .createCell(0);
			        celloutward.setCellValue("Outward");
			        celloutward.setCellStyle(boldStyle);
				    
				    
				    int tt=1;
				    for(int k =0; k< 19 ; k++)
			    	{
				    	rowoutward.createCell((short) tt).setCellValue(dbvalue_trans[21][k]);
				    	tt++;
				    	
			    	}
				    
				    //rowoutward.createCell((short) tt++).setCellValue("");
				   // rowoutward.createCell((short) tt++).setCellValue("");
				   // rowoutward.createCell((short) tt).setCellValue(sumr21);
				    
				    

			        Cell celloutwardtot = rowoutward .getCell(tt);  
			        celloutwardtot  = rowoutward .createCell(tt);
			        celloutwardtot.setCellValue(sumr21);
			        celloutwardtot.setCellStyle(boldStyle);
				    
				    
				    
				    HSSFRow rowinward = sheet.createRow((short) 28);
				  //  rowinward.createCell((short) 0).setCellValue("INWARD");
				    

			        Cell cellinward = rowinward .getCell(0);  
			        cellinward  = rowinward .createCell(0);
			        cellinward.setCellValue("Inward");
			        cellinward.setCellStyle(boldStyle);
				    
				    int ss=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowinward.createCell((short) ss).setCellValue(dbvalue_trans[k][18]);
				    	ss++;
			    	}
				    
				   rowinward.createCell((short) ss++).setCellValue("");
				    rowinward.createCell((short) ss++).setCellValue("");
				   // rowinward.createCell((short) ss++).setCellValue(sumr19);
			        

			        Cell cellinwardtot = rowinward .getCell(ss);  
			        cellinwardtot  = rowinward .createCell(ss);
			        cellinwardtot.setCellValue(sumr19);
			        cellinwardtot.setCellStyle(boldStyle);
				    
				    
				    
				    HSSFRow rowtot = sheet.createRow((short) 29);
				    //rowtot.createCell((short) 0).setCellValue("Total Apport Earnings");
				    //cell21.setCellStyle(cs);
				    

			        Cell celltotappor = rowtot .getCell(0);  
			        celltotappor  = rowtot .createCell(0);
			        celltotappor.setCellValue("Total Apport Earnings");
			        celltotappor.setCellStyle(cs);
			        //celltotappor.setCellStyle(boldStyle);
				    
				    
				    int uu=1;
				    for(int k =1; k< 18 ; k++)
			    	{
				    	rowtot.createCell((short) uu).setCellValue(dbvalue_trans[k][19]);
				    	uu++;
			    	}
				    rowtot.createCell((short) uu++).setCellValue("");
				    rowtot.createCell((short) uu++).setCellValue("");
				    //rowtot.createCell((short) uu++).setCellValue(sumr20);
				    
				    

			        Cell celltotapportot = rowtot .getCell(uu);  
			        celltotapportot  = rowtot .createCell(uu);
			        celltotapportot.setCellValue(sumr20);
			        celltotapportot.setCellStyle(boldStyle);
			        
			        HSSFRow rowfooter = sheet.createRow((short) 31);
				       
			        Cell cellfooter = rowfooter.getCell(3);  
			        cellfooter = rowfooter.createCell(3);    
			        cellfooter.setCellValue("     Note: Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data         ");
			      //  cellfooter.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 31, 31 , 3 , 19));
		            
		            HSSFRow rowfooter2 = sheet.createRow((short) 32);  
			        Cell cellfooter2 = rowfooter2.getCell(3);  
			        cellfooter2 = rowfooter2.createCell(3);    
			        cellfooter2.setCellValue("                   Outward Share : Earnings distributed to other Railways        ");
			       // cellfooter2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 32, 32 , 3 , 19));
		            
		            HSSFRow rowfooter3 = sheet.createRow((short) 33);  
			        Cell cellfooter3 = rowfooter3.getCell(3);  
			        cellfooter3 = rowfooter3.createCell(3);    
			        cellfooter3.setCellValue("     Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic)         ");
			       // cellfooter3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 33, 33 , 3 , 19));
		            
		            
		            
		            HSSFRow rowfooter4 = sheet.createRow((short) 34);  
			        Cell cellfooter4 = rowfooter4.getCell(3);  
			        cellfooter4 = rowfooter4.createCell(3);    
			        cellfooter4.setCellValue("                        Inward Share   : Earnings received from all Railways       ");
			       // cellfooter4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 34, 34 , 3 , 19));
		            
				    
		            
		            HSSFRow rowfooter5 = sheet.createRow((short) 35);  
			        Cell cellfooter5 = rowfooter5.getCell(3);  
			        cellfooter5 = rowfooter5.createCell(3);    
			        cellfooter5.setCellValue("          Total Apportionment Earnings : Retained Share + Inward Share ");
			        //cellfooter5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 35, 35 , 3 , 19));
		            
				    
		            
		            
		            HSSFRow rowfooter6 = sheet.createRow((short) 36);  
			        Cell cellfooter6 = rowfooter6.getCell(2);  
			        cellfooter6 = rowfooter6.createCell(2);    
			        cellfooter6.setCellValue(" Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings. ");
			        cellfooter6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 36 , 36 , 2 , 19));
		            
		            
		            HSSFRow rowfooter7 = sheet.createRow((short) 37);  
			        Cell cellfooter7 = rowfooter7.getCell(2);  
			        cellfooter7 = rowfooter7.createCell(2);    
			        cellfooter7.setCellValue(" Note 2- MUTP,MRTS,MMTS,CIDCO charges, Reservation slip, superfast surcharge slip charges are included in the apportioned share of orginating railway(Home Railway). ");
			        cellfooter7.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 37 , 37 , 2 , 19));
			        
		            
		          
			        
			        OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
			
	}
	
	/*public void  prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		ArrayList<Integer> retainevalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> inwardvalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> totalvalue =new 	ArrayList<Integer>() ;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  //String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
			  Statement stmt = con.createStatement();
		  
			//Statement stmt = con.createStatement();
			
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

			  ResultSet rs = stmt.executeQuery(Query);	
			    HSSFWorkbook hwb = new HSSFWorkbook(); 
			    HSSFSheet sheet =hwb.createSheet("new sheet"); 
				HSSFRow rowhead1 = sheet.createRow((short) 1);
				HSSFCellStyle style = hwb.createCellStyle();
				HSSFFont font = hwb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setFontHeightInPoints((short)10);
				font.setBoldweight((short) 10);//.setBold(true);
				style.setFont(font);
				rowhead1.createCell((short) 3).setCellValue("SUMMARY OF");//APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS            By CRIS"
				sheet.autoSizeColumn((short) 3);
				rowhead1.createCell((short) 4).setCellValue("APPORTIONMENT");
				sheet.autoSizeColumn((short) 4);
				rowhead1.createCell((short) 5).setCellValue("OF CATERING");
				sheet.autoSizeColumn((short) 5);
				rowhead1.createCell((short) 6).setCellValue("CHARGES");
				sheet.autoSizeColumn((short) 6);
				rowhead1.createCell((short) 7).setCellValue("FOR ALL");
				rowhead1.createCell((short) 8).setCellValue("INDIAN");
				rowhead1.createCell((short) 9).setCellValue("RAILWAYS");
				sheet.autoSizeColumn((short) 9);
				rowhead1.createCell((short) 10).setCellValue("PRS");
				rowhead1.createCell((short) 11).setCellValue("");
				rowhead1.createCell((short) 12).setCellValue("");
				rowhead1.createCell((short) 13).setCellValue("");
				rowhead1.createCell((short) 14).setCellValue("");
				rowhead1.createCell((short) 15).setCellValue("");
				rowhead1.createCell((short) 16).setCellValue("");
				rowhead1.createCell((short) 17).setCellValue("");
				rowhead1.createCell((short) 18).setCellValue("");
				rowhead1.createCell((short) 19).setCellValue("BY");
				rowhead1.createCell((short) 20).setCellValue("CRIS");
				//rowhead1.createCell((short) 20).setCellValue(modifiedDate);
				HSSFRow rowhead11 =sheet.createRow((short)2); 
				 rowhead11.createCell((short) 8).setCellValue(d);
				 rowhead11.createCell((short) 9).setCellValue(e);
				 
				 rowhead11.createCell((short) 19).setCellValue("Date");
				 
				 rowhead11.createCell((short) 20).setCellValue(modifiedDate);
				 HSSFRow rowheadfig =sheet.createRow((short)3); 
				 rowheadfig.createCell((short) 19).setCellValue("Figures in");
				 rowheadfig.createCell((short) 20).setCellValue("Units");
				  HSSFRow rowhead =sheet.createRow((short)4); 
				  rowhead.createCell((short) 0).setCellValue("ZONE"); 
				  rowhead.createCell((short)1).setCellValue("CR"); 
				  rowhead.createCell((short)2).setCellValue("EC");
				  rowhead.createCell((short)3).setCellValue("EO"); 
				  rowhead.createCell((short)4).setCellValue("ER");
				  rowhead.createCell((short) 5).setCellValue("KR"); 
				  rowhead.createCell((short)6).setCellValue("NC"); 
				  rowhead.createCell((short)7).setCellValue("NE");
				  rowhead.createCell((short)8).setCellValue("NF"); 
				  rowhead.createCell((short)9).setCellValue("NR");
				  rowhead.createCell((short)10).setCellValue("NW"); 
				  rowhead.createCell((short)11).setCellValue("SB");
				  rowhead.createCell((short)12).setCellValue("SC"); 
				  rowhead.createCell((short)13).setCellValue("SE");
				  rowhead.createCell((short) 14).setCellValue("SR"); 
				  rowhead.createCell((short)15).setCellValue("SW"); 
				  rowhead.createCell((short)16).setCellValue("WC");
				  rowhead.createCell((short)17).setCellValue("WR"); 
				  rowhead.createCell((short)18).setCellValue("Retained Share");
				  rowhead.createCell((short)19).setCellValue("Inward Share");
				  rowhead.createCell((short)20).setCellValue("Total Apport. Earning");
				  for(int index=0 ; index<=20 ;index++){
					  rowhead.getCell((short) index).setCellStyle((HSSFCellStyle) style);
					  
				  }
				  int index=6; int sno=0; String name="";
				  while(rs.next()) {
				
					  HSSFRow row = sheet.createRow((short)index); 
					  row.createCell((short)0).setCellValue(rs.getString("OWNRLY")); 
					  row.createCell((short) 1).setCellValue(rs.getString("CR"));
					  //System.out.println("train no is"+rs.getString("EC")); 
					  row.createCell((short)2).setCellValue(rs.getString("EC"));
					  row.createCell((short)3).setCellValue(rs.getString("EO"));
					  row.createCell((short)4).setCellValue(rs.getString("ER"));
					  row.createCell((short)5).setCellValue(rs.getString("KR"));
					  row.createCell((short)6).setCellValue(rs.getString("NC"));
					  row.createCell((short)7).setCellValue(rs.getString("NE"));
					  
					  row.createCell((short)8).setCellValue(rs.getString("NF"));
					  row.createCell((short)9).setCellValue(rs.getString("NR"));
					  row.createCell((short)10).setCellValue(rs.getString("NW"));
					  
					  row.createCell((short)11).setCellValue(rs.getString("SB"));
					  row.createCell((short)12).setCellValue(rs.getString("SC"));
					  row.createCell((short)13).setCellValue(rs.getString("SE"));
					  
					  row.createCell((short)14).setCellValue(rs.getString("SR"));
					  row.createCell((short)15).setCellValue(rs.getString("SW"));
					  row.createCell((short)16).setCellValue(rs.getString("WC"));
					  
					  row.createCell((short)17).setCellValue(rs.getString("WR"));
					  row.createCell((short)18).setCellValue(rs.getString("RETAINED"));
					  int a1=Integer.parseInt(rs.getString("RETAINED"));
					  retainevalue.add(i, a1);
					  row.createCell((short)19).setCellValue(rs.getString("INWARD"));
					  int b1=Integer.parseInt(rs.getString("INWARD"));
					  inwardvalue.add(i, b1);
					  row.createCell((short)20).setCellValue(rs.getString("TOTAL"));
					  int c1=Integer.parseInt(rs.getString("TOTAL"));
					  totalvalue.add(i, c1);
					  
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
			             index++ ;
			             i++;
					  
					  
					  }
				  
				  HSSFRow rowus = sheet.createRow((short) index+1);
				  rowus.createCell((short)0).setCellValue("Unmatched Share"); 
				  rowus.createCell((short) 1).setCellValue("0");
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowus.createCell((short)2).setCellValue("0");
				  rowus.createCell((short)3).setCellValue("0");
				  rowus.createCell((short)4).setCellValue("0");
				  rowus.createCell((short)5).setCellValue("0");
				  rowus.createCell((short)6).setCellValue("0");
				  rowus.createCell((short)7).setCellValue("0");
				  
				  rowus.createCell((short)8).setCellValue("0");
				  rowus.createCell((short)9).setCellValue("0");
				  rowus.createCell((short)10).setCellValue("0");
				  
				  rowus.createCell((short)11).setCellValue("0");
				  rowus.createCell((short)12).setCellValue("0");
				  rowus.createCell((short)13).setCellValue("0");
				  
				  rowus.createCell((short)14).setCellValue("0");
				  rowus.createCell((short)15).setCellValue("0");
				  rowus.createCell((short)16).setCellValue("0");
				  
				  rowus.createCell((short)17).setCellValue("0");
				  rowus.createCell((short)18).setCellValue(sumr18);
				  rowus.createCell((short)19).setCellValue(sumr19);
				  rowus.createCell((short)20).setCellValue(sumr20);
				  
				  
				  HSSFRow rowoa = sheet.createRow((short) index+2);
				  rowoa.createCell((short)0).setCellValue("Orignating Amount"); 
				  rowoa.createCell((short) 1).setCellValue(sumr1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowoa.createCell((short)2).setCellValue(sumr2);
				  rowoa.createCell((short)3).setCellValue(sumr3);
				  rowoa.createCell((short)4).setCellValue(sumr4);
				  rowoa.createCell((short)5).setCellValue(sumr5);
				  rowoa.createCell((short)6).setCellValue(sumr6);
				  rowoa.createCell((short)7).setCellValue(sumr7);
				  
				  rowoa.createCell((short)8).setCellValue(sumr8);
				  rowoa.createCell((short)9).setCellValue(sumr9);
				  rowoa.createCell((short)10).setCellValue(sumr10);
				  
				  rowoa.createCell((short)11).setCellValue(sumr11);
				  rowoa.createCell((short)12).setCellValue(sumr12);
				  rowoa.createCell((short)13).setCellValue(sumr13);
				  
				  rowoa.createCell((short)14).setCellValue(sumr14);
				  rowoa.createCell((short)15).setCellValue(sumr15);
				  rowoa.createCell((short)16).setCellValue(sumr16);
				  
				  rowoa.createCell((short)17).setCellValue(sumr17);
				  rowoa.createCell((short)18).setCellValue("");
				  rowoa.createCell((short)19).setCellValue("");
				  rowoa.createCell((short)20).setCellValue(sumr20);
				  
				  HSSFRow rowos = sheet.createRow((short) index+3);//retainevalue
				  long col1= sumr1-retainevalue.get(0);
				  long col2= sumr2-retainevalue.get(1);
				  long col3= sumr3-retainevalue.get(2);
				  long col4= sumr4-retainevalue.get(3);
				  long col5= sumr5-retainevalue.get(4);
				  long col6= sumr6-retainevalue.get(5);
				  long col7= sumr7-retainevalue.get(6);
				  long col8= sumr8-retainevalue.get(7);
				  long col9= sumr9-retainevalue.get(8);
				  long col10= sumr10-retainevalue.get(9);
				  long col11= sumr11-retainevalue.get(10);
				  long col12= sumr12-retainevalue.get(11);
				  long col13= sumr13-retainevalue.get(12);
				  long col14= sumr14-retainevalue.get(13);
				  long col15= sumr15-retainevalue.get(14);
				  long col16= sumr16-retainevalue.get(15);
				  long col17= sumr17-retainevalue.get(16);
				  long col18= sumr1-retainevalue.get(17);
				  long col19= sumr1-retainevalue.get(18);
				  long col20= sumr1-retainevalue.get(19);
				  System.out.println( col1 +""+col2+""+col3+""+col4+"");
				  long sum = col1+col2+col3+col4+col5+col6+col7+col8+col9+col10+col11+col12+col13+col14+col15+col16+col17;
				  
				  rowos.createCell((short)0).setCellValue("Outward Share"); 
				  rowos.createCell((short) 1).setCellValue(col1);
				  //System.out.println("train no is"+rs.getString("EC")); 
				  rowos.createCell((short)2).setCellValue(col2);
				  rowos.createCell((short)3).setCellValue(col3);
				  rowos.createCell((short)4).setCellValue(col4);
				  rowos.createCell((short)5).setCellValue(col5);
				  rowos.createCell((short)6).setCellValue(col6);
				  rowos.createCell((short)7).setCellValue(col7);
				  
				  rowos.createCell((short)8).setCellValue(col8);
				  rowos.createCell((short)9).setCellValue(col9);
				  rowos.createCell((short)10).setCellValue(col10);
				  
				  rowos.createCell((short)11).setCellValue(col11);
				  rowos.createCell((short)12).setCellValue(col12);
				  rowos.createCell((short)13).setCellValue(col13);
				  
				  rowos.createCell((short)14).setCellValue(col14);
				  rowos.createCell((short)15).setCellValue(col15);
				  rowos.createCell((short)16).setCellValue(col16);
				  
				  rowos.createCell((short)17).setCellValue(col17);
				  rowos.createCell((short)18).setCellValue("");
				  rowos.createCell((short)19).setCellValue("");
				  rowos.createCell((short)20).setCellValue(sum);
				  
				  
				 //retaine row 
				  
				  HSSFRow rowretain = sheet.createRow((short) index+4);
				  rowretain.createCell((short)0).setCellValue("Retained"); 
				  long sumofretain=0;
				  int k0=1;
				  for(int j=0 ;j<retainevalue.size(); j++){
					  
					  int aa=retainevalue.get(j);
					  
					  rowretain.createCell((short)k0).setCellValue(aa);
					  ++k0;
					  sumofretain=sumofretain+aa;
					  
				  }
				  rowretain.createCell((short) 18).setCellValue(""); 
				  rowretain.createCell((short) 19).setCellValue(""); 
				  rowretain.createCell((short)  20).setCellValue(sumofretain); 
				  
				  //INwARD ROW
				  
				  
				  HSSFRow rowinward = sheet.createRow((short) index+5);
				  rowinward.createCell((short)0).setCellValue("Inward"); 
				  long suminwrad=0;
				  int k=1;
				  for(int j=0 ;j<inwardvalue.size(); j++){
					  
					  int aa=inwardvalue.get(j);
					  
					  rowinward.createCell((short) k).setCellValue(aa);
					  k++;
					  suminwrad=suminwrad+aa;
					  
				  }
				  rowinward.createCell((short) 18).setCellValue(""); 
				  rowinward.createCell((short) 19).setCellValue(""); 
				  rowinward.createCell((short) 20).setCellValue(suminwrad); 
				  
				  
				  //Total Apport. Earnings
				  HSSFRow rowtotal = sheet.createRow((short) index+6);
				  rowtotal.createCell((short)0).setCellValue("Total Apport. Earnings"); 
				  long sumtot=0;
				  int k3=1;
				  for(int j=0 ;j<totalvalue.size(); j++){
					 
					  int aa=totalvalue.get(j);
					  
					  rowtotal.createCell((short)k3).setCellValue(aa);
					  
					 sumtot=sumtot+aa;
					  k3++;
					  
				  }
				  rowtotal.createCell((short) 18).setCellValue(""); 
				  rowtotal.createCell((short) 19).setCellValue(""); 
				  rowtotal.createCell((short) 20).setCellValue(sumtot); 
				  
				  
				  
				  OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
				  
				  
		  
	
	}*/
    
	public void  prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		
		ArrayList<Integer> retainevalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> inwardvalue =new 	ArrayList<Integer>() ;
		ArrayList<Integer> totalvalue =new 	ArrayList<Integer>() ;
		int i =0;
	    
        HttpServletResponse response = ServletActionContext.getResponse();
		 tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  //String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
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
		    
		    
		    
		    
		    HSSFWorkbook hwb = new HSSFWorkbook(); 
		    HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
		    HSSFSheet sheet =hwb.createSheet("new sheet"); 
		    HSSFCellStyle boldStyle = hwb.createCellStyle();
	        boldStyle.setFont(boldFont);
		    
		    HSSFRow rowzone=sheet.createRow(0); 
	        rowzone.createCell((short) 4).setCellValue(zonename);
            HSSFRow rowhead1 = sheet.createRow((short) 1);
	       
            Cell cell = rowhead1.getCell(0);  
            cell = rowhead1.createCell(0);    
            cell.setCellValue("                                                         SUMMARY OF APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS                                        By CRIS");
            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 0 , 20));
            
            HSSFRow rowhead2 = sheet.createRow((short) 3);
		       
	        Cell cel2 = rowhead2.getCell(0);  
            cel2 = rowhead2.createCell(0);    
            cel2.setCellValue("                                                                                "+d+"-"+e+"                                                                                                                                 Date-"+ modifiedDate);
            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 0 , 20));
           
            HSSFRow rowheadfig =sheet.createRow((short) 4); 
			
			   Cell celf = rowheadfig.getCell(14);  
			   celf = rowheadfig.createCell(14);    
			   celf.setCellValue(" Figures in Units");
			   celf.setCellStyle(boldStyle);
	           sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 4 , 4, 14 , 15));
	           
	           HSSFRow rowhead =sheet.createRow((short)5);
               Cell cell1 = rowhead .getCell(0);  
               cell1 = rowhead .createCell(0);
               cell1.setCellValue("Zone");
               cell1.setCellStyle(boldStyle);
                  
               Cell cell2 = rowhead .getCell(1);  
               cell2 = rowhead .createCell(1);
               cell2.setCellValue("CR");
               cell2.setCellStyle(boldStyle);


               Cell cell3 = rowhead .getCell(2);  
               cell3 = rowhead .createCell(2);
               cell3.setCellValue("EC");
               cell3.setCellStyle(boldStyle);

               Cell cell4 = rowhead .getCell(3);  
               cell4 = rowhead .createCell(3);
               cell4.setCellValue("EO");
               cell4.setCellStyle(boldStyle);

               Cell cell5 = rowhead .getCell(4);  
               cell5 = rowhead .createCell(4);
               cell5.setCellValue("ER");
               cell5.setCellStyle(boldStyle);

               Cell cell6 = rowhead .getCell(5);  
               cell6 = rowhead .createCell(5);
               cell6.setCellValue("KR");
               cell6.setCellStyle(boldStyle);

               Cell cell7 = rowhead .getCell(6);  
               cell7 = rowhead .createCell(6);
               cell7.setCellValue("NC");
               cell7.setCellStyle(boldStyle);

               Cell cell8 = rowhead .getCell(7);  
               cell8 = rowhead .createCell(7);
               cell8.setCellValue("NE");
               cell8.setCellStyle(boldStyle);

               Cell cell9 = rowhead .getCell(8);  
               cell9 = rowhead .createCell(8);
               cell9.setCellValue("NF");
               cell9.setCellStyle(boldStyle);

               Cell cell10 = rowhead .getCell(9);  
               cell10 = rowhead .createCell(9);
               cell10.setCellValue("NR");
               cell10.setCellStyle(boldStyle);

               Cell cell11 = rowhead .getCell(10);  
               cell11 = rowhead .createCell(10);
               cell11.setCellValue("NW");
               cell11.setCellStyle(boldStyle);


               Cell cell12 = rowhead .getCell(11);  
               cell12 = rowhead .createCell(11);
               cell12.setCellValue("SB");
               cell12.setCellStyle(boldStyle);

               Cell cell13 = rowhead .getCell(12);  
               cell13 = rowhead .createCell(12);
               cell13.setCellValue("SC");
               cell13.setCellStyle(boldStyle);

               Cell cell14 = rowhead .getCell(13);  
               cell14 = rowhead .createCell(13);
               cell14.setCellValue("SE");
               cell14.setCellStyle(boldStyle);

               Cell cell15= rowhead .getCell(14);  
               cell15 = rowhead .createCell(14);
               cell15.setCellValue("SR");
               cell15.setCellStyle(boldStyle);

              Cell cell16 = rowhead .getCell(15);  
               cell16  = rowhead .createCell(15);
               cell16 .setCellValue("SW");
               cell16 .setCellStyle(boldStyle);


               Cell cell17 = rowhead .getCell(16);  
               cell17  = rowhead .createCell(16);
               cell17.setCellValue("WC");
               cell17.setCellStyle(boldStyle);


               Cell cell18 = rowhead .getCell(17);  
               cell18  = rowhead .createCell(17);
               cell18.setCellValue("WR");
               cell18.setCellStyle(boldStyle);

               Cell cell19 = rowhead .getCell(18);  
               cell19  = rowhead .createCell(18);
               cell19.setCellValue("Retained Share");
               cell19.setCellStyle(cs);
               //cell19.setCellStyle(boldStyle);

               Cell cell20 = rowhead .getCell(19);  
               cell20  = rowhead .createCell(19);
               cell20.setCellValue("Inward Share");
               cell20.setCellStyle(cs);
               //cell20.setCellStyle(boldStyle);

               Cell cell21 = rowhead .getCell(20);  
               cell21 = rowhead .createCell(20);
               cell21.setCellValue("Total Apport. Earning");
               cell21.setCellStyle(boldStyle);
               cell21.setCellStyle(cs);

		    
				  int index=6; int sno=0; String name="";
				  
				  for(int rowno=0 ;rowno<23 ;rowno++){
					
						  HSSFRow row = sheet.createRow((short)index); 
						  
						  for(int col=0 ;col<21 ;col++){
						  Cell celldata0 = row .getCell(col);  
						  celldata0 = row .createCell(col);
			              celldata0.setCellValue(dbvalue[rowno][col]);
			              if(col==0)
			              {
			              celldata0.setCellStyle(cs);
			              }
			          
			              
			              if(col==20 && rowno==17)
			              {
			              celldata0.setCellStyle(cs);
			              }
			              if(col==19 && rowno==17)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			              if(col==18 && rowno==17)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			              
			              if(col==20 && rowno==18)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			             
			              if(col==20 && rowno==19)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			              if(col==20 && rowno==20)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			              if(col==20 && rowno==21)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
			              if(col==20 && rowno==22)
			              {
			              celldata0.setCellStyle(boldStyle);
			              }
						  }
						 index++;

				  }
				  
				  
				  OutputStream outObject = response.getOutputStream();
					 response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
					//write this workbook to an Outputstream.
					hwb.write(outObject);
					outObject.flush();
					outObject.close();
					con.close();
					
				  
				  
		  
	
	}
	
	public void exportprsstationnotintrainroute(String a, String b , String c, String d , String e ) throws Exception{
		  
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);  
		
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  zonename=fullnameofzone(a);
		 // String filename = "/ARMSFILES/sheet/"+a+" "+b+" "+d+" "+e+".xls";
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
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
		  
		  System.out.println("strQuery >>>"+strQuery);
		  
		  ResultSet rs = stmt.executeQuery(strQuery);	
		    HSSFWorkbook hwb = new HSSFWorkbook(); 
		    
		    
		    

			HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
			
			
			
		        HSSFSheet sheet = hwb.createSheet("new sheet");
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
		        HSSFRow rowzone=sheet.createRow(0); 
		       // rowzone.createCell((short) 4).setCellValue(zonename);
		        Cell cel2 = rowzone.getCell(0);  
	            cel2 = rowzone.createCell(0);    
	            cel2.setCellValue("                                                                          "+zonename+"                                                                                                                        ");
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 0 , 10));
	           
	         
		        
		        
		        
		        HSSFRow rowhead1 = sheet.createRow((short) 1);
		       
		        Cell cell = rowhead1.getCell(0);  
	            cell = rowhead1.createCell(0);    
	            cell.setCellValue("                                               PRS List of Stations not lying inTrain Route ");
	            cell.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 0 , 10));
	            
	           
		



		           HSSFRow rowhead2 = sheet.createRow((short) 3);
			       
			       
		            
		    
		
			
			HSSFRow rowheadthree = sheet.createRow((short) 3);
			
			 Cell cel3= rowheadthree.getCell(0);  
			 cel3 = rowheadthree.createCell(0);    
			 cel3.setCellValue("                                                     "+d+"-"+e+"                                                                                             Date-"+ modifiedDate);
			 cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	         sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 0 , 10));
	           
			
			
			
			
		  HSSFRow rowhead =sheet.createRow((short) 4); 
		  
		  Cell cell1 = rowhead .getCell(0);  
          cell1 = rowhead .createCell(0);
          cell1.setCellValue("Train Number");
          cell1.setCellStyle(boldStyle);
          cell1.setCellStyle(cs);
             
          Cell cell2 = rowhead .getCell(1);  
          cell2 = rowhead .createCell(1);
          cell2.setCellValue("From Station");
          cell2.setCellStyle(boldStyle);
          sheet.autoSizeColumn(1);
          


		  Cell cell3 = rowhead .getCell(2);  
		  cell3 = rowhead .createCell(2);
		  cell3.setCellValue("From Station Code");
		  cell3.setCellStyle(boldStyle);
		  sheet.autoSizeColumn(2);
          
             
          Cell cell4 = rowhead .getCell(3);  
          cell4 = rowhead .createCell(3);
          cell4.setCellValue("To Station");
          cell4.setCellStyle(boldStyle);
          sheet.autoSizeColumn(3);
          
          
          
          
		  Cell cell5 = rowhead .getCell(4);  
		  cell5 = rowhead .createCell(4);
		  cell5.setCellValue("To Station Code");
		  cell5.setCellStyle(boldStyle);
		  sheet.autoSizeColumn(4);
          
             
          Cell cell6 = rowhead .getCell(5);  
          cell6 = rowhead .createCell(5);
          cell6.setCellValue("Enroute Station");
          cell6.setCellStyle(boldStyle);
          sheet.autoSizeColumn(5);
          


		  Cell cell7 = rowhead .getCell(6);  
		  cell7 = rowhead .createCell(6);
		  cell7.setCellValue("Distance");
		  cell7.setCellStyle(boldStyle);
		  sheet.autoSizeColumn(6);
          
             
          Cell cell8 = rowhead .getCell(7);  
          cell8 = rowhead .createCell(7);
          cell8.setCellValue("Fare");
          cell8.setCellStyle(boldStyle);
          sheet.autoSizeColumn(7);
          
		 
		  int index=5; int sno=0; String name="";
		  //System.out.println("traisdffffffffffffffffn no is"+rs.getString(1));
		  while(rs.next()) {
		  System.out.println("inside while is"+rs.getString(1)); 
		  sno++;
		  
		  HSSFRow row = sheet.createRow((short)index); 
		  row.createCell((short)0).setCellValue(rs.getString("trainno")); 
		  row.createCell((short) 1).setCellValue(rs.getString("sstn"));
		  //System.out.println("train no is"+rs.getString(1)); 
		  row.createCell((short)2).setCellValue(rs.getString("sstn9"));
		  row.createCell((short)3).setCellValue(rs.getString("dstn"));
		  row.createCell((short)4).setCellValue(rs.getString("destn9"));
		  row.createCell((short)5).setCellValue(rs.getString("entstn"));
		  row.createCell((short)6).setCellValue(rs.getString("dist"));
		  row.createCell((short)7).setCellValue(rs.getString("fare"));
		  sumr1=sumr1+Long.parseLong(rs.getString("fare"));
		  //System.out.println("catering no is"+rs.getString(2)); index++; }
		  
		  
		 /* FileOutputStream fileOut = new FileOutputStream(filename);
		  hwb.write(fileOut); fileOut.close();*/
	
		index++;
		  
		  }

		  HSSFRow rowtot = sheet.createRow((short)index);
		  
		  
		  Cell celltot = rowtot .getCell(6);  
		  celltot = rowtot .createCell(6);
		  celltot.setCellValue("Total Fare");
		  celltot.setCellStyle(boldStyle);
             
		  

		  Cell celltotval = rowtot .getCell(7);  
		  celltotval = rowtot .createCell(7);
		  celltotval.setCellValue(sumr1);
		  celltotval.setCellStyle(boldStyle);
		  
		  
		 /* rowtot.createCell((short)6).setCellValue("Total Fare"); 
		  rowtot.createCell((short) 7).setCellValue(sumr1);
		*/
		  /*FileOutputStream fileOut = new FileOutputStream(filename);
		  hwb.write(fileOut); fileOut.close();
		  
		  
		  downloadFile(filename,b);*/
		  
		  OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
	}
	
	public void prsmatchunmatch(String a, String b , String c, String d , String e ) throws Exception{
		
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		  
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;	  
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
		
		  
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		    
		    
		    

			HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setBorderRight((short)4);
			 cs.setFont(boldFont);
			
			
			
		        HSSFSheet sheet = hwb.createSheet("new sheet");
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
		        HSSFRow rowzone=sheet.createRow((short) 0);
		       // rowzone.createCell((short) 4).setCellValue(zonename);
		        Cell cel2 = rowzone.getCell(0);  
	            cel2 = rowzone.createCell(0);    
	            cel2.setCellValue("                                                                STATISTICS  OF PASSENGER APPORTIONMENT FOR ALL INDIAN  RAILWAYS (PRS)                 			              BY CRIS");
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 12));
	           

	            HSSFRow rowhead22 =sheet.createRow((short) 1);
				
				  Cell cel22 = rowhead22.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel22 = rowhead22.createCell(12);    
				  cel22.setCellValue("   Figure in units ");
		    
			HSSFRow rowhead3 =sheet.createRow((short) 2);
			
			  Cell cel3 = rowhead3.getCell(0);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  cel3 = rowhead3.createCell(0);    
			  cel3.setCellValue("                                                                                                              For the Month "+d+"-"+e             );
			 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 0 , 9));
	          
			  Cell cel31 = rowhead3.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
			  cel31 = rowhead3.createCell(12);    
			  cel31.setCellValue(  "Date - "+modifiedDate);
			 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 12 , 13));
	          
	          

				 HSSFRow rowhead2 =sheet.createRow((short) 3);
				 Cell cel4 = rowhead2.getCell(1);  
				 cel4 = rowhead2.createCell(1);    
				 cel4.setCellValue("                  INPUT RECORDS           ");
				 cel4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		          sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 1 , 4));
		          
			
		          
		          Cell cel5 = rowhead2.getCell(5);  
		          cel5 = rowhead2.createCell(5);    
		          cel5.setCellValue("                  MATCH RECORDS          ");
		          cel5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 5, 8));
			          
			      
			      
			      
			      Cell cel6 = rowhead2.getCell(9);  
			      cel6 = rowhead2.createCell(9);    
			      cel6.setCellValue("               UNMATCH RECORDS          ");
			      cel6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 ,9, 12));
			          
		
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  
		  Cell celcol0 = rowhead.getCell(0);  
		  celcol0 = rowhead.createCell(0);    
		  celcol0.setCellValue(" RLY  ");
		  celcol0.setCellStyle(boldStyle);
		  
		  

		  Cell celcol1 = rowhead.getCell(1);  
		  celcol1 = rowhead.createCell(1);    
		  celcol1.setCellValue(" Records  ");
		  celcol1.setCellStyle(boldStyle);
		  sheet.autoSizeColumn(1);
		  
		  

		  Cell celcol2 = rowhead.getCell(2);  
		  celcol2 = rowhead.createCell(2);    
		  celcol2.setCellValue(" Total Fare Excl. catchg  ");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol2.setCellStyle(cs);
	     
		  
		  
		  

		  Cell celcol3 = rowhead.getCell(3);  
		  celcol3 = rowhead.createCell(3);    
		  celcol3.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol3.setCellStyle(cs);
		  sheet.autoSizeColumn(3);
		  
		  
		  

		  Cell celcol4 = rowhead.getCell(4);  
		  celcol4 = rowhead.createCell(4);    
		  celcol4.setCellValue("Catering Chg");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol4.setCellStyle(cs);
		  sheet.autoSizeColumn(4);
		  

		  Cell celcol5 = rowhead.getCell(5);  
		  celcol5 = rowhead.createCell(5);    
		  celcol5.setCellValue("Records");
		  celcol5.setCellStyle(boldStyle);
		 // celcol4.setCellStyle(cs);
		  sheet.autoSizeColumn(5);
		  
		  
		  
		  Cell celcol6 = rowhead.getCell(6);  
		  celcol6 = rowhead.createCell(6);    
		  celcol6.setCellValue("Total Fare Excl. catchg");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol6.setCellStyle(cs);
		  
		  
		  
		  
		  
		  Cell celcol7 = rowhead.getCell(7);  
		  celcol7 = rowhead.createCell(7);    
		  celcol7.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol7.setCellStyle(cs);
		  sheet.autoSizeColumn(7);
		  
		  
		  

		  Cell celcol8 = rowhead.getCell(8);  
		  celcol8 = rowhead.createCell(8);    
		  celcol8.setCellValue("Catering Chg");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol8.setCellStyle(cs);
		  sheet.autoSizeColumn(8);
		  
		  
		  
		  Cell celcol9 = rowhead.getCell(9);  
		  celcol9 = rowhead.createCell(9);    
		  celcol9.setCellValue("Records");
		  celcol9.setCellStyle(boldStyle);
		 // celcol4.setCellStyle(cs);
		  sheet.autoSizeColumn(9);
		  
		  
		  
		  Cell celcol10 = rowhead.getCell(10);  
		  celcol10 = rowhead.createCell(10);    
		  celcol10.setCellValue("Total Fare Excl. catchg");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol10.setCellStyle(cs);
		 
		  
		  
		  
		  Cell celcol11 = rowhead.getCell(11);  
		  celcol11 = rowhead.createCell(11);    
		  celcol11.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol11.setCellStyle(cs);
		  sheet.autoSizeColumn(11);
		  
		  
		  

		  Cell celcol12 = rowhead.getCell(12);  
		  celcol12 = rowhead.createCell(12);    
		  celcol12.setCellValue("Catering Chg");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol12.setCellStyle(cs);
		  sheet.autoSizeColumn(12);
		  
		  int index=7; int sno=0; String name="";
		  
			
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				//row.createCell((short) 0).setCellValue(rs.getString("rly"));
				

				  Cell celcolzone = row.getCell(0);  
				  celcolzone = row.createCell(0);    
				  celcolzone.setCellValue(rs.getString("rly"));
				  celcolzone.setCellStyle(boldStyle);
				
				
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				/*row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));*/
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_OTHERCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
			/*	row.createCell((short) 8).setCellValue(rs.getString("MATCH_SAFETYCHG"));*/
				row.createCell((short) 7).setCellValue(rs.getString("MATCH_OTHERCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				/*row.createCell((short) 13).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));*/
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				/*  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));*/
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  /*sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));*/
				  sumr9=sumr9+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				 /* sumr13=sumr13+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));*/
				  sumr14=sumr14+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
 				index++;

			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
		/*	row.createCell((short) 0).setCellValue("Total");
			//row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 1).setCellValue(sumr1);
			row.createCell((short) 2).setCellValue(sumr2);
			
			row.createCell((short) 3).setCellValue(sumr4);
			row.createCell((short) 4).setCellValue(sumr5);
			row.createCell((short) 5).setCellValue(sumr6);
			row.createCell((short) 6).setCellValue(sumr7);
			row.createCell((short) 8).setCellValue(sumr9);
			row.createCell((short) 7).setCellValue(sumr10);
			row.createCell((short) 8).setCellValue(sumr10);
			row.createCell((short) 9).setCellValue(sumr11);
			row.createCell((short) 10).setCellValue(sumr12);
			row.createCell((short) 13).setCellValue(sumr13);
			row.createCell((short) 11).setCellValue(sumr14);
			row.createCell((short) 12).setCellValue(sumr15);*/
			//row.createCell((short)15).setCellValue(rs.getString("UNMATCH_CATGCHG"));
			
		
			
			
			  Cell celsum0 = row.getCell(0);  
			  celsum0 = row.createCell(0);    
			  celsum0.setCellValue(" Total  ");
			  celsum0.setCellStyle(boldStyle);
			 /* sheet.autoSizeColumn(0);*/
			  

			  Cell celsum1 = row.getCell(1);  
			  celsum1 = row.createCell(1);    
			  celsum1.setCellValue(""+sumr1);
			  celsum1.setCellStyle(boldStyle);
		
			  
			  
			  

			  Cell celsum2 = row.getCell(2);  
			  celsum2 = row.createCell(2);    
			  celsum2.setCellValue(""+sumr2);
			  celsum2.setCellStyle(boldStyle);
			 
			  
			 // celsum2.setCellStyle(cs);
			  
			  
			  

			  Cell celsum3 = row.getCell(3);  
			  celsum3 = row.createCell(3);    
			  celsum3.setCellValue(""+sumr4);
			  celsum3.setCellStyle(boldStyle);
			
			  
			 // celsum3.setCellStyle(cs);
			  
			  
			  

			  Cell celsum4 = row.getCell(4);  
			  celsum4 = row.createCell(4);    
			  celsum4.setCellValue(""+sumr5);
			  celsum4.setCellStyle(boldStyle);
			
			  
			  //celsum4.setCellStyle(cs);
			  

			  Cell celsum5 = row.getCell(5);  
			  celsum5 = row.createCell(5);    
			  celsum5.setCellValue(""+sumr6);
			  celsum5.setCellStyle(boldStyle);
			
			  
			 // celcol4.setCellStyle(cs);
			  
			  
			  
			  Cell celsum6 = row.getCell(6);  
			  celsum6 = row.createCell(6);    
			  celsum6.setCellValue(""+sumr7);
			  celsum6.setCellStyle(boldStyle);
			
			  
			  //celcol6.setCellStyle(cs);
			  
			  
			  
			  
			  Cell celsum7 = row.getCell(7);  
			  celsum7 = row.createCell(7);    
			  celsum7.setCellValue(""+sumr9);
		      celsum7.setCellStyle(boldStyle);
		     
			  
			//  celcol7.setCellStyle(cs);
			  
			  
			  

			  Cell celsum8 = row.getCell(8);  
			  celsum8 = row.createCell(8);    
			  celsum8.setCellValue(""+sumr10);
			  celsum8.setCellStyle(boldStyle);
			  
			  //celsum8.setCellStyle(cs);
			  
			  
			  
			  Cell celsum9 = row.getCell(9);  
			  celsum9 = row.createCell(9);    
			  celsum9.setCellValue(""+sumr11);
			  celsum9.setCellStyle(boldStyle);
			
			 // celcol4.setCellStyle(cs);
			  
			  
			  
			  Cell celsum10 = row.getCell(10);  
			  celsum10 = row.createCell(10);    
			  celsum10.setCellValue(""+sumr12);
			 celsum10.setCellStyle(boldStyle);
		
			 // celcol10.setCellStyle(cs);
			  
			  
			  
			  
			  Cell celsum11 = row.getCell(11);  
			  celsum11 = row.createCell(11);    
			  celsum11.setCellValue(""+sumr14);
			  celsum11.setCellStyle(boldStyle);
			
			//  celcol11.setCellStyle(cs);
			  
			  
			  

			  Cell celsum12 = row.getCell(12);  
			  celsum12 = row.createCell(12);    
			  celsum12.setCellValue(""+sumr15);
			 celsum12.setCellStyle(boldStyle);
			 // celcol12.setCellStyle(cs);
			 
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
		
	}
	
	public void UtsMatchUnmatchrlywise(String a, String b , String c, String d , String e ) throws Exception{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		
		  tamsdbconnection dbcon =new tamsdbconnection(); con=dbcon.getconnect();
		  System.out.println("zone is "+a);
		  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
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
		
		
		  HSSFWorkbook hwb = new HSSFWorkbook(); 
		  HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		  
		  
		  
		  HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short)12);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short)10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
			
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
		        HSSFRow rowzone=sheet.createRow(0); 
		        Cell cel2 = rowzone.getCell(0);  
	            cel2 = rowzone.createCell(0);    
	            cel2.setCellValue("                                                            STATISTICS  OF PASSENGER  APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS) (Booking zone wise)                                                  BY CRIS  ");
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 12));
	           
	         
	            HSSFRow rowhead22 =sheet.createRow((short) 1);
				
				  Cell cel22 = rowhead22.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel22 = rowhead22.createCell(12);    
				  cel22.setCellValue("     Figure in units");
				 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 12, 13));
		          
	            
	            
	            
		    
				HSSFRow rowhead3 =sheet.createRow((short) 2);
				
				  Cell cel3 = rowhead3.getCell(5);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel3 = rowhead3.createCell(5);    
				  cel3.setCellValue(" For the Month " + d+ "-" +e);
				 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 5, 10));
		          
				  
				  
				  Cell cel31 = rowhead3.getCell(12);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel31 = rowhead3.createCell(12);    
				  cel31.setCellValue(  "Date - "+modifiedDate);
				 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 12 , 13));
		          
		          
		          

					 HSSFRow rowhead2 =sheet.createRow((short) 3);
					 Cell cel4 = rowhead2.getCell(1);  
					 cel4 = rowhead2.createCell(1);    
					 cel4.setCellValue("                  INPUT RECORDS           ");
					 cel4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			          sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 1 , 4));
			          
				
			          
			          Cell cel5 = rowhead2.getCell(5);  
			          cel5 = rowhead2.createCell(5);    
			          cel5.setCellValue("   MATCH RECORDS   ");
			          cel5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 5, 8));
				          
				      
				      Cell cel61 = rowhead2.getCell(9);  
				      cel61 = rowhead2.createCell(9);    
				      cel61.setCellValue("   UNMATCH RECORDS   ");
				      cel61.setCellStyle(boldStyle);
				      
				      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 ,9, 12));
				          
			
		
			 
			 
		  HSSFRow rowhead =sheet.createRow((short) 5);
		  
		  
		  Cell celcol0 = rowhead.getCell(0);  
		  celcol0 = rowhead.createCell(0);    
		  celcol0.setCellValue(" RLY  ");
		  celcol0.setCellStyle(boldStyle);
		  
		  

		  Cell celcol1 = rowhead.getCell(1);  
		  celcol1 = rowhead.createCell(1);    
		  celcol1.setCellValue("Total Records");
		  celcol1.setCellStyle(cs);
		  //sheet.autoSizeColumn(1);
		  
		  

		  Cell celcol2 = rowhead.getCell(2);  
		  celcol2 = rowhead.createCell(2);    
		  celcol2.setCellValue("Full Fare");
		  celcol2.setCellStyle(cs);
		 // sheet.autoSizeColumn(2);
		  
		  
		  

		  Cell celcol3 = rowhead.getCell(3);  
		  celcol3 = rowhead.createCell(3);    
		  celcol3.setCellValue("Safety Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol3.setCellStyle(cs);
		  //sheet.autoSizeColumn(3);
		  
		  
		  

		  Cell celcol4 = rowhead.getCell(4);  
		  celcol4 = rowhead.createCell(4);    
		  celcol4.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol4.setCellStyle(cs);
		 // sheet.autoSizeColumn(4);
		  

		  Cell celcol5 = rowhead.getCell(5);  
		  celcol5 = rowhead.createCell(5);    
		  celcol5.setCellValue("Total Records");
		  //celcol5.setCellStyle(boldStyle);
		 celcol5.setCellStyle(cs);
		  sheet.autoSizeColumn(5);
		  
		  
		  
		  Cell celcol6 = rowhead.getCell(6);  
		  celcol6 = rowhead.createCell(6);    
		  celcol6.setCellValue("Full Fare");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol6.setCellStyle(cs);
		  sheet.autoSizeColumn(6);
		  
		  
		  
		  
		  Cell celcol7 = rowhead.getCell(7);  
		  celcol7 = rowhead.createCell(7);    
		  celcol7.setCellValue("Safety Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol7.setCellStyle(cs);
		  sheet.autoSizeColumn(7);
		  
		  
		  

		  Cell celcol8 = rowhead.getCell(8);  
		  celcol8 = rowhead.createCell(8);    
		  celcol8.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol8.setCellStyle(cs);
		  sheet.autoSizeColumn(8);
		  
		  
		  
		  Cell celcol9 = rowhead.getCell(9);  
		  celcol9 = rowhead.createCell(9);    
		  celcol9.setCellValue("Total Records");
		 // celcol9.setCellStyle(boldStyle);
		  celcol9.setCellStyle(cs);
		  sheet.autoSizeColumn(9);
		  
		  
		  
		  Cell celcol10 = rowhead.getCell(10);  
		  celcol10 = rowhead.createCell(10);    
		  celcol10.setCellValue("Full Fare");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol10.setCellStyle(cs);
		  sheet.autoSizeColumn(10);
		  
		  
		  
		  
		  Cell celcol11 = rowhead.getCell(11);  
		  celcol11 = rowhead.createCell(11);    
		  celcol11.setCellValue("Safety Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol11.setCellStyle(cs);
		  sheet.autoSizeColumn(11);
		  
		  
		  

		  Cell celcol12 = rowhead.getCell(12);  
		  celcol12 = rowhead.createCell(12);    
		  celcol12.setCellValue("Other Charges");
		/*  celcol2.setCellStyle(boldStyle);*/
		  celcol12.setCellStyle(cs);
		  sheet.autoSizeColumn(12);
		 
		  int index=7; int sno=0; String name="";
		  
			while (rs.next()) {
				System.out.println("inside while is" + rs.getString(1));
				sno++;

				HSSFRow row = sheet.createRow((short) index);
				//row.createCell((short) 0).setCellValue(rs.getString("rly"));
				

				  Cell celzone = row.getCell(0);  
				  celzone = row.createCell(0);    
				  celzone.setCellValue(rs.getString("rly"));
				  celzone.setCellStyle(boldStyle);
				 
				row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
				row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
				row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
				row.createCell((short) 4).setCellValue(rs.getString("INPUT_OTHERCHG"));
				//row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
				row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
				row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
			    row.createCell((short) 7).setCellValue(rs.getString("MATCH_SAFETYCHG"));
				row.createCell((short) 8).setCellValue(rs.getString("MATCH_OTHERCHG"));
				//row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
				row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
				row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
				row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
				row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
				//row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  //sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  //sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  //sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				
				index++;

				/*FileOutputStream fileOut = new FileOutputStream(filename);
				hwb.write(fileOut);
				fileOut.close();*/
				
			}
			
			System.out.println("index is "+index);
			
			
			HSSFRow row = sheet.createRow((short) index);
			
			

			  Cell celsum = row.getCell(0);  
			  celsum = row.createCell(0);    
			  celsum.setCellValue("Total");
			  celsum.setCellStyle(boldStyle);
			

			  Cell celsum1 = row.getCell(1);  
			  celsum1 = row.createCell(1);    
			  celsum1.setCellValue(""+sumr1);
			  celsum1.setCellStyle(boldStyle);
			

			  Cell celsum2 = row.getCell(2);  
			  celsum2 = row.createCell(2);    
			  celsum2.setCellValue(""+sumr2);
			  celsum2.setCellStyle(boldStyle);
			  
			  

			  Cell celsum3 = row.getCell(3);  
			  celsum3 = row.createCell(3);    
			  celsum3.setCellValue(""+sumr3);
			  celsum3.setCellStyle(boldStyle);
			  

			  Cell celsum4 = row.getCell(4);  
			  celsum4 = row.createCell(4);    
			  celsum4.setCellValue(""+sumr4);
			  celsum4.setCellStyle(boldStyle);
			  

			  Cell celsum5 = row.getCell(5);  
			  celsum5 = row.createCell(5);    
			  celsum5.setCellValue(""+sumr5);
			  celsum5.setCellStyle(boldStyle);
			  
			  
			  Cell celsum6 = row.getCell(6);  
			  celsum6 = row.createCell(6);    
			  celsum6.setCellValue(""+sumr6);
			  celsum6.setCellStyle(boldStyle);
			  
			  
			  Cell celsum7 = row.getCell(7);  
			  celsum7 = row.createCell(7);    
			  celsum7.setCellValue(""+sumr7);
			  celsum7.setCellStyle(boldStyle);
			  
			  
			  Cell celsum8 = row.getCell(8);  
			  celsum8 = row.createCell(8);    
			  celsum8.setCellValue(""+sumr8);
			  celsum8.setCellStyle(boldStyle);
			  
			  
			  Cell celsum9 = row.getCell(9);  
			  celsum9 = row.createCell(9);    
			  celsum9.setCellValue(""+sumr9);
			  celsum9.setCellStyle(boldStyle);
			  
			  Cell celsum10 = row.getCell(10);  
			  celsum10 = row.createCell(10);    
			  celsum10.setCellValue(""+sumr10);
			  celsum10.setCellStyle(boldStyle);
			  
			  Cell celsum11 = row.getCell(11);  
			  celsum11 = row.createCell(11);    
			  celsum11.setCellValue(""+sumr11);
			  celsum11.setCellStyle(boldStyle);
			  
			  Cell celsum12 = row.getCell(12);  
			  celsum12 = row.createCell(12);    
			  celsum12.setCellValue(""+sumr12);
			  celsum12.setCellStyle(boldStyle);
			  
			
			
			
			 OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			//write this workbook to an Outputstream.
			hwb.write(outObject);
			outObject.flush();
			outObject.close();
			con.close();
		
	}
	
	
	//**************************************Method of Uts Match Unmatch Booking zone wise Excel Method**************************************	
public void UtsMatchUnmatchBookingzonewise(String a, String b , String c, String d , String e ) throws Exception{
			
			String month = "";
			month = view_data_obj.ChangeMonth_format(d);
			String year = "";
			year = e;
			System.out.println("month -----------> " + month);
			System.out.println("year -----------> " + year);
			
			int month_1 = Integer.parseInt(month);
			int year_1 = Integer.parseInt(year);
			
			  tamsdbconnection dbcon =new tamsdbconnection(); 
			  con=dbcon.getconnect();
			  System.out.println("zone is "+a);
			  String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate ;
			  Statement stmt = con.createStatement();
			  
			  String query= "select a.zone rly,a.total_records input_total ,a.full_fare Input_fullfare,a.safety_charge Input_safetychg,a.other_charge Input_otherchg, "
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
			  
			  
			  
			  HSSFWorkbook hwb = new HSSFWorkbook(); 
			  HSSFSheet sheet =hwb.createSheet("new sheet"); 
			  
			  
			  
			  
			  HSSFFont boldFont = hwb.createFont();
				boldFont.setFontHeightInPoints((short)12);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				HSSFFont boldFont2 = hwb.createFont();
				boldFont.setFontHeightInPoints((short)10);
				boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
				CellStyle cs =hwb.createCellStyle();
				 cs.setWrapText(true);
				 cs.setFont(boldFont);
				
			        HSSFCellStyle boldStyle = hwb.createCellStyle();
			        boldStyle.setFont(boldFont);
			        
			        HSSFRow rowzone=sheet.createRow(0); 
			        Cell cel2 = rowzone.getCell(0);  
		            cel2 = rowzone.createCell(0);    
		            cel2.setCellValue("     STATISTICS  OF PASSENGER  APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS) (Booking zone wise)                                             BY CRIS                              ");
		            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 12));
		           

		            HSSFRow rowhead22 =sheet.createRow((short) 1);
					
					  Cell cel22 = rowhead22.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
					  cel22 = rowhead22.createCell(11);    
					  cel22.setCellValue("     Figure in units");
					 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
					  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 1, 1 , 11, 12));
		            
			    
				HSSFRow rowhead3 =sheet.createRow((short) 2);
				
				  Cell cel3 = rowhead3.getCell(5);  
				  cel3 = rowhead3.createCell(5);    
				  cel3.setCellValue("    FOR THE MONTH  "+d+"-"+e);
				  cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
		          sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 5 , 9));
		          
		          
		          
				  Cell cel31 = rowhead3.getCell(11);  //                                                                                                              For the Month"+d+"-"+e+"                                                                                                                                                                                                             Date -"+modifiedDate
				  cel31 = rowhead3.createCell(11);    
				  cel31.setCellValue(  "Date - "+modifiedDate);
				 // cel3.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				  sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2 , 11 , 12));
		          
					 HSSFRow rowhead2 =sheet.createRow((short) 3);
					 Cell cel4 = rowhead2.getCell(1);  
					 cel4 = rowhead2.createCell(1);    
					 cel4.setCellValue("                  INPUT RECORDS           ");
					 cel4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			          sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 1 , 4));
			          
			          Cell cel5 = rowhead2.getCell(5);  
			          cel5 = rowhead2.createCell(5);    
			          cel5.setCellValue("             MATCH RECORDS          ");
			          cel5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				     sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 5, 8));
				          
				      Cell cel6 = rowhead2.getCell(9);  
				      cel6 = rowhead2.createCell(9);    
				      cel6.setCellValue("               UNMATCH RECORDS          ");
				      cel6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 ,9, 12));
				          
				  	/* HSSFRow rowhead2 =sheet.createRow((short) 3);
					 Cell cel4 = rowhead2.getCell(1);  
					 cel4 = rowhead2.createCell(1);    
					 cel4.setCellValue("                  INPUT RECORDS           ");
					 cel4.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
			         sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 1 , 4));
			          
				
			          
			          Cell cel5 = rowhead2.getCell(5);  
			          cel5 = rowhead2.createCell(5);    
			          cel5.setCellValue("     MATCH RECORDS        ");
			          cel5.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 5, 8));
				          
				      
				      
				      
				      Cell cel6 = rowhead2.getCell(9);  
				      cel6 = rowhead2.createCell(9);    
				      cel6.setCellValue("      UNMATCH RECORDS          ");
				      cel6.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
				      sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 ,9, 12));
				          */
			
			
				
		
				 
			  HSSFRow rowhead =sheet.createRow((short) 5);
			  

			  
			  Cell celcol0 = rowhead.getCell(0);  
			  celcol0 = rowhead.createCell(0);    
			  celcol0.setCellValue(" RLY  ");
			  celcol0.setCellStyle(boldStyle);
			  
			  

			  Cell celcol1 = rowhead.getCell(1);  
			  celcol1 = rowhead.createCell(1);    
			  celcol1.setCellValue("Total Records");
			  celcol1.setCellStyle(cs);
			  //sheet.autoSizeColumn(1);
			  
			  

			  Cell celcol2 = rowhead.getCell(2);  
			  celcol2 = rowhead.createCell(2);    
			  celcol2.setCellValue("Full Fare");
			  celcol2.setCellStyle(cs);
			 // sheet.autoSizeColumn(2);
			  
			  
			  

			  Cell celcol3 = rowhead.getCell(3);  
			  celcol3 = rowhead.createCell(3);    
			  celcol3.setCellValue("Safety Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol3.setCellStyle(cs);
			  //sheet.autoSizeColumn(3);
			  
			  
			  

			  Cell celcol4 = rowhead.getCell(4);  
			  celcol4 = rowhead.createCell(4);    
			  celcol4.setCellValue("Other Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol4.setCellStyle(cs);
			 // sheet.autoSizeColumn(4);
			  

			  Cell celcol5 = rowhead.getCell(5);  
			  celcol5 = rowhead.createCell(5);    
			  celcol5.setCellValue("Total Records");
			  //celcol5.setCellStyle(boldStyle);
			 celcol5.setCellStyle(cs);
			  sheet.autoSizeColumn(5);
			  
			  
			  
			  Cell celcol6 = rowhead.getCell(6);  
			  celcol6 = rowhead.createCell(6);    
			  celcol6.setCellValue("Full Fare");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol6.setCellStyle(cs);
			  sheet.autoSizeColumn(6);
			  
			  
			  
			  
			  Cell celcol7 = rowhead.getCell(7);  
			  celcol7 = rowhead.createCell(7);    
			  celcol7.setCellValue("Safety Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol7.setCellStyle(cs);
			  sheet.autoSizeColumn(7);
			  
			  
			  

			  Cell celcol8 = rowhead.getCell(8);  
			  celcol8 = rowhead.createCell(8);    
			  celcol8.setCellValue("Other Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol8.setCellStyle(cs);
			  sheet.autoSizeColumn(8);
			  
			  
			  
			  Cell celcol9 = rowhead.getCell(9);  
			  celcol9 = rowhead.createCell(9);    
			  celcol9.setCellValue("Total Records");
			 // celcol9.setCellStyle(boldStyle);
			  celcol9.setCellStyle(cs);
			  sheet.autoSizeColumn(9);
			  
			  
			  
			  Cell celcol10 = rowhead.getCell(10);  
			  celcol10 = rowhead.createCell(10);    
			  celcol10.setCellValue("Full Fare");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol10.setCellStyle(cs);
			  sheet.autoSizeColumn(10);
			  
			  
			  
			  
			  Cell celcol11 = rowhead.getCell(11);  
			  celcol11 = rowhead.createCell(11);    
			  celcol11.setCellValue("Safety Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol11.setCellStyle(cs);
			  sheet.autoSizeColumn(11);
			  
			  
			  

			  Cell celcol12 = rowhead.getCell(12);  
			  celcol12 = rowhead.createCell(12);    
			  celcol12.setCellValue("Other Charges");
			/*  celcol2.setCellStyle(boldStyle);*/
			  celcol12.setCellStyle(cs);
			  sheet.autoSizeColumn(12);
			  int index=7; int sno=0; String name="";
			  
			  
			  
				
				while (rs.next()) {
					System.out.println("inside while is" + rs.getString(1));
					sno++;

					HSSFRow row = sheet.createRow((short) index);
					/*row.createCell((short) 0).setCellValue(rs.getString("rly"));*/
					

					  Cell celzone = row.getCell(0);  
					  celzone = row.createCell(0);    
					  celzone.setCellValue(rs.getString("rly"));
					  celzone.setCellStyle(boldStyle);
					
					row.createCell((short) 1).setCellValue(rs.getString("INPUT_TOTAL"));
					row.createCell((short) 2).setCellValue(rs.getString("INPUT_FULLFARE"));
					row.createCell((short) 3).setCellValue(rs.getString("INPUT_SAFETYCHG"));
					row.createCell((short) 4).setCellValue(rs.getString("INPUT_OTHERCHG"));
					//row.createCell((short) 4).setCellValue(rs.getString("INPUT_CATGCHG"));
					row.createCell((short) 5).setCellValue(rs.getString("MATCH_TOTAL"));
					row.createCell((short) 6).setCellValue(rs.getString("MATCH_FULLFARE"));
				    row.createCell((short) 7).setCellValue(rs.getString("MATCH_SAFETYCHG"));
					row.createCell((short) 8).setCellValue(rs.getString("MATCH_OTHERCHG"));
					//row.createCell((short) 8).setCellValue(rs.getString("MATCH_CATGCHG"));
					row.createCell((short) 9).setCellValue(rs.getString("UNMATCH_TOTAL"));
					row.createCell((short) 10).setCellValue(rs.getString("UNMATCH_FULLFARE"));
					row.createCell((short) 11).setCellValue(rs.getString("UNMATCH_SAFETYCHG"));
					row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_OTHERCHG"));
					//row.createCell((short) 12).setCellValue(rs.getString("UNMATCH_CATGCHG"));
					  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
					  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
					  sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
					  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
					  //sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
					  sumr5=sumr5+ Long.parseLong(rs.getString("MATCH_TOTAL"));
					  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
					  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
					  sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
					  //sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
					  sumr9=sumr9+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
					  sumr10=sumr10+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
					  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
					  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
					  //sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
					
					index++;

					/*FileOutputStream fileOut = new FileOutputStream(filename);
					hwb.write(fileOut);
					fileOut.close();*/
					
				}
				
				System.out.println("index is "+index);
				
				
				HSSFRow row = sheet.createRow((short) index);
				
				

				  Cell celsum = row.getCell(0);  
				  celsum = row.createCell(0);    
				  celsum.setCellValue("Total");
				  celsum.setCellStyle(boldStyle);
				

				  Cell celsum1 = row.getCell(1);  
				  celsum1 = row.createCell(1);    
				  celsum1.setCellValue(""+sumr1);
				  celsum1.setCellStyle(boldStyle);
				

				  Cell celsum2 = row.getCell(2);  
				  celsum2 = row.createCell(2);    
				  celsum2.setCellValue(""+sumr2);
				  celsum2.setCellStyle(boldStyle);
				  
				  

				  Cell celsum3 = row.getCell(3);  
				  celsum3 = row.createCell(3);    
				  celsum3.setCellValue(""+sumr3);
				  celsum3.setCellStyle(boldStyle);
				  

				  Cell celsum4 = row.getCell(4);  
				  celsum4 = row.createCell(4);    
				  celsum4.setCellValue(""+sumr4);
				  celsum4.setCellStyle(boldStyle);
				  

				  Cell celsum5 = row.getCell(5);  
				  celsum5 = row.createCell(5);    
				  celsum5.setCellValue(""+sumr5);
				  celsum5.setCellStyle(boldStyle);
				  
				  
				  Cell celsum6 = row.getCell(6);  
				  celsum6 = row.createCell(6);    
				  celsum6.setCellValue(""+sumr6);
				  celsum6.setCellStyle(boldStyle);
				  
				  
				  Cell celsum7 = row.getCell(7);  
				  celsum7 = row.createCell(7);    
				  celsum7.setCellValue(""+sumr7);
				  celsum7.setCellStyle(boldStyle);
				  
				  
				  Cell celsum8 = row.getCell(8);  
				  celsum8 = row.createCell(8);    
				  celsum8.setCellValue(""+sumr8);
				  celsum8.setCellStyle(boldStyle);
				  
				  
				  Cell celsum9 = row.getCell(9);  
				  celsum9 = row.createCell(9);    
				  celsum9.setCellValue(""+sumr9);
				  celsum9.setCellStyle(boldStyle);
				  
				  Cell celsum10 = row.getCell(10);  
				  celsum10 = row.createCell(10);    
				  celsum10.setCellValue(""+sumr10);
				  celsum10.setCellStyle(boldStyle);
				  
				  Cell celsum11 = row.getCell(11);  
				  celsum11 = row.createCell(11);    
				  celsum11.setCellValue(""+sumr11);
				  celsum11.setCellStyle(boldStyle);
				  
				  Cell celsum12 = row.getCell(12);  
				  celsum12 = row.createCell(12);    
				  celsum12.setCellValue(""+sumr12);
				  celsum12.setCellStyle(boldStyle);
			
				
				 OutputStream outObject = response.getOutputStream();
				 response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
				//write this workbook to an Outputstream.
				hwb.write(outObject);
				outObject.flush();
				outObject.close();
				con.close();
			
		}


	public void  utsdetailsofmutpplateform(String a, String b, String c, String d, String e) throws Exception
	{
		
		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        tamsdbconnection dbcon= new tamsdbconnection();
		con = dbcon.getconnect();
		System.out.println("zone for text  " + a);
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		int sum=0;
		String filename =  a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		 filename = filename.replace(",", "");
		
		Statement stmt = con.createStatement();
		
		  String query="select p.orgrly orgrly,q.TC_TTE TC_TTE,q.platform platform,q.reservation_slip reservation_slip,q.superfast superfast,q.tourist tourist,q.parking parking,  "
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
					+ "(select distinct orgrly from tams_uts where  mm_bkg_date = "+month_1+" and yy_bkg_date = "+year_1+" and type_code = '9944')) e, "
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
		
		
		      HSSFWorkbook hwb = new HSSFWorkbook(); 
		      HSSFSheet sheet =hwb.createSheet("new sheet"); 
		  
		    HSSFFont boldFont = hwb.createFont();
			boldFont.setFontHeightInPoints((short) 10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFFont boldFont2 = hwb.createFont();
			boldFont.setFontHeightInPoints((short) 10);
			boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			CellStyle cs =hwb.createCellStyle();
			
			 cs.setWrapText(true);
			 cs.setFont(boldFont);
			
		        HSSFCellStyle boldStyle = hwb.createCellStyle();
		        boldStyle.setFont(boldFont);
		        
		        HSSFRow rowzone=sheet.createRow(0); 
		        Cell cel2 = rowzone.getCell(0);  
	            cel2 = rowzone.createCell(0);    
	            cel2.setCellValue("UTS DETAILS OF MUTP MMTS, CIDCO MRTS,TC/TTE PLATFORM ETC. (FOR ALL INDIAN RAILWAYS)                                 By CRIS");
	            cel2.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 0, 0 , 0 , 10));
	           
		 
			
			HSSFRow rowhead3 =sheet.createRow((short) 2);//
			
			 Cell cel21 = rowhead3.getCell(0);  
	            cel21 = rowhead3.createCell(0);    
	            cel21.setCellValue("      FOR THE MONTH "+d+"-"+e+"                                                                                                                      DATE : "+modifiedDate);
	            cel21.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 2, 2, 0 , 10));
	           
			
			

			HSSFRow rowhead5 =sheet.createRow((short) 3);
			    Cell cel22 = rowhead5.getCell(9);  
	            cel22 = rowhead5.createCell(9);    
	            cel22.setCellValue(" FIGURES IN THOUSANDS");
	            cel22.setCellStyle(boldStyle);//.setCellStyle(boldStyle);
	            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress( 3, 3 , 9 , 10));
	           
			
			
			
			  HSSFRow rowhead =sheet.createRow((short) 6);
			  
			  Cell celcol1 = rowhead.getCell(0);  
			  celcol1 = rowhead.createCell(0);    
			  celcol1.setCellValue("ORGRLY ");
			  celcol1.setCellStyle(boldStyle);
			  
			  
			  Cell celcol2 = rowhead.getCell(1);  
			  celcol2 = rowhead.createCell(1);    
			  celcol2.setCellValue("TC/TTE Tickets");
			  celcol2.setCellStyle(cs);
			  
			  

			  Cell celcol3 = rowhead.getCell(2);  
			  celcol3 = rowhead.createCell(2);    
			  celcol3.setCellValue("Platform Tickets charges");
			  celcol3.setCellStyle(cs);
			  

			  Cell celcol4 = rowhead.getCell(3);  
			  celcol4 = rowhead.createCell(3);    
			  celcol4.setCellValue("Reservation  slip charges");
			  celcol4.setCellStyle(cs);
			  

			  Cell celcol5 = rowhead.getCell(4);  
			  celcol5 = rowhead.createCell(4);    
			  celcol5.setCellValue("Superfast surcharge slip");
			  celcol5.setCellStyle(cs);
			  

			  Cell celcol6 = rowhead.getCell(5);  
			  celcol6 = rowhead.createCell(5);    
			  celcol6.setCellValue("Tourist Ticket charge");
			  celcol6.setCellStyle(cs);
			  

			  Cell celcol7 = rowhead.getCell(6);  
			  celcol7 = rowhead.createCell(6);    
			  celcol7.setCellValue("Platform cum parking ticket");
			  celcol7.setCellStyle(cs);
			  
			  Cell celcol8 = rowhead.getCell(7);  
			  celcol8 = rowhead.createCell(7);    
			  celcol8.setCellValue("MUTP charges");
			  celcol8.setCellStyle(boldStyle);
			  sheet.autoSizeColumn(7);
			  
			  Cell celcol9 = rowhead.getCell(8);  
			  celcol9 = rowhead.createCell(8);    
			  celcol9.setCellValue("MRTS charges");
			  celcol9.setCellStyle(boldStyle);
			  sheet.autoSizeColumn(8);
			  
			  
			  Cell celcol10 = rowhead.getCell(9);  
			  celcol10 = rowhead.createCell(9);    
			  celcol10.setCellValue("CIDCO charges");
			  celcol10.setCellStyle(cs);
			 
			  Cell celcol11 = rowhead.getCell(10);  
			  celcol11 = rowhead.createCell(10);    
			  celcol11.setCellValue("MMTS charges");
			  celcol11.setCellStyle(cs);
			  sheet.autoSizeColumn(10);
			 
		
			  
			  int index=7; int sno=0; String name="";
			  
		while(rs.next()){
			HSSFRow row = sheet.createRow((short) index);
			//row.createCell((short) 0).setCellValue(rs.getString("orgrly"));
			

			  Cell celcoldata1 = row.getCell(0);  
			  celcoldata1 = row.createCell(0);    
			  celcoldata1.setCellValue(rs.getString("orgrly"));
			  celcoldata1.setCellStyle(boldStyle);
			
			
			row.createCell((short) 1).setCellValue(rs.getString("TC_TTE"));
			row.createCell((short) 2).setCellValue(rs.getString("PLATFORM"));
			row.createCell((short) 3).setCellValue(rs.getString("RESERVATION_SLIP"));
			row.createCell((short) 4).setCellValue(rs.getString("SUPERFAST"));
			row.createCell((short) 5).setCellValue(rs.getString("TOURIST"));
			row.createCell((short) 6).setCellValue(rs.getString("PARKING"));
		    row.createCell((short) 7).setCellValue(rs.getString("MUTP_CHARGES"));
			row.createCell((short) 8).setCellValue(rs.getString("MRTS_CHARGES"));
			row.createCell((short) 9).setCellValue(rs.getString("CIDCO_CHARGES"));
			row.createCell((short) 10).setCellValue(rs.getString("MMTS_CHARGES"));
			sumr1=sumr1+Long.parseLong(rs.getString("TC_TTE"));
			sumr2=sumr2+Long.parseLong(rs.getString("PLATFORM"));
			sumr3=sumr3+Long.parseLong(rs.getString("RESERVATION_SLIP"));
			sumr4=sumr4+Long.parseLong(rs.getString("SUPERFAST"));
			sumr5=sumr5+Long.parseLong(rs.getString("TOURIST"));
			sumr6=sumr6+Long.parseLong(rs.getString("PARKING"));
			sumr7=sumr7+Long.parseLong(rs.getString("MUTP_CHARGES"));
			sumr8=sumr8+Long.parseLong(rs.getString("MRTS_CHARGES"));
			sumr9=sumr9+Long.parseLong(rs.getString("CIDCO_CHARGES"));
			sumr10=sumr10+Long.parseLong(rs.getString("MMTS_CHARGES"));
			index++;
			
		}
	
		
		HSSFRow row = sheet.createRow((short) index);
		//row.createCell((short) 0).setCellValue("Total");

		  Cell celsum1 = row.getCell(0);  
		  celsum1 = row.createCell(0);    
		  celsum1.setCellValue("Total");
		  celsum1.setCellStyle(boldStyle);
		  

		  Cell celsum2 = row.getCell(1);  
		  celsum2 = row.createCell(1);    
		  celsum2.setCellValue(""+sumr1);
		  celsum2.setCellStyle(boldStyle);
		  

		  Cell celsum3 = row.getCell(2);  
		  celsum3 = row.createCell(2);    
		  celsum3.setCellValue(""+sumr2);
		  celsum3.setCellStyle(boldStyle);
		  

		  Cell celsum4 = row.getCell(3);  
		  celsum4 = row.createCell(3);    
		  celsum4.setCellValue(""+sumr3);
		  celsum4.setCellStyle(boldStyle);
		  
		  Cell celsum5 = row.getCell(4);  
		  celsum5 = row.createCell(4);    
		  celsum5.setCellValue(""+sumr4);
		  celsum5.setCellStyle(boldStyle);
		  
		  Cell celsum6 = row.getCell(5);  
		  celsum6 = row.createCell(5);    
		  celsum6.setCellValue(""+sumr5);
		  celsum6.setCellStyle(boldStyle);
		  
		  Cell celsum7 = row.getCell(6);  
		  celsum7 = row.createCell(6);    
		  celsum7.setCellValue(""+sumr6);
		  celsum7.setCellStyle(boldStyle);
		  
		  Cell celsum8 = row.getCell(7);  
		  celsum8 = row.createCell(7);    
		  celsum8.setCellValue(""+sumr7);
		  celsum8.setCellStyle(boldStyle);
		  
		  Cell celsum9 = row.getCell(8);  
		  celsum9 = row.createCell(8);    
		  celsum9.setCellValue(""+sumr8);
		  celsum9.setCellStyle(boldStyle);
		  
		  Cell celsum10 = row.getCell(9);  
		  celsum10 = row.createCell(9);    
		  celsum10.setCellValue(""+sumr9);
		  celsum10.setCellStyle(boldStyle);
		  
		  Cell celsum11 = row.getCell(10);  
		  celsum11 = row.createCell(10);    
		  celsum11.setCellValue(""+sumr10);
		  celsum11.setCellStyle(boldStyle);
		  
		/*row.createCell((short) 1).setCellValue(sumr1);
		row.createCell((short) 2).setCellValue(sumr2);
	    row.createCell((short) 3).setCellValue(sumr3);
		row.createCell((short) 4).setCellValue(sumr4);
		row.createCell((short) 5).setCellValue(sumr5);
		row.createCell((short) 6).setCellValue(sumr6);
		row.createCell((short) 7).setCellValue(sumr7);
		row.createCell((short) 8).setCellValue(sumr8);
		row.createCell((short) 9).setCellValue(sumr9);
		row.createCell((short) 10).setCellValue(sumr10);
		*/
		System.out.println("year ------12111121212-----> " + year);
		   OutputStream outObject = response.getOutputStream();
			 response.setContentType("application/vnd.ms-excel");
			 response.setHeader("Content-Disposition", "inline; filename="+filename+".xls");
			 hwb.write(outObject);
			 outObject.flush();
			 outObject.close();
			 con.close();
		
	
			
	}
	
	
	@SuppressWarnings("resource")
	public void downloadFile(String path ,String filename ) throws MalformedURLException, IOException{
		System.out.println("fle name is"+path);
		
		
       File my_file = new File(path ); // We are downloading .txt file, in the format of doc with name check - check.doc
	    
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".xls\"");
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
	
	
	
	
	//zone full name
	
	
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



	
	
}
