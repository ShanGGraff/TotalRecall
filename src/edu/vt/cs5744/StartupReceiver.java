package edu.vt.cs5744;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * The purpose of this class is to make sure the broadcast service 
 * related to notifications remain on if the device gets restarted.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class StartupReceiver extends BroadcastReceiver 
{
	private static final String TAG = "StartupReceiver";// Variable to identify log entries
	
	/**
	 * Uses shared preference file to set alarm state.
	 * 
	 * @param context An instance of an activity
	 * @param intent An intent that can communicate with the OS
	 */
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// Displays a log message.
		Log.i(TAG, "The broadcast intent is received: " + intent.getAction());
		
		// Creates a shared preferences file to save the alarm state.
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		
		// Gets the state of the preference.
	    boolean isOn = preferences.getBoolean(PollService.IS_ALARM_ON, false);
	    
	    // Sends the state of the preference to the PollService.
	    PollService.setServiceAlarm(context, isOn);
	}

}
