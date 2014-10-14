package de.lbl.purchasewatcher;

import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import android.widget.AbsListView.*;
import de.lbl.purchasewatcher.NavigationDrawerFragment.*;

public class NavigationDrawerAdapter extends BaseAdapter
{
	List<DrawerMenuItem> menuItems;

	private AbsListView.LayoutParams params;
	
	public NavigationDrawerAdapter(){
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
		
		if(view == null){
			v = new TextView(App.context);
			v.setLayoutParams(params);
			v.setTextSize(16);
			v.setGravity(Gravity.CENTER_VERTICAL);
			v.setTextColor(App.context.getResources().getColor(android.R.color.holo_red_dark));
		}
		else
			v = (TextView) view;
			
		v.setText(dmi.view_title);
		v.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v)
				{
					Bundle b = new Bundle();
					b.putString(Constants.KEY_VIEW_NAME,dmi.view_name);
					b.putString(Constants.KEY_VIEW_MODE,dmi.mode);
					SystemController.tryAction(Constants.ACTION_DISPLAY_VIEW, b);
					
					//App.toast("drawer clicked");
				}
			});
			
		return v;
	}
	
	public void setDrawerMenu()
	{
		List<DrawerMenuItem> list = new ArrayList<>();

		DrawerMenuItem item = new DrawerMenuItem();

		item.view_name = Constants.VIEW_PURCHASE;
		item.view_title = "Purchases Overview";
		item.mode = Constants.MODE_OVERVIEW;
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = Constants.VIEW_THINGY;
		item.view_title = "Thingys Overview";
		item.mode = Constants.MODE_OVERVIEW;
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = Constants.VIEW_PURCHASE;
		item.view_title = "Purchase Editor";
		item.mode = Constants.MODE_EDIT;
		list.add(item);
		item = new DrawerMenuItem();

		item.view_name = Constants.VIEW_THINGY;
		item.view_title = "Thingy Editor";
		item.mode = Constants.MODE_EDIT;
		list.add(item);
		item = new DrawerMenuItem();

		menuItems = list;
		params = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
		
	}
	

	public class DrawerMenuItem{
		public String view_name;
		public String view_title;
		public String mode;
	}
}
