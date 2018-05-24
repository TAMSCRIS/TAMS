package tams.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.DateFormatter;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import tams.formbean.tamsbean;
import tamsdbconnection.tamsdbconnection;

public class ExportText {

    HttpServletResponse response = ServletActionContext.getResponse();
	
	
	Connection con =null;
	String zonename=null;
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
	long sumr20=0 ,sumr21=0 , sumr22=0,sumr23=0;
	Viewdata view_data_obj = new Viewdata();
	
	
	
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
		
		String filename="IR UTS DETAILS OF MUTP_MMTS_CIDCO_MRTS,TC_TTE,PLATFORM etc. FOR ALL INDIAN RAILWAYS"+ "_" + modifiedDate; //a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		FileWriter writer;
		
		zonename=fullnameofzone(a);

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
		
		ResultSet rs = stmt.executeQuery(query);
		
		
		File file = new File("./"+filename+".txt");
		writer = new FileWriter(file, true);
		
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		/*writer.write("                                                      "+zonename);*/
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("    UTS DETAILS OF MUTP, MMTS, CIDCO, MRTS,TC/TTE, PLATFORM ETC.  FOR ALL INDIAN RAILWAYS    By CRIS");
		writer.write("            "+modifiedDate);
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("                              FOR THE MONTH    "+d+" "+e);
		/*writer.write("  "+e);*/
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("                                                                                FIGURES IN RUPEES");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		//writer.write("                                       INWARD");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("*******************************************************************************************************************************************");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s ","orgrly" , "TC_TTE" , "PLATFORM" , "RES SLIP" ,"SUPERFAST" , "TOURIST" , "Plateform"  ,"MUTP" ,"MRTS" , "CIDCO " , "MMTS"));
		writer.write("\r\n");
		writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s ","   " , "Tickets " , "Tickets "  ," Charges ","Surcharges  " ,"Tickets " , "cum  " , "CHARGES" ,"CHARGES " ,"CHARGES " , " CHARGES" ));
		writer.write("\r\n");
		writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s ","   " , "  " , "  "  ,"   "," Slip " ," Charges" , " Parking " , " " ,"  " ,"  " , "  " ));
		
		writer.write("\r\n");
		writer.write("***********************************************************************************************************************************************");
		writer.write("\r\n");
		
		while((rs!=null) && (rs.next()))
			{
	
			writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s" ,rs.getString("orgrly"),rs.getString("TC_TTE") , rs.getString("PLATFORM")  ,  rs.getString("RESERVATION_SLIP"),rs.getString("SUPERFAST"),rs.getString("TOURIST") ,  rs.getString("PARKING"),rs.getString("MUTP_CHARGES") ,  rs.getString("MRTS_CHARGES")   ,rs.getString("CIDCO_CHARGES"),rs.getString("MMTS_CHARGES")));
			
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
			
			
			writer.write("\r\n");
			}
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s" , "TOTAL" , sumr1 , sumr2 ,  sumr3 , sumr4 , sumr5 ,  sumr6 , sumr7 ,  sumr8   , sumr9 , sumr10));
		writer.write("\r\n");
		writer.write("\r\n");
		  writer.close();
		   
