package rripp.pocketmd.userinterface.dialogs;

import rripp.pocketmd.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class CalculateGlasgow extends DialogFragment {
	
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface CalculateGlasgowListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    
    // Use this instance of the interface to deliver action events
    CalculateGlasgowListener mListener;
    
    // Override the Fragment.onAttach() method to instantiate the CalculateGlasgowListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the CalculateGlasgowListener so we can send events to the host
            mListener = (CalculateGlasgowListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement CalculateGlasgowListener");
        }
    }
	
	@Override 
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.use_calculator_dialog).setPositiveButton(R.string.yes_lbl, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// User have chosen to use the calculator, lets display the glasgow calculator now
				mListener.onDialogPositiveClick(CalculateGlasgow.this);
			}
		}).setNegativeButton(R.string.no_lbl, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// User decided to input Glasgow coma scale value manually
				mListener.onDialogNegativeClick(CalculateGlasgow.this);
			}
		});
		return builder.create();
	}
}
