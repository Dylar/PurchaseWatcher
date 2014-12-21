package de.lbl.purchasewatcher.system;

import org.json.JSONObject;

import de.lbl.purchasewatcher.model.Thingy;

import android.database.Cursor;

public interface StoreableFactory<T extends Storeable>
{
	public T createFromDatabase(Cursor c);

	public T createFromJsonObject(JSONObject json);
	
	public String getDatabaseTableName();

	public T reloadFromDatabase(T s);
}
