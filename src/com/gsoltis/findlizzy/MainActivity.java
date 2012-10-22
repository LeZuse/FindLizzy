package com.gsoltis.findlizzy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.gsoltis.trailblazer.LocationService;

public class MainActivity extends PreferenceActivity 
		implements OnSharedPreferenceChangeListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.service_prefs);
		SharedPreferences prefs = getPreferenceScreen().getSharedPreferences();
		boolean run = prefs.getBoolean("run_service", false);
		setService(run);
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals("run_service")) {
			boolean run = sharedPreferences.getBoolean("run_service", false);
			setService(run);
		}
	}

	private void setService(boolean start) {
		Intent i = new Intent(getApplicationContext(), LocationService.class);
		if (start) {
			Bundle extras = new Bundle();
			extras.putString(LocationService.FIREBASE_REF, 
					"http://demo.firebase.com/trailblazer/location/lizzy.json");
			extras.putString(LocationService.NOTIFICATION_STARTUP, 
					getText(R.string.location_service).toString());
			extras.putString(LocationService.NOTIFICATION_TEXT, 
					getText(R.string.location_service_name).toString());
			extras.putInt(LocationService.NOTIFICATION_ICON, 
					R.drawable.satellite_32);
			extras.putString(LocationService.NOTIFICATION_ACTIVITY, 
					this.getClass().getCanonicalName());
			i.putExtras(extras);
			startService(i);
		} else {
			stopService(i);
		}
	}
}
