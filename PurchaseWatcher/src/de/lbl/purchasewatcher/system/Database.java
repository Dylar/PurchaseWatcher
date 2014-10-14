package de.lbl.purchasewatcher.system;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.location.*;
import android.os.*;
import de.lbl.purchasewatcher.model.*;
import java.io.*;
import java.util.*;

public class Database extends SQLiteOpenHelper
 {

		private static final String TAG = "Database";
		private static final String DATABASE_NAME = "purchasewatcher.db";
		private static final int DATABASE_VERSION = 1;

		private static Database dbManager;
		private static SQLiteDatabase theDatabase;
		
		private Database(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public static synchronized Database getDatabase() {
// as a singleton, the db supports better multithreading
			if (dbManager == null) {
				dbManager = new Database(App.context);
			}

			return dbManager;

		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
// with these, the db supports better multithreading
			db.execSQL("PRAGMA read_uncommitted = true;");
			db.execSQL("PRAGMA synchronous=OFF;");
		}

		@Override
		public synchronized SQLiteDatabase getReadableDatabase() {
// with this approach, the db supports better multithreading
// also, its important to NEVER close the database.
			if (theDatabase == null) {
				theDatabase = super.getWritableDatabase();
			}

			return theDatabase;
		}

		@Override
		public synchronized SQLiteDatabase getWritableDatabase() {
			return this.getReadableDatabase();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(Thingy.SQL_CREATE);
			

// db.execSQL("CREATE VIRTUAL TABLE suggestions USING fts3(name, type, id);");
		}

		public Cursor getSuggestionsCursor(String word, int limit) {
			return getReadableDatabase().rawQuery("SELECT * FROM tasks WHERE name LIKE '%" + word + "%' LIMIT " + limit, null);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO: when database changes, change version number.
// if (getCurrentUser() != null) {
// GaroodaApplication.getDatabase().deleteAll(Client.TABLE);
// GaroodaApplication.getDatabase().deleteAll(StreamMessage.TABLE);
// GaroodaApplication.getDatabase().deleteAll(Task.TABLE);
// GaroodaApplication.getDatabase().deleteAll(TimeLog.TABLE);
// GaroodaApplication.getDatabase().deleteAll(Conversation.TABLE);
// GaroodaApplication.getDatabase().deleteAll(KnownLocation.TABLE);
// GaroodaApplication.getDatabase().deleteAll(Contact.TABLE);
//
// asyncDownloadAllClients();
// asyncDownloadAllTasks();
// asyncDownloadAllConversations();
// asyncDownloadAllKnownLocations();
// asyncDownloadAllContacts();
// }

		}

		public synchronized Storeable insert(Storeable s) {

			SQLiteDatabase db = getWritableDatabase();

			db.beginTransaction();
			try {
				int id = (int) db.insert(s.getDatabaseTable(), null, s.getContentValues());
				s.setDatabaseId(id);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}

			return s;
		}

		public synchronized boolean update(Storeable s) {
			SQLiteDatabase db = getWritableDatabase();
			int affectedRows = 0;

			db.beginTransaction();
			try {
				if(s.getServerId() == -1)
					affectedRows = db.update(s.getDatabaseTable(), s.getContentValues(), "id=" + s.getDatabaseId(), null);
				else
					affectedRows = db.update(s.getDatabaseTable(), s.getContentValues(), "server_id=" + s.getServerId(), null);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}

			if (affectedRows == 0) {
// Log.i("Database", "update of " + s + " failed. Inserting instead.");
				insert(s);
			}

// // database.close();

			return affectedRows > 0;
		}

		public synchronized <T extends Storeable> void updateAll(List<T> list) {
			SQLiteDatabase db = getWritableDatabase();
			db.beginTransaction();
			try {
				for (T s : list) {
					db.update(s.getDatabaseTable(), s.getContentValues(), null, null);
				}
				db.setTransactionSuccessful();

			} finally {
				db.endTransaction();
// db.close();
			}

		}

		public synchronized boolean delete(Storeable s) {
			SQLiteDatabase database = getWritableDatabase();
			int affectedRows = database.delete(s.getDatabaseTable(), "_id=" + s.getDatabaseId(), null);
// database.close();

			return affectedRows > 0;
		}

		public <T extends Storeable> List<T> getAllEntries(StoreableFactory<T> factory) {

			List<T> resultList = new ArrayList<T>();
			SQLiteDatabase database = getWritableDatabase();

			Cursor c = database.rawQuery("SELECT * FROM " + factory.getDatabaseTableName(), null);

			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					final T item = factory.createFromDatabase(c);
					if (item != null) {
						resultList.add(item);
					}
					c.moveToNext();
				}
			}
			c.close();
// database.close();

			return resultList;
		}

	public <T extends Storeable> List<T> getAllEntries(StoreableFactory<T> factory, String where) {
			List<T> resultList = new ArrayList<T>();
			SQLiteDatabase database = getWritableDatabase();

			Cursor c = database.rawQuery("SELECT * FROM " + factory.getDatabaseTableName() + " WHERE " + where, null);
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					final T item = factory.createFromDatabase(c);
					if (item != null) {
						resultList.add(item);
					}
					c.moveToNext();
				}
			}
			c.close();
