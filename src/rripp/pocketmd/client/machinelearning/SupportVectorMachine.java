package rripp.pocketmd.client.machinelearning;


import java.util.Map;

import libsvm.LibSVM;

import android.util.Log;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import rripp.pocketmd.client.datahendler.Deserialization;

public class SupportVectorMachine implements MachineLearningClassification {

	private final Classifier svm;
	private static Instance instance;
	private Integer classif;
	private Double classprob;
	
	public SupportVectorMachine(String target){
		Log.d("SVM","Deserializing the model...");
		classif = null;
		classprob = null;
    	svm = (Classifier) Deserialization.deserialize("libsvm.LibSVM." + target);
    	//instance = new SparseInstance();
    	Log.d("SVM","done");
	}
	
	public static void setInstance(Map<Integer, Double> map) {
		System.out.println("Setting the instance");
		instance = new SparseInstance();
		instance.putAll(map);
	}
	
	public void Predict(){
		classif = ((Double) svm.classify(instance)).intValue();
		classprob = ((LibSVM) svm).estimate(instance);
	}
	
	@Override
	public Integer getClassification() {
		return classif;
	}

	@Override
	public Double getClassProbability() {
		return classprob;
	}
}
