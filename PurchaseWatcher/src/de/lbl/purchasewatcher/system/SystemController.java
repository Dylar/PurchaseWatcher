package de.lbl.purchasewatcher.system;

import android.os.*;
import de.lbl.purchasewatcher.gui.*;
import java.util.*;
import de.lbl.purchasewatcher.model.*;
import java.lang.reflect.*;

public class SystemController
{
	private String TAG = "SystemController";

	public static SystemController sc = new SystemController();
	private static Gui gui;
	public static DataHandler dataHandler;

	
	private SystemController()
	{
		
	}

	private void stopSystem()
	{
		//act.finish();
		gui = null;
	}

	public void startSystem(Gui act)
	{
		// TODO LOADING IN BACKGROUND + SPLASH
		
		this.gui = act;
		dataHandler = new DataHandler();
		//dataHandler.loadingData();
	}

	public static void tryAction(int action,Bundle data)
	{
		switch (action)
		{
			case Constants.ACTION_DISPLAY_VIEW:
				gui.displayView(data);
				break;
//			case START_SERVICE:
//				startService();
//				break;
		}
	}
}
	

//	private static SystemAction getNewAction(int action, Bundle data)
//	{
//		SystemAction sa = null;
//		if (sc.actionPool.isEmpty())
//			sa = sc.new SystemAction();
//		else sa = sc.actionPool.poll();
//		sa.action = action;
//		sa.data = data;
//		return sa;
//	}

//	public static SystemController GetInstance()
//	{
//		return sc;
//	}

//	public void addToPool(SystemAction sa)
//	{
//		actionPool.add(sa);
//	}
//
//
//	public class SystemAction
//	{
//		public int action;
//		public Bundle data;
//
//
//	}


