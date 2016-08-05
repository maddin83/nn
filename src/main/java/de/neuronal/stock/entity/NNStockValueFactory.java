package de.neuronal.stock.entity;

import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

public class NNStockValueFactory {
	public static NNStockValue buildFromYahooStock(Stock stock, HistoricalQuote historicalQuote) {
		return new NNStockValue(stock.getName(), historicalQuote.getOpen(),
				historicalQuote.getClose(), historicalQuote.getHigh(), historicalQuote.getLow(),
				historicalQuote.getVolume(), historicalQuote.getDate().getTimeInMillis());
	}
}
