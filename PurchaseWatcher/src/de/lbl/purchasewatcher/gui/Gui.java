package de.lbl.purchasewatcher.gui;

import android.os.*;

public interface Gui
{
	public void displayView(Bundle data);
	
	public void onNavigationDrawerItemSelected(Bundle data);

	public void onSectionAttached(Bundle data);
}
