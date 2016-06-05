package de.neuronal.stock.stocks;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

/**
 * Created by martschmidt on 03.06.16.
 */
public class NNStockWorker {

    private String[] stockNames = { "^GDAXI", "^DJI", "^IXIC" };

    public Map<String, Stock> getHistoricalStockValues() {
	return callYahoo();
    }

    private Map<String, Stock> callYahoo() {
	Map<String, Stock> stockMap = null;
	try {
	    Calendar timeFrom = Calendar.getInstance();
	    timeFrom.add(Calendar.YEAR, -5);
	    stockMap = YahooFinance.get(stockNames, timeFrom, Interval.DAILY);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return stockMap;
    }
}
