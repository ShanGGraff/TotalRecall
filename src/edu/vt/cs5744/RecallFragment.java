package edu.vt.cs5744;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RecallFragment extends Fragment 
{
	public static final String EXTRA_RECALL_ID =
	        "vt.edu.sc5744.recall_id";
	private Results mApi;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	String recall_number = (String)getArguments().getSerializable(EXTRA_RECALL_ID);
    	
    	mApi = RecallReceiver.get(getActivity()).getResults(recall_number);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) 
    {
        if(mApi.getOrganization().equalsIgnoreCase("CPSC"))
		{
        	View v = inflater.inflate(R.layout.fragment_recall_cpsc, parent, false);
        	
        	TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
        	mOrganizationField.setText(mApi.getOrganization());
        	
            TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
            mNumberField.setText(mApi.getRecallNumber());
            
            TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
            mDateField.setText(mApi.getRecallDate());
            
            TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
            mUrlField.setText(mApi.getRecallUrl());
            
            TextView mManufacturersField = (TextView)v.findViewById(R.id.recall_manufacturers);
            mManufacturersField.setText(mApi.manufacturersToString());
            
            TextView mTypeField = (TextView)v.findViewById(R.id.recall_types);
            mTypeField.setText(mApi.typeToString());
            
            TextView mDescriptionsField = (TextView)v.findViewById(R.id.recall_descriptions);
            mDescriptionsField.setText(mApi.descriptionsToString());
            
            TextView mUpcsField = (TextView)v.findViewById(R.id.recall_upcs);
            mUpcsField.setText(mApi.upcsToString());
            
            TextView mHazardsField = (TextView)v.findViewById(R.id.recall_hazards);
            mHazardsField.setText(mApi.hazardsToString());
            
            TextView mCountriesField = (TextView)v.findViewById(R.id.recall_countries);
            mCountriesField.setText(mApi.countriesToString());  
            
            return v;
		}
		else if(mApi.getOrganization().equalsIgnoreCase("NHTSA"))
		{
			View v = inflater.inflate(R.layout.fragment_recall_nhtsa, parent, false);
			
			TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
        	mOrganizationField.setText(mApi.getOrganization());
        	
            TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
            mNumberField.setText(mApi.getRecallNumber());
            
            TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
            mDateField.setText(mApi.getRecallDate());
            
            TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
            mUrlField.setText(mApi.getRecallUrl());
            
            TextView mRecordsField = (TextView)v.findViewById(R.id.recall_records);
            mRecordsField.setText(mApi.recordsToString());
	        
            TextView mManufacturerCampaignNumberField = (TextView)v.findViewById(R.id.recall_manufacturer_campaign_number);
            mManufacturerCampaignNumberField.setText(mApi.getManufacturerCampaignNumber());
            
            TextView mComponentDescriptionField = (TextView)v.findViewById(R.id.recall_component_description);
            mComponentDescriptionField.setText(mApi.getComponentDescription());
            
            TextView mManufacturerField = (TextView)v.findViewById(R.id.recall_manufacturer);
            mManufacturerField.setText(mApi.getManufacturer());
            
            TextView mCodeField = (TextView)v.findViewById(R.id.recall_code);
            mCodeField.setText(mApi.getCode());
            
            TextView mPotentialUnitsAffectedField = (TextView)v.findViewById(R.id.recall_potential_units_affected);
            mPotentialUnitsAffectedField.setText(mApi.getPotentialUnitsAffected());
            
            TextView mInitiatorField = (TextView)v.findViewById(R.id.recall_initiator);
            mInitiatorField.setText(mApi.getInitiator());
            
            TextView mReportDateField = (TextView)v.findViewById(R.id.recall_report_date);
            mReportDateField.setText(mApi.getRecallDate());
            
            TextView mDefectSummaryField = (TextView)v.findViewById(R.id.recall_defect_summary);
            mDefectSummaryField.setText(mApi.getDefectSummary());
            
            TextView mConsequenceSummaryField = (TextView)v.findViewById(R.id.recall_consequence_summary);
            mConsequenceSummaryField.setText(mApi.getConsequenceSummary());
            
            TextView mCorrectiveSummaryField = (TextView)v.findViewById(R.id.recall_corrective_summary);
            mCorrectiveSummaryField.setText(mApi.getCorrectiveSummary());
            
            TextView mNotesField = (TextView)v.findViewById(R.id.recall_notes);
            mNotesField.setText(mApi.getNotes());
            
            TextView mRecallSubjectField = (TextView)v.findViewById(R.id.recall_recall_subject);
            mRecallSubjectField.setText(mApi.getRecallSubject());
            
	        return v;
		}
		else if(mApi.getOrganization().equalsIgnoreCase("FDA") || mApi.getOrganization().equalsIgnoreCase("USDA"))
		{
			View v = inflater.inflate(R.layout.fragment_recall_fdausda, parent, false);
			
			TextView mOrganizationField = (TextView)v.findViewById(R.id.recall_organization);
			mOrganizationField.setText(mApi.getOrganization());
	        
	        TextView mNumberField = (TextView)v.findViewById(R.id.recall_number);
	        mNumberField.setText(mApi.getRecallNumber());
	        
	        TextView mDateField = (TextView)v.findViewById(R.id.recall_date);
	        mDateField.setText(mApi.getRecallDate());
	        
	        TextView mUrlField = (TextView)v.findViewById(R.id.recall_url);
	        mUrlField.setText(mApi.getRecallUrl());
	        
	        TextView mDescriptionField = (TextView)v.findViewById(R.id.recall_description);
	        mDescriptionField.setText(mApi.getDescription());
	        
	        TextView mSummaryField = (TextView)v.findViewById(R.id.recall_summary);
	        mSummaryField.setText(mApi.getSummary());
	        
	        return v;
		} 
		else
		{
			return null;
		}
    }
    
    public static RecallFragment newInstance(String RecallId) 
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_RECALL_ID, RecallId);

        RecallFragment fragment = new RecallFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
