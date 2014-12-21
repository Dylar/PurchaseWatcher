package de.lbl.purchasewatcher.model;

import android.content.Intent;
import android.os.Bundle;
import de.lbl.purchasewatcher.system.App;
import de.lbl.purchasewatcher.system.Constants;
import de.lbl.purchasewatcher.system.Storeable;

public class PurchaseManager implements MainManager<Purchase>
{
	public static PurchaseManager	manager;
	private DataManager dh;

	public PurchaseManager()
	{
		dh = DataManager.manager;
	}
	
	@Override
	public void save(Purchase s)
	{
		dh.update(s);
		
		Intent intent = new Intent(Constants.BROADCAST_UPDATED_PURCHASE);
		intent.putExtra(Storeable.VAR_DATABASE_ID, s.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}

	@Override
	public Purchase load(long id)
	{
		return dh.getPurchaseById(id);
	}

	@Override
	public Purchase reload(Purchase p)
	{
		Purchase.FACTORY_PURCHASE.reloadFromDatabase(p);
		return p;
	}

	@Override
	public void createNew(Bundle b)
	{
		Purchase p = new Purchase();
		dh.update(p);

		Intent intent = new Intent(Constants.BROADCAST_NEW_PURCHASE);
		intent.putExtra(Storeable.VAR_DATABASE_ID, p.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}
	
	@Override
	public void delete(Purchase p)
	{
		dh.deletePurchases(p.getDatabaseId());
		
		Intent intent = new Intent(Constants.BROADCAST_DELETED_PURCHASE);
		intent.putExtra(Storeable.VAR_DATABASE_ID, p.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}
	
}