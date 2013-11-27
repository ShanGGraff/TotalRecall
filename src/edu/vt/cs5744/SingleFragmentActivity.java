package edu.vt.cs5744;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * The purpose of this class is to provide an abstract way to create an activity
 * that will host our fragments for the UI of this app.
 * 
 * @author Scott Chappell and Shane Graff
 */
public abstract class SingleFragmentActivity extends FragmentActivity 
{
	protected abstract Fragment createFragment();

	/**
	 * Used to create an activity that will host fragments.
	 * 
	 * @param savedInstanceState Certain saved states from the activity
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        // Inflate the view from activity_fragment_xml.
        setContentView(R.layout.activity_fragment);
        
        // Find the fragment in the FragmentManager.
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        // Create and add the fragment if it does not already exist.
        if (fragment == null) 
        {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}
