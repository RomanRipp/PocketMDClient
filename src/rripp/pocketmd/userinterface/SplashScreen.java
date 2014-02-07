package rripp.pocketmd.userinterface;

import rripp.pocketmd.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends Activity {
	
	private static int SPLASH_TIMEOUT = 1000;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//setContentView(GifView(this));
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run(){
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				finish();
			}
		}, SPLASH_TIMEOUT);
	}
}
