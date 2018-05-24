package tams.databean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tams.formbean.tamsbean;
import tamsdbconnection.tamsdbconnection;

public class tamsdao {

	Statement stmt;
	Connection con = null;
	tamsbean bean;
	tamsdbconnection dbcon;

	public boolean checkLogin(String user, String pass , String zone) {

		boolean status = false;
		// Connection con = null;

		try {

			try {
				dbcon = new tamsdbconnection();
				con = dbcon.getconnect();
				if (con != null) {
					System.out.println("user" + user + "pass" + pass + "zone" + zone);
				
					String query = "select * from taedusers where username ='"
							+ user + "'and password ='" + pass + "' and user_zone = '"+zone+"'  ";
					System.out.println(query);
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						if (rs.getString("username").equals(user)
								&& rs.getString("password").equals(pass)) {
							status = true;
							System.out.println("hi....."
									+ rs.getString("username"));
							System.out.println("hiuser....."
									+ rs.getString("password"));
						} else {
							status = false;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null)
					try {
						con.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public ArrayList<tamsbean> getreportyearDetail(String user) throws Exception {

		System.out.println("Arrived at getreportyearDetail method");
		List<tamsbean> rptlist = new ArrayList<tamsbean>();
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				System.out.println("connection setuped");
				stmt = con.createStatement();
				System.out.println("444444444444444444444444444444");
				
				String Query ="" ;
				
				if(user.equalsIgnoreCase("CRIS"))
				{
					Query = "select distinct year from tamsmonthmaster";
				}
				else
				{	
					Query = "select distinct year from tamsmonthmaster where done_flag ='Y' ";
				}
				
				 
				System.out.println("3333" + Query);
				ResultSet rs = stmt.executeQuery(Query);
				System.out.println("55555555555555555555555555" + rs);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setReportyear(rs.getString("year"));
					rptlist.add(bean);
				}

				System.out.println("list elements" + "  " + rptlist);
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");
		}
		return (ArrayList<tamsbean>) rptlist;
	}

	public ArrayList<tamsbean> getreportmonthDetail(String user,String year)
			throws Exception {

		System.out.println("Arrived at getreportmonthDetail method");
		List<tamsbean> rptmonthlist = new ArrayList<tamsbean>();
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				System.out.println("connection setuped");
				stmt = con.createStatement();
				// System.out.println("444444444444444444444444444444");
				
				
				String Query ="" ;
				
				if(user.equalsIgnoreCase("CRIS"))
				{
					Query = "select distinct month from tamsmonthmaster where year ='"+ year + "'   ";
				}
				else
				{	
					Query = "select distinct month from tamsmonthmaster where year ='"+ year + "' and done_flag ='Y'   ";
				}
				
				
				
				
				
				// System.out.println("3333"+Query);
				ResultSet rs = stmt.executeQuery(Query);
				// System.out.println("55555555555555555555555555"+rs);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setReportmonth(rs.getString("month"));
					rptmonthlist.add(bean);
				}

				System.out.println("list elements" + "  " + rptmonthlist);
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");

		}
		
	
		return (ArrayList<tamsbean>) rptmonthlist;
	}

	public ArrayList<tamsbean> getprojectDetail(String year, String month)
			throws Exception {

		System.out.println("Arrived at getprojectDetail method");
		List<tamsbean> rptprojectlist = new ArrayList<tamsbean>();

		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				System.out.println("connection setuped");
				stmt = con.createStatement();
				// System.out.println("444444444444444444444444444444");
				// String Query =
				// "select distinct project from taedapprprallreport";
				String query = "select distinct project from tamsreportmaster" ;
				// System.out.println("3333"+Query);
				ResultSet rs = stmt.executeQuery(query);
				// System.out.println("55555555555555555555555555"+rs);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setEarnprojecttype(rs.getString("project"));
					rptprojectlist.add(bean);
				}

				System.out.println("list elements" + "  " + rptprojectlist);
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");

		}

