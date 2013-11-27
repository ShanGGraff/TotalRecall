package edu.vt.cs5744;

import java.util.ArrayList;

/**
 * The purpose of this class is to store JSON Results from the 
 * publicly available usa.gov recall API.
 * Contains recall information from the Food and Drug Administration 
 * (FDA), U.S. Department of Agriculture's Food Safety and Inspection 
 * Service (UDSA FSIS), and FoodSafety.gov; car safety recalls from the 
 * National Highway Traffic Safety Administration (NHTSA); and product 
 * safety recalls from the Consumer Product Safety Commission (CPSC).
 * 
 * @author Scott Chappell and Shane Graff
 */
public class Results 
{
	// Section for all recall types
	private String organization; // Organization that oversees the recall
	private String recall_number;// ID of the recall, uniquely identifies each recall item
	private String recall_date;	 // Date recall is issued
	private String recall_url;	 // URL of government site with recall information
	
	// Section for CPSC recalls
	private ArrayList<String> manufacturers;// Manufacturer of product
	private ArrayList<String> product_types;// Type of project
	private ArrayList<String> descriptions;	// Description of product and reason for recall
	private ArrayList<String> upcs;			// UPCS code for the product
	private ArrayList<String> hazards;		// Hazard classification
	private ArrayList<String> countries;	// Country product was manufactured
	
	// Section for FDA and USDA recalls
	private String description;	// Description product and reason for recall
	private String summary;		// Brief summary of recall
	
	// Section for NHTSA recalls
	private ArrayList<Records> records;			// Contains list of Records objects
	private String manufacturer_campaign_number;// Manufacturer campaign number	
	private String component_description;		// Description of the component
	private String manufacturer;				// Manufacturer of vehicle
	private String code;						// Code for recalls
	private String potential_units_affected;	// Number of vehicles affected
	private String initiator;					// Initiator of recall
	private String report_date;					// Report date of found problem
	private String defect_summary;				// Brief summary of the recall
	private String consequence_summary;			// Description of what the problem is
	private String corrective_summary;			// Description of what action to take
	private String notes;						// Notes for the recall
	private String recall_subject;				// Brief subject descriptor
	
	/**
	 * A get method that returns the name of the overseeing organization.
	 * 
	 * @return A string of the name of the overseeing organization
	 */
	public String getOrganization()
	{
		return this.organization;
	}
	
	/**
	 * A get method that returns the recall ID.
	 * 
	 * @return A string of the recall ID
	 */
	public String getRecallNumber()
	{
		return this.recall_number;
	}
	
	/**
	 * A get method that returns the recall date
	 * 
	 * @return A string of the recall date
	 */
	public String getRecallDate()
	{
		return this.recall_date;
	}
	
	/**
	 * A get method that returns the recall URL
	 * 
	 * @return A string of the URL of a government site with recall information
	 */
	public String getRecallUrl() 
	{
		return this.recall_url;
	}

	/**
	 * A get method that returns the manufacturer of the product
	 * Only applicable for CPSC
	 * 
	 * @return A list of manufacturers of the recalled product
	 */
	public ArrayList<String> getManufacturers() 
	{
		return this.manufacturers;	
	}

	/**
	 * A get method that returns the type of product being recalled
	 * Only applicable for CPSC
	 * 
	 * @return A list of product types
	 */
	public ArrayList<String> getProductTypes() 
	{
		return this.product_types;	
	}
	
	/**
	 * A get method that returns the description of the product and the reason for a recall
	 * Only applicable for CPSC
	 * 
	 * @return A list of product descriptions and recall reasons
	 */
	public ArrayList<String> getDescriptions()
	{
		return this.descriptions;
	}

	/**
	 * A get method that returns the UPC codes of the product
	 * (Most of these were null for whatever reason)
	 * Only applicable for CPSC
	 * 
	 * @return A list of UPC product codes
	 */
	public ArrayList<String> getUpcs() 
	{
		return this.upcs;
	}

