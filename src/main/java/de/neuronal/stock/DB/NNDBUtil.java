package de.neuronal.stock.DB;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NNDBUtil {
    private Connection conn;
    public NNDBUtil(){
        this.conn = getH2Connection();
        initH2DB();
	}
    
    private  Connection getH2Connection() {
	    try {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
		return conn;
	    } catch (ClassNotFoundException | SQLException e) {
		//TODO
		e.printStackTrace();
	    }
	    return null; 
	}

    private  void closeH2Connection (Connection conn) {
	if (conn != null){
	    try {
		conn.close();
	    } catch (SQLException e) {
		//TOOD
		e.printStackTrace();
	    }	    
	}
    }
    
    private void initH2DB(){

	try {
	    Statement statement= conn.createStatement();
	    String sql = "CREATE TABLE IF NOT EXISTS STOCKS "
	    	+ "(ID INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
	    	+ "NAME VARCHAR(255),"
	    	+ "xOPEN DECIMAL, " +
                "CLOSE DECIMAL, " +
                "HIGH DECIMAL, " +
                "LOW DECIMAL, " +
                "VOLUME DECIMAL," +
                "TIMEINMILLIS BIGINT)";
	    statement.executeUpdate(sql);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	//closeH2Connection(conn);
    }



    public  String getDaxValues() {
	Statement statement;
	String resultString = null;
	String sql = "SELECT * FROM STOCKS";
	try {
	    statement = conn.createStatement();
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

    public String getEntryNumber(){
        Statement statement;
        String resultString = null;
        String sql = "SELECT count(1) x FROM STOCKS";
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                resultString += result.getInt("x") + "; ";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultString;
    }

    public void storeStockValues(Map<String, Stock> historicalStockValues)  {
        Set<Map.Entry<String, Stock>> entries = historicalStockValues.entrySet();
        for (Map.Entry entry: entries) {
            Stock stock = (Stock) entry.getValue();
            try {
                List<HistoricalQuote> histories = stock.getHistory();
                for (HistoricalQuote historyQoute:histories) {
                    Statement statement = conn.createStatement();
                    statement.execute("INSERT INTO STOCKS (NAME, xOPEN, CLOSE, HIGH, LOW, VOLUME, TIMEINMILLIS) " +
                             "VALUES ('"+entry.getKey()+"',"
                            + historyQoute.getOpen() + ","
                            + historyQoute.getClose() + ","
                            + historyQoute.getHigh() + ","
                            + historyQoute.getLow() + ","
                            + historyQoute.getVolume() + ","
                            + "'" + historyQoute.getDate().getTimeInMillis()+ "'"
                            +")");
                    System.out.println("inserted " + entry.getKey()+":"+historyQoute.getOpen() + "," + historyQoute.getDate().getTime());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getEntryNumber());
    }
}
