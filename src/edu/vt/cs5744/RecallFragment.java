package edu.vt.cs5744;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * The purpose of this class is to use fragments to display
 * individual recalls in a more detailed view.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class RecallFragment extends Fragment 
{
	// Map key for putSerializable command
	public static final String RECALL_ID = "vt.edu.cs5744.recall_id";
	private Results mApi;	// Results object
	
	/**
	 * Called when the activity first starts up.
	 * 
	 * @param savedInstanceState Potentially used to re-constructed from a previous saved state
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	
    	// Saves data between orientation changes.
    	setRetainInstance(true);
    	
    	// Retrieves the value in the map of the Bundle.
    	String recall_number = (String)getArguments().getSerializable(RECALL_ID);
    	
    	// Retrieves the values in the Results object.
    	mApi = RecallReceiver.get(getActivity()).getResults(recall_number);
    }
    
    /**
     * Creates the view and sets which layout is used to display the details of the recalls.
     * 
     * @param inflater A LayoutInflater that can be used to inflate any views in the fragment
     * @param parent Can be used to generate the LayoutParams of the view
     * @param savedInstanceState Potentially used to re-constructed from a previous saved state
     * @return The View associated with the activity
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) 
    {
    	// Places all recalls associated with CPSC in this layout.
        if(mApi.getOrganization().equalsIgnoreCase("CPSC"))
		{
        	View v = inflater.inflate(R.layout.fragment_recall_cpsc, parent, false);
        	
        	TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
        	mOrganizationField.setText("Organization: " + mApi.organizationToString());
        	
            TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
            mNumberField.setText("ID: " + mApi.recallNumberToString());
            
            TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
            mDateField.setText("Date: " + mApi.recallDateToString());
            
            TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
            mUrlField.setText("URL: " + mApi.recallUrlToString());
            
            TextView mManufacturersField = (TextView)v.findViewById(R.id.recall_manufacturers);
            mManufacturersField.setText("Manufacturers: " + mApi.manufacturersToString());
            
            TextView mTypeField = (TextView)v.findViewById(R.id.recall_types);
            mTypeField.setText("Types: " + mApi.typeToString());
            
            TextView mDescriptionsField = (TextView)v.findViewById(R.id.recall_descriptions);
            mDescriptionsField.setText("Descriptions: " + mApi.descriptionsToString());
            
            TextView mHazardsField = (TextView)v.findViewById(R.id.recall_hazards);
            mHazardsField.setText("Hazards: " + mApi.hazardsToString());
            
            TextView mCountriesField = (TextView)v.findViewById(R.id.recall_countries);
            mCountriesField.setText("Countries: " + mApi.countriesToString());  
            
            return v;
		}
        // Places all recalls associated with NHTSA in this layout.
		else if(mApi.getOrganization().equalsIgnoreCase("NHTSA"))
		{
			View v = inflater.inflate(R.layout.fragment_recall_nhtsa, parent, false);
			
			TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
        	mOrganizationField.setText("Organization: " + mApi.organizationToString());
        	
            TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
            mNumberField.setText("ID: " + mApi.recallNumberToString());
            
            TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
            mDateField.setText("Date: " + mApi.recallDateToString());
            
            TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
            mUrlField.setText("URL: " + mApi.recallUrlToString());
            
            TextView mRecordsField = (TextView)v.findViewById(R.id.recall_records);
            mRecordsField.setText(mApi.recordsToString());
	        
            TextView mManufacturerCampaignNumberField = 
            		(TextView)v.findViewById(R.id.recall_manufacturer_campaign_number);
            mManufacturerCampaignNumberField.setText("Manufacturer Campaign Number: " 
            		+ mApi.manufacturerCampaignNumberToString());
            
            TextView mComponentDescriptionField = 
            		(TextView)v.findViewById(R.id.recall_component_description);
            mComponentDescriptionField.setText("Component Description: " 
            		+ mApi.componentDescriptionToString());
            
            TextView mManufacturerField = (TextView)v.findViewById(R.id.recall_manufacturer);
            mManufacturerField.setText("Manufacturer: " + mApi.manufacturerToString());
            
            TextView mCodeField = (TextView)v.findViewById(R.id.recall_code);
            mCodeField.setText("Code: " + mApi.codeToString());
            
            TextView mPotentialUnitsAffectedField = 
            		(TextView)v.findViewById(R.id.recall_potential_units_affected);
            mPotentialUnitsAffectedField.setText("Potential Units Affected: " 
            		+ mApi.potentialUnitsAffectedToString());
            
            TextView mInitiatorField = (TextView)v.findViewById(R.id.recall_initiator);
            mInitiatorField.setText("Initiator: " + mApi.initiatorToString());
            
            TextView mReportDateField = (TextView)v.findViewById(R.id.recall_report_date);
            mReportDateField.setText("Report Date: " + mApi.reportDateToString());
            
            TextView mDefectSummaryField = (TextView)v.findViewById(R.id.recall_defect_summary);
            mDefectSummaryField.setText("Defect Summary: " + mApi.defectSummaryToString());
            
            TextView mConsequenceSummaryField = 
            		(TextView)v.findViewById(R.id.recall_consequence_summary);
            mConsequenceSummaryField.setText("Consequence Summary: " 
            		+ mApi.consequenceSummaryToString());
            
            TextView mCorrectiveSummaryField = 
            		(TextView)v.findViewById(R.id.recall_corrective_summary);
            mCorrectiveSummaryField.setText("Corrective Summary: " 
            		+ mApi.correctiveSummaryToString());
            
            TextView mNotesField = (TextView)v.findViewById(R.id.recall_notes);
            mNotesField.setText("Notes: " + mApi.notesToString());
            
            TextView mRecallSubjectField = (TextView)v.findViewById(R.id.recall_subject);
            mRecallSubjectField.setText("Subject: " + mApi.recallSubjectToString());
            
	        return v;
		}
        // Places all recalls associated with FDA or USDA in this layout.
		else if(mApi.getOrganization().equalsIgnoreCase("FDA") || 
				mApi.getOrganization().equalsIgnoreCase("USDA"))
		{
			View v = inflater.inflate(R.layout.fragment_recall_fdausda, parent, false);
			
			TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
			mOrganizationField.setText("Organization: " + mApi.organizationToString());
	        
	        TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
	        mNumberField.setText("ID: " + mApi.recallNumberToString());
	        
	        TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
	        mDateField.setText("Date: " + mApi.recallDateToString());
	        
	        TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
	        mUrlField.setText("URL: " + mApi.recallUrlToString());
	        
	        TextView mDescriptionField = (TextView)v.findViewById(R.id.recall_description);
	        mDescriptionField.setText("Description: " + mApi.descriptionToString());
	        
	        TextView mSummaryField = (TextView)v.findViewById(R.id.recall_summary);
	        mSummaryField.setText("Summary: " + mApi.summaryToString());
	        
	        return v;
		} 
		else
		{
			return null;
		}
    }
    
    /**
     * Creates new RecallFragments based on the recall ID.
     * 
     * @param RecallId The unique identifier for each recall
     * @return A new RecallFragment
     */
    public static RecallFragment newInstance(String RecallId) 
    {
        Bundle arguments = new Bundle();
        
        // Puts the recall id in a map in the Bundle.
        arguments.putSerializable(RECALL_ID, RecallId);

        // Creates new RecallFragments based on the recall ID.
        RecallFragment fragment = new RecallFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
