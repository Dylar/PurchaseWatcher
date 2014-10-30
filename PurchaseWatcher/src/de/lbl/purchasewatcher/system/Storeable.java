package de.lbl.purchasewatcher.system;

import android.content.ContentValues;

/**
 * Interface for resources that can be stored inside the {@link Database}.
 *
 */
public interface Storeable
{

	public static final String VAR_ID = "id";
	/**
	 * Returns the resource's id within the internal databse.
	 *
	 * @return the resources id
	 */
	public int getDatabaseId();

	/**
	 * Returns the resource's server id.
	 *
	 * @return the resources server id
	 */
	public int getServerId();

	/**
	 * Sets the resource's id within the internal databse. This should only be done when the entry in the databse is created for the first time.
	 *
	 * @param id
	 * the resources new id.
	 * @return the resources id
	 */
	public void setDatabaseId(int id);

	/**
	 * Returns the name of the database table that contains this resource.
	 *
	 * @return the table name
	 */
	public String getDatabaseTable();

	/**
	 * Creates a {@link ContentValues} representation of this resource. This is needet to store it inside the database.
	 *
	 * @return the table name
	 */
	public ContentValues getContentValues();
	
}
