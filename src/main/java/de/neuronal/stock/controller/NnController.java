package de.neuronal.stock.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.neuronal.stock.DB.NNDBUtil;
import de.neuronal.stock.nets.NNNetBuilder;
import de.neuronal.stock.stocks.NNStockWorker;
import yahoofinance.Stock;

@RestController
public class NnController {

    private NNNetBuilder netBuilder = new NNNetBuilder();
    private NNStockWorker stockWorker = new NNStockWorker();
    private NNDBUtil dbUtil = new NNDBUtil(); // DB will be set up if it does
					      // not exist

    @RequestMapping("/initiate")
    public void initiate() {

	// set up the neuronal network
	netBuilder.init();

	// get the stock values
	Map<String, Stock> historicalStockValues = stockWorker.getHistoricalStockValues();

	// save the stock values in DB
	dbUtil.storeStockValues(historicalStockValues);

	// normalize the stock values
	dbUtil.normalizeStockData();

    }

    // @RequestMapping("/test")
    // public double[] test(@RequestParam(value="in1", defaultValue="1") int
    // in1, @RequestParam(value="in2", defaultValue="1") int in2) {
    // //myMlPerceptron.setInput();
    // myMlPerceptron.calculate();
    // return myMlPerceptron.getOutput();
    // }

    /**
     * asks Yahoo for stock values of last 5 years and prints it in a String
     * 
     * @return
     */
    // @RequestMapping("/callYahoo")

}