	/**
	 * A get method that returns the hazards of the product
	 * Only applicable for CPSC
	 * 
	 * @return A list of hazards associated with the product
	 */
	public ArrayList<String> getHazards() 
	{
		return this.hazards;
	}

	/**
	 * A get method that returns the countries where the product was made
	 * Only applicable for CPSC
	 * 
	 * @return A list of countries where the product was made
	 */
	public ArrayList<String> getCountries() 
	{
		return this.countries;
	}
	
	/**
	 * A get method that returns the description of the product and the reason for a recall
	 * Only applicable for FDA and USDA
	 * 
	 * @return A string of the description and reason for a recall of the product
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * A get method that returns the summary of the recall
	 * Only applicable for FDA and USDA
	 * 
	 * @return A string of the summary of the recall
	 */
	public String getSummary()
	{
		return this.summary;
	}
	
	/**
	 * A get method that returns a list of vehicle records
	 * Only applicable for NHTSA
	 * 
	 * @return A list of vehicle Records objects
	 */
	public ArrayList<Records> getRecords() 
	{
		return this.records;
	}

	/**
	 * A get method that returns the manufacturer campaign number
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the manufacturer campaign number
	 */
	public String getManufacturerCampaignNumber() 
	{
		return this.manufacturer_campaign_number;
	}
	
	/**
	 * A get method that returns the component description of the vehicle
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the component description
	 */
	public String getComponentDescription()
	{
		return this.component_description;
	}
	
	/**
	 * A get method that returns the manufacturer of the vehicle
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the manufacturer of the vehicle
	 */
	public String getManufacturer() 
	{
		return this.manufacturer;
	}
	
	/**
	 * A get method that returns the code of the recall
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the code of the recall
	 */
	public String getCode() 
	{
		return this.code;
	}

	/**
	 * A get method that returns the number of vehicles affected by the recall
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the number of affected vehicles
	 */
	public String getPotentialUnitsAffected() 
	{
		return this.potential_units_affected;
	}

	/**
	 * A get method that returns the initiator of the recall
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the initiator
	 */
	public String getInitiator() 
	{
		return this.initiator;
	}

	/**
	 * A get method that returns the report date of the problem
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the report date
	 */
	public String getReportDate() 
	{
		return this.report_date;
	}

	/**
	 * A get method that returns the defect summary
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the defect summary
	 */
	public String getDefectSummary() 
	{
		return this.defect_summary;
	}

	/**
	 * A get method that returns the description of the problem
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the consequence summary
	 */
	public String getConsequenceSummary() 
	{
		return this.consequence_summary;
	}

	/**
	 * A get method that returns the description of actions to take
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the corrective summary
	 */
	public String getCorrectiveSummary() 
	{
		return this.corrective_summary;
	}

	/**
	 * A get method that returns a note of the recall
	 * Only applicable for NHTSA
	 * 
	 * @return A string of a note on the recall
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * A get method that returns the recall subject
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the recall subject
	 */
	public String getRecallSubject() 
	{
		return this.recall_subject;
	}
	
	/*
	 * ----------------------------------------------------------------
	 */
	
	/**
	 * A string representation of the overseeing organization
	 * Replaces null with "Not Available" if applicable
	 * 
	 * @return A string of the name of the overseeing organization
	 */
	public String organizationToString()
	{
		if(this.organization == null)
		{
			return "Not Available";
		}
		else
		{
			return this.organization;
		}	
	}
	
	/**
	 * A string representation of the recall ID
	 * Replaces null with "Not Available" if applicable
	 * 
	 * @return A string of the recall ID
	 */
	public String recallNumberToString()
	{
		if(this.recall_number == null)
		{
			return "Not Available";
		}
		else
		{
			return this.recall_number;
		}	
	}
	/**
	 * A string representation of the recall date
	 * Replaces null with "Not Available" if applicable
	 * 
	 * @return A string of the recall date
	 */
	public String recallDateToString()
	{
		if(this.recall_date == null)
		{
			return "Not Available";
		}
		else
		{
			return this.recall_date;
		}	
	}
	
