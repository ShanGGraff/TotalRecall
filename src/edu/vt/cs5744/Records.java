package edu.vt.cs5744;

public class Records 
{
	private String component_description;
	private String make;
	private String manufacturer;
	private String manufacturing_begin_date;
	private String manufacturing_end_date;
	private String model;
	private String recalled_component_id;
	private String year;
	
	public String toString() 
	{
		StringBuffer returnRecords = new StringBuffer("");
		if(this.component_description == null)
		{
			returnRecords.append("Component Description: ").append("Not Available");
		}
		else
		{
			returnRecords.append("Component Description: ").append(this.component_description);
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
			returnRecords.append("\nManufacturing Begin Date: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nManufacturing Begin Date: ").append(this.manufacturing_begin_date);
		}
		if(this.manufacturing_end_date == null)
		{
			returnRecords.append("\nManufacturing End Date: ").append("Not Available");
		}
		else
		{
			returnRecords.append("\nManufacturing End Date: ").append(this.manufacturing_end_date);
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
			returnRecords.append("\nComponent ID: ").append(this.recalled_component_id);
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