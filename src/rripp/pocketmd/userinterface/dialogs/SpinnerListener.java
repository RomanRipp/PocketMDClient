package rripp.pocketmd.userinterface.dialogs;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerListener implements OnItemSelectedListener {
	
	private int position = 0;

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		//The user selected Glasgow criteria
		position = pos;
		if (parent.getItemAtPosition(pos).equals("Intubated")){
			position = 0;
		}
		System.out.println("Position: "+position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// No selection happened
		position = 0;
	}
	
	public int getPosition(){
		return position;
	}

}
