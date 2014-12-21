package de.lbl.purchasewatcher.system;

import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.gui.GuiManager;
import de.lbl.purchasewatcher.model.DataManager;
import de.lbl.purchasewatcher.model.PurchaseManager;
import de.lbl.purchasewatcher.model.ThingyManager;

public class App extends Application
{
	public static Context ctx;

	public static void debug(String s)
	{
		Log.d("debug", s);
	}

	public static void toast(String s)
	{
		Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onCreate()
	{
		ctx = getApplicationContext();
		GuiManager.manager = new GuiManager();
		DataManager.manager = new DataManager();
		ThingyManager.manager = new ThingyManager();
		PurchaseManager.manager = new PurchaseManager();
		super.onCreate();
	}

	public static <T extends View> T findView(View v, int... id)
	{
		View t = v;
		for(int i : id){
			t = t.findViewById(i);
		}
		return (T) t;
	}
}
