package de.lbl.purchasewatcher.model;

import de.lbl.purchasewatcher.system.*;
import java.util.*;

public class DataHandler
{
	//public static DataHandler dh = new DataHandler();
	private Database db;
	//List<Thingy> allThingys = new ArrayList<>();
	//List<Purchase> allPurchase  = new ArrayList<>();
	//List<ThingyAttributes> allThingyAttributes  = new ArrayList<>() ;

	public DataHandler()
	{//TODO prozess callback 
		db = Database.getDatabase();
		//createDummys();
		loadPurchases();
	}

	public void updatePurchase(Purchase p)
	{
		db.update(p);
	}

	public void deletePurchases(int... purchases)
	{
		if(purchases.length != 0)
		for(Integer i : purchases){
			String where = Purchase.VAR_ID +"="+ i;
			db.deleteAll(Purchase.TABLE,where);
			where = Thingy.VAR_PURCHASE_ID +"="+ i;
			db.deleteAll(Thingy.TABLE,where);
		}
	}

	public List<Thingy> getAllThingyFrom(Purchase p)
	{
		List<Thingy> list = db.getAllEntries(new ThingyFactory<Thingy>(),Thingy.VAR_PURCHASE_ID + "=" + p.id);
		return list;
	}

	private void loadPurchases()
	{
		try
		{
			//allPurchase = db.getAllEntries(new PurchaseFactory<Purchase>());
		}
		catch (Exception e)
		{
			//createDummys();
		}
	}

//	private void createDummys()
//	{
//		Thingy t1 = new Thingy();
//		t1.brandname = "ja";
//		t1.cost = 2;
//		t1.productname = "pizza";
//		t1.id = 1;
//		t1.purchase_id = 1;
//
//		Thingy t2 = new Thingy();
//		t2.brandname = "ja";
//		t2.cost = 42;
//		t2.productname = "pizza2";
//		t2.id = 2;
//		t2.purchase_id = 1;
//
//		Purchase p = new Purchase();
//		p.date = new Date();
//		//p.id = 1;
//		p.thingys.add(t1.id);
//		p.thingys.add(t2.id);
//
//		allThingys.add(t1);
//		allThingys.add(t2);
//		allPurchase.add(p);
//	}

	public List<Purchase> getAllPurchase(List<Integer> filter)
	{
		List<Purchase> list = new ArrayList<>();
		if (filter == null)
		{
			list = db.getAllEntries(new PurchaseFactory<Purchase>());
		}
		else
		{
//			for (Purchase p : allPurchase)
//			{
//				if (filter.contains(p.id))
//				{
//					list.add(p);
//				}
//			}
		}
		return list;
	}
	
	public Purchase getPurchase(int id){
		return db.getEntryById(id, new PurchaseFactory<Purchase>());
	}
	
	public Thingy getThingy(int id){
		return db.getEntryById(id, new ThingyFactory<Thingy>());
	}

	public void loadingData()
	{
		loadThingys();
	}

	private void loadThingys()
	{
		//allPurchase
//		allThingyAttributes = db.getAllEntries(new ThingyAttributesFactory());
//		allThingys = db.getAllEntries(new ThingyFactory());
//		allThingyAttributes = db.getAllEntries(new ThingyAttributesFactory());
	}

	public void createNewPurchase()
	{
		Purchase p = new Purchase();
		p.date = Calendar.getInstance();
		db.insert(p);
		//allPurchase.add(p);
	}
	
	public void createNewThingy(int purchaseId)
	{
		Thingy t = new Thingy();
		t.purchase_id = purchaseId;
		db.insert(t);
	}
}