	/**
	 * A string representation of the recall URL
	 * Replaces null with "Not Available" if applicable
	 * 
	 * @return A string of the URL of a government site with recall information
	 */
	public String recallUrlToString() 
	{
		if(this.recall_url == null)
		{
			return "Not Available";
		}
		else
		{
			return this.recall_url;
		}
	}
	
	/*
	 * ----------------------------------------------------------------
	 */
	
	/**
	 * A string representation of the manufacturer of the product
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of manufacturers of the recalled product
	 */
	public String manufacturersToString()
	{
		StringBuffer returnManufacturers = new StringBuffer("");
		if(this.manufacturers == null)
		{
			returnManufacturers.append("Not Available").append(", ");
		}
		else
		{
			for(String m : this.manufacturers)
			{
				returnManufacturers.append(m).append(", ");
			}	
		}
		returnManufacturers.deleteCharAt(returnManufacturers.length()-2); 
		return returnManufacturers.toString();
	}
	
	/**
	 * A string representation of the type of product being recalled
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of product types
	 */
	public String typeToString()
	{
		StringBuffer returnType = new StringBuffer("");
		if(this.product_types == null)
		{
			returnType.append("Not Available").append(", ");
		}
		else
		{
			for(String t : this.product_types)
			{
				returnType.append(t).append(", ");
			}	
		}
		returnType.deleteCharAt(returnType.length()-2); 
		return returnType.toString();
	}
	
	/**
	 * A string representation of the description of the product and the reason for a recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of product descriptions and recall reasons
	 */
	public String descriptionsToString()
	{
		StringBuffer returnDescriptions = new StringBuffer("");
		if(this.descriptions == null)
		{
			returnDescriptions.append("Not Available").append(", ");
		}
		else
		{
			for(String d : this.descriptions)
			{
				returnDescriptions.append(d).append(", ");
			}	
		}
		returnDescriptions.deleteCharAt(returnDescriptions.length()-2); 
		return returnDescriptions.toString();
	}
	
	/**
	 * A string representation of the UPC codes of the product
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of UPC product codes
	 */
	public String upcsToString()
	{
		StringBuffer returnUpcs = new StringBuffer("");
		if(this.upcs == null)
		{
			returnUpcs.append("Not Available").append(", ");
		}
		else
		{
			for(String u : this.upcs)
			{
				returnUpcs.append(u).append(", ");
			}	
		}
		returnUpcs.deleteCharAt(returnUpcs.length()-2); 
		return returnUpcs.toString();
	}
	
	/**
	 * A string representation of the hazards of the product
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of hazards associated with the product
	 */
	public String hazardsToString()
	{
		StringBuffer returnHazards = new StringBuffer("");
		if(this.hazards == null)
		{
			returnHazards.append("Not Available").append(", ");
		}
		else
		{
			for(String h : this.hazards)
			{
				returnHazards.append(h).append(", ");
			}	
		}
		returnHazards.deleteCharAt(returnHazards.length()-2); 
		return returnHazards.toString();
	}
	
	/**
	 * A string representation of the countries where the product was made
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for CPSC
	 * 
	 * @return A list of countries where the product was made
	 */
	public String countriesToString()
	{
		StringBuffer returnCountries = new StringBuffer("");
		
		if(this.countries == null)
		{
			returnCountries.append("Not Available").append(", ");
		}
		else
		{
			for(String c : this.countries)
			{
				returnCountries.append(c).append(", ");
			}	
		}
		returnCountries.deleteCharAt(returnCountries.length()-2); 
		return returnCountries.toString();
	}
	
	/*
	 * ----------------------------------------------------------------
	 */
	
	/**
	 * A string representation of the description of the product and the reason for a recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for FDA and USDA
	 * 
	 * @return A string of the description and reason for a recall of the product
	 */
	public String descriptionToString()
	{
		if(this.description == null)
		{
			return "Not Available";
		}
		else
		{
			return this.description;
		}
	}
	
