package edu.vt.cs5744;

import java.util.ArrayList;


public class Results 
{
	private String organization;
	private String recall_number;
	private String recall_date;
	private String recall_url;
	
	private ArrayList<String> manufacturers;
	private ArrayList<String> product_types;
	private ArrayList<String> descriptions;
	private ArrayList<String> upcs;
	private ArrayList<String> hazards;
	private ArrayList<String> countries;	
	
	private String description;
	private String summary;
	
	private ArrayList<Records> records;
	private String manufacturer_campaign_number;
	private String component_description;
	private String manufacturer;
	private String code;
	private String potential_units_affected;
	private String initiator;
	private String report_date;
	private String defect_summary;
	private String consequence_summary;
	private String corrective_summary;
	private String notes;
	private String recall_subject;
	
	public String getOrganization()
	{
		return this.organization;
	}
	
	public String getRecallNumber()
	{
		return this.recall_number;
	}
	
	public String getRecallDate()
	{
		return this.recall_date;
	}
	
	public String getRecallUrl() 
	{
		return this.recall_url;
	}

	public ArrayList<String> getManufacturers() 
	{
		return this.manufacturers;	
	}

	public ArrayList<String> getProductTypes() 
	{
		return this.product_types;	
	}
	
	public ArrayList<String> getDescriptions()
	{
		return this.descriptions;
	}

	public ArrayList<String> getUpcs() 
	{
		return this.upcs;
	}

	public ArrayList<String> getHazards() 
	{
		return this.hazards;
	}

	public ArrayList<String> getCountries() 
	{
		return this.countries;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getSummary()
	{
		return this.summary;
	}

	public String getComponentDescription()
	{
		return this.component_description;
	}
	
	public ArrayList<Records> getRecords() 
	{
		return this.records;
	}

	public String getManufacturerCampaignNumber() 
	{
		return this.manufacturer_campaign_number;
	}

	public String getManufacturer() 
	{
		return this.manufacturer;
	}
	
	public String getCode() 
	{
		return this.code;
	}

	public String getPotentialUnitsAffected() 
	{
		return this.potential_units_affected;
	}

	public String getInitiator() 
	{
		return this.initiator;
	}

	public String getReportDate() 
	{
		return this.report_date;
	}

	public String getDefectSummary() 
	{
		return this.defect_summary;
	}

	public String getConsequenceSummary() 
	{
		return this.consequence_summary;
	}

	public String getCorrectiveSummary() 
	{
		return this.corrective_summary;
	}

	public String getNotes() {
		return this.notes;
	}

	public String getRecallSubject() 
	{
		return this.recall_subject;
	}
	
	/*
	 * ----------------------------------------------------------------
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
	
	public String manufacturersToString()
	{
		StringBuffer returnManufacturers = new StringBuffer("");
		for(String m : this.manufacturers)
		{
			if(m == null)
			{
				returnManufacturers.append("Not Available").append(", ");
			}
			else
			{
				returnManufacturers.append(m).append(", ");
			}
		}
		returnManufacturers.deleteCharAt(returnManufacturers.length()-2); 
		return returnManufacturers.toString();
	}
	
	public String typeToString()
	{
		StringBuffer returnType = new StringBuffer("");
		for(String t : this.product_types)
		{
			if(t == null)
			{
				returnType.append("Not Available").append(", ");
			}
			else
			{
				returnType.append(t).append(", ");
			}	
		}
		returnType.deleteCharAt(returnType.length()-2); 
		return returnType.toString();
	}
	
	public String descriptionsToString()
	{
		StringBuffer returnDescriptions = new StringBuffer("");
		for(String d : this.descriptions)
		{
			if(d == null)
			{
				returnDescriptions.append("Not Available").append(", ");
			}
			else
			{
				returnDescriptions.append(d).append(", ");
			}	
		}
		returnDescriptions.deleteCharAt(returnDescriptions.length()-2); 
		return returnDescriptions.toString();
	}
	
	public String upcsToString()
	{
		StringBuffer returnUpcs = new StringBuffer("");
		for(String u : this.upcs)
		{
			if(u == null)
			{
				returnUpcs.append("Not Available").append(", ");
			}
			else
			{
				returnUpcs.append(u).append(", ");
			}
		}
		returnUpcs.deleteCharAt(returnUpcs.length()-2); 
		return returnUpcs.toString();
	}
	
	public String hazardsToString()
	{
		StringBuffer returnHazards = new StringBuffer("");
		for(String h : this.hazards)
		{
			if(h == null)
			{
				returnHazards.append("Not Available").append(", ");
			}
			else
			{
				returnHazards.append(h).append(", ");
			}
		}
		returnHazards.deleteCharAt(returnHazards.length()-2); 
		return returnHazards.toString();
	}
	
	public String countriesToString()
	{
		StringBuffer returnCountries = new StringBuffer("");
		for(String c : this.countries)
		{
			if(c == null)
			{
				returnCountries.append("Not Available").append(", ");
			}
			else
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
