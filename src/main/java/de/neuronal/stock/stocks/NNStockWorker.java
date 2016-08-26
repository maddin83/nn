package de.neuronal.stock.stocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.neuronal.stock.entity.NNStockValue;
import de.neuronal.stock.entity.NNStockValueFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class NNStockWorker {

	private final String[] stockNames = { "^GDAXI", "^IXIC", "GLD", "BNO" };

	/**
	 * fetches Stock values from providers in internet
	 * 
	 * @return
	 */
	public List<NNStockValue> getHistoricalStockValues(int years) {
		// Todo possible calls to other providers than Yahoo go here
		return callYahoo(years);
	}

	/**
	 * YAHOO
	 * 
	 * @return
	 */
	private List<NNStockValue> callYahoo(int years) {
		Map<String, Stock> stockMap;
		List<NNStockValue> stockValues = new ArrayList<NNStockValue>();
		try {
			Calendar timeFrom = Calendar.getInstance();
			timeFrom.add(Calendar.YEAR, -years);
			stockMap = YahooFinance.get(stockNames, timeFrom, Interval.DAILY);

			Set<Map.Entry<String, Stock>> entries = stockMap.entrySet();
			for (Map.Entry<String, Stock> entry : entries) {
				Stock stock = entry.getValue();
				List<HistoricalQuote> histories = stock.getHistory();
				stockValues.addAll(histories.stream().map(historicalQuote -> NNStockValueFactory.buildFromYahooStock(stock, historicalQuote)).collect(Collectors.toList()));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stockValues;
	}
}
