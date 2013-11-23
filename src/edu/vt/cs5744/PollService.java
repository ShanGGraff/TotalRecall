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

public class PollService extends IntentService 
{
	private static final String TAG = "PollService";
	
	public static final int POLL_INTERVAL = 1000 * 60 * 60 * 2; // 2 hours
	public static final String IS_ALARM_ON = "isAlarmOn";
	
    public PollService() 
    {
        super(TAG);
    }
	
	@Override
	protected void onHandleIntent(Intent intent) 
	{
		ConnectivityManager cm = (ConnectivityManager)
	            getSystemService(Context.CONNECTIVITY_SERVICE);
	    @SuppressWarnings("deprecation")
	    boolean isNetworkAvailable = cm.getBackgroundDataSetting() &&
	        cm.getActiveNetworkInfo() != null;
	    if (!isNetworkAvailable) return;
	    
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	    String query = prefs.getString(AccessDataGov.SEARCH_QUERY, null);
	    String lastResultId = prefs.getString(AccessDataGov.LAST_RESULT_ID, null);

	    Recalls recalls;
	    if (query != null) 
	    {
	    	recalls = new AccessDataGov().search(1, query);
	    } 
	    else 
	    {
	    	recalls = new AccessDataGov().recent(1);
	    }

	    if (recalls.getSuccess().getResults().size() == 0)
	        return;

	    String resultId = recalls.getSuccess().getResults().get(0).getRecallNumber();

	    if (!resultId.equals(lastResultId)) 
	    {
	        Log.i(TAG, "Got a new result: " + resultId);
	        
	        Resources r = getResources();
	        PendingIntent pi = PendingIntent
	            .getActivity(this, 0, new Intent(this, RecallListFragment.class), 0);

	        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
	            .setTicker(r.getString(R.string.new_pictures_title))
	            .setSmallIcon(android.R.drawable.ic_menu_report_image)
	            .setContentTitle(r.getString(R.string.new_pictures_title))
	            .setContentText(r.getString(R.string.new_pictures_text))
	            .setContentIntent(pi)
	            .setAutoCancel(true);
	        // Creates an explicit intent for an Activity in your app
	        Intent resultIntent = new Intent(this, RecallListFragment.class);

	        // The stack builder object will contain an artificial back stack for the
	        // started Activity.
	        // This ensures that navigating backward from the Activity leads out of
	        // your application to the Home screen.
	        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
	        
	        // Adds the back stack for the Intent (but not the Intent itself)
	        stackBuilder.addParentStack(RecallListFragment.class);
	        
	        // Adds the Intent that starts the Activity to the top of the stack
	        stackBuilder.addNextIntent(resultIntent);
	        PendingIntent resultPendingIntent =
	                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
	        notification.setContentIntent(resultPendingIntent);
	        NotificationManager notificationManager = (NotificationManager)
	            getSystemService(NOTIFICATION_SERVICE);

	        notificationManager.notify(0, notification.build());
	    }

	    prefs.edit()
	        .putString(AccessDataGov.LAST_RESULT_ID, resultId)
	        .commit();    
		Log.i(TAG, "Received an intent: " + intent);
	}
	
	public static void setServiceAlarm(Context context, boolean isOn) 
	{
        Intent i = new Intent(context, PollService.class);
        PendingIntent pi = PendingIntent.getService(
                context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) 
        {
            alarmManager.setRepeating(AlarmManager.RTC,
                    System.currentTimeMillis(), POLL_INTERVAL, pi);
        } 
        else 
        {
            alarmManager.cancel(pi);
            pi.cancel();
        }
        
        PreferenceManager.getDefaultSharedPreferences(context)
	        .edit()
	        .putBoolean(PollService.IS_ALARM_ON, isOn)
	        .commit();
	}
	
	public static boolean isServiceAlarmOn(Context context) 
	{
	    Intent i = new Intent(context, PollService.class);
	    PendingIntent pi = PendingIntent.getService(
	            context, 0, i, PendingIntent.FLAG_NO_CREATE);
	    return pi != null;
	}
}
