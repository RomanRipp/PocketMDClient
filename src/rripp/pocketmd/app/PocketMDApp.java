package rripp.pocketmd.app;

import android.app.Application;
import android.content.Context;

public class PocketMDApp extends Application {
	
	private static PocketMDApp instance; 
	
	public static PocketMDApp getInstance(){
		return instance;
	}
	public static Context getContext(){
		return instance.getApplicationContext();
	}
	
	@Override
	public void onCreate() {
		instance = this;
		super.onCreate();
	}
}
