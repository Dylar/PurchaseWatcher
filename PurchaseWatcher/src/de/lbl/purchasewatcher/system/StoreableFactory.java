package de.lbl.purchasewatcher.system;

import android.database.*;
import org.json.*;

public interface StoreableFactory<T extends Storeable>
{

	public T createFromDatabase(Cursor c);

	public T createFromJsonObject(JSONObject json);
	
	public String getDatabaseTableName();
}
