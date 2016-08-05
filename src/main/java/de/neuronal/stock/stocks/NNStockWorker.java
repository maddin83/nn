package de.neuronal.stock.stocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.neuronal.stock.entity.NNStockValue;
import de.neuronal.stock.entity.NNStockValueFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * Created by martschmidt on 03.06.16.
 */
public class NNStockWorker {

	private String[] stockNames = { "^GDAXI", "^DJI", "^IXIC", "GLD", "BNO" };

	/**
	 * fetches Stock values from providers in internet
	 * 
	 * @return
	 */
	public List<NNStockValue> getHistoricalStockValues() {
		// Todo possible calls to other providers than Yahoo go here
		return callYahoo();
	}

	/**
	 * YAHOO
	 * 
	 * @return
	 */
	private List<NNStockValue> callYahoo() {
		Map<String, Stock> stockMap = null;
		List<NNStockValue> stockValues = new ArrayList<NNStockValue>();
		try {
			Calendar timeFrom = Calendar.getInstance();
			timeFrom.add(Calendar.YEAR, -1);
			stockMap = YahooFinance.get(stockNames, timeFrom, Interval.DAILY);

			Set<Map.Entry<String, Stock>> entries = stockMap.entrySet();
			for (Map.Entry<String, Stock> entry : entries) {
				Stock stock = (Stock) entry.getValue();
				List<HistoricalQuote> histories = stock.getHistory();
				for (HistoricalQuote historicalQuote : histories) {
					stockValues
							.add(NNStockValueFactory.buildFromYahooStock(stock, historicalQuote));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stockValues;
	}
}
