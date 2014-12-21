package de.lbl.purchasewatcher.model;

import java.util.Calendar;
import java.util.List;

import de.lbl.purchasewatcher.system.Database;
import de.lbl.purchasewatcher.system.Storeable;

public class DataManager
{
	private Database				db;
	public static DataManager	manager;


	public DataManager()
	{
		db = Database.getDatabase();
	}


	public void updatePurchase(Purchase p)
	{
		db.update(p);
	}


	public void deletePurchases(int... purchases)
	{
		if (purchases.length != 0)
			for (Integer i : purchases)
			{
				String where = Purchase.VAR_ID + "=" + i;
				db.deleteAll(Purchase.TABLE, where);
				where = Thingy.VAR_PURCHASE_ID + "=" + i;
				db.deleteAll(Thingy.TABLE, where);
			}
	}


	public void createNewPurchase()
	{
		Purchase p = new Purchase();
		p.date = Calendar.getInstance();
		db.insert(p);
	}


	public void update(Storeable t)
	{
		db.update(t);
	}


	public Thingy getThingyById(long id)
	{
		return db.getAllEntries(Thingy.FACTORY_THINGY, Thingy.VAR_DATABASE_ID + "=" + id).get(0);
	}

	public Purchase getPurchaseById(long id)
	{
		return db.getAllEntries(Purchase.FACTORY_PURCHASE, Purchase.VAR_ID + "=" + id).get(0);
	}

//	public List<Integer> getAllThidngyFrom(Purchase p)
//	{
//		List<Integer> list = db.getAllEntryIds(Thingy.FACTORY_THINGY, Thingy.VAR_PURCHASE_ID + "=" + p.id);
//		return list;
//	}




	public void deleteThingy(int databaseId)
	{
		
	}

}
