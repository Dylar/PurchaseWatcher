package de.lbl.purchasewatcher.gui.drawer;

import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.gui.GuiManager;
import de.lbl.purchasewatcher.gui.MainViewFragment;
import de.lbl.purchasewatcher.gui.drawer.NavigationDrawerFragment.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import android.widget.AbsListView.*;

public class NavigationDrawerAdapter extends BaseAdapter
{
	List<DrawerMenuItem>					menuItems;

	private AbsListView.LayoutParams	params;


	public NavigationDrawerAdapter()
	{
		setDrawerMenu();
	}


	@Override
	public int getCount()
	{
		return menuItems.size();
	}


	@Override
	public DrawerMenuItem getItem(int p1)
	{
		return menuItems.get(p1);
	}


	@Override
	public long getItemId(int p1)
	{
		return p1;
	}


	@Override
	public View getView(int pos, View view, ViewGroup parent)
	{
		final DrawerMenuItem dmi = getItem(pos);

		TextView v = null;

		if (view == null)
		{
			v = new TextView(App.ctx);
			v.setLayoutParams(params);
			v.setTextSize(16);
			v.setGravity(Gravity.CENTER_VERTICAL);
			v.setTextColor(App.ctx.getResources().getColor(android.R.color.holo_red_dark));
		}
		else
			v = (TextView) view;

		v.setText(dmi.view_title);
		v.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				Bundle b = new Bundle();
				b.putString(MainViewFragment.KEY_VIEW_TITLE, dmi.view_name);
				GuiManager.manager.openView(b);
			}
		});

		return v;
	}


	public void setDrawerMenu()
	{
		List<DrawerMenuItem> list = new ArrayList<>();

		DrawerMenuItem item = new DrawerMenuItem();

		item.view_name = MainViewFragment.VIEWNAME_OVERVIEW;
		item.view_title = App.ctx.getString(R.string.title_overview);
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = MainViewFragment.VIEWNAME_PURCHASE_OVERVIEW;
		item.view_title = App.ctx.getString(R.string.title_purchases);
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = MainViewFragment.VIEWNAME_THINGY_OVERVIEW;
		item.view_title = App.ctx.getString(R.string.title_thingys);
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = MainViewFragment.VIEWNAME_EDITOR;
		item.view_title = App.ctx.getString(R.string.title_editor);
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = MainViewFragment.VIEWNAME_CALENDAR;
		item.view_title = App.ctx.getString(R.string.title_calendar);
		list.add(item);
		item = new DrawerMenuItem();

		menuItems = list;
		params = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);

	}

	public class DrawerMenuItem
	{
		public String	view_name;
		public String	view_title;
		public String	mode;
	}
}
