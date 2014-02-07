package rripp.pocketmd.userinterface.dialogs;

import rripp.pocketmd.R;
import rripp.pocketmd.userinterface.InsertDataFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class NoInputsProvided extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.no_inputs_provided_dialog)
               .setPositiveButton(R.string.demo_btn, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   //User confirmed populate the fields with demo data
                	   InsertDataFragment.PopulateInputsForDemo();
                   }
               })
               .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        return builder.create();
    }
}