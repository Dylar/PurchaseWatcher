package de.lbl.purchasewatcher.gui;
import android.os.*;
import de.lbl.purchasewatcher.system.*;
import android.view.*;
import android.widget.*;

public class StartViewFragment extends MainViewFragment
{

	@Override
	public void changeMode(Bundle data)
	{
		// TODO: Implement this method
	}


	@Override
	public void updateView()
	{
		// TODO: Implement this method
	}


	@Override
	public void handleActionbarClick(MenuItem item)
	{
		gui.openDrawer();
	}


	@Override
	public void setUpFragment(Bundle data)
	{
		name = data.getString(Constants.KEY_VIEW_NAME);
		title = "Startmenu";
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		TextView v = new TextView(inflater.getContext());
		v.setText("Hallo");
		return v;
	}

}
