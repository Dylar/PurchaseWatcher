package de.lbl.purchasewatcher.model;

import android.database.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import org.json.*;
import java.text.*;
import android.util.*;

public class PurchaseFactory<T extends Storeable> implements StoreableFactory
{

	@Override
	public T createFromDatabase(Cursor c) {
		Purchase p = new Purchase();
		p.setDatabaseId(c.getInt(c.getColumnIndex(Purchase.VAR_ID)));
		
		//p.date = TODO
		
		try
		{
			long d = c.getLong(c.getColumnIndex(Purchase.VAR_DATE));
			p.date = Calendar.getInstance();
			p.date.setTime(new Date(d));
			//JSONObject ja = new JSONObject(c.getString(c.getColumnIndex(Purchase.VAR_THINGYS)));
			//Log.i("PurchaseFactory", ja.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return (T) p;
	}

	@Override
	public T createFromJsonObject(JSONObject json) {
		
		Thingy t = new Thingy();
		try {

			t.id =json.getInt(Thingy.VAR_ID);
			t.brandname = json.getString(Thingy.VAR_BRANDNAME);
			t.cost =json.getInt(Thingy.VAR_COST);
			t.type = json.getString(Thingy.VAR_TYPE);
			t.rank = json.getString(Thingy.VAR_RANK);
			t.purchase_id = json.getInt(Thingy.VAR_PURCHASE_ID);
			t.productname = json.getString(Thingy.VAR_PRODUCTNAME);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (T) t;
	}

	@Override
	public String getDatabaseTableName() {

		return Purchase.TABLE;
	}
}
