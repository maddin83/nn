package de.neuronal.stock.controller;

import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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
public class NeuronalController {
	
	private NeuralNetwork myMlPerceptron = new NeuralNetwork() ;
	
	@RequestMapping("/initiate")
    public int initiate() {
		
		NeuronProperties neuronProperties = new NeuronProperties();
	    neuronProperties.setProperty("transferFunction", TransferFunctionType.SIGMOID);

		// create multi layer perceptron
	    myMlPerceptron.setNetworkType(NeuralNetworkType.MULTI_LAYER_PERCEPTRON);

	    // create input layer
	    Layer inputLayer = LayerFactory.createLayer(2, neuronProperties);
	    inputLayer.addNeuron(new BiasNeuron());
	    myMlPerceptron.addLayer(inputLayer);
	    
	    // create hidden layer 1
	    Layer hiddenLayer1 = LayerFactory.createLayer(9, neuronProperties);
	    hiddenLayer1.addNeuron(new BiasNeuron());
	    myMlPerceptron.addLayer(hiddenLayer1);

	    // create full conectivity between input and hidden layer 1
	    ConnectionFactory.fullConnect(inputLayer, hiddenLayer1);
	 // createLayer output layer
	    Layer outputLayer = LayerFactory.createLayer(1, neuronProperties);
	    myMlPerceptron.addLayer(outputLayer);

	    // create full conectivity between input and hidden layer 2
	    ConnectionFactory.fullConnect(hiddenLayer1, outputLayer);

	    // set input and output cells for network
	    NeuralNetworkFactory.setDefaultIO(myMlPerceptron);

	    int maxIterations = 1000000;

	    double learningRate = 0.2;

	    double maxError = 0.000001;
	    double momentum = 0.7;

	    MomentumBackpropagation learningRule = new MomentumBackpropagation();
	    myMlPerceptron.setLearningRule(learningRule);

	    learningRule.setMomentum(momentum);
	    learningRule.setMaxError(maxError);
	    learningRule.setLearningRate(learningRate);
	    learningRule.setMaxIterations(maxIterations);

	    learningRule.addListener(new LearningEventListener() {
	        public void handleLearningEvent(LearningEvent learningEvent){
	            MomentumBackpropagation rule = (MomentumBackpropagation) learningEvent.getSource();

	        }
	    });

	    return myMlPerceptron.getLayersCount();  }
	
	@RequestMapping("/test")
    public double[] test(@RequestParam(value="in1", defaultValue="1") int in1, @RequestParam(value="in2", defaultValue="1") int in2) {
		//myMlPerceptron.setInput();
		myMlPerceptron.calculate();
		return myMlPerceptron.getOutput();
	}
	
	/**
	 * asks Yahoo for stock values of last 5 years and prints it in a String 
	 * @return
	 */
	@RequestMapping("/callYahoo")
	public String callYahoo(){
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			List<HistoricalQuote> historicalQuotes = null;
			Calendar timeFrom = Calendar.getInstance();
			timeFrom.add(Calendar.YEAR, -5);
			Stock stock = YahooFinance.get("^GDAXI", timeFrom, Interval.DAILY);
			historicalQuotes = stock.getHistory();
			stringBuilder.append(historicalQuotes.toString());
			for(HistoricalQuote quote : historicalQuotes){
			    stringBuilder.append(quote.toString() + ";"+ System.lineSeparator() + "x");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return stringBuilder.toString();
		}
}
