package edu.vt.cs5744;

import android.content.Context;

/**
 * The purpose of this class is to store a Recalls object 
 * to be accessed by other classes.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class RecallReceiver
{
	private Recalls mApi;							// Stores the results from the recall api
	private static RecallReceiver sRecallReceiver;	// Used to create new RecallReciver objects
    private Context mAppContext;					// An instance of an activity

    /**
     * Constructor for the RecallReceiver class.
     * 
     * @param appContext An instance of an activity
     */
    private RecallReceiver(Context appContext) 
    {
        mAppContext = appContext;  
    }

    /**
     * A static class that helps to create new RecallReceiver objects.
     * 
     * @param c An instance of an activity
     * @return A RecallReceiver object
     */
    public static RecallReceiver get(Context c) 
    {
    	// If sRecallReceiver is null, create a new instance of it.
        if (sRecallReceiver == null) 
        {
        	sRecallReceiver = new RecallReceiver(c.getApplicationContext());
        }
        return sRecallReceiver;
    }
    
    /**
     * A setter method that places the value of the Recalls object.
     * 
     * @param recalls Results from the recall API
     */
    public void setRecalls(Recalls recalls)
    {
    	mApi = recalls;
    }
    
    /**
     * A get method that returns the results of the recall API.
     * 
     * @return The Recalls object that holds the results of the recall API
     */
    public Recalls getRecalls() 
    {
        return mApi;
    }
    
    /**
     * A get method that returns individual results objects.
     * 
     * @param id The recall number 
     * @return A Results object that matches the recall number
     */
    public Results getResults(String id) 
    {
    	// Iterate through the list of Results.
        for (Results a : mApi.getSuccess().getResults()) 
        {
        	// Return a Results object if it matches the recall number.
            if (a.getRecallNumber().equals(id))
            {
            	return a;
            }      
        }
        return null;
    }
}
