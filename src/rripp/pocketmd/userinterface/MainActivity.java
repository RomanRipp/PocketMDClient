package rripp.pocketmd.userinterface;


import rripp.pocketmd.R;
import rripp.pocketmd.client.machinelearning.PredictorEngine;
import rripp.pocketmd.userinterface.dialogs.CalculateGlasgow;
import rripp.pocketmd.userinterface.dialogs.GlasgowComaScorer;
import rripp.pocketmd.userinterface.pageradapter.AppSectionsPagerAdapter;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

public class MainActivity extends FragmentActivity 
							implements ActionBar.TabListener,
								 CalculateGlasgow.CalculateGlasgowListener,
								 GlasgowComaScorer.GlasgowComaScorerListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    static ViewPager mViewPager;
    
    /**
     * The machine learning models for prediction
     */
    static PredictorEngine predictor;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i, this))
                            .setTabListener(this));
        }
        
        //get the machine learning models:
        predictor = new PredictorEngine();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static PredictorEngine getPredictor(){
    	return predictor;
    }
    /**
     * this is for changing tabs 
     * @return
     */
    public static ViewPager getViewPager(){
    	return mViewPager;
    }
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		//If user have chosen the calculator do not let him type in the Glasgo score
		EditText glasgoTextEdit = (EditText) findViewById(R.id.GLASGO1_Text);
		glasgoTextEdit.setFocusableInTouchMode(false);	
		glasgoTextEdit.setFocusable(false);
		
		//Now lets open glasgow come scale calculator dialog 
		DialogFragment glasgowdialog = new GlasgowComaScorer();
		glasgowdialog.show(getSupportFragmentManager(), "Open glasgow calculator");
	}
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		//If user have chosen not to use the calculator let him type in the Glasgo score
		EditText glasgowTextEdit = (EditText) findViewById(R.id.GLASGO1_Text);
		glasgowTextEdit.setFocusableInTouchMode(true);
		glasgowTextEdit.setFocusable(true);
	}

	@Override
	public void onDialogOkClick(Integer s) {
		// Write in the glasgow score from calculator
		EditText glasgowTextEdit = (EditText) findViewById(R.id.GLASGO1_Text);
		glasgowTextEdit.setText(s.toString());
	}
}
