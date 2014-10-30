package de.lbl.purchasewatcher.gui;

import android.os.*;

public interface Gui
{
	public void displayView(Bundle data);
	
	public void onDrawerOpened();
	public void openDrawer();
	public void onNavigationDrawerItemSelected(Bundle data);

	public void onSectionAttached(Bundle data);
	public void updateView();
	public void back();
}
