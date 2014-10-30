package de.lbl.purchasewatcher.system;

import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class App extends Application
{
	public static Context context;

	public static void debug(String s)
	{
		Log.i("debug", s);
		Log.d("debug", s);
	}

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

	public static <T extends View> T findView(View v, int... id)
	{
		View t = v;
		for(int i : id){
			t = t.findViewById(i);
		}
		return (T) t;
	}
}
