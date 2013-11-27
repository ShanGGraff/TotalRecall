package edu.vt.cs5744;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * The purpose of this class is implement a View Pager that allows 
 * individual recall pages to be navigated with page swipes.
 * 
 * @author Scott Chappell and Shane Graff
 */
public class RecallPagerActivity extends FragmentActivity 
{
	private ViewPager mViewPager;	// Stores a ViewPager object
	private Recalls mApi;			// Stores a Recalls object

	/**
	 * This creates the activity necessary for the View Pager.
	 * 
	 * @param savedInstanceState Certain saved states from the activity
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        // Creates a new ViewPager.
        mViewPager = new ViewPager(this);	
        
        // Sets the id for the ViewPager.
        mViewPager.setId(R.id.viewPager);	
        setContentView(mViewPager);
        
        // Retrieves the Recalls object with the results.
        mApi = RecallReceiver.get(this).getRecalls();
        
        // Adds each fragment to the Adapter.
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) 
        {
        	// Returns the size of the list.
            @Override
            public int getCount() 
            {
            	return mApi.getSuccess().getResults().size();
            }

            // Gets the position of the item in the list and returns a new RecallFragment.
            @Override
            public Fragment getItem(int pos) 
            {
            	Results recall = mApi.getSuccess().getResults().get(pos);
                return RecallFragment.newInstance(recall.getRecallNumber());
            }
        });
        
        // Displays the recall ID as the title of each page.
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() 
        {
            public void onPageScrollStateChanged(int state) { }

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) { }

            public void onPageSelected(int pos) 
            {
            	Results recall = mApi.getSuccess().getResults().get(pos);
        		if (recall.getRecallNumber() != null) 
                {
                    setTitle(recall.getRecallNumber());
                }  
            }
        });
        
        // This finds the correct recall result to display.
        String recall_number = (String)getIntent()
                .getSerializableExtra(RecallFragment.RECALL_ID);
        for (int i = 0; i < mApi.getSuccess().getResults().size(); i++) 
        {
        	if (mApi.getSuccess().getResults().get(i).getRecallNumber().equals(recall_number)) 
            {
        		mViewPager.setCurrentItem(i);
                break;
            } 
        }  
    }
}
