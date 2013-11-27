package edu.vt.cs5744;

import java.util.ArrayList;

/**
 * The purpose of this class is to store a JSON Success object from
 * the publicly available usa.gov recall API.
 * Contains recall information from the Food and Drug Administration 
 * (FDA), U.S. Department of Agriculture's Food Safety and Inspection 
 * Service (UDSA FSIS), and FoodSafety.gov; car safety recalls from the 
 * National Highway Traffic Safety Administration (NHTSA); and product 
 * safety recalls from the Consumer Product Safety Commission (CPSC).
 * 
 * @author Scott Chappell and Shane Graff
 */
public class Success 
{
	private Integer total;					// Total results from the api
	private ArrayList<Results> results;		// List of Results objects
	
	/**
	 * A get method that returns the total results from the API.
	 * 
	 * @return The total results from the API
	 */
	Integer getTotal()
	{
		return this.total;
	}
	
	/**
	 * A get method that returns a list of Results objects.
	 * 
	 * @return A list of Results objects from the API
	 */
	ArrayList<Results> getResults()
	{
		return this.results;
	}
}
