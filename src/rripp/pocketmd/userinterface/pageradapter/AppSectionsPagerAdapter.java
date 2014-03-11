package rripp.pocketmd.userinterface.pageradapter;

import rripp.pocketmd.R;
//import rripp.pocketmd.userinterface.ContributeDataFragment;
import rripp.pocketmd.userinterface.InsertDataFragment;
import rripp.pocketmd.userinterface.ResultDisplayFragment;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    public AppSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
            	//evoking data insertion fragment
            	return new InsertDataFragment();
            case 1:
            	//evoking result fragment
            	return new ResultDisplayFragment();
//            case 2:
            	//evoking result fragment
//            	return new ContributeDataFragment();

            default:
            	return null;
        }
    }

    @Override
    public int getCount() {
//        return 3;
        return 2;
    }
    
    public CharSequence getPageTitle(int position, Context context) {
    	switch (position){
    		case 0: return context.getText(R.string.input_fragment_name);
    		case 1: return context.getText(R.string.result_fragment_name);
//    		case 2: return context.getText(R.string.contribute_fragment_name);
    		default: return null;
    	}
    }
}
