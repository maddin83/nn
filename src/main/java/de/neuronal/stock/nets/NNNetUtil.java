package de.neuronal.stock.nets;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;

public class NNNetUtil {

	private final NeuralNetwork<MomentumBackpropagation> myMlPerceptron = new NeuralNetwork<MomentumBackpropagation>();

    public int init() {
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

	learningRule.addListener(learningEvent-> {MomentumBackpropagation rule = (MomentumBackpropagation) learningEvent.getSource();});

	return myMlPerceptron.getLayersCount();
    }

    public void trainNet(){}
}
