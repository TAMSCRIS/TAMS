package tamsdbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class tamsdbconnection {

	Statement stmt;
	Connection con = null;
	public  Connection getconnect() throws Exception {

		try {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//10.30.4.153:1521/tamsdb","tams","tams");
			System.out.println("connection obtained");
           if (con != null) {
				
				System.out.println("Connection sucess");
			}
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("connection obtained check");
        
		}

		return  con;
	}
	

}


