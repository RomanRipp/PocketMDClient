package rripp.pocketmd.client.datahendler;

import java.util.Map;
import java.util.Map.Entry;

import android.util.SparseArray;

public class DataTransmition {
	
	private static SparseArray<Double> instance;
	
	public  DataTransmition(){
	}
	/**
	 * retrieve parameters of the patient
	 * @return 
	 */
	public static SparseArray<Double> getInstance(){
		return instance;
	}
	/**
	 * this is the parameters of the patient we are willing to submit on a server
	 * @param p ihe instance itself
	 */
	public static void setInstance(Map<Integer, Double> p){
		instance = new SparseArray<Double>();
		for (Entry<Integer, Double> e : p.entrySet()){
			instance.put(e.getKey(), e.getValue());
		}
    }
}
