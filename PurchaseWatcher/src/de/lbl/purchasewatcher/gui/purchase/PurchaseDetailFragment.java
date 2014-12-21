package de.lbl.purchasewatcher.gui.purchase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import de.lbl.purchasewatcher.R;
import de.lbl.purchasewatcher.gui.GuiManager;
import de.lbl.purchasewatcher.gui.MainViewFragment;
import de.lbl.purchasewatcher.model.Purchase;
import de.lbl.purchasewatcher.model.PurchaseManager;
import de.lbl.purchasewatcher.model.ThingyManager;
import de.lbl.purchasewatcher.system.App;
import de.lbl.purchasewatcher.system.Constants;

public class PurchaseDetailFragment extends MainViewFragment
{
	public static final String	TAG	= PurchaseDetailFragment.class.getSimpleName();

	private Purchase				purchase;
	private ViewAdapter			adapt;

	private BroadcastReceiver	newThingyReceiver;
	private BroadcastReceiver	deletedPurchaseReceiver;


	@Override
	public void setUpFragment(Bundle data)
	{
		setName(VIEWNAME_PURCHASE_DETAILS);
		purchase = PurchaseManager.manager.load(data.getInt(Purchase.VAR_ID));
		
		newThingyReceiver = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent)
			{
				Log.d(TAG, "NEW THINGY: " + intent.getLongExtra(Purchase.VAR_ID, -1));
				updateView();
			}
		};
		
		deletedPurchaseReceiver = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent)
			{
				Log.d(TAG, "PURCHASE DELETED: " + intent.getLongExtra(Purchase.VAR_ID, -1));
				GuiManager.manager.back();
			}
		};

	}


	@Override
	public void onResume()
	{
		App.ctx.registerReceiver(newThingyReceiver, new IntentFilter(Constants.BROADCAST_NEW_THINGY));
		App.ctx.registerReceiver(deletedPurchaseReceiver, new IntentFilter(Constants.BROADCAST_DELETED_PURCHASE));
		super.onResume();
	}


	@Override
	public void updateView()
	{
		PurchaseManager.manager.reload(purchase);
		adapt.update();
	}


	@Override
	public void onPause()
	{
		App.ctx.unregisterReceiver(newThingyReceiver);
		App.ctx.unregisterReceiver(deletedPurchaseReceiver);
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
			case R.id.create_thingy:
				createNewThingy();
				break;
			case R.id.delete_purchase:
				deleteThisPurchase();
				break;
			case R.id.btn3:
				break;
		}
	}

	@Override
	public int getViewMenuLayoutId()
	{
		return R.menu.viewmenu_purchase_detail;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View root = inflater.inflate(R.layout.fragment_purchase_details, container, false);
		adapt = new PurchaseDetailAdapter(purchase);
		((ListView) App.findView(root, R.id.listview_thingys)).setAdapter(adapt);
		return root;
	}



	private void createNewThingy()
	{
		Bundle b = new Bundle();
		b.putInt(Purchase.VAR_ID, purchase.id);
		ThingyManager.manager.createNew(b);
	}


	private void deleteThisPurchase()
	{
		PurchaseManager.manager.delete(purchase);
	}

}
