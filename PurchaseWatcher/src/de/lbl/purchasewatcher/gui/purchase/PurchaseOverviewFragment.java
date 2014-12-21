package de.lbl.purchasewatcher.gui.purchase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.gui.MainViewFragment;
import de.lbl.purchasewatcher.model.PurchaseManager;
import de.lbl.purchasewatcher.model.ThingyManager;
import de.lbl.purchasewatcher.system.App;
import de.lbl.purchasewatcher.system.Constants;
import de.lbl.purchasewatcher.system.Storeable;

public class PurchaseOverviewFragment extends MainViewFragment
{

	private static final String	TAG	= PurchaseOverviewFragment.class.getSimpleName();

	private ViewAdapter			adapt;
	
	private BroadcastReceiver	newPurchaseReceiver;


	@Override
	public void setUpFragment(Bundle data)
	{
		setName(VIEWNAME_PURCHASE_OVERVIEW);
		
		newPurchaseReceiver = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent)
			{
				Log.d(TAG, "NEW PURCHASE: " + intent.getLongExtra(Storeable.VAR_DATABASE_ID, -1));
				updateView();
			}
		};
	}

	@Override
	public void onResume()
	{
		App.ctx.registerReceiver(newPurchaseReceiver, new IntentFilter(Constants.BROADCAST_NEW_PURCHASE));
		super.onResume();
	}


	@Override
	public void updateView()
	{
//		PurchaseManager.manager.reload(purchase);//TODO first load all, but than will see criteria
		adapt.update();
	}


	@Override
	public void onPause()
	{
		App.ctx.unregisterReceiver(newPurchaseReceiver);
		super.onPause();
	}

	@Override
	public void releaseFragment()
	{

	}


	@Override
	public void handleActionbarClick(MenuItem item)
	{
		int id = item.getItemId();
		switch (id)
		{
			case R.id.create_purchase:
				createPurchase();
				break;
		}
	}


	private void createPurchase()
	{
		PurchaseManager.manager.createNew(null);
	}

}
