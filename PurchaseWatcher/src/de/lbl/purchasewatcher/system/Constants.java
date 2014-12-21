package de.lbl.purchasewatcher.system;

import android.content.Intent;
import de.lbl.purchasewatcher.R;

public class Constants
{
	public static final int ACTION_DISPLAY_VIEW = 0;
	public static final int ACTION_CREATE_PURCHASE = 1;
	public static final int ACTION_DELETE_PURCHASE = 2;
//	public static final int ACTION_CREATE_THINGY = 3;
	public static final int ACTION_DELETE_THINGY = 4;
	
	public static final String KEY_DATA_THINGYS = "key_data_thingys";
	public static final String KEY_DATA_THINGY = "key_data_thingy";
	public static final String KEY_DATA_PURCHASE = "key_data_purchase";
	

	public static final String	BROADCAST_NEW_THINGY	= "broadcast_new_thingy";
	public static final String	BROADCAST_UPDATED_THINGY	= "broadcast_updated_thingy";
	public static final String	BROADCAST_DELETED_THINGY	= "broadcast_deleted_thingy";
	public static final String	BROADCAST_NEW_PURCHASE	= "broadcast_new_purchase";
	public static final String	BROADCAST_UPDATED_PURCHASE	= "broadcast_updated_purchase";
	public static final String	BROADCAST_DELETED_PURCHASE	= "broadcast_deleted_purchase";
	
	//public static final int SHOW_VIEW_THINGY_EDITOR = 1;
	//public static final int SHOW_VIEW_PURCHASE_OVERVIEW = 2;
}
