package edu.vt.cs5744;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

/**
 * The purpose of this class is to use a list of fragments to
 * display a list of recalled items.
 * 
 * @author Scott Chappell and Shane Graff
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RecallListFragment extends ListFragment 
{
	private static final String TAG = "RecallListFragment";// Variable to identify log entries
	private int currentPage = 1;	// The current page of the recall API request
	private RecallsAdapter adapter;	// A custom adapter used to display the list of fragments
	
	/**
	 * Called when the activity first starts up.
	 * 
	 * @param savedInstanceState Potentially used to re-constructed from a previous saved state
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        
        // Saves data between orientation changes.
        setRetainInstance(true);
        
        // Allows an options menu.
        setHasOptionsMenu(true);  
        
        // Updates the recall list with the current page.
        updateItems(currentPage); 
    }  
	
	/**
	 * Starts a background thread that is used for the HTTP request.
	 * 
	 * @param page The page number of the recall API
	 */
    public void updateItems(Integer page) 
    {
    	new FetchRecallsTask().execute(page);
    }
    
    /**
     * Implements a new ScrollListener on the list view when the orientation changes.
     * 
     * @param savedInstanceState Potentially used to re-constructed from a previous saved state
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
	{
        super.onActivityCreated(savedInstanceState);  
        getListView().setOnScrollListener(new EndlessScrollListener());   
	}
    
	/**
	 * Starts a new activity that will host the fragments for the detailed view
	 * when an item is clicked.
	 * 
	 * @param l The ListView where the click happened
	 * @param v The view that was clicked within the ListView
	 * @param position The position of the view in the list
	 * @param id The row id of the item that was clicked 
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		// Gets the specific Results object that was clicked.
		Results a = ((RecallsAdapter)getListAdapter()).getItem(position);
		 
		// Starts RecallPagerActivity with this item.
		Intent i = new Intent(getActivity(), RecallPagerActivity.class);
	    i.putExtra(RecallFragment.RECALL_ID, a.getRecallNumber());
	     
	    // New activity is started.
	    startActivity(i); 	 
	}
	 
	/**
	 * This is a custom adapter with the purpose of displaying the list of recalls.
	 * 
	 * @author Scott Chappell and Shane Graff
	 */
	private class RecallsAdapter extends ArrayAdapter<Results> 
	{
		/**
		 * A constructor for the RecallsAdapter.
		 * 
		 * @param recalls A list of Results objects 
		 */
		 public RecallsAdapter(ArrayList<Results> recalls) 
	     {
			 super(getActivity(), 0, recalls);
	     }
		 
		 /**
		  * Sets up the layouts for each recall in the list adapter.
		  * 
		  * @param position The position of each recall
		  * @param convertView The view used to inflate the layout
		  * @param parent Can be used to generate the LayoutParams of the view
		  */
		 @Override
		 public View getView(int position, View convertView, ViewGroup parent) 
		 {
			 // If we weren't given a view, inflate one.
			 if (convertView == null)
		     {
				 convertView = getActivity().getLayoutInflater()
			                .inflate(R.layout.list_item_recall, null);
		     }
			 
			 // Configure the view for this Recall.
			 Results a = getItem(position);
			 
			 TextView organizationTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_orgTextView);
			 organizationTextView.setText(a.organizationToString());
			 TextView titleTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_idTextView);
			 titleTextView.setText(a.recallNumberToString());
			 TextView dateTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_dateTextView);
			 dateTextView.setText(a.recallDateToString());
			 
			 // Sets specific entries for CPSC.
			 if(a.getOrganization().equalsIgnoreCase("CPSC"))
			 {
				 // Uses descriptionsToString().
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_desTextView);
			     descriptionTextView.setText(a.descriptionsToString());
			 }
			 // Sets specific entries for NHTSA.
			 else if(a.getOrganization().equalsIgnoreCase("NHTSA"))
			 {
				 // Uses defectSummaryToString().
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_desTextView);
			     descriptionTextView.setText(a.defectSummaryToString());
			 }
			 // Sets specific entries for FDA or USDA.
			 else if(a.getOrganization().equalsIgnoreCase("FDA") || 
					 a.getOrganization().equalsIgnoreCase("USDA"))
			 {
				 // Uses summaryToString().
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_desTextView);
			     descriptionTextView.setText(a.summaryToString());
			 }
			 return convertView; 
		 }
	}
	 
	/**
	 * Creates an option menu for the recall list fragment.
	 * 
	 * @param menu The options menu in which you place your items
	 * @param inflater Inflate the layout of the options menu
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	{
	     super.onCreateOptionsMenu(menu, inflater);
	     inflater.inflate(R.menu.fragment_recall_search, menu);
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
	     {
	         // Pull out the SearchView.
	         MenuItem searchItem = menu.findItem(R.id.menu_item_search);
	         SearchView searchView = (SearchView)searchItem.getActionView();

	         // Get the data from our searchable.xml as a SearchableInfo.
	         SearchManager searchManager = (SearchManager)getActivity()
	        		 .getSystemService(Context.SEARCH_SERVICE);
	         ComponentName name = getActivity().getComponentName();
	         SearchableInfo searchInfo = searchManager.getSearchableInfo(name);

	         searchView.setSearchableInfo(searchInfo);
	     }
	}

	/**
	 * Checks if a menu item is clicked.
	 * 
	 * @param item An item in the menu
	 * @return A boolean value indicating success
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
	    {
	     	// Checks if a search has been made.
	    	case R.id.menu_item_search:
	    		getActivity().onSearchRequested();
	            return true;
	        // Checks if the results have been cleared.
	    	case R.id.menu_item_clear:
	        	PreferenceManager.getDefaultSharedPreferences(getActivity())
	        		.edit().putString(AccessDataGov.SEARCH_QUERY, null).commit();
	        	// Resets the page number to one.
	        	currentPage = 1;
	        	updateItems(currentPage);
	            return true;
	        // Checks if notifications have been toggled on.
	        case R.id.menu_item_toggle_polling:
	            boolean shouldStartAlarm = !PollService.isServiceAlarmOn(getActivity());
	            PollService.setServiceAlarm(getActivity(), shouldStartAlarm);

	            // Version control for different versions of Android.
	            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	            	getActivity().invalidateOptionsMenu(); 
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * Dynamically modifies the contents of the menu.
	 * 
	 * @param menu The options menu as last shown or first initialized by onCreateOptionsMenu()
	 */
	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
	    super.onPrepareOptionsMenu(menu);

	    // Checks if the background service for finding new recalls is on.
	    // If yes, Notifications has a check mark, otherwise Notifications is blank.
	    MenuItem toggleItem = menu.findItem(R.id.menu_item_toggle_polling);
	    if (PollService.isServiceAlarmOn(getActivity())) 
	    {
	    	toggleItem.setChecked(true);
	    } 
	    else
	    {
	    	toggleItem.setChecked(false);
	    }
	    
	}
	
	/**
	 * This is a background thread used to access a web service
	 * without making the main UI thread unresponsive.
	 * 
	 * @author Scott Chappell and Shane Graff
	 */
	private class FetchRecallsTask extends AsyncTask<Integer, Void, Recalls>
	{
		/**
		 * Runs the background thread.
		 * 
		 * @param Interger An array of Integers (only params[0] is used)
		 * @return A Recalls object
		 */
	    @Override
	    protected Recalls doInBackground(Integer... params) 
	    {       
	    	// Returns a new Recalls object if the activity is null.
	        Activity activity = getActivity();
	        if (activity == null)
	        {
	            return new Recalls();
	    	}

	        // Gets the query string from the Shared preferences.
	        String query = PreferenceManager.getDefaultSharedPreferences(activity)
	        		.getString(AccessDataGov.SEARCH_QUERY, null);
	        // If query has a value, send it along the page number to the AccessDataGov class.
	        // The page number should always be one if there is a query string .
	        if (query != null) 
	        {
	        	// Resets the page number to one
	        	currentPage = params[0];
	            return new AccessDataGov().search(params[0], query);
	        } 
	        // If no query value exists, send only the page number to the AccessDataGov class.
	        else 
	        {
	            return new AccessDataGov().recent(params[0]);
	        }
	    }
	    
	    /**
	     * Takes the results of the background and puts them in the main UI thread.
	     * 
	     * @param recalls A Recalls object from the recall API
	     */
	    protected void onPostExecute(Recalls recalls)
	    {
	    	// If recalls is null, send a connection error message to the user.
	    	// This occurs if the user has no network connection.
	    	if(recalls != null)
	    	{
	    		// Checks if the current page number is one.
	    		// Indicates that a new RecallsAdapter needs to be created.
	    		if(currentPage == 1)
		    	{	
		    		RecallReceiver.get(getActivity()).setRecalls(recalls);
		    		getActivity().setTitle(R.string.recall_title);
		    		adapter = new RecallsAdapter(recalls.getSuccess().getResults());
				    setListAdapter(adapter);
		    	}
	    		// Otherwise, add the recall Results to the Adapter list.
		    	else
		    	{
		    		// This filters duplicate entries
		    		if(!adapter.getItem(0).recallNumberToString().equals(
		    				recalls.getSuccess().getResults().get(0).recallNumberToString()))
		    		{
		    			adapter.addAll(recalls.getSuccess().getResults());
		    		}
		    	}
	    		// Creates a new Scroll Listener when the ground thread is run.
	    		getListView().setOnScrollListener(new EndlessScrollListener());
	    	}
	    	// Pops up a network connection error to the user.
	    	else
	    	{
	    		Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_LONG).show();
	    	}
	    }   
	}

	/**
	 * Checks if the user scrolls down the list, and populates the list 
	 * if the user reaches a certain scroll point.
	 * 
	 * @author Scott Chappell and Shane Graff
	 */
	private class EndlessScrollListener implements OnScrollListener
	{
		private int visibleThreshold = 5;	// Threshold to determine new results
	    private int previousTotal = 0;		// Previous total of results
	    private boolean loading = true;		// Stores the loading state

	    /**
	     * Checks if the user is scrolling.
	     * 
	     * @param view The view whose scroll state is being reported
	     * @param firstVisibleItem the index of the first visible cell
	     * @param visibleItemCount the number of visible cells
	     * @param totalItemCount the number of items in the list adaptor 
	     */
	    @Override
	    public void onScroll(AbsListView view, int firstVisibleItem,
	            int visibleItemCount, int totalItemCount) 
	    {
	    	// Checks if data is loading.
	    	// The extra items should only be added once until the user scrolls again.
	        if (loading) 
	        {
	        	// Checks if the total item count is greater than the previous total.
	            if (totalItemCount > previousTotal) 
	            {
	                loading = false;
	                previousTotal = totalItemCount;
	            }
	        }
	        // Checks if loading is false, and if the user is 5 items from the bottom of the list.
	        if (!loading && 
	        		(totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold))
	        {
	        	loading = true;
	        	// Checks if the current page is less than 20 (max for the recalls API)
	        	// and if the total item count is less than the items in the list.
	            if(currentPage < 20 && totalItemCount < 
	            		RecallReceiver.get(getActivity()).getRecalls().getSuccess().getTotal())
	            {
	            	// Increments the page number
	            	currentPage++;
	            	
	            	// Sends a request to the background thread that will be received by the 
	            	// recalls API with a page number that is one higher than the previous one.
	            	updateItems(currentPage);	
	            }
	        }
	    }
	    /**
	     * Checks the current scroll state.  In this implementation it does nothing
	     * (but the OnScrollListener requires calling it).
	     * 
	     * @param view The view whose scroll state is being reported
	     * @param scrollState The current scroll state
	     */
	    @Override
	    public void onScrollStateChanged(AbsListView view, int scrollState) 
	    {
	    	
	    }
	}
}
