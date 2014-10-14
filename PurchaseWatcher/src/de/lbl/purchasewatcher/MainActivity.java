package de.lbl.purchasewatcher;

import android.app.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import de.lbl.purchasewatcher.gui.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;

public class MainActivity extends Activity implements Gui
{
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment	mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence					mTitle;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (App.context == null)
			App.context = getApplicationContext();
		//TODO CHECK FOR LEAK 
		SystemController.sc.startSystem(this);

		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.

		NavigationDrawerAdapter adapt = new NavigationDrawerAdapter();
		
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), adapt);
	}

	
	@Override
	public void onNavigationDrawerItemSelected(Bundle data)
	{
		String name = data.getString(Constants.KEY_VIEW_NAME);
		if(name == null){
			data.putString(Constants.KEY_VIEW_NAME, Constants.VIEW_START);
		}
		displayView(data);
	}


	@Override
	public void displayView(Bundle data)
	{
		String view = data.getString(Constants.KEY_VIEW_NAME);
		
		MainViewFragment frag = null;

		switch (view)
		{
			case Constants.VIEW_THINGY:
				frag = new ThingyViewFragment();
				break;
			case Constants.VIEW_PURCHASE:
				frag = new PurchaseViewFragment();
				break;
			case Constants.VIEW_START:
				frag = new StartViewFragment();
				break;
		}
		frag.setArguments(data);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, frag).commit();
		if(mNavigationDrawerFragment != null)
			mNavigationDrawerFragment.closeDrawer();
	}


	public void restoreActionBar()
	{
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if (!mNavigationDrawerFragment.isDrawerOpen())
		{
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSectionAttached(Bundle data)
	{
		mTitle = data.getString(Constants.KEY_VIEW_TITLE);
	}

}
