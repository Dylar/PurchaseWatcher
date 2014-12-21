package de.lbl.purchasewatcher.gui;

import android.app.Activity;
import android.os.Bundle;

public class GuiManager
{

	public static GuiManager	manager;
	
	private Gui gui;

	public void setActivity(Gui gui)
	{
		this.gui = gui;
	}

	public void onFragmentAttached(Bundle data)
	{
		gui.onSectionAttached(data);
	}
	
	public void back(){
		gui.back();
	}
	
	public void openView(Bundle data){
		gui.openView(data);
	}

	public Activity getCurrentActivity()
	{
		return (Activity) gui;
	}

}
