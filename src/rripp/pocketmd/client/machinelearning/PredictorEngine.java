package rripp.pocketmd.client.machinelearning;

import java.util.HashMap;
import java.util.Map;

import rripp.pocketmd.client.datahendler.Deserialization;

import android.util.Log;

public class PredictorEngine {
	
	/**
	 * The models are derived from data my server side application and
	 * later passed ass assets
	 */
	private static MachineLearningClassification[] models;
	/**
	 * predicted complications of the disease 
	 */
    private static Map<String, Integer> predictions;
    /**
     * probability value associated with a prediction
     */
    private static Map<String, Double> probabilities;
    /**
     * Constructor for prediction engine
     * this class handles classification problem 
     * by means of applying support vector machine 
     * algorithm to data
     */
	public PredictorEngine(){
		predictions = new HashMap<String, Integer>();
		probabilities = new HashMap<String, Double>();
		//We obtain models by deserializing them. 
		String[] outputnames = Deserialization.getOutNames();
		models = new MachineLearningClassification[outputnames.length];
		int i = 0;
		for (String s : outputnames) {
			Log.d("PredictorEngine", "Initializing the model "+s);
			models[i++] = new SupportVectorMachine(s);
			Log.d("PredictorEngine", s+" initialized ");
		}
	}
	/**
	 * This is the core of the program, this method 
	 * handles classification problem 
	 * for inpurs in instance. 
	 * @param instance
	 */
	public void doTheMachineLearningMagic(Map<Integer, Double> instance){
		//Setting the instance values for classification
		SupportVectorMachine.setInstance(instance);
		int i = 0;
		//Prediction phase for all outputs
		for (String s : Deserialization.getOutNames()){			
			System.out.println("Classifying "+s);
			models[i].Predict();
			predictions.put(s, (Integer) models[i].getClassification());
			probabilities.put(s, models[i].getClassProbability());
			i++;
		}
    	Log.d("predictorClass","All Calssified:  "+predictions);
	}
	/**
	 * return predicted values:
	 * @return
	 */
	public Map<String, Integer> getPredictions(){
		return predictions;
	}
	/**
	 * return probabilities:
	 * @return
	 */
	public Map<String, Double> getProbablities(){
		return probabilities;
	}

}
