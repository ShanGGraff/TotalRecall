package edu.vt.cs5744;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import android.net.Uri;
import android.util.Log;

public class AccessDataGov
{
	public static final String SEARCH_QUERY = "searchQuery";
	private static final String BASE = "http://api.usa.gov/recalls/search.json?";
    private static final String RECENT = "date";
    private static final String PER_PAGE = "20";
    
    public Recalls fetchJson(String  url) 
    {
    	try
        {
            URL dataGov = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(dataGov.openStream())); 
            
            Recalls response = new Gson().fromJson(in.readLine(), Recalls.class);
            
            in.close();
            
            return response;
        }
        catch(Exception e)
        {
            Log.e("Error_Message", "Problem connecting" + e.toString());
            return null;
        }
    }
    
    public Recalls recent()
    {
    	String url = Uri.parse(BASE).buildUpon()
                .appendQueryParameter("sort", RECENT)
                .appendQueryParameter("per_page", PER_PAGE)
                .build().toString();
    	
        return fetchJson(url);
    }
    
    public Recalls search(String query)
    {
    	String url = Uri.parse(BASE).buildUpon()
                .appendQueryParameter("sort", RECENT)
                .appendQueryParameter("per_page", PER_PAGE)
                .appendQueryParameter("query", query)
                .build().toString();
        return fetchJson(url);
    }
}
