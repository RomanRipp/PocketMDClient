package rripp.pocketmd.userinterface;

//import com.android.pocketmd.R;
import java.util.Map;
import java.util.Map.Entry;
import rripp.pocketmd.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
* A results fragment representing a section of the app, displays result of disfunction calculations.
*/
    
public class ResultDisplayFragment extends Fragment {
  
	public static final String ARG_SECTION_NUMBER = "section_number";
	private static View rootView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_result, container, false);
        return rootView;
    }
    
    public static void setPredictedValues(){
    	//Here we draw pretty bar chart
    	//First we start from getting the classes and probabilities: 
    	Map<String, Integer> classes = MainActivity.getPredictor().getPredictions();
    	Map<String, Double> probabilities = MainActivity.getPredictor().getProbablities();
        
        //Here we filling the table with predicted values
        ResultDisplayFragment.fillTable(classes);
        ResultDisplayFragment.drawChart(probabilities, classes);
    }
    /**
     * Populate table with values
     * @param classes
     */
    private static void fillTable(Map<String, Integer> classes) {
        for (Entry<String, Integer> entry : classes.entrySet()){
        	String tag = entry.getKey();
        	try{
        		//Getting the class value
        		Integer v = entry.getValue();
        		TextView valText = (TextView) rootView.findViewWithTag(tag+"_val");
        		if (v != null){
        			if (v == 1){
        				valText.setText(R.string.yes_lbl);
        			} else {
        				valText.setText(R.string.no_lbl);
        			}
        		} else {
        			valText.setText("N/A");
        		}
        	} catch (NullPointerException e){
        		Log.e("DisplayPredictions", "The view with tag "+tag+"not found");
        	}
        }		
	}
    /**
     * draw a chart
     * @param probabilities
     */
	private static void drawChart(Map<String, Double> probabilities, Map<String, Integer> classes){
		int scale = rootView.findViewWithTag("scaleSize").getWidth();
        for (Entry<String, Double> entry : probabilities.entrySet()){
        	String tag = entry.getKey();
        	try{
        		//getting the probability of the class
        		Double p = entry.getValue();
        		TextView graphBar = (TextView) rootView.findViewWithTag(tag+"_bar");
        		//TextView probText = (TextView) rootView.findViewWithTag(tag+"_prob");
        		if (p != null){
        			double c = classes.get(entry.getKey());
        			if (c == 0.0){
        				p = 1.0 - p;
        			}
        			int bar = (int) (scale*(p));
        			graphBar.getLayoutParams().width = bar;
        			//TODO add animation
        			p *= 100;
        			//set bar labels
        			graphBar.setText(p.intValue()+"%");
        		} else {
        			graphBar.getLayoutParams().width = 10;
        			graphBar.setText("N/A");
        		}
        	} catch (NullPointerException e){
        		Log.e("DisplayPredictions", "The view with tag "+tag+"not found");
        	}
        }
    }
}
