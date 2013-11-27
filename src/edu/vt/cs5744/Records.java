package edu.vt.cs5744;

/**
 * The purpose of this class is to store a JSON Records object from
 * the publicly available usa.gov recall API.
 * Records only exist with safety recalls from the 
 * National Highway Traffic Safety Administration (NHTSA).
 * 
 * @author Scott Chappell and Shane Graff
 */
public class Records 
{
	private String component_description;	// Component description of the vehicle
	private String make;					// Make of the vehicle
	private String manufacturer;			// Manufacturer of the vehicle
	private String manufacturing_begin_date;// Start date of recall for certain vehicles
	private String manufacturing_end_date;	// End date of recall for certain vehicles
	private String model;					// Model of the vehicle
	private String recalled_component_id;	// Component ID
	private String year;					// Year of the vehicle
	
	/**
	 * Returns a string representation of the values in the Records object.
	 * Places "Not Available" string in any null value (to make the app
	 * easier to read).
	 * 
	 * @return A string representation of the values in the Records object
	 */
	public String toString() 
	{
		// Creates a StringBuffer that will hold all the string values.
		StringBuffer returnRecords = new StringBuffer("");
		if(this.component_description == null)
		{
			returnRecords.append("Component Description: ")
				.append("Not Available");
		}
		else
		{
			returnRecords.append("Component Description: ")
				.append(this.component_description);
		}
		if(this.make == null)
		{
			returnRecords.append("\nMake: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nMake: ").append(this.make);
		}
		if(this.manufacturer == null)
		{
			returnRecords.append("\nManufacturer: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nManufacturer: ").append(this.manufacturer);
		}
		if(this.manufacturing_begin_date == null)
		{
			returnRecords.append("\nManufacturing Begin Date: ")
				.append("Not Available");
		}
		else
		{
			returnRecords.append("\nManufacturing Begin Date: ")
				.append(this.manufacturing_begin_date);
		}
		if(this.manufacturing_end_date == null)
		{
			returnRecords.append("\nManufacturing End Date: ")
				.append("Not Available");
		}
		else
		{
			returnRecords.append("\nManufacturing End Date: ")
				.append(this.manufacturing_end_date);
		}
		if(this.model == null)
		{
			returnRecords.append("\nModel: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nModel: ").append(this.model);
		}
		if(this.recalled_component_id == null)
		{
			returnRecords.append("\nComponent ID: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nComponent ID: ")
				.append(this.recalled_component_id);
		}
		if(this.year == null)
		{
			returnRecords.append("\nYear: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nYear: ").append(this.year);
		}
		
		return returnRecords.toString();
    
	}
}