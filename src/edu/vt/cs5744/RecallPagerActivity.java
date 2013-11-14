package edu.vt.cs5744;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class RecallPagerActivity extends FragmentActivity 
{
	private ViewPager mViewPager;
	private Recalls mApi;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        
        mApi = RecallReceiver.get(this).getRecalls();
        
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) 
        {
            @Override
            public int getCount() 
            {
            	return mApi.getSuccess().getResults().size();
            }

            @Override
            public Fragment getItem(int pos) 
            {
            	Results recall = mApi.getSuccess().getResults().get(pos);
                return RecallFragment.newInstance(recall.getRecallNumber());
            }
        });
        
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
        
        String recall_number = (String)getIntent()
                .getSerializableExtra(RecallFragment.EXTRA_RECALL_ID);
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
