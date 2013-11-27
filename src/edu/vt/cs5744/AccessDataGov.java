package edu.vt.cs5744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import android.net.Uri;
import android.util.Log;

/**
 * The purpose of this class is to build a URL and connect to the Internet.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class AccessDataGov
{
	public static final String SEARCH_QUERY = "searchQuery";   // Search query
	public static final String LAST_RESULT_ID = "lastResultId";// Last result from new recall check
	private static final String BASE = 
			"http://api.usa.gov/recalls/search.json?";		   // Base URL of recall API
    private static final String RECENT = "date";			   // Sort API by date
    private static final String PER_PAGE = "20";		       // Puts 20 results on a page
    
    /**
     * Uses a URL to access a web service.
     * 
     * @param url The URL (with certain parameters) that accesses the recall API
     * @return A Recalls object with all the recall data in it
     */
    public Recalls fetchJson(String  url) 
    {
    	try
        {
    		// Creates a new URL object.
            URL dataGov = new URL(url);
            
            // Opens a connection to the API server and puts the 
            // results in a BufferedReader object.
            // The results are in a JSON format.
            BufferedReader in = new BufferedReader(
            		new InputStreamReader(dataGov.openStream()));
            
            // Uses GSON to put the JSON data into a Recalls object.
            // GSON is a library from Google that provides the ability to parse JSON.
            Recalls response = new Gson().fromJson(in.readLine(), Recalls.class);
            
            // Closes the connection the web service.
            in.close();
            
            return response;
        }
        catch(IOException e)
        {
            Log.e("Error_Message", "Problem connecting" + e.toString());
            return null;
        }
    }
    
    /**
     * Creates a URL without a query term.
     * 
     * @param page The page number of the API
     * @return A Recalls object with all the recall data in it
     */
    public Recalls recent(Integer page)
    {
    	// Builds a string with different parameters.
    	String url = Uri.parse(BASE).buildUpon()
                .appendQueryParameter("sort", RECENT)
                .appendQueryParameter("per_page", PER_PAGE)
                .appendQueryParameter("page", page.toString())
                .build().toString();
    	
    	// Calls fetchJson to connect to the web service.
        return fetchJson(url);
    }
    
    /**
     * Creates a URL with a query term.
     * 
     * @param page The page number of the API
     * @param query The query term
     * @return A Recalls object with all the recall data in it
     */
    public Recalls search(Integer page, String query)
    {
    	// Builds a string with different parameters.
    	String url = Uri.parse(BASE).buildUpon()
                .appendQueryParameter("sort", RECENT)
                .appendQueryParameter("per_page", PER_PAGE)
                .appendQueryParameter("page", page.toString())
                .appendQueryParameter("query", query)
                .build().toString();
    	
    	// Calls fetchJson to connect to the web service.
        return fetchJson(url);
    }
}
