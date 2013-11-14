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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RecallListFragment extends ListFragment 
{
	private static final String TAG = "RecallListFragment";
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        updateItems(); 
	}  
	
	public void updateItems() 
	{
		try
        {
			new FetchRecallsTask().execute();
        }
        catch(Exception e)
        {
        	Log.e("Error_Message", "Problem connecting" + e.toString());
        } 
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
			 organizationTextView.setText(a.getOrganization());
			 TextView titleTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_idTextView);
			 titleTextView.setText(a.getRecallNumber());
			 TextView dateTextView =
					 (TextView)convertView.findViewById(R.id.recall_list_item_dateTextView);
			 dateTextView.setText(a.getRecallDate());
			 
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
			     descriptionTextView.setText(a.getDefectSummary());
			 }
			 else if(a.getOrganization().equalsIgnoreCase("FDA") || a.getOrganization().equalsIgnoreCase("USDA"))
			 {
				 TextView descriptionTextView =
			    		 (TextView)convertView.findViewById(R.id.recall_list_item_descriptionTextView);
			     descriptionTextView.setText(a.getSummary());
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
	            updateItems();
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	}
	
	private class FetchRecallsTask extends AsyncTask<Void, Void, Recalls>
	{
	    @Override
	    protected Recalls doInBackground(Void... params) 
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
	            return new AccessDataGov().search(query);
	        } 
	        else 
	        {
	            return new AccessDataGov().recent();
	        }
	    }
	    
	    protected void onPostExecute(Recalls recalls)
	    {
	    	RecallReceiver.get(getActivity()).setRecalls(recalls);
	    	getActivity().setTitle(R.string.recall_title);
		    RecallsAdapter adapter = new RecallsAdapter(recalls.getSuccess().getResults());
		    setListAdapter(adapter); 
	    }
	}
}
