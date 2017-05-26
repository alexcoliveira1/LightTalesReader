package com.alexcoliveira1.lighttalesreader;

import android.app.Application;

import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;

/**
 * Created by alex on 18/04/17.
 */

public class ReaderApplication extends Application{

	// Called when the application is starting, before any other application objects have been created.
	// Overriding this method is totally optional!
	@Override
	public void onCreate() {
	    super.onCreate();
        Fabric.with(this, new Crashlytics());
	}


	// This is called when the overall system is running low on memory, 
	// and would like actively running processes to tighten their belts.
	// Overriding this method is totally optional!
	@Override
	public void onLowMemory() {
	    super.onLowMemory();
	}
}
