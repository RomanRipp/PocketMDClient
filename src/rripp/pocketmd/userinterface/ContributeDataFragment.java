package rripp.pocketmd.userinterface;


import java.util.HashMap;
import java.util.Map;

import rripp.pocketmd.R;
import rripp.pocketmd.client.datahendler.DataTransmition;
import rripp.pocketmd.client.datahendler.Deserialization;
import rripp.pocketmd.userinterface.dialogs.NoInstanceToSend;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class ContributeDataFragment extends Fragment{
	
    private static View rootView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	rootView = inflater.inflate(R.layout.fragment_contribute_data, container, false);
    	
    	//Send data to server
        View button = rootView.findViewById(R.id.btn_contribute);
        button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//First we read the data from input fragment
				DataTransmition.setInstance(InsertDataFragment.readInputs());
				//Check if instance is not empty
				if (DataTransmition.getInstance().size() == 1){
					
            		DialogFragment dialog = new NoInstanceToSend();
            		dialog.show(getActivity().getSupportFragmentManager(), "NothingToSend");
					
				}else{
					//Now lets read the agraviations user entered
					System.out.println(readInputs());
					//File file = getActivity().getAssets().open();
					//new SendFile().execute();
					
				}
			}
        });
    	return rootView;
    }
    /**
     * reading the checkboxes
     */
    private Map<String, Boolean> readInputs(){
    	Map<String, Boolean> outputs = new HashMap<String, Boolean>();
    	for (String s : Deserialization.getOutNames()){
    		CheckBox outBox = (CheckBox) rootView.findViewWithTag(s+"_contribute");
    		outputs.put(s, outBox.isChecked());
    	}
    	return outputs;
    }
}
