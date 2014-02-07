package rripp.pocketmd.client.machinelearning;

import java.util.HashMap;
import java.util.Map;

import rripp.pocketmd.client.datahendler.Deserialization;

import android.util.Log;

public class PredictorEngine {

    private static Map<String, Integer> predictions;
    private static Map<String, Double> probabilities;
    private static MachineLearningClassification[] models;
	
	public PredictorEngine(){
		predictions = new HashMap<String, Integer>();
		probabilities = new HashMap<String, Double>();
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
	 * Here I run the prediction for all the outputs specified in the output names array
	 * @param instance
	 */
	public void doTheMachineLearningMagic(Map<Integer, Double> instance){
		//Setting the instance values for classifacation
		SupportVectorMachine.setInstance(instance);
		int i = 0;
		for (String s : Deserialization.getOutNames()){			
			System.out.println("Classifying "+s);
			models[i].Predict();
			predictions.put(s, (Integer) models[i].getClassification());
			probabilities.put(s, models[i].getClassProbability());//System.out.println(models[i].getClassProbability());
			i++;
		}
    	//Log.d("predictor","All Calssified:  "+predictions);
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
