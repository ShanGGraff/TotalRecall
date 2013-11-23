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
import android.util.Log;
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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RecallListFragment extends ListFragment 
{
	private static final String TAG = "RecallListFragment";
	private int currentPage = 1;
	private RecallsAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        updateItems(currentPage);   
    }  
     	
    public void updateItems(Integer page) 
    {
    	try
        {
    		new FetchRecallsTask().execute(page);
        }
        catch(Exception e)
        {
        	Log.e("Error_Message", "Problem connecting" + e.toString());
        } 
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
	{
        super.onActivityCreated(savedInstanceState);  
        getListView().setOnScrollListener(new EndlessScrollListener());   
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		 Results a = ((RecallsAdapter)getListAdapter()).getItem(position);
		 // Start RecallPagerActivity with this recall
		 Intent i = new Intent(getActivity(), RecallPagerActivity.class);
	     i.putExtra(RecallFragment.EXTRA_RECALL_ID, a.getRecallNumber());
	     startActivity(i); 	 
	}
	 
	private class RecallsAdapter extends ArrayAdapter<Results> 
	{
		 public RecallsAdapter(ArrayList<Results> recalls) 
	     {
			 super(getActivity(), 0, recalls);
	     }
		 
		 @Override
		 public View getView(int position, View convertView, ViewGroup parent) 
		 {
			 // If we weren't given a view, inflate one
			 if (convertView == null)
		     {
				 convertView = getActivity().getLayoutInflater()
			                .inflate(R.layout.list_item_recall, null);
		     }
			 
			 // Configure the view for this Recall
			 Results a = getItem(position);
			 
			 TextView organizationTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_organizationTextView);
			 organizationTextView.setText(a.organizationToString());
			 TextView titleTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_idTextView);
			 titleTextView.setText(a.recallNumberToString());
			 TextView dateTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_dateTextView);
			 dateTextView.setText(a.recallDateToString());
			 
			 if(a.getOrganization().equalsIgnoreCase("CPSC"))
			 {
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_descriptionTextView);
			     descriptionTextView.setText(a.descriptionsToString());
			 }
			 else if(a.getOrganization().equalsIgnoreCase("NHTSA"))
			 {
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_descriptionTextView);
			     descriptionTextView.setText(a.defectSummaryToString());
			 }
			 else if(a.getOrganization().equalsIgnoreCase("FDA") || a.getOrganization().equalsIgnoreCase("USDA"))
			 {
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_descriptionTextView);
			     descriptionTextView.setText(a.summaryToString());
			 }
			 
			 return convertView; 
		 }
	}
	 
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	{
	     super.onCreateOptionsMenu(menu, inflater);
	     inflater.inflate(R.menu.fragment_recall_search, menu);
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
	     {
	         // Pull out the SearchView
	         MenuItem searchItem = menu.findItem(R.id.menu_item_search);
	         SearchView searchView = (SearchView)searchItem.getActionView();

	         // Get the data from our searchable.xml as a SearchableInfo
	         SearchManager searchManager = (SearchManager)getActivity()
	        		 .getSystemService(Context.SEARCH_SERVICE);
	         ComponentName name = getActivity().getComponentName();
	         SearchableInfo searchInfo = searchManager.getSearchableInfo(name);

	         searchView.setSearchableInfo(searchInfo);
	     }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	     switch (item.getItemId()) 
	     {
	         case R.id.menu_item_search:
	             getActivity().onSearchRequested();
	             return true;
	         case R.id.menu_item_clear:
	        	 PreferenceManager.getDefaultSharedPreferences(getActivity())
	                .edit()
	                .putString(AccessDataGov.SEARCH_QUERY, null)
	                .commit();
	        	 currentPage = 1;
	        	 updateItems(currentPage);
	             return true;
	         case R.id.menu_item_toggle_polling:
	             boolean shouldStartAlarm = !PollService.isServiceAlarmOn(getActivity());
	             PollService.setServiceAlarm(getActivity(), shouldStartAlarm);

	             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
	                 getActivity().invalidateOptionsMenu();
	             
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
	    super.onPrepareOptionsMenu(menu);

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
	
	private class FetchRecallsTask extends AsyncTask<Integer, Void, Recalls>
	{
	    @Override
	    protected Recalls doInBackground(Integer... params) 
	    {       
	        Activity activity = getActivity();
	        if (activity == null)
	        {
	            return new Recalls();
	    	}

	        String query = PreferenceManager.getDefaultSharedPreferences(activity)
	        		.getString(AccessDataGov.SEARCH_QUERY, null);
	        if (query != null) 
	        {
	        	currentPage = params[0];
	            return new AccessDataGov().search(params[0], query);
	        } 
	        else 
	        {
	            return new AccessDataGov().recent(params[0]);
	        }
	    }
	    
	    protected void onPostExecute(Recalls recalls)
	    {
	    	if(recalls != null)
	    	{
	    		if(currentPage == 1)
		    	{	
		    		RecallReceiver.get(getActivity()).setRecalls(recalls);
		    		getActivity().setTitle(R.string.recall_title);
		    		adapter = new RecallsAdapter(recalls.getSuccess().getResults());
				    setListAdapter(adapter);
		    	}
		    	else
		    	{
		    		adapter.addAll(recalls.getSuccess().getResults());
		    	}
	    		getListView().setOnScrollListener(new EndlessScrollListener());
	    	}
	    	else
	    	{
	    		Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_LONG).show();
	    	}
	    }   
	}

	private class EndlessScrollListener implements OnScrollListener
	{
		private int visibleThreshold = 5;
	    private int previousTotal = 0;
	    private boolean loading = true;

	    protected EndlessScrollListener() 
	    {
	
	    }

	    @Override
	    public void onScroll(AbsListView view, int firstVisibleItem,
	            int visibleItemCount, int totalItemCount) 
	    {
	        if (loading) 
	        {
	            if (totalItemCount > previousTotal) 
	            {
	                loading = false;
	                previousTotal = totalItemCount;
	                currentPage++;
	            }
	        }
	        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) 
	        {
	            loading = true;
	            if(currentPage < 20 && 
	            		totalItemCount < RecallReceiver.get(getActivity()).getRecalls().getSuccess().getTotal())
	            {
	            	updateItems(currentPage);	
	            }
	        }
	    }

	    @Override
	    public void onScrollStateChanged(AbsListView view, int scrollState) 
	    {
	    	
	    }
	}
}
