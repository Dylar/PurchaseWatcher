package de.lbl.purchasewatcher.model;



import android.database.*;
import de.lbl.purchasewatcher.system.*;
import org.json.*;

public class ThingyFactory <T extends Storeable> implements StoreableFactory
{
	private static final String TAG = "ThingyFactory";
	
	@Override
	public T createFromDatabase(Cursor c) {
		Thingy t = new Thingy();
		t.setDatabaseId(c.getInt(c.getColumnIndex(Thingy.VAR_ID)));
		t.brandname = c.getString(c.getColumnIndex(Thingy.VAR_BRANDNAME));
		t.productname = c.getString(c.getColumnIndex(Thingy.VAR_PRODUCTNAME));
		t.cost = c.getInt(c.getColumnIndex(Thingy.VAR_COST));
		t.type = c.getString(c.getColumnIndex(Thingy.VAR_TYPE));
		t.rank = c.getString(c.getColumnIndex(Thingy.VAR_RANK));
		t.purchase_id = c.getInt(c.getColumnIndex(Thingy.VAR_PURCHASE_ID));
		
		return (T) t;
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

		return Thingy.TABLE;
	}
}

