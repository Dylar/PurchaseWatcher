package de.lbl.purchasewatcher.gui;

import android.os.*;
import de.lbl.purchasewatcher.R;

public interface Gui
{
	public void openView(Bundle data);
	
	public void onDrawerOpened();
	public void openDrawer();
	public void onNavigationDrawerItemSelected(Bundle data);

	public void onSectionAttached(Bundle data);
	public void updateView();
	public void back();
}