	/**
	 * A string representation of the summary of the recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for FDA and USDA
	 * 
	 * @return A string of the summary of the recall
	 */
	public String summaryToString()
	{
		if(this.summary == null)
		{
			return "Not Available";
		}
		else
		{
			return this.summary;
		}	
	}
	
	/*
	 * ----------------------------------------------------------------
	 */

	/**
	 * A string representation of a list of vehicle records
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A list of vehicle Records objects
	 */
	public String recordsToString()
	{
		StringBuffer returnRecords = new StringBuffer("");
		for(Records r : this.records)
		{
			returnRecords.append(r.toString()).append("\n");
		}
		returnRecords.deleteCharAt(returnRecords.length()-1); 
		return returnRecords.toString();
	}
	
	/**
	 * A string representation of the manufacturer campaign number
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the manufacturer campaign number
	 */
	public String manufacturerCampaignNumberToString() 
	{
		if(this.manufacturer_campaign_number == null)
		{
			return "Not Available";
		}
		else
		{
			return this.manufacturer_campaign_number;
		}	
	}
	
	/**
	 * A string representation of the component description of the vehicle
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the component description
	 */
	public String componentDescriptionToString()
	{
		if(this.component_description == null)
		{
			return "Not Available";
		}
		else
		{
			return this.component_description;
		}
	}

	/**
	 * A string representation of the manufacturer of the vehicle
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the manufacturer of the vehicle
	 */
	public String manufacturerToString() 
	{
		if(this.manufacturer == null)
		{
			return "Not Available";
		}
		else
		{
			return this.manufacturer;
		}	
	}
	
	/**
	 * A string representation of the code of the recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the code of the recall
	 */
	public String codeToString() 
	{
		if(this.code == null)
		{
			return "Not Available";
		}
		else
		{
			return this.code;
		}	
	}

	/**
	 * A string representation of the number of vehicles affected by the recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the number of affected vehicles
	 */
	public String potentialUnitsAffectedToString() 
	{
		if(this.potential_units_affected == null)
		{
			return "Not Available";
		}
		else
		{
			return this.potential_units_affected;
		}	
	}

	/**
	 * A string representation of the initiator of the recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the initiator
	 */
	public String initiatorToString() 
	{
		if(this.initiator == null)
		{
			return "Not Available";
		}
		else
		{
			return this.initiator;
		}
	}

	/**
	 * A string representation of the report date of the problem
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the report date
	 */
	public String reportDateToString() 
	{
		if(this.report_date == null)
		{
			return "Not Available";
		}
		else
		{
			return this.report_date;
		}
	}

	/**
	 * A string representation of the defect summary
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the defect summary
	 */
	public String defectSummaryToString() 
	{
		if(this.defect_summary == null)
		{
			return "Not Available";
		}
		else
		{
			return this.defect_summary;
		}
	}

	/**
	 * A string representation of the description of the problem
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the consequence summary
	 */
	public String consequenceSummaryToString() 
	{
		if(this.consequence_summary == null)
		{
			return "Not Available";
		}
		else
		{
			return this.consequence_summary;
		}	
	}

	/**
	 * A string representation of the description of actions to take
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the corrective summary
	 */
	public String correctiveSummaryToString() 
	{
		if(this.corrective_summary == null)
		{
			return "Not Available";
		}
		else
		{
			return this.corrective_summary;
		}	
	}

	/**
	 * A string representation of a note on the recall
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of a note on the recall
	 */
	public String notesToString() 
	{
		if(this.notes == null)
		{
			return "Not Available";
		}
		else
		{
			return this.notes;
		}
	}

	/**
	 * A string representation of the recall subject
	 * Replaces null with "Not Available" if applicable
	 * Only applicable for NHTSA
	 * 
	 * @return A string of the recall subject
	 */
	public String recallSubjectToString() 
	{
		if(this.recall_subject == null)
		{
			return "Not Available";
		}
		else
		{
			return this.recall_subject;
		}
	}
}
