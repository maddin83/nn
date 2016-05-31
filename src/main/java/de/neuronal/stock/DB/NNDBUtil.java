package de.neuronal.stock.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class NNDBUtil {
    
    public static Connection getH2Connection() {
	    try {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/nn");
		return conn;
	    } catch (ClassNotFoundException | SQLException e) {
		//TODO
		e.printStackTrace();
	    }
	    return null; 
	}

    private static void closeH2Connection (Connection conn) {
	if (conn != null){
	    try {
		conn.close();
	    } catch (SQLException e) {
		//TOOD
		e.printStackTrace();
	    }	    
	}
    }
    
    public static void initH2DB(){
	Connection conn = getH2Connection();
	try {
	    Statement statement= conn.createStatement();
	    String sql = "CREATE TABLE IF NOT EXISTS STOCKS "
	    	+ "(ID INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
	    	+ "NAME VARCHAR(255),"
	    	+ "STOCKVALUE DECIMAL)";
	    statement.executeUpdate(sql);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	closeH2Connection(conn);
    }

    public static String getDaxValues() {
	Connection h2Connection = getH2Connection();
	Statement statement;
	String resultString = null;
	String sql = "SELECT * FROM STOCKS";
	try {
	    statement = h2Connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while (result.next()){
		    resultString += result.getDouble("STOCKVALUE") + "; ";
		}
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return resultString;
    }
}
