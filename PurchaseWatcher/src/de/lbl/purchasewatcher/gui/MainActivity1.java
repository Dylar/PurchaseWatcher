package de.lbl.purchasewatcher.gui;

import android.app.*;
import android.os.*;
import android.support.v4.widget.*;
import de.lbl.purchasewatcher.*;
import de.lbl.purchasewatcher.gui.drawer.*;
import de.lbl.purchasewatcher.system.*;

public class MainActivity1 extends Activity implements Gui
{

	@Override
	public void openView(Bundle data)
	{
		// TODO: Implement this method
	}

	@Override
	public void onDrawerOpened()
	{
		// TODO: Implement this method
	}

	@Override
	public void openDrawer()
	{
		// TODO: Implement this method
	}

	@Override
	public void onNavigationDrawerItemSelected(Bundle data)
	{
		// TODO: Implement this method
	}

	@Override
	public void onSectionAttached(Bundle data)
	{
		// TODO: Implement this method
	}

	@Override
	public void updateView()
	{
		// TODO: Implement this method
	}

	@Override
	public void back()
	{
		// TODO: Implement this method
	}

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
		//App.debug("muh");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.

		NavigationDrawerAdapter adapt = new NavigationDrawerAdapter();

		//mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), adapt);
	}
	
	
}
