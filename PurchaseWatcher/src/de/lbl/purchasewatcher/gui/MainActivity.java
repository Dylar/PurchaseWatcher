package de.lbl.purchasewatcher.gui;

import android.app.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.R.id;
import de.lbl.purchasewatcher.R.layout;
import de.lbl.purchasewatcher.R.menu;
import de.lbl.purchasewatcher.gui.*;
import de.lbl.purchasewatcher.gui.calendar.CalendarFragment;
import de.lbl.purchasewatcher.gui.drawer.NavigationDrawerAdapter;
import de.lbl.purchasewatcher.gui.drawer.NavigationDrawerFragment;
import de.lbl.purchasewatcher.gui.purchase.PurchaseDetailFragment;
import de.lbl.purchasewatcher.gui.purchase.PurchaseOverviewFragment;
import de.lbl.purchasewatcher.gui.statistic.StatisticFragment;
import de.lbl.purchasewatcher.gui.thingy.ThingEditorFragment;
import de.lbl.purchasewatcher.gui.thingy.ThingyDetailFragment;
import de.lbl.purchasewatcher.gui.thingy.ThingyEditorFragment;
import de.lbl.purchasewatcher.gui.thingy.ThingyOverviewFragment;
import de.lbl.purchasewatcher.system.*;

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
		App.debug("muh");
		//super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_main);

		//mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		//mTitle = getTitle();

		// Set up the drawer.

		//NavigationDrawerAdapter adapt = new NavigationDrawerAdapter();

		//mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), adapt);
	}


	@Override
	public void onNavigationDrawerItemSelected(Bundle data)
	{
		String name = data.getString(MainViewFragment.KEY_VIEW_TITLE);
		if (name == null)
		{
			data.putString(MainViewFragment.KEY_VIEW_TITLE, MainViewFragment.VIEWNAME_OVERVIEW);
		}
		openView(data);
	}


	@Override
	public void openView(Bundle data)
	{
		String viewName = data.getString(MainViewFragment.KEY_VIEW_TITLE);

		MainViewFragment frag = getCurrentView();
		if (!frag.getViewName().equals(viewName))
		{
			switch (viewName)
			{
				case MainViewFragment.VIEWNAME_THINGY_OVERVIEW:
					frag = new ThingyOverviewFragment();
					break;
				case MainViewFragment.VIEWNAME_THINGY_DETAIL:
					frag = new ThingyDetailFragment();
					break;
				case MainViewFragment.VIEWNAME_EDITOR:
					frag = new ThingEditorFragment();
					break;
					
				case MainViewFragment.VIEWNAME_PURCHASE_OVERVIEW:
					frag = new PurchaseOverviewFragment();
					break;
				case MainViewFragment.VIEWNAME_PURCHASE_DETAILS:
					frag = new PurchaseDetailFragment();
					break;

				case MainViewFragment.VIEWNAME_OVERVIEW:
					frag = new OverviewFragment();
					break;
				case MainViewFragment.VIEWNAME_CALENDAR:
					frag = new CalendarFragment();
					break;
				case MainViewFragment.VIEWNAME_STATISTIC:
					frag = new StatisticFragment();
					break;
					
			}
			frag.setArguments(data);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.container, frag).commit();

		}
		
		if (mNavigationDrawerFragment != null)
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
		// App.toast("main act item selected");

		int id = item.getItemId();
		if (id == R.id.action_view_menu)
		{
			final MainViewFragment frag = getCurrentView();
			// Creating the instance of PopupMenu
			PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.action_view_menu));
			// Inflating the Popup using xml file
			popup.getMenuInflater().inflate(frag.getViewMenuLayoutId(), popup.getMenu());

			// registering popup with OnMenuItemClickListener
			popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item)
				{
					frag.handleActionbarClick(item);
					return true;
				}
			});

			popup.show();// showing popup menu
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	private MainViewFragment getCurrentView()
	{
		return (MainViewFragment) getFragmentManager().findFragmentById(R.id.container);
	}

	@Override
	public void onSectionAttached(Bundle data)
	{
		mTitle = data.getString(MainViewFragment.KEY_VIEW_TITLE);
	}


	@Override
	public void onDrawerOpened()
	{}


	@Override
	public void openDrawer()
	{
		mNavigationDrawerFragment.openDrawer();
	}


	@Override
	public void updateView()
	{
		getCurrentView().updateView();
	}

	@Override
	public void back()
	{
		onBackPressed();
	}
}
