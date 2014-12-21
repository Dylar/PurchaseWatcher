package de.lbl.purchasewatcher.gui;
import android.app.*;
import de.lbl.purchasewatcher.system.*;
import android.os.*;
import android.view.*;
import de.lbl.purchasewatcher.*;

public abstract class MainViewFragment extends Fragment
{
	
	public static final String VIEWNAME_PURCHASE_DETAILS = "view_purchase_detail";
	public static final String VIEWNAME_PURCHASE_OVERVIEW = "view_purchase_overview";
	public static final String VIEWNAME_THINGY_DETAIL = "view_thingy_detail";
	public static final String VIEWNAME_THINGY_OVERVIEW = "view_thingy_overview";
	public static final String VIEWNAME_CALENDAR = "view_calendar";
	public static final String VIEWNAME_EDITOR = "view_editor";
	public static final String VIEWNAME_STATISTIC = "view_statistic";
	public static final String VIEWNAME_OVERVIEW = "view_overview";

	public static final String KEY_VIEW_TITLE = "key_view_title";
	
	public String name = VIEWNAME_OVERVIEW;
	public String title = "You forgot something - dunno wat";
	
	public abstract void setUpFragment(Bundle data);
	public abstract void updateView();
	public abstract void releaseFragment();
	public abstract void handleActionbarClick(MenuItem item);
	
	public int getViewMenuLayoutId(){
		return R.menu.view_btns;
	}
	
	public String getViewTitle(){
		return title;
	}
	
	public String getViewName(){
		return name;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void onDetach()
	{
		releaseFragment();
		super.onDetach();
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		if(activity instanceof Gui){
			GuiManager.manager.setActivity((Gui) activity);

			Bundle data = getArguments();
			
			title = data.getString(KEY_VIEW_TITLE);
			setTitle(title);
			
			setUpFragment(data);
			GuiManager.manager.onFragmentAttached(data);
		}
		else
			throw new ClassCastException("Activity must implement Gui.");
	}
}