		   download(filename);
		 
			
		
	}
	public void exportcateringchgzonewisetrainwise(String a, String b, String c, String d, String e) throws Exception {
	
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
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		FileWriter writer;
		int sum=0;
		zonename=fullnameofzone(a);

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
		//InputStream inputStream = new ByteArrayInputStream(text.getBytes());

		//File file = new File(path);
		File file = new File("./"+filename+".txt");
		writer = new FileWriter(file, true);
				
		writer.write("\r\n");
		
		writer.write("PRS CATERING CHARGE APPORTIONMENT REPORT ZONE WISE TRAIN WISE    By CRIS");
		writer.write("\r\n");
		
		writer.write("                               " +zonename);
		writer.write("\r\n");
		writer.write("                                " + d + " - " + e + "                        Date - " + modifiedDate);
		
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("********************************************************************************************");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("sno.");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		
		writer.write("Train Number");
		writer.write("\t");
		writer.write("\t");
		
		writer.write("Catering Charge");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\r\n");
		writer.write("*******************************************************************************************");
		writer.write("\r\n");
		while((rs!=null) && (rs.next()))
			{
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write(rs.getString("S_No"));
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write(rs.getString("TRAINNO"));
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write(rs.getString("CATCHG"));
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			sum =sum+Integer.parseInt(rs.getString("CATCHG"));

			writer.write("\r\n");
			
		
			}
		String totsum=String.valueOf(sum);
		
		
		writer.write("*********************************************************************************************");
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		writer.write("Total Earning ");
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
		
		writer.write(totsum);
		writer.write("\r\n");
		writer.write("********************************************************************************************");
	    writer.close();
	   
	 
	   download(filename);
	    	
	    }
	
	public void prsstnnotintrainroute(String a, String b, String c, String d, String e) throws Exception{
		
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
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		FileWriter writer;
		
		zonename=fullnameofzone(a);

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
		
		//File file = new File(path);
		File file = new File("./"+filename+".txt");
		writer = new FileWriter(file, true);
		writer.write("\t");
		writer.write("\t");
		writer.write("\t");
	
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("\t");
		writer.write("\t");
		writer.write("(                        List of Stations not lying on the train route/List of Unmatch Trains.");
		writer.write("\r\n");
	    writer.write("\r\n");
		
		writer.write("                                                       " +zonename);
		writer.write("\r\n");
		writer.write("                                                     " + d + " - " + e + "                        Date - " + modifiedDate);
		
		writer.write("\r\n");
		writer.write("***************************************************************************************************************************************************");
		writer.write("\r\n");
		writer.write(String.format(" %15s %15s %15s %15s %15s %15s %15s %15s","Train number" , "From Station" , "From station code" , "To station","To station Code","Enroute station","Distance","Fare" ));
		writer.write("\r\n");
		writer.write("****************************************************************************************************************************************************");
		writer.write("\r\n");
		while((rs!=null) && (rs.next()))
			{
			writer.write(String.format(" %15s %15s %15s %15s %15s %15s %15s %15s",rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9) ));
			
			
			sumr1 =sumr1+Long.parseLong(rs.getString(9));

			writer.write("\r\n");
			
		
			}
		writer.write("\r\n");
		writer.write("\r\n");
		writer.write("                     Total Fare - " +sumr1 + "       ");
		//String totsum=String.valueOf(sum);
		
		
	
	    writer.close();
	   
	 
	    download(filename);
	    	
		
	}
	
	public void prsmatchunmatch(String a, String b, String c, String d, String e) throws Exception{
		
		
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
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		FileWriter writer;
		
		zonename=fullnameofzone(a);

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
			
			//File file = new File(path);
			File file = new File("./"+filename+".txt");
			writer = new FileWriter(file, true);
		
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (PRS)         By CRIS");
			
			
			writer.write("\r\n");
			
			writer.write("                                                                                                          Date" + modifiedDate);
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                   For the month     "+d + " - " + e + "                                 Figures in Units");
			
			
			writer.write("\r\n");
			writer.write("***************************************************************************************************************************************************************");
			
			writer.write("\r\n");
			writer.write(String.format(" %40s %40s  %40s   ","   INPUT RECORDS     " ,"       MATCH RECORDS " ,"           UNMATCH RECORDS "));
			writer.write("\r\n");
			
			writer.write("***************************************************************************************************************************************************************");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write(String.format(" %10s %10s %13s %10s %10s %10s %13s %10s %10s %10s %13s %10s %10s","RLY" , "Records" , "Total Fare  " , "Other " ,"Catering" ,  "Records" , "Total Fare  " , "Other " ,"Catering" , "Records" , "Total Fare  " , "Other " ,"Catering"));
			writer.write("\r\n");
			writer.write(String.format(" %10s %10s %13s %10s %10s %10s %10s %10s %10s %10s %13s %10s  %10s","   " ,"      " ,  "Excl. catchg"  ,"Charges" ,"Charges " , "      " ,  "Excl. catchg"  ,"Charges" ,"Charges " ,"      " ,  "Excl. catchg"  ,"Charges" ,"Charges "));
			
			writer.write("\r\n");
			writer.write("**************************************************************************************************************************************************************");
			writer.write("\r\n");
			while((rs!=null) && (rs.next()))
				{
		
				writer.write(String.format("%10s %10s %13s %10s %10s %10s  %13s %10s %10s %10s %13s %10s %10s " ,rs.getString("rly"),rs.getString("INPUT_TOTAL") , rs.getString("INPUT_FULLFARE" ) ,rs.getString("INPUT_OTHERCHG") ,  rs.getString("INPUT_CATGCHG"),rs.getString("MATCH_TOTAL") ,  rs.getString("MATCH_FULLFARE") ,  rs.getString("MATCH_OTHERCHG"),rs.getString("MATCH_CATGCHG")   ,rs.getString("UNMATCH_TOTAL"),rs.getString("UNMATCH_FULLFARE") ,rs.getString("UNMATCH_OTHERCHG") ,rs.getString("UNMATCH_CATGCHG") ));
				writer.write("\r\n");
				
				  sumr1=sumr1+ Long.parseLong(rs.getString("INPUT_TOTAL"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("INPUT_FULLFARE"));
				 // sumr3=sumr3+ Long.parseLong(rs.getString("INPUT_SAFETYCHG"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("INPUT_OTHERCHG"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("INPUT_CATGCHG"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("MATCH_TOTAL"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("MATCH_FULLFARE"));
				  //sumr8=sumr8+ Long.parseLong(rs.getString("MATCH_SAFETYCHG"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("MATCH_OTHERCHG"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("MATCH_CATGCHG"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("UNMATCH_TOTAL"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("UNMATCH_FULLFARE"));
				//  sumr13=sumr13+ Long.parseLong(rs.getString("UNMATCH_SAFETYCHG"));
				  sumr14=sumr14+ Long.parseLong(rs.getString("UNMATCH_OTHERCHG"));
				  sumr15=sumr15+ Long.parseLong(rs.getString("UNMATCH_CATGCHG"));
				//index++;
			
				}
			writer.write("\r\n");
			
			 String INPUT_TOTAL=String.valueOf(sumr1);  
	         String INPUT_FULLFARE=String.valueOf(sumr2);
	         String INPUT_OTHERCHG=String.valueOf(sumr4);
	         String INPUT_CATGCHG=String.valueOf(sumr5);
	         String MATCH_TOTAL=String.valueOf(sumr6);
	         String MATCH_FULLFARE=String.valueOf(sumr7);
	         String MATCH_OTHERCHG=String.valueOf(sumr9);
	         String MATCH_CATGCHG=String.valueOf(sumr10);
	         String UNMATCH_TOTAL=String.valueOf(sumr11);
	         String UNMATCH_FULLFARE=String.valueOf(sumr12);
	         String UNMATCH_OTHERCHG=String.valueOf(sumr14);
	         String UNMATCH_CATGCHG=String.valueOf(sumr15);
	        
	         writer.write(String.format("%10s %10s %13s %10s %10s %10s  %13s %10s %10s %10s %13s %10s %10s "," TOTAL" ,INPUT_TOTAL ,INPUT_FULLFARE,INPUT_OTHERCHG ,INPUT_CATGCHG,MATCH_TOTAL ,MATCH_FULLFARE,MATCH_OTHERCHG,MATCH_CATGCHG,UNMATCH_TOTAL,UNMATCH_FULLFARE, UNMATCH_OTHERCHG,UNMATCH_CATGCHG ));
		    writer.close();
            download(filename);	
		
	}
	
	public void prs_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
		
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
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
		
		zonename=fullnameofzone(a);
	
		int i=0;

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
			 //transpose matrix
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
				  dbvalue_trans[z][18]=String.valueOf(Long.parseLong(dbvalue_trans[z][19]) - Long.parseLong(dbvalue_trans[z][17]));
			  }
			  
			  
			   for(int k =1; k< 18 ; k++)//Long.parseLong(dbvalue_trans[18][column]
		    	{
		           sumr18= sumr18 + Long.parseLong(dbvalue_trans[k][17]);
		           sumr19 = sumr19 + Long.parseLong(dbvalue_trans[k][18]);
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
				 
				 
				 
				 
				 //TEXT FILE CREATION Start from here
				 
					//File file = new File(path);
					File file = new File("./"+filename+".txt");
					writer = new FileWriter(file, true);
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					/*writer.write("                                                      "+zonename);*/
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t"); 
					writer.write("                                         SUMMARY OF  APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)           By CRIS");
					
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("                                                                         FOR THE MONTH     "+d+" - "+e);
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("        DATE = ");
					writer.write(modifiedDate);
					/*writer.write("  "+e);*/
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("                                                                                                                  FIGURES IN 000's");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("***********************************************************************************************************************************************************************************************");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write(String.format(" %7s %7s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s","ZONE" , "CR" , "EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "  Retained " , "Inward" ,"Total Apport."));
					writer.write("\r\n");
					writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","   " , " " , " "  ," "," " ," " , " " , " " ," " ," " , " " , " " ," " ," " , " " , " ", " " ," " ,"Share "," Share "," Earnings "));
					
					writer.write("\r\n");
					writer.write("***********************************************************************************************************************************************************************************************");
					writer.write("\r\n");
					
					for(int jjj =1; jjj <18 ; jjj++){
						
						 
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							dbvalue[jjj-1][0], 
							dbvalue_trans[jjj][0],dbvalue_trans[jjj][1] , dbvalue_trans[jjj][2] , dbvalue_trans[jjj][3] , dbvalue_trans[jjj][4] , dbvalue_trans[jjj][5] , 
							dbvalue_trans[jjj][6] , dbvalue_trans[jjj][7] , dbvalue_trans[jjj][8] , dbvalue_trans[jjj][9] , dbvalue_trans[jjj][10] , 
							dbvalue_trans[jjj][11] , dbvalue_trans[jjj][12] , dbvalue_trans[jjj][13] , dbvalue_trans[jjj][14] , dbvalue_trans[jjj][15] , 
							dbvalue_trans[jjj][16] , dbvalue_trans[jjj][17] , dbvalue_trans[jjj][18] , dbvalue_trans[jjj][19] 
							
							)) ;
							writer.write("\r\n");
					
				
					}
				
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"  " ,"  " , "  " , " " , " " , " " , " "  , "  " ,"  " , "  " , " " , " " , " " , " " , "  " ,"  " , "  " , " " , sumr18 , sumr19 , sumr20 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							" OGA " ,dbvalue_trans[18][0] ,dbvalue_trans[18][1] ,dbvalue_trans[18][2] ,dbvalue_trans[18][3] ,dbvalue_trans[18][4] ,
							dbvalue_trans[18][5] ,dbvalue_trans[18][6] ,dbvalue_trans[18][7] ,dbvalue_trans[18][8] ,dbvalue_trans[18][9] ,
							dbvalue_trans[18][10] ,dbvalue_trans[18][11] ,dbvalue_trans[18][12] ,dbvalue_trans[18][13] ,dbvalue_trans[18][14] ,
							dbvalue_trans[18][15] ,dbvalue_trans[18][16]  ," "," ",sumr22 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Retained" ,dbvalue_trans[19][0] ,dbvalue_trans[19][1] ,dbvalue_trans[19][2] ,dbvalue_trans[19][3] ,dbvalue_trans[19][4] ,
							dbvalue_trans[19][5] ,dbvalue_trans[19][6] ,dbvalue_trans[19][7] ,dbvalue_trans[19][8] ,dbvalue_trans[19][9] ,
							dbvalue_trans[19][10] ,dbvalue_trans[19][11] ,dbvalue_trans[19][12] ,dbvalue_trans[19][13] ,dbvalue_trans[19][14] ,
							dbvalue_trans[19][15] ,dbvalue_trans[19][16]  ," "," ",sumr18 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Outward" ,dbvalue_trans[20][0] ,dbvalue_trans[20][1] ,dbvalue_trans[20][2] ,dbvalue_trans[20][3] ,dbvalue_trans[20][4] ,
							dbvalue_trans[20][5] ,dbvalue_trans[20][6] ,dbvalue_trans[20][7] ,dbvalue_trans[20][8] ,dbvalue_trans[20][9] ,
							dbvalue_trans[20][10] ,dbvalue_trans[20][11] ,dbvalue_trans[20][12] ,dbvalue_trans[20][13] ,dbvalue_trans[20][14] ,
							dbvalue_trans[20][15] ,dbvalue_trans[20][16]  ," "," ",sumr21 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							" Inward " ,dbvalue_trans[1][18] ,dbvalue_trans[2][18] ,dbvalue_trans[3][18] ,dbvalue_trans[4][18] ,dbvalue_trans[5][18] ,
							dbvalue_trans[6][18] ,dbvalue_trans[7][18] ,dbvalue_trans[8][18] ,dbvalue_trans[9][18] ,dbvalue_trans[10][18] ,
							dbvalue_trans[11][18] ,dbvalue_trans[12][18] ,dbvalue_trans[13][18] ,dbvalue_trans[14][18] ,dbvalue_trans[15][18] ,
							dbvalue_trans[16][18] ,dbvalue_trans[17][18]  ," "," ",sumr19 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Apport" ,dbvalue_trans[1][19] ,dbvalue_trans[2][19] ,dbvalue_trans[3][19] ,dbvalue_trans[4][19] ,dbvalue_trans[5][19] ,
							dbvalue_trans[6][19] ,dbvalue_trans[7][19] ,dbvalue_trans[8][19] ,dbvalue_trans[9][19] ,dbvalue_trans[10][19] ,
							dbvalue_trans[11][19] ,dbvalue_trans[12][19] ,dbvalue_trans[13][19] ,dbvalue_trans[14][19] ,dbvalue_trans[15][19] ,
							dbvalue_trans[16][19] ,dbvalue_trans[17][19] ," "," ",sumr20 ));
					writer.write("\r\n");
					writer.write("Earnings");
					
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("Note:  Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data");
					writer.write("\r\n");
					writer.write("       Outward Share : Earnings distributed to other Railways");
					writer.write("\r\n");
					writer.write("       Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic)");
					writer.write("\r\n");
					writer.write(" 		 Inward Share   : Earnings received from all Railways");
					writer.write("\r\n");
					writer.write("		 Total Apportionment Earnings : Retained Share + Inward Share");
					writer.write("\r\n");
					writer.write("Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings.");
					writer.write("\r\n");
					writer.write("Note 2- Catering charges have not been included in apportioned share of railways.");
					writer.write("\r\n");
				 
			  writer.close();
			   
			   download(filename);
			 
	}
	
	public void uts_apportionment_matrix(String a, String b, String c, String d, String e) throws Exception{
		
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
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
	
		zonename=fullnameofzone(a);
		
		int i=0;

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
	 
				 
				 
				 
				 
				 //TEXT FILE CREATION Start from here
				 
					//File file = new File(path);
		            File file = new File("./"+filename+".txt");
					writer = new FileWriter(file, true);
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					/*writer.write("                                                      "+zonename);*/
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t"); 
					writer.write("                                         SUMMARY OF  APPORTIONMENT FOR ALL INDIAN RAILWAYS (PRS)           By CRIS");
					
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("                                                                         FOR THE MONTH     "+d+" - "+e);
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("        DATE = ");
					writer.write(modifiedDate);
					/*writer.write("  "+e);*/
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("\t");
					writer.write("                                                                                                                  FIGURES IN 000's");
					writer.write("\r\n");
					writer.write("\t");
					writer.write("\t");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("***********************************************************************************************************************************************************************************************");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write(String.format(" %7s %7s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s","ZONE" , "CR" , "EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "  Retained " , "Inward" ,"Total Apport."));
					writer.write("\r\n");
					writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","   " , " " , " "  ," "," " ," " , " " , " " ," " ," " , " " , " " ," " ," " , " " , " ", " " ," " ,"Share "," Share "," Earnings "));
					
					writer.write("\r\n");
					writer.write("***********************************************************************************************************************************************************************************************");
					writer.write("\r\n");
					
					for(int jjj =1; jjj <18 ; jjj++){
						
						 
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							dbvalue[jjj-1][0], 
							dbvalue_trans[jjj][0],dbvalue_trans[jjj][1] , dbvalue_trans[jjj][2] , dbvalue_trans[jjj][3] , dbvalue_trans[jjj][4] , dbvalue_trans[jjj][5] , 
							dbvalue_trans[jjj][6] , dbvalue_trans[jjj][7] , dbvalue_trans[jjj][8] , dbvalue_trans[jjj][9] , dbvalue_trans[jjj][10] , 
							dbvalue_trans[jjj][11] , dbvalue_trans[jjj][12] , dbvalue_trans[jjj][13] , dbvalue_trans[jjj][14] , dbvalue_trans[jjj][15] , 
							dbvalue_trans[jjj][16] , dbvalue_trans[jjj][17] , dbvalue_trans[jjj][18] , dbvalue_trans[jjj][19] 
							
							)) ;
							writer.write("\r\n");
					
				
					}
				
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"  " ,"  " , "  " , " " , " " , " " , " "  , "  " ,"  " , "  " , " " , " " , " " , " " , "  " ,"  " , "  " , " " , sumr18 , sumr19 , sumr20 ));
					writer.write("\r\n");
					
					
					
					
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Platform" ,dbvalue_trans[18][0] ,dbvalue_trans[18][1] ,dbvalue_trans[18][2] ,dbvalue_trans[18][3] ,dbvalue_trans[18][4] ,
							dbvalue_trans[18][5] ,dbvalue_trans[18][6] ,dbvalue_trans[18][7] ,dbvalue_trans[18][8] ,dbvalue_trans[18][9] ,
							dbvalue_trans[18][10] ,dbvalue_trans[18][11] ,dbvalue_trans[18][12] ,dbvalue_trans[18][13] ,dbvalue_trans[18][14] ,
							dbvalue_trans[18][15] ,dbvalue_trans[18][16]  ," "," ",sumr23 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"OGA" ,dbvalue_trans[19][0] ,dbvalue_trans[19][1] ,dbvalue_trans[19][2] ,dbvalue_trans[19][3] ,dbvalue_trans[19][4] ,
							dbvalue_trans[19][5] ,dbvalue_trans[19][6] ,dbvalue_trans[19][7] ,dbvalue_trans[19][8] ,dbvalue_trans[19][9] ,
							dbvalue_trans[19][10] ,dbvalue_trans[19][11] ,dbvalue_trans[19][12] ,dbvalue_trans[19][13] ,dbvalue_trans[19][14] ,
							dbvalue_trans[19][15] ,dbvalue_trans[19][16]  ," "," ",sumr22 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Retained" ,dbvalue_trans[20][0] ,dbvalue_trans[20][1] ,dbvalue_trans[20][2] ,dbvalue_trans[20][3] ,dbvalue_trans[20][4] ,
							dbvalue_trans[20][5] ,dbvalue_trans[20][6] ,dbvalue_trans[20][7] ,dbvalue_trans[20][8] ,dbvalue_trans[20][9] ,
							dbvalue_trans[20][10] ,dbvalue_trans[20][11] ,dbvalue_trans[20][12] ,dbvalue_trans[20][13] ,dbvalue_trans[20][14] ,
							dbvalue_trans[20][15] ,dbvalue_trans[20][16]  ," "," ",sumr18 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Outward" ,dbvalue_trans[21][0] ,dbvalue_trans[21][1] ,dbvalue_trans[21][2] ,dbvalue_trans[21][3] ,dbvalue_trans[21][4] ,
							dbvalue_trans[21][5] ,dbvalue_trans[21][6] ,dbvalue_trans[21][7] ,dbvalue_trans[21][8] ,dbvalue_trans[21][9] ,
							dbvalue_trans[21][10] ,dbvalue_trans[21][11] ,dbvalue_trans[21][12] ,dbvalue_trans[21][13] ,dbvalue_trans[21][14] ,
							dbvalue_trans[21][15] ,dbvalue_trans[21][16]  ," "," ",sumr21 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							" Inward" ,dbvalue_trans[1][18] ,dbvalue_trans[2][18] ,dbvalue_trans[3][18] ,dbvalue_trans[4][18] ,dbvalue_trans[5][18] ,
							dbvalue_trans[6][18] ,dbvalue_trans[7][18] ,dbvalue_trans[8][18] ,dbvalue_trans[9][18] ,dbvalue_trans[10][18] ,
							dbvalue_trans[11][18] ,dbvalue_trans[12][18] ,dbvalue_trans[13][18] ,dbvalue_trans[14][18] ,dbvalue_trans[15][18] ,
							dbvalue_trans[16][18] ,dbvalue_trans[17][18]  ," "," ",sumr19 ));
					writer.write("\r\n");
					
					writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,
							"Apport" ,dbvalue_trans[1][19] ,dbvalue_trans[2][19] ,dbvalue_trans[3][19] ,dbvalue_trans[4][19] ,dbvalue_trans[5][19] ,
							dbvalue_trans[6][19] ,dbvalue_trans[7][19] ,dbvalue_trans[8][19] ,dbvalue_trans[9][19] ,dbvalue_trans[10][19] ,
							dbvalue_trans[11][19] ,dbvalue_trans[12][19] ,dbvalue_trans[13][19] ,dbvalue_trans[14][19] ,dbvalue_trans[15][19] ,
							dbvalue_trans[16][19] ,dbvalue_trans[17][19] ," "," ",sumr20 ));
					writer.write("\r\n");
					writer.write("Earnings");
					
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("\r\n");
					writer.write("Note:  Originating Fare : Total Originating Earnings of Individual Railway as per EDP Data");
					writer.write("\r\n");
					writer.write("       Outward Share : Earnings distributed to other Railways");
					writer.write("\r\n");
					writer.write("       Retained Share : Earnings retained by Home Railway (Local Traffic + Part of Foreign Traffic)");
					writer.write("\r\n");
					writer.write(" 		 Inward Share   : Earnings received from all Railways");
					writer.write("\r\n");
					writer.write("		 Total Apportionment Earnings : Retained Share + Inward Share");
					writer.write("\r\n");
					writer.write("Note 1- Unmatched records earnings share have been included in orginating railway apportioned earnings.");
					writer.write("\r\n");
					writer.write("Note 2- Catering charges have not been included in apportioned share of railways.");
					writer.write("\r\n");
				 
			  writer.close();
			   
			   download(filename);
			 
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
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
	
		zonename=fullnameofzone(a);
		ArrayList<Integer> retain =new ArrayList<Integer>();
		ArrayList<Integer> inward =new ArrayList<Integer>();
		ArrayList<Integer> total =new ArrayList<Integer>();
		int i=0;

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
				ResultSet rs = stmt.executeQuery(strQuery);
				

				
				System.out.println("strQuery >>>"+strQuery);
					
					 long Sum_array[] []=new long[18][21];
					
					for(int t =0; t<18 ; t++)
					{
						for(int j =0; j<21 ; j++)
						{
							Sum_array[t][j] = 0;
						}
					}
					
					
					int k =0;
					int m =0;
				
				File file = new File("./"+filename+".txt");
				writer = new FileWriter(file, true);
				writer.write("\t");
				writer.write("\t");
				writer.write("\t");
				writer.write("\r\n");
			
				writer.write("\t");
				writer.write("\t");
				writer.write("                                                                                               PRS PRTNFILE REPORT");
				writer.write("\r\n");
				writer.write("                                                                                                 "+zonename+"                                                                                   BY CRIS");
				writer.write("\r\n");
				writer.write("Figures in Units                                                                Passenger Apportionment of PRS for the month of " +d+" - "+e+"                                                           "+"Date:"+modifiedDate);
				writer.write("\t");
				writer.write("\t");
				writer.write("\t");
				writer.write("\r\n");
	
				
				writer.write("\r\n");
				writer.write("\r\n");
				
				writer.write("*******************************************************************************************************************************************************************************************************************************************************");
				writer.write("\r\n");
				writer.write("\r\n");
				writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","Rly To" ,"Gauge Code","Amount", "CR" , "EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "Total Shared" , "DIFF"));
				writer.write("\r\n");
				writer.write("******************************************************************************************************************************************************************************************************************************************************");
				writer.write("\r\n");
				while(rs.next()){
					
					
					System.out.println("m is"+m);
			   		
			   		
			   		if((m%3)==0  && m!=0)
			   		{
			   	
			   			System.out.println("k is"+k);
			   			writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s " ,"","TOT CRDT" , Sum_array[k][1] , Sum_array[k][2] ,  Sum_array[k][3] , Sum_array[k][4] ,  Sum_array[k][5] ,  Sum_array[k][6] , Sum_array[k][7]  , Sum_array[k][8] , Sum_array[k][9] , Sum_array[k][10] , Sum_array[k][11] , Sum_array[k][12] , Sum_array[k][13] , Sum_array[k][14] , Sum_array[k][15] , Sum_array[k][16] , Sum_array[k][17] , Sum_array[k][18] , Sum_array[k][19] , Sum_array[k][20]));
			   		
			   			writer.write("\r\n");
						writer.write("\r\n");
			   		}
			   		
			   		
			   		
			   		
			   		
			   		if((m%3)==0) 
			   		{
			   		 writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s  ", rs.getString("torly") , rs.getString("orgg") , rs.getString("Amount") ,  rs.getString("CR") , rs.getString("EC") ,  rs.getString("ER") ,  rs.getString("KR") , rs.getString("NC") , rs.getString("NE") , rs.getString("NF") , rs.getString("NR") , rs.getString("NW") , rs.getString("SB") , rs.getString("SC") , rs.getString("SE") , rs.getString("SR") , rs.getString("SW") , rs.getString("WC") , rs.getString("WR"), rs.getString("Total_shared") , rs.getString("Diff")));
			   		 writer.write("\r\n");
					 writer.write("\r\n");
			   		 
			   		}
			   		else 
			   		{
			   		         writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s","" , rs.getString("orgg") , rs.getString("Amount") ,  rs.getString("CR") , rs.getString("EC") ,  rs.getString("ER") ,  rs.getString("KR") , rs.getString("NC") , rs.getString("NE") , rs.getString("NF") , rs.getString("NR") , rs.getString("NW") , rs.getString("SB") , rs.getString("SC") , rs.getString("SE") , rs.getString("SR") , rs.getString("SW") , rs.getString("WC") , rs.getString("WR"), rs.getString("Total_shared") , rs.getString("Diff")));
			   		         writer.write("\r\n");
							 writer.write("\r\n");
			   		}
			   		
			   		
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
			   	
			   		++m;	
				}

				
				writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s " ,"","TOT CRDT" , Sum_array[k][1] , Sum_array[k][2] ,  Sum_array[k][3] , Sum_array[k][4] ,  Sum_array[k][5] ,  Sum_array[k][6] , Sum_array[k][7]  , Sum_array[k][8] , Sum_array[k][9] , Sum_array[k][10] , Sum_array[k][11] , Sum_array[k][12] , Sum_array[k][13] , Sum_array[k][14] , Sum_array[k][15] , Sum_array[k][16] , Sum_array[k][17] , Sum_array[k][18] , Sum_array[k][19] , Sum_array[k][20] ));
				 writer.write("\r\n");
				 writer.write("\r\n");
				writer.close();
			       
				 download(filename);	
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
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
	
		zonename=fullnameofzone(a);
		ArrayList<Integer> retain =new ArrayList<Integer>();
		ArrayList<Integer> inward =new ArrayList<Integer>();
		ArrayList<Integer> total =new ArrayList<Integer>();
		int i=0;

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
				ResultSet rs = stmt.executeQuery(strQuery);
				

				
				System.out.println("strQuery >>>"+strQuery);
					
					 long Sum_array[] []=new long[18][21];
					
					for(int t =0; t<18 ; t++)
					{
						for(int j =0; j<21 ; j++)
						{
							Sum_array[t][j] = 0;
						}
					}
					
					
					int k =0;
					int m =0;
				
				File file = new File("./"+filename+".txt");
				writer = new FileWriter(file, true);
				writer.write("\t");
				writer.write("\t");
				writer.write("\t");
				writer.write("\r\n");
			
				writer.write("\t");
				writer.write("\t");
				writer.write("                                                                                               UTS PRTNFILE REPORT");
				writer.write("\r\n");
				writer.write("                                                                                                "+zonename+"                                                                                   BY CRIS");
				writer.write("\r\n");
				writer.write("Figures in Units                                                                Passenger Apportionment of UTS for the month of " +d+" - "+e+"                                                           "+"Date:"+modifiedDate);
				writer.write("\t");
				writer.write("\t");
				writer.write("\t");
				writer.write("\r\n");
	
				
				writer.write("\r\n");
				writer.write("\r\n");
				
				writer.write("*******************************************************************************************************************************************************************************************************************************************************");
				writer.write("\r\n");
				writer.write("\r\n");
				writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","Rly To" ,"Gauge Code","Amount", "CR" , "EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "Total Shared" , "DIFF"));
				writer.write("\r\n");
				writer.write("******************************************************************************************************************************************************************************************************************************************************");
				writer.write("\r\n");
				while(rs.next()){
					
					
					System.out.println("m is"+m);
			   		
			   		
			   		if((m%4)==0  && m!=0)
			   		{
			   	
			   			System.out.println("k is"+k);
			   			writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s " ,"","TOT CRDT" , Sum_array[k][1] , Sum_array[k][2] ,  Sum_array[k][3] , Sum_array[k][4] ,  Sum_array[k][5] ,  Sum_array[k][6] , Sum_array[k][7]  , Sum_array[k][8] , Sum_array[k][9] , Sum_array[k][10] , Sum_array[k][11] , Sum_array[k][12] , Sum_array[k][13] , Sum_array[k][14] , Sum_array[k][15] , Sum_array[k][16] , Sum_array[k][17] , Sum_array[k][18] , Sum_array[k][19] , Sum_array[k][20]));
			   		
			   			writer.write("\r\n");
						writer.write("\r\n");
			   		}
			   		
			   		
			   		
			   		
			   		
			   		if((m%4)==0) 
			   		{
			   		 writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s  ", rs.getString("dstnrly") , rs.getString("FROM_GUAGE") , rs.getString("Amount") ,  rs.getString("CR") , rs.getString("EC") ,  rs.getString("ER") ,  rs.getString("KR") , rs.getString("NC") , rs.getString("NE") , rs.getString("NF") , rs.getString("NR") , rs.getString("NW") , rs.getString("SB") , rs.getString("SC") , rs.getString("SE") , rs.getString("SR") , rs.getString("SW") , rs.getString("WC") , rs.getString("WR"), rs.getString("Total_shared") , rs.getString("Diff")));
			   		 writer.write("\r\n");
					 writer.write("\r\n");
			   		 
			   		}
			   		else 
			   		{
			   		         writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s  ","", rs.getString("FROM_GUAGE") , rs.getString("Amount") ,  rs.getString("CR") , rs.getString("EC") ,  rs.getString("ER") ,  rs.getString("KR") , rs.getString("NC") , rs.getString("NE") , rs.getString("NF") , rs.getString("NR") , rs.getString("NW") , rs.getString("SB") , rs.getString("SC") , rs.getString("SE") , rs.getString("SR") , rs.getString("SW") , rs.getString("WC") , rs.getString("WR"), rs.getString("Total_shared") , rs.getString("Diff")));
			   		         writer.write("\r\n");
							 writer.write("\r\n");
			   		}
			   		
			   		
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
			   	
			   		++m;	
				}

				
				writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s " ,"","TOT CRDT" , Sum_array[k][1] , Sum_array[k][2] ,  Sum_array[k][3] , Sum_array[k][4] ,  Sum_array[k][5] ,  Sum_array[k][6] , Sum_array[k][7]  , Sum_array[k][8] , Sum_array[k][9] , Sum_array[k][10] , Sum_array[k][11] , Sum_array[k][12] , Sum_array[k][13] , Sum_array[k][14] , Sum_array[k][15] , Sum_array[k][16] , Sum_array[k][17] , Sum_array[k][18] , Sum_array[k][19] , Sum_array[k][20] ));
				 writer.write("\r\n");
				 writer.write("\r\n");
				writer.close();
			       
				 download(filename);	
	}
	
	
	
	
	
	/*public void prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		
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
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
	
		zonename=fullnameofzone(a);
		ArrayList<Integer> retain =new ArrayList<Integer>();
		ArrayList<Integer> inward =new ArrayList<Integer>();
		ArrayList<Integer> total =new ArrayList<Integer>();
		int i=0;

		Statement stmt = con.createStatement();
		String query="select a.ownrly, "
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
		
		
		
		 System.out.println("strQuery >>>"+query);
			
			ResultSet rs = stmt.executeQuery(query);
			File file = new File("./"+filename+".txt");
			writer = new FileWriter(file, true);
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                      "+zonename);
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                 SUMMARY OF  APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS              By CRIS");
			writer.write("                 "+modifiedDate);
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                                                            FOR THE MONTH     "+d+" "+e);
			writer.write("  "+e);
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                                                                                                     FIGURES IN RUPEES");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                       INWARD");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("***************************************************************************************************************************************************************************************************************************************************************************");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s","ZONE" , "CR" , "EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "Retained " , "Inward" ,"Total Apport."));
			writer.write("\r\n");
			writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","   " , " " , " "  ," "," " ," " , " " , " " ," " ," " , " " , " " ," " ," " , " " , " ", " " ," " ,"Share "," Share "," Earnings "));
			
			writer.write("\r\n");
			writer.write("****************************************************************************************************************************************************************************************************************************************************************************");
			writer.write("\r\n");
			while((rs!=null) && (rs.next()))
				{
		
				writer.write(String.format("%8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s " ,rs.getString("OWNRLY"),rs.getString("CR") , rs.getString("EC" ) ,rs.getString("EO") ,  rs.getString("ER"),rs.getString("KR") ,  rs.getString("NC") ,  rs.getString("NE"),rs.getString("NF")   ,rs.getString("NR"),rs.getString("NW") ,rs.getString("SB") ,rs.getString("SC"),rs.getString("SE"),rs.getString("SR"),rs.getString("SW"),rs.getString("WC"),rs.getString("WR") ,rs.getString("RETAINED"),rs.getString("INWARD"),rs.getString("TOTAL")));
				writer.write("\r\n");
				int aa= Integer.parseInt(rs.getString("RETAINED"));
				retain.add(i,aa );
				int bb= Integer.parseInt(rs.getString("INWARD"));
				inward.add(i, bb);
				int cc= Integer.parseInt(rs.getString("TOTAL"));
				total.add(i, cc);
				++i;
				
				
				  sumr1=sumr1+ Long.parseLong(rs.getString("CR"));
				  sumr2=sumr2+ Long.parseLong(rs.getString("EC"));
				 sumr3=sumr3+ Long.parseLong(rs.getString("EO"));
				  sumr4=sumr4+ Long.parseLong(rs.getString("ER"));
				  sumr5=sumr5+ Long.parseLong(rs.getString("KR"));
				  sumr6=sumr6+ Long.parseLong(rs.getString("NC"));
				  sumr7=sumr7+ Long.parseLong(rs.getString("NE"));
				  sumr8=sumr8+ Long.parseLong(rs.getString("NF"));
				  sumr9=sumr9+ Long.parseLong(rs.getString("NR"));
				  sumr10=sumr10+ Long.parseLong(rs.getString("NW"));
				  sumr11=sumr11+ Long.parseLong(rs.getString("SB"));
				  sumr12=sumr12+ Long.parseLong(rs.getString("SC"));
			     sumr13=sumr13+ Long.parseLong(rs.getString("SE"));
				  sumr14=sumr14+ Long.parseLong(rs.getString("SR"));
				  sumr15=sumr15+ Long.parseLong(rs.getString("SW"));
				  sumr16=sumr16+ Long.parseLong(rs.getString("WC"));
				  sumr17=sumr17+ Long.parseLong(rs.getString("WR"));
				  sumr18=sumr18+ Long.parseLong(rs.getString("RETAINED"));
				  sumr19=sumr19+ Long.parseLong(rs.getString("INWARD"));
				  sumr20=sumr20+ Long.parseLong(rs.getString("TOTAL"));
				  
				//index++;
			
				}
			writer.write("\r\n");
			
			 String cr=String.valueOf(sumr1);  
	         String ec=String.valueOf(sumr2);
	         String eo=String.valueOf(sumr3);
	         String er=String.valueOf(sumr4);
	         String kr=String.valueOf(sumr5);
	         String nc=String.valueOf(sumr6);
	         String ne=String.valueOf(sumr7);
	         String nf=String.valueOf(sumr8);
	         String nr=String.valueOf(sumr9);
	         String nw=String.valueOf(sumr10);
	         String sb=String.valueOf(sumr11);
	         String sc =String.valueOf(sumr12);
	         String se =String.valueOf(sumr13);
	         String sr =String.valueOf(sumr14);
	         String sw =String.valueOf(sumr15);
	         String wc =String.valueOf(sumr16);
	         String wr =String.valueOf(sumr17);
	         String retainsum =String.valueOf(sumr18);
	         String inwardsum =String.valueOf(sumr19);
	         String totsum =String.valueOf(sumr20);
	        
	         writer.write(String.format(" %6s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s"," Unmatched" , "0 " , " 0"  ,"0" ," 0" , " 0" , " 0" ,"0 " ," 0" , " 0" , " 0" ,"0 " ,"0 " , " 0" , "0 ", " 0" ," 0" ,"0",retainsum,inwardsum,totsum));
	         writer.write("\r\n");// Share
	         writer.write(String.format("%8s","Share") );
	         writer.write("\r\n");
	         writer.write(String.format(" %6s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","Orignating " , sumr1 , sumr2 , sumr3,sumr4 , sumr5, sumr6 ,sumr7 ,sumr8 , sumr9, sumr10 ,sumr11 ,sumr12 ,sumr13 , sumr14, sumr15,sumr16 ,sumr17 ," "," ",sumr20));
	       //Amount
	         writer.write("\r\n");
	         writer.write(String.format("Amount"));
	         writer.write("\r\n");
	         
	         writer.write("\r\n");
	         writer.write("\r\n");
	         long outward1=sumr1-retain.get(0);
	         long outward2=sumr2-retain.get(1);
	         long outward3=sumr3-retain.get(2);
	         long outward4=sumr4-retain.get(3);
	         long outward5=sumr5-retain.get(4);
	         long outward6=sumr6-retain.get(5);
	         long outward7=sumr7-retain.get(6);
	         long outward8=sumr8-retain.get(7);
	         long outward9=sumr9-retain.get(8);
	         long outward10=sumr10-retain.get(9);
	         long outward11=sumr11-retain.get(10);
	         long outward12=sumr12-retain.get(11);
	         long outward13=sumr13-retain.get(12);
	         long outward14=sumr14-retain.get(13);
	         long outward15=sumr15-retain.get(14);
	         long outward16=sumr16-retain.get(15);
	         long outward17=sumr17-retain.get(16);
	         
	         
	         long sumoutward=outward1+outward2+outward3+outward4+outward5+outward6+outward7+outward8+outward9+outward10+outward11+outward12+outward13+outward14+outward15+outward16+outward17;
	         
	         
	         
	         
	         writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","Outward Share" , outward1 , outward2 , outward3,outward4 , outward5, outward6 ,outward7 ,outward8 , outward9, outward10 ,outward11 ,outward12 ,outward13 , outward14, outward15,outward16 ,outward17 ," "," ",sumoutward));

	         writer.write("\r\n");
	         writer.write("\r\n");
	         writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","Retained" , retain.get(0) , retain.get(1) , retain.get(2),retain.get(3) , retain.get(4), retain.get(5) ,retain.get(6) ,retain.get(7) , retain.get(8), retain.get(9) ,retain.get(10) ,retain.get(11) ,retain.get(12) , retain.get(13), retain.get(14),retain.get(15) ,retain.get(16) ," "," ",sumr18));

	         writer.write("\r\n");
	         writer.write("\r\n");
	         writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","Inward" , inward.get(0) , inward.get(1) , inward.get(2),inward.get(3) , inward.get(4), inward.get(5) ,inward.get(6) ,inward.get(7) , inward.get(8), inward.get(9) ,inward.get(10) ,inward.get(11) ,inward.get(12) , inward.get(13), inward.get(14),inward.get(15) ,inward.get(16) ," "," ",sumr19));
	         writer.write("\r\n");
	         writer.write("\r\n");
	         writer.write(String.format(" %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s  %8s %8s %8s %8s %8s %8s %8s %8s","TOtal" , total.get(0) , total.get(1) , total.get(2),total.get(3) , total.get(4), total.get(5) ,total.get(6) ,total.get(7) , total.get(8), total.get(9) ,total.get(10) ,total.get(11) ,total.get(12) , total.get(13), total.get(14),total.get(15) ,total.get(16) ," "," ",sumr20));
	         writer.close();
	       
		   download(filename);	
		
	}*/
	
