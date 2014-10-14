package de.lbl.purchasewatcher.model;

import de.lbl.purchasewatcher.system.*;
import android.content.*;

public class Thingy implements Storeable
{

	public static final String TABLE = "thingys";
	
	public static final String VAR_ID = "id";
	public static final String VAR_COST = "cost";
	public static final String VAR_PRODUCTNAME = "productname";
	public static final String VAR_BRANDNAME = "brandname";
	public static final String VAR_TYPE = "type";
	public static final String VAR_RANK = "rank";
	public static final String VAR_PURCHASE_ID = "purchase_id";
	
	public static final String SQL_CREATE = "CREATE TABLE "
	+ TABLE
	+ " (" + VAR_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ VAR_COST + " INTEGER default null,"
	+ VAR_PURCHASE_ID + " INTEGER default null,"
	+ VAR_PRODUCTNAME+ " TEXT default null,"
	+ VAR_BRANDNAME+" TEXT default null,"
	+ VAR_RANK+" TEXT default null,"
	+ VAR_TYPE+" TEXT default null);";
	
	public int id;
	public int purchase_id;
	
	public String productname;
	public String brandname;
	public String type;
	public String rank;

	public int cost;

	
	@Override
	public int getDatabaseId()
	{
		return id;
	}

	@Override
	public int getServerId()
	{
		return id;
	}

	@Override
	public void setDatabaseId(int id)
	{
		this.id = id;
	}

	@Override
	public String getDatabaseTable()
	{
		return TABLE;
	}

	@Override
	public ContentValues getContentValues()
	{
		ContentValues cvs = new ContentValues();

		cvs.put(VAR_BRANDNAME, brandname);
		cvs.put(VAR_PRODUCTNAME, productname);
		cvs.put(VAR_ID, getDatabaseId());
		cvs.put(VAR_TYPE, type);
		cvs.put(VAR_RANK, rank);
		cvs.put(VAR_COST, cost);
		
		return cvs;
	}

}
