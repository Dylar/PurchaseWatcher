package de.lbl.purchasewatcher.gui;
import android.app.*;
import de.lbl.purchasewatcher.system.*;
import android.os.*;
import android.view.*;
import de.lbl.purchasewatcher.*;

public abstract class MainViewFragment extends Fragment
{
	public Gui gui;
	public String name = Constants.VIEW_START;
	public String title = "You forgot something - dunno wat";
	public String mode = Constants.MODE_OVERVIEW;
	
	public abstract void setUpFragment(Bundle data);
	public abstract void handleActionbarClick(MenuItem item);
	public abstract void updateView();
	public abstract void changeMode(Bundle data);
	
	public int getViewMenuLayoutId(){
		return R.menu.view_btns;
	}
	
	public String getViewTitle(){
		return title;
	}
	
	public String getViewName(){
		return name;
	}
	
	public String getMode(){
		return mode;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setMode(String mode){
		this.mode = mode;
	}
	
	@Override
	public void onDetach()
	{
		gui = null;
		super.onDetach();
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		if(activity instanceof Gui){
			gui = (Gui) activity;

			Bundle data = getArguments();
			
			String mode = data.getString(Constants.KEY_VIEW_MODE);
			setTitle(mode);
			setName(data.getString(Constants.KEY_VIEW_NAME));
			setMode(mode);
			
			setUpFragment(data);
			data.putString(Constants.KEY_VIEW_TITLE,getViewTitle());
			gui.onSectionAttached(data);
		}
		else
			throw new ClassCastException("Activity must implement Gui.");
		
	}
}