// database.close();

			return resultList;
		}


	public <T extends Storeable> T getEntryByServerId(int serverID, StoreableFactory<T> factory) {
			T item = null;
			SQLiteDatabase database = getWritableDatabase();

			final String table = factory.getDatabaseTableName();
			Cursor c = database.rawQuery("SELECT * FROM " + table + " WHERE server_id = " + serverID, null);

			if (c.moveToFirst()) {
				item = factory.createFromDatabase(c);
			}
			c.close();
// database.close();

			return item;

		}

	public <T extends Storeable> T getEntryById(int dbId, StoreableFactory<T> factory) {
			T item = null;
			SQLiteDatabase database = getWritableDatabase();

			final String table = factory.getDatabaseTableName();
			Cursor c = database.rawQuery("SELECT * FROM " + table + " WHERE _id = " + dbId, null);

			if (c.moveToFirst()) {
				item = factory.createFromDatabase(c);
			}
			c.close();
// database.close();

			return item;

		}

//			public boolean containsResourceFromServer(Resource res) {
//
//			SQLiteDatabase database = getWritableDatabase();
//
//			final String table = res.getDatabaseTable();
//			Cursor c = database.rawQuery("SELECT * FROM " + table + " WHERE server_id = " + res.getServerId(), null);
//
//			boolean databaseContainsEntry = c.getCount() > 0;
//			c.close();
//// database.close();
//
//			return databaseContainsEntry;
//		}

		public void deleteAll(String table) {
			deleteAll(table, null);
		}

		public void deleteAll(String table, String where) {
			SQLiteDatabase database = getWritableDatabase();
			database.delete(table, where, null);
// database.close();
		}

		public <T extends Storeable> void insertAll(List<T> allStoredLocations) {
			SQLiteDatabase db = getWritableDatabase();
			db.beginTransaction();
			try {
				for (T s : allStoredLocations) {
					db.insert(s.getDatabaseTable(), null, s.getContentValues());
				}
				db.setTransactionSuccessful();

			} finally {
				db.endTransaction();
// db.close();
			}
		}

		public static void copyDatabaseToSD() {
			new Thread() {
				@Override
				public void run() {
					File f = new File("/data/data/de.appdream.garooda/databases/" + DATABASE_NAME);
					final File exportFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GAROODA/");
					if (!exportFile.exists()) {
						exportFile.mkdirs();
					}
					if (f.exists()) {
						try {
							BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(new File(exportFile, DATABASE_NAME)));
							BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f));

							byte[] data = new byte[32 * 1024];

							while ((fis.read(data)) > 0) {
								fos.write(data);
							}

							fos.flush();
							fos.close();
							fis.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}.start();
		}

		

	}

	