		return (ArrayList<tamsbean>) rptprojectlist;
	}

	public ArrayList<tamsbean> getreportDetail(String project) throws Exception {

		System.out.println("Arrived at getreportDetail method");
		List<tamsbean> reportlist = new ArrayList<tamsbean>();
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				System.out.println("connection setuped");
				stmt = con.createStatement();
				// System.out.println("444444444444444444444444444444");
				String Query = "select ir_flag,report_id, REPORTNAME from tamsreportmaster "
						+ "where project = '" + project +"' "
								+ "and done_flag = 'Y' "
								+ " order by report_id"; 
					
			System.out.print(Query);
						 
				// System.out.println("3333"+Query);
				ResultSet rs = stmt.executeQuery(Query);
				// System.out.println("55555555555555555555555555"+rs);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setReporttype(rs.getString("reportname"));
					System.out.print("IR is ="+rs.getInt("ir_flag"));
					bean.setIr_flag(rs.getInt("ir_flag"));
					reportlist.add(bean);
				}

				System.out.println("list elements" + "  " + reportlist);
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");

		}

		return (ArrayList<tamsbean>) reportlist;
	}

	public ArrayList<tamsbean> getrlyDetail(int ir_flag , String zone_name) throws Exception {
		List<tamsbean> reportrlylist = new ArrayList<tamsbean>();
		System.out.println(" zone name " + zone_name);
		if(zone_name.equalsIgnoreCase("IR")){
		
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				stmt = con.createStatement();
				String Query = "select orgrly from tamsorgrlymaster where ir_flag = "+ ir_flag+" order by orgrly";
				System.out.println(Query);
				ResultSet rs = stmt.executeQuery(Query);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setZone(rs.getString("orgrly"));
					reportrlylist.add(bean);
				}

				//System.out.println("list elements" + "  " + reportrlylist);
				con.close();
			}
		}
		
	
		catch (Exception ex) {
			System.out.println("list not print");

		}

		return (ArrayList<tamsbean>) reportrlylist;
		
		}
		
		else
		{	
			if(ir_flag ==0)
				{
				System.out.println(" zone name " + zone_name);
				bean.setZone(zone_name);
				reportrlylist.add(bean);
				return (ArrayList<tamsbean>) reportrlylist;
				}
			else			{
			System.out.println(" zone name " + zone_name);
			bean.setZone("IR");
			reportrlylist.add(bean);
			return (ArrayList<tamsbean>) reportrlylist;
			}
		}
	}
	
public int	getIR(String reportname){
	int ir=0;
	try {
		dbcon = new tamsdbconnection();
		con = dbcon.getconnect();
		
		if (con != null) {
			stmt = con.createStatement();
			String Query = "select IR_FLAG from tamsreportmaster where Reportname='"+reportname+"'";
			System.out.println(Query);
			ResultSet rs = stmt.executeQuery(Query);
			while (rs.next()) {
				ir=rs.getInt("IR_FLAG");
				/*bean = new tamsbean();
				bean.setZone(rs.getString("IR_flag"));
				*/
			}

			//System.out.println("list elements" + "  " + reportrlylist);
			con.close();
		}
	}
	

	catch (Exception ex) {
		System.out.println("list not print");

	}

	
	
	
	return ir;
	}

	
	
	
	
	
	public ArrayList<tamsbean> getirDetail() throws Exception {
		List<tamsbean> reportrlylist = new ArrayList<tamsbean>();
		System.out.println("inside trirtirtiirtritirtri ");
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				stmt = con.createStatement();
				String Query = "select orgrly from taedapprprallreport  where  REPORTNAME ='PRS CATERING CHARGES APPORTIONMENT REPORT'";
				ResultSet rs = stmt.executeQuery(Query);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setZone(rs.getString("orgrly"));
					reportrlylist.add(bean);
				}
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");

		}

		return (ArrayList<tamsbean>) reportrlylist;
	}

	public ArrayList<tamsbean> getconvertreport() throws Exception {

		System.out.println("Arrived at getconvertreport method");
		List<tamsbean> rptconvertlist = new ArrayList<tamsbean>();
		try {
			dbcon = new tamsdbconnection();
			con = dbcon.getconnect();
			if (con != null) {
				System.out.println("connection setuped");
				stmt = con.createStatement();
				// System.out.println("444444444444444444444444444444");
				String Query = "select reportfarmat from taedapprprallreport";
				// System.out.println("3333"+Query);
				ResultSet rs = stmt.executeQuery(Query);
				// System.out.println("55555555555555555555555555"+rs);
				while (rs.next()) {
					bean = new tamsbean();
					bean.setReportconverttype(rs.getString("reportfarmat"));
					rptconvertlist.add(bean);
				}

				System.out.println("list elements" + "  " + rptconvertlist);
				con.close();
			}
		}

		catch (Exception ex) {
			System.out.println("list not print");

		}

		return (ArrayList<tamsbean>) rptconvertlist;
	}
}
