package rripp.pocketmd.userinterface.dialogs;

import rripp.pocketmd.R;
import rripp.pocketmd.userinterface.MainActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class NoInstanceToSend extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.no_instance_to_send_dialog)
               .setPositiveButton(R.string.ok_btn, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   //Go to the insert data fragment so user can enter parameters of the patient
                	   MainActivity.getViewPager().setCurrentItem(0);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
