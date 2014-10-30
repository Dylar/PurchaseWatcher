package de.lbl.purchasewatcher;

import android.app.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.gui.*;
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
		if (name == null)
		{
			data.putString(Constants.KEY_VIEW_NAME, Constants.VIEW_START);
		}
		displayView(data);
	}


	@Override
	public void displayView(Bundle data)
	{
		String view = data.getString(Constants.KEY_VIEW_NAME);

		MainViewFragment frag = getCurrentView();
		if (frag != null && frag.getViewName().equals(view))
		{
			frag.changeMode(data);
		}
		else
		{
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
		//App.toast("main act item selected");

		int id = item.getItemId();
		if (id == R.id.action_view_menu)
		{
			final MainViewFragment frag = getCurrentView();
			//Creating the instance of PopupMenu 
            PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.action_view_menu));  
            //Inflating the Popup using xml file  
            popup.getMenuInflater().inflate(frag.getViewMenuLayoutId(), popup.getMenu());  

            //registering popup with OnMenuItemClickListener  
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
					public boolean onMenuItemClick(MenuItem item)
					{  
						//Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();  
						frag.handleActionbarClick(item);
						return true;  
					}  
				});  

            popup.show();//showing popup menu  
			return true;
		}

//		App.toast("in main act");
//		if (item.getItemId() == R.id.action_settings)
//		{
//			App.toast("settings clicked. im drawer");
//			return true;
//		}

		return super.onOptionsItemSelected(item);
	}

	private MainViewFragment getCurrentView()
	{
		return (MainViewFragment) getFragmentManager().findFragmentById(R.id.container);
	}

	@Override
	public void onSectionAttached(Bundle data)
	{
		mTitle = data.getString(Constants.KEY_VIEW_TITLE);
	}

	@Override
	public void onDrawerOpened()
	{
		//App.toast("main drawer opened");
	}
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
	public void onBackPressed()
	{
		MainViewFragment frag = getCurrentView();
		if (frag.getMode().equals(Constants.MODE_OVERVIEW))
			super.onBackPressed();
		else
		{
			Bundle b = new Bundle();
			b.putString(Constants.KEY_VIEW_MODE, Constants.MODE_OVERVIEW);
			frag.changeMode(b);
		}
	}

	@Override
	public void back()
	{
		onBackPressed();
	}
}
