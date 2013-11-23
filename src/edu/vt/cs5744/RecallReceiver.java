package edu.vt.cs5744;

import android.content.Context;

public class RecallReceiver
{
	private Recalls mApi;
	private static RecallReceiver sRecallReceiver;
    private Context mAppContext;

    private RecallReceiver(Context appContext) 
    {
        mAppContext = appContext;  
    }

    public static RecallReceiver get(Context c) 
    {
        if (sRecallReceiver == null) 
        {
        	sRecallReceiver = new RecallReceiver(c.getApplicationContext());
        }
        return sRecallReceiver;
    }
    
    public void setRecalls(Recalls recalls)
    {
    	mApi = recalls;
    }
    
    public Recalls getRecalls() 
    {
        return mApi;
    }
    
    public Results getResults(String number) 
    {
        for (Results a : mApi.getSuccess().getResults()) 
        {
            if (a.getRecallNumber().equals(number))
            {
            	return a;
            }      
        }
        return null;
    }
}
