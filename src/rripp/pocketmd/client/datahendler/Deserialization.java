package rripp.pocketmd.client.datahendler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import rripp.pocketmd.app.PocketMDApp;

public class Deserialization {
	/**
	 * Constructor
	 * @param c - context
	 */
	//TODO This needs to come from server in a file
	private static String[] paramNames = { "TIMEPOST", "PULSE1", "IndSh1", "DAD1", "SAD1", "ShockTime", "TEMP1", "MVP1", "RESP1", "GLUC1", "GLASGO1", "AGE"};
	private static Integer[] paramIndexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	private static Double[] paramMin = { 0.0, 0.0, 0.4, 0.0, 0.0, 0.0, 32.0, 0.0, 0.0, 0.0, 3.0, 0.0};
	private static Double[] paramMax = { 107.0, 200.0, 1.5, 200.0, 250.0, 107.0, 42.0, 1.0, 50.0, 311.1, 15.0, 100.0};
	private static String[] outputnames = {"ISH", "OSL1", "OSL3" ,"OSL5", "OSL7", "OSL9", "OSL10"};

	public static String[] getParamNames(){
		return paramNames;
	}

	public static Integer[] getParamIndexes(){
		return paramIndexes;
	}

	public static Double[] getParamMin(){
		return paramMin;
	}

	public static Double[] getParamMax(){
		return paramMax;
	}

	public static String[] getOutNames(){
		return outputnames;
	}
	
	public Deserialization(){
	}
	/**
	 * getting the machine learning model from serialized type
	 * @return
	 */
	public static Object deserialize (String modelName){
		Object object = null;
		String filePath = "serializedTypes/"+modelName+".model";
		try
	      {
	         InputStream fileIn = PocketMDApp.getContext().getAssets().open(filePath);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         object = in.readObject();
	         in.close();
	         fileIn.close();
	      } catch(IOException i) {
	         i.printStackTrace();
	      } catch(ClassNotFoundException c) {
	         System.out.println(modelName+" class not found");
	         c.printStackTrace();
	      }
		return object;
	}
}
