package de.neuronal.stock.controller;

import de.neuronal.stock.DB.NNDBUtil;
import de.neuronal.stock.nets.NNNetBuilder;
import de.neuronal.stock.stockpull.NNStockPull;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NnController {

    private NNNetBuilder netBuilder = new NNNetBuilder();
    private NNStockPull stockPull =  new NNStockPull();
    private NNDBUtil dbUtil = new NNDBUtil(); //DB will be set up if it does not exist

	@RequestMapping("/initiate")
    public void initiate() {

        // set up the neuronal network
		netBuilder.init();

        // get the stock values
        Map<String, Stock> historicalStockValues = stockPull.getHistoricalStockValues();

        // save the stock values in DB
        dbUtil.storeStockValues(historicalStockValues);


	}



//	@RequestMapping("/test")
//    public double[] test(@RequestParam(value="in1", defaultValue="1") int in1, @RequestParam(value="in2", defaultValue="1") int in2) {
//		//myMlPerceptron.setInput();
//		myMlPerceptron.calculate();
//		return myMlPerceptron.getOutput();
//	}
	
	/**
	 * asks Yahoo for stock values of last 5 years and prints it in a String 
	 * @return
	 */
	//@RequestMapping("/callYahoo")

}
