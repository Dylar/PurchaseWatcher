package de.lbl.purchasewatcher.gui;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.system.*;

public class PurchaseViewFragment extends MainViewFragment 
{

	PurchaseOverviewAdapter adapt;
	
	@Override
	public void setUpFragment(Bundle data)
	{
		
		//this.setArguments(data);
	}
	
	@Override
	public void setTitle(String mode){
		switch(mode){
			case Constants.MODE_OVERVIEW:
				title = "Purchase overview";
				break;
			case Constants.MODE_DETAIL:
				title = "Purchase details";
				break;
			case Constants.MODE_EDIT:
				title = "Purchase editor";
				break;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		ListView root = new ListView(inflater.getContext()); //inflater.inflate(R.layout.fragment_purchase_view, container,false);
		
		adapt = new PurchaseOverviewAdapter(null);
		
		root.setAdapter(adapt);
		
		return root;
	}
	
	
	
}
