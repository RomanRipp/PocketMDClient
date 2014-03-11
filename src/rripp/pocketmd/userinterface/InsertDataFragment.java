package rripp.pocketmd.userinterface;

import java.util.HashMap;
import java.util.Map;

import rripp.pocketmd.R;
import rripp.pocketmd.client.datahendler.Deserialization;
import rripp.pocketmd.userinterface.dialogs.CalculateGlasgow;
import rripp.pocketmd.userinterface.dialogs.NoInputsProvided;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A data insertion fragment representing a section of the app, displays input parameters.
 */

public class InsertDataFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private static View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	rootView = inflater.inflate(R.layout.fragment_input_data, container, false);
    	
    	//Calculates index shock if pulse and sad is entered
    	final View indShEditText = rootView.findViewById(R.id.IndSh1_Text);
    	indShEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText indSh = (EditText)v;
				EditText sad = (EditText) rootView.findViewById(R.id.SAD1_Text);
				String sadText = sad.getText().toString();
				EditText pulse = (EditText) rootView.findViewById(R.id.PULSE1_Text);
				String pulseText = pulse.toString();
				if (sadText.trim().length() + pulseText.trim().length() > 0){
					Double indShText = Double.parseDouble(sadText) / Double.parseDouble(sadText);
					indSh.setText(indShText.toString());
				}
			}
		});

    	
    	// If MVP is selected the respiratory rate should be inactive
    	final View mvpCheckBox = rootView.findViewById(R.id.mvpCheckBox);
    	mvpCheckBox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox)v;
				EditText respEditText = (EditText) rootView.findViewById(R.id.RESP1_Text);
				// make respiratory text edit inactive if mvp is clicked
				if (checkBox.isChecked()){
					respEditText.setVisibility(View.GONE);
				}else{
					respEditText.setVisibility(View.VISIBLE);
				}
			}
		});

    	
    	// Glasgow scale can be calculated using dialogues. 
    	View glasgoTextEdit = rootView.findViewById(R.id.GLASGO1_Text);
    	glasgoTextEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Let user decide how he wants to enter the glasgow coma scale using dialogues
				DialogFragment dialog = new CalculateGlasgow();
				dialog.show(getActivity().getSupportFragmentManager(), "Open GCS calculator?");
			}
		});
    	
    	// Go to next fragment with button
        View nextButton = rootView.findViewById(R.id.btn_Next);
    	nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    	compute();
                    }
                });
        return rootView;
    }
    
    @Override
    public void onPause() {
    	System.out.println("Pause");
    	super.onPause();
    }
    
    @SuppressLint("UseSparseArrays")
	public static Map<Integer, Double> readInputs(){

    	Map<Integer, Double> instance = new HashMap<Integer, Double>();
    	//Reading input values from input activity
    	for (int key : Deserialization.getParamIndexes()){
    		String paramNames = Deserialization.getParamNames()[key];
    		//mvp is a check box so we skip through
    		if (paramNames != "MVP1"){
    			EditText editText = (EditText) rootView.findViewWithTag(paramNames);
    			String val = editText.getText().toString();
    			if (!("").equals(val)){
    				Double value = Double.parseDouble(val);
    				//All values has their limits
    				double maximum = Deserialization.getParamMax()[key];
    				double minimum = Deserialization.getParamMin()[key];
    				if (value > maximum || value < minimum){
    					value = limitValue(value, minimum, maximum);
    					//Set proper limited value back to text edit
    					editText.setText(value.toString());
    				}
    				instance.put(key, value);
    			}
    		} else {
    	    	CheckBox mvpBox = (CheckBox) rootView.findViewWithTag("MVP1");
    	    	if (mvpBox.isChecked()){
    	    		instance.put(key, 1.0);
    	    	}else{
    	    		instance.put(key, 0.0);
    	    	}
    	    	key++;
    		}
    	}
    	return instance;
    }
    
    /**
     * The values are limited by minimum and maximum possible values 
     * @param v the entered value
     * @param min the minimum possible 
     * @param max the maximum possible
     * @return limited value
     */
    private static double limitValue(double v, double min, double max){
    	if (v > max){
    		v = max;
    	}
    	if (v < min) {
    		v = min;
    	}
    	return v;
    }
    /**
     * If user did not enter the input values 
     * we populate them so we can compute demo      
     * The values comes from our database
     */
    public static void PopulateInputsForDemo(){
    	//{ "TIMEPOST", "PULSE1", "IndSh1", "DAD1", "SAD1", "ShockTime", "TEMP1", "MVP1", "RESP1", "GLUC1", "GLASGO1", "AGE"}
    	//{0=1.0, 1=110.0, 2=0.92, 3=70.0, 4=120.0, 6=36.6, 7=1.0, 8=0.0, 9=9.4, 10=14.0, 11=20.0}
    	EditText t = (EditText) rootView.findViewWithTag("TIMEPOST");
    	t.setText("1.0");
    	t = ((EditText) rootView.findViewWithTag("PULSE1"));
    	t.setText("110.0");
    	t = (EditText) rootView.findViewWithTag("IndSh1");
    	t.setText("0.92");
    	t = (EditText) rootView.findViewWithTag("DAD1");
    	t.setText("70.0");
    	t = (EditText) rootView.findViewWithTag("SAD1");
    	t.setText("120.0");
    	//((EditText) rootView.findViewWithTag("ShockTime")).setText(""); //this one is missing in DB :(
    	t = (EditText) rootView.findViewWithTag("TEMP1");
    	t.setText("36.6");
    	CheckBox c = (CheckBox) rootView.findViewWithTag("MVP1");
    	c.setChecked(!c.isChecked());
    	t = (EditText) rootView.findViewWithTag("RESP1");
    	t.setText("0.0");
    	t = (EditText) rootView.findViewWithTag("GLUC1");
    	t.setText("9.4");
    	t = (EditText) rootView.findViewWithTag("GLASGO1");
    	t.setText("14.0");
    	t = (EditText) rootView.findViewWithTag("AGE");
    	t.setText("20.0");
    }
    
    public void compute(){
    	//Read the patient parameters
    	Map<Integer, Double> instance = readInputs();
    	
    	if (instance.size()==1){
    		
    		DialogFragment dialog = new NoInputsProvided();
    		dialog.show(getActivity().getSupportFragmentManager(), "NoInputsBebe");
    		//If user decided to see the demo populate input fields
    		
    	} else {
    		//Run the classification: 
    		MainActivity.getPredictor().doTheMachineLearningMagic(instance);
    		//Set the values
    		ResultDisplayFragment.setPredictedValues();
    		//Hide the keyboard
    		final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    		imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    		//go to the results fragment: 
    		MainActivity.getViewPager().setCurrentItem(1);
    		//Give the values for contribution: (may be not a good idea)
    		//DataTransmition.setInstance(instance);
    	}
    }
}