public void prscateringmatrix(String a, String b, String c, String d, String e) throws Exception{
		
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
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		System.out.println("zone for text  " + a);
		//String path = a + " " + b + " " + d	+ " " + e + ".txt";
		FileWriter writer;
	
		zonename=fullnameofzone(a);
		ArrayList<Integer> retain =new ArrayList<Integer>();
		ArrayList<Integer> inward =new ArrayList<Integer>();
		ArrayList<Integer> total =new ArrayList<Integer>();
		int i=0;

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
	    dbvalue[17][0]="Unmatch";
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
	    dbvalue[20][0]="Retained";
	    for(int var=0 ;var<17 ; var++){
	    	dbvalue[20][var+1]=dbvalue[var][18];
	    }
	    dbvalue[20][20]=dbvalue[17][18];
	    
	    //Outward Share
	    dbvalue[19][0]="Outward";
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
	    
	    dbvalue[22][0]="Tot.appor.";
	    for(int var=0 ;var<17 ; var++){
	    	dbvalue[22][var+1]=dbvalue[var][20];
	    }
	    dbvalue[22][20]=dbvalue[17][20];
	    
			File file = new File("./"+filename+".txt");
			writer = new FileWriter(file, true);
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			/*writer.write("                                                      "+zonename);*/
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                 SUMMARY OF  APPORTIONMENT OF CATERING CHARGES FOR ALL INDIAN RAILWAYS PRS                   By CRIS");
			writer.write("                 "+modifiedDate);
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                                         FOR THE MONTH     "+d+" "+e);
			/*writer.write("  "+e);*/
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                                                                                                     FIGURES IN RUPEES");
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			//writer.write("                                       INWARD");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write("****************************************************************************************************************************************************************************************************************************************");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write(String.format("%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s","ZONE" , "CR" , "   EC" , "EO" ,"ER" , "KR" , "NC"  ,"NE" ,"NF" , "NR" , "NW"  ,"SB" ,"SC" ,"SE" ,"SR" ,"SW" ,"WC" , "WR" , "Retained " , "Inward" ,"Total Apport."));
			writer.write("\r\n");
			writer.write(String.format("%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s%11s %11s%11s%11s%11s%11s%11s%11s%11s","   " , " " , " "  ," "," " ," " , " " , " " ," " ," " , " " , " " ," " ," " , " " , " ", " " ," " ,"Share "," Share "," Earnings "));
			
			writer.write("\r\n");
			writer.write("*********************************************************************************************************************************************************************************************************************************************");
			writer.write("\r\n");
			for(int row=0 ; row<23 ;row++)
				{
		
				writer.write(String.format("%10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s %10s  %10s %10s %10s %10s %10s %10s %10s " ,dbvalue[row][0],dbvalue[row][1] , dbvalue[row][2] , dbvalue[row][3] ,  dbvalue[row][4],dbvalue[row][5] ,  dbvalue[row][6] ,  dbvalue[row][7], dbvalue[row][8]  ,dbvalue[row][9],dbvalue[row][10] ,dbvalue[row][11] ,dbvalue[row][12],dbvalue[row][13],dbvalue[row][14],dbvalue[row][15],dbvalue[row][16],dbvalue[row][17] ,dbvalue[row][18],dbvalue[row][19],dbvalue[row][20]));
				writer.write("\r\n");
				
			
				}
			writer.write("\r\n");
			
			
	         writer.close();
	       
		   download(filename);	
		
	}

	public void UtsMatchUnmatchOrgrlywise(String a, String b, String c, String d, String e) throws Exception{
		
	
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
		//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
		String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
		FileWriter writer;
	
		zonename=fullnameofzone(a);

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
			
			//File file = new File(path);
			
			File file = new File("./"+filename+".txt");
			writer = new FileWriter(file, true);
		
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)         By CRIS");
			
			writer.write("\r\n");
			
			writer.write("                                                    Originating zone wise");
			writer.write("\r\n");
			
			writer.write("                                                                                                          Date" + modifiedDate);
			writer.write("\r\n");
			writer.write("\t");
			writer.write("\t");
			writer.write("\t");
			writer.write("                                                   For the month     "+d + " - " + e + "                                 Figures in Units");
			
			
			writer.write("\r\n");
			writer.write("***************************************************************************************************************************************************************");
			
			writer.write("\r\n");
			writer.write(String.format(" %40s %40s  %40s   ","   INPUT RECORDS     " ,"       MATCH RECORDS " ,"           UNMATCH RECORDS "));
			writer.write("\r\n");
			
			writer.write("***************************************************************************************************************************************************************");
			writer.write("\r\n");
			writer.write("\r\n");
			writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","RLY" , "Records" , "Full Fare" , "Safety" ,"Other" , "Records" , "Full Fare" , "Safety" ,"Other" , "Records" , "Full Fare" , "Safety" ,"Other" ));
			writer.write("\r\n");
			writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","   " ,"      " ,  "         "  ,"Charges" ,"Charges" ,"      " ,  "         "  ,"Charges" ,"Charges" ,"      " ,  "         "  ,"Charges" ,"Charges"));
				
			writer.write("\r\n");
			writer.write("**************************************************************************************************************************************************************");
			writer.write("\r\n");
			while((rs!=null) && (rs.next()))
				{
		
				writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s  %10s" ,rs.getString("rly"),rs.getString("INPUT_TOTAL") , rs.getString("INPUT_FULLFARE" )  ,  rs.getString("INPUT_SAFETYCHG"),rs.getString("INPUT_OTHERCHG"),rs.getString("MATCH_TOTAL") ,  rs.getString("MATCH_FULLFARE"),rs.getString("MATCH_SAFETYCHG") ,  rs.getString("MATCH_OTHERCHG")   ,rs.getString("UNMATCH_TOTAL"),rs.getString("UNMATCH_FULLFARE") ,rs.getString("UNMATCH_SAFETYCHG"),rs.getString("UNMATCH_OTHERCHG") ));
				writer.write("\r\n");
				
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
				//index++;
			
				}
			writer.write("\r\n");
			
			 String INPUT_TOTAL=String.valueOf(sumr1);  
	         String INPUT_FULLFARE=String.valueOf(sumr2);
	         String INPUT_SAFETYCHG=String.valueOf(sumr3);
	         String INPUT_OTHERCHG=String.valueOf(sumr4);
	         
	         String MATCH_TOTAL=String.valueOf(sumr5);
	         String MATCH_FULLFARE=String.valueOf(sumr6);
	         String MATCH_SAFETYCHG=String.valueOf(sumr7);
	         String MATCH_OTHERCHG=String.valueOf(sumr8);
	         
	         String UNMATCH_TOTAL=String.valueOf(sumr9);
	         String UNMATCH_FULLFARE=String.valueOf(sumr10);
	         String UNMATCH_SAFETYCHG=String.valueOf(sumr11);
	         String UNMATCH_OTHERCHG=String.valueOf(sumr12);
	        
	        
	         writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s  %10s "," TOTAL" ,INPUT_TOTAL ,INPUT_FULLFARE,INPUT_SAFETYCHG,INPUT_OTHERCHG ,MATCH_TOTAL ,MATCH_FULLFARE,MATCH_SAFETYCHG,MATCH_OTHERCHG, UNMATCH_TOTAL,UNMATCH_FULLFARE,UNMATCH_SAFETYCHG ,UNMATCH_OTHERCHG ));
		     writer.close();
		    download(filename);	
		
	}
	
	

	public void utsunmatchedodpairs(String a, String b, String c, String d, String e){

		String month = "";
		month = view_data_obj.ChangeMonth_format(d);
		String year = "";
		year = e;
		System.out.println("month -----------> " + month);
		System.out.println("year -----------> " + year);
		
		int month_1 = Integer.parseInt(month);
		int year_1 = Integer.parseInt(year);
		try{
			tamsdbconnection dbcon= new tamsdbconnection();
			con = dbcon.getconnect();
			System.out.println("zone for text  " + a);
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
			//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
			String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
			FileWriter writer;
		
			zonename=fullnameofzone(a);

			Statement stmt = con.createStatement();
			String query=" select station_from,station_from_code,station_upto,station_to_code,via,distance , sum(full_fare)  from tams_uts where mm_bkg_date ="+month+" and yy_bkg_date = "+year
					+ "and apprcnf_flag is null and orgrly = '"+a+"' group by station_from,station_from_code , station_upto,station_to_code,via,distance  "
					+ "order by station_from,station_upto ";
			
			
			
			
			
			ResultSet rs = stmt.executeQuery(query);
			 zonename=fullnameofzone(a);
			 
			 
			 File file = new File("./"+filename+".txt");
				writer = new FileWriter(file, true);
			
				writer.write("\r\n");
				writer.write("\t");
				writer.write("\t");
				writer.write("    UTS Unmatched OD pairs with Via, Distance and Full Fare        By CRIS");
				
				writer.write("\r\n");

				writer.write("\r\n");
				writer.write("                                  " + zonename);
				writer.write("\r\n");
				writer.write("                                                                          Date " + modifiedDate);
				writer.write("\r\n");
				writer.write("\t");
				writer.write("\t");
				writer.write("\t");
				writer.write("               For the month  "+d + " - " + e + "                  Figures in Units");
				
				
				writer.write("\r\n");
				
				
				writer.write("*************************************************************************************************");
				writer.write("\r\n");
				writer.write("\r\n");
				writer.write(String.format("%10s %10s %10s %10s %12s %10s %10s","Station " , "Station " , "Station " , "Station To " ,"Via" , "Distance" , " Fare"  ));
				writer.write("\r\n");
				writer.write(String.format("%10s %10s %10s %10s %12s %10s %10s"," From  " ,"From Code" ,  "Upto "  ,"Code" ,"   " ,"      " ,  "         "  ,""));
					
				writer.write("\r\n");
				writer.write("*************************************************************************************************");
				writer.write("\r\n");
				while((rs!=null) && (rs.next()))
					{
			
					writer.write(String.format("%10s %10s %10s %10s %15s %10s %10s" ,rs.getString("station_from"),rs.getString("station_from_code") , rs.getString("station_upto" )  ,  rs.getString("station_to_code"),rs.getString("via"),rs.getString("distance") ,  rs.getString("sum(full_fare)") ));
					writer.write("\r\n");
					
					  sumr1=sumr1+ Long.parseLong(rs.getString("sum(full_fare)"));
					 
				
					}
				writer.write("\r\n");
				
				 String INPUT_TOTAL=String.valueOf(sumr1);  
		        
		        
					writer.write("************************************************************************************************");
				    writer.write("\r\n");
				    writer.write("\r\n");
					
					writer.write(String.format("%10s %10s %10s %10s %15s %10s %10s"," "," "," "," "," "," TOTAL FARE" ,INPUT_TOTAL  ));
		         
		         writer.write("\r\n");

		         writer.write("\r\n");

		         writer.write("\r\n");

		         writer.write("\r\n");
		         writer.write("\r\n");
		         
			     writer.close();
			    download(filename);	
		}catch(Exception ex){
			
		}
		
	}
