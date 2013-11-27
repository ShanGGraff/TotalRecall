package edu.vt.cs5744;

/**
 * The purpose of this class is to store a JSON Recalls object from
 * the publicly available usa.gov recall API.
 * Contains recall information from the Food and Drug Administration 
 * (FDA), U.S. Department of Agriculture's Food Safety and Inspection 
 * Service (UDSA FSIS), and FoodSafety.gov; car safety recalls from the 
 * National Highway Traffic Safety Administration (NHTSA); and product 
 * safety recalls from the Consumer Product Safety Commission (CPSC).
 * 
 * @author Scott Chappell and Shane Graff
 */
public class Recalls 
{
	private Success success;	// A Success object
	
	/**
	 * A get method that returns a Success object.
	 * 
	 * @return A Success object from the usa.gov recall API
	 */
	Success getSuccess()
	{
		return success;
	}
}
