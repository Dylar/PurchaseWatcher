package de.lbl.purchasewatcher.model;



import org.json.JSONObject;

import android.database.Cursor;
import de.lbl.purchasewatcher.system.Storeable;
import de.lbl.purchasewatcher.system.StoreableFactory;

public class ThingyFactory implements StoreableFactory<Thingy>
{
	private static final String TAG = "ThingyFactory";
	
	@Override
	public Thingy createFromDatabase(Cursor c) {
		Thingy t = new Thingy();
		t.setDatabaseId(c.getInt(c.getColumnIndex(Thingy.VAR_DATABASE_ID)));
		t.brandname = c.getString(c.getColumnIndex(Thingy.VAR_BRANDNAME));
		t.productname = c.getString(c.getColumnIndex(Thingy.VAR_PRODUCTNAME));
		t.cost = c.getInt(c.getColumnIndex(Thingy.VAR_COST));
		t.type = c.getString(c.getColumnIndex(Thingy.VAR_TYPE));
		t.rank = c.getString(c.getColumnIndex(Thingy.VAR_RANK));
		t.purchase_id = c.getInt(c.getColumnIndex(Thingy.VAR_PURCHASE_ID));
		
		return t;
	}

	@Override
	public Thingy createFromJsonObject(JSONObject json) {

		Thingy t = new Thingy();
		try {

			t.id =json.getInt(Thingy.VAR_DATABASE_ID);
			t.brandname = json.getString(Thingy.VAR_BRANDNAME);
			t.cost =json.getInt(Thingy.VAR_COST);
			t.type = json.getString(Thingy.VAR_TYPE);
			t.rank = json.getString(Thingy.VAR_RANK);
			t.purchase_id = json.getInt(Thingy.VAR_PURCHASE_ID);
			t.productname = json.getString(Thingy.VAR_PRODUCTNAME);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public String getDatabaseTableName() {

		return Thingy.TABLE;
	}

	@Override
	public Thingy reloadFromDatabase(Thingy s)
	{
		// TODO Auto-generated method stub
		return null;
	}
}

