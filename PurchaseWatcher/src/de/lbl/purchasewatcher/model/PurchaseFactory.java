package de.lbl.purchasewatcher.model;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.database.Cursor;
import de.lbl.purchasewatcher.system.StoreableFactory;

public class PurchaseFactory implements StoreableFactory<Purchase>
{

	@Override
	public Purchase createFromDatabase(Cursor c)
	{
		Purchase p = new Purchase();
		p.setDatabaseId(c.getInt(c.getColumnIndex(Purchase.VAR_ID)));

		// p.date = TODO

		try
		{
			long d = c.getLong(c.getColumnIndex(Purchase.VAR_DATE));
			p.date = Calendar.getInstance();
			p.date.setTime(new Date(d));
			// JSONObject ja = new
			// JSONObject(c.getString(c.getColumnIndex(Purchase.VAR_THINGYS)));
			// Log.i("PurchaseFactory", ja.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return p;
	}
	
	@Override
	public Purchase reloadFromDatabase(Purchase p)
	{
		return DataManager.manager.getPurchaseById(p.getDatabaseId());
	}


	@Override
	public Purchase createFromJsonObject(JSONObject json)
	{
		return null;
	}


	@Override
	public String getDatabaseTableName()
	{
		return Purchase.TABLE;
	}

}
