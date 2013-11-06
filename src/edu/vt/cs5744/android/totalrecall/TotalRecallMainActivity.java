package edu.vt.cs5744.android.totalrecall;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.vt.cs5744.android.totalrecall.R;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class TotalRecallMainActivity extends Activity {
	List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_recall);
        //testing branching part deux
        ListView myListView = (ListView)findViewById(R.id.listView);
        
        try
        {
        	RecallAPI api = new AccessDataGov().execute().get();
        	ArrayAdapter<ResultsCPSC> simpleAdpt = new ArrayAdapter<ResultsCPSC>
        		(this, android.R.layout.simple_list_item_1, api.getSuccess().getGovData());
        	myListView.setAdapter(simpleAdpt);
        }
        catch(Exception e)
        {
        	Log.e("Error_Message", "Problem connecting" + e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.total_recall_mitch, menu);
        return true;
    }
}
