package rripp.pocketmd.userinterface.dialogs;


import rripp.pocketmd.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class GlasgowComaScorer extends DialogFragment{
	
	private OnItemSelectedListener listener1;
	private OnItemSelectedListener listener2;
	private OnItemSelectedListener listener3;
	
	public interface GlasgowComaScorerListener {
		public void onDialogOkClick(Integer s);
	}
	
    // Use this instance of the interface to deliver action events
	GlasgowComaScorerListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the CalculateGlasgowListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the CalculateGlasgowListener so we can send events to the host
            mListener = (GlasgowComaScorerListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement GlasgowComaScorerListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		//inflate the dialog, Yep this is how awesome I am!
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_gcscalculator, null);
		builder.setTitle(R.string.glasgo_calculator_dialog)
				.setView(dialogView)
				.setPositiveButton(R.string.ok_btn, new DialogInterface.OnClickListener() {
									
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int score = ((SpinnerListener) listener1).getPosition();
					score += ((SpinnerListener) listener2).getPosition();
					score += ((SpinnerListener) listener3).getPosition();
					mListener.onDialogOkClick(score);
				}
			});
		
		/**
		 * The eye opening spinner
		 */
		Spinner spinner1 = (Spinner) dialogView.findViewById(R.id.spinner1);
		listener1 = new SpinnerListener();
		spinner1.setOnItemSelectedListener(listener1);
		
		/**
		 * The motor response spinner
		 */
		Spinner spinner2 = (Spinner) dialogView.findViewById(R.id.spinner2);
		listener2 = new SpinnerListener();
		spinner2.setOnItemSelectedListener(listener2);
		
		/**
		 * The voice response
		 */
		Spinner spinner3 = (Spinner) dialogView.findViewById(R.id.spinner3);
		listener3 = new SpinnerListener();
		spinner3.setOnItemSelectedListener(listener3);
		
		return builder.create();
	}
}
