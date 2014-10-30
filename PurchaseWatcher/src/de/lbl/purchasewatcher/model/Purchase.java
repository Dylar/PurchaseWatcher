package de.lbl.purchasewatcher.model;
import android.content.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import org.json.*;

public class Purchase implements Storeable
{
	public static final String TABLE = "purchases";

	//public static final String VAR_ID = "id";
	public static final String VAR_DATE = "date";
	public static final String VAR_THINGYS = "thingys";

	public static final String SQL_CREATE = "CREATE TABLE "
	+ TABLE
	+ " (" + VAR_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ VAR_DATE+ " LONG default null,"
	+ VAR_THINGYS+" TEXT default null);";

	public int id = -1;
	public Calendar date;
	//public Date date;
	
	public List<Integer> thingys = new ArrayList<>();

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

		//cvs.put(VAR_ID, id);
		cvs.put(VAR_DATE, date.getTime().getTime());
		cvs.put(VAR_THINGYS, new JSONArray(thingys).toString());
		
		return cvs;
	}
}