//************************************************Text Method of Uts Match Unmatch Report Booking wise************************************************************************
	public void UtsMatchUnmatchBookingZonewise(String a, String b, String c, String d, String e) throws Exception{
	
	
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
	//String path="/ARMSFILES/sheet/"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
	//String path="E:/TAMSJASPERREPORTS"+ a + " " + b + " " + d+ " - " + e + "_" + modifiedDate+".txt";
	String filename= a + " " + b + " " + d+ " - " + e + "_" + modifiedDate;
	FileWriter writer;
	
	zonename=fullnameofzone(a);

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
	 				//File file = new File(path);
	 				
	 				File file = new File("./"+filename+".txt");
	 				writer = new FileWriter(file, true);
	 			
	 				writer.write("\r\n");
	 				writer.write("\t");
	 				writer.write("\t");
	 				writer.write("                         STATISTICS OF PASSENGER APPORTIONMENT FOR ALL INDIAN RAILWAYS (UTS)         By CRIS");
	 				
	 				writer.write("\r\n");
	 				
	 				writer.write("                                                    Booking zone wise");
	 				writer.write("\r\n");
	 				
	 				writer.write("                                                                                                          Date" + modifiedDate);
	 				writer.write("\r\n");
	 				writer.write("\t");
	 				writer.write("\t");
	 				writer.write("\t");
	 				writer.write("                                                   For the month     "+d + " - " + e + "                                 Figures in Units");
	 				
	 				
	 				writer.write("\r\n");
	 				writer.write("***************************************************************************************************************************************************************");
	 				
	 				writer.write("\r\n");
	 				writer.write(String.format(" %40s %40s  %40s   ","   INPUT RECORDS     " ,"       MATCH RECORDS " ,"           UNMATCH RECORDS "));
	 				writer.write("\r\n");
	 				
	 				writer.write("***************************************************************************************************************************************************************");
	 				writer.write("\r\n");
	 				writer.write("\r\n");
	 				writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","RLY" , "Records" , "Full Fare" , "Safety" ,"Other" , "Records" , "Full Fare" , "Safety" ,"Other" , "Records" , "Full Fare" , "Safety" ,"Other" ));
	 				writer.write("\r\n");
	 				writer.write(String.format(" %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s","   " ,"      " ,  "         "  ,"Charges" ,"Charges" ,"      " ,  "         "  ,"Charges" ,"Charges" ,"      " ,  "         "  ,"Charges" ,"Charges"));
	 					
	 				writer.write("\r\n");
	 				writer.write("**************************************************************************************************************************************************************");
	 				writer.write("\r\n");
	 				while((rs!=null) && (rs.next()))
	 					{
	 			
	 					writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s  %10s" ,rs.getString("rly"),rs.getString("INPUT_TOTAL") , rs.getString("INPUT_FULLFARE" )  ,  rs.getString("INPUT_SAFETYCHG"),rs.getString("INPUT_OTHERCHG"),rs.getString("MATCH_TOTAL") ,  rs.getString("MATCH_FULLFARE"),rs.getString("MATCH_SAFETYCHG") ,  rs.getString("MATCH_OTHERCHG")   ,rs.getString("UNMATCH_TOTAL"),rs.getString("UNMATCH_FULLFARE") ,rs.getString("UNMATCH_SAFETYCHG"),rs.getString("UNMATCH_OTHERCHG") ));
	 					writer.write("\r\n");
	 					
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
	 					//index++;
	 				
	 					}
	 				writer.write("\r\n");
	 				
	 				 String INPUT_TOTAL=String.valueOf(sumr1);  
	 		         String INPUT_FULLFARE=String.valueOf(sumr2);
	 		         String INPUT_SAFETYCHG=String.valueOf(sumr3);
	 		         String INPUT_OTHERCHG=String.valueOf(sumr4);
	 		         
	 		         String MATCH_TOTAL=String.valueOf(sumr5);
	 		         String MATCH_FULLFARE=String.valueOf(sumr6);
	 		         String MATCH_SAFETYCHG=String.valueOf(sumr7);
	 		         String MATCH_OTHERCHG=String.valueOf(sumr8);
	 		         
	 		         String UNMATCH_TOTAL=String.valueOf(sumr9);
	 		         String UNMATCH_FULLFARE=String.valueOf(sumr10);
	 		         String UNMATCH_SAFETYCHG=String.valueOf(sumr11);
	 		         String UNMATCH_OTHERCHG=String.valueOf(sumr12);
	 		        
	 		        
	 		         writer.write(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s  %10s "," TOTAL" ,INPUT_TOTAL ,INPUT_FULLFARE,INPUT_SAFETYCHG,INPUT_OTHERCHG ,MATCH_TOTAL ,MATCH_FULLFARE,MATCH_SAFETYCHG,MATCH_OTHERCHG, UNMATCH_TOTAL,UNMATCH_FULLFARE,UNMATCH_SAFETYCHG ,UNMATCH_OTHERCHG ));
	 			    
	 		        writer.write("\r\n");

			         writer.write("\r\n");

			         writer.write("\r\n");
	 		         
	 		         writer.close();
	 			    download(filename);	
	 			
	 		
	
}
	
	
	public void download(String filename) throws IOException
	{
		try{
	   // File my_file = new File(path); // We are downloading .txt file, in the format of doc with name check - check.doc
			if(con!=null){
				con.close();
			}
			File my_file = new File("./"+filename+".txt");
	    
        HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("application/text");
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+".txt\"");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
       
        byte[] buffer = new byte[4096];
        int length;

       
        
        
        while ((length = in.read(buffer)) != -1) {
        	out.write(buffer, 0, length);
    		System.out.println(buffer);
    		}

   
        in.close();
         out.flush(); 
        if(my_file.delete()){
			System.out.println("folder is deleted");
		}
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
        
        
		 
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
		
		
		if(zone.equalsIgnoreCase("IR")){
			zone="Indian Railway";
		}
		
		
		
		
		
		return zone;
		
	}

		
		
		
	}


