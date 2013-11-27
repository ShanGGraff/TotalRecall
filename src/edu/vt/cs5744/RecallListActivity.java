package edu.vt.cs5744;

import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * The purpose of this class is to create an activity to host the fragments
 * that will compose the home page of the app.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class RecallListActivity extends SingleFragmentActivity 
{
	private static final String TAG = "RecallListFragment";// Variable to identify log entries
	
	/**
	 * Creates the fragments for the list of recall items
	 * 
	 * @return A new fragment
	 */
	@Override
	protected Fragment createFragment() 
	{
		return new RecallListFragment();
	}
	
	/**
	 * Processes the intents for this activity.
	 * In this case, the intents are just for searches.
	 * 
	 * @param intent An intent that can communicate with the OS
	 */
	@Override
	public void onNewIntent(Intent intent) 
	{
		// Looks up the fragment.
		RecallListFragment fragment = (RecallListFragment)
	            getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

		// If the intent is based on ACTION_SEARCH, it stores the query in a shared preference.
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
	    {
	    	String query = intent.getStringExtra(SearchManager.QUERY);
	        Log.i(TAG, "A new search query received: " + query);
	        
	        PreferenceManager.getDefaultSharedPreferences(this)
            .edit().putString(AccessDataGov.SEARCH_QUERY, query).commit();
	    }

	    // Updates the RecallListFragment and sets the page to one.
	    fragment.updateItems(1); 
	}
}
