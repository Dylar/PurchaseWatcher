package de.lbl.purchasewatcher.system;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;

public class App extends Application
{
	public static Context context;

	public static void toast(String s)
	{
		Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onCreate()
	{
		context = getApplicationContext();
		super.onCreate();
	}

	public static <T extends View> T findView(View v, int id)
	{
		return (T) v.findViewById(id);
	}
}
