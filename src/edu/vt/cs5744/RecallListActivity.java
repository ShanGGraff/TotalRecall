package edu.vt.cs5744;

import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

public class RecallListActivity extends SingleFragmentActivity 
{
	private static final String TAG = "RecallListFragment";
	
	@Override
	protected Fragment createFragment() 
	{
		return new RecallListFragment();
	}
	
	@Override
	public void onNewIntent(Intent intent) 
	{
		RecallListFragment fragment = (RecallListFragment)
	            getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
	    {
	    	String query = intent.getStringExtra(SearchManager.QUERY);
	        Log.i(TAG, "Received a new search query: " + query);
	        
	        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString(AccessDataGov.SEARCH_QUERY, query)
            .commit();
	    }

	    fragment.updateItems(); 
	}
}
