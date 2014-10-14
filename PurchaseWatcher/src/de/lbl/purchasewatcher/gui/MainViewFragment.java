package de.lbl.purchasewatcher.gui;
import android.app.*;
import de.lbl.purchasewatcher.system.*;
import android.os.*;

public abstract class MainViewFragment extends Fragment
{
	public Gui gui;
	public String name = Constants.VIEW_START;
	public String title = "You forgot something";
	
	public abstract void setUpFragment(Bundle data);
	
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
			
			setTitle(data.getString(Constants.KEY_VIEW_MODE));
			setName(data.getString(Constants.KEY_VIEW_NAME));
			
			setUpFragment(data);
			data.putString(Constants.KEY_VIEW_TITLE,getViewTitle());
			gui.onSectionAttached(data);
		}
	}
}
