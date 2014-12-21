package de.lbl.purchasewatcher.gui.purchase;

import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.model.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import android.view.View.*;
import android.os.*;

public class PurchaseOverviewAdapter extends ViewAdapter
{
	List<Integer> list;
	AbsListView.LayoutParams params;
	List<Integer> filter;
	
	public PurchaseOverviewAdapter(List<Integer> filter){
		this.filter = filter;
//		list = SystemController.dataHandler.getAllPurchase(filter);
		params = new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 175);
	}
	
	public void update(){
		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Purchase getItem(int p1)
	{
		return null;
//		return list.get(p1);
	}

	@Override
	public long getItemId(int p1)
	{
		return p1;
	}

	@Override
	public View getView(int pos, View contentView, ViewGroup parent)
	{
		final Purchase p = getItem(pos);
		
		TextView v = null;
		if(contentView == null){
			v = new TextView(App.ctx); //LayoutInflater.from(App.context).inflate(R.
			v.setLayoutParams(params);
			v.setTextSize(18);
			v.setGravity(Gravity.CENTER_VERTICAL);
			v.setTextColor(App.ctx.getResources().getColor(android.R.color.holo_red_dark));
		}
		else
			v = (TextView) contentView;
			
		v.setText("ID: "+ p.id + ", " + p.date.toString());
		v.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v)
				{
					//App.debug("p "+p.id);
					Bundle b = new Bundle();
//					b.putString(Constants.KEY_VIEW_NAME, Constants.VIEW_PURCHASE);
//					b.putString(Constants.KEY_VIEW_MODE,Constants.MODE_DETAIL);
//					b.putInt(Constants.KEY_ID, p.id);
//					SystemController.tryAction(Constants.ACTION_DISPLAY_VIEW, b);
				}
		});
		
		return v;
	}

}
