package edu.vt.cs5744;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * The purpose of this class is to check for new recalls and send the 
 * user notifications when new recalls are found.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class PollService extends IntentService 
{
	
	private static final String TAG = "PollService";// Variable to identify log entries
	
	public static final int POLL_INTERVAL = 1000 * 60 * 60 * 2; // 2 hours
	public static final String IS_ALARM_ON = "isAlarmOn";// State of the broadcast receiver
	
	/**
	 * Constructor for PollService class.
	 */
    public PollService() 
    {
        super(TAG);
    }
	
    /**
     * Main notification method.
     * Checks if new recall has been released and sends a notification to the system.
     * 
     * @param intent An intent that can communicate with the OS
     */
	@Override
	protected void onHandleIntent(Intent intent) 
	{
		// Sets up a ConnectivityManager object to check network status.
		ConnectivityManager cm = (ConnectivityManager)
	            getSystemService(Context.CONNECTIVITY_SERVICE);
		
		// Checks if the app has a network connection.
	    @SuppressWarnings("deprecation")
	    boolean isNetworkAvailable = cm.getBackgroundDataSetting() &&
	        cm.getActiveNetworkInfo() != null;
	    
	    // Returns if no connection.
	    if (!isNetworkAvailable) 
	    {
	    	return;
	    }
	    
	    // Creates a SharedPreferences object.
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	    
	    // Retrieves a string for the shared preferences tied to search query.
	    String query = preferences.getString(AccessDataGov.SEARCH_QUERY, null);
	    
	    // Retrieves a string for the shared preferences tied to last result ID.
	    String lastResultId = preferences.getString(AccessDataGov.LAST_RESULT_ID, null);

	    // Declares Recalls object.
	    Recalls recalls;
	    
	    // Looks up a query result if the query preference is not null.
	    if (query != null) 
	    {
	    	recalls = new AccessDataGov().search(1, query);
	    } 
	    // Otherwise lookup results without query.
	    else 
	    {
	    	recalls = new AccessDataGov().recent(1);
	    }
	    
	    // Returns if the list of results is empty.
	    if (recalls.getSuccess().getResults().size() == 0)
	    {
	    	return;	
	    }
	        
	    // The first recall found is stored in this variable (out of a list of 20).
	    String resultId = recalls.getSuccess().getResults().get(0).getRecallNumber();

	    // Checks if the new result ID equals the old result ID.
	    if (!resultId.equals(lastResultId)) 
	    {
	    	// Sends a log message.
	        Log.i(TAG, "A new result is found: " + resultId);
	        
	        // Get resources for the activity.
	        Resources r = getResources();
	        
	        // Instantiate a Builder object.
	        NotificationCompat.Builder notification = 
	        		new NotificationCompat.Builder(this)
	        		.setSmallIcon(android.R.drawable.ic_notification_overlay)
	                .setContentTitle(r.getString(R.string.new_recalls_title))
	                .setContentText(r.getString(R.string.new_recalls_text))
	        		.setAutoCancel(true);
	        		
	        // Creates an explicit intent for an Activity in the app.
			Intent resultIntent = new Intent(this, RecallListActivity.class);
			
			// The stack builder object will contain an artificial back stack for the
			// started Activity.
			// This ensures that navigating backward from the Activity leads out of
			// the application to the Home screen.
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			
			// Adds the back stack for the Intent (but not the Intent itself).
			stackBuilder.addParentStack(RecallListActivity.class);
			
			// Adds the Intent that starts the Activity to the top of the stack.
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent =
			        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setContentIntent(resultPendingIntent);
			NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(0, notification.build());	
	    }

	    // Make the old result id equal to the new result ID.
	    preferences.edit().putString(AccessDataGov.LAST_RESULT_ID, resultId).commit();    
	    
	    // 
		Log.i(TAG, "Intent has been received: " + intent);
	}
	
	/**
	 * Turns on and off alarms based on AlarmManager that can send intents.
	 * 
	 * @param context An instance of an activity
	 * @param isOn Shared preference state of the alarm 
	 */
	public static void setServiceAlarm(Context context, boolean isOn) 
	{
		// Creates a new intent for the PollService.
        Intent i = new Intent(context, PollService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        // Create the alarm service.
        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        // If the alarm is on, send the intent every poll interval.
        if (isOn) 
        {
            alarmManager.setRepeating(AlarmManager.RTC,
                    System.currentTimeMillis(), POLL_INTERVAL, pi);
        } 
        // Otherwise cancel the intent.
        else 
        {
            alarmManager.cancel(pi);
            pi.cancel();
        }
        
        // Put the state of the alarm in the shared preferences.
        PreferenceManager.getDefaultSharedPreferences(context)
	        .edit().putBoolean(PollService.IS_ALARM_ON, isOn).commit();
	}
	
	/**
	 * Checks where the alarm is on or off.
	 * 
	 * @param context An instance of an activity
	 * @return pi That has the state of the alarm
	 */
	public static boolean isServiceAlarmOn(Context context) 
	{
		// If PendingIntent does does not already exist, return null instead of creating it.
	    Intent i = new Intent(context, PollService.class);
	    PendingIntent pi = PendingIntent.getService(
	            context, 0, i, PendingIntent.FLAG_NO_CREATE);
	    return pi != null;
	}
}
